package org.openmrs.module.esaudemetadata;

/**
 * Created by codehub on 3/18/16. This class contains all the utilities that are
 * required to run the esaude
 */
public class EsaudeMetadataUtils {

	public static final class _PackageNames {

		public static final String METADATA_CONSULTATION_COHORTS = "consultation_schedule_cohort-3.zip";
		public static final String METADATA_CONSULTATION_CURRENT_STORY = "consultation_current_story-1.zip";
		public static final String METADATA_DIAGNOSIS_CONCEPTS = "Diagnosis_Concepts-1.zip";
		public static final String METADATA_DIAGNOSIS_FORM_FIELDS_ADULT = "Diagnosis_Adult_Schema-1.zip";
		public static final String METADATA_PERSON_ATTRIBUTE_TYPE = "person_attributes-1.zip";
		public static final String METADATA_PATIENT_IDENTIFIER_TYPES = "patient_identifiers-1.zip";
		public static final String METADATA_FORM_FIELDS = "form_fields-1.zip";
		public static final String METADATA_DIAGNOSIS_FORM_FIELDS_CHILD = "Diagnosis_Child_Schema-1.zip";
		public static final String METADATA_WHO_FIELDS = "WHO_FIELDS-1.zip";
		public static final String METADATA_RELATIONSHIP_TYPE = "Relationship_type-1.zip";
		public static final String METADATA_POC_MAPPING_PRESCRIPTION = "poc_mapping_prescriptions-1.zip";
		public static final String METADATA_DIAGNOSIS_ICD10_FIELDS = "CriptoAndHistory_fields-1.zip";
		public static final String METADATA_CDC_RETENTION_REPORT = "CDC_Retention_Report_v2-8.zip";
		public static final String METADATA_REQUIRED_PATIENT_IDENTIFIER_TYPES = "required_patient_identifier_type-1.zip";
		public static final String METADATA_PRESCRIPTION_CONVSET_FROM_METADATA = "prescriotion_convset_from_metatata-1.zip";
		public static final String METADATA_CLCARVQ12017_1 = "CLCARVQ12017-1.zip";
		public static final String METADATA_ANAMNESE_MARKED_ON = "anamnese_marked_on-1.zip";
		public static final String METADATA_APSS = "APSS_Seguimento-1.zip";
		public static final String METADATA_TREATMENT_PRESCRIBED_UPDATED = "treatment_prescribed_updated-1.zip";
		public static final String METADATA_PHAMARCY = "PHARMACY_METADATA-1.zip";
		public static final String METADATA_POC_COHORT_QUERY = "POC_COHORT_QUERY-2.zip";
		public static final String METADATA_PRESCRIPTION_CONVSET_UPDATED = "prescription_convset_updated-1.zip";
		public static final String METADATA_IMPORT_CONCEPT_DICTIONARY_FOR_DRUGS = "Import_Concept_Dictionary_for_Drugs-1.zip";
		public static final String METADATA_FIX_CONCEPT_WITH_STOP_WORDS = "FIX_CONCEPT_WITH_STOP_WORDS-1.zip";
		public static final String METADATA_PREVIOUS_ANTIRETROVIRAL_CONCEPTS_FOR_DRUGS = "PREVIOUS_ANTIRETROVIRAL_CONCEPTS_FOR_DRUGS-1.zip";
		public static final String METADATA_TREATMENT_PRESCRIBED_CONCEPTS_FOR_DRUGS = "TREATMENT_PRESCRIBED_CONCEPTS_FOR_DRUGS-1.zip";
		public static final String METADATA_DRUGS_FROM_SIGLUS_FIRST = "DRUGS_FROM_SIGLUS_FIRST-1.zip";
		public static final String METADATA_DRUGS_FROM_SIGLUS_SECOND = "DRUGS_FROM_SIGLUS_SECOND-1.zip";
		public static final String METADATA_ARV_REGIMENS = "ARV_Regimens-1.zip";
		public static final String METADATA_DURATION_UNITS_UPDATED = "duration_units_updated-1.zip";
		public static final String METADATA_RETIRE_DRUGS = "RetiredDrugs-1.zip";
		public static final String METADATA_ESAUDE_ROLES = "eSaude_Roles-1.zip";
		public static final String METADATA_PREGNANCY = "pregnancy_fields-1.zip";
		public static final String METADATA_CONCEITO = "conceito_22771-1.zip";
		public static final String METADATA_HIV_TYPE_OF_TEST = "hiv_type_of_test-1.zip";
		public static final String METADATA_INE_CONCEPT_SOURCE = "INEConceptSource-1.zip";
		public static final String METADATA_OCCUPATIONS_AS_LIST_FORM = "POCOccupationField-1.zip";
		public static final String METADATA_SEXUAL_PARTNER_INFO_CONCEPT = "Sexual_Partner_Information-1";
	}

