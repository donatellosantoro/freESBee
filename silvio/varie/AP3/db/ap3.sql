
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;


COMMENT ON SCHEMA public IS 'Standard public schema';




SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;


CREATE TABLE amministrazione (
    id integer NOT NULL,
    codice character varying,
    nome character varying,
    indirizzo integer,
    email character varying,
    dominio_pec character varying,
    sito_istituzionale character varying,
    responsabile integer
);


ALTER TABLE public.amministrazione OWNER TO silvio;


CREATE SEQUENCE amministrazione_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.amministrazione_id_seq OWNER TO silvio;


ALTER SEQUENCE amministrazione_id_seq OWNED BY amministrazione.id;



CREATE TABLE areaorganizzativaomogenea (
    id integer NOT NULL,
    codice_aoo character varying,
    nome character varying,
    responsabile integer,
    data_istituzione date,
    data_soppressione date,
    certification_autority character varying,
    email character varying,
    telefono character varying,
    fax character varying,
    indirizzo integer,
    id_amministrazione integer
);


ALTER TABLE public.areaorganizzativaomogenea OWNER TO silvio;


CREATE SEQUENCE area_organizzativa_omogenea_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.area_organizzativa_omogenea_id_seq OWNER TO silvio;


ALTER SEQUENCE area_organizzativa_omogenea_id_seq OWNED BY areaorganizzativaomogenea.id;



CREATE TABLE classifica (
    id integer NOT NULL,
    livello_classifica character varying,
    codice_classifica character varying,
    descrizione_classifica character varying
);


ALTER TABLE public.classifica OWNER TO silvio;


CREATE SEQUENCE classifica_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.classifica_id_seq OWNER TO silvio;


ALTER SEQUENCE classifica_id_seq OWNED BY classifica.id;



CREATE TABLE documento (
    id integer NOT NULL,
    codice_documento character varying,
    documento character varying,
    nome_documento character varying,
    id_documentofascicolo integer
);


ALTER TABLE public.documento OWNER TO silvio;


CREATE TABLE documentofascicolo (
    id integer NOT NULL,
    descrizione_corrispondente character varying,
    data_registrazione date,
    estremiprotocollo_anno character(4),
    estremiprotocollo_numero character varying,
    arrivopartenza character varying,
    codice_registro character varying,
    descrizione_registro character varying,
    id_amministrazione integer,
    id_areaorganizzativaomogenea integer,
    id_fascicolo integer
);


ALTER TABLE public.documentofascicolo OWNER TO silvio;


CREATE SEQUENCE "documentoFascicolo_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public."documentoFascicolo_id_seq" OWNER TO silvio;


ALTER SEQUENCE "documentoFascicolo_id_seq" OWNED BY documentofascicolo.id;



CREATE SEQUENCE documento_id_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.documento_id_seq OWNER TO silvio;


ALTER SEQUENCE documento_id_seq OWNED BY documento.id;



CREATE TABLE fascicolo (
    id integer NOT NULL,
    codice_ufficio character varying,
    descrizione_ufficio character varying,
    anno character(4),
    numero character varying,
    subnumero character varying,
    id_classifica integer
);


ALTER TABLE public.fascicolo OWNER TO silvio;


CREATE SEQUENCE fascicolo_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.fascicolo_id_seq OWNER TO silvio;


ALTER SEQUENCE fascicolo_id_seq OWNED BY fascicolo.id;



CREATE TABLE localita (
    id integer NOT NULL,
    via character varying,
    numero_civico character varying,
    cap character varying,
    citta character varying,
    sigla_provincia character varying,
    regione character varying
);


ALTER TABLE public.localita OWNER TO silvio;


CREATE SEQUENCE localita_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.localita_id_seq OWNER TO silvio;


ALTER SEQUENCE localita_id_seq OWNED BY localita.id;



CREATE TABLE persona (
    nome character varying,
    cognome character varying,
    titolo character varying,
    email character varying,
    telefono character varying,
    id integer NOT NULL
);


ALTER TABLE public.persona OWNER TO silvio;


CREATE SEQUENCE persona_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.persona_id_seq OWNER TO silvio;


ALTER SEQUENCE persona_id_seq OWNED BY persona.id;



CREATE TABLE responsabiletrattamento (
    id integer NOT NULL,
    codice_responsabiletrattamento character varying,
    descrizioneresponsabiletrattamento character varying,
    id_documentofascicolo integer
);


ALTER TABLE public.responsabiletrattamento OWNER TO silvio;


CREATE SEQUENCE responsabiletrattamento_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.responsabiletrattamento_id_seq OWNER TO silvio;


ALTER SEQUENCE responsabiletrattamento_id_seq OWNED BY responsabiletrattamento.id;



CREATE TABLE serviziotelematico_amministrazione (
    id integer NOT NULL,
    nome character varying,
    descrizione character varying,
    telefono character varying,
    url character varying,
    email character varying,
    codice_ufficio character varying,
    id_amministrazione integer
);


ALTER TABLE public.serviziotelematico_amministrazione OWNER TO postgres;


