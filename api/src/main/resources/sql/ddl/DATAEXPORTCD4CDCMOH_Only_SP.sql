DROP PROCEDURE IF EXISTS `FillCD4CDCMISAU` //

CREATE PROCEDURE `FillCD4CDCMISAU`(startDate date,endDate date)
    READS SQL DATA
begin


DECLARE no_more_rows int;
DECLARE locationID int;
DECLARE referenceID int;
DECLARE referenceName varchar(300);
DECLARE pacientes int;
DECLARE colunas int;
Declare queryCreateField varchar(300);
Declare queryUpdateField varchar(300);


DECLARE cur_referencia CURSOR FOR
	Select 	location_id,
			reference_id,
			reference_name,
			count(*) pacientes
	from 	inscrito_cd4 				
	where 	enrollment_date is not null
	group by location_id,reference_name;
	

DECLARE cur_local_Inscricao CURSOR FOR
	Select distinct	location_id
	from 	inscrito_cd4;				
	

DECLARE CONTINUE HANDLER FOR NOT FOUND SET no_more_rows=1;

/*=================NOVOS INSCRITOS====================================*/
truncate activo;
truncate inicio_tarv;
truncate inscrito_cd4;
truncate cd4_dados;

insert into inscrito_cd4(patient_id,enrollment_date,location_id)
select patient_id,data_abertura,location_id
from 
(
	select 	p.patient_id,min(encounter_datetime) data_abertura,e.location_id 
	from 	 patient p 
			inner join  encounter e on e.patient_id=p.patient_id 
	where 	e.voided=0 and p.voided=0 and e.encounter_type in (5,7) and 
			e.encounter_datetime<=endDate  
	group 	by e.location_id,p.patient_id
)inscricao 
where data_abertura between startDate and endDate and location_id is not null;

/*Update location name*/
update inscrito_cd4, location
set inscrito_cd4.location_name=location.name
where inscrito_cd4.location_id=location.location_id;

/*Update proveniencia*/
update inscrito_cd4, obs
set inscrito_cd4.reference_id=obs.value_coded
where inscrito_cd4.patient_id=obs.person_id and obs.concept_id=1594 and obs.voided=0;

/*Update proveniencia nome*/
update inscrito_cd4, concept_name
set inscrito_cd4.reference_name=concept_name.name
where inscrito_cd4.reference_id=concept_name.concept_id and concept_name.locale='pt' and concept_name.concept_name_type='FULLY_SPECIFIED';

/*Update data diagnostico*/
update inscrito_cd4, obs
set inscrito_cd4.diagnose_date=obs.value_datetime
where inscrito_cd4.patient_id=obs.person_id and obs.concept_id=6123 and obs.voided=0;

update inscrito_cd4 set dias_diagnostico_inscricao=datediff(enrollment_date,diagnose_date) where diagnose_date is not null and enrollment_date is not null;

/*Update data primero CD4 - Lab*/
update inscrito_cd4,
(	select 	person_id,min(obs_datetime) data_CD4
	from 	 obs
	where 	concept_id=5497 and voided=0 and obs_datetime<=endDate
	group by person_id
)cd4
set inscrito_cd4.first_cd4_date=cd4.data_CD4
where inscrito_cd4.patient_id=cd4.person_id;

/*Update valor primero CD4 - Lab*/
update inscrito_cd4, obs
set inscrito_cd4.first_cd4=obs.value_numeric
where 	inscrito_cd4.patient_id=obs.person_id and inscrito_cd4.first_cd4_date=obs.obs_datetime and 
		obs.concept_id=5497;

update inscrito_cd4 set dias_inscricao_cd4=datediff(first_cd4_date,enrollment_date) where first_cd4_date is not null and enrollment_date is not null;
		
/*Update data primero CD4 - Seguimento*/
update inscrito_cd4,
(	select 	person_id,min(obs_datetime) data_CD4
	from 	 obs
	where 	concept_id=1695 and voided=0 and obs_datetime<=endDate
	group by person_id
)cd4
set inscrito_cd4.first_cd4_date=cd4.data_CD4
where inscrito_cd4.patient_id=cd4.person_id and inscrito_cd4.first_cd4_date is null;

