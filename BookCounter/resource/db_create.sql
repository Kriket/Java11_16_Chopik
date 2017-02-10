-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema CBRC_DB
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema CBRC_DB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `CBRC_DB` DEFAULT CHARACTER SET utf8 ;
USE `CBRC_DB` ;

-- -----------------------------------------------------
-- Table `CBRC_DB`.`BOOK`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CBRC_DB`.`BOOK` (
  `ID` INT NOT NULL,
  `BRIEF` VARCHAR(50) NULL,
  `PUBLISH_YEAR` INT(11) NULL,
  `AUTHOR` VARCHAR(255) NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CBRC_DB`.`EMPLOYEE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CBRC_DB`.`EMPLOYEE` (
  `NAME` VARCHAR(255) NULL,
  `DATE_OF_BIRTH` DATE NULL,
  `EMAIL` VARCHAR(50) NULL,
  `ID` INT NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `CBRC_DB`.`EMPLOYEE_BOOK`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CBRC_DB`.`EMPLOYEE_BOOK` (
  `BOOK_ID` INT NULL,
  `EMPLOYEE_ID` INT NULL,
  `ID` INT NOT NULL AUTO_INCREMENT,
  INDEX `fk_EMPLOYEE_BOOK_EMPLOYEE1_idx` (`EMPLOYEE_ID` ASC),
  PRIMARY KEY (`ID`),
  CONSTRAINT `fk_EMPLOYEE_BOOK_BOOK`
    FOREIGN KEY (`BOOK_ID`)
    REFERENCES `CBRC_DB`.`BOOK` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_EMPLOYEE_BOOK_EMPLOYEE1`
    FOREIGN KEY (`EMPLOYEE_ID`)
    REFERENCES `CBRC_DB`.`EMPLOYEE` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
