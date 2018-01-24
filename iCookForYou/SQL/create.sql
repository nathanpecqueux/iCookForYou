CREATE TABLE `TAllergy` (
  `idAllergy` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`idAllergy`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `TDiet` (
  `idDiet` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`idDiet`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `TFavorites` (
  `idUserF` int(11) NOT NULL,
  `idRecipeF` int(11) DEFAULT NULL,
  PRIMARY KEY (`idUserF`),
  KEY `fk_idRecipeF_idx` (`idRecipeF`),
  CONSTRAINT `fk_idRecipeF` FOREIGN KEY (`idRecipeF`) REFERENCES `TRecipe` (`idRecipe`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_idUserF` FOREIGN KEY (`idUserF`) REFERENCES `TUser` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `TFood` (
  `idFood` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `quantity` int(11) NOT NULL,
  `unit` varchar(45) NOT NULL,
  `codeEAN` int(13) NOT NULL,
  PRIMARY KEY (`idFood`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `THistory` (
  `idUserH` int(11) NOT NULL,
  `idRecipeH` int(11) NOT NULL,
  PRIMARY KEY (`idUserH`,`idRecipeH`),
  KEY `fk_idRecipeH_idx` (`idRecipeH`),
  CONSTRAINT `fk_idRecipeH` FOREIGN KEY (`idRecipeH`) REFERENCES `TRecipe` (`idRecipe`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_idUserH` FOREIGN KEY (`idUserH`) REFERENCES `TUser` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `TRecipe` (
  `idRecipe` int(11) NOT NULL AUTO_INCREMENT,
  `nameProvider` varchar(45) NOT NULL,
  PRIMARY KEY (`idRecipe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `TStock` (
  `idUserS` int(11) NOT NULL,
  `idFood` int(11) NOT NULL,
  `quantityS` int(11) NOT NULL,
  PRIMARY KEY (`idUserS`,`idFood`),
  KEY `fk_idFood_idx` (`idFood`),
  CONSTRAINT `fk_idFoodS` FOREIGN KEY (`idFood`) REFERENCES `TFood` (`idFood`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_idUserS` FOREIGN KEY (`idUserS`) REFERENCES `TUser` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `TUser` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `firstname` varchar(45) NOT NULL,
  `mail` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `TUserAllergy` (
  `idUserUA` int(11) NOT NULL,
  `idAllergyUA` int(11) NOT NULL,
  PRIMARY KEY (`idUserUA`,`idAllergyUA`),
  KEY `fk_idAllergyUA_idx` (`idAllergyUA`),
  CONSTRAINT `fk_idAllergyUA` FOREIGN KEY (`idAllergyUA`) REFERENCES `TAllergy` (`idAllergy`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_idUserUA` FOREIGN KEY (`idUserUA`) REFERENCES `TUser` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `TUserDiet` (
  `idUserUD` int(11) NOT NULL AUTO_INCREMENT,
  `idDietUD` int(11) NOT NULL,
  PRIMARY KEY (`idUserUD`,`idDietUD`),
  KEY `fk_idDietUD_idx` (`idDietUD`),
  CONSTRAINT `fk_idDietUD` FOREIGN KEY (`idDietUD`) REFERENCES `TDiet` (`idDiet`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_idUserUD` FOREIGN KEY (`idUserUD`) REFERENCES `TUser` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
