CREATE TABLE IF NOT EXISTS revenue
(`revenue_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
 `date` date,
 location int,
 `sum` decimal,
 foreign key (location) references LOCATION
);