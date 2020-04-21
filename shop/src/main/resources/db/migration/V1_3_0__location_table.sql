CREATE TABLE IF NOT EXISTS `location` (
    `location_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(20),
    `address_country` varchar(100),
    `address_city` varchar(100),
    `address_county` varchar(100),
    `address_streetAddress` varchar(100)
);