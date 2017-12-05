package org.openmrs.module.esaudemetadata.metadatadeploy;

import org.openmrs.module.esaudemetadata.MetadataBundleUtils;
import org.openmrs.module.metadatadeploy.bundle.AbstractMetadataBundle;
import org.springframework.stereotype.Component;

import static org.openmrs.module.metadatadeploy.bundle.CoreConstructors.*;

@Component
public class CommonMetadataBundle extends AbstractMetadataBundle {

    /**
     * @see org.openmrs.module.metadatadeploy.bundle.AbstractMetadataBundle#install()
     */
    @Override
    public void install() throws Exception {
        // install the patient identifier types
        // install person attribute types
        //Install Encounter Type
        install(encounterType("Test deploy encounter type", "This is a testing encounter type", MetadataBundleUtils._EncounterType.TEST_ENCOUNTER_TYPE));
        //Install forms
        install(form("Test deploy form", "The form to collect different data", MetadataBundleUtils._EncounterType.TEST_ENCOUNTER_TYPE, "1.0", MetadataBundleUtils._Form.TEST_FORM));
        //installing programs
        //install Locations
        // Install Encounter Role
        //global properties

        //installing privileges
        install(privilege(MetadataBundleUtils._Privilege.ADMIN_POC, "Poc administration to be done just test"));
        install(privilege(MetadataBundleUtils._Privilege.VIEW_POC, "Poc viewing to be done just test"));
        //Tying roles to privileges
        install(role(MetadataBundleUtils._Role.POC, "Looks up patients just test", idSet(), idSet(
                MetadataBundleUtils._Privilege.ADMIN_POC,
                MetadataBundleUtils._Privilege.VIEW_POC)));
    }
}
