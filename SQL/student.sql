-- -----------------------------------------------------
-- Schema StudentDB
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `StudentDB`;

-- -----------------------------------------------------
-- Schema StudentDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `StudentDB` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `StudentDB`;

-- DROP USER IF EXISTS cst8288@localhost;
CREATE USER IF NOT EXISTS 'cst8288'@'localhost' IDENTIFIED BY '8288';
GRANT ALL ON `StudentDB`.* TO 'cst8288'@'localhost';

-- -----------------------------------------------------
-- Table `StudentDB`.`StudentRecord`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StudentDB`.`StudentRecord` (
  `ID` INT NOT NULL,
  `FirstName` NVARCHAR(100) NOT NULL,
  `LastName` NVARCHAR(100) NOT NULL,
  `DateOfBirth` DATE NOT NULL,
  `EmailAddress` NVARCHAR(100) NOT NULL,
  `Midterm1Score` INT,
  `Midterm2Score` INT,
  `Assignment1Score` INT,
  `Assignment2Score` INT,
  `Assignment3Score` INT,
  `Assignment4Score` INT,
  `Assignment5Score` INT,
  `OverallFinalScore` INT,
  `OverallFinalGrade` NVARCHAR(2),
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Insert to table `StudentDB`.`StudentRecord`
-- -----------------------------------------------------
INSERT INTO `StudentDB`.`StudentRecord` (`ID`, `FirstName`, `LastName`, `DateOfBirth`, `EmailAddress`, `Midterm1Score`, `Midterm2Score`, `Assignment1Score`, `Assignment2Score`, `Assignment3Score`, `Assignment4Score`, `Assignment5Score`) 
	VALUES ('1', 'James', 'Milner', '1986-01-04', 'james01milner@gmail.com', '77', '88', '81', '92', '96', '84', '77');