CREATE TABLE CINEAPP_FILMES

(COD_FILME 	 NUMBER(15)    NOT NULL,
 NOME        VARCHAR2(150) NOT NULL,
 DESCRICAO   VARCHAR2(250) NOT NULL,
 GENERO      VARCHAR2(25),
 PRIMARY KEY (COD_FILME));

COMMIT;
