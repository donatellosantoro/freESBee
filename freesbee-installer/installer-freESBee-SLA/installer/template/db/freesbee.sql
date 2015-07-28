
    create table AccordoServizio (
        id int8 not null,
        descrizione varchar(255),
        nome varchar(255) not null unique,
        oraRegistrazione timestamp,
        privato bool not null,
        profiloCollaborazione varchar(255),
        profiloEGov_id int8 not null,
        primary key (id),
        unique (profiloEGov_id)
    );

    create table Azione (
        id int8 not null,
        nome varchar(255) not null,
        accordoServizio_id int8 not null,
        profiloEGov_id int8,
        primary key (id)
    );

    create table Configurazione (
        id int8 not null,
        NICA bool,
        connettoreRegistroServizi varchar(255),
        indirizzoPortaApplicativa varchar(255) not null,
        indirizzoPortaDelegata varchar(255) not null,
        inviaANICA bool,
        registroFreesbee bool not null,
        tempo varchar(255) not null,
        soggettoErogatoreNICA_id int8,
        soggettoErogatoreRegistroServizi_id int8,
        primary key (id)
    );

    create table Messaggio (
        id int8 not null,
        idMessaggio varchar(255),
        idSil varchar(255),
        oraRegistrazione varchar(255),
        riferimentoMessaggio varchar(255),
        silRelatesTo varchar(255),
        tipo varchar(255),
        primary key (id),
        unique (idMessaggio, tipo)
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
        stringaServizio varchar(255),
        stringaTipoServizio varchar(255),
        servizioApplicativo_id int8,
        azione_id int8,
        servizio_id int8,
        primary key (id),
        unique (nome)
    );

    create table PortaDelegata (
        id int8 not null,
        descrizione varchar(255),
        nome varchar(255) not null,
        stringaAzione varchar(255),
        stringaErogatore varchar(255),
        stringaFruitore varchar(255),
        stringaServizio varchar(255),
        stringaTipoErogatore varchar(255),
        stringaTipoFruitore varchar(255),
        stringaTipoServizio varchar(255),
        servizio_id int8,
        azione_id int8,
        soggettoFruitore_id int8,
        primary key (id)
    );

    create table ProfiloEGov (
        id int8 not null,
        confermaRicezione bool not null,
        consegnaInOrdine bool not null,
        gestioneDuplicati bool not null,
        idCollaborazione bool not null,
        scadenza timestamp,
        primary key (id)
    );

    create table Servizio (
        id int8 not null,
        correlato bool not null,
        nome varchar(255) not null,
        oraRegistrazione timestamp,
        privato bool not null,
        tipo varchar(20) default 'SPC' not null,
        accordoServizio_id int8 not null,
        erogatore_id int8 not null,
        primary key (id),
        unique (tipo, nome, erogatore_id),
        unique (erogatore_id, accordoServizio_id)
    );

    create table ServizioApplicativo (
        id int8 not null,
        connettore varchar(255),
        descrizione varchar(255),
        nome varchar(255),
        primary key (id),
        unique (nome)
    );

    create table Servizio_Soggetto (
        listaServiziFruitore_id int8 not null,
        fruitori_id int8 not null
    );

    create table Sincronizzazione (
        id int8 not null,
        freesbee bool not null,
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
        add constraint FK1670E1DCDA8E8C21 
        foreign key (profiloEGov_id) 
        references ProfiloEGov deferrable initially immediate;

    alter table Azione 
        add constraint FK75D39D36DA8E8C21 
        foreign key (profiloEGov_id) 
        references ProfiloEGov deferrable initially immediate;

    alter table Azione 
        add constraint FK75D39D36AD1F84C1 
        foreign key (accordoServizio_id) 
        references AccordoServizio deferrable initially immediate;

    alter table Configurazione 
        add constraint FKB7D68355B6A2FA4E 
        foreign key (soggettoErogatoreRegistroServizi_id) 
        references Soggetto deferrable initially immediate;

    alter table Configurazione 
        add constraint FKB7D68355B0F62B9C 
        foreign key (soggettoErogatoreNICA_id) 
        references Soggetto deferrable initially immediate;

    alter table PortaApplicativa 
        add constraint FKC6387A1C8AD0D013 
        foreign key (servizio_id) 
        references Servizio deferrable initially immediate;

    alter table PortaApplicativa 
        add constraint FKC6387A1C4904D161 
        foreign key (servizioApplicativo_id) 
        references ServizioApplicativo deferrable initially immediate;

    alter table PortaApplicativa 
        add constraint FKC6387A1CB01025B3 
        foreign key (azione_id) 
        references Azione deferrable initially immediate;

    alter table PortaDelegata 
        add constraint FK40B5E9818AD0D013 
        foreign key (servizio_id) 
        references Servizio deferrable initially immediate;

    alter table PortaDelegata 
        add constraint FK40B5E981324E80E3 
        foreign key (id) 
        references PortaDelegata deferrable initially immediate;

    alter table PortaDelegata 
        add constraint FK40B5E981B01025B3 
        foreign key (azione_id) 
        references Azione deferrable initially immediate;

    alter table PortaDelegata 
        add constraint FK40B5E98114933A65 
        foreign key (soggettoFruitore_id) 
        references Soggetto deferrable initially immediate;

    alter table Servizio 
        add constraint FK560FCB4DD67492EF 
        foreign key (erogatore_id) 
        references Soggetto deferrable initially immediate;

    alter table Servizio 
        add constraint FK560FCB4DAD1F84C1 
        foreign key (accordoServizio_id) 
        references AccordoServizio deferrable initially immediate;

    alter table Servizio_Soggetto 
        add constraint FK1A74527887313F53 
        foreign key (listaServiziFruitore_id) 
        references Servizio deferrable initially immediate;

    alter table Servizio_Soggetto 
        add constraint FK1A745278A4B9147 
        foreign key (fruitori_id) 
        references Soggetto deferrable initially immediate;

    create table hibernate_sequences (
         sequence_name varchar(255),
         sequence_next_hi_value int4 
    ) ;
