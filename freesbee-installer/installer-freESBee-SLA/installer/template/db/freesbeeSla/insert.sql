INSERT INTO utente (username, password) VALUES ('admin', '21232f297a57a5a743894a0e4a801fc3');

INSERT INTO configurazione (id, indirizzo_registro_servizi,nome_SLA, tipo_SLA, accordo_servizio_sla, nome_servizio_monitoraggio_sla, tipo_servizio_monitoraggio_sla, pd_monitoraggio_sla, accordo_servizio_stato, nome_servizio_monitoraggio_stato, tipo_servizio_monitoraggio_stato, pd_monitoraggio_stato) VALUES (1, 'http://192.168.7.6:8171/ws/registroServizi','NICA', 'SPC','ASMonitoraggioSla','ServizioMonitoraggioSLA', 'SPC',  'http://192.168.7.6:8172/PD/PD_MONITORAGGIO_SLA/', 'ASMonitoraggioStato','ServizioMonitoraggioStato', 'SPC','http://192.168.7.6:8172/PD/PD_MONITORAGGIO_STATO/');

INSERT INTO configurazione_sp (id, url_freesbee_sp, risorsa, autenticazione, risorsa_pd_monitoraggio_sla, risorsa_pd_monitoraggio_stato) VALUES (1, 'http://192.168.7.6:8082/freesbeesp', 'https://sp.example.unibas.org/', 'UTENTE', 'PD_MONITORAGGIO_SLA/', 'PD_MONITORAGGIO_STATO/');

--INSERT INTO icar_inf2_soggetto (id, nome_soggetto) VALUES (1, 'FruitoreUNICAR');
--INSERT INTO icar_inf2_soggetto (id, nome_soggetto) VALUES (2, 'ErogatoreUNICAR');
--INSERT INTO icar_inf2_soggetto (id, nome_soggetto) VALUES (3, 'FruitoreUNIBAS');
--INSERT INTO icar_inf2_soggetto (id, nome_soggetto) VALUES (4, 'ErogatoreUNIBAS');

