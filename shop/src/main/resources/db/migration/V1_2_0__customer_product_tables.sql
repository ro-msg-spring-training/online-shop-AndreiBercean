CREATE TABLE IF NOT EXISTS `customer` (
    `customer_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `first_name` varchar(20),
    `last_name` varchar(20),
    `username` varchar(20),
    `password` varchar(60),
    `email` varchar(30)
);

CREATE TABLE IF NOT EXISTS `product_category` (
    `category_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(20),
    `description` varchar(100)
);