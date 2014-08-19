CREATE DATABASE `simplestnote`
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `note_permissions` (
  `id`              INT(11)  NOT NULL AUTO_INCREMENT,
  `note_version_id` INT(11)  NOT NULL,
  `owner_id`        INT(11)  NOT NULL,
  `shared_date`     DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idnote_permissions_UNIQUE` (`id`)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =utf8;

CREATE TABLE `note_versions` (
  `id`            INT(10) UNSIGNED NOT NULL,
  `note_id`       INT(11)          NOT NULL,
  `modified_date` DATETIME         NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =utf8;

CREATE TABLE `notes` (
  `id`          INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `title`       VARCHAR(250)     NOT NULL,
  `content`     TEXT,
  `create_date` DATETIME         NOT NULL,
  `tags`        TEXT,
  `parent_id`   INT(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =utf8;

CREATE TABLE `tags` (
  `id`      INT(11)      NOT NULL AUTO_INCREMENT,
  `name`    VARCHAR(250) NOT NULL,
  `user_id` INT(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =utf8;

CREATE TABLE `users` (
  `id`                BIGINT(20)  NOT NULL AUTO_INCREMENT,
  `first_name`        VARCHAR(50) NOT NULL,
  `last_name`         VARCHAR(50) NOT NULL,
  `surname`           VARCHAR(50) DEFAULT NULL,
  `e_mail`            VARCHAR(50) NOT NULL,
  `birth_day`         DATE        NOT NULL,
  `user_name`         VARCHAR(50) NOT NULL,
  `password`          VARCHAR(50) NOT NULL,
  `gender`            CHAR(5)     NOT NULL,
  `is_verified`       BIT(1) DEFAULT NULL,
  `verification_code` TEXT,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `e_mail_UNIQUE` (`e_mail`)
)
  ENGINE =InnoDB
  AUTO_INCREMENT =16
  DEFAULT CHARSET =utf8;
