-- MySQL Workbench Forward Engineering

##SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
##SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
##SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema new_schooldb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema new_schooldb
-- -----------------------------------------------------
unlock tables;
CREATE SCHEMA IF NOT EXISTS `new_schooldb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `new_schooldb` ;



-- -----------------------------------------------------
-- Table `new_schooldb`.`client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new_schooldb`.`client` (
  `id` INT NOT NULL,
  `Nume` VARCHAR(45) NOT NULL,
  `Oras` VARCHAR(45) NOT NULL,
  `Adresa` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Varsta` INT NOT NULL,
  `Telefon` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

set @saved_cs_client=@@character_set_client;
set character_set_client=utf8;
set character_set_client=@saved_cs_client;





-- -----------------------------------------------------
-- Table `new_schooldb`.`produs`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new_schooldb`.`produs` (
  `id` INT NOT NULL,
  `Nume` VARCHAR(45) NOT NULL,
  `cantitate` INT NOT NULL,
  `pret1buc` INT NOT NULL,
  PRIMARY KEY (`id`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;




-- -----------------------------------------------------
-- Table `new_schooldb`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `new_schooldb`.`orders` (
  `id` INT NOT NULL,
  `idClient` INT NOT NULL,
  `idProdus` INT NOT NULL,
  `cantitate` INT NOT NULL,
  `data` DATE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `idProdus_idx` (`idProdus` ASC) ,
  INDEX `idClient_idx` (`idClient` ASC) ,
  CONSTRAINT `idClient`
    FOREIGN KEY (`idClient`)
    REFERENCES `new_schooldb`.`client` (`id`),
  CONSTRAINT `idProdus`
    FOREIGN KEY (`idProdus`)
    REFERENCES `new_schooldb`.`produs` (`id`))
  
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;



insert into client (id,Nume,Oras,Adresa,Email,Varsta,Telefon) values
(1,'Big Joe Davis','Cluj-Napoca',' Bulevardul 54','big_joe@gmail.com',18,'0741111111'),
(2,'Small John Dane','Cluj-Napoca','Corneliu Coposu 12','small_john@gmail.com',22,'0741111112'),
(3,'Travolta John','Cluj-Napoca','Bulevardul 11','travolta_john@gmail.com',54,'0741111113'),
(4,'Anabell Smith ','Cluj-Napoca','Muncii 22','anabell12@gmail.com',22,'0741111114'),
(5,'John Dave','Cluj-Napoca','Bulevardul 77','johny112@gmail.com',25,'0741111115'),
(6,'Mircea Dan','Cluj-Napoca','Muncii 11','dan112@yahoo.com',72,'0741111116'),
(7,'Iris Hieb','Cluj-Napoca','Muncii 77','iris55@gmail.com',55,'0741111117'),
(8,'Carrie Victoria','Cluj-Napoca','Eroilor 4','carry22@gmail.com',33,'0741111118'),
(9,'Antonio Lup','Cluj-Napoca','Avram Iancu 5','antonio988@yahoo.com',63,'0741111119'),
(10,'Velkan Ilie','Bucharest','Unirii  12','il_velkan@yahoo.com',26,'0741111121'),
(11,'Horea Gilca','Bucharest','Mosilor 88','ho_gil@gmail.com',84,'0741111122'),
(12,'Costica Pavel','Bucharest','Calea Victoriei 9','costic@gmail.com',91,'0741111123'),
(13,'Iancu Petri','Bucharest','Calea Victoriei19','iancu31@gmail.com',19,'0741111124'),
(14,'Flavius Pavel','Targu Mures','Bolyai 80','flavi21@yahoo.com',43,'0741111125');



insert into produs(id,Nume,cantitate,pret1buc) values
(1,'Chrismas T-shirt',110,99),(2,'Lego Duplo Castle',126,85),
(3,'Lego Castle',137,80),(4,'Salami Sasesc',0,33),
(5,'Spaghetti Italy',60,13),(6,'Ski XL',0,723),
(7,'Samsung S8',8,2000),(8,'Fiat Punto',2,45000),
(9,'Samsung S9',2,2800),(10,'Minge Fotbal Adidas',20,99),
(11,'Huavei P9',15,1600),(12,'Lego Duplo Construction',60,50),
(13,'Minge Fotbal Mini',50,55),(14,'Ford Focus',1,40000),
(15,'Barbie Doll',150,99),(16,'Bike Merida XST',4,1050),
(17,'Dog Plus',50,25),(18,'Pokemon cards',49,60),
(19,'Bike Focus Raven',3,5400),
(20,'Necklace silver',11,220);


insert into orders(id,idClient,idProdus,cantitate,data) values
(1,1,1,3,'2018-04-09'),(2,14,3,10,'2018-04-09'),
(3,6,4,3,'2018-04-09'),(4,4,4,1,'2018-04-09'),
(5,5,20,3,'2018-04-10'),(6,3,20,1,'2018-04-10'),
(7,1,7,1,'2018-04-16');








##SET SQL_MODE=@OLD_SQL_MODE;
##SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
##SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
