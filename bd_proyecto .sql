-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 14-11-2020 a las 22:03:36
-- Versión del servidor: 5.7.28-log
-- Versión de PHP: 7.4.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bd_proyecto`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `idCliente` int(11) NOT NULL,
  `Cui` varchar(30) DEFAULT NULL,
  `Nombre` varchar(100) DEFAULT NULL,
  `Direccion` varchar(100) DEFAULT NULL,
  `Estado` varchar(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`idCliente`, `Cui`, `Nombre`, `Direccion`, `Estado`) VALUES
(1, '2305688580101', 'Jorge Roberto Contreras', '12 Av. 12-33, Zona 1', '1'),
(2, '1900898980201', 'Luis Rolando Soto', '29 Calle 3-34, Zona 2', '2'),
(3, '0023894580101', 'Elmer Estrada', '1ra. Av. 11-23, Zona 11', '1'),
(4, '1023344540101', 'Sandra Godínez', '2da Calle 2-23, Zona 5', '1'),
(5, '1234564560101', 'Ana González', '3ra. Av. 4-12, Zona 8', '1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_ventas`
--

CREATE TABLE `detalle_ventas` (
  `IdDetalleVentas` int(11) NOT NULL,
  `Producto_idProducto` int(11) NOT NULL,
  `Ventas_IdVentas` int(11) NOT NULL,
  `Cantidad` int(11) DEFAULT NULL,
  `PrecioVenta` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `detalle_ventas`
--

INSERT INTO `detalle_ventas` (`IdDetalleVentas`, `Producto_idProducto`, `Ventas_IdVentas`, `Cantidad`, `PrecioVenta`) VALUES
(1, 2, 2, 1, 150),
(2, 3, 2, 10, 15),
(3, 2, 3, 5, 150);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `idProducto` int(11) NOT NULL,
  `Nombre` varchar(45) DEFAULT NULL,
  `Precio` double DEFAULT NULL,
  `Stock` int(11) DEFAULT NULL,
  `Estado` varchar(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`idProducto`, `Nombre`, `Precio`, `Stock`, `Estado`) VALUES
(1, 'Camisa', 80, 45, '1'),
(2, 'Pantalon', 150, 45, '2'),
(3, 'Calcetines', 15, 35, '1'),
(4, 'Playera', 35, 25, '2'),
(5, 'Sandalia', 300, 15, '1'),
(6, 'Abrigos', 500, 100, '1'),
(7, 'Sueter', 200, 150, '1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vendedor`
--

CREATE TABLE `vendedor` (
  `idVendedor` int(11) NOT NULL,
  `cui` varchar(30) DEFAULT NULL,
  `nombre` varchar(125) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `estado` varchar(5) DEFAULT NULL,
  `usuario` varchar(50) DEFAULT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `vendedor`
--

INSERT INTO `vendedor` (`idVendedor`, `cui`, `nombre`, `telefono`, `estado`, `usuario`, `password`) VALUES
(1, '2305988580101', 'Pedro López', '52365656', '1', 'pedro', '12345'),
(2, '2805958580101', 'Juana Ruiz', '52698978', '2', 'juana', '12345'),
(3, '5624859850101', 'Oscar Velásquez', '49898952', '1', 'oscar', '12345'),
(4, '6950528520101', 'Susana López', '42857802', '2', 'susana', '12345'),
(5, '2305988585555', 'Rodrigo Interiano', NULL, NULL, 'rodrigo', '12345'),
(6, '2805958586666', 'Luis Gomez', NULL, NULL, 'luis', '12345'),
(7, '1234578', 'Raul Agreda', '1234578', '1', 'raul', '12345'),
(8, '1234578', 'Daniel Cabrera', '1234578', '1', 'daniel', '12345');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

CREATE TABLE `ventas` (
  `IdVentas` int(11) NOT NULL,
  `Cliente_idCliente` int(11) NOT NULL,
  `Vendedor_idVendedor` int(11) NOT NULL,
  `NumeroVentas` varchar(244) DEFAULT NULL,
  `FechaVentas` date DEFAULT NULL,
  `Monto` double DEFAULT NULL,
  `Estado` varchar(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `ventas`
--

INSERT INTO `ventas` (`IdVentas`, `Cliente_idCliente`, `Vendedor_idVendedor`, `NumeroVentas`, `FechaVentas`, `Monto`, `Estado`) VALUES
(1, 4, 1, '', '2020-10-11', 300, '1'),
(2, 1, 1, '2', '2020-10-12', 300, '1'),
(3, 1, 1, '3', '2020-10-12', 750, '1');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`idCliente`);

--
-- Indices de la tabla `detalle_ventas`
--
ALTER TABLE `detalle_ventas`
  ADD PRIMARY KEY (`IdDetalleVentas`),
  ADD KEY `fk_Producto_has_Ventas_Producto1` (`Producto_idProducto`),
  ADD KEY `fk_Producto_has_Ventas_Ventas1` (`Ventas_IdVentas`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`idProducto`);

--
-- Indices de la tabla `vendedor`
--
ALTER TABLE `vendedor`
  ADD PRIMARY KEY (`idVendedor`);

--
-- Indices de la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`IdVentas`),
  ADD KEY `fk_Cliente_has_Vendedor_Cliente` (`Cliente_idCliente`),
  ADD KEY `fk_Cliente_has_Vendedor_Vendedor1` (`Vendedor_idVendedor`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `detalle_ventas`
--
ALTER TABLE `detalle_ventas`
  MODIFY `IdDetalleVentas` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `detalle_ventas`
--
ALTER TABLE `detalle_ventas`
  ADD CONSTRAINT `fk_Producto_has_Ventas_Producto1` FOREIGN KEY (`Producto_idProducto`) REFERENCES `producto` (`idProducto`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Producto_has_Ventas_Ventas1` FOREIGN KEY (`Ventas_IdVentas`) REFERENCES `ventas` (`IdVentas`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD CONSTRAINT `fk_Cliente_has_Vendedor_Cliente` FOREIGN KEY (`Cliente_idCliente`) REFERENCES `cliente` (`idCliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Cliente_has_Vendedor_Vendedor1` FOREIGN KEY (`Vendedor_idVendedor`) REFERENCES `vendedor` (`idVendedor`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
