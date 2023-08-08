BEGIN TRANSACTION;

DROP TABLE IF EXISTS users,progress,user_profile,meal_user,food,profile_img;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE progress (
	profile_id int,
	current_weight decimal NOT NULL,
	desired_weight decimal NOT NULL,
	bmi decimal NOT NULL,
	log_day date NOT NULL,
	progress_id SERIAL NOT NULL
);

CREATE TABLE user_profile (
	user_id int,
	profile_id serial,
	birthday date NOT NULL,
	height decimal NOT NULL,
	gender varchar(1) NOT NULL,
	starting_weight decimal NOT NULL,
	display_name varchar(12) NOT NULL,
	profile_pic_id int NOT NULL
);

CREATE TABLE meal_user (
	meal_id serial,
	profile_id int,   ---DONT SAVE ******* ALSO.. MAKE CONSTRAINTS!!
	meal_type varchar(10) NOT NULL,
	log_day date NOT NULL
);

CREATE TABLE food (
	food_id serial,
	meal_id int NOT NULL,
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


COMMIT TRANSACTION;