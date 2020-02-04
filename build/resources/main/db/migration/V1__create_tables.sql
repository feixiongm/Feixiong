CREATE TABLE seller (
    /*id                INTEGER NOT NULL default nextval('department_id_seq'), */
    id                BIGSERIAL NOT NULL,
    name              VARCHAR(30) not null unique,
    email             VARCHAR(30) not null unique,
    phone_number      VARCHAR(20) not null unique,
    description       VARCHAR(150),
    location          VARCHAR(100)
);
ALTER TABLE seller ADD CONSTRAINT seller_pk PRIMARY KEY ( id );
CREATE TABLE location (
    /*id              INTEGER NOT NULL default nextval('employee_id_seq'),*/
    id              BIGSERIAL NOT NULL,
    name            VARCHAR(30) not null unique,
    phone_number    VARCHAR(30),
    email           VARCHAR(50),
    address         VARCHAR(150),
    seller_id       BIGSERIAL NOT NULL
);
ALTER TABLE location ADD CONSTRAINT location_pk PRIMARY KEY ( id );
ALTER TABLE location ADD CONSTRAINT FK_ID foreign key (seller_id) REFERENCES seller(id);
CREATE TABLE products(
    id                BIGSERIAL NOT NULL,
    product_name      VARCHAR(30) not null,
    description       VARCHAR(150),
    price             VARCHAR(20),
    weight            VARCHAR(10),
    year              VARCHAR(4),
    location_id       bigint NOT NULL

);
ALTER TABLE products ADD CONSTRAINT products_pk PRIMARY KEY ( id );
ALTER TABLE products ADD CONSTRAINT FK_ID foreign key (location_id) REFERENCES location(id);