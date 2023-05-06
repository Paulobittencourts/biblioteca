CREATE TABLE usuario(
    id bigint(20) NOT NULL AUTO_INCREMENT,
    nome varchar(120) NOT NULL,
    email varchar(120) NOT NULL,
    data datetime NOT NULL,
    PRIMARY KEY (id)
)