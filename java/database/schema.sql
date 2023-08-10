BEGIN TRANSACTION;

DROP TABLE IF EXISTS users,goals,profile,meal_user,food,profile_img, meals, meal_foods;

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
	log_day date NOT NULL
);

CREATE TABLE profile (
	user_id int,
	profile_id serial,
	birthday date NOT NULL,
	height decimal NOT NULL,
	current_weight decimal NOT NULL,
	display_name varchar(12) NOT NULL,
	profile_pic_id int NOT NULL,
	goals int
);

CREATE TABLE meal_user (
	meal_id serial PRIMARY KEY,
	user_id int,
	--CONSTRAINT PK_meal_id PRIMARY KEY (meal_id),
	CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
	--CONSTRAINT FK_meal_id FOREIGN KEY (meal_id) REFERENCES meal(meal_id)
);

CREATE TABLE food (
	food_id serial,
	food_name varchar(100) NOT NULL,
	food_type varchar(50) NOT NULL,
	serving_size decimal NOT NULL,
	calories decimal NOT NULL,
	num_of_servings decimal NOT NULL,
	CONSTRAINT PK_food_id PRIMARY KEY (food_id)
);

CREATE TABLE meals(
	meal_id int NOT NULL,
	meal_type varchar(10) NOT NULL,
	log_day date NOT NULL,
	is_quick_meal boolean NOT NULL,
	--food_id int NULL,
	CONSTRAINT FK_meal_user FOREIGN KEY (meal_id) REFERENCES meal_user(meal_id),
	CONSTRAINT UQ_meal_id UNIQUE (meal_id)
	--CONSTRAINT FK_food_id FOREIGN KEY (food_id) REFERENCES food(food_id)
);

CREATE TABLE meal_foods (
	meal_id int NULL,
	food_id int NULL,
	CONSTRAINT PK_meal_foods PRIMARY KEY (meal_id, food_id),
	CONSTRAINT FK_meal_id FOREIGN KEY (meal_id) REFERENCES meals(meal_id),
	CONSTRAINT FK_food_id FOREIGN KEY (food_id) REFERENCES food(food_id)
);


CREATE TABLE profile_img (
	profile_pic_id serial,
	url varchar(10)
);

COMMIT TRANSACTION;