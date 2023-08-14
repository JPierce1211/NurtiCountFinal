BEGIN TRANSACTION;

DROP TABLE IF EXISTS users,goals,profile, meal_food, food,meals, profile_img;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE goals (
	goal_id serial,
	desired_weight decimal NOT NULL,
	bmi decimal NOT NULL,
	log_day varchar(10) NOT NULL
);

CREATE TABLE profile (
	user_id int,
	profile_id serial,
	birthday varchar(10) NOT NULL,
	height decimal NOT NULL,
	current_weight decimal NOT NULL,
	display_name varchar(100) NOT NULL,
	profile_pic_id int NOT NULL,
	goals int
);

CREATE TABLE meals(
	meal_id int PRIMARY KEY,
	user_id int REFERENCES users(user_id),
	meal_type varchar(10) NOT NULL,
	meal_name varchar(1000) NULL,
	meal_description varchar(1000) NULL,
	log_day varchar(10) NOT NULL,
	is_quick_meal boolean NOT NULL

);

CREATE TABLE food (
	food_id serial PRIMARY KEY,
	meal_id int REFERENCES meals(meal_id),
	food_name varchar(100) NOT NULL,
	food_type varchar(50) NULL,
	serving_size decimal NOT NULL,
	calories decimal NOT NULL,
	num_of_servings decimal NOT NULL,
	is_quick_food boolean NOT NULL
);

CREATE TABLE meal_food(
	meal_id int REFERENCES meals(meal_id),
	food_id int REFERENCES food(food_id),
	log_day varchar(10) NOT NULL,
	CONSTRAINT PK_meal_foods PRIMARY KEY (meal_id, food_id)
);

CREATE TABLE profile_img (
	profile_pic_id serial,
	url varchar(10)
);

COMMIT TRANSACTION;

-- 	CONSTRAINT FK_meal_user FOREIGN KEY (meal_id) REFERENCES meal_user(meal_id),
-- 	CONSTRAINT UQ_meal_id UNIQUE (meal_id)


-- CREATE TABLE meal_user (
-- 	meal_id serial PRIMARY KEY,
-- 	user_id int,
-- 	CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
-- );

-- CREATE TABLE meals(
-- 	id int NOT NULL,
-- 	meal_type varchar(10) NOT NULL,
-- 	log_day date NOT NULL,
-- 	is_quick_meal boolean NOT NULL,
-- 	CONSTRAINT FK_meal_user FOREIGN KEY (meal_id) REFERENCES meal_user(meal_id),
-- 	CONSTRAINT UQ_meal_id UNIQUE (meal_id)
-- );

-- CREATE TABLE meal_foods (
-- 	meal_id int NULL,
-- 	food_id int NULL,
-- 	CONSTRAINT PK_meal_foods PRIMARY KEY (meal_id, food_id),
-- 	CONSTRAINT FK_meal_id FOREIGN KEY (meal_id) REFERENCES meals(meal_id),
-- 	CONSTRAINT FK_food_id FOREIGN KEY (food_id) REFERENCES food(food_id)
-- );




