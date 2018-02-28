/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.esaudemetadata;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Location;
import org.openmrs.LocationAttribute;
import org.openmrs.LocationAttributeType;
import org.openmrs.api.LocationService;
import org.openmrs.api.context.Context;
import org.openmrs.util.OpenmrsClassLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;

/**
 *  class that will map correctly a health facility with unique code, province and district
 */

public class HealthFacilities {

    protected Log log = LogFactory.getLog(getClass());

    public static void uploadLocations(){
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
                    }
                    //get the location and check if the health facility code is set
                    //check for location attribute for this location, if it exists means we already set it up
                    //No need to repeat the process
                    if(location != null) {
                        Collection<LocationAttribute> locationAttributeValues = location.getActiveAttributes();
                        String valueOfAttribute = "";
                        if (locationAttributeValues.size() > 0) {
                            for (LocationAttribute locationAttribute : locationAttributeValues) {
                                if (locationAttribute.getAttributeType().getUuid().equals("132895aa-1c88-11e8-b6fd-7395830b63f3")) {
                                    //pick that value and break from the for loop
                                    valueOfAttribute = locationAttribute.getValue().toString();
                                }
                            }
                        }
                        //now that we have the attribute value we need, we can now check if location exists and no attribute value is set
                        if (StringUtils.isEmpty(valueOfAttribute)){
                            //this means the location needs the attribute as required
                        }
                    }

                }
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void createLocationAttributeType(){
        LocationService locationService = Context.getLocationService();
        LocationAttributeType locationAttributeType = locationService.getLocationAttributeTypeByUuid("132895aa-1c88-11e8-b6fd-7395830b63f3");
        //if missing create one here
        if(locationAttributeType == null){
            LocationAttributeType type = new LocationAttributeType();
            type.setName("Health facility code");
            type.setCreator(Context.getAuthenticatedUser());
            type.setDatatypeClassname("org.openmrs.customdatatype.datatype.FreeTextDatatype");
            type.setDescription("Attribute that hold the unique code for the facility");
            type.setUuid("132895aa-1c88-11e8-b6fd-7395830b63f3");
            locationService.saveLocationAttributeType(type);
        }

    }
}
