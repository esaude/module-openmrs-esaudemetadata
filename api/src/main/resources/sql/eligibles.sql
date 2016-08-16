/*select the right user to create metadata*/

SELECT user_id INTO @user_id FROM users where uuid='f9a19c60-57eb-11e6-a798-507b9dc4c741';


INSERT INTO patientflags_flag(`name`,`criteria`,`message`,`enabled`,`evaluator`,`description`,`creator`,`date_created`,`changed_by`,`date_changed`,`retired`,`retired_by`,`date_retired`,`retire_reason`,`uuid`,`priority_id`)
            VALUES('ELIGIBLES',
                    'SELECT o.person_id
                      FROM obs o,
                      (SELECT p.patient_id, max(e.encounter_datetime) AS encounter_datetime, o.concept_id
                      FROM patient p
                      INNER JOIN encounter e on p.patient_id=e.patient_id
                      INNER JOIN obs o on o.encounter_id=e.encounter_id
                      WHERE e.encounter_type=13
                      AND e.voided=0
                      AND p.voided=0
                      AND o.voided=0
                      AND o.concept_id = 5497
                      GROUP BY patient_id
                      ) last_encounter
                      WHERE o.person_id=last_encounter.patient_id
                      AND o.obs_datetime=last_encounter.encounter_datetime
                      AND o.voided=0
                      AND o.concept_id = 5497
                      AND o.value_numeric<=500

                      UNION

                    SELECT o.person_id
                     FROM obs o,
                     (SELECT p.patient_id,MAX(encounter_datetime) AS encounter_datetime
                         FROM patient p
                         INNER JOIN encounter e ON p.patient_id=e.patient_id
                         INNER JOIN obs o ON o.encounter_id=e.encounter_id
                          WHERE encounter_type IN (6,9)
                          AND e.voided=0
                          AND p.voided=0
                          AND o.voided=0
                          AND o.concept_id=5356
                            group by patient_id
                    ) last_encounter
                  WHERE o.person_id=last_encounter.patient_id
                    AND o.obs_datetime=last_encounter.encounter_datetime
                    AND o.voided=0
                    AND o.concept_id=5356
                    AND o.value_coded in (1206,1207)

                  UNION

                  SELECT p.patient_id FROM patient p
                  INNER JOIN person pe ON pe.person_id=p.patient_id,
                    (SELECT p.patient_id, MAX(e.encounter_datetime) last_enconter_date FROM patient p
                      INNER JOIN encounter e on e.patient_id = p.patient_id
                      INNER JOIN obs o on o.encounter_id = e.encounter_id
                      WHERE o.concept_id = 1030
                        AND o.value_coded = 703
                        AND o.voided = 0
                        AND e.voided = 0
                        and p.voided = 0) last_encounter
                  WHERE p.patient_id = last_encounter.patient_id
                  AND TIMESTAMPDIFF(YEAR, pe.birthdate, now()) < 5
                  AND p.voided = 0
                  AND pe.voided = 0

                  UNION

                 SELECT p.patient_id
                 FROM patient p
                  INNER JOIN encounter e ON p.patient_id=e.patient_id
                  INNER JOIN obs o ON o.encounter_id=e.encounter_id
                  WHERE e.voided=0
                  AND o.voided=0
                  AND o.concept_id=1113
                  AND e.encounter_type IN (6,9);', 'O paciente deve iniciar o TARV', 1, 'org.openmrs.module.patientflags.evaluator.SQLFlagEvaluator', NULL, @user_id, current_date, NULL, NULL, 0, NULL, NULL, NULL, '20c19bc0-62ef-11e6-8fbd-507b9dc4c741', NULL);