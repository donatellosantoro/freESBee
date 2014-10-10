
    create table AccordoDiCollaborazione (
        id int8 not null,
        WSDLErogatore varchar(255),
        WSDLFruitore varchar(255),
        cartellaFiles varchar(255),
        fileTypes varchar(255),
        nome varchar(255) not null unique,
        primary key (id)
    );

    create table Configurazione (
        id int8 not null,
        dirConfig varchar(255),
        porta varchar(255),
        primary key (id)
    );

    create table Dati (
        id int8 not null,
        payloadCostante text,
        datiSQL_id int8,
        datiCostanti_id int8,
        datiInterattivi_id int8,
        primary key (id)
    );

    create table DatiCostanti (
        id int8 not null,
        primary key (id)
    );

    create table DatiInterattivi (
        id int8 not null,
        primary key (id)
    );

    create table DatiSQL (
        id int8 not null,
        select_id int8,
        primary key (id)
    );

    create table DatoPrimitivo (
        id int8 not null,
        nome varchar(255) not null,
        tipo varchar(255),
        valore varchar(255),
        datiInterattivi_id int8,
        datiCostanti_id int8,
        primary key (id)
    );

    create table IstanzaAccordoDiCollaborazione (
        id int8 not null,
        cartellaFiles varchar(255),
        nome varchar(255) not null unique,
        ruolo varchar(255),
        accordo_id int8 not null,
        primary key (id)
    );

    create table IstanzaOperation (
        id int8 not null,
        XSLTCompletiToSoapFruitore varchar(255),
        XSLTDatiToSQLFruitore varchar(255),
        XSLTDatiToSoapErogatore varchar(255),
        XSLTSoapToSQLErogatore varchar(255),
        XSLTSoapToSQLInsertErogatore varchar(255),
        autenticazioneFederata bool not null,
        avvioRapido bool not null,
        elaboraRichiesta bool not null,
        elaboraRisposta bool not null,
        indirizzoRispostaAsincronoErogatore varchar(255),
        nomeApp varchar(255),
        rispostaAutomatica bool not null,
        skinDir varchar(255),
        operation_id int8 not null,
        datiRichiesta_id int8,
        datiRisposta_id int8,
        istanzaPortType_id int8 not null,
        parametriSLA_id int8,
        primary key (id)
    );

    create table IstanzaPortType (
        id int8 not null,
        URLInvio varchar(255),
        tipo varchar(255) not null,
        istanzaAccordo_id int8 not null,
        portType_id int8 not null,
        primary key (id)
    );

    create table Message (
        id int8 not null,
        namespace varchar(255),
        nome varchar(255) not null,
        types varchar(255),
        operation_id int8,
        primary key (id)
    );

    create table Messaggio (
        id int8 not null,
        data timestamp,
        idMessaggio varchar(255),
        idRelatesTo varchar(255),
        indirizzo text,
        istanzaPortType varchar(255),
        messaggio text,
        tipo varchar(255),
        istanzaOperation_id int8 not null,
        messaggioRisposta_id int8,
        primary key (id)
    );

    create table MessaggioSbloccoManuale (
        id int8 not null,
        idMessaggio varchar(255),
        idMessaggioRelatedTo varchar(255),
        indirizzo varchar(255),
        indirizzoRisposta varchar(255),
        istanzaPortType varchar(255),
        messaggio text,
        test bool not null,
        tipoCorrelazione varchar(255),
        istanzaOperation_id int8,
        messaggioRichiesta_id int8,
        primary key (id)
    );

    create table Operation (
        id int8 not null,
        nome varchar(255) not null,
        profiloDiCollaborazione varchar(255) not null,
        messageFault_id int8,
        portType_id int8 not null,
        messageIn_id int8,
        messageOut_id int8,
        primary key (id)
    );

    create table ParametriSLA (
        id int8 not null,
        indirizzoWS text,
        primary key (id)
    );

    create table PortType (
        id int8 not null,
        nome varchar(255) not null,
        ruolo varchar(255) not null,
        accordoDiCollaborazione_id int8 not null,
        primary key (id)
    );

    create table Query (
        id int8 not null,
        indirizzoDB varchar(255) not null,
        nomeDB varchar(255) not null,
        nomeUtente varchar(255) not null,
        password varchar(255) not null,
        query text,
        tipoDB varchar(255) not null,
        primary key (id)
    );

    create table Utente (
        id int8 not null,
        nome varchar(255) not null,
        password varchar(255) not null,
        ruolo varchar(255) not null,
        primary key (id)
    );

    alter table Dati 
        add constraint FK2063D2ACCB79DD 
        foreign key (datiCostanti_id) 
        references DatiCostanti;

    alter table Dati 
        add constraint FK2063D22C13297 
        foreign key (datiSQL_id) 
        references DatiSQL;

    alter table Dati 
        add constraint FK2063D27B131CF7 
        foreign key (datiInterattivi_id) 
        references DatiInterattivi;

    alter table DatiSQL 
        add constraint FKB941749CB919C3 
        foreign key (select_id) 
        references Query;

    alter table DatoPrimitivo 
        add constraint FKB2AB9BF9ACCB79DD 
        foreign key (datiCostanti_id) 
        references DatiCostanti;

    alter table DatoPrimitivo 
        add constraint FKB2AB9BF97B131CF7 
        foreign key (datiInterattivi_id) 
        references DatiInterattivi;

    alter table IstanzaAccordoDiCollaborazione 
        add constraint FK5556161C94A56DA2 
        foreign key (accordo_id) 
        references AccordoDiCollaborazione;

    alter table IstanzaOperation 
        add constraint FK1600E38987B56AD8 
        foreign key (datiRisposta_id) 
        references Dati;

    alter table IstanzaOperation 
        add constraint FK1600E3894F85ED1D 
        foreign key (parametriSLA_id) 
        references ParametriSLA;

    alter table IstanzaOperation 
        add constraint FK1600E38923571799 
        foreign key (datiRichiesta_id) 
        references Dati;

    alter table IstanzaOperation 
        add constraint FK1600E38964E5CD7 
        foreign key (istanzaPortType_id) 
        references IstanzaPortType;

    alter table IstanzaOperation 
        add constraint FK1600E3893DAB397 
        foreign key (operation_id) 
        references Operation;

    alter table IstanzaPortType 
        add constraint FKD65918F93767CEA8 
        foreign key (istanzaAccordo_id) 
        references IstanzaAccordoDiCollaborazione;

    alter table IstanzaPortType 
        add constraint FKD65918F9FD656D1D 
        foreign key (portType_id) 
        references PortType;

    alter table Message 
        add constraint FK9C2397E73DAB397 
        foreign key (operation_id) 
        references Operation;

    alter table Messaggio 
        add constraint FK219D4ECF9AA0E352 
        foreign key (messaggioRisposta_id) 
        references Messaggio;

    alter table Messaggio 
        add constraint FK219D4ECF180FBB1D 
        foreign key (istanzaOperation_id) 
        references IstanzaOperation;

    alter table MessaggioSbloccoManuale 
        add constraint FKD35EA491CC86132D 
        foreign key (messaggioRichiesta_id) 
        references Messaggio;

    alter table MessaggioSbloccoManuale 
        add constraint FKD35EA491180FBB1D 
        foreign key (istanzaOperation_id) 
        references IstanzaOperation;

    alter table Operation 
        add constraint FKDA8CF5476FC83D52 
        foreign key (messageIn_id) 
        references Message;

    alter table Operation 
        add constraint FKDA8CF5472473AFE3 
        foreign key (messageFault_id) 
        references Message;

    alter table Operation 
        add constraint FKDA8CF5478EEDB0F7 
        foreign key (messageOut_id) 
        references Message;

    alter table Operation 
        add constraint FKDA8CF547FD656D1D 
        foreign key (portType_id) 
        references PortType;

    alter table PortType 
        add constraint FK2F44D77B761DA037 
        foreign key (accordoDiCollaborazione_id) 
        references AccordoDiCollaborazione;

    create table hibernate_sequences (
         sequence_name varchar(255),
         sequence_next_hi_value int4 
    ) ;
