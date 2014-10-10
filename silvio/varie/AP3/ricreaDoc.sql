delete from documento;

INSERT INTO documento(id,
            codice_amministrazione, codice_aoo, nome_documento, mime_type, 
            contenuto_documento, codice_doc_inf,percorso)
    VALUES (1,'r_sicili', 'R_SICILI', 'icardoc.txt', 'text/plain','RG9jdW1lbnRvIGRpIHByb3ZhIElDQVI=', 'doc01', '/opt/config/doc/doc01.txt');
INSERT INTO documento(id,
            codice_amministrazione, codice_aoo, nome_documento, mime_type, 
            contenuto_documento, codice_doc_inf,percorso)
    VALUES (2,'r_sicili', 'R_SICILI', 'icardoc.rtf', 'text/plain','e1xydGYxXGFuc2lcYW5zaWNwZzEyNTJcZGVmZjBcZGVmbGFuZzEwNDB7XGZvbnR0Ymx7XGYwXGZzd2lzc1xmY2hhcnNldDAgQXJpYWw7fX0Ke1xjb2xvcnRibCA7XHJlZDBcZ3JlZW4wXGJsdWUyNTU7fQp7XCpcZ2VuZXJhdG9yIE1zZnRlZGl0IDUuNDEuMjEuMjUwODt9XHZpZXdraW5kNFx1YzFccGFyZFxxY1xjZjFcYlxmMFxmczQwIERvY3VtZW50byBkaSBQcm92YSBJQ0FSXGNmMFxiMFxmczIwXHBhcgp9Cg', 'doc02', '/opt/config/doc/doc02.txt');
