package org.openmrs.module.esaudemetadata.metadatadeploy;

import org.openmrs.PatientIdentifierType;
import org.openmrs.customdatatype.datatype.RegexValidatedTextDatatype;
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

		// Install forms
		install(form("ADULTO: PROCESSO PARTE A - ANAMNESE", "Processo de Hospital de Dia 1/2",
				MetadataBundleUtils._EncounterType.S_TARV_ADULTO_INICIAL_A, "1.1",
				MetadataBundleUtils._Form.ADULTO_PROCESSO_PARTE_A_ANAMNESE));
		// install Location attribute

		// Install Encounter Role
		// global properties

		// installing privileges
		install(privilege(MetadataBundleUtils._Privilege.READ_TEST_ORDER, "View Test Orders"));
		install(privilege(MetadataBundleUtils._Privilege.WRITE_TEST_ORDER, "Create Test Orders"));
		install(privilege(MetadataBundleUtils._Privilege.UPDATE_TEST_ORDER, "Update Test Orders"));

		// Tying roles to privileges
		install(role(MetadataBundleUtils._Role.POC_CLINICIAN, "Creates test orders", idSet(),
				idSet(MetadataBundleUtils._Privilege.READ_TEST_ORDER, MetadataBundleUtils._Privilege.UPDATE_TEST_ORDER,
						MetadataBundleUtils._Privilege.WRITE_TEST_ORDER)));

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
		install(personAttributeType("Nickname", "Patient's nick name", String.class, null, true, 1.0,
				MetadataBundleUtils._PersonAttributeType.NICK_NAME));
	}
}
