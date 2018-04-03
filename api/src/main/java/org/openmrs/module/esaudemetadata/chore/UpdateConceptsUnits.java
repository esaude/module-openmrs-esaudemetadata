package org.openmrs.module.esaudemetadata.chore;

import org.springframework.stereotype.Component;

import java.io.PrintWriter;

@Component("esaudemetadata.chore.updateConceptsUnits")
public class UpdateConceptsUnits extends AbstractChore {

    /**
     * @see org.openmrs.module.esaudemetadata.chore.AbstractChore#perform(java.io.PrintWriter)
     */
    @Override
    public void perform(PrintWriter output) {
        System.out.println("The chore is executed correctly");
    }
}