/*Update valor primero CD4 - Seguimento*/
update inscrito_cd4, obs
set inscrito_cd4.first_cd4=obs.value_numeric
where 	inscrito_cd4.patient_id=obs.person_id and inscrito_cd4.first_cd4_date=obs.obs_datetime and 
		obs.concept_id=1695 and inscrito_cd4.first_cd4 is null;	

/*=================FIM NOVOS INSCRITOS====================================*/		

/*=================NOVOS INICIO TARV====================================*/
	insert into inicio_tarv(patient_id,art_start_date,location_id)
	select patient_id,data_inicio,location_id
	from
	(	Select patient_id,min(data_inicio) data_inicio,location_id
		from
				(	Select 	p.patient_id,min(e.encounter_datetime) data_inicio,e.location_id
					from 	 patient p 
							inner join  encounter e on p.patient_id=e.patient_id	
							inner join  obs o on o.encounter_id=e.encounter_id
					where 	e.voided=0 and o.voided=0 and p.voided=0 and 
							e.encounter_type in (18,6,9) and o.concept_id=1255 and o.value_coded=1256 and 
							e.encounter_datetime<=endDate
					group by p.patient_id
			
					union
			
					Select 	p.patient_id,min(value_datetime) data_inicio,e.location_id
					from 	 patient p
							inner join  encounter e on p.patient_id=e.patient_id
							inner join  obs o on e.encounter_id=o.encounter_id
					where 	p.voided=0 and e.voided=0 and o.voided=0 and e.encounter_type in (18,6,9) and 
							o.concept_id=1190 and o.value_datetime is not null and 
							o.value_datetime<=endDate 
					group by p.patient_id

					union

					select 	pg.patient_id,date_enrolled data_inicio,pg.location_id
					from 	 patient p inner join  patient_program pg on p.patient_id=pg.patient_id
					where 	pg.voided=0 and p.voided=0 and program_id=2 and date_enrolled<=endDate 
					
					
					union
					
					
				  SELECT 	e.patient_id, MIN(e.encounter_datetime) AS data_inicio,e.location_id
				  FROM 		 patient p
							inner join  encounter e on p.patient_id=e.patient_id
				  WHERE		p.voided=0 and e.encounter_type=18 AND e.voided=0 and e.encounter_datetime<=endDate
				  GROUP BY 	p.patient_id				
					
					
				) inicio
			group by location_id,patient_id	
	)inicio1
	where data_inicio between startDate and endDate and location_id is not null;

/*Update location name*/
update inicio_tarv, location
set inicio_tarv.location_name=location.name
where inicio_tarv.location_id=location.location_id;

/*Update proveniencia*/
update inicio_tarv, obs
set inicio_tarv.reference_id=obs.value_coded
where inicio_tarv.patient_id=obs.person_id and obs.concept_id=1594 and obs.voided=0;

/*Update proveniencia nome*/
update inicio_tarv, concept_name
set inicio_tarv.reference_name=concept_name.name
where inicio_tarv.reference_id=concept_name.concept_id and concept_name.locale='pt' and concept_name.concept_name_type='FULLY_SPECIFIED';


/*Update data CD4 Inicio - Lab*/
update inicio_tarv,
(	select 	person_id,min(obs_datetime) data_CD4
	from 	 obs inner join inicio_tarv on obs.person_id=inicio_tarv.patient_id
	where 	concept_id=5497 and voided=0 and obs_datetime between date_add(art_start_date, interval -3 month) and date_add(art_start_date, interval 3 month)
	group by person_id
)cd4
set inicio_tarv.cd4_art_initiation_date=cd4.data_CD4
where inicio_tarv.patient_id=cd4.person_id;

/*Update valor CD4 Inicio - Lab*/
update inicio_tarv, obs
set inicio_tarv.cd4_art_initiation=obs.value_numeric
where 	inicio_tarv.patient_id=obs.person_id and inicio_tarv.cd4_art_initiation_date=obs.obs_datetime and 
		obs.concept_id=5497;
		
		
