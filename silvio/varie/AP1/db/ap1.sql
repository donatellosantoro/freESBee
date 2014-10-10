CREATE TABLE datiammissione (
    id integer NOT NULL,
    numero_scheda character varying NOT NULL,
    regime_ricovero integer NOT NULL,
    data_ricovero character varying NOT NULL,
    provenienza_paziente integer NOT NULL,
    tipo_ricovero integer NOT NULL,
    motivo_ricovero integer NOT NULL,
    traumatismi_e_intossicazioni integer NOT NULL,
    paziente_cf character varying NOT NULL,
    struttura_ricovero integer
);

CREATE TABLE medico (
    id SERIAL NOT NULL,
    cognome character varying NOT NULL,
    nome character varying NOT NULL,
    comune_residenza character varying NOT NULL,
    codice_fiscale character(16) NOT NULL,
    codice_regionale character varying NOT NULL,
    telefono character varying,
    cellulare character varying,
	PRIMARY KEY (id),
	UNIQUE (codice_fiscale)
);

CREATE TABLE paziente (
    id SERIAL NOT NULL,
    cognome character varying NOT NULL,
    nome character varying NOT NULL,
    codice_fiscale character(16) NOT NULL,
    data_nascita character varying,
    sesso character(1) NOT NULL,
    comune_residenza character varying,
    asl_residenza character(3),
    medico integer,
    codice_ssr character varying,
	PRIMARY KEY (id),
	UNIQUE (codice_fiscale)
);

CREATE TABLE ricetta (
    numero integer NOT NULL,
    diagnosi character varying,
    data character varying NOT NULL,
    posizione_utente_ticket character varying NOT NULL,
    importo_ticket double precision NOT NULL,
    importo_totale double precision NOT NULL,
    codice_struttura_erogatrice integer NOT NULL,
    codice_prestazione integer NOT NULL,
    codifica_nomenclatore integer NOT NULL,
    quantita integer NOT NULL,
    importo double precision NOT NULL,
    paziente integer, 
	PRIMARY KEY (numero)
);

ALTER TABLE ricetta
    ADD CONSTRAINT paziente FOREIGN KEY (paziente) REFERENCES paziente(id);

ALTER TABLE ONLY paziente
    ADD CONSTRAINT paziente_medico_fkey FOREIGN KEY (medico) REFERENCES medico(id);