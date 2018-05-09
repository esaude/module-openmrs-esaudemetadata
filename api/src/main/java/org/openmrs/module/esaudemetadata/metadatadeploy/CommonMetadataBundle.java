package org.openmrs.module.esaudemetadata.metadatadeploy;

import static org.openmrs.module.metadatadeploy.bundle.CoreConstructors.encounterType;
import static org.openmrs.module.metadatadeploy.bundle.CoreConstructors.form;
import static org.openmrs.module.metadatadeploy.bundle.CoreConstructors.idSet;
import static org.openmrs.module.metadatadeploy.bundle.CoreConstructors.locationAttributeType;
import static org.openmrs.module.metadatadeploy.bundle.CoreConstructors.patientIdentifierType;
import static org.openmrs.module.metadatadeploy.bundle.CoreConstructors.personAttributeType;
import static org.openmrs.module.metadatadeploy.bundle.CoreConstructors.privilege;
import static org.openmrs.module.metadatadeploy.bundle.CoreConstructors.role;

import org.openmrs.Concept;
import org.openmrs.PatientIdentifierType;
import org.openmrs.customdatatype.datatype.RegexValidatedTextDatatype;
import org.openmrs.module.esaudemetadata.Dictionary;
import org.openmrs.module.esaudemetadata.MetadataBundleUtils;
import org.openmrs.module.metadatadeploy.bundle.AbstractMetadataBundle;
import org.openmrs.util.AttributableDate;
import org.springframework.stereotype.Component;

import static org.openmrs.module.metadatadeploy.bundle.CoreConstructors.encounterType;
import static org.openmrs.module.metadatadeploy.bundle.CoreConstructors.idSet;
import static org.openmrs.module.metadatadeploy.bundle.CoreConstructors.locationAttributeType;
import static org.openmrs.module.metadatadeploy.bundle.CoreConstructors.patientIdentifierType;
import static org.openmrs.module.metadatadeploy.bundle.CoreConstructors.personAttributeType;
import static org.openmrs.module.metadatadeploy.bundle.CoreConstructors.privilege;
import static org.openmrs.module.metadatadeploy.bundle.CoreConstructors.role;

@Component
public class CommonMetadataBundle extends AbstractMetadataBundle {

