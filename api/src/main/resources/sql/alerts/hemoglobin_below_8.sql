/*select the right user to create metadata*/

SELECT user_id INTO @user_id FROM users where uuid='f9a19c60-57eb-11e6-a798-507b9dc4c741';


INSERT INTO patientflags_flag(`name`,`criteria`,`message`,`enabled`,`evaluator`,`description`,`creator`,`date_created`,`changed_by`,`date_changed`,`retired`,`retired_by`,`date_retired`,`retire_reason`,`uuid`,`priority_id`)
    VALUES('LOW HEMOGLOBIN LEVEL',
    'SELECT p.patient_id FROM patient p WHERE
    (SELECT ob.concept_id FROM obs ob WHERE ob.person_id = p.patient_id AND ob.concept_id=21 AND ob.voided = 0 AND ob.value_numeric < 8 AND ob.obs_datetime = (SELECT max(obs.obs_datetime) FROM obs obs WHERE obs.person_id=p.patient_id));',
    'Hemoglobin level is low',
	  1,
	  'org.openmrs.module.patientflags.evaluator.SQLFlagEvaluator',
	  NULL,
	  @user_id,
	  current_date,
	  NULL,
	  NULL,
	   0,
	  NULL,
	  NULL,
	  NULL,
	  '77b1d9f4-8095-11e6-b384-507b9dc4c741',
	  NULL);
