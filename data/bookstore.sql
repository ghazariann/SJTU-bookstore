DROP TABLE IF EXISTS `books`;
CREATE TABLE `books` (
    `id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(255) default null,
    `type` VARCHAR(255) default null,
    `author` VARCHAR(255) default null,
    `price` DECIMAL(10, 2) default null,
    `description` VARCHAR(2000) default null,
    `inventory` INT(11) default null,
    `tags` VARCHAR(255) default null);
-- Original books with updated tags
INSERT INTO `books` VALUES ('1', 'The Great Gatsby', 'Fiction', 'F. Scott Fitzgerald', '95.20', 'A novel about the decadence and excess of the Jazz Age', '1000', 'Adventure;Romance');
INSERT INTO `books` VALUES ('2', '1984', 'Dystopian', 'George Orwell', '120.50', 'A dystopian novel about a society under total surveillance', '900', 'Dystopian');
INSERT INTO `books` VALUES ('3', 'To Kill a Mockingbird', 'Fiction', 'Harper Lee', '85.75', 'A classic novel of racism and injustice', '1200',  'Adventure;Romance');
INSERT INTO `books` VALUES ('4', 'Moby-Dick', 'Adventure', 'Herman Melville', '110.00', 'An epic sea story of Captain Ahab’s obsessive quest to hunt down the great white whale Moby Dick', '700',  'Adventure');

-- Additional books to cover all subgenres
INSERT INTO `books` VALUES ('5', 'The Diary of Anne Frank', 'Non-Fiction', 'Anne Frank', '100.00', 'The writings from the Dutch language diary kept by Anne Frank while she was in hiding during World War II', '1200',  'Historical;Biography');
INSERT INTO `books` VALUES ('6', 'The Da Vinci Code', 'Mystery', 'Dan Brown', '150.00', 'A mystery thriller novel that follows a symbologist and a cryptologist who discover a conspiracy in the works of Leonardo da Vinci', '800', 'Crime;Thriller');
INSERT INTO `books` VALUES ('7', 'A Brief History of Time', 'Non-Fiction', 'Stephen Hawking', '130.00', 'A landmark volume in science writing by one of the great minds of our time', '1100', 'Science');
INSERT INTO `books` VALUES ('8', 'Gone Girl', 'Mystery', 'Gillian Flynn', '125.00', 'A thriller novel about a woman who mysteriously disappears on her wedding anniversary', '850', 'Crime;Thriller');
INSERT INTO `books` VALUES ('9', 'Pride and Prejudice', 'Romance', 'Jane Austen', '80.25', 'A romantic novel about manners, upbringing, morality, education, and marriage in the society of the British landed gentry', '1300', 'Romance');
INSERT INTO `books` VALUES ('10', 'The Martian', 'Fiction', 'Andy Weir', '115.00', 'A science fiction novel about an astronaut stranded on Mars, struggling to survive', '700', 'Adventure;Science');
INSERT INTO `books` VALUES ('11', 'Sapiens: A Brief History of Humankind', 'Non-Fiction', 'Yuval Noah Harari', '140.00', 'An exploration of the history and impact of Homo sapiens on the planet', '1300',  'Historical;Science');
INSERT INTO `books` VALUES ('12', 'The Adventures of Sherlock Holmes', 'Mystery', 'Arthur Conan Doyle', '90.00', 'A collection of stories featuring the famous detective Sherlock Holmes', '950', 'Crime');

CREATE TABLE users (
	`id` int(11) PRIMARY KEY AUTO_INCREMENT,
    `name` varchar(255) Not Null,
	`address` VARCHAR(255)  default NULL,
    `telephone` VARCHAR(15)  default NULL,
	`type` int(11) NOT NULL -- 0 = admin, 1 = user
   --  `userAuth` Int(11)  default NULL
);

CREATE TABLE user_auth (
    `id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `email` VARCHAR(255) NOT NULL UNIQUE,
	`password` VARCHAR(255) NOT NULL,
    `disabled` BOOLEAN NOT NULL,
    `user_id` INT(11),
	FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);

-- ALTER TABLE UserAuth ADD CONSTRAINT FK_UserAuth_UserID FOREIGN KEY (user) REFERENCES Users(id);
-- ALTER TABLE Users ADD CONSTRAINT FK_User_UserAuthID FOREIGN KEY (userAuth) REFERENCES UserAuth(id);

-- Add admin user
INSERT INTO users (name, address, telephone, type) VALUES ('Vahagn', 'st. Bagratunyac 30, Yerevan, Armenia', '+8617269740816', 0);
SET @last_id_in_Users = LAST_INSERT_ID();
INSERT INTO user_auth (email, password, user_id, disabled) VALUES ('vgn', '123', @last_id_in_Users, 0);

-- Add standard user
INSERT INTO users (name, address, telephone, type) VALUES ('Simon', 'st. dongchuanglu 800, Shanghai, China', '+867269540816', 1);
SET @last_id_in_Users = LAST_INSERT_ID();
INSERT INTO user_auth (email, password, user_id, disabled) VALUES ('sim', '123',  @last_id_in_Users, 1);

CREATE TABLE `cart_items` (
    `id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `user_id` INT(11),
    `book_id` INT(11),
    `quantity` INT(11) NOT NULL,
    `price` DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
    FOREIGN KEY (`book_id`) REFERENCES `books` (`id`)
);

CREATE TABLE `orders` (
    `id` INT(11) PRIMARY KEY AUTO_INCREMENT,
    `user_id` INT(11),
    `order_date` DATETIME NOT NULL,
    `total_price` DECIMAL(10,2) NOT NULL,
    `shipping_address` VARCHAR(255) NOT NULL,
    FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);

CREATE TABLE `order_items` (
    `id` INT(11) PRIMARY KEY AUTO_INCREMENT,
	`order_id` INT(11),
    `book_id` INT(11),
    `quantity` INT(11) NOT NULL,
    `price` DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (`book_id`) REFERENCES `books` (`id`),
    FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
);


-- use mybookstore;
-- DROP TABLE IF EXISTS order_items;
-- DROP TABLE IF EXISTS orders;
-- DROP TABLE IF EXISTS cart_items;
-- DROP TABLE IF EXISTS user_auth;
-- DROP TABLE IF EXISTS users;
-- DROP TABLE IF EXISTS books;
