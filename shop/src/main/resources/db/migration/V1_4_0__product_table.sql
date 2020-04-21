CREATE TABLE IF NOT EXISTS product
(`product_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
 `name` varchar(20),
 `description` varchar(100),
 `price` decimal,
 `weight` double,
 product_category int,
 supplier int,
 image_url varchar(60),
 foreign key (product_category) references PRODUCT_CATEGORY,
 foreign key (supplier) references SUPPLIER
);