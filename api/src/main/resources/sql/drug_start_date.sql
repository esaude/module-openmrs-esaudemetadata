/*select the right user to create metadata*/

SELECT user_id INTO @user_id FROM users where uuid='f9a19c60-57eb-11e6-a798-507b9dc4c741';

INSERT INTO patientflags_flag(`name`,`criteria`,`message`,`enabled`,`evaluator`,`description`,`creator`,`date_created`,`changed_by`,`date_changed`,`retired`,`retired_by`,`date_retired`,`retire_reason`,`uuid`,`priority_id`)
    VALUES('DRUG START DATE', 'SELECT p.patient_id FROM patient p	INNER JOIN person pe on (p.patient_id = pe.person_id)	INNER JOIN obs o on (o.person_id = p.patient_id)	where TIMESTAMPDIFF(YEAR, pe.birthdate , now()) < 2
	and o.concept_id = 1190;', 'Deve solicitar o diagnostico definitivo', 1, 'org.openmrs.module.patientflags.evaluator.SQLFlagEvaluator', NULL, @user_id, current_date, NULL, NULL, 0, NULL, NULL, NULL, '86dc0c16-6393-11e6-b7e4-507b9dc4c741', NULL);
