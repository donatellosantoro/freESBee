# TEST 1 e TEST 2
insert into icar_inf2_service values (1,10,100,'prova',1,'<?xml version="1.0" encoding="UTF-8"?>
<wsag:GuaranteeTerms xmlns:wsag="http://schemas.ggf.org/graap/2007/03/ws-agreement" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://schemas.ggf.org/graap/2007/03/ws-agreement GuaranteeTerms.xsd">
	<wsag:GuaranteeTerm wsag:Name="TempoRispostaMedio1M" wsag:Obligated="ServiceProvider">
		<wsag:ServiceLevelObjective>
			<wsag:CustomServiceLevel>
				<wsag:Threshold wsag:Operator="LessEqual" wsag:Value="500"/>
				<wsag:Function wsag:resultType="double" xsi:type="wsag:Mean">
					<wsag:BasicMetric wsag:Unit="milliseconds">tempoRisposta</wsag:BasicMetric>
					<wsag:Window>
						<wsag:Interval>Month</wsag:Interval>
					</wsag:Window>
				</wsag:Function>
			</wsag:CustomServiceLevel>
		</wsag:ServiceLevelObjective>
		<wsag:BusinessValueList/>
	</wsag:GuaranteeTerm>
	<wsag:GuaranteeTerm wsag:Name="MaxTempoRisposta5" wsag:Obligated="ServiceProvider">
		<wsag:ServiceLevelObjective>
			<wsag:CustomServiceLevel>
				<wsag:Threshold wsag:Operator="Less" wsag:Value="1100"/>
				<wsag:Function wsag:resultType="double" xsi:type="wsag:Max">
					<wsag:BasicMetric wsag:Unit="milliseconds">tempoRisposta</wsag:BasicMetric>
					<wsag:Window>
						<wsag:Times>5</wsag:Times>
					</wsag:Window>
				</wsag:Function>
			</wsag:CustomServiceLevel>
		</wsag:ServiceLevelObjective>
		<wsag:BusinessValueList/>
	</wsag:GuaranteeTerm>
</wsag:GuaranteeTerms>',0,1);


insert into icar_inf2_sla_object_trace values (1,10,100,'tempoRisposta','2008-03-28 01:00:02',300,999);
insert into icar_inf2_sla_object_trace values (1,10,100,'tempoRisposta','2008-03-28 01:00:03',400,999);
insert into icar_inf2_sla_object_trace values (1,10,100,'tempoRisposta','2008-03-28 01:00:04',500,999);
insert into icar_inf2_sla_object_trace values (1,10,100,'tempoRisposta','2008-03-28 01:00:05',5400,999);
insert into icar_inf2_sla_object_trace values (1,10,100,'tempoRisposta','2008-03-28 01:00:06',3400,999);
insert into icar_inf2_sla_object_trace values (1,10,100,'tempoRisposta','2008-03-28 01:00:07',200,999);
insert into icar_inf2_sla_object_trace values (1,10,100,'tempoRisposta','2008-03-28 01:00:08',500,999);
insert into icar_inf2_sla_object_trace values (1,10,100,'tempoRisposta','2008-03-28 01:00:09',500,999);
insert into icar_inf2_sla_object_trace values (1,10,100,'tempoRisposta','2008-03-28 01:00:12',100,999);
insert into icar_inf2_sla_object_trace values (1,10,100,'tempoRisposta','2008-03-28 01:00:22',230,999);



# TEST 3
insert into icar_inf2_service values (2,20,200,'prova2',1,'<?xml version="1.0" encoding="UTF-8"?>
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
</wsag:GuaranteeTerms>',0,1);
insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-01-12 01:44:22',290,999);
insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-01-14 01:44:25',290,999);
insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-01-18 01:44:30',960,999);
insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-01-18 01:44:35',290,999);
insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-01-20 01:55:38',289,999);
insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-01-20 01:55:42',299,999);
insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-01-22 01:55:46',390,999);
insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-01-22 01:33:50',390,999);
insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-01-22 01:33:53',390,999);
insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-01-22 01:33:56',390,999);				
				
insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-02-08 23:23:22',249,999);
insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-02-18 23:32:35',259,999);
insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-02-18 21:00:54',269,999);
insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-02-18 12:00:54',279,999);
insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-02-23 23:00:12',289,999);
insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-02-23 23:00:54',299,999);
insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-02-23 12:00:34',349,999);
insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-02-25 22:00:44',319,999);
insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-02-25 22:00:55',329,999);
insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-02-25 22:00:22',339,999);
				
				
insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-03-01 14:00:22',740,999);
insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-03-01 22:00:25',750,999);
insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-03-01 23:12:30',660,999);
insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-03-03 12:32:35',170,999);
insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-03-05 20:12:38',180,999);
insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-03-13 20:21:42',390,999);
insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-03-13 19:18:46',240,999);
insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-03-13 18:44:50',910,999);
insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-03-13 18:33:53',220,999);
insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-03-13 22:22:56',130,999);
				

insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-03-25 12:02:22',565,999);
insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-03-25 12:20:25',210,999);
insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-03-26 21:12:30',250,999);
insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-03-26 21:40:35',290,999);
insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-03-26 23:20:38',180,999);
insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-03-26 12:05:42',490,999);
insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-03-27 23:03:46',390,999);
insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-03-27 12:07:50',380,999);
insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-03-27 05:01:53',330,999);
insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-03-27 03:06:56',230,999);


insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-03-28 01:00:22',240,999);
insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-03-28 01:00:25',250,999);
insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-03-28 01:00:30',260,999);
insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-03-28 01:00:35',270,999);
insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-03-28 01:00:38',280,999);
insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-03-28 01:00:42',290,999);
insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-03-28 01:00:46',340,999);
insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-03-28 01:00:50',310,999);
insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-03-28 01:00:53',320,999);
insert into icar_inf2_sla_object_trace values (2,20,200,'tempoRisposta','2008-03-28 01:00:56',330,999);


# TEST 4
insert into icar_inf2_service values (4,40,400,'prova4',1,'<wsag:GuaranteeTerms xmlns:wsag="http://schemas.ggf.org/graap/2007/03/ws-agreement" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://schemas.ggf.org/graap/2007/03/ws-agreement GuaranteeTerms.xsd">

					<wsag:GuaranteeTerm wsag:Name="TempoRispostaMedio1MUniSomm" wsag:Obligated="ServiceProvider">
						<wsag:ServiceLevelObjective>
							<wsag:CustomServiceLevel>
								<wsag:Threshold wsag:Operator="Less" wsag:Value="3"/>
								<wsag:Function wsag:resultType="double" xsi:type="wsag:Mean">
									<wsag:BasicMetric wsag:Unit="seconds">tempoRisposta</wsag:BasicMetric>
									<wsag:Window>
										<wsag:Interval>Month</wsag:Interval>
									</wsag:Window>
								</wsag:Function>
							</wsag:CustomServiceLevel>
						</wsag:ServiceLevelObjective>
						<wsag:BusinessValueList/>
					</wsag:GuaranteeTerm>
					<wsag:GuaranteeTerm wsag:Name="MassimoTempoRispostaUniSomm" wsag:Obligated="ServiceProvider">
						<wsag:ServiceLevelObjective>
							<wsag:CustomServiceLevel>
								<wsag:Threshold wsag:Operator="Less" wsag:Value="45"/>
								<wsag:Function wsag:resultType="double" xsi:type="wsag:Max">
									<wsag:BasicMetric wsag:Unit="seconds">tempoRisposta</wsag:BasicMetric>
									<wsag:Window>
										<wsag:Interval>TwoWeeks</wsag:Interval>
									</wsag:Window>
								</wsag:Function>
							</wsag:CustomServiceLevel>
						</wsag:ServiceLevelObjective>
						<wsag:BusinessValueList/>
					</wsag:GuaranteeTerm>
					<wsag:GuaranteeTerm wsag:Name="TempoRispostaMedio1MUniUrg" wsag:Obligated="ServiceProvider">
						<wsag:ServiceLevelObjective>
							<wsag:CustomServiceLevel>
								<wsag:Threshold wsag:Operator="Less" wsag:Value="3"/>
								<wsag:Function wsag:resultType="double" xsi:type="wsag:Sum">
									<wsag:BasicMetric wsag:Unit="seconds">tempoRisposta</wsag:BasicMetric>
									<wsag:Window>
										<wsag:Interval>Week</wsag:Interval>
									</wsag:Window>
								</wsag:Function>
							</wsag:CustomServiceLevel>
						</wsag:ServiceLevelObjective>
						<wsag:BusinessValueList/>
					</wsag:GuaranteeTerm>
					<wsag:GuaranteeTerm wsag:Name="MassimoTempoRispostaUniUrg" wsag:Obligated="ServiceProvider">
						<wsag:ServiceLevelObjective>
							<wsag:CustomServiceLevel>
								<wsag:Threshold wsag:Operator="Less" wsag:Value="45"/>
								<wsag:Function wsag:resultType="double" xsi:type="wsag:Sum">
									<wsag:BasicMetric wsag:Unit="seconds">tempoRisposta</wsag:BasicMetric>
									<wsag:Window>
										<wsag:Interval>SixHours</wsag:Interval>
									</wsag:Window>
								</wsag:Function>
							</wsag:CustomServiceLevel>
						</wsag:ServiceLevelObjective>
						<wsag:BusinessValueList/>
					</wsag:GuaranteeTerm>
					<wsag:GuaranteeTerm wsag:Name="TempoRispostaMedio1MUniLav" wsag:Obligated="ServiceProvider">
						<wsag:ServiceLevelObjective>
							<wsag:CustomServiceLevel>
								<wsag:Threshold wsag:Operator="Less" wsag:Value="3"/>
								<wsag:Function wsag:resultType="double" xsi:type="wsag:Mean">
									<wsag:BasicMetric wsag:Unit="seconds">tempoRisposta</wsag:BasicMetric>
									<wsag:Window>
										<wsag:Times>5</wsag:Times>
									</wsag:Window>
								</wsag:Function>
							</wsag:CustomServiceLevel>
						</wsag:ServiceLevelObjective>
						<wsag:BusinessValueList/>
					</wsag:GuaranteeTerm>
					<wsag:GuaranteeTerm wsag:Name="MassimoTempoRispostaUniLav" wsag:Obligated="ServiceProvider">
						<wsag:ServiceLevelObjective>
							<wsag:CustomServiceLevel>
								<wsag:Threshold wsag:Operator="Less" wsag:Value="45"/>
								<wsag:Function wsag:resultType="double" xsi:type="wsag:Max">
									<wsag:BasicMetric wsag:Unit="seconds">tempoRisposta</wsag:BasicMetric>
									<wsag:Window>
										<wsag:Times>5</wsag:Times>
									</wsag:Window>
								</wsag:Function>
							</wsag:CustomServiceLevel>
						</wsag:ServiceLevelObjective>
						<wsag:BusinessValueList/>
					</wsag:GuaranteeTerm>
					<wsag:GuaranteeTerm wsag:Name="TempoRispostaMedio1MVarDatori" wsag:Obligated="ServiceProvider">
						<wsag:ServiceLevelObjective>
							<wsag:CustomServiceLevel>
								<wsag:Threshold wsag:Operator="Less" wsag:Value="3"/>
								<wsag:Function wsag:resultType="double" xsi:type="wsag:Sum">
									<wsag:BasicMetric wsag:Unit="seconds">tempoRisposta</wsag:BasicMetric>
									<wsag:Window>
										<wsag:Times>5</wsag:Times>
									</wsag:Window>
								</wsag:Function>
							</wsag:CustomServiceLevel>
						</wsag:ServiceLevelObjective>
						<wsag:BusinessValueList/>
					</wsag:GuaranteeTerm>
					<wsag:GuaranteeTerm wsag:Name="MassimoTempoRispostaVarDatori" wsag:Obligated="ServiceProvider">
						<wsag:ServiceLevelObjective>
							<wsag:CustomServiceLevel>
								<wsag:Threshold wsag:Operator="Less" wsag:Value="45"/>
								<wsag:Function wsag:resultType="double" xsi:type="wsag:Sum">
									<wsag:BasicMetric wsag:Unit="seconds">tempoRisposta</wsag:BasicMetric>
									<wsag:Window>
										<wsag:Times>5</wsag:Times>
									</wsag:Window>
								</wsag:Function>
							</wsag:CustomServiceLevel>
						</wsag:ServiceLevelObjective>
						<wsag:BusinessValueList/>
					</wsag:GuaranteeTerm>
				</wsag:GuaranteeTerms>',0,1);
				
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-01-12 01:44:22',290,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-01-14 01:44:25',290,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-01-18 01:44:30',960,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-01-18 01:44:35',290,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-01-20 01:55:38',289,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-01-20 01:55:42',299,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-01-22 01:55:46',390,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-01-22 01:33:50',390,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-01-22 01:33:53',390,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-01-22 01:33:56',390,999);				
				
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-02-08 23:23:22',249,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-02-18 23:32:35',259,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-02-18 21:00:54',269,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-02-18 12:00:54',279,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-02-23 23:00:12',289,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-02-23 23:00:54',299,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-02-23 12:00:34',349,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-02-25 22:00:44',319,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-02-25 22:00:55',329,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-02-25 22:00:22',339,999);
				
				
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-03-01 14:00:22',740,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-03-01 22:00:25',750,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-03-01 23:12:30',660,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-03-03 12:32:35',170,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-03-05 20:12:38',180,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-03-13 20:21:42',390,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-03-13 19:18:46',240,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-03-13 18:44:50',910,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-03-13 18:33:53',220,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-03-13 22:22:56',130,999);
				

insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-03-25 12:02:22',565,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-03-25 12:20:25',210,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-03-26 21:12:30',250,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-03-26 21:40:35',290,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-03-26 23:20:38',180,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-03-26 12:05:42',490,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-03-27 23:03:46',390,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-03-27 12:07:50',380,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-03-27 05:01:53',330,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-03-27 03:06:56',230,999);

insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-03-28 01:00:22',240,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-03-28 01:00:25',250,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-03-28 01:00:30',260,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-03-28 01:00:35',270,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-03-28 01:00:38',280,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-03-28 01:00:42',290,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-03-28 01:00:46',340,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-03-28 01:00:50',310,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-03-28 01:00:53',320,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-03-28 01:00:56',330,999);

insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-03-29 17:55:00',240,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-03-29 17:44:00',250,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-03-29 17:35:00',260,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-03-29 17:30:00',270,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-03-29 14:40:00',280,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-03-29 14:00:00',290,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-03-29 13:04:22',340,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-03-29 11:20:00',310,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-03-29 11:00:00',320,999);
insert into icar_inf2_sla_object_trace values (4,40,400,'tempoRisposta','2008-03-30 00:00:00',330,999);










# TEST 5 - Function Divide con operand aventi basicMetric entrambi diversi da null
insert into icar_inf2_service values (5,50,500,'prova5',1,'<?xml version="1.0" encoding="UTF-8"?>
<wsag:GuaranteeTerms xmlns:wsag="http://schemas.ggf.org/graap/2007/03/ws-agreement" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://schemas.ggf.org/graap/2007/03/ws-agreement GuaranteeTerms.xsd">
	<wsag:GuaranteeTerm wsag:Name="Prova5" wsag:Obligated="ServiceProvider">
		<wsag:ServiceLevelObjective>
			<wsag:CustomServiceLevel>
			  <wsag:Threshold wsag:Operator="LessEqual" wsag:Value="3"/>			  
			    <wsag:Function wsag:resultType="double" xsi:type="wsag:Divide" > 
   			        <wsag:Operand>
   			            <wsag:BasicMetric wsag:Unit="milliseconds">tempoRisposta</wsag:BasicMetric>
			        </wsag:Operand>
			        <wsag:Operand>
			            <wsag:BasicMetric wsag:Unit="milliseconds">tempoChiamata</wsag:BasicMetric>
			        </wsag:Operand>
			  </wsag:Function>
			</wsag:CustomServiceLevel>
		</wsag:ServiceLevelObjective>
		<wsag:BusinessValueList/>
	</wsag:GuaranteeTerm>
</wsag:GuaranteeTerms>',0,1);

insert into icar_inf2_sla_object_trace values (5,50,500,'tempoRisposta','2008-03-28 01:00:42',290,999);
insert into icar_inf2_sla_object_trace values (5,50,500,'tempoRisposta','2008-03-28 01:00:46',340,999);
insert into icar_inf2_sla_object_trace values (5,50,500,'tempoRisposta','2008-03-28 01:00:50',310,999);
insert into icar_inf2_sla_object_trace values (5,50,500,'tempoRisposta','2008-03-28 01:00:53',320,999);
insert into icar_inf2_sla_object_trace values (5,50,500,'tempoRisposta','2008-03-28 01:00:56',330,999);
insert into icar_inf2_sla_object_trace values (5,50,500,'tempoRisposta','2008-03-28 01:00:22',240,999);
insert into icar_inf2_sla_object_trace values (5,50,500,'tempoRisposta','2008-03-28 01:00:25',250,999);
insert into icar_inf2_sla_object_trace values (5,50,500,'tempoRisposta','2008-03-28 01:00:30',260,999);
insert into icar_inf2_sla_object_trace values (5,50,500,'tempoRisposta','2008-03-28 01:00:35',270,999);
insert into icar_inf2_sla_object_trace values (5,50,500,'tempoRisposta','2008-03-28 01:00:38',280,999);
insert into icar_inf2_sla_object_trace values (5,50,500,'tempoChiamata','2008-03-28 01:00:42',290,999);
insert into icar_inf2_sla_object_trace values (5,50,500,'tempoChiamata','2008-03-28 01:00:46',340,999);
insert into icar_inf2_sla_object_trace values (5,50,500,'tempoChiamata','2008-03-28 01:00:50',310,999);
insert into icar_inf2_sla_object_trace values (5,50,500,'tempoChiamata','2008-03-28 01:00:53',320,999);
insert into icar_inf2_sla_object_trace values (5,50,500,'tempoChiamata','2008-03-28 01:00:56',330,999);
insert into icar_inf2_sla_object_trace values (5,50,500,'tempoChiamata','2008-03-28 01:00:22',240,999);
insert into icar_inf2_sla_object_trace values (5,50,500,'tempoChiamata','2008-03-28 01:00:25',250,999);
insert into icar_inf2_sla_object_trace values (5,50,500,'tempoChiamata','2008-03-28 01:00:30',260,999);
insert into icar_inf2_sla_object_trace values (5,50,500,'tempoChiamata','2008-03-28 01:00:35',270,999);
insert into icar_inf2_sla_object_trace values (5,50,500,'tempoChiamata','2008-03-28 01:00:38',280,999);




# TEST 6 - Function Divide con operando1 avente Function, e operando2 avente basicMetric diverso da null - OK
insert into icar_inf2_service values (6,60,600,'prova6',1,'<?xml version="1.0" encoding="UTF-8"?>
<wsag:GuaranteeTerms xmlns:wsag="http://schemas.ggf.org/graap/2007/03/ws-agreement" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://schemas.ggf.org/graap/2007/03/ws-agreement GuaranteeTerms.xsd">
	<wsag:GuaranteeTerm wsag:Name="Prova6" wsag:Obligated="ServiceProvider">
		<wsag:ServiceLevelObjective>
			<wsag:CustomServiceLevel>
			  <wsag:Threshold wsag:Operator="LessEqual" wsag:Value="3"/>			  
			    <wsag:Function wsag:resultType="double" xsi:type="wsag:Divide" > 
   			        <wsag:Operand>
			          <wsag:Function wsag:resultType="double" xsi:type="wsag:Mean"> 
			            <wsag:BasicMetric wsag:Unit="seconds">tempoRisposta</wsag:BasicMetric>
			            <wsag:Window>
			              <wsag:Times>5</wsag:Times>
			            </wsag:Window>
			          </wsag:Function>   			            
			        </wsag:Operand>
			        <wsag:Operand>
			            <wsag:BasicMetric wsag:Unit="milliseconds">tempoRisposta</wsag:BasicMetric>
			        </wsag:Operand>
			  </wsag:Function>
			</wsag:CustomServiceLevel>
		</wsag:ServiceLevelObjective>
		<wsag:BusinessValueList/>
	</wsag:GuaranteeTerm>
</wsag:GuaranteeTerms>',0,1);

insert into icar_inf2_sla_object_trace values (6,60,600,'tempoRisposta','2008-03-28 01:00:22',240,023);
insert into icar_inf2_sla_object_trace values (6,60,600,'tempoRisposta','2008-03-28 01:00:25',250,543);
insert into icar_inf2_sla_object_trace values (6,60,600,'tempoRisposta','2008-03-28 01:00:30',260,023);
insert into icar_inf2_sla_object_trace values (6,60,600,'tempoRisposta','2008-03-28 01:00:35',270,678);
insert into icar_inf2_sla_object_trace values (6,60,600,'tempoRisposta','2008-03-28 01:00:38',280,983);
insert into icar_inf2_sla_object_trace values (6,60,600,'tempoRisposta','2008-03-28 01:00:42',290,012);
insert into icar_inf2_sla_object_trace values (6,60,600,'tempoRisposta','2008-03-28 01:00:46',340,233);
insert into icar_inf2_sla_object_trace values (6,60,600,'tempoRisposta','2008-03-28 01:00:50',310,654);
insert into icar_inf2_sla_object_trace values (6,60,600,'tempoRisposta','2008-03-28 01:00:53',320,124);
insert into icar_inf2_sla_object_trace values (6,60,600,'tempoRisposta','2008-03-28 01:00:56',330,876);


# TEST 7 : Trova il Massimo tra più divisioni
insert into icar_inf2_service values (7,70,700,'prova7',1,'<?xml version="1.0" encoding="UTF-8"?>
<wsag:GuaranteeTerms xmlns:wsag="http://schemas.ggf.org/graap/2007/03/ws-agreement" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://schemas.ggf.org/graap/2007/03/ws-agreement GuaranteeTerms.xsd">
	<wsag:GuaranteeTerm wsag:Name="Prova7" wsag:Obligated="ServiceProvider">
		<wsag:ServiceLevelObjective>
			<wsag:CustomServiceLevel>
		 		<wsag:Threshold wsag:Operator="Greater" wsag:Value="50"/> <!-- ci si obbliga ad avere pi di 50 rec al secondo -->	
		 		<!-- media aritmetica tra una serie di letture (lista di q.t di record per unit di tempo) nell arco di 2 sett. -->
		 		<wsag:Function wsag:resultType="double" xsi:type="wsag:Max">
		 			<!-- numero di record diviso tempo di risposta -->
		 			<wsag:Function wsag:resultType="double" xsi:type="wsag:Divide">
		 				<wsag:Operand>
		 					<!-- nome di metrica di risorsa oggetto di tracciatura -->
		 					<wsag:BasicMetric wsag:Unit="seconds">tempoRisposta</wsag:BasicMetric>
		 				</wsag:Operand>
		 				<wsag:Operand>
		 					<!-- nome di metrica di risorsa oggetto di tracciatura -->
		 					<wsag:BasicMetric wsag:Unit="seconds">tempoChiamata</wsag:BasicMetric>
		 				</wsag:Operand>
		 			</wsag:Function>
		 			<wsag:Window>
		 				<wsag:Interval>TwoWeeks</wsag:Interval>
		 			</wsag:Window>
		 		</wsag:Function>
			</wsag:CustomServiceLevel>
		</wsag:ServiceLevelObjective>
		<wsag:BusinessValueList/>
	</wsag:GuaranteeTerm>
</wsag:GuaranteeTerms>',0,1);

insert into icar_inf2_sla_object_trace values (7,70,700,'tempoRisposta','2008-03-20 15:00:22',012,023);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoRisposta','2008-03-21 16:00:25',43,543);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoRisposta','2008-03-22 17:00:30',589,023);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoRisposta','2008-03-24 18:00:35',456,678);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoRisposta','2008-03-24 19:00:38',123,983);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoRisposta','2008-03-25 21:00:42',432,012);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoRisposta','2008-03-25 21:00:46',340,233);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoRisposta','2008-03-25 21:00:50',310,654);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoRisposta','2008-03-25 22:00:53',320,124);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoRisposta','2008-03-27 23:00:56',342,876);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoRisposta','2008-03-27 11:00:42',987,453);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoRisposta','2008-03-27 12:00:46',234,234);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoRisposta','2008-03-27 04:00:50',789,843);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoRisposta','2008-03-28 03:00:53',345,654);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoRisposta','2008-03-28 02:00:56',532,234);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoChiamata','2008-03-22 01:00:22',764,023);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoChiamata','2008-03-23 01:00:25',654,543);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoChiamata','2008-03-23 01:00:30',912,023);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoChiamata','2008-03-24 01:00:35',123,678);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoChiamata','2008-03-24 01:00:38',234,983);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoChiamata','2008-03-24 01:00:42',111,012);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoChiamata','2008-03-25 01:00:46',322,233);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoChiamata','2008-03-25 01:00:50',777,654);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoChiamata','2008-03-25 01:00:53',444,124);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoChiamata','2008-03-27 01:00:56',234,876);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoChiamata','2008-03-27 01:00:42',654,453);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoChiamata','2008-03-27 01:00:46',123,234);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoChiamata','2008-03-27 01:00:50',453,843);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoChiamata','2008-03-28 01:00:53',978,654);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoChiamata','2008-03-28 01:00:56',234,234);