/*Update data CD4 Inicio - Seguimento*/
update inicio_tarv,
(	select 	person_id,min(obs_datetime) data_CD4
	from 	 obs inner join inicio_tarv on obs.person_id=inicio_tarv.patient_id
	where 	concept_id=1695 and voided=0 and obs_datetime between date_add(art_start_date, interval -3 month) and date_add(art_start_date, interval 3 month)
	group by person_id
)cd4
set inicio_tarv.cd4_art_initiation_date=cd4.data_CD4
where inicio_tarv.patient_id=cd4.person_id and cd4_art_initiation_date is null;

/*Update valor CD4 Inicio - Seguimento*/
update inicio_tarv, obs
set 	inicio_tarv.cd4_art_initiation=obs.value_numeric
where 	inicio_tarv.patient_id=obs.person_id and inicio_tarv.cd4_art_initiation_date=obs.obs_datetime and 
		obs.concept_id=1695 and cd4_art_initiation is null;

/*=================FIM NOVOS INICIO TARV====================================*/

/*=================ACTIVO TARV====================================*/
insert into activo(patient_id,art_start_date,location_id)
	select patient_id,data_inicio,location_id
	from
	(	Select patient_id,min(data_inicio) data_inicio,location_id
		from
				(	Select 	p.patient_id,min(e.encounter_datetime) data_inicio,e.location_id
					from 	 patient p 
							inner join  encounter e on p.patient_id=e.patient_id	
							inner join  obs o on o.encounter_id=e.encounter_id
					where 	e.voided=0 and o.voided=0 and p.voided=0 and 
							e.encounter_type in (18,6,9) and o.concept_id=1255 and o.value_coded=1256 and 
							e.encounter_datetime<=endDate
					group by e.location_id,p.patient_id
			
					union
			
					Select 	p.patient_id,min(value_datetime) data_inicio,e.location_id
					from 	 patient p
							inner join  encounter e on p.patient_id=e.patient_id
							inner join  obs o on e.encounter_id=o.encounter_id
					where 	p.voided=0 and e.voided=0 and o.voided=0 and e.encounter_type in (18,6,9) and 
							o.concept_id=1190 and o.value_datetime is not null and 
							o.value_datetime<=endDate 
					group by e.location_id,p.patient_id

					union

					select 	pg.patient_id,min(date_enrolled) data_inicio,pg.location_id
					from 	 patient p inner join  patient_program pg on p.patient_id=pg.patient_id
					where 	pg.voided=0 and p.voided=0 and program_id=2 and date_enrolled<=endDate 
					group by pg.location_id,p.patient_id
					
					union
					
					
				  SELECT 	e.patient_id, MIN(e.encounter_datetime) AS data_inicio,e.location_id
				  FROM 		 patient p
							inner join  encounter e on p.patient_id=e.patient_id
				  WHERE		p.voided=0 and e.encounter_type=18 AND e.voided=0 and e.encounter_datetime<=endDate
				  GROUP BY 	e.location_id,p.patient_id				
					
					
				) inicio
			group by location_id,patient_id	
	)inicio1
	where location_id is not null;


/*Update location name*/
update activo, location
set activo.location_name=location.name
where activo.location_id=location.location_id;

/*Update proveniencia*/
update activo, obs
set activo.reference_id=obs.value_coded
where activo.patient_id=obs.person_id and obs.concept_id=1594 and obs.voided=0 and activo.location_id=obs.location_id;

/*Update proveniencia nome*/
update activo, concept_name
set activo.reference_name=concept_name.name
where activo.reference_id=concept_name.concept_id and concept_name.locale='pt' and concept_name.concept_name_type='FULLY_SPECIFIED';

