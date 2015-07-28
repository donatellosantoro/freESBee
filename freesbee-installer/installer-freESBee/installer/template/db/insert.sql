BEGIN TRANSACTION;
SET CONSTRAINTS ALL DEFERRED;

INSERT INTO "accordoservizio" VALUES ('98304', '', 'ASLoopback', '2008-03-27 12:00:00', null, 'f', 'EGOV_IT_ServizioSincrono', '98304');
INSERT INTO "configurazione" VALUES ('1', 'f', '', 'http://0.0.0.0:8196/PA/', 'http://0.0.0.0:8192/PD/', 'f', 'f', 'f', 't', 'EGOV_IT_SPC', null, null);
INSERT INTO "hibernate_sequences" VALUES ('Configurazione', '1');
INSERT INTO "hibernate_sequences" VALUES ('Soggetto', '5');
INSERT INTO "hibernate_sequences" VALUES ('ProfiloEGov', '4');
INSERT INTO "hibernate_sequences" VALUES ('AccordoServizio', '4');
INSERT INTO "hibernate_sequences" VALUES ('Servizio', '4');
INSERT INTO "hibernate_sequences" VALUES ('Azione', '4');
INSERT INTO "hibernate_sequences" VALUES ('PortaDelegata', '8');
INSERT INTO "hibernate_sequences" VALUES ('ServizioApplicativo', '4');
INSERT INTO "hibernate_sequences" VALUES ('Messaggio', '1');
INSERT INTO "hibernate_sequences" VALUES ('PortaApplicativa', '5');
INSERT INTO "hibernate_sequences" VALUES ('Sincronizzazione', '2');
INSERT INTO "azione" VALUES ('1', null, 'echoService', 'EGOV_IT_ServizioSincrono', '98304', null);
INSERT INTO "portaapplicativa" VALUES ('131072', '', 'PALoopback', null, null, null, null, null, '1', '98304', '65536');
INSERT INTO "portadelegata" VALUES ('229376', '', 'f', 'EchoService', null, null, null, null, null, null, null, '1', '98304', '131072');
INSERT INTO "profiloegov" VALUES ('98304', 'f', 'f', 't', 'f', null);
INSERT INTO "servizio" VALUES ('98304', null, 'f', 'f', 'TestServizioLoopback', '2008-03-27 12:00:00', 'f', 'SPC', null, '98304', '131073');
INSERT INTO "servizio_soggetto" VALUES ('98304', '131072');
INSERT INTO "servizioapplicativo" VALUES ('65536', 'http://localhost:8191/ws/echoService', '', 'f', 'EchoService');
INSERT INTO "soggetto" VALUES ('131072', '', 'f', 'FruitoreLoopback', '2008-03-27 12:00:00', 'http://localhost:8196/PA/', 'SPC');
INSERT INTO "soggetto" VALUES ('131073', '', 'f', 'ErogatoreLoopback', '2008-03-27 12:00:00', 'http://localhost:8196/PA/', 'SPC');
INSERT INTO "utente" VALUES ('1', 'admin', '21232f297a57a5a743894a0e4a801fc3', 'amministratore');
INSERT INTO "utente" VALUES ('2', 'user', 'ee11cbb19052e40b07aac0ca060c23ee', 'user');


COMMIT TRANSACTION;