insert into icar_inf2_sla_object_trace values (7,70,700,'tempoChiamata','2008-03-20 15:00:22',240,023);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoChiamata','2008-03-21 16:00:25',250,543);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoChiamata','2008-03-22 17:00:30',260,023);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoChiamata','2008-03-24 18:00:35',270,678);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoChiamata','2008-03-24 19:00:38',280,983);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoChiamata','2008-03-25 21:00:42',290,012);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoChiamata','2008-03-25 21:00:46',340,233);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoChiamata','2008-03-25 21:00:50',310,654);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoChiamata','2008-03-25 22:00:53',320,124);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoChiamata','2008-03-27 23:00:56',342,876);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoChiamata','2008-03-27 11:00:42',765,453);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoChiamata','2008-03-27 12:00:46',274,234);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoChiamata','2008-03-27 04:00:50',744,843);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoChiamata','2008-03-28 03:00:53',233,654);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoChiamata','2008-03-28 02:00:56',543,234);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoRisposta','2008-03-22 01:00:22',432,023);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoRisposta','2008-03-23 01:00:25',123,543);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoRisposta','2008-03-23 01:00:30',123,023);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoRisposta','2008-03-24 01:00:35',543,678);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoRisposta','2008-03-24 01:00:38',654,983);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoRisposta','2008-03-24 01:00:42',870,012);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoRisposta','2008-03-25 01:00:46',344,233);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoRisposta','2008-03-25 01:00:50',332,654);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoRisposta','2008-03-25 01:00:53',120,124);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoRisposta','2008-03-27 01:00:56',324,876);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoRisposta','2008-03-27 01:00:42',654,453);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoRisposta','2008-03-27 01:00:46',123,234);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoRisposta','2008-03-27 01:00:50',453,843);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoRisposta','2008-03-28 01:00:53',978,654);
insert into icar_inf2_sla_object_trace values (7,70,700,'tempoRisposta','2008-03-28 01:00:56',345,234);




