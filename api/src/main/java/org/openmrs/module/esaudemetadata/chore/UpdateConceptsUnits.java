package org.openmrs.module.esaudemetadata.chore;

import org.apache.commons.lang3.StringUtils;
import org.openmrs.Concept;
import org.openmrs.ConceptDatatype;
import org.openmrs.ConceptNumeric;
import org.openmrs.api.ConceptService;
import org.openmrs.api.context.Context;
import org.openmrs.module.esaudemetadata.Dictionary;
import org.openmrs.util.OpenmrsClassLoader;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

@Component("esaudemetadata.chore.updateConceptsUnits")
public class UpdateConceptsUnits extends AbstractChore {

    /**
     * @see org.openmrs.module.esaudemetadata.chore.AbstractChore#perform(java.io.PrintWriter)
     */
    @Override
    public void perform(PrintWriter output) {
        setUpConceptsMinAndMaxValues();
    }

    void setUpConceptsMinAndMaxValues(){

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
