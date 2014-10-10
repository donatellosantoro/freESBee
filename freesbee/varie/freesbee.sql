
    create table AccordoServizio (
        id int8 not null,
        descrizione varchar(255),
        nome varchar(255) not null,
        oraRegistrazione timestamp,
        policyXACML text,
        privato boolean not null,
        profiloCollaborazione varchar(255),
        profiloEGov_id int8 not null,
        primary key (id)
    );

    create table Azione (
        id int8 not null,
        descrizione varchar(255),
        nome varchar(255) not null,
        profiloCollaborazione varchar(255),
        accordoServizio_id int8 not null,
        profiloEGov_id int8,
        primary key (id)
    );

    create table Configurazione (
        id int8 not null,
        NICA boolean,
        connettoreRegistroServizi varchar(255),
        indirizzoPortaApplicativa varchar(255) not null,
        indirizzoPortaDelegata varchar(255) not null,
        inviaANICA boolean,
        registroFreesbee boolean not null,
        tempo varchar(255) not null,
        soggettoErogatoreNICA_id int8,
        soggettoErogatoreRegistroServizi_id int8,
        primary key (id)
    );

    create table Eccezione (
        id int8 not null,
        codiceEccezione varchar(255),
        contestoCodifica varchar(255),
        posizione varchar(255),
        rilevanza varchar(255),
        messaggio_id int8,
        primary key (id)
    );

    create table Messaggio (
        id int8 not null,
        azione varchar(255),
        collaborazione varchar(255),
        erogatore varchar(255),
        fruitore varchar(255),
        idMessaggio varchar(255),
        idSil varchar(255),
        oraRegistrazione varchar(255),
        profiloCollaborazione varchar(255),
        riferimentoMessaggio varchar(255),
        scadenza varchar(255),
        servizio varchar(255),
        servizioCorrelato varchar(255),
        silRelatesTo varchar(255),
        tipo varchar(255),
        tipoErogatore varchar(255),
        tipoFruitore varchar(255),
        tipoMessaggio varchar(255),
        tipoProfiloCollaborazione varchar(255),
        tipoServizio varchar(255),
        tipoServizioCorrelato varchar(255),
        primary key (id)
    );

    create table MessaggioDiagnostico (
        id int8 not null,
        gdo timestamp,
        identificativoFunzione varchar(255),
        identificativoPorta varchar(255),
        severita int4 not null,
        testoDiagnostico varchar(255),
        primary key (id)
    );

    create table PortaApplicativa (
        id int8 not null,
        descrizione varchar(255),
        nome varchar(255) not null,
        stringaAzione varchar(255),
        stringaErogatore varchar(255),
        stringaServizio varchar(255),
        stringaTipoErogatore varchar(255),
        stringaTipoServizio varchar(255),
        azione_id int8,
        servizio_id int8,
        servizioApplicativo_id int8,
        primary key (id)
    );

    create table PortaDelegata (
        id int8 not null,
        descrizione varchar(255),
        fruitoreQueryString boolean not null,
        nome varchar(255) not null,
        stringaAzione varchar(255),
        stringaErogatore varchar(255),
        stringaFruitore varchar(255),
        stringaServizio varchar(255),
        stringaTipoErogatore varchar(255),
        stringaTipoFruitore varchar(255),
        stringaTipoServizio varchar(255),
        azione_id int8,
        servizio_id int8,
        soggettoFruitore_id int8,
        primary key (id)
    );

    create table ProfiloEGov (
        id int8 not null,
        confermaRicezione boolean not null,
        consegnaInOrdine boolean not null,
        gestioneDuplicati boolean not null,
        idCollaborazione boolean not null,
        scadenza timestamp,
        primary key (id)
    );

    create table Servizio (
        id int8 not null,
        azioni varchar(255),
        correlato boolean not null,
        nome varchar(255) not null,
        oraRegistrazione timestamp,
        privato boolean not null,
        tipo varchar(20) default 'SPC' not null,
        urlServizio varchar(255),
        accordoServizio_id int8 not null,
        erogatore_id int8 not null,
        primary key (id)
    );

    create table ServizioApplicativo (
        id int8 not null,
        connettore varchar(255),
        descrizione varchar(255),
        nome varchar(255),
        primary key (id)
    );

    create table Servizio_Soggetto (
        listaServiziFruitore_id int8 not null,
        fruitori_id int8 not null
    );

    create table Sincronizzazione (
        id int8 not null,
        freesbee boolean not null,
        indirizzo varchar(255),
        nome varchar(255),
        primary key (id)
    );

    create table Soggetto (
        id int8 not null,
        descrizione varchar(255),
        nome varchar(255) not null,
        oraRegistrazione timestamp,
        portaDominio varchar(255),
        tipo varchar(255) not null,
        primary key (id)
    );

    create table Utente (
        id int8 not null,
        nomeUtente varchar(255) not null,
        password varchar(255) not null,
        ruolo varchar(255) not null,
        primary key (id)
    );

    alter table AccordoServizio 
        add constraint UK_3vj70j5ixu133dq8qpmhvmhky unique (profiloEGov_id);

    alter table AccordoServizio 
        add constraint UK_9jr5fxvnkjuxi68m5na1q89b7 unique (nome);

    alter table AccordoServizio 
        add constraint FK_3vj70j5ixu133dq8qpmhvmhky 
        foreign key (profiloEGov_id) 
        references ProfiloEGov deferrable initially immediate;

    alter table Azione 
        add constraint FK_pc3grxolh1mruqaq2oilx7qcv 
        foreign key (accordoServizio_id) 
        references AccordoServizio deferrable initially immediate;

    alter table Azione 
        add constraint FK_97tnjh1ccnq4t63xdygyqd7o2 
        foreign key (profiloEGov_id) 
        references ProfiloEGov deferrable initially immediate;

    alter table Configurazione 
        add constraint FK_hme4d7ij9p6okrm70kisrldfo 
        foreign key (soggettoErogatoreNICA_id) 
        references Soggetto deferrable initially immediate;

    alter table Configurazione 
        add constraint FK_ckrkv410d80ak2upp28yxyo9p 
        foreign key (soggettoErogatoreRegistroServizi_id) 
        references Soggetto deferrable initially immediate;

    alter table Eccezione 
        add constraint FK_shg8478t0dgxoqokljx9ju8tj 
        foreign key (messaggio_id) 
        references Messaggio deferrable initially immediate;

    alter table Messaggio 
        add constraint UK_dmhqvubmr16tdnxxsrshcy7v3 unique (idMessaggio, tipo);

    alter table PortaApplicativa 
        add constraint UK_hnyboy757axl8r15w7oyogjum unique (nome);

    alter table PortaApplicativa 
        add constraint FK_g6tq9kdtnxmpxslw2rb1bmy7m 
        foreign key (azione_id) 
        references Azione deferrable initially immediate;

    alter table PortaApplicativa 
        add constraint FK_feaairc67n1p9wbpwlvk2sygi 
        foreign key (servizio_id) 
        references Servizio deferrable initially immediate;

    alter table PortaApplicativa 
        add constraint FK_5ur3po6lvnt4pftemwngxcx9f 
        foreign key (servizioApplicativo_id) 
        references ServizioApplicativo deferrable initially immediate;

    alter table PortaDelegata 
        add constraint FK_frtujkif4fqnvprwgepcq0n4y 
        foreign key (azione_id) 
        references Azione deferrable initially immediate;

    alter table PortaDelegata 
        add constraint FK_sdadxfvysee2w9jrwja030sou 
        foreign key (servizio_id) 
        references Servizio deferrable initially immediate;

    alter table PortaDelegata 
        add constraint FK_s4crin7ihw3m1us0se1o8s4k5 
        foreign key (soggettoFruitore_id) 
        references Soggetto deferrable initially immediate;

    alter table PortaDelegata 
        add constraint FK_a1b35ifegwdqbx0ophpaq5nmf 
        foreign key (id) 
        references PortaDelegata deferrable initially immediate;

    alter table Servizio 
        add constraint UK_rrvnfyq2na5w9i79b7ptrff26 unique (tipo, nome, erogatore_id);

    alter table Servizio 
        add constraint FK_73bjtpwpl27bpis6wceoxx86k 
        foreign key (accordoServizio_id) 
        references AccordoServizio deferrable initially immediate;

    alter table Servizio 
        add constraint FK_mg0q7pngs0yh4bwmh3yjja4mf 
        foreign key (erogatore_id) 
        references Soggetto deferrable initially immediate;

    alter table ServizioApplicativo 
        add constraint UK_mmkg51ugdoirwdmw96u8eatbx unique (nome);

    alter table Servizio_Soggetto 
        add constraint FK_7bjwupqxuaxofncadr0uows9k 
        foreign key (fruitori_id) 
        references Soggetto deferrable initially immediate;

    alter table Servizio_Soggetto 
        add constraint FK_pd32ypphg0hlcr1qtey9s7396 
        foreign key (listaServiziFruitore_id) 
        references Servizio deferrable initially immediate;

    create table hibernate_sequences (
         sequence_name varchar(255),
         sequence_next_hi_value int4 
    ) ;
