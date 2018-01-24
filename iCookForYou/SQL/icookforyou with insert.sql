-- phpMyAdmin SQL Dump
-- version 4.6.6deb4
-- https://www.phpmyadmin.net/
--
-- Client :  localhost:3306
-- Généré le :  Mer 24 Janvier 2018 à 10:52
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
-- Structure de la table `TAliment`
--
-- Création :  Mer 24 Janvier 2018 à 09:15
--

CREATE TABLE `TAliment` (
  `idAliment` int(11) NOT NULL,
  `nom` varchar(45) NOT NULL,
  `quantite` int(11) NOT NULL,
  `unite` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELATIONS POUR LA TABLE `TAliment`:
--

--
-- Contenu de la table `TAliment`
--

INSERT INTO `TAliment` (`idAliment`, `nom`, `quantite`, `unite`) VALUES
(1, 'lait', 1, 'litre'),
(2, 'oeuf', 6, 'unit'),
(3, 'farine', 500, 'g');

-- --------------------------------------------------------

--
-- Structure de la table `TAllergie`
--
-- Création :  Mer 24 Janvier 2018 à 08:36
--

CREATE TABLE `TAllergie` (
  `idTAllergie` int(11) NOT NULL,
  `nom` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELATIONS POUR LA TABLE `TAllergie`:
--

--
-- Contenu de la table `TAllergie`
--

INSERT INTO `TAllergie` (`idTAllergie`, `nom`) VALUES
(5, 'cacahuete');

-- --------------------------------------------------------

--
-- Structure de la table `TFavoris`
--
-- Création :  Mer 24 Janvier 2018 à 09:17
--

CREATE TABLE `TFavoris` (
  `idUserF` int(11) NOT NULL,
  `idRecetteF` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELATIONS POUR LA TABLE `TFavoris`:
--   `idUserF`
--       `TUser` -> `idTUser`
--

--
-- Contenu de la table `TFavoris`
--

INSERT INTO `TFavoris` (`idUserF`, `idRecetteF`) VALUES
(0, 4589);

-- --------------------------------------------------------

--
-- Structure de la table `THistorique`
--
-- Création :  Mer 24 Janvier 2018 à 09:17
--

CREATE TABLE `THistorique` (
  `idUserH` int(11) NOT NULL,
  `idRecetteH` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELATIONS POUR LA TABLE `THistorique`:
--   `idUserH`
--       `TUser` -> `idTUser`
--

--
-- Contenu de la table `THistorique`
--

INSERT INTO `THistorique` (`idUserH`, `idRecetteH`) VALUES
(0, 6875);

-- --------------------------------------------------------

--
-- Structure de la table `TRegime`
--
-- Création :  Mer 24 Janvier 2018 à 08:46
--

CREATE TABLE `TRegime` (
  `idTRegime` int(11) NOT NULL,
  `nom` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELATIONS POUR LA TABLE `TRegime`:
--

--
-- Contenu de la table `TRegime`
--

INSERT INTO `TRegime` (`idTRegime`, `nom`) VALUES
(1, 'vegetarien'),
(2, 'vegan'),
(3, 'sans gluten');

-- --------------------------------------------------------

--
-- Structure de la table `TStock`
--
-- Création :  Mer 24 Janvier 2018 à 09:50
--

CREATE TABLE `TStock` (
  `idUserS` int(11) NOT NULL,
  `idAliment` int(11) NOT NULL,
  `stock` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELATIONS POUR LA TABLE `TStock`:
--   `idAliment`
--       `TAliment` -> `idAliment`
--   `idUserS`
--       `TUser` -> `idTUser`
--

--
-- Contenu de la table `TStock`
--

INSERT INTO `TStock` (`idUserS`, `idAliment`, `stock`) VALUES
(0, 1, 6),
(0, 2, 6),
(0, 3, 1);

-- --------------------------------------------------------

--
-- Structure de la table `TUser`
--
-- Création :  Mer 24 Janvier 2018 à 08:42
--

CREATE TABLE `TUser` (
  `idTUser` int(11) NOT NULL,
  `nom` varchar(45) NOT NULL,
  `prenom` varchar(45) NOT NULL,
  `mail` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELATIONS POUR LA TABLE `TUser`:
--

--
-- Contenu de la table `TUser`
--

INSERT INTO `TUser` (`idTUser`, `nom`, `prenom`, `mail`, `password`) VALUES
(0, 'lebegue', 'clement', 'clement@gmail.com', 'clement');

-- --------------------------------------------------------

--
-- Structure de la table `TUserAllergie`
--
-- Création :  Mer 24 Janvier 2018 à 08:53
--

CREATE TABLE `TUserAllergie` (
  `idTUserUA` int(11) NOT NULL,
  `idTAllergieUA` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELATIONS POUR LA TABLE `TUserAllergie`:
--   `idTAllergieUA`
--       `TAllergie` -> `idTAllergie`
--   `idTUserUA`
--       `TUser` -> `idTUser`
--

--
-- Contenu de la table `TUserAllergie`
--

INSERT INTO `TUserAllergie` (`idTUserUA`, `idTAllergieUA`) VALUES
(0, 5);

-- --------------------------------------------------------

--
-- Structure de la table `TUserRegime`
--
-- Création :  Mer 24 Janvier 2018 à 09:06
--

CREATE TABLE `TUserRegime` (
  `idTUserUR` int(11) NOT NULL,
  `idTRegimeUR` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELATIONS POUR LA TABLE `TUserRegime`:
--   `idTRegimeUR`
--       `TRegime` -> `idTRegime`
--   `idTUserUR`
--       `TUser` -> `idTUser`
--

--
-- Contenu de la table `TUserRegime`
--

INSERT INTO `TUserRegime` (`idTUserUR`, `idTRegimeUR`) VALUES
(0, 1);

--
-- Index pour les tables exportées
--

--
-- Index pour la table `TAliment`
--
ALTER TABLE `TAliment`
  ADD PRIMARY KEY (`idAliment`);

--
-- Index pour la table `TAllergie`
--
ALTER TABLE `TAllergie`
  ADD PRIMARY KEY (`idTAllergie`);

--
-- Index pour la table `TFavoris`
--
ALTER TABLE `TFavoris`
  ADD PRIMARY KEY (`idUserF`);

--
-- Index pour la table `THistorique`
--
ALTER TABLE `THistorique`
  ADD PRIMARY KEY (`idUserH`);

--
-- Index pour la table `TRegime`
--
ALTER TABLE `TRegime`
  ADD PRIMARY KEY (`idTRegime`);

--
-- Index pour la table `TStock`
--
ALTER TABLE `TStock`
  ADD PRIMARY KEY (`idUserS`,`idAliment`),
  ADD KEY `fk_idAlimentS_idx` (`idAliment`);

--
-- Index pour la table `TUser`
--
ALTER TABLE `TUser`
  ADD PRIMARY KEY (`idTUser`);

--
-- Index pour la table `TUserAllergie`
--
ALTER TABLE `TUserAllergie`
  ADD PRIMARY KEY (`idTUserUA`,`idTAllergieUA`),
  ADD KEY `fk_idAllergieUA_idx` (`idTAllergieUA`);

--
-- Index pour la table `TUserRegime`
--
ALTER TABLE `TUserRegime`
  ADD PRIMARY KEY (`idTUserUR`,`idTRegimeUR`),
  ADD KEY `fk_idRegimeUR_idx` (`idTRegimeUR`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `TAliment`
--
ALTER TABLE `TAliment`
  MODIFY `idAliment` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT pour la table `TAllergie`
--
ALTER TABLE `TAllergie`
  MODIFY `idTAllergie` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT pour la table `TRegime`
--
ALTER TABLE `TRegime`
  MODIFY `idTRegime` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `TFavoris`
--
ALTER TABLE `TFavoris`
  ADD CONSTRAINT `fk_idUserF` FOREIGN KEY (`idUserF`) REFERENCES `TUser` (`idTUser`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `THistorique`
--
ALTER TABLE `THistorique`
  ADD CONSTRAINT `fk_idUserH` FOREIGN KEY (`idUserH`) REFERENCES `TUser` (`idTUser`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `TStock`
--
ALTER TABLE `TStock`
  ADD CONSTRAINT `fk_idAlimentS` FOREIGN KEY (`idAliment`) REFERENCES `TAliment` (`idAliment`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_idUserS` FOREIGN KEY (`idUserS`) REFERENCES `TUser` (`idTUser`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `TUserAllergie`
--
ALTER TABLE `TUserAllergie`
  ADD CONSTRAINT `fk_idAllergieUA` FOREIGN KEY (`idTAllergieUA`) REFERENCES `TAllergie` (`idTAllergie`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_idUserUA` FOREIGN KEY (`idTUserUA`) REFERENCES `TUser` (`idTUser`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `TUserRegime`
--
ALTER TABLE `TUserRegime`
  ADD CONSTRAINT `fk_idRegimeUR` FOREIGN KEY (`idTRegimeUR`) REFERENCES `TRegime` (`idTRegime`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_idUserUR` FOREIGN KEY (`idTUserUR`) REFERENCES `TUser` (`idTUser`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
