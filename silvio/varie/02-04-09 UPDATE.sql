ALTER TABLE parametrisla DROP CONSTRAINT parametrisla_indirizzows_key;
ALTER TABLE parametrisla ALTER indirizzows TYPE text;
ALTER TABLE messaggio ALTER indirizzo TYPE text;
