CREATE TABLE `Celulares`(
    `id_celular` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `marca` VARCHAR(255) NOT NULL,
    `modelo` VARCHAR(255) NOT NULL,
    `sistema_operativo` VARCHAR(255) NOT NULL,
    `gama` ENUM('alta', 'media', 'baja') NOT NULL,
    `precio` DECIMAL(8, 2) NOT NULL,
    `stock` INT NOT NULL
);
CREATE TABLE `Clientes`(
    `id_cliente` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `nombre` VARCHAR(255) NOT NULL,
    `identificacion` INT NOT NULL,
    `correo` VARCHAR(255) NOT NULL,
    `telefono` VARCHAR(255) NOT NULL
);
CREATE TABLE `Ventas`(
    `id_venta` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `id_cliente` INT NOT NULL,
    `fecha` DATE NOT NULL,
    `total` DECIMAL(10, 2) NOT NULL
);
CREATE TABLE `Detalle_Ventas`(
    `id_detalle_venta` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `id_venta` INT NOT NULL,
    `id_celular` INT NOT NULL,
    `cantidad` INT NOT NULL,
    `subtotal` DECIMAL(10, 2) NOT NULL
);
ALTER TABLE
    `Detalle_Ventas` ADD CONSTRAINT `detalle_ventas_id_celular_foreign` FOREIGN KEY(`id_celular`) REFERENCES `Celulares`(`id_celular`);
ALTER TABLE
    `Detalle_Ventas` ADD CONSTRAINT `detalle_ventas_id_venta_foreign` FOREIGN KEY(`id_venta`) REFERENCES `Ventas`(`id_venta`);
ALTER TABLE
    `Ventas` ADD CONSTRAINT `ventas_id_cliente_foreign` FOREIGN KEY(`id_cliente`) REFERENCES `Clientes`(`id_cliente`);
