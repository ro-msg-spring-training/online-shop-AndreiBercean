CREATE TABLE IF NOT EXISTS orders
(`order_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
 shipped_from int,
 customer int,
 `created_at` time,
 `address_country` varchar(100),
 `address_city` varchar(100),
 `address_county` varchar(100),
 `address_streetAddress` varchar(100),
 foreign key (shipped_from) references LOCATION,
 foreign key (customer) references CUSTOMER
);