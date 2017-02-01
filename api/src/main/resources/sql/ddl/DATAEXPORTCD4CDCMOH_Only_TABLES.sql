DROP TABLE IF EXISTS `activo`;
CREATE TABLE `activo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `patient_id` int(11) DEFAULT NULL,
  `location_id` int(11) DEFAULT NULL,
  `location_name` varchar(255) DEFAULT NULL,
  `reference_id` int(11) DEFAULT NULL,
  `reference_name` varchar(255) DEFAULT NULL,
  `art_start_date` datetime DEFAULT NULL,
  `last_art_pickup` datetime DEFAULT NULL,
  `scheduled_last_art` datetime DEFAULT NULL,
  `last_followup` datetime DEFAULT NULL,
  `scheduled_followup` datetime DEFAULT NULL,
  `last_episode` datetime DEFAULT NULL,
  `scheduled_episode` datetime DEFAULT NULL,
  `scheduled_episode_60day` datetime DEFAULT NULL,
  `activo` tinyint(4) DEFAULT NULL,
  `last_cd4` double DEFAULT NULL,
  `last_cd4_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `cd4_dados`;
CREATE TABLE `cd4_dados` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `location_id` int(11) DEFAULT NULL,
  `location_name` varchar(255) DEFAULT NULL,
  `INSC_PACIENTES` int(11) DEFAULT NULL,
  `media_dias_diagnostico_inscricao` decimal(10,2) DEFAULT NULL,
  `media_dias_inscricao_cd4` decimal(10,2) DEFAULT NULL,
  `pacientes_cd4` int(11) DEFAULT NULL,
  `media_cd4` decimal(10,2) DEFAULT NULL,
  `mediana_cd4` decimal(10,2) DEFAULT NULL,
  `maximo_cd4` decimal(10,2) DEFAULT NULL,
  `minimo_cd4` decimal(10,2) DEFAULT NULL,
  `n_inicio_tarv` int(11) DEFAULT NULL,
  `n_inicio_tarv_semprov` int(11) DEFAULT NULL,
  `n_iniciotarv_tb` int(11) DEFAULT NULL,
  `n_iniciotarv_smi` int(11) DEFAULT NULL,
  `n_iniciotarv_starv` int(11) DEFAULT NULL,
  `n_iniciotarv_tb_cd4` int(11) DEFAULT NULL,
  `n_iniciotarv_smi_cd4` int(11) DEFAULT NULL,
  `n_iniciotarv_starv_cd4` int(11) DEFAULT NULL,
  `n_iniciotarv_tb_cd4_mediana` double(9,2) DEFAULT NULL,
  `n_iniciotarv_smi_cd4_mediana` double(9,2) DEFAULT NULL,
  `n_iniciotarv_starv_cd4_mediana` double(9,2) DEFAULT NULL,
  `n_activo_tarv` int(11) DEFAULT NULL,
  `n_activo_tarv_semprov` int(11) DEFAULT NULL,
  `n_activotarv_tb` int(11) DEFAULT NULL,
  `n_activotarv_smi` int(11) DEFAULT NULL,
  `n_activotarv_starv` int(11) DEFAULT NULL,
  `n_activotarv_tb_cd4` int(11) DEFAULT NULL,
  `n_activotarv_smi_cd4` int(11) DEFAULT NULL,
  `n_activotarv_starv_cd4` int(11) DEFAULT NULL,
  `n_activotarv_tb_cd4_mediana` double(9,2) DEFAULT NULL,
  `n_activotarv_smi_cd4_mediana` double(9,2) DEFAULT NULL,
  `n_activotarv_starv_cd4_mediana` double(9,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `inicio_tarv`;
CREATE TABLE `inicio_tarv` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `patient_id` int(11) DEFAULT NULL,
  `location_id` int(11) DEFAULT NULL,
  `location_name` varchar(255) DEFAULT NULL,
  `art_start_date` datetime DEFAULT NULL,
  `reference_id` int(11) DEFAULT NULL,
  `reference_name` varchar(255) DEFAULT NULL,
  `cd4_art_initiation` double(255,0) DEFAULT NULL,
  `cd4_art_initiation_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `inscrito_cd4`;
CREATE TABLE `inscrito_cd4` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `patient_id` int(11) DEFAULT NULL,
  `enrollment_date` datetime DEFAULT NULL,
  `location_id` int(11) DEFAULT NULL,
  `location_name` varchar(255) DEFAULT NULL,
  `reference_id` int(11) DEFAULT NULL,
  `reference_name` varchar(255) DEFAULT NULL,
  `first_cd4` double DEFAULT NULL,
  `first_cd4_date` datetime DEFAULT NULL,
  `diagnose_date` datetime DEFAULT NULL,
  `dias_diagnostico_inscricao` int(11) DEFAULT NULL,
  `dias_inscricao_cd4` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;