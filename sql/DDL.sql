DROP SCHEMA dagligvare;

CREATE SCHEMA `dagligvare`;

USE `dagligvare`;

CREATE TABLE `Van`
(
    `id`       INT         NOT NULL,
    `brand`    varchar(45) NOT NULL,
    `model`    varchar(45) NOT NULL,
    `capacity` DOUBLE      NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `Delivery`
(
    `id`            INT  NOT NULL,
    `deliveryDate`  DATE NOT NULL,
    `fromWarehouse` varchar(45),
    `destination`   varchar(45),
    `van`           INT,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`van`) REFERENCES Van (`id`)
);

CREATE TABLE `Product`
(
    `id`     INT         NOT NULL,
    `name`   varchar(45) NOT NULL,
    `price`  DOUBLE      NOT NULL,
    `weight` DOUBLE      NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `ProductOrder`
(
    `id`       INT    NOT NULL,
    `quantity` DOUBLE NOT NULL,
    `product`  INT    NOT NULL,
    `delivery` INT    NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`product`) REFERENCES Product (`id`),
    FOREIGN KEY (`delivery`) REFERENCES Delivery (`id`)
);