/*Ultimo levantamento de ARV*/
update activo,
(	
	SELECT 	e.patient_id, max(e.encounter_datetime) AS data_lev,e.location_id
	FROM 	 patient p
			inner join  encounter e on p.patient_id=e.patient_id
	WHERE		p.voided=0 and e.encounter_type=18 AND e.voided=0 and e.encounter_datetime<=endDate
	GROUP BY 	e.location_id,p.patient_id
) ultimo_lev
set activo.last_art_pickup=ultimo_lev.data_lev
where activo.patient_id=ultimo_lev.patient_id and activo.location_id=ultimo_lev.location_id;

/*Update data proximo*/
update activo, obs
set activo.scheduled_last_art=obs.value_datetime
where 	activo.patient_id=obs.person_id and obs.concept_id=5096 and obs.voided=0 and 
		activo.location_id=obs.location_id and activo.last_art_pickup=obs.obs_datetime;

update activo
set scheduled_last_art=date_add(last_art_pickup, interval 30 day)
where last_art_pickup is not null and scheduled_last_art is null;


/*Ultima consulta de ARV*/
update activo,
(	
	SELECT 	e.patient_id, max(e.encounter_datetime) AS data_lev,e.location_id
	FROM 	 patient p
			inner join  encounter e on p.patient_id=e.patient_id
	WHERE		p.voided=0 and e.encounter_type in (6,9) AND e.voided=0 and e.encounter_datetime<=endDate
	GROUP BY 	e.location_id,p.patient_id
) ultimo_lev
set activo.last_followup=ultimo_lev.data_lev
where activo.patient_id=ultimo_lev.patient_id and activo.location_id=ultimo_lev.location_id;

/*Update data proximo seguimento*/
update activo, obs
set activo.scheduled_followup=obs.value_datetime
where 	activo.patient_id=obs.person_id and obs.concept_id=1410 and obs.voided=0 and 
		activo.location_id=obs.location_id and activo.last_followup=obs.obs_datetime;

update activo
set scheduled_followup=date_add(last_followup, interval 30 day)
where last_followup is not null and scheduled_followup is null;

update activo set last_episode=if(last_art_pickup is null and last_followup is not null,last_followup,null);

update activo set last_episode=if(last_art_pickup is not null and last_followup is null,last_art_pickup,null);

update activo set last_episode=if(last_art_pickup is not null and last_followup is not null and last_art_pickup>=last_followup,last_art_pickup,last_followup);


update activo set scheduled_episode=if(scheduled_last_art is null and scheduled_followup is not null,scheduled_followup,null);

update activo set scheduled_episode=if(scheduled_last_art is not null and scheduled_followup is null,scheduled_last_art,null);

update activo set scheduled_episode=if(scheduled_last_art is not null and scheduled_followup is not null and scheduled_last_art>=scheduled_followup,scheduled_last_art,scheduled_followup);

update activo set scheduled_episode_60day=date_add(scheduled_episode, interval 60 day) where scheduled_episode is not null;

update activo set activo=if(scheduled_episode_60day<endDate,0,1) where scheduled_episode_60day is not null;


/*Update data CD4 Inicio - Lab*/
update activo,
(	select 	person_id,max(obs_datetime) data_CD4,activo.location_id
	from 	 obs inner join activo on obs.person_id=activo.patient_id
	where 	concept_id=5497 and voided=0 and obs_datetime between date_add(endDate, interval -6 month) and endDate and 
			obs.location_id=activo.location_id
	group by activo.location_id,person_id
)cd4
set activo.last_cd4_date=cd4.data_CD4
where activo.patient_id=cd4.person_id and activo.location_id=cd4.location_id;

/*Update valor CD4 Inicio - Lab*/
update 	activo, obs
set 	activo.last_cd4=obs.value_numeric
where 	activo.patient_id=obs.person_id and activo.last_cd4_date=obs.obs_datetime and 
		obs.concept_id=5497 and obs.location_id=activo.location_id;
		
