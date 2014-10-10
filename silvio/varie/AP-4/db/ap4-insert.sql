BEGIN TRANSACTION;
SET CONSTRAINTS ALL DEFERRED;

INSERT INTO scheda_anagrafica_professionale (codice_fiscale, cognome, nome, sesso, data_nascita)VALUES('codicefiscale1', 'cognome1', 'nome1', 'm', '2008-07-14 12:00:01' );
INSERT INTO scheda_anagrafica_professionale (codice_fiscale, cognome, nome, sesso, data_nascita)VALUES('codicefiscale2', 'cognome2', 'nome2', 'm', '2008-07-14 12:00:02' );
INSERT INTO scheda_anagrafica_professionale (codice_fiscale, cognome, nome, sesso, data_nascita)VALUES('codicefiscale3', 'cognome3', 'nome3', 'm', '2008-07-14 12:00:03' );
INSERT INTO scheda_anagrafica_professionale (codice_fiscale, cognome, nome, sesso, data_nascita)VALUES('codicefiscale4', 'cognome4', 'nome4', 'm', '2008-07-14 12:00:04' );
INSERT INTO scheda_anagrafica_professionale (codice_fiscale, cognome, nome, sesso, data_nascita)VALUES('codicefiscale5', 'cognome5', 'nome5', 'm', '2008-07-14 12:00:05' );
INSERT INTO scheda_anagrafica_professionale (codice_fiscale, cognome, nome, sesso, data_nascita)VALUES('codicefiscale6', 'cognome6', 'nome6', 'm', '2008-07-14 12:00:06' );
INSERT INTO scheda_anagrafica_professionale (codice_fiscale, cognome, nome, sesso, data_nascita)VALUES('codicefiscale7', 'cognome7', 'nome7', 'm', '2008-07-14 12:00:07' );

COMMIT TRANSACTION;
