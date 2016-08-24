SET SQL_SAFE_UPDATES = 0;
SET FOREIGN_KEY_CHECKS=0;

#Use in HTML Form (1) the form ADULTO: PROCESSO PARTE A - ANAMNESE (67) that contains form fields instead
update openmrs.htmlformentry_html_form set form_id = 67 where id = 1;
#Change  existing encounters that reference to form (99), to reference ADULTO: PROCESSO PARTE A - ANAMNESE (67) that contains form fields instead
update openmrs.encounter set form_id = 67 where form_id = 99;
#Unpublish ADULTO: PROCESSO PARTE A - ANAMNESE (99) without fields
update openmrs.form set published = 0 where form_id = 99;
#Publish ADULTO: PROCESSO PARTE A - ANAMNESE (67) with fields
update openmrs.form set published = 1 where form_id = 67;

#Use in HTML Form (10) the form  PEDIATRIA: PROCESSO PARTE A - ANAMNESE (68) that contains form fields instead
update openmrs.htmlformentry_html_form set form_id = 68 where id = 10;
#Change  existing encounters hat reference to form (108), to reference PEDIATRIA: PROCESSO PARTE A - ANAMNESE (68) that contains form fields instead
update openmrs.encounter set form_id = 68 where form_id = 108;
#Unpublish PEDIATRIA: PROCESSO PARTE A - ANAMNESE (108) without fields
update openmrs.form set published = 0 where form_id = 108;
#Publish PEDIATRIA: PROCESSO PARTE A - ANAMNESE (68) with fields
update openmrs.form set published = 1 where form_id = 68;

#Use in HTML Form (28) the form ADULTO: SEGUIMENTO (37) that contains form fields instead
update openmrs.htmlformentry_html_form set form_id = 37 where id = 28;
#Change  existing encounters that reference to form (126), to reference ADULTO: SEGUIMENTOE (37) that contains form fields instead
update openmrs.encounter set form_id = 37 where form_id = 126;
#Unpublish ADULTO: SEGUIMENTO (126) without fields
update openmrs.form set published = 0 where form_id = 126;
#Publish ADULTO: SEGUIMENTO (37) with fields
update openmrs.form set published = 1 where form_id = 37;

#Use in HTML Form (29) the form PEDIATRIA: SEGUIMENTO (44) that contains form fields instead
update openmrs.htmlformentry_html_form set form_id = 44 where id = 29;
#Change  existing encounters that reference to form (126), to reference PEDIATRIA: SEGUIMENTOE (44) that contains form fields instead
update openmrs.encounter set form_id = 44 where form_id = 127;
#Unpublish PEDIATRIA: SEGUIMENTO (127) without fields
update openmrs.form set published = 0 where form_id = 127;
#Publish PEDIATRIA: SEGUIMENTO (44) with fields
update openmrs.form set published = 1 where form_id = 44;

SET SQL_SAFE_UPDATES = 1;
SET FOREIGN_KEY_CHECKS=1;