/*Update data CD4 Inicio - Seguimento*/
update activo,
(	select 	person_id,max(obs_datetime) data_CD4,activo.location_id
	from 	 obs inner join activo on obs.person_id=activo.patient_id
	where 	concept_id=1695 and voided=0 and obs_datetime between date_add(endDate, interval -6 month) and endDate and 
			obs.location_id=activo.location_id
	group by activo.location_id,person_id
)cd4
set activo.last_cd4_date=cd4.data_CD4
where activo.patient_id=cd4.person_id and activo.location_id=cd4.location_id and activo.last_cd4_date is null;

/*Update valor CD4 Inicio - Seguimento*/
update 	activo, obs
set 	activo.last_cd4=obs.value_numeric
where 	activo.patient_id=obs.person_id and activo.last_cd4_date=obs.obs_datetime and 
		obs.concept_id=1695 and obs.location_id=activo.location_id and activo.last_cd4 is null;
/*=================FIM TARV====================================*/


/*==========================Populando tabela de Dados=====================*/

insert into cd4_dados (location_name,location_id) 
select location_name,location_id from inscrito_cd4 group by location_id;

update cd4_dados,
(	select 	location_id,count(*) pacientes
	from 	inscrito_cd4
	where 	enrollment_date is not null
	group by location_id
) inscrito
set cd4_dados.INSC_PACIENTES=inscrito.pacientes
where cd4_dados.location_id=inscrito.location_id;

update cd4_dados,
(	select 	location_id,avg(dias_diagnostico_inscricao) media
	from 	inscrito_cd4
	where 	dias_diagnostico_inscricao is not null
	group by location_id
) media
set cd4_dados.media_dias_diagnostico_inscricao=media.media
where cd4_dados.location_id=media.location_id;

update cd4_dados,
(	select 	location_id,avg(dias_inscricao_cd4) media
	from 	inscrito_cd4
	where 	dias_inscricao_cd4 is not null
	group by location_id
) media
set cd4_dados.media_dias_inscricao_cd4=media.media
where cd4_dados.location_id=media.location_id;

update cd4_dados,
(	select 	location_id,
			avg(first_cd4) media_cd4,
			min(first_cd4) minimo_cd4,
			max(first_cd4) maximo_cd4,
			count(*) pacientes_cd4
	from 	inscrito_cd4
	where 	first_cd4 is not null
	group by location_id
) media
set 
cd4_dados.media_cd4=media.media_cd4,
cd4_dados.minimo_cd4=media.minimo_cd4,
cd4_dados.maximo_cd4=media.maximo_cd4,
cd4_dados.pacientes_cd4=media.pacientes_cd4
where cd4_dados.location_id=media.location_id;

/*Inicio de TARV*/
update cd4_dados,
(	select count(*) pacientes,location_id
	from inicio_tarv
	where location_id is not null and art_start_date is not null
	group by location_id
) inicio
set cd4_dados.n_inicio_tarv=inicio.pacientes
where cd4_dados.location_id=inicio.location_id;

/*Inicio de TARV - Sem proveniencia*/
update cd4_dados,
(	select count(*) pacientes,location_id
	from inicio_tarv
	where location_id is not null and art_start_date is not null and reference_id is null
	group by location_id
) inicio
set cd4_dados.n_inicio_tarv_semprov=inicio.pacientes
where cd4_dados.location_id=inicio.location_id;

/*Inicio de TARV_TB*/
update cd4_dados,
(	select count(*) pacientes,location_id
	from inicio_tarv
	where location_id is not null and art_start_date is not null and reference_id=1414
	group by location_id
) inicio
set cd4_dados.n_iniciotarv_tb=inicio.pacientes
where cd4_dados.location_id=inicio.location_id;

/*Inicio de TARV_SMI*/
update cd4_dados,
(	select count(*) pacientes,location_id
	from inicio_tarv
	where location_id is not null and art_start_date is not null and reference_id in (1598,6288)
	group by location_id
) inicio
set cd4_dados.n_iniciotarv_smi=inicio.pacientes
where cd4_dados.location_id=inicio.location_id;

/*Inicio de TARV_STARV*/
update cd4_dados,
(	select count(*) pacientes,location_id
	from inicio_tarv
	where location_id is not null and art_start_date is not null and reference_id not in (1598,6288,1414)
	group by location_id
) inicio
set cd4_dados.n_iniciotarv_starv=inicio.pacientes
where cd4_dados.location_id=inicio.location_id;

