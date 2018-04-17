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
import org.openmrs.Concept;
import org.openmrs.ConceptDatatype;
import org.openmrs.ConceptNumeric;
import org.openmrs.api.ConceptService;
import org.openmrs.api.context.Context;
import org.openmrs.util.OpenmrsClassLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ConceptsRanges {

    public static void setUpConceptsMinAndMaxValues(){

        InputStream path = OpenmrsClassLoader.getInstance().getResourceAsStream("metadata/concepts_value_ranges_1.csv");
        ConceptService service = Context.getConceptService();
        ConceptDatatype numericConceptDatatype = service.getConceptDatatypeByUuid("8d4a4488-c2cc-11de-8d13-0010c6dffd0f");

        String line = "";
        String cvsSplitBy = ",";
        String headLine = "";
        String concept_uuid = "";
        String min = "";
        String max = "";
        String units = "";

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(path, "UTF-8"));
            //exclude the first line as this holds the column headers
            headLine = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] records = line.split(cvsSplitBy);
                concept_uuid = records[1];
                min = records[5];
                units = records[7];
                max = records[8];
                if(StringUtils.isNotEmpty(concept_uuid) && StringUtils.isNotBlank(concept_uuid)){
                    Concept concept = Dictionary.getConcept(concept_uuid);
                    if(concept != null && concept.getDatatype().equals(numericConceptDatatype)){
                        ConceptNumeric conceptNumeric = service.getConceptNumeric(concept.getConceptId());
                        if(conceptNumeric != null) {
                            if (StringUtils.isNotEmpty(max)) {
                                conceptNumeric.setHiAbsolute(Double.parseDouble(max));
                            }
                            if (StringUtils.isNotEmpty(min)) {
                                conceptNumeric.setLowAbsolute(Double.parseDouble(min));
                            }
                            if (StringUtils.isNotEmpty(units)) {
                                conceptNumeric.setUnits(units);
                            }
                            //save back the concept
                            service.saveConcept(concept);
                        }
                    }

                }


            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
