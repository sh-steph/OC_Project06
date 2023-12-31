CREATE TABLE `USERS` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`email` varchar(255) NOT NULL UNIQUE,
	`admin` BOOLEAN NOT NULL DEFAULT false,
	`username` varchar(255) NOT NULL,
	`password` varchar(255) NOT NULL,
	`created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`)
);

CREATE TABLE `SUBSCRIPTIONS` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`user_id` INT NOT NULL,
	`theme_id` INT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `POSTS` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`theme_id` INT NOT NULL,
	`user_id` INT NOT NULL,
	`title` varchar(255) NOT NULL,
	`description` TEXT NOT NULL,
	`created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`)
);

CREATE TABLE `THEMES` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`title` varchar(255) NOT NULL UNIQUE,
	`created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`)
);

CREATE TABLE `COMMENTS` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`user_id` INT NOT NULL,
	`post_id` INT NOT NULL,
	`comment` TEXT NOT NULL,
	`created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`)
);

ALTER TABLE `SUBSCRIPTIONS` ADD CONSTRAINT `SUBSCRIPTIONS_fk0` FOREIGN KEY (`user_id`) REFERENCES `USERS`(`id`);

ALTER TABLE `SUBSCRIPTIONS` ADD CONSTRAINT `SUBSCRIPTIONS_fk1` FOREIGN KEY (`theme_id`) REFERENCES `THEMES`(`id`);

ALTER TABLE `POSTS` ADD CONSTRAINT `POSTS_fk0` FOREIGN KEY (`theme_id`) REFERENCES `THEMES`(`id`);

ALTER TABLE `POSTS` ADD CONSTRAINT `POSTS_fk1` FOREIGN KEY (`user_id`) REFERENCES `USERS`(`id`);

ALTER TABLE `COMMENTS` ADD CONSTRAINT `COMMENTS_fk0` FOREIGN KEY (`user_id`) REFERENCES `USERS`(`id`);

ALTER TABLE `COMMENTS` ADD CONSTRAINT `COMMENTS_fk1` FOREIGN KEY (`post_id`) REFERENCES `POSTS`(`id`);

INSERT INTO USERS (email, admin, username, password, created_at)
VALUES ('admin@mdd.com', true, 'Admin', '$2a$10$.Hsa/ZjUVaHqi0tp9xieMeewrnZxrZ5pQRzddUXE/WjDu2ZThe6Iq', '2023-09-08 09:53:09');

INSERT INTO THEMES (title, created_at)
VALUES ('Développement web', '2023-09-21 09:53:09');

INSERT INTO POSTS (theme_id, user_id, title, description, created_at)
VALUES (1, 1, 'Par où débuter dans le développement web ?', 'Bonjour, je souhaite suivre un cursurs dans le développement mais je ne sais pas par où commencer au vue du nombre de technologies existant.', '2023-09-21 09:53:09');

INSERT INTO SUBSCRIPTIONS (theme_id, user_id)
VALUES (1, 1);

INSERT INTO COMMENTS (user_id, post_id, comment, created_at)
VALUES (1, 1, 'Bonjour, il est préférable pour tout développeur de commencer par des exercices sur les algorithmes afin de travailler sur ta reflexion à résoudre un problème et de te familiariser dans cet environnement avant de te lancer sur les technologies en question', '2023-09-21 10:53:09');

ALTER TABLE THEMES
ADD COLUMN description TEXT;
