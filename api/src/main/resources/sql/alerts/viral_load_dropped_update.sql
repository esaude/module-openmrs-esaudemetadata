UPDATE patientflags_flag SET criteria = 'SELECT p.patient_id FROM patient p WHERE
    (SELECT ob.value_numeric FROM obs ob WHERE concept_id=730 AND ob.voided = 0 AND ob.obs_datetime=(
		SELECT MAX(ob1.obs_datetime)
			FROM obs ob1 WHERE ob1.voided = 0 AND ob1.obs_datetime < (
				SELECT MAX(ob2.obs_datetime) FROM obs ob2 WHERE ob2.voided = 0 and ob2.concept_id=730)
				) AND ob.value_numeric > 0.5 * (SELECT ob3.value_numeric FROM obs ob3 WHERE ob3.value_numeric IS NOT NULL AND ob3.voided = 0 AND ob3.concept_id=730 AND ob3.obs_datetime = (SELECT MAX(ob4.obs_datetime) FROM obs ob4 WHERE ob4.concept_id=730 AND ob4.voided = 0)));'
WHERE uuid='a1d03d6c-8972-11e6-848d-507b9dc4c741';