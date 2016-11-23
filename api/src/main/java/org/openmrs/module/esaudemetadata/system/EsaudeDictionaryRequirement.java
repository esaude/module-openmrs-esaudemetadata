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
package org.openmrs.module.esaudemetadata.system;

import org.openmrs.api.context.Context;
import org.openmrs.module.esaudemetadata.EsaudeMetadataUtils;
import org.openmrs.module.esaudemetadata.requirement.Requirement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Requirement for esaude dictionary. This is instantiated as a bean in the application context so that the version value
 * can be taken from the project POM.
 */
public class EsaudeDictionaryRequirement implements Requirement {

    /**
     * Name of global property that stores database concepts version
     */

    public String requiredVersion = EsaudeMetadataUtils._OtherMetadata.requiredConceptVersion;

    /**
     * @see Requirement#getName()
     */
    @Override
    public String getName() {
        return "esaude Dictionary";
    }

    /**
     * @see org.openmrs.module.esaudemetadata.requirement.Requirement#getRequiredVersion()
     */
    @Override
    public String getRequiredVersion() {
        return requiredVersion;
    }

    /**
     * Sets the required version
     * @param requiredVersion the required version
     */
    public void setRequiredVersion(String requiredVersion) {
        this.requiredVersion = requiredVersion;
    }

    /**
     * @see org.openmrs.module.esaudemetadata.requirement.Requirement#getFoundVersion()
     */
    @Override
    public String getFoundVersion() {
        return Context.getAdministrationService().getGlobalProperty(EsaudeMetadataUtils._GlobalProperties.GP_DICTIONARY_VERSION);
    }

    /**
     * @see org.openmrs.module.esaudemetadata.requirement.Requirement#isSatisfied()
     */
    @Override
    public boolean isSatisfied() {
        return checkEsaudeDictionaryVersions(requiredVersion, getFoundVersion());
    }

    /**
     * Checks found esaude version against the required version.
     * @param required the required version
     * @param found the found version
     * @return true if found version is equal or greater to the required version
     */
    protected static boolean checkEsaudeDictionaryVersions(String required, String found) {
        if (found == null) {
            return false;
        }

        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        try {
            Date requiredDate = format.parse(required);
            Date foundDate = format.parse(found);

            return foundDate.equals(requiredDate) || foundDate.after(requiredDate);
        } catch (Exception e) {
            return false;
        }
    }

}
