CREATE DATABASE `simplestnote`
  DEFAULT CHARACTER SET utf8;
CREATE TABLE note_permissions
(
  id              INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  note_version_id INT             NOT NULL,
  owner_id        INT             NOT NULL,
  shared_date     DATETIME        NOT NULL
);
CREATE TABLE note_versions
(
  id            INT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
  note_id       INT                      NOT NULL,
  modified_date DATETIME                 NOT NULL
);
CREATE TABLE notes
(
  id          INT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
  title       VARCHAR(250)             NOT NULL,
  content     LONGTEXT,
  create_date DATETIME                 NOT NULL,
  tags        LONGTEXT,
  parent_id   INT,
  user_id     BIGINT                   NOT NULL
);
CREATE TABLE users
(
  id                BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  first_name        VARCHAR(50)        NOT NULL,
  last_name         VARCHAR(50)        NOT NULL,
  surname           VARCHAR(50),
  e_mail            VARCHAR(50)        NOT NULL,
  birth_day         DATE               NOT NULL,
  user_name         VARCHAR(50)        NOT NULL,
  password          VARCHAR(50)        NOT NULL,
  gender            CHAR(5)            NOT NULL,
  is_verified       BIT,
  verification_code LONGTEXT,
  address           LONGTEXT
);
CREATE UNIQUE INDEX idnote_permissions_UNIQUE ON note_permissions (id);
CREATE UNIQUE INDEX id_UNIQUE ON note_versions (id);
CREATE UNIQUE INDEX id_UNIQUE ON notes (id);
CREATE UNIQUE INDEX e_mail_UNIQUE ON users (e_mail);
CREATE UNIQUE INDEX id_UNIQUE ON users (id);