/*Inicio de TARV_TB*/
update cd4_dados,
(	select count(*) pacientes,location_id
	from inicio_tarv
	where location_id is not null and art_start_date is not null and reference_id=1414 and cd4_art_initiation is not null
	group by location_id
) inicio
set cd4_dados.n_iniciotarv_tb_cd4=inicio.pacientes
where cd4_dados.location_id=inicio.location_id;


/*Inicio de TARV_SMI*/
update cd4_dados,
(	select count(*) pacientes,location_id
	from inicio_tarv
	where location_id is not null and art_start_date is not null and reference_id in (1598,6288) and cd4_art_initiation is not null
	group by location_id
) inicio
set cd4_dados.n_iniciotarv_smi_cd4=inicio.pacientes
where cd4_dados.location_id=inicio.location_id;

/*Inicio de TARV_STARV*/
update cd4_dados,
(	select count(*) pacientes,location_id
	from inicio_tarv
	where location_id is not null and art_start_date is not null and reference_id not in (1598,6288,1414) and cd4_art_initiation is not null
	group by location_id
) inicio
set cd4_dados.n_iniciotarv_starv_cd4=inicio.pacientes
where cd4_dados.location_id=inicio.location_id;


/*Activo de TARV*/
update cd4_dados,
(	select count(*) pacientes,location_id
	from activo
	where location_id is not null and activo=1
	group by location_id
) activo
set cd4_dados.n_activo_tarv=activo.pacientes
where cd4_dados.location_id=activo.location_id;


/*Activo de TARV - sem proveniencia*/
update cd4_dados,
(	select count(*) pacientes,location_id
	from activo
	where location_id is not null and activo=1 and reference_id is null
	group by location_id
) activo
set cd4_dados.n_activo_tarv_semprov=activo.pacientes
where cd4_dados.location_id=activo.location_id;

/*Activo de TARV TB*/
update cd4_dados,
(	select count(*) pacientes,location_id
	from activo
	where location_id is not null and activo=1 and reference_id=1414
	group by location_id
) activo
set cd4_dados.n_activotarv_tb=activo.pacientes
where cd4_dados.location_id=activo.location_id;

/*Activo de TARV SMI*/
update cd4_dados,
(	select count(*) pacientes,location_id
	from activo
	where location_id is not null and activo=1 and reference_id in (1598,6288)
	group by location_id
) activo
set cd4_dados.n_activotarv_smi=activo.pacientes
where cd4_dados.location_id=activo.location_id;

/*Activo de TARV SMI*/
update cd4_dados,
(	select count(*) pacientes,location_id
	from activo
	where location_id is not null and activo=1 and reference_id not in (1598,6288,1414)
	group by location_id
) activo
set cd4_dados.n_activotarv_starv=activo.pacientes
where cd4_dados.location_id=activo.location_id;

/*Activo de TARV TB CD4*/
update cd4_dados,
(	select count(*) pacientes,location_id
	from activo
	where location_id is not null and activo=1 and reference_id=1414 and last_cd4 is not null
	group by location_id
) activo
set cd4_dados.n_activotarv_tb_cd4=activo.pacientes
where cd4_dados.location_id=activo.location_id;


/*Activo de TARV SMI CD4*/
update cd4_dados,
(	select count(*) pacientes,location_id
	from activo
	where location_id is not null and activo=1 and reference_id in (1598,6288) and last_cd4 is not null
	group by location_id
) activo
set cd4_dados.n_activotarv_smi_cd4=activo.pacientes
where cd4_dados.location_id=activo.location_id;

/*Activo de TARV STARV CD4*/
update cd4_dados,
(	select count(*) pacientes,location_id
	from activo
	where location_id is not null and activo=1 and reference_id not in (1598,6288,1414) and last_cd4 is not null
	group by location_id
) activo
set cd4_dados.n_activotarv_starv_cd4=activo.pacientes
where cd4_dados.location_id=activo.location_id;



