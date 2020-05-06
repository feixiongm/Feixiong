CREATE TABLE images (
  id                BIGSERIAL NOT NULL,
  file_name         VARCHAR(512) not null,
  user_id           BIGINT NOT NULL,
  upload_time       TIMESTAMP,
  s3key             VARCHAR(150),
  url               VARCHAR(512),
  extension         VARCHAR(150),
  uuid              VARCHAR(300),
  description       VARCHAR(512)
);
ALTER TABLE images ADD CONSTRAINT images_pk PRIMARY KEY(id);
ALTER TABLE images
    ADD CONSTRAINT images_user_fk FOREIGN KEY (user_id)
    REFERENCES users(id);