--INSERT INTO icar_inf2_service (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_id_agreement, inf2_id_state, inf2_sla_object, inf2_count_pending_request, inf2_active) VALUES ('IcarSincronoLoopbackUNIBAS', 'FruitoreUNIBAS', 'ErogatoreUNIBAS', 'provaUNIBAS', 'Ready_Idle', '<?xml version="1.0" encoding="UTF-8"?>
--<wsag:GuaranteeTerms xmlns:wsag="http://schemas.ggf.org/graap/2007/03/ws-agreement" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://schemas.ggf.org/graap/2007/03/ws-agreement GuaranteeTerms.xsd">
--	<wsag:GuaranteeTerm wsag:Name="TempoRispostaMedio1M" wsag:Obligated="ServiceProvider">
--		<wsag:ServiceLevelObjective>
--			<wsag:CustomServiceLevel>
--				<wsag:Threshold wsag:Operator="LessEqual" wsag:Value="500"/>
--				<wsag:Function wsag:resultType="double" xsi:type="wsag:Mean">
--					<wsag:BasicMetric wsag:Unit="milliseconds">tempoRisposta</wsag:BasicMetric>
--					<wsag:Window>
--						<wsag:Interval>Month</wsag:Interval>
--					</wsag:Window>
--				</wsag:Function>
--			</wsag:CustomServiceLevel>
--		</wsag:ServiceLevelObjective>
--		<wsag:BusinessValueList/>
--	</wsag:GuaranteeTerm>
--	<wsag:GuaranteeTerm wsag:Name="MaxTempoRisposta5" wsag:Obligated="ServiceProvider">
--		<wsag:ServiceLevelObjective>
--			<wsag:CustomServiceLevel>
--				<wsag:Threshold wsag:Operator="Less" wsag:Value="1100"/>
--				<wsag:Function wsag:resultType="double" xsi:type="wsag:Max">
--					<wsag:BasicMetric wsag:Unit="milliseconds">tempoRisposta</wsag:BasicMetric>
--					<wsag:Window>
--						<wsag:Times>5</wsag:Times>
--					</wsag:Window>
--				</wsag:Function>
--			</wsag:CustomServiceLevel>
--		</wsag:ServiceLevelObjective>
--		<wsag:BusinessValueList/>
--	</wsag:GuaranteeTerm>
--</wsag:GuaranteeTerms>', 0, 1);

--INSERT INTO icar_inf2_service (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_id_agreement, inf2_id_state, inf2_sla_object, inf2_count_pending_request, inf2_active) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'ErogatoreUNICAR', 'agreementId', 'Ready_Idle', '<?xml version="1.0" encoding="UTF-8"?>
--<wsag:GuaranteeTerms xmlns:wsag="http://schemas.ggf.org/graap/2007/03/ws-agreement" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://schemas.ggf.org/graap/2007/03/ws-agreement GuaranteeTerms.xsd">
--	<wsag:GuaranteeTerm wsag:Name="TempoRispostaMedio1M" wsag:Obligated="ServiceProvider">
--		<wsag:ServiceLevelObjective>
--			<wsag:CustomServiceLevel>
--				<wsag:Threshold wsag:Operator="LessEqual" wsag:Value="500"/>
--				<wsag:Function wsag:resultType="double" xsi:type="wsag:Mean">
--					<wsag:BasicMetric wsag:Unit="milliseconds">tempoRisposta</wsag:BasicMetric>
--					<wsag:Window>
--						<wsag:Interval>Month</wsag:Interval>
--					</wsag:Window>
--				</wsag:Function>
--			</wsag:CustomServiceLevel>
--		</wsag:ServiceLevelObjective>
--		<wsag:BusinessValueList/>
--	</wsag:GuaranteeTerm>
--	<wsag:GuaranteeTerm wsag:Name="MaxTempoRisposta5" wsag:Obligated="ServiceProvider">
--		<wsag:ServiceLevelObjective>
--			<wsag:CustomServiceLevel>
--				<wsag:Threshold wsag:Operator="Less" wsag:Value="1100"/>
--				<wsag:Function wsag:resultType="double" xsi:type="wsag:Max">
--					<wsag:BasicMetric wsag:Unit="milliseconds">tempoRisposta</wsag:BasicMetric>
--					<wsag:Window>
--						<wsag:Times>5</wsag:Times>
--					</wsag:Window>
--				</wsag:Function>
--			</wsag:CustomServiceLevel>
--		</wsag:ServiceLevelObjective>
--		<wsag:BusinessValueList/>
--	</wsag:GuaranteeTerm>
--</wsag:GuaranteeTerms>', 0, 1);

--INSERT INTO icar_inf2_service (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_id_agreement, inf2_id_state, inf2_sla_object, inf2_count_pending_request, inf2_active) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'Erogatore', 'agreementId', 'Ready_Idle', '<?xml version="1.0" encoding="UTF-8"?>
--<wsag:GuaranteeTerms xmlns:wsag="http://schemas.ggf.org/graap/2007/03/ws-agreement" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://schemas.ggf.org/graap/2007/03/ws-agreement GuaranteeTerms.xsd">
--	<wsag:GuaranteeTerm wsag:Name="TempoRispostaMedio1M" wsag:Obligated="ServiceProvider">
--		<wsag:ServiceLevelObjective>
--			<wsag:CustomServiceLevel>
--				<wsag:Threshold wsag:Operator="LessEqual" wsag:Value="500"/>
--				<wsag:Function wsag:resultType="double" xsi:type="wsag:Mean">
--					<wsag:BasicMetric wsag:Unit="milliseconds">tempoRisposta</wsag:BasicMetric>
--					<wsag:Window>
--						<wsag:Interval>Month</wsag:Interval>
--					</wsag:Window>
--				</wsag:Function>
--			</wsag:CustomServiceLevel>
--		</wsag:ServiceLevelObjective>
--		<wsag:BusinessValueList/>
--	</wsag:GuaranteeTerm>
--	<wsag:GuaranteeTerm wsag:Name="MaxTempoRisposta5" wsag:Obligated="ServiceProvider">
--		<wsag:ServiceLevelObjective>
--			<wsag:CustomServiceLevel>
--				<wsag:Threshold wsag:Operator="Less" wsag:Value="1100"/>
--				<wsag:Function wsag:resultType="double" xsi:type="wsag:Max">
--					<wsag:BasicMetric wsag:Unit="milliseconds">tempoRisposta</wsag:BasicMetric>
--					<wsag:Window>
--						<wsag:Times>5</wsag:Times>
--					</wsag:Window>
--				</wsag:Function>
--			</wsag:CustomServiceLevel>
--		</wsag:ServiceLevelObjective>
--		<wsag:BusinessValueList/>
--	</wsag:GuaranteeTerm>
--</wsag:GuaranteeTerms>', 0, 1);


--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'ErogatoreUNICAR', 'tempoRisposta', '2008-04-18 20:20:18', 33, 589);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'ErogatoreUNICAR', 'tempoRisposta', '2008-04-18 20:20:23', 24, 992);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'ErogatoreUNICAR', 'tempoRisposta', '2008-04-18 20:20:27', 38, 864);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'ErogatoreUNICAR', 'tempoRisposta', '2008-04-18 20:20:32', 26, 92);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'ErogatoreUNICAR', 'tempoRisposta', '2008-04-18 20:20:35', 28, 847);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'ErogatoreUNICAR', 'tempoRisposta', '2008-04-18 20:20:39', 29, 900);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'ErogatoreUNICAR', 'tempoRisposta', '2008-04-18 20:20:53', 43, 686);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'ErogatoreUNICAR', 'tempoRisposta', '2008-04-18 20:58:40', 29, 914);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'ErogatoreUNICAR', 'tempoRisposta', '2008-05-05 17:21:18', 42, 782);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'ErogatoreUNICAR', 'tempoRisposta', '2008-05-05 17:21:41', 25, 13);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'ErogatoreUNICAR', 'tempoRisposta', '2008-05-05 17:22:03', 34, 934);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'ErogatoreUNICAR', 'tempoRisposta', '2008-05-05 17:21:56', 29, 988);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'ErogatoreUNICAR', 'tempoRisposta', '2008-05-05 17:23:35', 35, 10);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'ErogatoreUNICAR', 'tempoRisposta', '2008-05-05 18:09:11', 447, 242);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'ErogatoreUNICAR', 'tempoRisposta', '2008-05-05 18:40:11', 34, 312);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'ErogatoreUNICAR', 'tempoRisposta', '2008-05-05 18:40:40', 27, 745);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'ErogatoreUNICAR', 'tempoRisposta', '2008-05-05 18:57:26', 26, 664);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'ErogatoreUNICAR', 'tempoRisposta', '2008-05-05 18:59:24', 26, 789);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'ErogatoreUNICAR', 'tempoRisposta', '2008-05-05 19:00:56', 35, 14);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'ErogatoreUNICAR', 'tempoRisposta', '2008-05-05 19:04:16', 25, 645);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'ErogatoreUNICAR', 'tempoRisposta', '2008-05-05 19:04:35', 487, 825);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'ErogatoreUNICAR', 'tempoRisposta', '2008-05-05 19:06:16', 35, 545);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'ErogatoreUNICAR', 'tempoRisposta', '2008-05-05 19:06:34', 26, 858);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'ErogatoreUNICAR', 'tempoRisposta', '2008-05-05 19:06:54', 25, 769);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'ErogatoreUNICAR', 'tempoRisposta', '2008-05-05 19:07:14', 36, 141);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'ErogatoreUNICAR', 'tempoRisposta', '2008-05-05 19:07:37', 46, 289);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'ErogatoreUNICAR', 'tempoRisposta', '2008-05-05 19:08:14', 34, 119);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'ErogatoreUNICAR', 'tempoRisposta', '2008-05-05 19:09:35', 84, 318);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'ErogatoreUNICAR', 'tempoRisposta', '2008-05-05 19:09:36', 100, 859);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'ErogatoreUNICAR', 'tempoRisposta', '2008-05-05 19:09:38', 126, 694);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'ErogatoreUNICAR', 'tempoRisposta', '2008-05-05 19:09:40', 67, 925);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'ErogatoreUNICAR', 'tempoRisposta', '2008-05-05 19:10:58', 267, 19);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'ErogatoreUNICAR', 'tempoRisposta', '2008-05-05 19:11:21', 51, 334);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'ErogatoreUNICAR', 'tempoRisposta', '2008-05-05 19:11:36', 379, 519);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'ErogatoreUNICAR', 'tempoRisposta', '2008-05-05 19:11:46', 67, 809);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'ErogatoreUNICAR', 'tempoRisposta', '2008-05-05 19:12:26', 67, 623);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'ErogatoreUNICAR', 'tempoRisposta', '2008-05-05 19:12:29', 117, 990);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'ErogatoreUNICAR', 'tempoRisposta', '2008-05-05 19:12:32', 102, 16);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'ErogatoreUNICAR', 'tempoRisposta', '2008-05-05 19:12:33', 121, 119);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'ErogatoreUNICAR', 'tempoRisposta', '2008-05-05 19:16:27', 34, 913);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'ErogatoreUNICAR', 'tempoRisposta', '2008-05-05 19:16:29', 68, 904);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'ErogatoreUNICAR', 'tempoRisposta', '2008-05-05 19:16:32', 100, 371);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'ErogatoreUNICAR', 'tempoRisposta', '2008-05-05 19:16:34', 104, 974);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'ErogatoreUNICAR', 'tempoRisposta', '2008-05-05 19:18:33', 72, 214);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'ErogatoreUNICAR', 'tempoRisposta', '2008-05-05 19:18:35', 110, 58);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'ErogatoreUNICAR', 'tempoRisposta', '2008-05-05 19:18:37', 117, 120);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'ErogatoreUNICAR', 'tempoRisposta', '2008-05-05 19:18:38', 87, 888);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopbackUNIBAS', 'FruitoreUNIBAS', 'ErogatoreUNIBAS', 'tempoRisposta', '2008-04-18 20:20:18', 1500, 589);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopbackUNIBAS', 'FruitoreUNIBAS', 'ErogatoreUNIBAS', 'tempoRisposta', '2008-04-18 20:20:23', 1000, 992);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopbackUNIBAS', 'FruitoreUNIBAS', 'ErogatoreUNIBAS', 'tempoRisposta', '2008-04-18 20:20:27', 1200, 864);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopbackUNIBAS', 'FruitoreUNIBAS', 'ErogatoreUNIBAS', 'tempoRisposta', '2008-04-18 20:20:32', 1600, 92);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopbackUNIBAS', 'FruitoreUNIBAS', 'ErogatoreUNIBAS', 'tempoRisposta', '2008-04-18 20:20:35', 1550, 847);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopbackUNIBAS', 'FruitoreUNIBAS', 'ErogatoreUNIBAS', 'tempoRisposta', '2008-04-18 20:20:39', 1900, 900);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopbackUNIBAS', 'FruitoreUNIBAS', 'ErogatoreUNIBAS', 'tempoRisposta', '2008-04-18 20:20:44', 800, 220);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopbackUNIBAS', 'FruitoreUNIBAS', 'ErogatoreUNIBAS', 'tempoRisposta', '2008-04-18 20:20:53', 1000, 686);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopbackUNIBAS', 'FruitoreUNIBAS', 'ErogatoreUNIBAS', 'tempoRisposta', '2008-04-18 20:20:40', 750, 914);


