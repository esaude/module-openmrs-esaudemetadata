SET SQL_SAFE_UPDATES = 0;
UPDATE htmlformentry_html_form SET xml_data = REPLACE(xml_data, 'getValueAsString(pt)', 'valueCoded.name');
SET SQL_SAFE_UPDATES = 1;