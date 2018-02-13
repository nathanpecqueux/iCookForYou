-- phpMyAdmin SQL Dump
-- version 4.6.6deb4
-- https://www.phpmyadmin.net/
--
-- Client :  localhost:3306
-- Généré le :  Mer 07 Février 2018 à 15:42
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
(1, 'arachides'),
(2, 'fruit à écales'),
(3, 'lait de vache'),
(4, 'oeufs'),
(5, 'poissons'),
(6, 'fruits de mer');

--
-- Contenu de la table `TCategory`
--

INSERT INTO `TCategory` (`idCategory`, `name`, `idParentCategory`) VALUES
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
-- Contenu de la table `TFavorites`
--

INSERT INTO `TFavorites` (`idUserF`, `idRecipeF`) VALUES
(1, 16969),
(2, 16969);

--
-- Contenu de la table `TFood`
--

INSERT INTO `TFood` (`idFood`, `name`, `quantity`, `idUnitF`, `codeEAN`, `idCategoryF`) VALUES
(1, 'lait', 1, 1, 156, 1),
(2, 'poisson', 1, 3, 95641, NULL),
(3, 'farine de blé T110', 500, 2, 9854, 3),
(4, 'oeufs', 6, 3, 845, NULL);

--
-- Contenu de la table `THistory`
--

INSERT INTO `THistory` (`idUserH`, `idRecipeH`) VALUES
(2, 16969);

--
-- Contenu de la table `TRecipe`
--

INSERT INTO `TRecipe` (`idRecipe`, `idProviderR`) VALUES
(16969, 2);

--
-- Contenu de la table `TRecipeProvider`
--

INSERT INTO `TRecipeProvider` (`idRecipeProvider`, `name`, `url`) VALUES
(1, 'wecook', 'http://api.wecook.fr/'),
(2, 'marmiton', 'http://www.marmiton.org/');

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

--
-- Contenu de la table `TTool`
--

INSERT INTO `TTool` (`idTool`, `name`) VALUES
(1, 'four'),
(2, 'thermomix');

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

INSERT INTO `TUser` (`idUser`, `lastName`, `firstName`, `mail`, `password`, `weight(kg)`, `size(cm)`, `age`, `sex`, `physicalActivity`) VALUES
(1, 'Pecqueux', 'nathan', 'nathan@gmail.com', 'nathan', '65.00', 178, 22, 'man', 'active'),
(2, 'Lebegue', 'Clement', 'clement@hotmail.fr', 'clement', '80.00', 190, 25, 'woman', 'athlete'),
(3, 'Bomy', 'Francois', 'fr@gmail.com', 'franfran', '120.00', 180, 23, 'man', 'sedentary');

--
-- Contenu de la table `TUserAllergy`
--

INSERT INTO `TUserAllergy` (`idUserUA`, `idAllergyUA`) VALUES
(1, 2),
(1, 5),
(2, 1);

--
-- Contenu de la table `TUserDiet`
--

INSERT INTO `TUserDiet` (`idUserUD`, `idDietUD`) VALUES
(2, 1);

--
-- Contenu de la table `TUserTool`
--

INSERT INTO `TUserTool` (`idUserUT`, `idToolUT`) VALUES
(1, 1),
(1, 2),
(2, 2);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