--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'Erogatore', 'tempoRisposta', '2008-04-18 20:20:18', 33, 589);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'Erogatore', 'tempoRisposta', '2008-04-18 20:20:23', 24, 992);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'Erogatore', 'tempoRisposta', '2008-04-18 20:20:27', 38, 864);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'Erogatore', 'tempoRisposta', '2008-04-18 20:20:32', 26, 92);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'Erogatore', 'tempoRisposta', '2008-04-18 20:20:35', 28, 847);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'Erogatore', 'tempoRisposta', '2008-04-18 20:20:39', 29, 900);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'Erogatore', 'tempoRisposta', '2008-04-18 20:20:53', 43, 686);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'Erogatore', 'tempoRisposta', '2008-04-18 20:58:40', 29, 914);
--INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('IcarSincronoLoopback', 'FruitoreUNICAR', 'Erogatore', 'tempoRisposta', '2008-05-05 17:21:18', 42, 782);




--INSERT INTO  icar_inf2_error  VALUES   ('000','ok',NULL);
--INSERT INTO  icar_inf2_error  VALUES   ('001','ok: cambiato stato e incrementato contatore',NULL);
--INSERT INTO  icar_inf2_error  VALUES   ('002','ok: incrementato contatore ',NULL);
--INSERT INTO  icar_inf2_error  VALUES   ('003','ok: decrementato contatore',NULL);
--INSERT INTO  icar_inf2_error  VALUES   ('004','ok: cambiato stato e decrementato contatore',NULL);
--INSERT INTO  icar_inf2_error  VALUES   ('005','ok: stato aggiornato o update non effettuato',NULL);
--INSERT INTO  icar_inf2_error  VALUES   ('100','warning','warning');
--INSERT INTO  icar_inf2_error  VALUES   ('101','warning: stato non gestito','warning');
--INSERT INTO  icar_inf2_error  VALUES   ('200','errore generico','errore');
--INSERT INTO  icar_inf2_error  VALUES   ('201','errore: chiave duplicata','errore');
--INSERT INTO  icar_inf2_error  VALUES   ('202','errore: trovati piu'' elementi','errore');
--INSERT INTO  icar_inf2_error  VALUES   ('203','errore: tracciatura non consentita-accordo non registrato o non attivo','errore');
--INSERT INTO  icar_inf2_error  VALUES   ('204','errore: nessun record trovato o modificato','errore');
--INSERT INTO  icar_inf2_error  VALUES   ('205','errore: stato inesistente','errore');

--INSERT INTO  icar_inf2_service_catalog_state  VALUES   (0,'NOT_READY');
--INSERT INTO  icar_inf2_service_catalog_state  VALUES   (1,'READY_IDLE');
--INSERT INTO  icar_inf2_service_catalog_state  VALUES   (2,'READY_PROCESSING');
--INSERT INTO  icar_inf2_service_catalog_state  VALUES   (3,'COMPLETED');

--INSERT INTO  icar_inf2_sla_catalog_state  VALUES   (0,'NOT_DETERMINED');
--INSERT INTO  icar_inf2_sla_catalog_state  VALUES   (1,'FULFILLED');
--INSERT INTO  icar_inf2_sla_catalog_state  VALUES   (2,'VIOLATED');



