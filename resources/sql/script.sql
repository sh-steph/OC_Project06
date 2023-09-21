CREATE TABLE `users` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`email` varchar(255) NOT NULL UNIQUE,
	`admin` BOOLEAN NOT NULL DEFAULT false,
	`username` varchar(255) NOT NULL,
	`password` varchar(255) NOT NULL,
	`created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`)
);

CREATE TABLE `subscriptions` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`user_id` INT NOT NULL,
	`theme_id` INT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `posts` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`theme_id` INT NOT NULL,
	`user_id` INT NOT NULL,
	`title` varchar(255) NOT NULL,
	`description` TEXT NOT NULL,
	`created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`)
);

CREATE TABLE `themes` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`title` varchar(255) NOT NULL UNIQUE,
	`created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`)
);

CREATE TABLE `comments` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`user_id` INT NOT NULL,
	`post_id` INT NOT NULL,
	`comment` TEXT NOT NULL,
	`created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`)
);

ALTER TABLE `subscriptions` ADD CONSTRAINT `subscriptions_fk0` FOREIGN KEY (`user_id`) REFERENCES `users`(`id`);

ALTER TABLE `subscriptions` ADD CONSTRAINT `subscriptions_fk1` FOREIGN KEY (`theme_id`) REFERENCES `themes`(`id`);

ALTER TABLE `posts` ADD CONSTRAINT `posts_fk0` FOREIGN KEY (`theme_id`) REFERENCES `themes`(`id`);

ALTER TABLE `posts` ADD CONSTRAINT `posts_fk1` FOREIGN KEY (`user_id`) REFERENCES `users`(`id`);

ALTER TABLE `comments` ADD CONSTRAINT `comments_fk0` FOREIGN KEY (`user_id`) REFERENCES `users`(`id`);

ALTER TABLE `comments` ADD CONSTRAINT `comments_fk1` FOREIGN KEY (`post_id`) REFERENCES `posts`(`id`);

INSERT INTO users (email, admin, username, password, created_at)
VALUES ('admin@mdd.com', true, 'Admin', '$2a$10$.Hsa/ZjUVaHqi0tp9xieMeewrnZxrZ5pQRzddUXE/WjDu2ZThe6Iq', '2023-09-08 09:53:09');

INSERT INTO themes (title, created_at)
VALUES ('Basket Ball', '2023-09-21 09:53:09');

INSERT INTO posts (theme_id, user_id, title, description, created_at)
VALUES (1, 1, 'The amazing Stephen Curry', 'Unstopable shooter description', '2023-09-21 09:53:09');

INSERT INTO subscriptions (theme_id, user_id)
VALUES (1, 1);

INSERT INTO comments (user_id, post_id, comment, created_at)
VALUES (1, 1, 'Still below Jordan at his prime', '2023-09-21 10:53:09');
