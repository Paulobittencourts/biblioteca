CREATE table reserva (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    usuario_id bigint(40) NOT NULL,
    livro_id bigint(40) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (usuario_id) REFERENCES usuario (id),
    FOREIGN KEY (livro_id) REFERENCES livro (id)

)