CREATE TABLE serviziotelematico_areaorganizzativaomogenea (
    id integer NOT NULL,
    nome character varying,
    descrizione character varying,
    telefono character varying,
    url character varying,
    email character varying,
    codice_ufficio character varying,
    id_areaorganizzativaomogenea integer
);


ALTER TABLE public.serviziotelematico_areaorganizzativaomogenea OWNER TO postgres;


CREATE SEQUENCE serviziotelematico_id_seq_amministrazione
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.serviziotelematico_id_seq_amministrazione OWNER TO postgres;


ALTER SEQUENCE serviziotelematico_id_seq_amministrazione OWNED BY serviziotelematico_amministrazione.id;



CREATE SEQUENCE serviziotelematico_id_seq_areaorganizzativaomogenea
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.serviziotelematico_id_seq_areaorganizzativaomogenea OWNER TO postgres;


ALTER SEQUENCE serviziotelematico_id_seq_areaorganizzativaomogenea OWNED BY serviziotelematico_areaorganizzativaomogenea.id;



CREATE TABLE serviziotelematico_unitaorganizzativa (
    id integer NOT NULL,
    nome character varying,
    descrizione character varying,
    telefono character varying,
    url character varying,
    email character varying,
    codice_ufficio character varying,
    id_unitaorganizzativa integer
);


ALTER TABLE public.serviziotelematico_unitaorganizzativa OWNER TO postgres;


CREATE SEQUENCE serviziotelematico_id_seq_unitaorganizzativa
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.serviziotelematico_id_seq_unitaorganizzativa OWNER TO postgres;


ALTER SEQUENCE serviziotelematico_id_seq_unitaorganizzativa OWNED BY serviziotelematico_unitaorganizzativa.id;



CREATE TABLE unitaorganizzativa (
    id integer NOT NULL,
    codice_uo character varying,
    nome_ufficio character varying,
    codice_uo_livello_superiore character varying,
    codice_aoo character varying,
    indirizzo integer,
    responsabile integer,
    email character varying,
    email_pec character varying,
    telefono character varying,
    fax character varying,
    url_ca character varying,
    id_amministrazione integer
);


ALTER TABLE public.unitaorganizzativa OWNER TO silvio;


CREATE SEQUENCE unitaorganizzativa_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.unitaorganizzativa_id_seq OWNER TO silvio;


ALTER SEQUENCE unitaorganizzativa_id_seq OWNED BY unitaorganizzativa.id;



ALTER TABLE amministrazione ALTER COLUMN id SET DEFAULT nextval('amministrazione_id_seq'::regclass);



ALTER TABLE areaorganizzativaomogenea ALTER COLUMN id SET DEFAULT nextval('area_organizzativa_omogenea_id_seq'::regclass);



ALTER TABLE classifica ALTER COLUMN id SET DEFAULT nextval('classifica_id_seq'::regclass);



ALTER TABLE documento ALTER COLUMN id SET DEFAULT nextval('documento_id_seq'::regclass);



ALTER TABLE documentofascicolo ALTER COLUMN id SET DEFAULT nextval('"documentoFascicolo_id_seq"'::regclass);



ALTER TABLE fascicolo ALTER COLUMN id SET DEFAULT nextval('fascicolo_id_seq'::regclass);



ALTER TABLE localita ALTER COLUMN id SET DEFAULT nextval('localita_id_seq'::regclass);



ALTER TABLE persona ALTER COLUMN id SET DEFAULT nextval('persona_id_seq'::regclass);



ALTER TABLE responsabiletrattamento ALTER COLUMN id SET DEFAULT nextval('responsabiletrattamento_id_seq'::regclass);



ALTER TABLE serviziotelematico_amministrazione ALTER COLUMN id SET DEFAULT nextval('serviziotelematico_id_seq_amministrazione'::regclass);



ALTER TABLE serviziotelematico_areaorganizzativaomogenea ALTER COLUMN id SET DEFAULT nextval('serviziotelematico_id_seq_areaorganizzativaomogenea'::regclass);



ALTER TABLE serviziotelematico_unitaorganizzativa ALTER COLUMN id SET DEFAULT nextval('serviziotelematico_id_seq_unitaorganizzativa'::regclass);



ALTER TABLE unitaorganizzativa ALTER COLUMN id SET DEFAULT nextval('unitaorganizzativa_id_seq'::regclass);



ALTER TABLE ONLY amministrazione
    ADD CONSTRAINT amministrazione_pkey PRIMARY KEY (id);



ALTER TABLE ONLY areaorganizzativaomogenea
    ADD CONSTRAINT area_organizzativa_omogenea_pkey PRIMARY KEY (id);



ALTER TABLE ONLY classifica
    ADD CONSTRAINT classifica_pkey PRIMARY KEY (id);



ALTER TABLE ONLY documentofascicolo
    ADD CONSTRAINT "documentoFascicolo_pkey" PRIMARY KEY (id);



ALTER TABLE ONLY documento
    ADD CONSTRAINT documento_pkey PRIMARY KEY (id);