SET no_more_rows=0;
OPEN cur_referencia;
cur_loop:WHILE(no_more_rows=0) DO
     FETCH cur_referencia INTO locationID,referenceID,referenceName,pacientes;
     IF no_more_rows=1 THEN
          LEAVE cur_loop;
     END IF;
	 
	 
	if referenceName is null then
		set referenceName='SEM_PROVENIENCIA';
	else
		set referenceName=replace(referenceName, ' ', '_');
		set referenceName=replace(referenceName, '/', '');
		set referenceName=replace(referenceName, ',', '');
		set referenceName=replace(referenceName, '.', '');
	end if;

	set referenceName=concat('INSC_',referenceName);
	
	if( not exists (SELECT * 
		FROM information_schema.COLUMNS 
		WHERE TABLE_SCHEMA = DATABASE() 
		AND TABLE_NAME = 'cd4_dados' 
		AND COLUMN_NAME = referenceName) )
	then
		SET @queryCreateField = CONCAT('ALTER TABLE cd4_dados ADD ', referenceName, ' int(10);');
		PREPARE stmt FROM @queryCreateField; 
		EXECUTE stmt; 
		DEALLOCATE PREPARE stmt;
	end if;	
	 
	SET @queryUpdateField = CONCAT('update cd4_dados set ', referenceName, ' = ',pacientes,' where location_id=',locationID,';');
	PREPARE stmt FROM @queryUpdateField; 
	EXECUTE stmt; 
	DEALLOCATE PREPARE stmt;	
	
END WHILE cur_loop;
CLOSE cur_referencia;

SET no_more_rows=0;

OPEN cur_local_Inscricao;
cur_loop:WHILE(no_more_rows=0) DO
     FETCH cur_local_Inscricao INTO locationID;
     IF no_more_rows=1 THEN
          LEAVE cur_loop;
     END IF;
	
	/*Mediana CD4 Inscrito*/
	update cd4_dados
	set mediana_cd4 = (
		SELECT AVG(t1.first_cd4) as Mediana 
		FROM 
			(
				SELECT @rownum:=@rownum+1 as row_number, a.first_cd4 
				FROM inscrito_cd4 a, (SELECT @rownum:=0) row
				WHERE a.location_id = locationID and first_cd4 is not null
				ORDER BY a.first_cd4
			) AS t1,

			(
				SELECT COUNT(*) AS total_rows 
				FROM inscrito_cd4 a
				WHERE a.location_id = locationID and first_cd4 is not null
			) AS t2

		WHERE t1.row_number in ( floor((total_rows+1)/2), floor((total_rows+2)/2))
	)
	where location_id=locationID;


/*Mediana CD4 Inicio TB*/
	update cd4_dados
	set n_iniciotarv_tb_cd4_mediana = (
		SELECT AVG(t1.cd4_art_initiation) as Mediana 
		FROM 
			(
				SELECT @rownum:=@rownum+1 as row_number, a.cd4_art_initiation 
				FROM inicio_tarv a, (SELECT @rownum:=0) row
				WHERE a.location_id = locationID and cd4_art_initiation is not null and reference_id=1414
				ORDER BY a.cd4_art_initiation
			) AS t1,

			(
				SELECT COUNT(*) AS total_rows 
				FROM inicio_tarv a
				WHERE a.location_id = locationID and cd4_art_initiation is not null and reference_id=1414
			) AS t2

		WHERE t1.row_number in ( floor((total_rows+1)/2), floor((total_rows+2)/2))
	)
	where location_id=locationID;

/*Mediana CD4 Inicio SMI*/
	update cd4_dados
	set n_iniciotarv_smi_cd4_mediana = (
		SELECT AVG(t1.cd4_art_initiation) as Mediana 
		FROM 
			(
				SELECT @rownum:=@rownum+1 as row_number, a.cd4_art_initiation 
				FROM inicio_tarv a, (SELECT @rownum:=0) row
				WHERE a.location_id = locationID and cd4_art_initiation is not null and reference_id in (1598,6288)
				ORDER BY a.cd4_art_initiation
			) AS t1,

			(
				SELECT COUNT(*) AS total_rows 
				FROM inicio_tarv a
				WHERE a.location_id = locationID and cd4_art_initiation is not null and reference_id in (1598,6288)
			) AS t2

		WHERE t1.row_number in ( floor((total_rows+1)/2), floor((total_rows+2)/2))
	)
	where location_id=locationID;


