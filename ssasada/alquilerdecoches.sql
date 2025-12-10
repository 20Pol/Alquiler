-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 08-12-2025 a las 19:32:59
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
-- Base de datos: `alquilerdecoches`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alquiler`
--

CREATE TABLE `alquiler` (
  `id_alquiler` int(11) NOT NULL,
  `fecha_inicio_real` date DEFAULT NULL,
  `fecha_fin_real` date DEFAULT NULL,
  `kilometraje_inicio` int(11) NOT NULL,
  `kilometraje_fin` int(11) DEFAULT NULL,
  `costo_total` decimal(38,2) DEFAULT NULL,
  `id_contrato` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id_cliente` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `dni` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `licencia_conducir` varchar(255) DEFAULT NULL,
  `id_usuario` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`id_cliente`, `nombre`, `apellido`, `dni`, `telefono`, `direccion`, `email`, `licencia_conducir`, `id_usuario`) VALUES
(1, 'Diaz', 'Mamani', '76543218', '978666743', 'Ayacucho', 'diaz@gmail.com', 'Q123456', 1),
(3, 'juanito', 'perez', '76543219', '978420000', 'lima', 'juanito@gmail.com', 'Q12345678', 3),
(4, 'adminn', 'adminn', '76543211', '978420000', 'lima', 'admin1@gmail.com', 'Q12345679', 4),
(5, 'Yancito', 'Diazhino', '76543216', '975123010', 'Arequipa', 'yancito@gmail.com', 'S12345678', 7),
(7, 'Alex', 'Pepe', '76543215', '978654322', 'lima', 'alecito@gmail.com', 'Q12345611', 9),
(9, 'jairo', 'Piña', '76543217', '978420007', 'Ayacucho', 'jairo@gmail.com', 'Q12345618', 11),
(10, 'Ana', 'Huaman', '76643218', '978420300', 'Av. Dolores Arequipa', 'ana@gmail.com', 'Q12335611', 12);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comprobante`
--

CREATE TABLE `comprobante` (
  `id_comprobante` int(11) NOT NULL,
  `tipo_comprobante` enum('BOLETA','FACTURA') NOT NULL,
  `fecha_emision` datetime DEFAULT current_timestamp(),
  `total` decimal(10,2) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `id_alquiler` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contrato`
--

