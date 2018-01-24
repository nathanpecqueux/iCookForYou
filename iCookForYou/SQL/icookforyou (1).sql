-- phpMyAdmin SQL Dump
-- version 4.6.6deb4
-- https://www.phpmyadmin.net/
--
-- Client :  localhost:3306
-- Généré le :  Mer 24 Janvier 2018 à 14:16
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
(1, 'poisson'),
(2, 'lait de vache'),
(3, 'soja');

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
  `idRecipeF` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `TFood`
--

CREATE TABLE `TFood` (
  `idFood` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `quantity` int(11) NOT NULL,
  `unit` varchar(45) NOT NULL,
  `codeEAN` int(13) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `TFood`
--

INSERT INTO `TFood` (`idFood`, `name`, `quantity`, `unit`, `codeEAN`) VALUES
(1, 'pomme', 3, 'unit', 1465),
(2, 'lait', 6, 'litre', 148),
(3, 'farine', 500, 'g', 1498);

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
(1, 5648449);

-- --------------------------------------------------------

--
-- Structure de la table `TRecipe`
--

CREATE TABLE `TRecipe` (
  `idRecipe` int(11) NOT NULL,
  `nameProvider` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `TRecipe`
--

INSERT INTO `TRecipe` (`idRecipe`, `nameProvider`) VALUES
(5648449, 'wecook');

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
(1, 1, 8),
(1, 2, 4),
(2, 3, 1);

-- --------------------------------------------------------

--
-- Structure de la table `TUser`
--

CREATE TABLE `TUser` (
  `idUser` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `firstname` varchar(45) NOT NULL,
  `mail` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Contenu de la table `TUser`
--

INSERT INTO `TUser` (`idUser`, `name`, `firstname`, `mail`, `password`) VALUES
(1, 'pecqueux', 'nathan', 'sdqg', 'qsdfg'),
(2, 'lebegue', 'clement', 'qdfh', '<sfg');

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
(1, 1),
(1, 3),
(2, 2);

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
(1, 2);

--
-- Index pour les tables exportées
--

--
-- Index pour la table `TAllergy`
--
ALTER TABLE `TAllergy`
  ADD PRIMARY KEY (`idAllergy`);

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
  ADD PRIMARY KEY (`idFood`);

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
  ADD PRIMARY KEY (`idRecipe`);

--
-- Index pour la table `TStock`
--
ALTER TABLE `TStock`
  ADD PRIMARY KEY (`idUserS`,`idFood`),
  ADD KEY `fk_idFood_idx` (`idFood`);

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
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `TAllergy`
--
ALTER TABLE `TAllergy`
  MODIFY `idAllergy` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT pour la table `TDiet`
--
ALTER TABLE `TDiet`
  MODIFY `idDiet` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT pour la table `TFood`
--
ALTER TABLE `TFood`
  MODIFY `idFood` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT pour la table `TRecipe`
--
ALTER TABLE `TRecipe`
  MODIFY `idRecipe` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5648450;
--
-- AUTO_INCREMENT pour la table `TUser`
--
ALTER TABLE `TUser`
  MODIFY `idUser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `TUserDiet`
--
ALTER TABLE `TUserDiet`
  MODIFY `idUserUD` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `TFavorites`
--
ALTER TABLE `TFavorites`
  ADD CONSTRAINT `fk_idRecipeF` FOREIGN KEY (`idRecipeF`) REFERENCES `TRecipe` (`idRecipe`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_idUserF` FOREIGN KEY (`idUserF`) REFERENCES `TUser` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `THistory`
--
ALTER TABLE `THistory`
  ADD CONSTRAINT `fk_idRecipeH` FOREIGN KEY (`idRecipeH`) REFERENCES `TRecipe` (`idRecipe`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_idUserH` FOREIGN KEY (`idUserH`) REFERENCES `TUser` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION;

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

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