/*Mediana CD4 Inicio SMI*/
	update cd4_dados
	set n_iniciotarv_starv_cd4_mediana = (
		SELECT AVG(t1.cd4_art_initiation) as Mediana 
		FROM 
			(
				SELECT @rownum:=@rownum+1 as row_number, a.cd4_art_initiation 
				FROM inicio_tarv a, (SELECT @rownum:=0) row
				WHERE a.location_id = locationID and cd4_art_initiation is not null and reference_id not in (1598,6288,1414)
				ORDER BY a.cd4_art_initiation
			) AS t1,

			(
				SELECT COUNT(*) AS total_rows 
				FROM inicio_tarv a
				WHERE a.location_id = locationID and cd4_art_initiation is not null and reference_id not in (1598,6288,1414)
			) AS t2

		WHERE t1.row_number in ( floor((total_rows+1)/2), floor((total_rows+2)/2))
	)
	where location_id=locationID;



/*Mediana CD4 Inicio TB*/
	update cd4_dados
	set n_activotarv_tb_cd4_mediana = (
		SELECT AVG(t1.last_cd4) as Mediana 
		FROM 
			(
				SELECT @rownum:=@rownum+1 as row_number, a.last_cd4 
				FROM activo a, (SELECT @rownum:=0) row
				WHERE a.location_id = locationID and last_cd4 is not null and reference_id=1414
				ORDER BY a.last_cd4
			) AS t1,

			(
				SELECT COUNT(*) AS total_rows 
				FROM activo a
				WHERE a.location_id = locationID and last_cd4 is not null and reference_id=1414
			) AS t2

		WHERE t1.row_number in ( floor((total_rows+1)/2), floor((total_rows+2)/2))
	)
	where location_id=locationID;
	
	/*Mediana CD4 Inicio SMI*/
	update cd4_dados
	set n_activotarv_smi_cd4_mediana = (
		SELECT AVG(t1.last_cd4) as Mediana 
		FROM 
			(
				SELECT @rownum:=@rownum+1 as row_number, a.last_cd4 
				FROM activo a, (SELECT @rownum:=0) row
				WHERE a.location_id = locationID and last_cd4 is not null and reference_id in (1598,6288)
				ORDER BY a.last_cd4
			) AS t1,

			(
				SELECT COUNT(*) AS total_rows 
				FROM activo a
				WHERE a.location_id = locationID and last_cd4 is not null and reference_id in (1598,6288)
			) AS t2

		WHERE t1.row_number in ( floor((total_rows+1)/2), floor((total_rows+2)/2))
	)
	where location_id=locationID;
	
	/*Mediana CD4 Inicio SMI*/
	update cd4_dados
	set n_activotarv_starv_cd4_mediana = (
		SELECT AVG(t1.last_cd4) as Mediana 
		FROM 
			(
				SELECT @rownum:=@rownum+1 as row_number, a.last_cd4 
				FROM activo a, (SELECT @rownum:=0) row
				WHERE a.location_id = locationID and last_cd4 is not null and reference_id not in (1598,6288,1414)
				ORDER BY a.last_cd4
			) AS t1,

			(
				SELECT COUNT(*) AS total_rows 
				FROM activo a
				WHERE a.location_id = locationID and last_cd4 is not null and reference_id not in (1598,6288,1414)
			) AS t2

		WHERE t1.row_number in ( floor((total_rows+1)/2), floor((total_rows+2)/2))
	)
	where location_id=locationID;

	
	
END WHILE cur_loop;
CLOSE cur_local_Inscricao;
SET no_more_rows=0;

end //
