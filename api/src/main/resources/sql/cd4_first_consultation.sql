/*select the right user to create metadata*/

SELECT user_id INTO @user_id FROM users where uuid='f9a19c60-57eb-11e6-a798-507b9dc4c741';


INSERT INTO patientflags_flag(`name`,`criteria`,`message`,`enabled`,`evaluator`,`description`,`creator`,`date_created`,`changed_by`,`date_changed`,`retired`,`retired_by`,`date_retired`,`retire_reason`,`uuid`,`priority_id`)
    VALUES('CD4 FIRST CONSULTATION',
    'SELECT p.patient_id FROM patient p WHERE
    (SELECT count(en.encounter_id) FROM encounter en WHERE en.patient_id = p.patient_id AND encounter_type IN (6, 9)) > 0 and
    (SELECT count(ob.obs_id) FROM obs ob WHERE ob.person_id = p.patient_id AND ob.concept_id IN (5497, 730, 1695)  and ob.voided = 0) = 0;',
	  'Teste de CD4 em falta',
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
	  '6dc22a24-639a-11e6-8047-507b9dc4c741',
	  NULL);
