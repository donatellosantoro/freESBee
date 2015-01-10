
    alter table Configurazione 
        add column mutuaAutenticazionePortaApplicativa boolean not null;

    alter table Configurazione 
        add column mutuaAutenticazionePortaDelegata boolean not null;
