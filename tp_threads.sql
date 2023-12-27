-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 28 déc. 2023 à 01:44
-- Version du serveur : 10.4.28-MariaDB
-- Version de PHP : 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `tp_threads`
--

-- --------------------------------------------------------

--
-- Structure de la table `account`
--

CREATE TABLE `account` (
  `ACCOUNTNUMBER` int(11) NOT NULL,
  `ID_PERSON` int(11) NOT NULL,
  `label_account` varchar(50) NOT NULL,
  `BALANCE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `account`
--

INSERT INTO `account` (`ACCOUNTNUMBER`, `ID_PERSON`, `label_account`, `BALANCE`) VALUES
(1, 1, 'main account', 600),
(2, 2, 'dad account', 0),
(3, 1, 'secondary account', 100);

-- --------------------------------------------------------

--
-- Structure de la table `banktransaction`
--

CREATE TABLE `banktransaction` (
  `ID_TRANSACTION` int(11) NOT NULL,
  `TARGETACCOUNTNUMBER` int(11) DEFAULT NULL,
  `SOURCEACCOUNTNUMBER` int(11) DEFAULT NULL,
  `DATE` date NOT NULL,
  `TYPE` varchar(10) NOT NULL,
  `AMOUNT` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `banktransaction`
--

INSERT INTO `banktransaction` (`ID_TRANSACTION`, `TARGETACCOUNTNUMBER`, `SOURCEACCOUNTNUMBER`, `DATE`, `TYPE`, `AMOUNT`) VALUES
(1, NULL, 1, '2023-12-24', 'DEPOT', 300),
(2, NULL, 1, '2023-12-24', 'DEPOT', 200),
(3, NULL, 1, '2023-12-28', 'DEPOT', 300),
(4, NULL, 1, '2023-12-28', 'RETRAIT', 200),
(5, 3, 1, '2023-12-28', 'TRANSFERT', 100),
(6, NULL, 2, '2023-12-28', 'DEPOT', 500),
(7, 1, 2, '2023-12-28', 'TRANSFERT', 500);

-- --------------------------------------------------------

--
-- Structure de la table `person`
--

CREATE TABLE `person` (
  `ID_PERSON` int(11) NOT NULL,
  `FIRSTNAME` varchar(30) NOT NULL,
  `LASTNAME` varchar(30) NOT NULL,
  `EMAIL` varchar(60) NOT NULL,
  `PHONENUMBER` varchar(15) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `person`
--

INSERT INTO `person` (`ID_PERSON`, `FIRSTNAME`, `LASTNAME`, `EMAIL`, `PHONENUMBER`, `password`) VALUES
(1, 'yassine', 'basskar', 'basskar2049@gmail.com', '0613141344', '1234'),
(2, 'yassine', 'moutaoikkil', 'moutaoikkil2049@gmail.com', '0623531245', '1234'),
(3, 'another', 'person', 'person@gmail.com', '0613932354', '1234');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`ACCOUNTNUMBER`),
  ADD KEY `FK_HAVE` (`ID_PERSON`);

--
-- Index pour la table `banktransaction`
--
ALTER TABLE `banktransaction`
  ADD PRIMARY KEY (`ID_TRANSACTION`),
  ADD KEY `FK_SOURCE` (`SOURCEACCOUNTNUMBER`),
  ADD KEY `FK_TARGET` (`TARGETACCOUNTNUMBER`);

--
-- Index pour la table `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`ID_PERSON`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `account`
--
ALTER TABLE `account`
  MODIFY `ACCOUNTNUMBER` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `banktransaction`
--
ALTER TABLE `banktransaction`
  MODIFY `ID_TRANSACTION` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `person`
--
ALTER TABLE `person`
  MODIFY `ID_PERSON` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `FK_HAVE` FOREIGN KEY (`ID_PERSON`) REFERENCES `person` (`ID_PERSON`);

--
-- Contraintes pour la table `banktransaction`
--
ALTER TABLE `banktransaction`
  ADD CONSTRAINT `FK_SOURCE` FOREIGN KEY (`SOURCEACCOUNTNUMBER`) REFERENCES `account` (`ACCOUNTNUMBER`),
  ADD CONSTRAINT `FK_TARGET` FOREIGN KEY (`TARGETACCOUNTNUMBER`) REFERENCES `account` (`ACCOUNTNUMBER`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
