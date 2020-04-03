CREATE SEQUENCE role_id_seq START WITH 1;
-- DROP TABLE IF EXISTS users CASCADE;
-- DROP TABLE IF EXISTS roles CASCADE;
CREATE TABLE roles (
    id                   BIGSERIAL NOT NULL,
    name                 VARCHAR(30) not null unique,
    allowed_resource     VARCHAR(200),
    allowed_read         boolean not null default 'N',
    allowed_create       boolean not null default 'N',
    allowed_update       boolean not null default 'N',
    allowed_delete       boolean not null default 'N'
);
ALTER TABLE roles ADD CONSTRAINT role_pk PRIMARY KEY ( id );
CREATE TABLE users_roles (
    user_id    BIGINT NOT NULL,
    role_id    BIGINT NOT NULL
);
ALTER TABLE users_roles
    ADD CONSTRAINT users_fk FOREIGN KEY ( user_id )
        REFERENCES users ( id );
ALTER TABLE users_roles
    ADD CONSTRAINT role_fk FOREIGN KEY ( role_id )
        REFERENCES roles ( id );

