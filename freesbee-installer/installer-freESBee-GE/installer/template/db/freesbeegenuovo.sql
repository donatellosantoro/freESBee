
    create table CategoriaEventiEsterna (
        id int8 not null,
        attiva bool not null,
        inAttesa bool not null,
        nome varchar(255) not null,
        primary key (id),
        unique (nome)
    );

    create table CategoriaEventiInterna (
        id int8 not null,
        attiva bool not null,
        nome varchar(255) not null,
        primary key (id),
        unique (nome)
    );

    create table Configurazione (
        id int8 not null,
        nomeGestoreEventi varchar(255),
        nomeServizioConsegna varchar(255),
        nomeServizioNotifica varchar(255),
        nomeServizioPubblica varchar(255),
        pdConsegna varchar(255),
        pdNotifica varchar(255),
        pdPubblica varchar(255),
        scadenzaMessaggi int4 not null,
        tipoGestoreEventi varchar(255),
        tipoServizioConsegna varchar(255),
        tipoServizioNotifica varchar(255),
        tipoServizioPubblica varchar(255),
        primary key (id)
    );

    create table ConfigurazioneSP (
        id int8 not null,
        autenticazione varchar(255),
        nomeUtenteSP varchar(255),
        passwordSP varchar(255),
        risorsa varchar(255),
        risorsaPdConsegna varchar(255),
        risorsaPdNotifica varchar(255),
        risorsaPdPubblica varchar(255),
        urlFreesbeeSP varchar(255),
        primary key (id)
    );

    create table EventoPubblicatoEsterno (
        id int8 not null,
        dataPubblicazione timestamp not null,
        messaggio text,
        nomePubblicatoreEsterno varchar(255),
        tipoPubblicatoreEsterno varchar(255),
        categoriaEventi_id int8 not null,
        pubblicatore_id int8 not null,
        primary key (id)
    );

    create table EventoPubblicatoInterno (
        id int8 not null,
        dataPubblicazione timestamp not null,
        messaggio text,
        categoriaEventi_id int8 not null,
        pubblicatore_id int8 not null,
        primary key (id)
    );

    create table FiltroContenuto (
        id int4 not null,
        regularExpression varchar(255) not null,
        primary key (id)
    );

    create table FiltroData (
        id int4 not null,
        dataFine timestamp,
        dataInizio timestamp not null,
        primary key (id)
    );

    create table FiltroPubblicatoreEsterno (
        id int8 not null,
        pubblicatore_id int8 not null,
        sottoscrizione_id int8 not null,
        primary key (id),
        unique (pubblicatore_id, sottoscrizione_id)
    );

    create table FiltroPubblicatoreInterno (
        id int8 not null,
        pubblicatore_id int8 not null,
        sottoscrizione_id int8 not null,
        primary key (id),
        unique (pubblicatore_id, sottoscrizione_id)
    );

    create table GestoreEventi (
        id int8 not null,
        descrizione varchar(255),
        nome varchar(255) not null,
        tipo varchar(255) not null,
        primary key (id),
        unique (tipo, nome)
    );

    create table PubblicatoreEsterno (
        id int8 not null,
        descrizione varchar(255),
        nome varchar(255) not null,
        tipo varchar(255) not null,
        primary key (id),
        unique (tipo, nome)
    );

    create table PubblicatoreEsterno_CategoriaEventiEsterna (
        listaPubblicatori_id int8 not null,
        listaCatgoriaEventi_id int8 not null
    );

    create table PubblicatoreInterno (
        id int8 not null,
        descrizione varchar(255),
        nome varchar(255) not null,
        tipo varchar(255) not null,
        primary key (id),
        unique (tipo, nome)
    );

    create table PubblicatoreInterno_CategoriaEventiInterna (
        listaPubblicatori_id int8 not null,
        listaCatgoriaEventi_id int8 not null
    );

    create table Sottoscrittore (
        id int8 not null,
        descrizione varchar(255),
        nome varchar(255) not null,
        tipo varchar(255) not null,
        primary key (id),
        unique (tipo, nome)
    );

    create table SottoscrizioneEsterna (
        id int8 not null,
        scadenzaAttesa timestamp,
        tipoSottoscrizione varchar(20) CHECK (tipoSottoscrizione LIKE 'CONSEGNA' OR tipoSottoscrizione LIKE 'NOTIFICA') not null,
        categoriaEventi_id int8 not null,
        filtroContenuto_id int4,
        filtroData_id int4 not null,
        sottoscrittore_id int8 not null,
        primary key (id),
        unique (filtroData_id)
    );

    create table SottoscrizioneInterna (
        id int8 not null,
        tipoSottoscrizione varchar(20) CHECK (tipoSottoscrizione LIKE 'CONSEGNA' OR tipoSottoscrizione LIKE 'NOTIFICA') not null,
        categoriaEventi_id int8 not null,
        filtroContenuto_id int4,
        filtroData_id int4 not null,
        sottoscrittore_id int8 not null,
        primary key (id),
        unique (filtroData_id)
    );

    create table StatoMessaggioEsterno (
        id int8 not null,
        dataAggiornamento timestamp,
        erroreProcessamento varchar(255),
        stato varchar(255),
        eventoPubblicato_id int8 not null,
        sottoscrittore_id int8 not null,
        primary key (id)
    );

    create table StatoMessaggioInterno (
        id int8 not null,
        dataAggiornamento timestamp,
        erroreProcessamento varchar(255),
        stato varchar(255),
        eventoPubblicato_id int8 not null,
        sottoscrittore_id int8 not null,
        primary key (id)
    );

    create table Utente (
        nomeUtente varchar(255) not null,
        password varchar(255) not null,
        primary key (nomeUtente)
    );

    alter table EventoPubblicatoEsterno 
        add constraint FK48EB28E4EB9CA02 
        foreign key (categoriaEventi_id) 
        references CategoriaEventiEsterna deferrable initially immediate;

    alter table EventoPubblicatoEsterno 
        add constraint FK48EB28E46608140C 
        foreign key (pubblicatore_id) 
        references PubblicatoreEsterno deferrable initially immediate;

    alter table EventoPubblicatoInterno 
        add constraint FK13FBDACDD9CA7BEB 
        foreign key (categoriaEventi_id) 
        references CategoriaEventiInterna deferrable initially immediate;

    alter table EventoPubblicatoInterno 
        add constraint FK13FBDACD3118C5F5 
        foreign key (pubblicatore_id) 
        references PubblicatoreInterno deferrable initially immediate;

    alter table FiltroPubblicatoreEsterno 
        add constraint FK4B9A687E16A42660 
        foreign key (sottoscrizione_id) 
        references SottoscrizioneEsterna deferrable initially immediate;

    alter table FiltroPubblicatoreEsterno 
        add constraint FK4B9A687E6608140C 
        foreign key (pubblicatore_id) 
        references PubblicatoreEsterno deferrable initially immediate;

    alter table FiltroPubblicatoreInterno 
        add constraint FK16AB1A67E1B4D849 
        foreign key (sottoscrizione_id) 
        references SottoscrizioneInterna deferrable initially immediate;

    alter table FiltroPubblicatoreInterno 
        add constraint FK16AB1A673118C5F5 
        foreign key (pubblicatore_id) 
        references PubblicatoreInterno deferrable initially immediate;

    alter table PubblicatoreEsterno_CategoriaEventiEsterna 
        add constraint FK63FCF8FDCBF8ABA5 
        foreign key (listaPubblicatori_id) 
        references PubblicatoreEsterno deferrable initially immediate;

    alter table PubblicatoreEsterno_CategoriaEventiEsterna 
        add constraint FK63FCF8FDD7008926 
        foreign key (listaCatgoriaEventi_id) 
        references CategoriaEventiEsterna deferrable initially immediate;

    alter table PubblicatoreInterno_CategoriaEventiInterna 
        add constraint FK6330E2DD97095D8E 
        foreign key (listaPubblicatori_id) 
        references PubblicatoreInterno deferrable initially immediate;

    alter table PubblicatoreInterno_CategoriaEventiInterna 
        add constraint FK6330E2DDA2113B0F 
        foreign key (listaCatgoriaEventi_id) 
        references CategoriaEventiInterna deferrable initially immediate;

    alter table SottoscrizioneEsterna 
        add constraint FKA505EAC9EB9CA02 
        foreign key (categoriaEventi_id) 
        references CategoriaEventiEsterna deferrable initially immediate;

    alter table SottoscrizioneEsterna 
        add constraint FKA505EAC9E8F61F20 
        foreign key (sottoscrittore_id) 
        references Sottoscrittore deferrable initially immediate;

    alter table SottoscrizioneEsterna 
        add constraint FKA505EAC9F45FAE00 
        foreign key (filtroData_id) 
        references FiltroData deferrable initially immediate;

    alter table SottoscrizioneEsterna 
        add constraint FKA505EAC9DC9DB0D4 
        foreign key (filtroContenuto_id) 
        references FiltroContenuto deferrable initially immediate;

    alter table SottoscrizioneInterna 
        add constraint FK70169CB2D9CA7BEB 
        foreign key (categoriaEventi_id) 
        references CategoriaEventiInterna deferrable initially immediate;

    alter table SottoscrizioneInterna 
        add constraint FK70169CB2E8F61F20 
        foreign key (sottoscrittore_id) 
        references Sottoscrittore deferrable initially immediate;

    alter table SottoscrizioneInterna 
        add constraint FK70169CB2F45FAE00 
        foreign key (filtroData_id) 
        references FiltroData deferrable initially immediate;

    alter table SottoscrizioneInterna 
        add constraint FK70169CB2DC9DB0D4 
        foreign key (filtroContenuto_id) 
        references FiltroContenuto deferrable initially immediate;

    alter table StatoMessaggioEsterno 
        add constraint FKEF9D4A605F9922A8 
        foreign key (eventoPubblicato_id) 
        references EventoPubblicatoEsterno deferrable initially immediate;

    alter table StatoMessaggioEsterno 
        add constraint FKEF9D4A60E8F61F20 
        foreign key (sottoscrittore_id) 
        references Sottoscrittore deferrable initially immediate;

    alter table StatoMessaggioInterno 
        add constraint FKBAADFC492AA9D491 
        foreign key (eventoPubblicato_id) 
        references EventoPubblicatoInterno deferrable initially immediate;

    alter table StatoMessaggioInterno 
        add constraint FKBAADFC49E8F61F20 
        foreign key (sottoscrittore_id) 
        references Sottoscrittore deferrable initially immediate;

    create table hibernate_sequences (
         sequence_name varchar(255),
         sequence_next_hi_value int4 
    ) ;
