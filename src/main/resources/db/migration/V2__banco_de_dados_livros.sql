CREATE TABLE livro
(
    id bigint(20) NOT NULL AUTO_INCREMENT,
    nome varchar(120) NOT NULL,
    autor varchar(120) NOT NULL,
    ano_de_lancamento datetime NOT NULL,
    quantidade bigint(20) NOT NULL,
    status varchar(120) NOT NULL,
    usuario_id bigint(20) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (usuario_id) REFERENCES usuario (id)
)