ALTER TABLE ONLY fascicolo
    ADD CONSTRAINT fascicolo_pkey PRIMARY KEY (id);



ALTER TABLE ONLY localita
    ADD CONSTRAINT localita_pkey PRIMARY KEY (id);



ALTER TABLE ONLY persona
    ADD CONSTRAINT persona_pkey PRIMARY KEY (id);



ALTER TABLE ONLY responsabiletrattamento
    ADD CONSTRAINT responsabiletrattamento_pkey PRIMARY KEY (id);



ALTER TABLE ONLY serviziotelematico_amministrazione
    ADD CONSTRAINT serviziotelematico_amministrazione_pkey PRIMARY KEY (id);



ALTER TABLE ONLY serviziotelematico_areaorganizzativaomogenea
    ADD CONSTRAINT serviziotelematico_areaorganizzativaomogenea_pkey PRIMARY KEY (id);



ALTER TABLE ONLY serviziotelematico_unitaorganizzativa
    ADD CONSTRAINT serviziotelematico_unitaorganizzativa_pkey PRIMARY KEY (id);



ALTER TABLE ONLY unitaorganizzativa
    ADD CONSTRAINT unitaorganizzativa_pkey PRIMARY KEY (id);



ALTER TABLE ONLY amministrazione
    ADD CONSTRAINT amministrazione_indirizzo_fkey FOREIGN KEY (indirizzo) REFERENCES localita(id);



ALTER TABLE ONLY amministrazione
    ADD CONSTRAINT amministrazione_responsabile_fkey FOREIGN KEY (responsabile) REFERENCES persona(id);



ALTER TABLE ONLY areaorganizzativaomogenea
    ADD CONSTRAINT area_organizzativa_omogenea_indirizzo_fkey FOREIGN KEY (indirizzo) REFERENCES localita(id);



ALTER TABLE ONLY areaorganizzativaomogenea
    ADD CONSTRAINT area_organizzativa_omogenea_responsabile_fkey FOREIGN KEY (responsabile) REFERENCES persona(id);



ALTER TABLE ONLY areaorganizzativaomogenea
    ADD CONSTRAINT areaorganizzativaomogenea_id_amministrazione_fkey FOREIGN KEY (id_amministrazione) REFERENCES amministrazione(id);



ALTER TABLE ONLY documentofascicolo
    ADD CONSTRAINT "documentoFascicolo_id_amministrazione_fkey" FOREIGN KEY (id_amministrazione) REFERENCES amministrazione(id);



ALTER TABLE ONLY documentofascicolo
    ADD CONSTRAINT "documentoFascicolo_id_areaorganizzativaomogenea_fkey" FOREIGN KEY (id_areaorganizzativaomogenea) REFERENCES areaorganizzativaomogenea(id);



ALTER TABLE ONLY documento
    ADD CONSTRAINT documento_id_documentofascicolo_fkey FOREIGN KEY (id_documentofascicolo) REFERENCES documentofascicolo(id);



ALTER TABLE ONLY documentofascicolo
    ADD CONSTRAINT documentofascicolo_id_fascicolo_fkey FOREIGN KEY (id_fascicolo) REFERENCES fascicolo(id);



ALTER TABLE ONLY fascicolo
    ADD CONSTRAINT fascicolo_id_classifica_fkey FOREIGN KEY (id_classifica) REFERENCES classifica(id);



ALTER TABLE ONLY responsabiletrattamento
    ADD CONSTRAINT responsabiletrattamento_id_documentofascicolo_fkey FOREIGN KEY (id_documentofascicolo) REFERENCES documentofascicolo(id);



ALTER TABLE ONLY serviziotelematico_amministrazione
    ADD CONSTRAINT serviziotelematico_amministrazione_id_amministrazione_fkey FOREIGN KEY (id_amministrazione) REFERENCES amministrazione(id);



ALTER TABLE ONLY serviziotelematico_areaorganizzativaomogenea
    ADD CONSTRAINT serviziotelematico_areaorgani_id_areaorganizzativaomogenea_fkey FOREIGN KEY (id_areaorganizzativaomogenea) REFERENCES areaorganizzativaomogenea(id);



ALTER TABLE ONLY serviziotelematico_unitaorganizzativa
    ADD CONSTRAINT serviziotelematico_unitaorganizzativ_id_unitaorganizzativa_fkey FOREIGN KEY (id_unitaorganizzativa) REFERENCES unitaorganizzativa(id);



ALTER TABLE ONLY unitaorganizzativa
    ADD CONSTRAINT unitaorganizzativa_id_amministrazione_fkey FOREIGN KEY (id_amministrazione) REFERENCES amministrazione(id);



ALTER TABLE ONLY unitaorganizzativa
    ADD CONSTRAINT unitaorganizzativa_indirizzo_fkey FOREIGN KEY (indirizzo) REFERENCES localita(id);



ALTER TABLE ONLY unitaorganizzativa
    ADD CONSTRAINT unitaorganizzativa_responsabile_fkey FOREIGN KEY (responsabile) REFERENCES persona(id);



REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;



