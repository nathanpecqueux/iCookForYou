CREATE TABLE `TAllergy` (
  `idAllergy` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`idAllergy`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `TDiet` (
  `idDiet` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`idDiet`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `TFood` (
  `idFood` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `idUnitF` int(11) NOT NULL,
  `excluded` enum('vegan','vegetarien','sansgluten') DEFAULT NULL,
  PRIMARY KEY (`idFood`),
  KEY `fk_idUnitF_idx` (`idUnitF`),
  CONSTRAINT `fk_idUnitF` FOREIGN KEY (`idUnitF`) REFERENCES `TUnit` (`idUnit`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `THistory` (
  `idUserH` int(11) NOT NULL,
  `urlRecipe` varchar(150) NOT NULL,
  PRIMARY KEY (`idUserH`,`urlRecipe`),
  CONSTRAINT `fk_idUserH` FOREIGN KEY (`idUserH`) REFERENCES `TUser` (`idUser`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `TStock` (
  `idUserS` int(11) NOT NULL,
  `idFood` int(11) NOT NULL,
  `quantityS` int(11) NOT NULL,
  PRIMARY KEY (`idUserS`,`idFood`),
  KEY `fk_idFood_idx` (`idFood`),
  CONSTRAINT `fk_idFoodS` FOREIGN KEY (`idFood`) REFERENCES `TFood` (`idFood`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_idUserS` FOREIGN KEY (`idUserS`) REFERENCES `TUser` (`idUser`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `TUnit` (
  `idUnit` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`idUnit`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `TUser` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `lastName` varchar(45) NOT NULL,
  `firstName` varchar(45) NOT NULL,
  `mail` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `weight(kg)` decimal(5,2) DEFAULT NULL,
  `size(cm)` int(3) DEFAULT NULL,
  `age` int(3) DEFAULT NULL,
  `sex` enum('man','woman') DEFAULT NULL,
  `physicalActivity` enum('sedentary','active','athlete') DEFAULT NULL,
  PRIMARY KEY (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `TUserAllergy` (
  `idUserUA` int(11) NOT NULL,
  `idAllergyUA` int(11) NOT NULL,
  PRIMARY KEY (`idUserUA`,`idAllergyUA`),
  KEY `fk_idAllergyUA_idx` (`idAllergyUA`),
  CONSTRAINT `fk_idAllergyUA` FOREIGN KEY (`idAllergyUA`) REFERENCES `TAllergy` (`idAllergy`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_idUserUA` FOREIGN KEY (`idUserUA`) REFERENCES `TUser` (`idUser`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `TUserDiet` (
  `idUserUD` int(11) NOT NULL AUTO_INCREMENT,
  `idDietUD` int(11) NOT NULL,
  PRIMARY KEY (`idUserUD`,`idDietUD`),
  KEY `fk_idDietUD_idx` (`idDietUD`),
  CONSTRAINT `fk_idDietUD` FOREIGN KEY (`idDietUD`) REFERENCES `TDiet` (`idDiet`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_idUserUD` FOREIGN KEY (`idUserUD`) REFERENCES `TUser` (`idUser`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;
