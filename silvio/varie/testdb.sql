CREATE TABLE asl (
    id integer NOT NULL,
    nome character varying,
    codice integer
);


ALTER TABLE public.asl OWNER TO postgres;

CREATE TABLE utenti (
    id integer NOT NULL,
    nome character varying,
    cognome character varying,
    residenza character varying,
    codiceasl integer,
    codicefiscale character varying
);


ALTER TABLE public.utenti OWNER TO silvio;


INSERT INTO asl (id, nome, codice) VALUES (1, 'ASL numero 1 della Basilicata', 1);
INSERT INTO asl (id, nome, codice) VALUES (2, 'ASLxxx', 342);


INSERT INTO utenti (id, nome, cognome, residenza, codiceasl, codicefiscale) VALUES (100, 'Winston', 'Smith', 'GF', 1, 'wsgf');
INSERT INTO utenti (id, nome, cognome, residenza, codiceasl, codicefiscale) VALUES (101, 'Eddie', 'Vedder', 'Seattle', 1, 'pj');


ALTER TABLE ONLY asl
    ADD CONSTRAINT asl_pkey PRIMARY KEY (id);


ALTER TABLE ONLY utenti
    ADD CONSTRAINT utenti_codicefiscale_key UNIQUE (codicefiscale);


ALTER TABLE ONLY utenti
    ADD CONSTRAINT utenti_pkey PRIMARY KEY (id);


ALTER TABLE ONLY utenti
    ADD CONSTRAINT "utenti_codiceASL_fkey" FOREIGN KEY (codiceasl) REFERENCES asl(id);


REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM silvio;
GRANT ALL ON SCHEMA public TO silvio;
GRANT ALL ON SCHEMA public TO PUBLIC;