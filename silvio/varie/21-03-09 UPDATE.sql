create table ParametriSLA (
	id int8 not null,
	indirizzoWS varchar(255) unique,
	primary key (id)
);

ALTER TABLE istanzaoperation ADD COLUMN "parametrisla_id" bigint;

ALTER TABLE istanzaoperation ADD COLUMN "avviorapido" bool;

alter table istanzaoperation 
	add constraint FK1600E3894F85ED1D 
	foreign key (parametrisla_id) 
	references ParametriSLA;
	
UPDATE istanzaoperation
	SET avviorapido=false