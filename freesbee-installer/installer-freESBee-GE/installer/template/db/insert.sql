
INSERT INTO categoriaeventiinterna (id, attiva, nome) VALUES (1, true, 'GEControlProtocol');
INSERT INTO categoriaeventiesterna (id, attiva, nome, inattesa) VALUES (1, true, 'GEControlProtocol', false);

--Il gestore eventi deve essere registrato
INSERT INTO gestoreeventi (id, nome, tipo, descrizione) VALUES (1, 'NICA_PUGLIA', 'SPC', '');
--Il gestore eventi deve essere pubblicatore di GEControlProtocol
INSERT INTO pubblicatoreinterno(id, nome, tipo, descrizione) VALUES (1, 'NICA_PUGLIA', 'SPC', '');
INSERT INTO pubblicatoreinterno_categoriaeventiinterna(listapubblicatori_id,listacatgoriaeventi_id) VALUES (1,1);

INSERT INTO sottoscrittore (id, nome, tipo, descrizione) VALUES (1, 'NICA_PUGLIA', 'SPC', '');
INSERT INTO filtrodata (id, datafine, datainizio) VALUES (1, NULL, '2009-06-10 20:20:00');
INSERT INTO sottoscrizioneesterna (id, tiposottoscrizione, categoriaeventi_id, sottoscrittore_id, filtrodata_id, filtrocontenuto_id) VALUES (1, 'CONSEGNA', 1, 1, 1, NULL);

INSERT INTO configurazione (id,azioneservizioconsegna,azioneservizionotifica, nomegestoreeventi, nomeservizioconsegna, nomeservizionotifica, nomeserviziopubblica, pdconsegna, pdnotifica, pdpubblica, scadenzamessaggi, tipogestoreeventi, tiposervizioconsegna, tiposervizionotifica, tiposerviziopubblica) VALUES (1,'urn:consegnaMessaggio','urn:notificaMessaggio', 'NICA_PUGLIA', 'ServizioConsegna', 'ServizioNotifica', 'ServizioPubblica', 'http://localhost:8111/PD/PD_CONSEGNA/', 'http://localhost:8111/PD/PD_NOTIFICA/', 'http://localhost:8111/PD/PD_PUBBLICA/', 1296000, 'SPC', 'SPC', 'SPC', 'SPC');

INSERT INTO configurazionesp (id, autenticazione, nomeutentesp, passwordsp, risorsa, risorsapdconsegna, risorsapdnotifica, risorsapdpubblica, urlfreesbeesp) VALUES (1, 'AGENTE', 'agentege', 'agentege', 'https://sp.example.unibas.org/', 'PD_CONSEGNA/', 'PD_NOTIFICA/', 'PD_PUBBLICA/', 'https://192.168.7.17:8443/freesbeesp/schermoLogin.faces');

INSERT INTO utente (nomeutente, password) VALUES ('admin', '21232f297a57a5a743894a0e4a801fc3');

INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('CategoriaEventiInterna', 1);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('CategoriaEventiEsterna', 1);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('GestoreEventi', 1);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('PubblicatoreInterno', 1);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('Sottoscrittore', 1);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('FiltroData', 1);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('SottoscrizioneInterna', 1);
INSERT INTO hibernate_sequences (sequence_name, sequence_next_hi_value) VALUES ('SottoscrizioneEsterna', 1);


