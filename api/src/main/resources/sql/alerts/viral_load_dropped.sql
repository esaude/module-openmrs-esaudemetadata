         /*select the right user to create metadata*/

SELECT user_id INTO @user_id FROM users where uuid='f9a19c60-57eb-11e6-a798-507b9dc4c741';


INSERT INTO patientflags_flag(`name`,`criteria`,`message`,`enabled`,`evaluator`,`description`,`creator`,`date_created`,`changed_by`,`date_changed`,`retired`,`retired_by`,`date_retired`,`retire_reason`,`uuid`,`priority_id`)
    VALUES('VIRAL LOAD DROPPED',
    'SELECT p.patient_id FROM patient p WHERE
    (SELECT ob.value_numeric FROM obs ob WHERE concept_id=730 AND ob.obs_datetime=(SELECT MAX(ob1.obs_datetime) FROM obs ob1 WHERE ob1.obs_datetime < (SELECT MAX(ob2.obs_datetime) FROM obs ob2)) AND ob.value_numeric > 0.5 * (SELECT ob3.value_numeric FROM obs ob3 WHERE ob3.value_numeric IS NOT NULL AND ob3.obs_datetime = (SELECT MAX(ob4.obs_datetime) FROM obs ob4 WHERE ob4.concept_id=730)));',
    'Viral Load Dropped! the patient is doing the Prophylaxis',
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
	  'a1d03d6c-8972-11e6-848d-507b9dc4c741',
	  NULL);