	public static final class _PackageUuids {
		public static final String METADATA_CONSULTATION_COHORTS_GROUP_UUID = "c8e7a6ab-c061-4afd-a5ee-79247ba1c9c3";
		public static final String METADATA_CONSULTATION_CURRENT_STORY_GROUP_UUID = "e9b78422-3092-4ecb-ab9b-2b9e60c767d7";
		public static final String METADATA_DIAGNOSIS_CONCEPTS_UUID = "75aaf4eb-8e4d-4e0a-a1a6-c2496eca2e58";
		public static final String METADATA_DIAGNOSIS_FORM_FIELDS_ADULT_GROUP_UUID = "fc94ac84-7ca2-4acd-8bfd-17f70b9d17aa";
		public static final String METADATA_PERSON_ATTRIBUTE_TYPE_GROUP_UUID = "a38b6cd7-2177-4549-8660-30def2bf4a56";
		public static final String METADATA_PATIENT_IDENTIFIER_TYPES_GROUP_UUID = "e2ad2438-6a7c-45ec-a13a-32550c8560e2";
		public static final String METADATA_FORM_FIELDS_GROUP_UUID = "b51c174d-6a78-4ab6-839d-44c75b0bfb30";
		public static final String METADATA_DIAGNOSIS_FORM_FIELDS_CHILD_GORUP_UUID = "a8641598-613d-4da0-a0f6-07866bc8c31d";
		public static final String METADATA_WHO_FIELDS_GROUP_UUID = "69071fe7-0425-4299-9f9c-addf07c2ba42";
		public static final String METADATA_RELATIONSHIP_TYPE_GROUP_UUID = "dabdade7-2fe4-49c1-a7a3-34104f7e2822";
		public static final String METADATA_POC_MAPPING_PRESCRIPTION_GROUP_UUID = "9e5453d6-4be0-4013-b3fb-d080fbadfa7b";
		public static final String METADATA_DIAGNOSIS_ICD10_GROUP_UUID = "61ba13e3-9c06-46e8-95a6-730e16871338";
		public static final String METADATA_CDC_RETENTION_REPORT_GROUP_UUID = "f8e06981-e13b-40ba-ab28-c17d96cff16e";
		public static final String METADATA_REQUIRED_PATIENT_IDENTIFIER_TYPES_GROUP_UUID = "f6b66133-ffe1-4350-986b-31b81b86b257";
		public static final String METADATA_PRESCRIPTION_CONVSET_FROM_METADATA_GROUP_UUID = "312d23c3-7f3f-494a-bc9c-d217f0a514ed";
		public static final String METADATA_CLCARVQ12017_1_GROUP_UUID = "daae9409-104a-4690-9a87-d5e800dc3cf8";
		public static final String METADATA_ANAMNESE_MARKED_ON_GROUP_UUID = "86a73c93-27db-467f-8d86-61101482031b";
		public static final String METADATA_APSS_GROUP_UUID = "51ea0c37-6f3e-4975-909c-4193c38e959d";
		public static final String METADATA_TREATMENT_PRESCRIBED_UPDATED_GROUP_UUID = "7b30660c-35c6-4c13-82b3-53401e6aa3d5";
		public static final String METADATA_PHAMARCY_GROUP_UUID = "f0dc5830-4c92-4266-91b2-3f4028c193d4";
		public static final String METADATA_POC_COHORT_QUERY_GROUP_UUID = "f0dc5830-4c92-4266-91b2-3f4028c193d4";

		public static final String METADATA_PRESCRIPTION_CONVSET_UPDATED_GROUP_UUID = "7864ba84-39a6-4edb-9152-4d3d3b4abb7b";
		public static final String METADATA_IMPORT_CONCEPT_DICTIONARY_FOR_DRUGS_GROUP_UUID = "c1ae523a-8114-49f8-ba5f-4ddba4b568d1";
		public static final String METADATA_FIX_CONCEPT_WITH_STOP_WORDS_GROUP_UUID = "19a09fdb-601e-48a3-bfea-3e5d327059bb";
		public static final String METADATA_PREVIOUS_ANTIRETROVIRAL_CONCEPTS_FOR_DRUGS_GROUP_UUID = "d427a99e-9757-488a-bb39-5347d2a25c29";
		public static final String METADATA_TREATMENT_PRESCRIBED_CONCEPTS_FOR_DRUGS_GROUP_UUID = "1c856b08-9455-4800-a0a9-745f3cbb16e4";
		public static final String METADATA_DRUGS_FROM_SIGLUS_FIRST_GROUP_UUID = "7a581c69-684d-40e2-8b57-8941d712d7cf";
		public static final String METADATA_DRUGS_FROM_SIGLUS_SECOND_GROUP_UUID = "3465be5c-2bfd-41c3-a863-461f2153725e";
		public static final String METADATA_ARV_REGIMENS_GROUP_UUID = "353d1931-2959-4cb0-bc8e-4025dcaf9d21";
		public static final String METADATA_DURATION_UNITS_UPDATED_GROUP_UUID = "a3330d2e-a05c-4241-8b5f-31697fe98b2f";
		public static final String METADATA_RETIRE_DRUGS_GROUP_UUID = "caa59c55-f358-4d3c-b75b-0e2d4bae568f";
		public static final String METADATA_ESAUDE_ROLES_GROUP_UUID = "2f1866ad-161b-4769-b148-8b417151c071";

		public static final String METADATA_PREGNANCY_GROUP_UUID = "dc51083d-1c96-481d-b8b7-755b65c76158";
		public static final String METADATA_CONCEITO_GROUP_UUID = "88bd4671-41dd-4780-8e5a-de0eccd84ef9";
		public static final String METADATA_HIV_TYPE_OF_TEST_GROUP_UUID = "398df755-01db-48d0-a68f-cdf2abfef623";

		public static final String METADATA_INE_CONCEPT_SOURCE_GROUP_UUID = "a460aa7b-a8cb-4386-91a3-3ea194251b65";
		public static final String METADATA_OCCUPATIONS_AS_LIST_FORM_GROUP_UUID = "92c856ab-4183-401f-a8a5-5e55c3243394";
		public static final String METADATA_SEXUAL_PARTNER_INFO_CONCEPT_GROUP_UUID = "ade412c9-8987-4df3-b6e4-f1caebc317f9";
    }

	public static final class _GlobalProperties {
		public static final String GP_DICTIONARY_VERSION = "esaude.conceptVersion";
	}

	public static final class _OtherMetadata {
		public static final String requiredConceptVersion = "20161001";
	}
}
