-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-05-2025 a las 05:38:53
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `parking`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `registro`
--

CREATE TABLE `registro` (
  `idRegistro` int(11) NOT NULL,
  `fecha` varchar(45) DEFAULT NULL,
  `Visitante_Cédula1` int(11) DEFAULT NULL,
  `Vehiculo_Placa1` varchar(45) DEFAULT NULL,
  `Tarifa_idTarifa` int(11) DEFAULT NULL,
  `Hora_de_ingreso` time DEFAULT NULL,
  `Hora_de_salida` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tarifa`
--

CREATE TABLE `tarifa` (
  `idTarifa` int(11) NOT NULL,
  `anio` varchar(45) DEFAULT NULL,
  `Tipo_de_Vehiculo_idTipo` int(11) DEFAULT NULL,
  `TarifaPlena` time DEFAULT NULL,
  `TarifaPorMinuto` time DEFAULT NULL,
  `TarifaMinCarro` time DEFAULT NULL,
  `TarifaPlenCarro` time DEFAULT NULL,
  `TarifaMinMoto` time DEFAULT NULL,
  `TarifaPlenMoto` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehiculo`
--

CREATE TABLE `vehiculo` (
  `Placa` varchar(45) NOT NULL,
  `Marca` varchar(45) DEFAULT NULL,
  `Modelo` varchar(45) DEFAULT NULL,
  `Tipo_de_Vehiculo_idTipo` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `vehiculo`
--

INSERT INTO `vehiculo` (`Placa`, `Marca`, `Modelo`, `Tipo_de_Vehiculo_idTipo`) VALUES
('JHG', 'audi', 'Caratumba', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `visitante`
--

CREATE TABLE `visitante` (
  `Cedula` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `registro`
--
ALTER TABLE `registro`
  ADD PRIMARY KEY (`idRegistro`),
  ADD KEY `Visitante_Cédula1` (`Visitante_Cédula1`),
  ADD KEY `Vehiculo_Placa1` (`Vehiculo_Placa1`),
  ADD KEY `Tarifa_idTarifa` (`Tarifa_idTarifa`);

--
-- Indices de la tabla `tarifa`
--
ALTER TABLE `tarifa`
  ADD PRIMARY KEY (`idTarifa`);

--
-- Indices de la tabla `vehiculo`
--
ALTER TABLE `vehiculo`
  ADD PRIMARY KEY (`Placa`);

--
-- Indices de la tabla `visitante`
--
ALTER TABLE `visitante`
  ADD PRIMARY KEY (`Cedula`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `registro`
--
ALTER TABLE `registro`
  ADD CONSTRAINT `registro_ibfk_1` FOREIGN KEY (`Visitante_Cédula1`) REFERENCES `visitante` (`Cedula`),
  ADD CONSTRAINT `registro_ibfk_2` FOREIGN KEY (`Vehiculo_Placa1`) REFERENCES `vehiculo` (`Placa`),
  ADD CONSTRAINT `registro_ibfk_3` FOREIGN KEY (`Tarifa_idTarifa`) REFERENCES `tarifa` (`idTarifa`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
