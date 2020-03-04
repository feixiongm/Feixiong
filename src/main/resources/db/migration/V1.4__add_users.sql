CREATE TABLE users(
    id                BIGSERIAL NOT NULL,
    name              VARCHAR(30) not null,
    first_name        VARCHAR(150),
    last_name         VARCHAR(150),
    password          VARCHAR(150),
    email             VARCHAR(150)

);
ALTER TABLE users ADD CONSTRAINT users_pk PRIMARY KEY ( id );
