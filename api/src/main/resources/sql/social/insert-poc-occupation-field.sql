SET FOREIGN_KEY_CHECKS=0;
DELETE FROM form_field WHERE form_field.uuid = 'e2a030ca-1d5f-11e0-b929-000c29ad1d07';
INSERT INTO field (name,description,field_type,concept_id,table_name,attribute_name,default_value,select_multiple,creator,date_created,changed_by,date_changed,retired,retired_by,date_retired,retire_reason,uuid) VALUES ('Occupation POC','Occupation POC',1,23697,'','','',0,1,'2018-04-03 14:42:23',NULL,NULL,0,NULL,NULL,NULL,'f7fdb155-44fa-4add-9a98-d46ced953af0');
SELECT field_id INTO @field_id FROM field WHERE field.uuid = 'f7fdb155-44fa-4add-9a98-d46ced953af0';
INSERT INTO form_field (form_id,field_id,field_number,field_part,page_number,parent_form_field,min_occurs,max_occurs,required,changed_by,date_changed,creator,date_created,sort_weight,uuid) VALUES (67,@field_id,NULL,NULL,NULL,4539,NULL,NULL,0,NULL,NULL,1,'2018-04-03 14:42:23',275.00000,'d15c24c0-d5b8-45f9-a0b4-0f38c96ce09d');
SET FOREIGN_KEY_CHECKS=1;
