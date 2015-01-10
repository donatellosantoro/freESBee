
    alter table Servizio 
        add column mutuaAutenticazione boolean not null;

    alter table Servizio 
        add column urlCertificato varchar(255);

    alter table Servizio 
        add column urlRidefinito boolean not null;

    alter table ServizioApplicativo 
        add column mutuaAutenticazione boolean not null;

    alter table ServizioApplicativo 
        add column urlCertificato varchar(255);

    alter table Soggetto 
        add column mutuaAutenticazione boolean not null;

    alter table Soggetto 
        add column urlCertificato varchar(255);
