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
import org.openmrs.api.AdministrationService;
import org.openmrs.api.LocationService;
import org.openmrs.api.context.Context;
import org.openmrs.util.OpenmrsClassLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 *  class that will map correctly a health facility with unique code, province and district
 */

public class HealthFacilities {

    protected Log log = LogFactory.getLog(getClass());

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

    public static LocationAttribute setLocationAttribute(String facilityCode, Location location){
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

    public static void assignFacilityCodeToUnKownLocation(String uuid){
        LocationService locationService = Context.getLocationService();
        Location location = locationService.getLocationByUuid(uuid);
        if(location != null){
            setLocationAttribute("0000001", location);
            location.setCountyDistrict("Desconhecido");
            location.setStateProvince("Desconhecido");

            //save the location, this will edit existing one
            locationService.saveLocation(location);
        }
    }

}
