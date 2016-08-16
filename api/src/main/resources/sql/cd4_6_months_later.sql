/*select the right user to create metadata*/

SELECT user_id INTO @user_id FROM users where uuid='f9a19c60-57eb-11e6-a798-507b9dc4c741';


INSERT INTO patientflags_flag(`name`,`criteria`,`message`,`enabled`,`evaluator`,`description`,`creator`,`date_created`,`changed_by`,`date_changed`,`retired`,`retired_by`,`date_retired`,`retire_reason`,`uuid`,`priority_id`)
    VALUES('CD4 6 MONTHS LATER',
    'select en.patient_id
    from
    (select * from encounter e where e.encounter_type in (6, 9) and e.encounter_datetime =(select max(en.encounter_datetime) from encounter en where en.patient_id = e.patient_id and e.encounter_type in (6, 9))) as en
    where TIMESTAMPDIFF(MONTH, en.encounter_datetime, now()) >= 6 AND (select count(o.obs_id) from obs o where o.person_id = en.patient_id AND o.obs_datetime between (en.encounter_datetime and now()) AND o.concept_id IN (5497, 730, 1695)) = 0;',
	  'Teste de CD4 em falta(passam 6 meses desde a ultima consulta)',
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
	  '7aa53280-639b-11e6-95c2-507b9dc4c741',
	  NULL);
