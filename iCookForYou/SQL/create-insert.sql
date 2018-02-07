-- phpMyAdmin SQL Dump
-- version 4.6.6deb4
-- https://www.phpmyadmin.net/
--
-- Client :  localhost:3306
-- Généré le :  Mer 07 Février 2018 à 15:43
-- Version du serveur :  10.1.26-MariaDB-0+deb9u1
-- Version de PHP :  7.0.19-1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `icookforyou`
--

-- --------------------------------------------------------

--
-- Structure de la table `TAllergy`
--

CREATE TABLE `TAllergy` (
  `idAllergy` int(11) NOT NULL,
  `name` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `TAllergy`
--

INSERT INTO `TAllergy` (`idAllergy`, `name`) VALUES
(1, 'arachides'),
(2, 'fruit à écales'),
(3, 'lait de vache'),
(4, 'oeufs'),
(5, 'poissons'),
(6, 'fruits de mer');

-- --------------------------------------------------------

--
-- Structure de la table `TCategory`
--

CREATE TABLE `TCategory` (
  `idCategory` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `idParentCategory` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `TCategory`
--

INSERT INTO `TCategory` (`idCategory`, `name`, `idParentCategory`) VALUES
(1, 'farine', NULL),
(2, 'farine de blé', 1),
(3, 'farine de blé T110', 2);

-- --------------------------------------------------------

--
-- Structure de la table `TDiet`
--

CREATE TABLE `TDiet` (
  `idDiet` int(11) NOT NULL,
  `name` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `TDiet`
--

INSERT INTO `TDiet` (`idDiet`, `name`) VALUES
(1, 'vegan'),
(2, 'vegetarien'),
(3, 'sans gluten');

-- --------------------------------------------------------

--
-- Structure de la table `TFavorites`
--

CREATE TABLE `TFavorites` (
  `idUserF` int(11) NOT NULL,
  `idRecipeF` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `TFavorites`
--

INSERT INTO `TFavorites` (`idUserF`, `idRecipeF`) VALUES
(1, 16969),
(2, 16969);

-- --------------------------------------------------------

--
-- Structure de la table `TFood`
--

CREATE TABLE `TFood` (
  `idFood` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `quantity` int(11) NOT NULL,
  `idUnitF` int(11) NOT NULL,
  `codeEAN` int(13) NOT NULL,
  `idCategoryF` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `TFood`
--

INSERT INTO `TFood` (`idFood`, `name`, `quantity`, `idUnitF`, `codeEAN`, `idCategoryF`) VALUES
(1, 'lait', 1, 1, 156, 1),
(2, 'poisson', 1, 3, 95641, NULL),
(3, 'farine de blé T110', 500, 2, 9854, 3),
(4, 'oeufs', 6, 3, 845, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `THistory`
--

CREATE TABLE `THistory` (
  `idUserH` int(11) NOT NULL,
  `idRecipeH` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `THistory`
--

INSERT INTO `THistory` (`idUserH`, `idRecipeH`) VALUES
(2, 16969);

-- --------------------------------------------------------

--
-- Structure de la table `TRecipe`
--

CREATE TABLE `TRecipe` (
  `idRecipe` int(11) NOT NULL,
  `idProviderR` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `TRecipe`
--

INSERT INTO `TRecipe` (`idRecipe`, `idProviderR`) VALUES
(16969, 2);

-- --------------------------------------------------------

--
-- Structure de la table `TRecipeProvider`
--

CREATE TABLE `TRecipeProvider` (
  `idRecipeProvider` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `url` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `TRecipeProvider`
--

INSERT INTO `TRecipeProvider` (`idRecipeProvider`, `name`, `url`) VALUES
(1, 'wecook', 'http://api.wecook.fr/'),
(2, 'marmiton', 'http://www.marmiton.org/');

-- --------------------------------------------------------

--
-- Structure de la table `TStock`
--

CREATE TABLE `TStock` (
  `idUserS` int(11) NOT NULL,
  `idFood` int(11) NOT NULL,
  `quantityS` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `TStock`
--

INSERT INTO `TStock` (`idUserS`, `idFood`, `quantityS`) VALUES
(1, 1, 6),
(1, 3, 2),
(2, 2, 8),
(3, 1, 1),
(3, 2, 3),
(3, 3, 2);

-- --------------------------------------------------------

--
-- Structure de la table `TTool`
--

CREATE TABLE `TTool` (
  `idTool` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `TTool`
--

INSERT INTO `TTool` (`idTool`, `name`) VALUES
(1, 'four'),
(2, 'thermomix');

-- --------------------------------------------------------

--
-- Structure de la table `TUnit`
--

CREATE TABLE `TUnit` (
  `idUnit` int(11) NOT NULL,
  `name` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `TUnit`
--

INSERT INTO `TUnit` (`idUnit`, `name`) VALUES
(1, 'litre'),
(2, 'gramme'),
(3, 'unit');

-- --------------------------------------------------------

--
-- Structure de la table `TUser`
--

CREATE TABLE `TUser` (
  `idUser` int(11) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `firstName` varchar(45) NOT NULL,
  `mail` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `weight(kg)` decimal(5,2) DEFAULT NULL,
  `size(cm)` int(3) DEFAULT NULL,
  `age` int(3) DEFAULT NULL,
  `sex` enum('man','woman') DEFAULT NULL,
  `physicalActivity` enum('sedentary','active','athlete') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `TUser`
--

INSERT INTO `TUser` (`idUser`, `lastName`, `firstName`, `mail`, `password`, `weight(kg)`, `size(cm)`, `age`, `sex`, `physicalActivity`) VALUES
(1, 'Pecqueux', 'nathan', 'nathan@gmail.com', 'nathan', '65.00', 178, 22, 'man', 'active'),
(2, 'Lebegue', 'Clement', 'clement@hotmail.fr', 'clement', '80.00', 190, 25, 'woman', 'athlete'),
(3, 'Bomy', 'Francois', 'fr@gmail.com', 'franfran', '120.00', 180, 23, 'man', 'sedentary');

-- --------------------------------------------------------

--
-- Structure de la table `TUserAllergy`
--

CREATE TABLE `TUserAllergy` (
  `idUserUA` int(11) NOT NULL,
  `idAllergyUA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `TUserAllergy`
--

INSERT INTO `TUserAllergy` (`idUserUA`, `idAllergyUA`) VALUES
(1, 2),
(1, 5),
(2, 1);

-- --------------------------------------------------------

--
-- Structure de la table `TUserDiet`
--

CREATE TABLE `TUserDiet` (
  `idUserUD` int(11) NOT NULL,
  `idDietUD` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `TUserDiet`
--

INSERT INTO `TUserDiet` (`idUserUD`, `idDietUD`) VALUES
(2, 1);

-- --------------------------------------------------------

--
-- Structure de la table `TUserTool`
--

CREATE TABLE `TUserTool` (
  `idUserUT` int(11) NOT NULL,
  `idToolUT` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `TUserTool`
--

INSERT INTO `TUserTool` (`idUserUT`, `idToolUT`) VALUES
(1, 1),
(1, 2),
(2, 2);

--
-- Index pour les tables exportées
--

--
-- Index pour la table `TAllergy`
--
ALTER TABLE `TAllergy`
  ADD PRIMARY KEY (`idAllergy`);

--
-- Index pour la table `TCategory`
--
ALTER TABLE `TCategory`
  ADD PRIMARY KEY (`idCategory`),
  ADD KEY `fk_idSubCategoryC_idx` (`idParentCategory`);

--
-- Index pour la table `TDiet`
--
ALTER TABLE `TDiet`
  ADD PRIMARY KEY (`idDiet`);

--
-- Index pour la table `TFavorites`
--
ALTER TABLE `TFavorites`
  ADD PRIMARY KEY (`idUserF`),
  ADD KEY `fk_idRecipeF_idx` (`idRecipeF`);

--
-- Index pour la table `TFood`
--
ALTER TABLE `TFood`
  ADD PRIMARY KEY (`idFood`),
  ADD KEY `fk_idUnitF_idx` (`idUnitF`),
  ADD KEY `fk_idCategoryF_idx` (`idCategoryF`);

--
-- Index pour la table `THistory`
--
ALTER TABLE `THistory`
  ADD PRIMARY KEY (`idUserH`,`idRecipeH`),
  ADD KEY `fk_idRecipeH_idx` (`idRecipeH`);

--
-- Index pour la table `TRecipe`
--
ALTER TABLE `TRecipe`
  ADD PRIMARY KEY (`idRecipe`),
  ADD KEY `fk_idProviderR_idx` (`idProviderR`);

--
-- Index pour la table `TRecipeProvider`
--
ALTER TABLE `TRecipeProvider`
  ADD PRIMARY KEY (`idRecipeProvider`);

--
-- Index pour la table `TStock`
--
ALTER TABLE `TStock`
  ADD PRIMARY KEY (`idUserS`,`idFood`),
  ADD KEY `fk_idFood_idx` (`idFood`);

--
-- Index pour la table `TTool`
--
ALTER TABLE `TTool`
  ADD PRIMARY KEY (`idTool`);

--
-- Index pour la table `TUnit`
--
ALTER TABLE `TUnit`
  ADD PRIMARY KEY (`idUnit`);

--
-- Index pour la table `TUser`
--
ALTER TABLE `TUser`
  ADD PRIMARY KEY (`idUser`);

--
-- Index pour la table `TUserAllergy`
--
ALTER TABLE `TUserAllergy`
  ADD PRIMARY KEY (`idUserUA`,`idAllergyUA`),
  ADD KEY `fk_idAllergyUA_idx` (`idAllergyUA`);

--
-- Index pour la table `TUserDiet`
--
ALTER TABLE `TUserDiet`
  ADD PRIMARY KEY (`idUserUD`,`idDietUD`),
  ADD KEY `fk_idDietUD_idx` (`idDietUD`);

--
-- Index pour la table `TUserTool`
--
ALTER TABLE `TUserTool`
  ADD PRIMARY KEY (`idUserUT`,`idToolUT`),
  ADD KEY `fk_idToolUT_idx` (`idToolUT`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `TAllergy`
--
ALTER TABLE `TAllergy`
  MODIFY `idAllergy` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT pour la table `TCategory`
--
ALTER TABLE `TCategory`
  MODIFY `idCategory` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT pour la table `TDiet`
--
ALTER TABLE `TDiet`
  MODIFY `idDiet` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT pour la table `TFood`
--
ALTER TABLE `TFood`
  MODIFY `idFood` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT pour la table `TRecipe`
--
ALTER TABLE `TRecipe`
  MODIFY `idRecipe` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16970;
--
-- AUTO_INCREMENT pour la table `TRecipeProvider`
--
ALTER TABLE `TRecipeProvider`
  MODIFY `idRecipeProvider` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `TTool`
--
ALTER TABLE `TTool`
  MODIFY `idTool` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `TUnit`
--
ALTER TABLE `TUnit`
  MODIFY `idUnit` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT pour la table `TUser`
--
ALTER TABLE `TUser`
  MODIFY `idUser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT pour la table `TUserDiet`
--
ALTER TABLE `TUserDiet`
  MODIFY `idUserUD` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `TCategory`
--
ALTER TABLE `TCategory`
  ADD CONSTRAINT `fk_idSubCategoryC` FOREIGN KEY (`idParentCategory`) REFERENCES `TCategory` (`idCategory`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `TFavorites`
--
ALTER TABLE `TFavorites`
  ADD CONSTRAINT `fk_idRecipeF` FOREIGN KEY (`idRecipeF`) REFERENCES `TRecipe` (`idRecipe`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_idUserF` FOREIGN KEY (`idUserF`) REFERENCES `TUser` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `TFood`
--
ALTER TABLE `TFood`
  ADD CONSTRAINT `fk_idCategoryF` FOREIGN KEY (`idCategoryF`) REFERENCES `TCategory` (`idCategory`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_idUnitF` FOREIGN KEY (`idUnitF`) REFERENCES `TUnit` (`idUnit`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `THistory`
--
ALTER TABLE `THistory`
  ADD CONSTRAINT `fk_idRecipeH` FOREIGN KEY (`idRecipeH`) REFERENCES `TRecipe` (`idRecipe`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_idUserH` FOREIGN KEY (`idUserH`) REFERENCES `TUser` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `TRecipe`
--
ALTER TABLE `TRecipe`
  ADD CONSTRAINT `fk_idProviderR` FOREIGN KEY (`idProviderR`) REFERENCES `TRecipeProvider` (`idRecipeProvider`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `TStock`
--
ALTER TABLE `TStock`
  ADD CONSTRAINT `fk_idFoodS` FOREIGN KEY (`idFood`) REFERENCES `TFood` (`idFood`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_idUserS` FOREIGN KEY (`idUserS`) REFERENCES `TUser` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `TUserAllergy`
--
ALTER TABLE `TUserAllergy`
  ADD CONSTRAINT `fk_idAllergyUA` FOREIGN KEY (`idAllergyUA`) REFERENCES `TAllergy` (`idAllergy`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_idUserUA` FOREIGN KEY (`idUserUA`) REFERENCES `TUser` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `TUserDiet`
--
ALTER TABLE `TUserDiet`
  ADD CONSTRAINT `fk_idDietUD` FOREIGN KEY (`idDietUD`) REFERENCES `TDiet` (`idDiet`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_idUserUD` FOREIGN KEY (`idUserUD`) REFERENCES `TUser` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `TUserTool`
--
ALTER TABLE `TUserTool`
  ADD CONSTRAINT `fk_idToolUT` FOREIGN KEY (`idToolUT`) REFERENCES `TTool` (`idTool`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_idUserUT` FOREIGN KEY (`idUserUT`) REFERENCES `TUser` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