CREATE TABLE `contrato` (
  `id_contrato` int(11) NOT NULL,
  `fecha_firma` date NOT NULL,
  `terminos_condiciones` text DEFAULT NULL,
  `id_reserva` int(11) NOT NULL,
  `id_empleado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dano`
--

CREATE TABLE `dano` (
  `id_dano` int(11) NOT NULL,
  `descripcion` text NOT NULL,
  `costo_estimado` decimal(38,2) DEFAULT NULL,
  `fecha_registro` date DEFAULT NULL,
  `id_alquiler` int(11) NOT NULL,
  `id_empleado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `id_empleado` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `cargo` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  `id_sucursal` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`id_empleado`, `nombre`, `apellido`, `cargo`, `telefono`, `email`, `id_usuario`, `id_sucursal`) VALUES
(1, 'Admin', 'Sistema', 'Administrador', NULL, 'admin@alquiler.com', 5, NULL),
(2, 'Admin2', 'Respaldo', 'Admin', NULL, 'admin2@test.com', 6, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mantenimiento`
--

CREATE TABLE `mantenimiento` (
  `id_mantenimiento` int(11) NOT NULL,
  `fecha_mantenimiento` date DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `descripcion` text DEFAULT NULL,
  `costo` decimal(38,2) DEFAULT NULL,
  `id_vehiculo` int(11) NOT NULL,
  `id_empleado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pago`
--

CREATE TABLE `pago` (
  `id_pago` int(11) NOT NULL,
  `fecha_pago` date DEFAULT NULL,
  `monto` decimal(38,2) DEFAULT NULL,
  `metodo_pago` varchar(255) DEFAULT NULL,
  `referencia` varchar(100) DEFAULT NULL,
  `id_comprobante` int(11) NOT NULL,
  `tipo_comprobante` varchar(255) DEFAULT NULL,
  `id_alquiler` int(11) DEFAULT NULL,
  `id_cliente` int(11) DEFAULT NULL,
  `id_reserva` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reserva`
--

CREATE TABLE `reserva` (
  `id_reserva` int(11) NOT NULL,
  `fecha_reserva` datetime(6) DEFAULT NULL,
  `fecha_inicio` date NOT NULL,
  `fecha_fin` date NOT NULL,
  `costo_estimado` double DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `id_cliente` int(11) NOT NULL,
  `id_vehiculo` int(11) NOT NULL,
  `direccion_entrega` varchar(255) DEFAULT NULL,
  `metodo_entrega` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `reserva`
--

INSERT INTO `reserva` (`id_reserva`, `fecha_reserva`, `fecha_inicio`, `fecha_fin`, `costo_estimado`, `estado`, `id_cliente`, `id_vehiculo`, `direccion_entrega`, `metodo_entrega`) VALUES
(1, '2025-12-08 13:02:06.000000', '2025-12-01', '2025-12-09', 640, 'reservado', 10, 4, NULL, 'tienda'),
(2, '2025-12-08 13:16:19.000000', '2025-12-08', '2025-12-09', 200, 'reservado', 10, 3, NULL, 'tienda');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE `rol` (
  `id_rol` int(11) NOT NULL,
  `nombre_rol` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`id_rol`, `nombre_rol`) VALUES
(1, 'ADMIN'),
(2, 'CLIENTE'),
(3, 'EMPLEADO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `seguro`
--

CREATE TABLE `seguro` (
  `id_seguro` int(11) NOT NULL,
  `compania` varchar(255) DEFAULT NULL,
  `tipo_cobertura` varchar(255) DEFAULT NULL,
  `fecha_inicio` date NOT NULL,
  `fecha_fin` date NOT NULL,
  `costo` decimal(38,2) DEFAULT NULL,
  `id_vehiculo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `estado` enum('ACTIVO','INACTIVO') DEFAULT 'ACTIVO',
  `id_rol` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `username`, `password`, `estado`, `id_rol`) VALUES
(1, 'diaz', '$2a$10$7E2cLTVJE8ubfY4BrwpeD.fiz416bZ2Q0rFAs6PYsUIDqaOcdEvqa', 'ACTIVO', 2),
(2, 'Goku', '$2a$10$vBT6/vzSujpBuI5YecZ9CeI8ggQ0sgPlRR5rSDFS.f5hKrTr8MQYG', 'ACTIVO', 2),
(3, 'juan', '$2a$10$QDeEGHeYOSVH1CEZMgqkTuHFgxKEkQDxqp6KdSZ6CjOtI1exiK3Ii', 'ACTIVO', 2),
(4, 'admin1', '$2a$10$aP/Wsh7hNUumITENbJ.vD.Su8fQI16MWa2KYvoDljJPvL7R4PQOfq', 'ACTIVO', 1),
(5, 'admin', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRkgVduVkyf9kx/1K.O9/yGbZcS', 'ACTIVO', 1),
(6, 'admin2', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOcXBcharda2', 'ACTIVO', 1),
(7, 'Yancito', '$2a$10$Bk8KDk0Gthwh9yntYIxXHuMANxsbi86/fOcW0W4p1D55pd95kFx/.', 'ACTIVO', 1),
(8, 'Alecito', '$2a$10$VBAnM1QXjsq99VBuTYmnfueWQVlbZcZOB1K9by4k4JUHpso4CvVju', 'ACTIVO', 2),
(9, 'Alecit', '$2a$10$BcK5wAUhEErm3Mv/Kul49eqyf8P2D4Y46izEyvm/ZWe0i.b7L4eF2', 'ACTIVO', 2),
(11, 'Jairo', '$2a$10$ge0e7Z3g3OukRzzrY1k3ZObdgWB9SJ1Yn2p4YURijPUD5azTjU7gO', 'ACTIVO', 2),
(12, 'Ana', '$2a$10$Z0cFjdCZU6PIfYE91.uiKOAM2hjixfd6qcBYVNo1PLqku505SvXXS', 'ACTIVO', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehiculo`
--

CREATE TABLE `vehiculo` (
  `id_vehiculo` int(11) NOT NULL,
  `placa` varchar(255) DEFAULT NULL,
  `marca` varchar(255) DEFAULT NULL,
  `modelo` varchar(255) DEFAULT NULL,
  `anio` int(11) DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `kilometraje_actual` int(11) DEFAULT 0,
  `precio_diario` double NOT NULL,
  `asientos` int(11) DEFAULT NULL,
  `tipo_vehiculo` varchar(255) DEFAULT NULL,
  `id_sucursal` int(11) DEFAULT NULL,
  `combustible` varchar(255) DEFAULT NULL,
  `imagen` varchar(255) DEFAULT NULL,
  `transmision` varchar(255) DEFAULT NULL,
  `imagen_url` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `vehiculo`
--

INSERT INTO `vehiculo` (`id_vehiculo`, `placa`, `marca`, `modelo`, `anio`, `color`, `estado`, `kilometraje_actual`, `precio_diario`, `asientos`, `tipo_vehiculo`, `id_sucursal`, `combustible`, `imagen`, `transmision`, `imagen_url`, `descripcion`) VALUES
(1, 'A1B-234', 'Hyundai', 'Tucson', 2024, 'rojo', 'DISPONIBLE', 12000, 70, NULL, 'AUTO', NULL, NULL, NULL, NULL, 'https://www.grupogamboa.com/img/gama/hyundai/tucson/743a527d-b3d8-4f5c-842a-82584db2b9c3.png', 'hermoso auto familiar.'),
(2, 'A1B-235', 'Hyundai', 'SantaFe', 2023, 'blanco', 'DISPONIBLE', 15000, 90, NULL, 'AUTO', NULL, NULL, NULL, NULL, 'https://pictures.dealer.com/s/sonicphilpottmotorshyundai/0676/8b2deee5ed6536381fa29781a8c86011x.jpg', 'Hermoso auto familiar para llevar a tu familia.'),
(3, 'A1B-233', 'Toyota', 'SupraMK5', 2025, 'rojo', 'DISPONIBLE', 5000, 200, NULL, 'DEPORTIVO', NULL, NULL, NULL, NULL, 'https://maxtondesign.com/hpeciai/49a8e305fb30d32ce932f72f3ba97016/eng_pl_Side-Skirts-Diffusers-V-3-Toyota-Supra-Mk5-14414_2.jpg', 'Auto hermoso, único en Arequipa'),
(4, 'A1B-223', 'Toyota', 'Rav4', 2023, 'azul turquesa', 'DISPONIBLE', 30000, 80, 5, 'CAMIONETA', NULL, NULL, NULL, 'MECANICO', 'https://grupopana.com.pe/hs-fs/hubfs/rav4_celeste8W9_01.jpg?width=600&name=rav4_celeste8W9_01.jpg', 'Hermoso auto para ir por la ciudad con la familia.'),
(5, 'V1B-333', 'Kia', 'Picanto', 2024, 'blanco', 'DISPONIBLE', 45000, 60, 5, 'AUTO', NULL, 'GASOLINA', NULL, 'AUTOMATICO', 'https://www.autoland.com.pe/file/collections/kia-picanto-peru-modelo_v1.jpg', 'Auto pequeño para movilizarte por la ciudad');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alquiler`
--
ALTER TABLE `alquiler`
  ADD PRIMARY KEY (`id_alquiler`),
  ADD UNIQUE KEY `id_contrato` (`id_contrato`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id_cliente`),
  ADD UNIQUE KEY `dni` (`dni`),
  ADD UNIQUE KEY `UKm1e6bw9cg5aydhk0rlylo5oom` (`id_usuario`);

--
-- Indices de la tabla `comprobante`
--
ALTER TABLE `comprobante`
  ADD PRIMARY KEY (`id_comprobante`),
  ADD UNIQUE KEY `id_alquiler` (`id_alquiler`),
  ADD KEY `id_cliente` (`id_cliente`);

--
-- Indices de la tabla `contrato`
--
ALTER TABLE `contrato`
  ADD PRIMARY KEY (`id_contrato`),
  ADD UNIQUE KEY `id_reserva` (`id_reserva`),
  ADD KEY `id_empleado` (`id_empleado`);

--
-- Indices de la tabla `dano`
--
ALTER TABLE `dano`
  ADD PRIMARY KEY (`id_dano`),
  ADD KEY `id_alquiler` (`id_alquiler`),
  ADD KEY `id_empleado` (`id_empleado`);

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`id_empleado`),
  ADD KEY `id_usuario` (`id_usuario`),
  ADD KEY `FK5lshn105gw8o1gcw78wysko3u` (`id_sucursal`);

--
-- Indices de la tabla `mantenimiento`
--
ALTER TABLE `mantenimiento`
  ADD PRIMARY KEY (`id_mantenimiento`),
  ADD KEY `id_vehiculo` (`id_vehiculo`),
  ADD KEY `id_empleado` (`id_empleado`);

--
-- Indices de la tabla `pago`
--
ALTER TABLE `pago`
  ADD PRIMARY KEY (`id_pago`),
  ADD KEY `id_comprobante` (`id_comprobante`),
  ADD KEY `FKtmmkd4i8nn9ekei7lurg1019j` (`id_alquiler`),
  ADD KEY `FK2fmwlqws6jmrycdhkn2bl8m0p` (`id_cliente`),
  ADD KEY `FKcf877idupj5b48wjatfaws6sg` (`id_reserva`);

--
-- Indices de la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD PRIMARY KEY (`id_reserva`),
  ADD KEY `id_cliente` (`id_cliente`),
  ADD KEY `id_vehiculo` (`id_vehiculo`);

--
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`id_rol`),
  ADD UNIQUE KEY `nombre_rol` (`nombre_rol`);

--
-- Indices de la tabla `seguro`
--
ALTER TABLE `seguro`
  ADD PRIMARY KEY (`id_seguro`),
  ADD KEY `id_vehiculo` (`id_vehiculo`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `username` (`username`),
  ADD KEY `id_rol` (`id_rol`);

--
-- Indices de la tabla `vehiculo`
--
ALTER TABLE `vehiculo`
  ADD PRIMARY KEY (`id_vehiculo`),
  ADD UNIQUE KEY `placa` (`placa`),
  ADD KEY `FK2ytxlq59206qke84w9hwwdib2` (`id_sucursal`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `alquiler`
--
ALTER TABLE `alquiler`
  MODIFY `id_alquiler` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id_cliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `comprobante`
--
ALTER TABLE `comprobante`
  MODIFY `id_comprobante` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `contrato`
--
ALTER TABLE `contrato`
  MODIFY `id_contrato` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `dano`
--
ALTER TABLE `dano`
  MODIFY `id_dano` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `empleado`
--
ALTER TABLE `empleado`
  MODIFY `id_empleado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `mantenimiento`
--
ALTER TABLE `mantenimiento`
  MODIFY `id_mantenimiento` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pago`
--
ALTER TABLE `pago`
  MODIFY `id_pago` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `reserva`
--
ALTER TABLE `reserva`
  MODIFY `id_reserva` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `rol`
--
ALTER TABLE `rol`
  MODIFY `id_rol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `seguro`
--
ALTER TABLE `seguro`
  MODIFY `id_seguro` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `vehiculo`
--
ALTER TABLE `vehiculo`
  MODIFY `id_vehiculo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `alquiler`
--
ALTER TABLE `alquiler`
  ADD CONSTRAINT `alquiler_ibfk_1` FOREIGN KEY (`id_contrato`) REFERENCES `contrato` (`id_contrato`);

--
-- Filtros para la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `FKetx0tojxf5yevxcyt6qb526x5` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`);

--
-- Filtros para la tabla `comprobante`
--
ALTER TABLE `comprobante`
  ADD CONSTRAINT `comprobante_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`),
  ADD CONSTRAINT `comprobante_ibfk_2` FOREIGN KEY (`id_alquiler`) REFERENCES `alquiler` (`id_alquiler`);

--
-- Filtros para la tabla `contrato`
--
ALTER TABLE `contrato`
  ADD CONSTRAINT `contrato_ibfk_1` FOREIGN KEY (`id_reserva`) REFERENCES `reserva` (`id_reserva`),
  ADD CONSTRAINT `contrato_ibfk_2` FOREIGN KEY (`id_empleado`) REFERENCES `empleado` (`id_empleado`);

--
-- Filtros para la tabla `dano`
--
ALTER TABLE `dano`
  ADD CONSTRAINT `dano_ibfk_1` FOREIGN KEY (`id_alquiler`) REFERENCES `alquiler` (`id_alquiler`),
  ADD CONSTRAINT `dano_ibfk_2` FOREIGN KEY (`id_empleado`) REFERENCES `empleado` (`id_empleado`);

--
-- Filtros para la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD CONSTRAINT `FK5lshn105gw8o1gcw78wysko3u` FOREIGN KEY (`id_sucursal`) REFERENCES `sucursal` (`id_sucursal`),
  ADD CONSTRAINT `empleado_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`);

--
-- Filtros para la tabla `mantenimiento`
--
ALTER TABLE `mantenimiento`
  ADD CONSTRAINT `mantenimiento_ibfk_1` FOREIGN KEY (`id_vehiculo`) REFERENCES `vehiculo` (`id_vehiculo`),
  ADD CONSTRAINT `mantenimiento_ibfk_2` FOREIGN KEY (`id_empleado`) REFERENCES `empleado` (`id_empleado`);

--
-- Filtros para la tabla `pago`
--
ALTER TABLE `pago`
  ADD CONSTRAINT `FK2fmwlqws6jmrycdhkn2bl8m0p` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`),
  ADD CONSTRAINT `FKcf877idupj5b48wjatfaws6sg` FOREIGN KEY (`id_reserva`) REFERENCES `reserva` (`id_reserva`),
  ADD CONSTRAINT `FKtmmkd4i8nn9ekei7lurg1019j` FOREIGN KEY (`id_alquiler`) REFERENCES `alquiler` (`id_alquiler`),
  ADD CONSTRAINT `pago_ibfk_1` FOREIGN KEY (`id_comprobante`) REFERENCES `comprobante` (`id_comprobante`);

--
-- Filtros para la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD CONSTRAINT `reserva_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`) ON DELETE CASCADE,
  ADD CONSTRAINT `reserva_ibfk_2` FOREIGN KEY (`id_vehiculo`) REFERENCES `vehiculo` (`id_vehiculo`);

--
-- Filtros para la tabla `seguro`
--
ALTER TABLE `seguro`
  ADD CONSTRAINT `seguro_ibfk_1` FOREIGN KEY (`id_vehiculo`) REFERENCES `vehiculo` (`id_vehiculo`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_rol`);

--
-- Filtros para la tabla `vehiculo`
--
ALTER TABLE `vehiculo`
  ADD CONSTRAINT `FK2ytxlq59206qke84w9hwwdib2` FOREIGN KEY (`id_sucursal`) REFERENCES `sucursal` (`id_sucursal`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
