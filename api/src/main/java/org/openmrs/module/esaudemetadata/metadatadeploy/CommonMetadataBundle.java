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
        //Install Encounter Type
        install(encounterType("CCU: RASTREIO", "Rastreio do cancro do colo uterino", MetadataBundleUtils._EncounterType.CCU_RASTREIO));
        install(encounterType("ADMISSION", "ADMISSION encounter", MetadataBundleUtils._EncounterType.ADMISSION));
        install(encounterType("APSS: PREVENÇÃO POSITIVA", "Avaliação Psico-Social e Prevenção Positiva", MetadataBundleUtils._EncounterType.APSS_PREVENCAO_POSITIVA));
        install(encounterType("APSS: PREVENÇÃO POSITIVA - SEGUIMENTO", "Ficha de apoio psicossocial e prevenção positiva - Seguimento", MetadataBundleUtils._EncounterType.APSS_PREVENCAO_POSITIVA_SEGUIMENTO));
        install(encounterType("CCR: PCR", "TESTE DE PCR", MetadataBundleUtils._EncounterType.CCR_PCR));
        install(encounterType("CCR: PROCESSO", "Primeira visita do Criança em risco", MetadataBundleUtils._EncounterType.CCR_PROCESSO));
        install(encounterType("CCR: SEGUIMENTO", "Seguimento visita do Criança Em Risco", MetadataBundleUtils._EncounterType.CCR_SEGUIMENTO));
        install(encounterType("Consultation", "Consultation encounter", MetadataBundleUtils._EncounterType.Consultation));
        install(encounterType("DISCHARGE", "DISCHARGE encounter", MetadataBundleUtils._EncounterType.DISCHARGE));
        install(encounterType("INVESTIGATION", "Investigation encounter", MetadataBundleUtils._EncounterType.INVESTIGATION));
        install(encounterType("S.TARV: ADULTO INICIAL A", "Primeira visita do paciente adulto", MetadataBundleUtils._EncounterType.S_TARV_ADULTO_INICIAL_A));

        //Install forms
        install(form("ADULTO: PROCESSO PARTE A - ANAMNESE", "Processo de Hospital de Dia 1/2", MetadataBundleUtils._EncounterType.S_TARV_ADULTO_INICIAL_A, "1.1", MetadataBundleUtils._Form.ADULTO_PROCESSO_PARTE_A_ANAMNESE));
        //install Locations
        // Install Encounter Role
        //global properties

        //installing privileges
        //install(privilege(MetadataBundleUtils._Privilege.ADMIN_POC, "Poc administration to be done just test"));
        //install(privilege(MetadataBundleUtils._Privilege.VIEW_POC, "Poc viewing to be done just test"));
        //Tying roles to privileges
        //install(role(MetadataBundleUtils._Role.POC, "Looks up patients just test", idSet(), idSet(
         //       MetadataBundleUtils._Privilege.ADMIN_POC,
           //     MetadataBundleUtils._Privilege.VIEW_POC)));
    }
}
