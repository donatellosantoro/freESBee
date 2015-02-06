BEGIN TRANSACTION;
SET CONSTRAINTS ALL DEFERRED;

INSERT INTO "accordoservizio" VALUES ('131072', '', 'ASAttachment', '2015-01-15 15:39:12.73', null, 'f', 'EGOV_IT_ServizioSincrono', '131072');
INSERT INTO "hibernate_sequences" VALUES ('Configurazione', '1');
INSERT INTO "hibernate_sequences" VALUES ('Soggetto', '6');
INSERT INTO "hibernate_sequences" VALUES ('ProfiloEGov', '5');
INSERT INTO "hibernate_sequences" VALUES ('AccordoServizio', '5');
INSERT INTO "hibernate_sequences" VALUES ('Servizio', '5');
INSERT INTO "hibernate_sequences" VALUES ('Azione', '5');
INSERT INTO "hibernate_sequences" VALUES ('PortaDelegata', '9');
INSERT INTO "hibernate_sequences" VALUES ('ServizioApplicativo', '5');
INSERT INTO "hibernate_sequences" VALUES ('Messaggio', '2');
INSERT INTO "hibernate_sequences" VALUES ('PortaApplicativa', '6');
INSERT INTO "hibernate_sequences" VALUES ('Sincronizzazione', '2');
INSERT INTO "azione" VALUES ('131072', null, 'testAttachmentEcho', 'EGOV_IT_ServizioSincrono', '131072', null);
INSERT INTO "portaapplicativa" VALUES ('163840', '', 'PAAttachment', null, null, null, null, null, '131072', '131072', '131072');
INSERT INTO "portadelegata" VALUES ('262144', '', 'f', 'AttachmentTest', null, null, null, null, null, null, null, '131072', '131072', '163841');
INSERT INTO "profiloegov" VALUES ('131072', 'f', 'f', 't', 'f', null);
INSERT INTO "servizio" VALUES ('131072', null, 'f', 'f', 'ServizioAttachment', '2015-01-15 15:38:47.587', 'f', 'SPC', null, '131072', '163840');
INSERT INTO "servizio_soggetto" VALUES ('131072', '163841'); 
INSERT INTO "servizioapplicativo" VALUES ('131072', 'http://localhost:8191/ws/verificaInstallazione', '', 'f', 'AttachmentService');
INSERT INTO "soggetto" VALUES ('163840', '', 'f', 'ErogatoreTest', '2015-01-15 15:37:51.074', 'http://localhost:8196/PA/', 'SPC');
INSERT INTO "soggetto" VALUES ('163841', '', 'f', 'FruitoreTest', '2015-01-15 15:38:51.074', 'http://localhost:8196/PA/', 'SPC');

COMMIT TRANSACTION;
