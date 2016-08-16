/*select the right user to create metadata*/

SELECT user_id INTO @user_id FROM users where uuid='f9a19c60-57eb-11e6-a798-507b9dc4c741';

INSERT INTO patientflags_flag(`name`,`criteria`,`message`,`enabled`,`evaluator`,`description`,`creator`,`date_created`,`changed_by`,`date_changed`,`retired`,`retired_by`,`date_retired`,`retire_reason`,`uuid`,`priority_id`)
    VALUES('CTZ_PROPHILAXIS',
    'SELECT p.patient_id FROM patient p
	INNER JOIN
	(
	  SELECT p.patient_id, MAX(encounter_datetime) AS encounter_datetime
       FROM patient p
       INNER JOIN encounter e ON p.patient_id=e.patient_id
       INNER JOIN obs o ON o.encounter_id=e.encounter_id
       INNER JOIN person pe on pe.person_id = p.patient_id
    		WHERE e.encounter_type IN (6,9)
    		AND e.voided=0
    		AND p.voided=0
    		AND o.voided=0
    		AND o.concept_id=1695
    		AND o.value_numeric > 350
    		AND TIMESTAMPDIFF(YEAR, pe.birthdate, now()) >= 5
	)last_encounter on last_encounter.patient_id = p.patient_id
	INNER JOIN
	(
	  SELECT DISTINCT(p.patient_id)
       FROM patient p
       INNER JOIN encounter e ON p.patient_id=e.patient_id
       INNER JOIN obs o ON o.encounter_id=e.encounter_id
    		WHERE e.encounter_type IN (6,9)
    		AND e.voided=0
    		AND p.voided=0
    		AND o.voided=0
    		AND o.concept_id = 6121
		AND o.value_coded = 1065
	) ctz on ctz.patient_id = p.patient_id;',
	'O Paciente deve suspender a profilaxia com CTZ',
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
	'4adee25e-639a-11e6-987a-507b9dc4c741',
	NULL);
