DROP TABLE IF EXISTS t_answers_translations;
DROP TABLE IF EXISTS t_answers;
DROP TABLE IF EXISTS t_comics_translations;
DROP TABLE IF EXISTS t_comics;
DROP TABLE IF EXISTS t_questions_translations;
DROP TABLE IF EXISTS t_questions;
DROP TABLE IF EXISTS t_reading_profiles_translations;
DROP TABLE IF EXISTS t_reading_profiles;

CREATE TABLE t_reading_profiles (
	rp_id SERIAL,
	rp_profile_name VARCHAR(200) NOT NULL UNIQUE,
	rp_profile_pic VARCHAR(200),
	CONSTRAINT pk_rp_id PRIMARY KEY(rp_id)
);

CREATE TABLE t_reading_profiles_translations (
	rpt_id SERIAL,
	rpt_language VARCHAR(5) NOT NULL,
	rpt_reading_profile_id INTEGER,
	rpt_description VARCHAR(1000) NOT NULL UNIQUE,	
	CONSTRAINT pk_rpt_id PRIMARY KEY(rpt_id),
	CONSTRAINT fk_rpt_reading_profile_id
		FOREIGN KEY(rpt_reading_profile_id)
		REFERENCES t_reading_profiles(rp_id)
);

CREATE TABLE t_questions (
	q_id SERIAL,
	q_order INTEGER NOT NULL UNIQUE,
	CONSTRAINT pk_q_id PRIMARY KEY(q_id)
);

CREATE TABLE t_questions_translations (
	qt_id SERIAL,
	qt_language VARCHAR(5) NOT NULL,
	qt_question_id INTEGER,
	qt_label VARCHAR(500) NOT NULL UNIQUE,	
	CONSTRAINT pk_qt_id PRIMARY KEY(qt_id),
	CONSTRAINT fk_qt_id
		FOREIGN KEY(qt_question_id)
		REFERENCES t_questions(q_id)
);

CREATE TABLE t_comics (
	c_id SERIAL,
	c_reading_profile_id INTEGER,
	c_author VARCHAR(200) NOT NULL,	
	c_publication_date INTEGER NOT NULL,
	c_front_cover VARCHAR(300) NOT NULL,	
	CONSTRAINT pk_c_id PRIMARY KEY(c_id),
	CONSTRAINT fk_c_reading_profile_id
		FOREIGN KEY(c_reading_profile_id)
		REFERENCES t_reading_profiles(rp_id)
);

CREATE TABLE t_comics_translations (
	ct_id SERIAL,
	ct_language VARCHAR(5) NOT NULL,
	ct_comic_id INTEGER,
	ct_title VARCHAR(200) NOT NULL,
	ct_country VARCHAR(200) NOT NULL,
	ct_genre VARCHAR(100) NOT NULL,
	ct_editor VARCHAR(200) NOT NULL,	
	ct_isbn VARCHAR(200) NOT NULL UNIQUE,
	ct_summary VARCHAR(500) NOT NULL,	
	CONSTRAINT pk_ct_id PRIMARY KEY(ct_id),
	CONSTRAINT fk_ct_comic_id
		FOREIGN KEY(ct_comic_id)
		REFERENCES t_comics(c_id)
);

CREATE TABLE t_answers (
	a_id SERIAL,
	a_order INTEGER NOT NULL UNIQUE,
	a_question_id INTEGER,
	a_reading_profile_id INTEGER,
	CONSTRAINT pk_a_id PRIMARY KEY(a_id),
	CONSTRAINT fk_a_reading_profile_id
		FOREIGN KEY(a_reading_profile_id)
		REFERENCES t_reading_profiles(rp_id),
	CONSTRAINT fk_a_question_id
		FOREIGN KEY(a_question_id)
		REFERENCES t_questions(q_id)
);

CREATE TABLE t_answers_translations (
	at_id SERIAL,
	at_language VARCHAR(5) NOT NULL,
	at_answer_id INTEGER,
	at_label VARCHAR(500) NOT NULL UNIQUE,	
	CONSTRAINT pk_at_id PRIMARY KEY(at_id),
	CONSTRAINT fk_at_answer_id
		FOREIGN KEY(at_answer_id)
		REFERENCES t_answers(a_id)
);