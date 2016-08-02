/*set the person relation*/
SELECT person_id INTO @person_id FROM person where uuid='aa81a280-57e9-11e6-bc5b-507b9dc4c741';

/*
Set the person names that are compulsory*/

INSERT INTO person_name(`preferred`, `person_id`, `prefix`, `given_name`, `middle_name`, `family_name_prefix`, `family_name`, `family_name2`, `family_name_suffix`, `degree`, `creator`, `date_created`, `voided`, `voided_by`, `date_voided`, `void_reason`, `changed_by`, `date_changed`, `uuid`)
VALUES(0, @person_id, NULL, 'Metadata', NULL, NULL, 'Esaude', NULL, NULL, NULL, 1, current_date, 0, NULL, NULL, NULL, NULL, NULL, '2a1b5f8a-57eb-11e6-9c63-507b9dc4c741' );

 /*set the user relation*/
 INSERT INTO users(username, password, salt, secret_question, secret_answer, creator, date_created, changed_by, date_changed, person_id, retired, retired_by, date_retired, retire_reason, uuid)
 VALUES('esaude.metadata', '027e2bab0bd326f22e2980c1e9d1b9f424d7ccab5f9120c65b42ce00db894f758b409603dc60d535a3e659d18aa2b824fabc4f3d8d523f3f7b953f467d6cd566', '111b720b201d6c53e77faa905c9babb654699643477f7422b35b6093100029cdafedaacb705f41179bd351d13b18c11c1998f982888e16bb70c2d466a84f3c08',
        NULL, NULL, 1, current_date, NULL, NULL, @person_id, 0, NULL, NULL, NULL, 'f9a19c60-57eb-11e6-a798-507b9dc4c741');