	/**
	 * @see org.openmrs.module.metadatadeploy.bundle.AbstractMetadataBundle#install()
	 */
	@Override
	public void install() throws Exception {
		// Install Encounter Type
		install(encounterType("CCU: RASTREIO", "Rastreio do cancro do colo uterino",
				MetadataBundleUtils._EncounterType.CCU_RASTREIO));
		install(encounterType("ADMISSION", "ADMISSION encounter", MetadataBundleUtils._EncounterType.ADMISSION));
		install(encounterType("APSS: PREVENÇÃO POSITIVA", "Avaliação Psico-Social e Prevenção Positiva",
				MetadataBundleUtils._EncounterType.APSS_PREVENCAO_POSITIVA));
		install(encounterType("APSS: PREVENÇÃO POSITIVA - SEGUIMENTO",
				"Ficha de apoio psicossocial e prevenção positiva - Seguimento",
				MetadataBundleUtils._EncounterType.APSS_PREVENCAO_POSITIVA_SEGUIMENTO));
		install(encounterType("CCR: PCR", "TESTE DE PCR", MetadataBundleUtils._EncounterType.CCR_PCR));
		install(encounterType("CCR: PROCESSO", "Primeira visita do Criança em risco",
				MetadataBundleUtils._EncounterType.CCR_PROCESSO));
		install(encounterType("CCR: SEGUIMENTO", "Seguimento visita do Criança Em Risco",
				MetadataBundleUtils._EncounterType.CCR_SEGUIMENTO));
		install(encounterType("Consultation", "Consultation encounter",
				MetadataBundleUtils._EncounterType.Consultation));
		install(encounterType("DISCHARGE", "DISCHARGE encounter", MetadataBundleUtils._EncounterType.DISCHARGE));
		install(encounterType("INVESTIGATION", "Investigation encounter",
				MetadataBundleUtils._EncounterType.INVESTIGATION));
		install(encounterType("S.TARV: ADULTO INICIAL A", "Primeira visita do paciente adulto",
				MetadataBundleUtils._EncounterType.S_TARV_ADULTO_INICIAL_A));
		install(encounterType("LAB RESULT", "Lab Result encounter", MetadataBundleUtils._EncounterType.LAB_RESULT));
		install(encounterType("MISAU: ATS", "Aconselhamento e Testagem de Saude",
				MetadataBundleUtils._EncounterType.MISAU_ATS));
		install(encounterType("MISAU: ITS", "REGISTO DIARIO DE CASOS DE DTS",
				MetadataBundleUtils._EncounterType.MISAU_ITS));
		install(encounterType("MISAU: LABORATORIO", "Laboratorio",
				MetadataBundleUtils._EncounterType.MISAU_LABORATORIO));
		install(encounterType("POC: CURRENT STORY",
				"A consultation where the clinician writes the notes about the current clinical observations of the patient",
				MetadataBundleUtils._EncounterType.POC_CURRENT_STORY));
		install(encounterType("POC:DISPENSATION", "For drugs dispensation on the pharmacy module - poc",
				MetadataBundleUtils._EncounterType.POC_DISPENSATION));
		install(encounterType("PTV: MATERNIDADE", "Prevencao de transmissao vertical na maternidade",
				MetadataBundleUtils._EncounterType.PTV_MATERNIDADE));
		install(encounterType("PTV: PRE-NATAL INICIAL", "Prevenção Transmisiveis Vertical",
				MetadataBundleUtils._EncounterType.PTV_PRE_NATAL_INICIAL));
		install(encounterType("PTV: PRE-NATAL SEGUIMENTO", "Prevenção Transmisiveis Vertical",
				MetadataBundleUtils._EncounterType.PTV_PRE_NATAL_SEGUIMENTO));
		install(encounterType("S.TARV: ACONSELHAMENTO", "MISAU/HDD TARV Aconselhamento",
				MetadataBundleUtils._EncounterType.S_TARV_ACONSELHAMENTO));
		install(encounterType("S.TARV: ACONSELHAMENTO SEGUIMENTO", "Seguimento Aconselhamento",
				MetadataBundleUtils._EncounterType.S_TARV_ACONSELHAMENTO_SEGUIMENTO));
		install(encounterType("S.TARV: ADULTO INICIAL B", "Outpatient Adult Initial Visit part 2",
				MetadataBundleUtils._EncounterType.S_TARV_ADULTO_INICIAL_B));
		install(encounterType("S.TARV: ADULTO SEGUIMENTO", "seguimento visita do paciente adulto",
				MetadataBundleUtils._EncounterType.S_TARV_ADULTO_SEGUIMENTO));
		install(encounterType("S.TARV: AVALIACAO DE ADESAO", "Avaliacao de adesao para pacientes em TARV",
				MetadataBundleUtils._EncounterType.S_TARV_AVALIACAO_DE_ADESAO));
		install(encounterType("S.TARV: AVALIACAO E PREPARACAO DO CANDIDATO TARV",
				"Avalicao e preparacao psicologica do candidato ao TARV",
				MetadataBundleUtils._EncounterType.S_TARV_AVALIACAO_E_PREPARACAO_DO_CANDIDATO_TARV));
		install(encounterType("S.TARV: BUSCA ACTIVA", "Busca Activa",
				MetadataBundleUtils._EncounterType.S_TARV_BUSCA_ACTIVA));
		install(encounterType("S.TARV: FARMACIA", "Farmacia", MetadataBundleUtils._EncounterType.S_TARV_FARMACIA));
		install(encounterType("S.TARV: LIVRO PRE-TARV", "Representa o livro de registo PRE-TARV",
				MetadataBundleUtils._EncounterType.S_TARV_LIVRO_PRE_TARV));
		install(encounterType("S.TARV: LIVRO TARV", "Representa o livro de registo TARV",
				MetadataBundleUtils._EncounterType.S_TARV_LIVRO_TARV));
		install(encounterType("S.TARV: PEDIATRIA INICIAL A", "Representa o livro de registo TARV",
				MetadataBundleUtils._EncounterType.S_TARV_PEDIATRIA_INICIAL_A));
		install(encounterType("S.TARV: PEDIATRIA INICIAL B",
				"Primeira visita de paciente pediatrico, parte B. As duas ultimas paginas",
				MetadataBundleUtils._EncounterType.S_TARV_PEDIATRIA_INICIAL_B));
		install(encounterType("S.TARV: PEDIATRIA SEGUIMENTO", "Seguimento visita do paciente pediatria",
				MetadataBundleUtils._EncounterType.S_TARV_PEDIATRIA_SEGUIMENTO));
		install(encounterType("S.TARV: SOLICITAÇÃO ARV", "Solicitação de Medicamentos Anti-Retrovirais",
				MetadataBundleUtils._EncounterType.S_TARV_SOLICITACAO_ARV));
		install(encounterType("S.TARV: TERMO DE CONSENTIMENTO DE VISITA",
				"Termo de consentimento de visita domiciliaria/busca activa",
				MetadataBundleUtils._EncounterType.S_TARV_TERMO_DE_CONSENTIMENTO_DE_VISITA));
		install(encounterType("TRANSFER", "TRANSFER encounter", MetadataBundleUtils._EncounterType.TRANSFER));
		install(encounterType("TUBERCULOSE: LIVRO", "Livro de Registo de Doentes com Tuberculose",
				MetadataBundleUtils._EncounterType.TUBERCULOSE_LIVRO));
		install(encounterType("TUBERCULOSE: PROCESSO", "TRATAMENTO DE TUBERCULOSE",
				MetadataBundleUtils._EncounterType.TUBERCULOSE_PROCESSO));
		install(encounterType("TUBERCULOSE: RASTREIO", "TUBURCULOSE: RASTREIO",
				MetadataBundleUtils._EncounterType.TUBERCULOSE_RASTREIO));
		install(encounterType("TUBERCULOSE: SEGUIMENTO", "Seguimento de doentes com tuberculose",
				MetadataBundleUtils._EncounterType.TUBERCULOSE_SEGUIMENTO));
        install(encounterType("Sexual Partners", "Sexual Partners encounter type",
                MetadataBundleUtils._EncounterType.SEXUAL_PARTNERS));

		// Install forms
		/*install(form("ADULTO: PROCESSO PARTE A - ANAMNESE", "Processo de Hospital de Dia 1/2",
				MetadataBundleUtils._EncounterType.S_TARV_ADULTO_INICIAL_A, "1.1",
				MetadataBundleUtils._Form.ADULTO_PROCESSO_PARTE_A_ANAMNESE));*/

		// installing privileges
		install(privilege(MetadataBundleUtils._Privilege.READ_TEST_ORDER,
				MetadataBundleUtils._Privilege.READ_TEST_ORDER));
		install(privilege(MetadataBundleUtils._Privilege.WRITE_TEST_ORDER,
				MetadataBundleUtils._Privilege.WRITE_TEST_ORDER));
		install(privilege(MetadataBundleUtils._Privilege.EDIT_TEST_ORDER,
				MetadataBundleUtils._Privilege.EDIT_TEST_ORDER));
		install(privilege(MetadataBundleUtils._Privilege.DELETE_VITALS, MetadataBundleUtils._Privilege.DELETE_VITALS));
		install(privilege(MetadataBundleUtils._Privilege.EDIT_VITALS, MetadataBundleUtils._Privilege.EDIT_VITALS));
		install(privilege(MetadataBundleUtils._Privilege.READ_ANAMNESIS,
				MetadataBundleUtils._Privilege.READ_ANAMNESIS));
		install(privilege(MetadataBundleUtils._Privilege.READ_DIAGNOSIS,
				MetadataBundleUtils._Privilege.READ_DIAGNOSIS));
		install(privilege(MetadataBundleUtils._Privilege.READ_OMS_STADIUM,
				MetadataBundleUtils._Privilege.READ_OMS_STADIUM));
		install(privilege(MetadataBundleUtils._Privilege.READ_PATIENT, MetadataBundleUtils._Privilege.READ_PATIENT));
		install(privilege(MetadataBundleUtils._Privilege.READ_DRUG_PRESCRIPTION,
				MetadataBundleUtils._Privilege.READ_DRUG_PRESCRIPTION));
		install(privilege(MetadataBundleUtils._Privilege.READ_RELEVANT_ASPECTS,
				MetadataBundleUtils._Privilege.READ_RELEVANT_ASPECTS));
		install(privilege(MetadataBundleUtils._Privilege.READ_SOCIAL, MetadataBundleUtils._Privilege.READ_SOCIAL));
		install(privilege(MetadataBundleUtils._Privilege.READ_VITALS, MetadataBundleUtils._Privilege.READ_VITALS));
		install(privilege(MetadataBundleUtils._Privilege.WRITE_DIAGNOSIS,
				MetadataBundleUtils._Privilege.WRITE_DIAGNOSIS));
		install(privilege(MetadataBundleUtils._Privilege.WRITE_DRUG_PRESCRIPTION,
				MetadataBundleUtils._Privilege.WRITE_DRUG_PRESCRIPTION));
		install(privilege(MetadataBundleUtils._Privilege.WRITE_SOCIAL, MetadataBundleUtils._Privilege.WRITE_SOCIAL));
		install(privilege(MetadataBundleUtils._Privilege.WRITE_VITALS, MetadataBundleUtils._Privilege.WRITE_VITALS));
        install(privilege(MetadataBundleUtils._Privilege.WRITE_VISIT, MetadataBundleUtils._Privilege.WRITE_VISIT));
        install(privilege(MetadataBundleUtils._Privilege.READ_PATIENT_PROGRAM, MetadataBundleUtils._Privilege.READ_PATIENT_PROGRAM));
		install(privilege(MetadataBundleUtils._Privilege.DELETE_VISIT, MetadataBundleUtils._Privilege.DELETE_VISIT));
		install(privilege(MetadataBundleUtils._Privilege.WRITE_OMS_STADIUM, MetadataBundleUtils._Privilege.WRITE_OMS_STADIUM));
		install(privilege(MetadataBundleUtils._Privilege.EDIT_OMS_STADIUM, MetadataBundleUtils._Privilege.EDIT_OMS_STADIUM));
		install(privilege(MetadataBundleUtils._Privilege.EDIT_DRUG_PRESCRIPTION, MetadataBundleUtils._Privilege.EDIT_DRUG_PRESCRIPTION));
		install(privilege(MetadataBundleUtils._Privilege.WRITE_RELEVANT_ASPECTS, MetadataBundleUtils._Privilege.WRITE_RELEVANT_ASPECTS));
		install(privilege(MetadataBundleUtils._Privilege.EDIT_RELEVANT_ASPECTS, MetadataBundleUtils._Privilege.EDIT_RELEVANT_ASPECTS));
		install(privilege(MetadataBundleUtils._Privilege.DELETE_DIAGNOSIS, MetadataBundleUtils._Privilege.DELETE_DIAGNOSIS));
		install(privilege(MetadataBundleUtils._Privilege.EDIT_DIAGNOSIS, MetadataBundleUtils._Privilege.EDIT_DIAGNOSIS));
		install(privilege(MetadataBundleUtils._Privilege.DELETE_DRUG_PRESCRIPTION, MetadataBundleUtils._Privilege.DELETE_DRUG_PRESCRIPTION));
		install(privilege(MetadataBundleUtils._Privilege.WRITE_ANAMNESIS, MetadataBundleUtils._Privilege.WRITE_ANAMNESIS));
		install(privilege(MetadataBundleUtils._Privilege.EDIT_ANAMNESIS, MetadataBundleUtils._Privilege.EDIT_ANAMNESIS));
		install(privilege(MetadataBundleUtils._Privilege.READ_DRUG_DISPENSATION, MetadataBundleUtils._Privilege.READ_DRUG_DISPENSATION));
		install(privilege(MetadataBundleUtils._Privilege.WRITE_DRUG_DISPENSATION, MetadataBundleUtils._Privilege.WRITE_DRUG_DISPENSATION));
		install(privilege(MetadataBundleUtils._Privilege.EDIT_DRUG_DISPENSATION, MetadataBundleUtils._Privilege.EDIT_DRUG_DISPENSATION));
		install(privilege(MetadataBundleUtils._Privilege.DELETE_DRUG_DISPENSATION, MetadataBundleUtils._Privilege.DELETE_DRUG_DISPENSATION));
		install(privilege(MetadataBundleUtils._Privilege.READ_TEST_RESULT, MetadataBundleUtils._Privilege.READ_TEST_RESULT));
		install(privilege(MetadataBundleUtils._Privilege.WRITE_TEST_RESULT, MetadataBundleUtils._Privilege.WRITE_TEST_RESULT));
		install(privilege(MetadataBundleUtils._Privilege.EDIT_TEST_RESULT, MetadataBundleUtils._Privilege.EDIT_TEST_RESULT));
		install(privilege(MetadataBundleUtils._Privilege.DELETE_RELEVANT_ASPECTS, MetadataBundleUtils._Privilege.DELETE_RELEVANT_ASPECTS));
		install(privilege(MetadataBundleUtils._Privilege.DELETE_OMS_STADIUM, MetadataBundleUtils._Privilege.DELETE_OMS_STADIUM));
		install(privilege(MetadataBundleUtils._Privilege.DELETE_TEST_ORDER, MetadataBundleUtils._Privilege.DELETE_TEST_ORDER));
		install(privilege(MetadataBundleUtils._Privilege.DELETE_TEST_RESULT, MetadataBundleUtils._Privilege.DELETE_TEST_RESULT));




		// Tying roles to privileges
        install(role(MetadataBundleUtils._Role.POC_USER,
                "Holds all core OpenMRS privileges required for a POC user",
                idSet(),
                idSet(MetadataBundleUtils._Privilege.GET_CONCEPTS,
                        // Patient search
                        MetadataBundleUtils._Privilege.GET_PATIENTS,
                        MetadataBundleUtils._Privilege.GET_OBSERVATIONS,
                        // --
                        // Programs
                        MetadataBundleUtils._Privilege.GET_PROGRAMS,
                        MetadataBundleUtils._Privilege.GET_PATIENT_PROGRAMS,
                        // --
                        // Visits / Check-in
                        MetadataBundleUtils._Privilege.GET_VISITS,
                        MetadataBundleUtils._Privilege.GET_ENCOUNTERS,
                        MetadataBundleUtils._Privilege.GET_VISIT_TYPES,
                        MetadataBundleUtils._Privilege.GET_VISIT_ATTRIBUTE_TYPES,
                        MetadataBundleUtils._Privilege.EDIT_VISITS,
                        MetadataBundleUtils._Privilege.ADD_VISITS,
                        // --
                        // Clinical Services
                        MetadataBundleUtils._Privilege.GET_PEOPLE,
                        MetadataBundleUtils._Privilege.GET_ENCOUNTER_ROLES,
                        MetadataBundleUtils._Privilege.EDIT_ENCOUNTERS,
                        MetadataBundleUtils._Privilege.ADD_ENCOUNTERS,
                        // --
                        // Test Order
                        MetadataBundleUtils._Privilege.GET_CARE_SETTINGS,
                        // --
						// Prescription
						MetadataBundleUtils._Privilege.GET_ORDER_FREQUENCIES
						// --
                )));

		install(role(MetadataBundleUtils._Role.POC_CLINICIAN,
				"Will have access to clinical app but he can not change or delete some records",
				idSet(MetadataBundleUtils._Role.POC_USER),
				idSet(MetadataBundleUtils._Privilege.READ_TEST_ORDER, MetadataBundleUtils._Privilege.EDIT_TEST_ORDER,
						MetadataBundleUtils._Privilege.WRITE_TEST_ORDER,
						MetadataBundleUtils._Privilege.EDIT_VITALS, MetadataBundleUtils._Privilege.READ_ANAMNESIS,
						MetadataBundleUtils._Privilege.READ_DIAGNOSIS, MetadataBundleUtils._Privilege.READ_OMS_STADIUM,
						MetadataBundleUtils._Privilege.READ_PATIENT, MetadataBundleUtils._Privilege.READ_DRUG_PRESCRIPTION,
						MetadataBundleUtils._Privilege.READ_RELEVANT_ASPECTS, MetadataBundleUtils._Privilege.READ_SOCIAL,
						MetadataBundleUtils._Privilege.READ_VITALS, MetadataBundleUtils._Privilege.WRITE_DIAGNOSIS,
						MetadataBundleUtils._Privilege.WRITE_DRUG_PRESCRIPTION,
						MetadataBundleUtils._Privilege.WRITE_VITALS,
						MetadataBundleUtils._Privilege.WRITE_ANAMNESIS,
						MetadataBundleUtils._Privilege.EDIT_ANAMNESIS,
						MetadataBundleUtils._Privilege.WRITE_OMS_STADIUM,
						MetadataBundleUtils._Privilege.EDIT_OMS_STADIUM)));

		install(role(MetadataBundleUtils._Role.POC_CLINICIAN_ADMIN,
				"Will have full access to clinical app ",
				idSet(MetadataBundleUtils._Role.POC_CLINICIAN),
				idSet(MetadataBundleUtils._Privilege.DELETE_DIAGNOSIS,
						MetadataBundleUtils._Privilege.DELETE_DRUG_PRESCRIPTION,
						MetadataBundleUtils._Privilege.DELETE_SOCIAL,
						MetadataBundleUtils._Privilege.EDIT_DIAGNOSIS,
						MetadataBundleUtils._Privilege.EDIT_DRUG_PRESCRIPTION,
						MetadataBundleUtils._Privilege.EDIT_RELEVANT_ASPECTS,
						MetadataBundleUtils._Privilege.EDIT_SOCIAL,
						MetadataBundleUtils._Privilege.WRITE_RELEVANT_ASPECTS,
						MetadataBundleUtils._Privilege.DELETE_VITALS,
						MetadataBundleUtils._Privilege.DELETE_RELEVANT_ASPECTS,
						MetadataBundleUtils._Privilege.DELETE_OMS_STADIUM,
						MetadataBundleUtils._Privilege.DELETE_TEST_ORDER)));

        install(role(MetadataBundleUtils._Role.POC_RECEPTIONIST,
                "Will have access to Registration, Vitals and Social app but he can not change or delete some records ",
                idSet(MetadataBundleUtils._Role.POC_USER),
				idSet(MetadataBundleUtils._Privilege.READ_PATIENT,
						MetadataBundleUtils._Privilege.READ_PATIENT_PROGRAM,
						MetadataBundleUtils._Privilege.READ_SOCIAL, MetadataBundleUtils._Privilege.READ_VITALS,
						MetadataBundleUtils._Privilege.WRITE_PATIENT, MetadataBundleUtils._Privilege.WRITE_PATIENT_PROGRAM,
						MetadataBundleUtils._Privilege.WRITE_SOCIAL, MetadataBundleUtils._Privilege.WRITE_VITALS,
						MetadataBundleUtils._Privilege.WRITE_VISIT, MetadataBundleUtils._Privilege.DELETE_VISIT,
						MetadataBundleUtils._Privilege.READ_TEST_RESULT,
						MetadataBundleUtils._Privilege.WRITE_TEST_RESULT,
						MetadataBundleUtils._Privilege.EDIT_TEST_RESULT,
						MetadataBundleUtils._Privilege.WRITE_SOCIAL,
						MetadataBundleUtils._Privilege.EDIT_SOCIAL)));

		install(role(MetadataBundleUtils._Role.POC_RECEPTIONIST_ADMIN,
                "Will have  full access to Registration, Vitals and Social app",
                idSet(MetadataBundleUtils._Role.POC_RECEPTIONIST),
                idSet(MetadataBundleUtils._Privilege.DELETE_PATIENT, MetadataBundleUtils._Privilege.DELETE_PATIENT_PROGRAM,
                        MetadataBundleUtils._Privilege.DELETE_SOCIAL, MetadataBundleUtils._Privilege.DELETE_VITALS,
                        MetadataBundleUtils._Privilege.EDIT_PATIENT, MetadataBundleUtils._Privilege.EDIT_PATIENT_PROGRAM,
                        MetadataBundleUtils._Privilege.EDIT_SOCIAL, MetadataBundleUtils._Privilege.EDIT_VITALS)));

		install(role(MetadataBundleUtils._Role.POC_NURSE,
				"Will have access to Clinical, Vitals and Social app but he can not change or delete some records",
				idSet(MetadataBundleUtils._Role.POC_USER),
				idSet(MetadataBundleUtils._Privilege.READ_VITALS,
						MetadataBundleUtils._Privilege.WRITE_VITALS)));

		install(role(MetadataBundleUtils._Role.POC_PHARMACIST,
				"Will have access to pharmacy app but can not change or delete some records",
				idSet(MetadataBundleUtils._Role.POC_USER),
				idSet(MetadataBundleUtils._Privilege.READ_DRUG_DISPENSATION,
						MetadataBundleUtils._Privilege.WRITE_DRUG_DISPENSATION)));

		install(role(MetadataBundleUtils._Role.POC_PHARMACIST_ADMIN,
				"Will have full access to pharmacy app",
				idSet(MetadataBundleUtils._Role.POC_PHARMACIST),
				idSet(MetadataBundleUtils._Privilege.DELETE_DRUG_DISPENSATION,
						MetadataBundleUtils._Privilege.EDIT_DRUG_DISPENSATION)));

		install(role(MetadataBundleUtils._Role.POC_PHARMACIST_INDEPENDENT,
				"Will have access to Registration and Pharmacy app  but he can not change or delete some records",
				idSet(MetadataBundleUtils._Role.POC_USER),
				idSet(MetadataBundleUtils._Privilege.READ_DRUG_DISPENSATION,
						MetadataBundleUtils._Privilege.WRITE_DRUG_DISPENSATION,
						MetadataBundleUtils._Privilege.READ_DRUG_PRESCRIPTION,
						MetadataBundleUtils._Privilege.WRITE_DRUG_PRESCRIPTION)));

		install(role(MetadataBundleUtils._Role.POC_PHARMACIST_INDEPENDENT_ADMIN,
				"Will have  full access to Registration and Pharmacy app",
				idSet(MetadataBundleUtils._Role.POC_PHARMACIST_INDEPENDENT),
				idSet(MetadataBundleUtils._Privilege.DELETE_DRUG_DISPENSATION,
						MetadataBundleUtils._Privilege.EDIT_DRUG_DISPENSATION,
						MetadataBundleUtils._Privilege.EDIT_DRUG_PRESCRIPTION,
						MetadataBundleUtils._Privilege.DELETE_DRUG_PRESCRIPTION)));

		install(role(MetadataBundleUtils._Role.POC_PSYCHOLOGIST,
				"Will have  full access to Social app",
				idSet(MetadataBundleUtils._Role.POC_USER),
				idSet(MetadataBundleUtils._Privilege.READ_PATIENT,
						MetadataBundleUtils._Privilege.READ_SOCIAL,
						MetadataBundleUtils._Privilege.WRITE_SOCIAL)));

		install(role(MetadataBundleUtils._Role.POC_PSYCHOLOGIST_ADMIN,
				"Will have  full access to Social app",
				idSet(MetadataBundleUtils._Role.POC_PSYCHOLOGIST),
				idSet(MetadataBundleUtils._Privilege.EDIT_SOCIAL,
						MetadataBundleUtils._Privilege.DELETE_SOCIAL)));

		install(role(MetadataBundleUtils._Role.POC_LAB_TECHNICIAN,
				"Will have full access to Laboratory app ",
				idSet(MetadataBundleUtils._Role.POC_USER),
				idSet(MetadataBundleUtils._Privilege.READ_TEST_ORDER,
						MetadataBundleUtils._Privilege.WRITE_TEST_ORDER,
						MetadataBundleUtils._Privilege.READ_TEST_RESULT,
						MetadataBundleUtils._Privilege.WRITE_TEST_RESULT,
						MetadataBundleUtils._Privilege.EDIT_TEST_RESULT)));

		install(role(MetadataBundleUtils._Role.POC_LAB_TECHNICIAN_ADMIN,
				"Will have full access to Laboratory app ",
				idSet(MetadataBundleUtils._Role.POC_LAB_TECHNICIAN),
				idSet(MetadataBundleUtils._Privilege.DELETE_TEST_ORDER,
						MetadataBundleUtils._Privilege.DELETE_TEST_RESULT)));



		// install the Health facility code here and make sure it exists all the
		// time
		install(locationAttributeType("Health Facility Code",
				"Unique facility code allocated by the Ministry of Health", RegexValidatedTextDatatype.class,
				"\\d{5,10}", 0, 1, MetadataBundleUtils._LocationAttributeType.MASTER_FACILITY_CODE));
		// install identifier types here
		install(patientIdentifierType("NID (SERVICO TARV)", "Numero de Identificaçao de Doente, serviço TARV",
				"[0-9]{8}/[0-9]{2}/[0-9]{5}", "PPDDUUSS/AA/NNNNN", null,
				PatientIdentifierType.LocationBehavior.NOT_USED, true,
				MetadataBundleUtils._PatientIdentifierType.NID_SERVICO_TARV));

		install(patientIdentifierType("BILHETE DE IDENTIDADE (BI)",
				"O Bilhete de Identidade referido no artigo anterior é válido em todo o território nacional e é documento bastante para prova de identidade do deu possuidor.",
				"[0-9]{9,12}[A-Z]{1}", "000000000X", null, PatientIdentifierType.LocationBehavior.NOT_USED, false,
				MetadataBundleUtils._PatientIdentifierType.BILHETE_DE_IDENTIDADE_BI));

		install(patientIdentifierType("NUMERO CANCRO CERVICAL",
				"Numero de registo de paciente para rastreio do cancro do colo uterino", "[0-9]/[0-9]{4}",
				"NNNNNN/20_ _", null, PatientIdentifierType.LocationBehavior.NOT_USED, false,
				MetadataBundleUtils._PatientIdentifierType.NUMERO_CANCRO_CERVICAL));

		// install the person attributes
		install(personAttributeType("Alcunha", "Patient's nick name", String.class, null, true, 1.0,
				MetadataBundleUtils._PersonAttributeType.NICK_NAME));
		install(personAttributeType("Proveniência", "", Concept.class,
				Dictionary.getConcept("e1dca2ea-1d5f-11e0-b929-000c29ad1d07").getConceptId(), false, 2.0,
				MetadataBundleUtils._PersonAttributeType.Proveniencia));
		install(personAttributeType("Ponto de Referência", "", String.class, null, false, 3.0,
				MetadataBundleUtils._PersonAttributeType.Ponto_de_Referência));
		install(personAttributeType("Numero de Telefone 1", "Telefone de contacto do paciente", String.class, null,
				false, 4.0, MetadataBundleUtils._PersonAttributeType.Numero_de_Telefone_1));
		install(personAttributeType("Numero de Telefone 2", "", String.class, null, false, 5.0,
				MetadataBundleUtils._PersonAttributeType.Numero_de_Telefone_2));
		install(personAttributeType("Data do teste HIV", "", AttributableDate.class, null, false, 6.0,
				MetadataBundleUtils._PersonAttributeType.Data_do_teste_HIV));

		install(personAttributeType("Tipo de teste HIV", "", Concept.class,
				Dictionary.getConcept("c7ac119c-d59d-474d-8334-0c5bdd2e9863").getConceptId(), false, 7.0,
				MetadataBundleUtils._PersonAttributeType.Tipo_de_teste_HIV));
		install(personAttributeType("Resultado do Teste HIV", "As respostas para o resultado do teste HIV", Concept.class,
				Dictionary.getConcept("e1d800dc-1d5f-11e0-b929-000c29ad1d07").getConceptId(), false, 8.0,
				MetadataBundleUtils._PersonAttributeType.Resultado_do_Teste_HIV));

		// install programs
		// need to include work flows before activating them
		/*
		 * install(program("SERVICO TARV - CUIDADO",
		 * "Programa de seguimento e cuidado aos pacientes HIV+",
		 * MetadataBundleUtils._Concepts.ARV_SERVICE_CARE,
		 * MetadataBundleUtils._Programs.SERVICO_TARV_CUIDADO));
		 * install(program("SERVICO TARV - TRATAMENTO",
		 * "Programa de seguimento e tratamento aos pacientes HIV+",
		 * MetadataBundleUtils._Concepts.ART_SERVICE_TREATMENT,
		 * MetadataBundleUtils._Programs.SERVICO_TARV_TRATAMENTO));
		 * install(program("TUBERCULOSE", "Programa de Combate a Tuberculose",
		 * MetadataBundleUtils._Concepts.
		 * NATIONAL_PROGRAM_AGAINST_TUBERCULOSIS_AND_LEPROSY,
		 * MetadataBundleUtils._Programs.TUBERCULOSE)); install(program("CCR",
		 * "Programa de consultas para criancas em risco (CCR)",
		 * MetadataBundleUtils._Concepts.CHILD_AT_RISK_CLINIC,
		 * MetadataBundleUtils._Programs.CCR)); install(program("CCU",
		 * "Programa de Rastreio de Cancro do Colo de Utero",
		 * MetadataBundleUtils._Concepts.CERVICAL_CANCER,
		 * MetadataBundleUtils._Programs.CCU)); install(program("PTV/ETV",
		 * "Programa de representa o estado de gravidez de uma mulher",
		 * MetadataBundleUtils._Concepts.PREVENTION_MOTHER_CHILD_TRANSMISSION,
		 * MetadataBundleUtils._Programs.PTV_ETV));
		 * install(program("CLINICA MOVEL",
		 * "Seguimento de pacientes atraves da Clinica Móvel",
		 * MetadataBundleUtils._Concepts.MOBILE_CLINIC,
		 * MetadataBundleUtils._Programs.CLINICA_MOVEL));
		 */

	}
}
