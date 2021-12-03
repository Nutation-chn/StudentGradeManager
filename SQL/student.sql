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
  `StudentID` INT NOT NULL auto_increment,
  `FirstName` NVARCHAR(100) NOT NULL,
  `LastName` NVARCHAR(100) NOT NULL,
  `DateOfBirth` DATE NOT NULL,
  `EmailAddress` NVARCHAR(100) NOT NULL,
  `Midterm1` INT,
  `Midterm2` INT,
  `Assignment1` INT,
  `Assignment2` INT,
  `Assignment3` INT,
  `Assignment4` INT,
  `Assignment5` INT,
  `FinalScore` INT,
  `FinalGrade` NVARCHAR(2),
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Insert to table `StudentDB`.`StudentRecord`
-- -----------------------------------------------------
INSERT INTO `StudentDB`.`StudentRecord` (`FirstName`, `LastName`, `DateOfBirth`, `EmailAddress`, `Midterm1`, `Midterm2`, `Assignment1`, `Assignment2`, `Assignment3`, `Assignment4`, `Assignment5`) 
	VALUES ( 'James', 'Milner', '1986-01-04', 'james01milner@gmail.com', '77', '88', '81', '92', '96', '84', '77');