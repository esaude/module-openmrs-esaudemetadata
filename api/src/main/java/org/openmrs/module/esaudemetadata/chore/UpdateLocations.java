package org.openmrs.module.esaudemetadata.chore;

import org.apache.commons.lang3.StringUtils;
import org.openmrs.Location;
import org.openmrs.LocationAttribute;
import org.openmrs.LocationAttributeType;
import org.openmrs.api.AdministrationService;
import org.openmrs.api.LocationService;
import org.openmrs.api.context.Context;
import org.openmrs.util.OpenmrsClassLoader;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;

@Component("esaudemetadata.chore.updateLocations")
public class UpdateLocations extends AbstractChore {

    /**
     * @see org.openmrs.module.esaudemetadata.chore.AbstractChore#perform(java.io.PrintWriter)
     */
    @Override
    public void perform(PrintWriter output) {
        uploadLocations();
    }
    void uploadLocations(){
        LocationService locationService = Context.getLocationService();
        InputStream path = OpenmrsClassLoader.getInstance().getResourceAsStream("metadata/health_facilities.csv");

        String line = "";
        String cvsSplitBy = ",";
        String headLine = "";
        String health_facility_code = "";
        String facility_name = "";
        String province = "";
        String district = "";

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(path, "UTF-8"));
            //exclude the first line as this holds the column headers
            headLine = br.readLine();
            while ((line = br.readLine()) != null) {

                String[] records = line.split(cvsSplitBy);
                health_facility_code = records[0];
                province = records[1];
                district = records[2];
                facility_name = records[3];
                //facility name must be present at all time
                if (StringUtils.isNotEmpty(facility_name)) {
                    Location location = locationService.getLocation(facility_name);
                    //check whether that location does not exist in the database
                    if (location == null) {
                        //create the location and associate it with respective metadata
                        Location newLocation = new Location();
                        newLocation.setName(facility_name);
                        newLocation.setCreator(Context.getAuthenticatedUser());
                        newLocation.setStateProvince(province);
                        newLocation.setCountyDistrict(district);

                        //set the facility unique code for this location
                        setLocationAttribute(health_facility_code, newLocation);
                        locationService.saveLocation(newLocation);
                    }
                    //get the location and check if the health facility code is set
                    //check for location attribute for this location, if it exists means we already set it up
                    //No need to repeat the process
                    if(location != null) {
                        setLocationAttribute(health_facility_code, location);
                        location.setCountyDistrict(district);
                        location.setStateProvince(province);

                        //save the location, this will edit existing one
                        locationService.saveLocation(location);
                    }

                }
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        //delete unwanted locations
        removeNonMatchingLocations();
    }
    LocationAttribute setLocationAttribute(String facilityCode, Location location){
        LocationService locationService = Context.getLocationService();
        LocationAttributeType locationAttributeType = locationService.getLocationAttributeTypeByUuid("132895aa-1c88-11e8-b6fd-7395830b63f3");
        LocationAttribute attribute = new LocationAttribute();

        if (location != null) {
            Collection<LocationAttribute> availableAttributes = location.getActiveAttributes();
            if(availableAttributes.size() == 0){
                //this means there are no attribute created, we therefore create one for this location
                attribute.setDateCreated(new Date());
                attribute.setAttributeType(locationAttributeType);
                attribute.setValue(facilityCode);
                attribute.setCreator(Context.getAuthenticatedUser());
                location.addAttribute(attribute);
            }
            else {
                //this means we already have attributes with the set values, let only focus on the one with mfl code
                //we loop through all the attributes that are availabe if we find one with location attribute we just update it
                //we we don't get one we just create new

                for (LocationAttribute locationAttribute : availableAttributes) {

                    if (locationAttribute.getAttributeType().equals(locationAttributeType)) {
                        locationAttribute.setValue(facilityCode);
                        location.addAttribute(locationAttribute);
                        break;
                    }
                    else {
                        //create new one here
                        attribute.setDateCreated(new Date());
                        attribute.setAttributeType(locationAttributeType);
                        attribute.setValue(facilityCode);
                        attribute.setCreator(Context.getAuthenticatedUser());
                        location.addAttribute(attribute);
                    }
                }
            }

        }

        return attribute;
    }

    private void removeNonMatchingLocations(){
        AdministrationService as = Context.getAdministrationService();
        String locations_in_attributes_table = "SELECT location_id FROM location_attribute";
        String locations_to_remove = "UPDATE location SET retired=1, retire_reason='Not used' WHERE location_id NOT IN("+locations_in_attributes_table+")";
        as.executeSQL(locations_to_remove, false);
    }




}
