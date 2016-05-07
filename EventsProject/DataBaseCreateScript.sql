-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema clujT
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema clujT
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `clujT` DEFAULT CHARACTER SET utf8 ;
USE `clujT` ;

-- -----------------------------------------------------
-- Table `clujT`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clujT`.`users` (
  `username` VARCHAR(75) NOT NULL,
  `userpassword` VARCHAR(45) NULL,
  `admin` TINYINT(1) NULL,
  `userID` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(45) NULL,
  PRIMARY KEY (`userID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `clujT`.`events`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clujT`.`events` (
  `eventID` INT NOT NULL AUTO_INCREMENT,
  `eventTitle` VARCHAR(45) NULL,
  `eventDirector` VARCHAR(45) NULL,
  `eventDistribution` VARCHAR(200) NULL,
  `eventReleaseDate` DATETIME NULL,
  `eventTickets` INT NULL,
  PRIMARY KEY (`eventID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `clujT`.`Tickets`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `clujT`.`Tickets` (
  `ticketID` INT NOT NULL AUTO_INCREMENT,
  `ticketRow` INT NULL,
  `ticketColumn` INT NULL,
  `ticketEvent` VARCHAR(70) NULL,
  `eventID` INT NOT NULL,
  PRIMARY KEY (`ticketID`, `eventID`),
  INDEX `fk_Tickets_events_idx` (`eventID` ASC),
  CONSTRAINT `fk_Tickets_events`
    FOREIGN KEY (`eventID`)
    REFERENCES `clujT`.`events` (`eventID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
