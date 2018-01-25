-- phpMyAdmin SQL Dump
-- version 4.6.6deb4
-- https://www.phpmyadmin.net/
--
-- Client :  localhost:3306
-- Généré le :  Jeu 25 Janvier 2018 à 10:54
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

--
-- Contenu de la table `TAllergy`
--

INSERT INTO `TAllergy` (`idAllergy`, `name`) VALUES
(1, 'poisson'),
(2, 'lait de vache'),
(3, 'soja');

--
-- Contenu de la table `TCategory`
--

INSERT INTO `TCategory` (`idCategory`, `name`, `idSubCategory`) VALUES
(1, 'farine', NULL),
(2, 'farine de blé', 1),
(3, 'farine de blé T110', 2);

--
-- Contenu de la table `TDiet`
--

INSERT INTO `TDiet` (`idDiet`, `name`) VALUES
(1, 'vegan'),
(2, 'vegetarien'),
(3, 'sans gluten');

--
-- Contenu de la table `TFood`
--

INSERT INTO `TFood` (`idFood`, `name`, `quantity`, `idUnitF`, `codeEAN`, `idCategoryF`) VALUES
(16, 'lait', 5, 1, 156, 1),
(17, 'poisson', 1, 3, 95641, NULL),
(18, 'farine de blé T110', 500, 2, 9854, 3),
(22, 'oeufs', 6, 3, 845, NULL);

--
-- Contenu de la table `TRecipe`
--

INSERT INTO `TRecipe` (`idRecipe`, `nameProvider`) VALUES
(5648449, 'wecook');

--
-- Contenu de la table `TStock`
--

INSERT INTO `TStock` (`idUserS`, `idFood`, `quantityS`) VALUES
(1, 16, 1),
(1, 18, 2),
(2, 22, 1);

--
-- Contenu de la table `TUnit`
--

INSERT INTO `TUnit` (`idUnit`, `name`) VALUES
(1, 'litre'),
(2, 'gramme'),
(3, 'unit');

--
-- Contenu de la table `TUser`
--

INSERT INTO `TUser` (`idUser`, `name`, `firstname`, `mail`, `password`) VALUES
(1, 'pecqueux', 'nathan', 'sdqg', 'qsdfg'),
(2, 'lebegue', 'clement', 'qdfh', '<sfg');

--
-- Contenu de la table `TUserAllergy`
--

INSERT INTO `TUserAllergy` (`idUserUA`, `idAllergyUA`) VALUES
(1, 1),
(1, 3),
(2, 2);

--
-- Contenu de la table `TUserDiet`
--

INSERT INTO `TUserDiet` (`idUserUD`, `idDietUD`) VALUES
(1, 2);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
