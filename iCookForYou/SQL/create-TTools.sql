-- phpMyAdmin SQL Dump
-- version 4.6.6deb4
-- https://www.phpmyadmin.net/
--
-- Client :  localhost:3306
-- Généré le :  Lun 29 Janvier 2018 à 00:23
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

-- --------------------------------------------------------

--
-- Structure de la table `TCategory`
--

CREATE TABLE `TCategory` (
  `idCategory` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `idParentCategory` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `TDiet`
--

CREATE TABLE `TDiet` (
  `idDiet` int(11) NOT NULL,
  `name` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `TFavorites`
--

CREATE TABLE `TFavorites` (
  `idUserF` int(11) NOT NULL,
  `idRecipeF` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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

-- --------------------------------------------------------

--
-- Structure de la table `THistory`
--

CREATE TABLE `THistory` (
  `idUserH` int(11) NOT NULL,
  `idRecipeH` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `TRecipe`
--

CREATE TABLE `TRecipe` (
  `idRecipe` int(11) NOT NULL,
  `idProviderR` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `TRecipeProvider`
--

CREATE TABLE `TRecipeProvider` (
  `idRecipeProvider` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `url` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `TStock`
--

CREATE TABLE `TStock` (
  `idUserS` int(11) NOT NULL,
  `idFood` int(11) NOT NULL,
  `quantityS` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `TTools`
--

CREATE TABLE `TTools` (
  `idTools` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `TUnit`
--

CREATE TABLE `TUnit` (
  `idUnit` int(11) NOT NULL,
  `name` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `TUser`
--

CREATE TABLE `TUser` (
  `idUser` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `firstname` varchar(45) NOT NULL,
  `mail` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `weight(kg)` decimal(5,2) DEFAULT NULL,
  `size(cm)` int(3) DEFAULT NULL,
  `age` int(3) DEFAULT NULL,
  `sex` enum('man','woman') DEFAULT NULL,
  `physicalActivity` enum('sedentary','active','athlete') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `TUserAllergy`
--

CREATE TABLE `TUserAllergy` (
  `idUserUA` int(11) NOT NULL,
  `idAllergyUA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `TUserDiet`
--

CREATE TABLE `TUserDiet` (
  `idUserUD` int(11) NOT NULL,
  `idDietUD` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `TUserTools`
--

CREATE TABLE `TUserTools` (
  `idUserUT` int(11) NOT NULL,
  `idToolsUT` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
-- Index pour la table `TTools`
--
ALTER TABLE `TTools`
  ADD PRIMARY KEY (`idTools`);

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
-- Index pour la table `TUserTools`
--
ALTER TABLE `TUserTools`
  ADD PRIMARY KEY (`idUserUT`,`idToolsUT`),
  ADD KEY `fk_idToolsUT_idx` (`idToolsUT`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `TAllergy`
--
ALTER TABLE `TAllergy`
  MODIFY `idAllergy` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `TCategory`
--
ALTER TABLE `TCategory`
  MODIFY `idCategory` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `TDiet`
--
ALTER TABLE `TDiet`
  MODIFY `idDiet` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `TFood`
--
ALTER TABLE `TFood`
  MODIFY `idFood` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `TRecipe`
--
ALTER TABLE `TRecipe`
  MODIFY `idRecipe` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `TRecipeProvider`
--
ALTER TABLE `TRecipeProvider`
  MODIFY `idRecipeProvider` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `TTools`
--
ALTER TABLE `TTools`
  MODIFY `idTools` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `TUnit`
--
ALTER TABLE `TUnit`
  MODIFY `idUnit` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `TUser`
--
ALTER TABLE `TUser`
  MODIFY `idUser` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `TUserDiet`
--
ALTER TABLE `TUserDiet`
  MODIFY `idUserUD` int(11) NOT NULL AUTO_INCREMENT;
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
-- Contraintes pour la table `TUserTools`
--
ALTER TABLE `TUserTools`
  ADD CONSTRAINT `fk_idToolsUT` FOREIGN KEY (`idToolsUT`) REFERENCES `TTools` (`idTools`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_idUserUT` FOREIGN KEY (`idUserUT`) REFERENCES `TUser` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
