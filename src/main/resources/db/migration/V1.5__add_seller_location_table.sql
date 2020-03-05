CREATE TABLE sellers_locations (
    seller_id        BIGINT NOT NULL,
    location_id      BIGINT NOT NULL
);

ALTER TABLE sellers_locations
    ADD CONSTRAINT sellers_fk FOREIGN KEY ( seller_id )
        REFERENCES sellers ( id );

ALTER TABLE sellers_locations
    ADD CONSTRAINT locations_fk FOREIGN KEY ( location_id )
        REFERENCES locations ( id );