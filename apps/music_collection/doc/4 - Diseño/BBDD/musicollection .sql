-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 12-11-2018 a las 10:23:52
-- Versión del servidor: 5.7.14
-- Versión de PHP: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `musicollection`
--
CREATE DATABASE IF NOT EXISTS `musicollection` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `musicollection`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `artistas`
--

CREATE TABLE `artistas` (
  `idArtista` bigint(20) UNSIGNED NOT NULL,
  `nombreArtista` varchar(30) COLLATE utf8_bin NOT NULL,
  `añoArtista` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `artistas`
--

INSERT INTO `artistas` (`idArtista`, `nombreArtista`, `añoArtista`) VALUES
(1, 'Freddy Mercury', 1970),
(2, 'David Bowie', 1965),
(3, 'Robert Smith', 1965),
(4, 'Bruno Mars', 1990),
(5, 'Maluma', 1980),
(6, 'Mick Jagger', 1955),
(7, 'Bruce Springsteen', 1960),
(8, 'Nach Scratch', 1972),
(9, 'Bob Dylan', 1960),
(10, 'Jennifer Lopez', 1972),
(16, 'Paul McCartney', 1950),
(17, 'John Lennon', 1951),
(18, 'Steven Patrick Morrissey', 1959),
(19, 'Joe Strummer', 1976),
(20, 'Mick Jones', 1976),
(21, 'Rob Harper', 1976),
(22, 'Peret', 1951),
(23, 'Santiago Auserón', 1960),
(24, 'Loquillo', 1965),
(25, 'Alaska', 1963),
(27, 'Tino Casal', 1965);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `artistasestilos`
--

CREATE TABLE `artistasestilos` (
  `idArt` bigint(20) UNSIGNED NOT NULL,
  `idEst` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `artistasestilos`
--

INSERT INTO `artistasestilos` (`idArt`, `idEst`) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 6),
(5, 4),
(6, 1),
(7, 2),
(8, 3),
(9, 6),
(10, 4),
(16, 1),
(17, 1),
(18, 6),
(19, 2),
(20, 2),
(21, 2),
(24, 2),
(25, 1),
(27, 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estilos`
--

CREATE TABLE `estilos` (
  `idEstilo` bigint(20) UNSIGNED NOT NULL,
  `nombreEstilo` varchar(20) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `estilos`
--

INSERT INTO `estilos` (`idEstilo`, `nombreEstilo`) VALUES
(1, 'Pop'),
(2, 'Rock'),
(3, 'Hip Hop'),
(4, 'Latino'),
(5, 'Indie'),
(6, 'Alternativo'),
(7, 'Rumba'),
(8, 'Jazz');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gente`
--

CREATE TABLE `gente` (
  `idGente` bigint(20) UNSIGNED NOT NULL,
  `nombreGente` varchar(30) COLLATE utf8_bin NOT NULL,
  `añoGente` int(10) UNSIGNED NOT NULL,
  `idArtistaGente` bigint(20) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `gente`
--

INSERT INTO `gente` (`idGente`, `nombreGente`, `añoGente`, `idArtistaGente`) VALUES
(11, 'Brian May', 1955, 1),
(12, 'Roger Taylor', 1956, 6),
(21, 'Charles Morricone', 1966, 4),
(22, 'Maximilian Morgan', 1967, NULL),
(31, 'Dick Mcnamara', 1956, 1),
(32, 'Mike Horney', 1966, 1),
(41, 'John Sullivan', 1967, NULL),
(42, 'Michael Strump', 1960, NULL),
(51, 'Antonio Cardenas', 1985, NULL),
(52, 'Oswaldo Exposito', 1984, NULL),
(61, 'Richard Wright', 1960, NULL),
(62, 'Syd Barrett ', 1966, NULL),
(71, 'Clarence Clemons', 1967, 7),
(72, 'Tom Morello', 1967, NULL),
(81, 'Kase O', 1966, NULL),
(82, 'Kuba Luga', 1956, NULL),
(91, 'Tedham Porterhouse', 1956, NULL),
(92, 'Mathew Anderson', 1960, NULL),
(101, 'Vanessa Ezequiel', 1999, NULL),
(102, 'Leticia Arroyo', 1998, NULL),
(104, 'Ian Lord', 1961, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `relacionartistas`
--

CREATE TABLE `relacionartistas` (
  `idArtista1` bigint(20) UNSIGNED NOT NULL,
  `idArtista2` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `relacionartistas`
--

INSERT INTO `relacionartistas` (`idArtista1`, `idArtista2`) VALUES
(1, 2),
(2, 1),
(4, 10),
(5, 10),
(10, 4),
(10, 5),
(16, 17),
(17, 16),
(19, 20),
(19, 21),
(20, 19),
(21, 19),
(23, 27),
(24, 25),
(25, 24),
(27, 23);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `artistas`
--
ALTER TABLE `artistas`
  ADD PRIMARY KEY (`idArtista`) USING BTREE;

--
-- Indices de la tabla `artistasestilos`
--
ALTER TABLE `artistasestilos`
  ADD KEY `idArt` (`idArt`),
  ADD KEY `idEst` (`idEst`);

--
-- Indices de la tabla `estilos`
--
ALTER TABLE `estilos`
  ADD PRIMARY KEY (`idEstilo`) USING BTREE;

--
-- Indices de la tabla `gente`
--
ALTER TABLE `gente`
  ADD PRIMARY KEY (`idGente`),
  ADD KEY `IndiceArtista` (`idArtistaGente`);

--
-- Indices de la tabla `relacionartistas`
--
ALTER TABLE `relacionartistas`
  ADD PRIMARY KEY (`idArtista1`,`idArtista2`),
  ADD KEY `idArtista1` (`idArtista1`),
  ADD KEY `idArtista2` (`idArtista2`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `artistas`
--
ALTER TABLE `artistas`
  MODIFY `idArtista` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;
--
-- AUTO_INCREMENT de la tabla `estilos`
--
ALTER TABLE `estilos`
  MODIFY `idEstilo` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT de la tabla `gente`
--
ALTER TABLE `gente`
  MODIFY `idGente` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=105;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `artistasestilos`
--
ALTER TABLE `artistasestilos`
  ADD CONSTRAINT `artistasestilos_ibfk_1` FOREIGN KEY (`idArt`) REFERENCES `artistas` (`idArtista`) ON UPDATE CASCADE,
  ADD CONSTRAINT `artistasestilos_ibfk_2` FOREIGN KEY (`idEst`) REFERENCES `estilos` (`idEstilo`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `gente`
--
ALTER TABLE `gente`
  ADD CONSTRAINT `gente_ibfk_1` FOREIGN KEY (`idArtistaGente`) REFERENCES `artistas` (`idArtista`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `relacionartistas`
--
ALTER TABLE `relacionartistas`
  ADD CONSTRAINT `relacionartistas_ibfk_1` FOREIGN KEY (`idArtista1`) REFERENCES `artistas` (`idArtista`) ON UPDATE CASCADE,
  ADD CONSTRAINT `relacionartistas_ibfk_2` FOREIGN KEY (`idArtista2`) REFERENCES `artistas` (`idArtista`) ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
