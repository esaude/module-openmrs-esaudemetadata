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

import org.openmrs.GlobalProperty;
import org.openmrs.api.context.Context;

public class EsaudeCoreUtils {

    /**
     * Sets an untyped global property
     * @param property the property name
     * @param value the property value
     */
    public static void setGlobalProperty(String property, String value) {
        GlobalProperty gp = Context.getAdministrationService().getGlobalPropertyObject(property);
        if (gp == null) {
            gp = new GlobalProperty();
            gp.setProperty(property);
        }
        gp.setPropertyValue(value);
        Context.getAdministrationService().saveGlobalProperty(gp);
    }
}
