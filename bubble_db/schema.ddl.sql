DROP TABLE IF EXISTS t_answers;
DROP TABLE IF EXISTS t_comics;
DROP TABLE IF EXISTS t_questions;
DROP TABLE IF EXISTS t_reading_profiles;

CREATE TABLE t_reading_profiles (
	rp_id SERIAL,
	rp_description VARCHAR(1000) NOT NULL,
	rp_profile_name VARCHAR(200) NOT NULL UNIQUE,
	CONSTRAINT pk_movie_id PRIMARY KEY(rp_id)	
);

CREATE TABLE t_questions (
	q_id SERIAL,
	q_order INTEGER NOT NULL UNIQUE,
	q_label VARCHAR(500) NOT NULL,
	CONSTRAINT pk_question_id PRIMARY KEY(q_id)
);

CREATE TABLE t_comics (
	c_id SERIAL,
	c_isbn VARCHAR(200) NOT NULL UNIQUE,
	c_title VARCHAR(200) NOT NULL,
	c_author VARCHAR(200) NOT NULL,
	c_country VARCHAR(200) NOT NULL,
	c_publication_date INTEGER NOT NULL,
	c_genre VARCHAR(100) NOT NULL,
	c_front_cover VARCHAR(300) NOT NULL,
	c_editor VARCHAR(200) NOT NULL,
	c_reading_profile_id INTEGER,
	c_summary VARCHAR(500) NOT NULL,
	CONSTRAINT pk_comic_id PRIMARY KEY(c_id),
	CONSTRAINT fk_reading_profile_id
		FOREIGN KEY(c_reading_profile_id)
		REFERENCES t_reading_profiles(rp_id)
);

CREATE TABLE t_answers (
	a_id SERIAL,
	a_order INTEGER NOT NULL UNIQUE,
	a_label VARCHAR(500) NOT NULL,
	a_question_id INTEGER,
	a_reading_profile_id INTEGER,
	CONSTRAINT pk_answer_id PRIMARY KEY(a_id),
	CONSTRAINT fk_reading_profile_id
		FOREIGN KEY(a_reading_profile_id)
		REFERENCES t_reading_profiles(rp_id),
	CONSTRAINT fk_question_id
		FOREIGN KEY(a_question_id)
		REFERENCES t_questions(q_id)
);