drop database freesbee_sla;
create database freesbee_sla;

CREATE TABLE icar_inf2_soggetto
(
    id INT8 NOT NULL,
    nome_soggetto VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE icar_inf2_service
(
    inf2_id_service VARCHAR(80) NOT NULL,
    inf2_id_initiator VARCHAR(80) NOT NULL,
    inf2_id_responder VARCHAR(80) NOT NULL,
    inf2_id_agreement VARCHAR(80) NOT NULL,
    inf2_id_state VARCHAR(30) NOT NULL,
    inf2_sla_object TEXT,
    inf2_count_pending_request INT8 default 0 NOT NULL,
    inf2_active INT8 default 1 NOT NULL,
    PRIMARY KEY (inf2_id_service,inf2_id_initiator,inf2_id_responder)
);

CREATE TABLE icar_inf2_sla_object_trace
(
    inf2_id_service VARCHAR(80) NOT NULL,
    inf2_id_initiator VARCHAR(80) NOT NULL,
    inf2_id_responder VARCHAR(80) NOT NULL,
    inf2_sla_basic_metric VARCHAR(80) NOT NULL,
    inf2_sla_basic_metric_data_insert TIMESTAMP NOT NULL,
    inf2_sla_basic_metric_value DOUBLE PRECISION NOT NULL,
    inf2_sla_basic_metric_milliseconds_insert DECIMAL(3) NOT NULL,
    PRIMARY KEY (inf2_id_service,inf2_id_initiator,inf2_id_responder,inf2_sla_basic_metric,inf2_sla_basic_metric_data_insert,inf2_sla_basic_metric_milliseconds_insert)
);

CREATE TABLE utente
(
    username VARCHAR(45) NOT NULL,
    password VARCHAR(100) NOT NULL,
    PRIMARY KEY (username),
    CONSTRAINT utente_U_1 UNIQUE (username)
);

CREATE TABLE configurazione
(
    id INT8 NOT NULL,
    indirizzo_registro_servizi VARCHAR(180) NOT NULL,
    nome_SLA VARCHAR(180) NOT NULL,
    tipo_SLA VARCHAR(180) NOT NULL,
    accordo_servizio_sla VARCHAR(180) NOT NULL,
    nome_servizio_monitoraggio_sla VARCHAR(180) NOT NULL,
    tipo_servizio_monitoraggio_sla VARCHAR(180) NOT NULL,
    pd_monitoraggio_sla VARCHAR(180) NOT NULL,
    accordo_servizio_stato VARCHAR(180) NOT NULL,
    nome_servizio_monitoraggio_stato VARCHAR(180) NOT NULL,
    tipo_servizio_monitoraggio_stato VARCHAR(180) NOT NULL,
    pd_monitoraggio_stato VARCHAR(180) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE configurazione_sp
(
    id INT8 NOT NULL,
    url_freesbee_sp VARCHAR(180) NOT NULL,
    risorsa VARCHAR(180) NOT NULL,
    autenticazione VARCHAR(6) NOT NULL,
    risorsa_pd_monitoraggio_sla VARCHAR(180) NOT NULL,
    risorsa_pd_monitoraggio_stato VARCHAR(180) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE icar_inf2_sla_object_trace
    ADD CONSTRAINT icar_inf2_sla_object_trace_FK_1 FOREIGN KEY (inf2_id_service, inf2_id_initiator, inf2_id_responder)
    REFERENCES icar_inf2_service (inf2_id_service, inf2_id_initiator, inf2_id_responder)
;