# TEST 8 : una media tra pi divisioni - OK
insert into icar_inf2_service values (8,80,800,'prova8',1,'<?xml version="1.0" encoding="UTF-8"?>
<wsag:GuaranteeTerms xmlns:wsag="http://schemas.ggf.org/graap/2007/03/ws-agreement" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://schemas.ggf.org/graap/2007/03/ws-agreement GuaranteeTerms.xsd">
	<wsag:GuaranteeTerm wsag:Name="VelocitaMediaDatiRestituiti2W" wsag:Obligated="ServiceProvider">
		<wsag:ServiceLevelObjective>
			<wsag:CustomServiceLevel>
		 		<wsag:Threshold wsag:Operator="Greater" wsag:Value="50"/> <!-- ci si obbliga ad avere pi di 50 rec al secondo -->	
		 		<!-- media aritmetica tra una serie di letture (lista di q.t di record per unit di tempo) nell arco di 2 sett. -->
		 		<wsag:Function wsag:resultType="double" xsi:type="wsag:Mean">
		 			<!-- numero di record diviso tempo di risposta -->
		 			<wsag:Function wsag:resultType="double" xsi:type="wsag:Divide">
		 				<wsag:Operand>
		 					<!-- nome di metrica di risorsa oggetto di tracciatura -->
		 					<wsag:BasicMetric wsag:Unit="seconds">tempoRisposta</wsag:BasicMetric>
		 				</wsag:Operand>
		 				<wsag:Operand>
		 					<!-- nome di metrica di risorsa oggetto di tracciatura -->
		 					<wsag:BasicMetric wsag:Unit="seconds">tempoChiamata</wsag:BasicMetric>
		 				</wsag:Operand>
		 			</wsag:Function>
		 			<wsag:Window>
		 				<wsag:Interval>TwoWeeks</wsag:Interval>
		 			</wsag:Window>
		 		</wsag:Function>
			</wsag:CustomServiceLevel>
		</wsag:ServiceLevelObjective>
		<wsag:BusinessValueList/>
	</wsag:GuaranteeTerm>
</wsag:GuaranteeTerms>',0,1);

insert into icar_inf2_sla_object_trace values (8,80,800,'tempoChiamata','2008-03-28 12:00:22',240,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoChiamata','2008-03-25 13:00:25',250,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoChiamata','2008-03-22 12:00:30',260,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoChiamata','2008-03-19 14:00:35',270,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoChiamata','2008-03-23 15:00:38',280,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoChiamata','2008-03-22 16:00:42',290,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoChiamata','2008-03-20 22:00:46',340,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoChiamata','2008-03-20 21:00:50',310,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoChiamata','2008-03-20 22:00:53',320,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoChiamata','2008-03-27 23:00:56',342,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoChiamata','2008-03-27 16:00:42',765,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoChiamata','2008-03-27 16:00:46',274,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoChiamata','2008-03-27 15:00:50',744,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoChiamata','2008-03-28 10:00:53',233,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoChiamata','2008-03-28 09:00:56',543,999);

insert into icar_inf2_sla_object_trace values (8,80,800,'tempoRisposta','2008-03-28 12:00:22',424,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoRisposta','2008-03-25 13:00:25',645,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoRisposta','2008-03-22 12:00:30',765,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoRisposta','2008-03-19 14:00:35',323,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoRisposta','2008-03-23 15:00:38',123,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoRisposta','2008-03-22 16:00:42',765,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoRisposta','2008-03-20 22:00:46',342,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoRisposta','2008-03-20 21:00:50',543,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoRisposta','2008-03-20 22:00:53',756,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoRisposta','2008-03-27 23:00:56',453,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoRisposta','2008-03-27 16:00:42',234,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoRisposta','2008-03-27 16:00:46',274,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoRisposta','2008-03-27 15:00:50',744,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoRisposta','2008-03-28 10:00:53',964,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoRisposta','2008-03-28 09:00:56',543,999);


insert into icar_inf2_sla_object_trace values (8,80,800,'tempoRisposta','2008-03-28 01:00:22',432,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoRisposta','2008-03-25 01:00:25',423,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoRisposta','2008-03-22 01:00:30',321,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoRisposta','2008-03-19 01:00:35',544,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoRisposta','2008-03-23 01:00:38',444,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoRisposta','2008-03-22 01:00:42',222,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoRisposta','2008-03-20 01:00:46',111,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoRisposta','2008-03-20 01:00:50',444,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoRisposta','2008-03-20 01:00:53',555,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoRisposta','2008-03-27 01:00:56',666,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoRisposta','2008-03-27 01:00:42',888,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoRisposta','2008-03-27 01:00:46',666,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoRisposta','2008-03-27 01:00:50',444,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoRisposta','2008-03-28 01:00:53',222,999);

insert into icar_inf2_sla_object_trace values (8,80,800,'tempoChiamata','2008-03-28 01:00:22',432,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoChiamata','2008-03-25 01:00:25',231,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoChiamata','2008-03-22 01:00:30',123,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoChiamata','2008-03-19 01:00:35',543,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoChiamata','2008-03-23 01:00:38',321,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoChiamata','2008-03-22 01:00:42',870,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoChiamata','2008-03-20 01:00:46',344,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoChiamata','2008-03-20 01:00:50',424,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoChiamata','2008-03-20 01:00:53',120,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoChiamata','2008-03-27 01:00:56',544,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoChiamata','2008-03-27 01:00:42',654,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoChiamata','2008-03-27 01:00:46',213,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoChiamata','2008-03-27 01:00:50',453,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoChiamata','2008-03-28 01:00:53',432,999);
insert into icar_inf2_sla_object_trace values (8,80,800,'tempoChiamata','2008-03-28 01:00:56',312,999);





# Prova 9 - Funzionamento HitsFunction query con window interval
insert into icar_inf2_service values (9,90,900,'prova9',1,'<?xml version="1.0" encoding="UTF-8"?>
<wsag:GuaranteeTerms xmlns:wsag="http://schemas.ggf.org/graap/2007/03/ws-agreement" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://schemas.ggf.org/graap/2007/03/ws-agreement GuaranteeTerms.xsd">
	<wsag:GuaranteeTerm wsag:Name="Prova9" wsag:Obligated="ServiceProvider">
		<wsag:ServiceLevelObjective>
			<wsag:CustomServiceLevel>
				<wsag:Threshold wsag:Operator="LessEqual" wsag:Value="330"/>
				<wsag:Function wsag:resultType="double" xsi:type="wsag:Hits">
					<wsag:BasicMetric wsag:Unit="milliseconds">tempoRisposta</wsag:BasicMetric>
					<wsag:Window>
						<wsag:Interval>Month</wsag:Interval>
					</wsag:Window>
				</wsag:Function>
			</wsag:CustomServiceLevel>
		</wsag:ServiceLevelObjective>
		<wsag:BusinessValueList/>
	</wsag:GuaranteeTerm>
</wsag:GuaranteeTerms>',0,1);

insert into icar_inf2_sla_object_trace values (9,90,900,'tempoRisposta','2008-03-28 01:00:22',240,023);
insert into icar_inf2_sla_object_trace values (9,90,900,'tempoRisposta','2008-03-25 01:00:25',250,543);
insert into icar_inf2_sla_object_trace values (9,90,900,'tempoRisposta','2008-03-22 01:00:30',260,023);
insert into icar_inf2_sla_object_trace values (9,90,900,'tempoRisposta','2008-03-19 01:00:35',270,678);
insert into icar_inf2_sla_object_trace values (9,90,900,'tempoRisposta','2008-03-23 01:00:38',280,983);
insert into icar_inf2_sla_object_trace values (9,90,900,'tempoRisposta','2008-03-22 01:00:42',290,012);
insert into icar_inf2_sla_object_trace values (9,90,900,'tempoRisposta','2008-03-20 01:00:46',340,233);
insert into icar_inf2_sla_object_trace values (9,90,900,'tempoRisposta','2008-03-20 01:00:50',310,654);
insert into icar_inf2_sla_object_trace values (9,90,900,'tempoRisposta','2008-03-20 01:00:53',320,124);
insert into icar_inf2_sla_object_trace values (9,90,900,'tempoRisposta','2008-03-27 01:00:56',342,876);
insert into icar_inf2_sla_object_trace values (9,90,900,'tempoRisposta','2008-03-27 01:00:42',765,453);
insert into icar_inf2_sla_object_trace values (9,90,900,'tempoRisposta','2008-03-27 01:00:46',274,234);
insert into icar_inf2_sla_object_trace values (9,90,900,'tempoRisposta','2008-03-27 01:00:50',744,843);
insert into icar_inf2_sla_object_trace values (9,90,900,'tempoRisposta','2008-03-28 01:00:53',233,654);
insert into icar_inf2_sla_object_trace values (9,90,900,'tempoRisposta','2008-03-28 01:00:56',543,234);




# Test 10 - Funzionamento HitsFunction query con window times
insert into icar_inf2_service values (10,100,1000,'prova10',1,'<?xml version="1.0" encoding="UTF-8"?>
<wsag:GuaranteeTerms xmlns:wsag="http://schemas.ggf.org/graap/2007/03/ws-agreement" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://schemas.ggf.org/graap/2007/03/ws-agreement GuaranteeTerms.xsd">
	<wsag:GuaranteeTerm wsag:Name="Prova10" wsag:Obligated="ServiceProvider">
		<wsag:ServiceLevelObjective>
			<wsag:CustomServiceLevel>
				<wsag:Threshold wsag:Operator="LessEqual" wsag:Value="330"/>
				<wsag:Function wsag:resultType="double" xsi:type="wsag:Hits">
					<wsag:BasicMetric wsag:Unit="milliseconds">tempoRisposta</wsag:BasicMetric>
			            	<wsag:Window>
			            	    <wsag:Times>10</wsag:Times>
			           	</wsag:Window>
				</wsag:Function>
			</wsag:CustomServiceLevel>
		</wsag:ServiceLevelObjective>
		<wsag:BusinessValueList/>
	</wsag:GuaranteeTerm>
</wsag:GuaranteeTerms>',0,1);

insert into icar_inf2_sla_object_trace values (10,100,1000,'tempoRisposta','2008-03-28 01:00:22',240,023);
insert into icar_inf2_sla_object_trace values (10,100,1000,'tempoRisposta','2008-03-25 01:00:25',250,543);
insert into icar_inf2_sla_object_trace values (10,100,1000,'tempoRisposta','2008-03-22 01:00:30',260,023);
insert into icar_inf2_sla_object_trace values (10,100,1000,'tempoRisposta','2008-03-19 01:00:35',270,678);
insert into icar_inf2_sla_object_trace values (10,100,1000,'tempoRisposta','2008-03-23 01:00:38',280,983);
insert into icar_inf2_sla_object_trace values (10,100,1000,'tempoRisposta','2008-03-22 01:00:42',290,012);
insert into icar_inf2_sla_object_trace values (10,100,1000,'tempoRisposta','2008-03-20 01:00:46',340,233);
insert into icar_inf2_sla_object_trace values (10,100,1000,'tempoRisposta','2008-03-20 01:00:50',310,654);
insert into icar_inf2_sla_object_trace values (10,100,1000,'tempoRisposta','2008-03-20 01:00:53',320,124);
insert into icar_inf2_sla_object_trace values (10,100,1000,'tempoRisposta','2008-03-27 01:00:56',342,876);
insert into icar_inf2_sla_object_trace values (10,100,1000,'tempoRisposta','2008-03-27 01:00:42',765,453);
insert into icar_inf2_sla_object_trace values (10,100,1000,'tempoRisposta','2008-03-27 01:00:46',274,234);
insert into icar_inf2_sla_object_trace values (10,100,1000,'tempoRisposta','2008-03-27 01:00:50',744,843);
insert into icar_inf2_sla_object_trace values (10,100,1000,'tempoRisposta','2008-03-28 01:00:53',233,654);
insert into icar_inf2_sla_object_trace values (10,100,1000,'tempoRisposta','2008-03-28 01:00:56',543,234);








# TEST 11 - Funzionamento HitsFunction metodo getResultOperation
insert into icar_inf2_service values (11,110,1100,'prova11',1,'<?xml version="1.0" encoding="UTF-8"?>
<wsag:GuaranteeTerms xmlns:wsag="http://schemas.ggf.org/graap/2007/03/ws-agreement" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://schemas.ggf.org/graap/2007/03/ws-agreement GuaranteeTerms.xsd">
	<wsag:GuaranteeTerm wsag:Name="Prova11" wsag:Obligated="ServiceProvider">
		<wsag:ServiceLevelObjective>
			<wsag:CustomServiceLevel>
				<wsag:Threshold wsag:Operator="Less" wsag:Value="0.01"/>
				<wsag:Function wsag:resultType="double" xsi:type="wsag:Divide">
		 			<wsag:Operand>
						<wsag:Function wsag:resultType="double" xsi:type="wsag:Hits">
							<wsag:BasicMetric wsag:Unit="error">erroreInterazione</wsag:BasicMetric>
							<wsag:Threshold wsag:Operator="Equal" wsag:Value="1"/>
			            			<wsag:Window>
			            		    		<wsag:Interval>ThreeMonths</wsag:Interval>
			           			</wsag:Window>
						</wsag:Function>		 			
		 			</wsag:Operand>
		 			<wsag:Operand>
						<wsag:Function wsag:resultType="double" xsi:type="wsag:Hits">
							<wsag:BasicMetric wsag:Unit="error">erroreInterazione</wsag:BasicMetric>
			            			<wsag:Window>
			            		    		<wsag:Interval>ThreeMonths</wsag:Interval>
			           			</wsag:Window>
						</wsag:Function>		 			
		 			</wsag:Operand>
				</wsag:Function>
			</wsag:CustomServiceLevel>
		</wsag:ServiceLevelObjective>
		<wsag:BusinessValueList/>
	</wsag:GuaranteeTerm>
</wsag:GuaranteeTerms>',0,1);

insert into icar_inf2_sla_object_trace values (11,110,1100,'erroreInterazione','2008-03-14 01:00:22',1,023);
insert into icar_inf2_sla_object_trace values (11,110,1100,'erroreInterazione','2008-01-14 01:00:25',1,543);
insert into icar_inf2_sla_object_trace values (11,110,1100,'erroreInterazione','2008-01-18 01:00:30',1,023);
insert into icar_inf2_sla_object_trace values (11,110,1100,'erroreInterazione','2008-01-18 01:00:38',0,983);
insert into icar_inf2_sla_object_trace values (11,110,1100,'erroreInterazione','2008-01-18 01:00:42',0,012);
insert into icar_inf2_sla_object_trace values (11,110,1100,'erroreInterazione','2008-01-25 01:00:46',1,233);
insert into icar_inf2_sla_object_trace values (11,110,1100,'erroreInterazione','2008-01-25 01:00:50',1,654);
insert into icar_inf2_sla_object_trace values (11,110,1100,'erroreInterazione','2008-01-25 01:00:53',1,124);
insert into icar_inf2_sla_object_trace values (11,110,1100,'erroreInterazione','2008-01-27 01:00:56',1,876);
insert into icar_inf2_sla_object_trace values (11,110,1100,'erroreInterazione','2008-01-27 01:00:42',1,453);
insert into icar_inf2_sla_object_trace values (11,110,1100,'erroreInterazione','2008-01-27 01:00:46',1,234);
insert into icar_inf2_sla_object_trace values (11,110,1100,'erroreInterazione','2008-03-15 01:00:50',1,843);
insert into icar_inf2_sla_object_trace values (11,110,1100,'erroreInterazione','2008-03-17 01:00:53',1,654);
insert into icar_inf2_sla_object_trace values (11,110,1100,'erroreInterazione','2008-03-17 01:00:56',1,234);
insert into icar_inf2_sla_object_trace values (11,110,1100,'erroreInterazione','2008-02-17 01:00:22',1,023);
insert into icar_inf2_sla_object_trace values (11,110,1100,'erroreInterazione','2008-02-18 01:00:25',1,543);
insert into icar_inf2_sla_object_trace values (11,110,1100,'erroreInterazione','2008-02-28 01:00:30',1,023);
insert into icar_inf2_sla_object_trace values (11,110,1100,'erroreInterazione','2008-02-19 01:00:35',1,678);
insert into icar_inf2_sla_object_trace values (11,110,1100,'erroreInterazione','2008-02-25 01:00:38',1,983);
insert into icar_inf2_sla_object_trace values (11,110,1100,'erroreInterazione','2008-02-25 01:00:42',1,012);
insert into icar_inf2_sla_object_trace values (11,110,1100,'erroreInterazione','2008-02-26 01:00:46',1,233);
insert into icar_inf2_sla_object_trace values (11,110,1100,'erroreInterazione','2008-02-26 01:00:50',1,654);
insert into icar_inf2_sla_object_trace values (11,110,1100,'erroreInterazione','2008-02-26 01:00:53',1,124);
insert into icar_inf2_sla_object_trace values (11,110,1100,'erroreInterazione','2008-03-27 01:00:56',1,876);
insert into icar_inf2_sla_object_trace values (11,110,1100,'erroreInterazione','2008-03-27 01:00:42',1,453);
insert into icar_inf2_sla_object_trace values (11,110,1100,'erroreInterazione','2008-03-27 01:00:46',1,234);
insert into icar_inf2_sla_object_trace values (11,110,1100,'erroreInterazione','2008-03-27 01:00:50',1,843);
insert into icar_inf2_sla_object_trace values (11,110,1100,'erroreInterazione','2008-03-28 01:00:53',1,654);
insert into icar_inf2_sla_object_trace values (11,110,1100,'erroreInterazione','2008-03-28 01:00:56',0,234);







# TEST 12 : metodo getResultOperationComplex della classe MAXFunction
insert into icar_inf2_service values (12,120,1200,'prova12',1,'<?xml version="1.0" encoding="UTF-8"?>
<wsag:GuaranteeTerms xmlns:wsag="http://schemas.ggf.org/graap/2007/03/ws-agreement" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://schemas.ggf.org/graap/2007/03/ws-agreement GuaranteeTerms.xsd">
	<wsag:GuaranteeTerm wsag:Name="Prova12" wsag:Obligated="ServiceProvider">
		<wsag:ServiceLevelObjective>
			<wsag:CustomServiceLevel>
		 		<wsag:Threshold wsag:Operator="Greater" wsag:Value="50"/> <!-- ci si obbliga ad avere pi di 50 rec al secondo -->	
		 		<!-- media aritmetica tra una serie di letture (lista di q.t di record per unit di tempo) nell arco di 2 sett. -->
		 		<wsag:Function wsag:resultType="double" xsi:type="wsag:Max">
		 			<!-- numero di record diviso tempo di risposta -->
		 			<wsag:Function wsag:resultType="double" xsi:type="wsag:Divide">
		 				<wsag:Operand>
		 					<!-- nome di metrica di risorsa oggetto di tracciatura -->
		 					<wsag:BasicMetric wsag:Unit="seconds">tempoRisposta</wsag:BasicMetric>
		 				</wsag:Operand>
		 				<wsag:Operand>
		 					<!-- nome di metrica di risorsa oggetto di tracciatura -->
		 					<wsag:BasicMetric wsag:Unit="seconds">tempoChiamata</wsag:BasicMetric>
		 				</wsag:Operand>
		 			</wsag:Function>
		 			<wsag:Window>
		 				<wsag:Interval>TwoWeeks</wsag:Interval>
		 			</wsag:Window>
		 		</wsag:Function>
			</wsag:CustomServiceLevel>
		</wsag:ServiceLevelObjective>
		<wsag:BusinessValueList/>
	</wsag:GuaranteeTerm>
</wsag:GuaranteeTerms>',0,1);

insert into icar_inf2_sla_object_trace values (12,120,1200,'tempoRisposta','2008-03-28 01:00:22',240,023);
insert into icar_inf2_sla_object_trace values (12,120,1200,'tempoRisposta','2008-03-25 01:00:25',250,543);
insert into icar_inf2_sla_object_trace values (12,120,1200,'tempoRisposta','2008-03-22 01:00:30',260,023);
insert into icar_inf2_sla_object_trace values (12,120,1200,'tempoRisposta','2008-03-19 01:00:35',270,678);
insert into icar_inf2_sla_object_trace values (12,120,1200,'tempoRisposta','2008-03-23 01:00:38',280,983);
insert into icar_inf2_sla_object_trace values (12,120,1200,'tempoRisposta','2008-03-22 01:00:42',290,012);
insert into icar_inf2_sla_object_trace values (12,120,1200,'tempoRisposta','2008-03-20 01:00:46',340,233);
insert into icar_inf2_sla_object_trace values (12,120,1200,'tempoRisposta','2008-03-20 01:00:50',310,654);
insert into icar_inf2_sla_object_trace values (12,120,1200,'tempoRisposta','2008-03-20 01:00:53',320,124);
insert into icar_inf2_sla_object_trace values (12,120,1200,'tempoRisposta','2008-03-27 01:00:56',342,876);
insert into icar_inf2_sla_object_trace values (12,120,1200,'tempoRisposta','2008-03-27 01:00:42',765,453);
insert into icar_inf2_sla_object_trace values (12,120,1200,'tempoRisposta','2008-03-27 01:00:46',274,234);
insert into icar_inf2_sla_object_trace values (12,120,1200,'tempoRisposta','2008-03-27 01:00:50',744,843);
insert into icar_inf2_sla_object_trace values (12,120,1200,'tempoRisposta','2008-03-28 01:00:53',233,654);
insert into icar_inf2_sla_object_trace values (12,120,1200,'tempoRisposta','2008-03-28 01:00:56',543,234);

insert into icar_inf2_sla_object_trace values (12,120,1200,'tempoChiamata','2008-03-28 01:00:22',432,023);
insert into icar_inf2_sla_object_trace values (12,120,1200,'tempoChiamata','2008-03-25 01:00:25',123,543);
insert into icar_inf2_sla_object_trace values (12,120,1200,'tempoChiamata','2008-03-22 01:00:30',123,023);
insert into icar_inf2_sla_object_trace values (12,120,1200,'tempoChiamata','2008-03-19 01:00:35',543,678);
insert into icar_inf2_sla_object_trace values (12,120,1200,'tempoChiamata','2008-03-23 01:00:38',654,983);
insert into icar_inf2_sla_object_trace values (12,120,1200,'tempoChiamata','2008-03-22 01:00:42',870,012);
insert into icar_inf2_sla_object_trace values (12,120,1200,'tempoChiamata','2008-03-20 01:00:46',344,233);
insert into icar_inf2_sla_object_trace values (12,120,1200,'tempoChiamata','2008-03-20 01:00:50',332,654);
insert into icar_inf2_sla_object_trace values (12,120,1200,'tempoChiamata','2008-03-20 01:00:53',120,124);
insert into icar_inf2_sla_object_trace values (12,120,1200,'tempoChiamata','2008-03-27 01:00:56',324,876);
insert into icar_inf2_sla_object_trace values (12,120,1200,'tempoChiamata','2008-03-27 01:00:42',654,453);
insert into icar_inf2_sla_object_trace values (12,120,1200,'tempoChiamata','2008-03-27 01:00:46',123,234);
insert into icar_inf2_sla_object_trace values (12,120,1200,'tempoChiamata','2008-03-27 01:00:50',453,843);
insert into icar_inf2_sla_object_trace values (12,120,1200,'tempoChiamata','2008-03-28 01:00:53',978,654);
insert into icar_inf2_sla_object_trace values (12,120,1200,'tempoChiamata','2008-03-28 01:00:56',345,234);




# TEST 13

insert into icar_inf2_service values (13,130,1300,'prova13',1,'<?xml version="1.0" encoding="UTF-8"?>
<wsag:GuaranteeTerms xmlns:wsag="http://schemas.ggf.org/graap/2007/03/ws-agreement" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://schemas.ggf.org/graap/2007/03/ws-agreement GuaranteeTerms.xsd">
        <wsag:GuaranteeTerm wsag:Name="Prova13" wsag:Obligated="ServiceProvider">
                <wsag:ServiceLevelObjective>
                        <wsag:CustomServiceLevel>
                                <wsag:Threshold wsag:Operator="Less" wsag:Value="40"/>
                                <wsag:Function wsag:resultType="double" xsi:type="wsag:Divide">
                                         <wsag:Operand>
                                                <wsag:Function wsag:resultType="double" xsi:type="wsag:Max">
                                                        <wsag:BasicMetric wsag:Unit="seconds">tempoRisposta</wsag:BasicMetric>
                                                            <wsag:Window>
                                                                        <wsag:Interval>Month</wsag:Interval>
                                                           </wsag:Window>
                                                </wsag:Function>                                         
                                         </wsag:Operand>
                                         <wsag:Operand>
                                                <wsag:Function wsag:resultType="double" xsi:type="wsag:Max">
                                                        <wsag:BasicMetric wsag:Unit="seconds">tempoRisposta</wsag:BasicMetric>
                                                            <wsag:Window>
                                                                        <wsag:Interval>TwoWeeks</wsag:Interval>
                                                           </wsag:Window>
                                                </wsag:Function>                                         
                                         </wsag:Operand>
                                            <wsag:Window>
                                                        <wsag:Interval>ThreeMonths</wsag:Interval>
                                           </wsag:Window>
                                </wsag:Function>
                        </wsag:CustomServiceLevel>
                </wsag:ServiceLevelObjective>
                <wsag:BusinessValueList/>
        </wsag:GuaranteeTerm>
</wsag:GuaranteeTerms>',0,1);


insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-01-12 01:44:22',290,999);
insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-01-14 01:44:25',290,999);
insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-01-18 01:44:30',960,999);
insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-01-18 01:44:35',290,999);
insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-01-20 01:55:38',289,999);
insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-01-20 01:55:42',299,999);
insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-01-22 01:55:46',390,999);
insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-01-22 01:33:50',390,999);
insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-01-22 01:33:53',390,999);
insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-01-22 01:33:56',390,999);				
				
insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-02-08 23:23:22',249,999);
insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-02-18 23:32:35',259,999);
insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-02-18 21:00:54',269,999);
insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-02-18 12:00:54',279,999);
insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-02-23 23:00:12',289,999);
insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-02-23 23:00:54',299,999);
insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-02-23 12:00:34',349,999);
insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-02-25 22:00:44',319,999);
insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-02-25 22:00:55',329,999);
insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-02-25 22:00:22',339,999);
				
				
insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-03-01 14:00:22',740,999);
insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-03-01 22:00:25',750,999);
insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-03-01 23:12:30',660,999);
insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-03-03 12:32:35',170,999);
insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-03-05 20:12:38',180,999);
insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-03-13 20:21:42',390,999);
insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-03-13 19:18:46',240,999);
insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-03-13 18:44:50',910,999);
insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-03-13 18:33:53',220,999);
insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-03-13 22:22:56',130,999);
				

insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-03-25 12:02:22',565,999);
insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-03-25 12:20:25',210,999);
insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-03-26 21:12:30',250,999);
insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-03-26 21:40:35',290,999);
insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-03-26 23:20:38',180,999);
insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-03-26 12:05:42',490,999);
insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-03-27 23:03:46',390,999);
insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-03-27 12:07:50',380,999);
insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-03-27 05:01:53',330,999);
insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-03-27 03:06:56',230,999);

insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-03-28 01:00:22',240,999);
insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-03-28 01:00:25',250,999);
insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-03-28 01:00:30',260,999);
insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-03-28 01:00:35',270,999);
insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-03-28 01:00:38',280,999);
insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-03-28 01:00:42',290,999);
insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-03-28 01:00:46',340,999);
insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-03-28 01:00:50',310,999);
insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-03-28 01:00:53',320,999);
insert into icar_inf2_sla_object_trace values (13,130,1300,'tempoRisposta','2008-03-28 01:00:56',330,999);