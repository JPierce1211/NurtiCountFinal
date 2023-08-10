BEGIN TRANSACTION;

DROP TABLE IF EXISTS users,goals,profile,meal_user,food,profile_img, meals;

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
	meal_id serial,
	user_id int   ---DONT SAVE ******* ALSO.. MAKE CONSTRAINTS!!
);

CREATE TABLE food (
	food_id serial,
	food_name varchar(100) NOT NULL,
	food_type varchar(50) NOT NULL,
	serving_size decimal NOT NULL,
	calories decimal NOT NULL,
	num_of_servings decimal NOT NULL
);

CREATE TABLE profile_img (
	profile_pic_id serial,
	url varchar(10)
);

CREATE TABLE meals(
	meal_id int NOT NULL,
	meal_type varchar(10) NOT NULL,
	log_day date NOT NULL,
	is_quick_meal boolean NOT NULL,
	food_id int NOT NULL
);


COMMIT TRANSACTION;