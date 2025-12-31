DROP TABLE IF EXISTS t_answers;
DROP TABLE IF EXISTS t_comics;
DROP TABLE IF EXISTS t_questions;
DROP TABLE IF EXISTS t_reading_profiles;

CREATE TABLE t_reading_profiles (
	id SERIAL,
	description VARCHAR(1000) NOT NULL,
	profile_name VARCHAR(200) NOT NULL UNIQUE,
	CONSTRAINT pk_movie_id PRIMARY KEY(id)	
);

CREATE TABLE t_questions (
	id SERIAL,
	question_order INTEGER NOT NULL UNIQUE,
	label VARCHAR(500) NOT NULL,
	CONSTRAINT pk_question_id PRIMARY KEY(id)
);

CREATE TABLE t_comics (
	id SERIAL,
	isbn VARCHAR(200) NOT NULL UNIQUE,
	title VARCHAR(200) NOT NULL,
	author VARCHAR(200) NOT NULL,
	country VARCHAR(200) NOT NULL,
	publication_date INTEGER NOT NULL,
	genre VARCHAR(100) NOT NULL,
	front_cover VARCHAR(300) NOT NULL,
	editor VARCHAR(200) NOT NULL,
	reading_profile_id INTEGER,
	summary VARCHAR(500) NOT NULL,
	CONSTRAINT pk_comic_id PRIMARY KEY(id),
	CONSTRAINT fk_reading_profile_id
		FOREIGN KEY(reading_profile_id)
		REFERENCES t_reading_profiles(id)
);

CREATE TABLE t_answers (
	id SERIAL,
	answer_order INTEGER NOT NULL UNIQUE,
	label VARCHAR(500) NOT NULL,
	question_id INTEGER,
	reading_profile_id INTEGER,
	CONSTRAINT pk_answer_id PRIMARY KEY(id),
	CONSTRAINT fk_reading_profile_id
		FOREIGN KEY(reading_profile_id)
		REFERENCES t_reading_profiles(id),
	CONSTRAINT fk_question_id
		FOREIGN KEY(question_id)
		REFERENCES t_questions(id)
);