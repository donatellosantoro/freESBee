INSERT INTO icar_inf2_service (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_id_agreement, inf2_id_state, inf2_sla_object, inf2_count_pending_request, inf2_active) VALUES ('14', '140', '1400', 'provaUNIBAS', 'Ready_Idle', '<?xml version="1.0" encoding="UTF-8"?>
<wsag:GuaranteeTerms xmlns:wsag="http://schemas.ggf.org/graap/2007/03/ws-agreement" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://schemas.ggf.org/graap/2007/03/ws-agreement GuaranteeTerms.xsd">
<wsag:GuaranteeTerm wsag:Name="TempoMedioCorretto5_10" wsag:Obligated="ServiceProvider">
		<wsag:ServiceLevelObjective>
			<wsag:CustomServiceLevel>
			  <wsag:Threshold wsag:Operator="LessEqual" wsag:Value="3"/>			  
				<wsag:Function wsag:resultType="double" xsi:type="wsag:Divide" > 
			    <wsag:Operand>			      
			      <wsag:Function wsag:resultType="double" xsi:type="wsag:Plus">
			        <wsag:Operand>			          
			          <wsag:Function wsag:resultType="double" xsi:type="wsag:Mean"> 
			            <wsag:BasicMetric wsag:Unit="seconds">tempoRisposta</wsag:BasicMetric>
			            <wsag:Window>
			              <wsag:Times>5</wsag:Times>
			            </wsag:Window>
			          </wsag:Function>
			        </wsag:Operand>
			        <wsag:Operand>
			          <wsag:Function wsag:resultType="double" xsi:type="wsag:Mean"> 
			            <wsag:BasicMetric wsag:Unit="seconds">tempoRisposta</wsag:BasicMetric>
			            <wsag:Window>
			              <wsag:Times>10</wsag:Times>
			            </wsag:Window>
			          </wsag:Function>
			        </wsag:Operand>
			      </wsag:Function>
			    </wsag:Operand>
			    <wsag:Operand>
			      <wsag:LongScalar>2</wsag:LongScalar>
			    </wsag:Operand>
			  </wsag:Function>
			</wsag:CustomServiceLevel>
		</wsag:ServiceLevelObjective>
		<wsag:BusinessValueList/>
	</wsag:GuaranteeTerm>
    </wsag:GuaranteeTerms>', 0, 1);

INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-01-12 01:44:22', 290, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-01-14 01:44:25', 290, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-01-18 01:44:30', 960, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-01-18 01:44:35', 290, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-01-20 01:55:38', 289, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-01-20 01:55:42', 299, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-01-22 01:55:46', 390, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-01-22 01:33:50', 390, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-01-22 01:33:53', 390, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-01-22 01:33:56', 390, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-02-08 23:23:22', 249, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-02-18 23:32:35', 259, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-02-18 21:00:54', 269, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-02-18 12:00:54', 279, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-02-23 23:00:12', 289, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-02-23 23:00:54', 299, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-02-23 12:00:34', 349, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-02-25 22:00:44', 319, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-02-25 22:00:55', 329, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-02-25 22:00:22', 339, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-03-01 14:00:22', 740, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-03-01 22:00:25', 750, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-03-01 23:12:30', 660, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-03-03 12:32:35', 170, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-03-05 20:12:38', 180, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-03-13 20:21:42', 390, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-03-13 19:18:46', 240, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-03-13 18:44:50', 910, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-03-13 18:33:53', 220, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-03-13 22:22:56', 130, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-03-25 12:02:22', 565, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-03-25 12:20:25', 210, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-03-26 21:12:30', 250, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-03-26 21:40:35', 290, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-03-26 23:20:38', 180, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-03-26 12:05:42', 490, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-03-27 23:03:46', 390, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-03-27 12:07:50', 380, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-03-27 05:01:53', 330, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-03-27 03:06:56', 230, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-03-28 01:00:22', 240, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-03-28 01:00:25', 250, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-03-28 01:00:30', 260, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-03-28 01:00:35', 270, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-03-28 01:00:38', 280, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-03-28 01:00:42', 290, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-03-28 01:00:46', 340, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-03-28 01:00:50', 310, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-03-28 01:00:53', 320, 999);
INSERT INTO icar_inf2_sla_object_trace (inf2_id_service, inf2_id_initiator, inf2_id_responder, inf2_sla_basic_metric, inf2_sla_basic_metric_data_insert, inf2_sla_basic_metric_value, inf2_sla_basic_metric_milliseconds_insert) VALUES ('14', '140', '1400', 'tempoRisposta', '2008-03-28 01:00:56', 330, 999);
