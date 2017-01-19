CREATE SCHEMA IF NOT EXISTS `shop` DEFAULT CHARACTER SET utf8 ;
USE `shop` ;

-- -----------------------------------------------------
-- Table `shop`.`shop`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shop`.`shop` (
  `title` VARCHAR(255) NOT NULL,
  `category` ENUM('SNOWBOARDING', 'AIRSOFT') NOT NULL,
  `price` INT NOT NULL,
  `all_quantity` INT NOT NULL,
  `rent_quantity` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`title`),
  UNIQUE INDEX `title_UNIQUE` (`title` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shop`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shop`.`users` (
  `login` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`login`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Data for table `shop`.`shop`
-- -----------------------------------------------------
START TRANSACTION;
USE `shop`;
INSERT INTO `shop`.`shop` (`title`, `category`, `price`, `all_quantity`, `rent_quantity`) VALUES ('Weapon', 'AIRSOFT', 200, 10, 0);
INSERT INTO `shop`.`shop` (`title`, `category`, `price`, `all_quantity`, `rent_quantity`) VALUES ('Bullet', 'AIRSOFT', 5, 100, 0);
INSERT INTO `shop`.`shop` (`title`, `category`, `price`, `all_quantity`, `rent_quantity`) VALUES ('Boots', 'SNOWBOARDING', 30, 5, 0);

COMMIT;