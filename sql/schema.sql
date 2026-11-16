-- Esquema equivalente al que Hibernate genera con spring.jpa.hibernate.ddl-auto=update
CREATE DATABASE IF NOT EXISTS eduoptima_db CHARACTER SET utf8mb4;
USE eduoptima_db;

CREATE TABLE IF NOT EXISTS student (
  id            BIGINT       NOT NULL AUTO_INCREMENT,
  academic_risk INT          NOT NULL,
  name          VARCHAR(255) NOT NULL,
  priority      INT          NOT NULL,
  topic         VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS tutor (
  id           BIGINT       NOT NULL AUTO_INCREMENT,
  max_capacity INT          NOT NULL,
  name         VARCHAR(255) NOT NULL,
  specialty    VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS assigment (
  id BIGINT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (id)
) ENGINE=InnoDB;
