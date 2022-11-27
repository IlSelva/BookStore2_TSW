DROP Database if exists progettoTSW;
CREATE DATABASE progettoTSW;
use progettoTSW;
    
Create Table Autore(
	id int(11) not null auto_increment,
    nome varchar(20) not null,
    cognome varchar(20) not null,
    Primary key(id),
    FULLTEXT KEY(nome, cognome)
);

Create Table Libro(
	id int(11) not null auto_increment,
	titolo varchar(30) not null,
    Autore int(11) not null,
    editore char(11) not null,
    genere char(3) not null,
    descrizione varchar(255) not null,
    ncopiedisp int(2) not null default 0,
    prezzocent bigint(20) not null,
	primary key(id),
    Foreign key(Autore) references Autore(id),
    FULLTEXT KEY(titolo),
    FULLTEXT KEY(titolo, editore, descrizione)
);


Create Table Cliente(
	id int(11) not null auto_increment,
    nome varchar(20) not null,
    email varchar(256) not null,
    passwordhash char(40) not null,
    admin boolean not null default false,
    primary key(id),
    UNIQUE KEY (email)
);

Create Table Acquisto(
	id int not null auto_increment,
    Libro int(11) not null, 
    Cliente int(11) not null,
    Dataac date not null,
    prezzocent bigint(20) not null,
	primary key(id),
    foreign key(Libro) references Libro(id),
    foreign key (Cliente) references Cliente(id)
);


Create Table Carrello(
	Cliente int(11) not null,
    Libro int(11) not null,
    quantita int(3) unsigned not null,
    primary key(Cliente,Libro),
    foreign key(Libro) references Libro(id)
);

CREATE TABLE login (
  id char(36) NOT NULL,
  idutente int(11) NOT NULL,
  token char(36) NOT NULL,
  time timestamp NOT NULL,
  PRIMARY KEY (id),
  KEY (idutente),
  CONSTRAINT FOREIGN KEY (idutente) REFERENCES Cliente(id)
);

#date "YYYY-MM-DD"

LOCK TABLES Autore WRITE;
insert into Autore(nome,cognome) values
("George","Orwell"),
("Dante","Alighieri"),
("Alessandro","Manzoni");
UNLOCK TABLES;

insert into Libro(titolo,Autore,editore,genere,descrizione,ncopiedisp,prezzocent) values
("1984",1,"Feltrinelli","rom","il Grande Fratello",5,"2000"),
("La fattoria degli animali",1,"Feltrinelli","rom","socialismo animale",2,"1999"),
("Divina Commedia",2,"Mondadori","poe","poema allegorico-didascalico",9,"1472"),
("I promessi sposi",3,"Mondadori","rom","romanzo storico Renzo e lucia",15,"1872"),
("La vita nuova",2,"Mondadori","rom","prosimetro",10,"1294");

insert into Cliente(nome,email,passwordhash,admin) values
("Silvio","spastore@email.it",SHA1("Password"),0),
("Alessandro","scagliozziDOC@Foggia.fg",sha1("foggia"),1),
("Prova","prova@prova.com",SHA1("Password1"),0);

create view Generi as
select distinct(genere)
from Libro;

create view Editori as
select distinct(editore)
from Libro;

select * 
from Cliente;

select * 
from login;

/*
create view LibriAutori as
select Autore , count(*) as nlib
From Libro
group by Autore;

create view numprestiti as
select cliente, count(*) num
from prestito
group by cliente;


insert into libro values
('Omaggio alla Catalogna',1,'08386600152','bio','guerra civile spagnola',2,'1938-04-25');

*/

