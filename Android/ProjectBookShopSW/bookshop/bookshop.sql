-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Ven 24 Mars 2017 à 10:37
-- Version du serveur :  5.7.14
-- Version de PHP :  5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `bookshop`
--

DELIMITER $$
--
-- Procédures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_liste_personne` ()  BEGIN
Select * from t_personne ORDER by id DESC;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `get_personne_byConnexion` (IN `login` VARCHAR(128), IN `motdepasse` VARCHAR(128))  BEGIN
	SELECT * FROM t_personne WHERE email LIKE login AND password LIKE motdepasse;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `t_auteur`
--

CREATE TABLE `t_auteur` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prenom` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `t_auteur`
--

INSERT INTO `t_auteur` (`id`, `nom`, `prenom`) VALUES
(1, 'Khadra', 'Yasmina'),
(2, 'Jules', ' Leclerc'),
(3, 'Jean-François', 'Nogier'),
(4, 'Thierry', 'Bouillot'),
(5, 'Marcel', 'Proust');

-- --------------------------------------------------------

--
-- Structure de la table `t_commande`
--

CREATE TABLE `t_commande` (
  `id` int(11) NOT NULL,
  `quantite` int(11) NOT NULL,
  `numero` varchar(75) COLLATE utf8_unicode_ci NOT NULL,
  `personne_id` int(11) DEFAULT NULL,
  `date` date NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `t_librairie`
--

CREATE TABLE `t_librairie` (
  `id` int(11) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `adresse` varchar(256) NOT NULL,
  `ville` varchar(80) NOT NULL,
  `codepostal` int(11) NOT NULL,
  `latitude` float NOT NULL,
  `longitude` float NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `t_livre`
--

CREATE TABLE `t_livre` (
  `id` int(11) NOT NULL,
  `titre` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description` longtext COLLATE utf8_unicode_ci NOT NULL,
  `prix` double NOT NULL,
  `isbn` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `photo` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `t_livre`
--

INSERT INTO `t_livre` (`id`, `titre`, `description`, `prix`, `isbn`, `photo`) VALUES
(1, 'aaa-UX Design et ergonomie des interfaces - 6e éd.', 'aaa-Pour garantir le succès d\'une application celle-ci doit\r\nnon seulement être utile mais également facile à utiliser.\r\nC\'est la raison pour laquelle l\'UX design est devenu une étape\r\nincontournable dans la conception des produits numériques.\r\nCet ouvrage de référence s\'adresse à tous les professionnels\r\nimpliqués dans la conception et le développement d\'applications.\r\nCe guide a été lu, relu, exploité et utilisé par de très nombreux chefs\r\nde projet, développeurs et concepteurs d\'interface depuis sa\r\npremière édition en 2001. Délibérément pragmatique, il présente\r\nune méthode claire et efficace pour « penser UX design » et vous\r\naider à trouver des solutions pour vos projets.\r\nÀ travers de nombreux exemples, vous y découvrirez des réponses\r\naux questions que vous vous posez au fur et à mesure de la\r\nréalisation de vos applications, depuis le ciblage des utilisateurs,\r\njusqu\'aux choix graphiques, en passant par la conception du\r\nsystème de navigation et des éléments d\'interaction.\r\nCette 6e édition apporte des compléments et des mises à jour\r\nsur les applications ubiquitaires (multi-écrans), le web responsive,\r\nou la conception dite mobile first. ', 350, '9782100754618-aaa', 'https://aaa-ec56229aec51f1baff1d-185c3068e22352c56024573e929788ff.ssl.cf1.rackcdn.com/attachments/large/4/5/0/004396450.jpg');

-- --------------------------------------------------------

--
-- Structure de la table `t_livre_auteur`
--

CREATE TABLE `t_livre_auteur` (
  `id` int(11) NOT NULL,
  `livre_id` int(11) NOT NULL,
  `auteur_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `t_livre_auteur`
--

INSERT INTO `t_livre_auteur` (`id`, `livre_id`, `auteur_id`) VALUES
(4, 1, 4),
(3, 1, 5),
(5, 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `t_livre_commande`
--

CREATE TABLE `t_livre_commande` (
  `id` int(11) NOT NULL,
  `livre_id` int(11) NOT NULL,
  `commande_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `t_livre_librairie`
--

CREATE TABLE `t_livre_librairie` (
  `id` int(11) NOT NULL,
  `livre_id` int(11) NOT NULL,
  `librairie_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `t_livre_theme`
--

CREATE TABLE `t_livre_theme` (
  `id` int(11) NOT NULL,
  `livre_id` int(11) NOT NULL,
  `theme_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `t_livre_theme`
--

INSERT INTO `t_livre_theme` (`id`, `livre_id`, `theme_id`) VALUES
(2, 1, 3),
(3, 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `t_personne`
--

CREATE TABLE `t_personne` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prenom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `civilite` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `photo` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `t_personne`
--

INSERT INTO `t_personne` (`id`, `nom`, `prenom`, `email`, `password`, `civilite`, `photo`, `date`) VALUES
(2, 'jeje', 'freddy', 'freddy@gmail.com', '51eeed7251f3f56288ee554afaa1028b7fbc3daacc4a952be4ae8d18ddaf3320', 'Monsieur', '', '2017-03-22 14:56:11'),
(3, 'ABIB', 'yanis', 'abib.yanis@gmail.com', 'b6d4a89ccde3fb8fc69865ac105801287867cf735adf0b8bbca86ee9186f7b64', 'Monsieur', '20170323153316-Penguins.jpg', '2017-03-22 15:02:38');

-- --------------------------------------------------------

--
-- Structure de la table `t_stock`
--

CREATE TABLE `t_stock` (
  `id` int(11) NOT NULL,
  `quantite` int(11) NOT NULL,
  `livre_id` int(11) DEFAULT NULL,
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `t_stock`
--

INSERT INTO `t_stock` (`id`, `quantite`, `livre_id`, `date`) VALUES
(1, 120, 1, '2017-03-24 09:59:16');

-- --------------------------------------------------------

--
-- Structure de la table `t_theme`
--

CREATE TABLE `t_theme` (
  `id` int(11) NOT NULL,
  `libelle` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `t_theme`
--

INSERT INTO `t_theme` (`id`, `libelle`) VALUES
(1, 'Littérature'),
(2, 'Sciences et Techniques'),
(3, 'Arts et beaux livres');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `t_auteur`
--
ALTER TABLE `t_auteur`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `t_commande`
--
ALTER TABLE `t_commande`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_commande` (`personne_id`);

--
-- Index pour la table `t_librairie`
--
ALTER TABLE `t_librairie`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `t_livre`
--
ALTER TABLE `t_livre`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `t_livre_auteur`
--
ALTER TABLE `t_livre_auteur`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_livre_auteur_1` (`livre_id`),
  ADD KEY `idx_livre_auteur_2` (`auteur_id`);

--
-- Index pour la table `t_livre_commande`
--
ALTER TABLE `t_livre_commande`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_livre_commande_1` (`livre_id`),
  ADD KEY `idx_livre_commande_2` (`commande_id`);

--
-- Index pour la table `t_livre_librairie`
--
ALTER TABLE `t_livre_librairie`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_livre_librairie_1` (`livre_id`),
  ADD KEY `fk_livre_librairie_2` (`librairie_id`);

--
-- Index pour la table `t_livre_theme`
--
ALTER TABLE `t_livre_theme`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_livre_theme_1` (`livre_id`),
  ADD KEY `idx_livre_theme_2` (`theme_id`);

--
-- Index pour la table `t_personne`
--
ALTER TABLE `t_personne`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `t_stock`
--
ALTER TABLE `t_stock`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idx_stock` (`livre_id`);

--
-- Index pour la table `t_theme`
--
ALTER TABLE `t_theme`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `t_auteur`
--
ALTER TABLE `t_auteur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT pour la table `t_commande`
--
ALTER TABLE `t_commande`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `t_librairie`
--
ALTER TABLE `t_librairie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `t_livre`
--
ALTER TABLE `t_livre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `t_livre_auteur`
--
ALTER TABLE `t_livre_auteur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT pour la table `t_livre_commande`
--
ALTER TABLE `t_livre_commande`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `t_livre_librairie`
--
ALTER TABLE `t_livre_librairie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `t_livre_theme`
--
ALTER TABLE `t_livre_theme`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT pour la table `t_personne`
--
ALTER TABLE `t_personne`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT pour la table `t_stock`
--
ALTER TABLE `t_stock`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `t_theme`
--
ALTER TABLE `t_theme`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
