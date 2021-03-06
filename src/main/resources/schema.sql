CREATE TABLE movimiento (
    id   INTEGER      NOT NULL AUTO_INCREMENT,
    userId INTEGER NOT NULL,
    ticket VARCHAR(128) NOT NULL,
    valor FLOAT NOT NULL,
    cantidad INTEGER NOT NULL,
    fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);
CREATE TABLE usuario (
    id   INTEGER      NOT NULL AUTO_INCREMENT,
    username VARCHAR(128) NOT NULL UNIQUE,
    password VARCHAR(128) NOT NULL,
    mail VARCHAR(128) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);