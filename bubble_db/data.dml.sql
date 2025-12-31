DELETE FROM t_answers;
DELETE FROM t_comics;
DELETE FROM t_questions;
DELETE FROM t_reading_profiles;

INSERT INTO t_reading_profiles (id, profile_name, description) VALUES
(
  1,
  'THE_DREAMER',
  'The Dreamer is drawn to emotional, poetic and introspective comic books. This reader enjoys stories that explore inner feelings, personal journeys and quiet moments of reflection. Visual creativity and artistic expression matter as much as the story itself.'
),
(
  2,
  'THE_ADVENTURER',
  'The Adventurer seeks action, excitement and epic journeys. This reader loves dynamic plots, heroic characters and immersive worlds filled with quests, battles and discoveries. Fast-paced storytelling and a strong sense of adventure are essential.'
),
(
  3,
  'THE_DARK_MIND',
  'The Dark Mind is fascinated by complex, psychological and often unsettling stories. This reader appreciates dark atmospheres, moral ambiguity and narratives that explore the shadows of human nature. Noir, thriller and intense emotional tension are key elements.'
),
(
  4,
  'THE_LAUGH_SEEKER',
  'The Laugh Seeker reads comics mainly for fun and relaxation. Humor, lighthearted stories and playful characters are central to this profile. This reader enjoys witty dialogue, visual gags and stories that bring joy and laughter without taking themselves too seriously.'
),
(
  5,
  'THE_ACTIVIST_READER',
  'The Activist Reader is passionate about socially engaged comic books. This profile values stories that address political, feminist and societal issues, often highlighting marginalized voices. Reading is both a cultural and political act, meant to inspire awareness and change.'
),
(
  6,
  'OLD_CLASSIC_READER',
  'The Old Classic Reader has a deep appreciation for timeless and iconic comic books. This reader enjoys traditional storytelling, clear narratives and historically significant works that shaped the comic book medium. Heritage, cultural legacy and enduring characters are central.'
);


INSERT INTO t_questions (id, question_order, label) VALUES
(1, 1, 'What kind of story do you enjoy the most?'),
(2, 2, 'What do you look for first in a comic book?'),
(3, 3, 'Which atmosphere appeals to you the most?'),
(4, 4, 'What kind of characters do you prefer?'),
(5, 5, 'What format do you usually prefer?'),
(6, 6, 'How important is the visual style to you?'),
(7, 7, 'How do you feel about social and political themes?'),
(8, 8, 'What reading pace do you enjoy the most?'),
(9, 9, 'What level of realism do you prefer?'),
(10, 10, 'What emotion do you want to feel after reading?'),
(11, 11, 'What kind of comics do you naturally gravitate toward?'),
(12, 12, 'How do you relate to classic comic characters?'),
(13, 13, 'What storytelling style do you value the most?');

INSERT INTO t_answers (answer_order, label, question_id, reading_profile_id) VALUES

-- Q1
(1, 'Emotional and poetic stories', 1, 1),
(2, 'Action-filled adventures', 1, 2),
(3, 'Dark and psychological narratives', 1, 3),
(4, 'Light and humorous stories', 1, 4),

-- Q2
(5, 'Artistic expression and emotions', 2, 1),
(6, 'World-building and adventure', 2, 2),
(7, 'Tension and moral complexity', 2, 3),
(8, 'Strong social or political messages', 2, 5),

-- Q3
(9, 'Dreamy and contemplative', 3, 1),
(10, 'Epic and immersive', 3, 2),
(11, 'Dark and unsettling', 3, 3),
(12, 'Bright and playful', 3, 4),

-- Q4
(13, 'Sensitive and introspective characters', 4, 1),
(14, 'Brave heroes and explorers', 4, 2),
(15, 'Flawed and ambiguous characters', 4, 3),
(16, 'Engaged and socially aware characters', 4, 5),

-- Q5
(17, 'Short stories or one-shots', 5, 1),
(18, 'Long-running series', 5, 2),
(19, 'Standalone intense stories', 5, 3),
(20, 'Any format as long as it is fun', 5, 4),

-- Q6
(21, 'Essential, visuals carry the emotion', 6, 1),
(22, 'Important if it enhances action', 6, 2),
(23, 'Crucial for mood and tension', 6, 3),
(24, 'Important when it supports the message', 6, 5),

-- Q7
(25, 'I actively seek engaged and feminist themes', 7, 5),
(26, 'I appreciate them when they are subtle', 7, 1),
(27, 'Only if they add depth or darkness', 7, 3),
(28, 'I prefer to avoid them and just have fun', 7, 4),

-- Q8
(29, 'Slow and reflective', 8, 1),
(30, 'Fast-paced and dynamic', 8, 2),
(31, 'Slow but intense', 8, 3),
(32, 'Fast and entertaining', 8, 4),

-- Q9
(33, 'Realistic and socially grounded stories', 9, 5),
(34, 'Emotionally realistic, even if symbolic', 9, 1),
(35, 'Escapism matters more than realism', 9, 2),
(36, 'Raw and harsh realism', 9, 3),

-- Q10
(37, 'Moved and thoughtful', 10, 1),
(38, 'Energized and excited', 10, 2),
(39, 'Challenged or disturbed', 10, 3),
(40, 'Empowered or inspired to act', 10, 5),

-- Q11
(41, 'Timeless and iconic comic books', 11, 6),
(42, 'Creative and experimental works', 11, 1),
(43, 'Dark and mature comics', 11, 3),
(44, 'Entertaining and humorous comics', 11, 4),

-- Q12
(45, 'They are cultural landmarks and references', 12, 6),
(46, 'I enjoy them, especially adventure classics', 12, 2),
(47, 'I like when they are reinterpreted or subverted', 12, 3),
(48, 'I prefer contemporary and engaged characters', 12, 5),

-- Q13
(49, 'Clear and traditional storytelling', 13, 6),
(50, 'Epic and adventurous storytelling', 13, 2),
(51, 'Complex and psychological narratives', 13, 3),
(52, 'Stories that question society and norms', 13, 5);


INSERT INTO t_comics (
  id,
  isbn,
  title,
  author,
  country,
  publication_date,
  genre,
  front_cover,
  editor,
  reading_profile_id,
  summary
) VALUES

-- =========================
-- THE_DREAMER (1)
-- =========================
(DEFAULT, 'ISBN-D-001', 'Daytripper', 'Fábio Moon & Gabriel Bá', 'Brazil', 2010, 'Drama', 'daytripper.jpg', 'Vertigo', 1, 'A poetic exploration of life, love, and the many possible paths of a man named Brás.'),
(DEFAULT, 'ISBN-D-002', 'Blankets', 'Craig Thompson', 'USA', 2003, 'Autobiography', 'blankets.jpg', 'Top Shelf', 1, 'A heartfelt coming-of-age story exploring family, first love, and faith.'),
(DEFAULT, 'ISBN-D-003', 'The Arrival', 'Shaun Tan', 'Australia', 2006, 'Poetic', 'arrival.jpg', 'Arthur A. Levine', 1, 'A wordless journey of an immigrant discovering a strange and wondrous new world.'),
(DEFAULT, 'ISBN-D-004', 'Asterios Polyp', 'David Mazzucchelli', 'USA', 2009, 'Introspection', 'asterios.jpg', 'Pantheon', 1, 'A visually inventive story about an architect confronting his life, relationships, and identity.'),
(DEFAULT, 'ISBN-D-005', 'Blue Is the Warmest Color', 'Julie Maroh', 'France', 2010, 'Romance', 'blue.jpg', 'Glénat', 1, 'A tender and emotional story of first love and self-discovery between two young women.'),

-- =========================
-- THE_ADVENTURER (2)
-- =========================
(DEFAULT, 'ISBN-A-001', 'Thorgal', 'Jean Van Hamme', 'Belgium', 1977, 'Adventure', 'thorgal.jpg', 'Le Lombard', 2, 'Epic adventures of a Viking hero navigating dangerous lands and mythical threats.'),
(DEFAULT, 'ISBN-A-002', 'Lanfeust of Troy', 'Christophe Arleston', 'France', 1994, 'Fantasy', 'lanfeust.jpg', 'Soleil', 2, 'A young hero discovers magical powers and embarks on quests in a fantastical world.'),
(DEFAULT, 'ISBN-A-003', 'One Piece', 'Eiichiro Oda', 'Japan', 1997, 'Adventure', 'onepiece.jpg', 'Shueisha', 2, 'A young pirate sets sail with his crew to find the legendary treasure, the One Piece.'),
(DEFAULT, 'ISBN-A-004', 'Saga', 'Brian K. Vaughan', 'USA', 2012, 'Sci-Fi', 'saga.jpg', 'Image Comics', 2, 'A star-crossed couple from warring races struggle to protect their child in a vast universe.'),
(DEFAULT, 'ISBN-A-005', 'Bone', 'Jeff Smith', 'USA', 1991, 'Fantasy', 'bone.jpeg', 'Cartoon Books', 2, 'Three cousins get lost in a mysterious valley filled with wonderful and dangerous creatures.'),

-- =========================
-- THE_DARK_MIND (3)
-- =========================
(DEFAULT, 'ISBN-DM-001', 'Blacksad', 'Juan Díaz Canales & Juanjo Guarnido', 'Spain', 2000, 'Noir', 'blacksad.jpg', 'Dargaud', 3, 'A detective cat navigates crime, corruption, and human vice in a noir-inspired world.'),
(DEFAULT, 'ISBN-DM-002', 'Watchmen', 'Alan Moore & Dave Gibbons', 'UK', 1987, 'Psychological', 'watchmen.jpg', 'DC Comics', 3, 'Superheroes face moral dilemmas and societal collapse in a dark alternate history.'),
(DEFAULT, 'ISBN-DM-003', 'Sin City', 'Frank Miller', 'USA', 1991, 'Noir', 'sincity.jpg', 'Dark Horse', 3, 'Intertwined stories of crime, revenge, and survival in a gritty, violent city.'),
(DEFAULT, 'ISBN-DM-004', 'From Hell', 'Alan Moore & Eddie Campbell', 'UK', 1999, 'Crime', 'fromhell.jpg', 'Top Shelf', 3, 'A detailed exploration of Jack the Ripper’s crimes and the society surrounding him.'),
(DEFAULT, 'ISBN-DM-005', 'V for Vendetta', 'Alan Moore & David Lloyd', 'UK', 1988, 'Political', 'vforvendetta.jpg', 'DC Comics', 3, 'A masked revolutionary fights a dystopian government to inspire freedom and justice.'),

-- =========================
-- THE_LAUGH_SEEKER (4)
-- =========================
(DEFAULT, 'ISBN-L-001', 'Gaston Lagaffe', 'André Franquin', 'Belgium', 1957, 'Humor', 'gaston.jpg', 'Dupuis', 4, 'Comical adventures of an accident-prone office worker and his hilarious inventions.'),
(DEFAULT, 'ISBN-L-002', 'Asterix', 'René Goscinny & Albert Uderzo', 'France', 1959, 'Humor', 'asterix.jpg', 'Hachette', 4, 'The adventures of a clever Gaul and his friends resisting Roman occupation with wit and humor.'),
(DEFAULT, 'ISBN-L-003', 'Calvin and Hobbes', 'Bill Watterson', 'USA', 1985, 'Humor', 'calvin.jpg', 'Andrews McMeel', 4, 'A young boy and his tiger friend explore imagination, mischief, and life’s lessons.'),
(DEFAULT, 'ISBN-L-004', 'Kid Paddle', 'Midam', 'Belgium', 1993, 'Humor', 'kidpaddle.jpg', 'Dupuis', 4, 'Funny stories of a boy passionate about video games and his comical adventures.'),
(DEFAULT, 'ISBN-L-005', 'The Simpsons Comics', 'Matt Groening', 'USA', 1993, 'Humor', 'simpsons.jpg', 'Bongo Comics', 4, 'The Simpsons family gets into humorous situations reflecting modern life and society.'),

-- =========================
-- THE_ACTIVIST_READER (5)
-- =========================
(DEFAULT, 'ISBN-AR-001', 'Persepolis', 'Marjane Satrapi', 'France', 2000, 'Biography', 'persepolis.jpg', 'L’Association', 5, 'A young girl’s life during the Iranian revolution, exploring identity and resilience.'),
(DEFAULT, 'ISBN-AR-002', 'Culottées', 'Pénélope Bagieu', 'France', 2016, 'Feminism', 'culottees.jpg', 'Gallimard', 5, 'Stories of remarkable women who defied conventions and shaped history.'),
(DEFAULT, 'ISBN-AR-003', 'Fun Home', 'Alison Bechdel', 'USA', 2006, 'Biography / Identity', 'fun_home.jpg', 'Houghton Mifflin', 5, 'Memoir exploring family, sexuality, and self-discovery.'),
(DEFAULT, 'ISBN-AR-004', 'Aya of Yop City', 'Marguerite Abouet', 'Ivory Coast', 2005, 'Social', 'aya.jpg', 'Gallimard', 5, 'A lively story of a young woman navigating life, love, and friendship in Ivory Coast.'),
(DEFAULT, 'ISBN-AR-005', 'The World Without Us', 'Alan Weisman & illustrated adaptation', 'USA', 2010, 'Ecology', 'world_without_us.jpg', 'Picador', 5, 'An imaginative look at how Earth would evolve if humans disappeared.'),
(DEFAULT, 'ISBN-AR-006', 'Algues Vertes', 'Inès Leraud & Pierre Van Hove', 'France', 2021, 'Ecology / Investigative', 'algues_vertes.jpg', 'Les Arènes', 5, 'Investigative report on the environmental disaster of green algae pollution in Brittany.'),
(DEFAULT, 'ISBN-AR-007', 'Pyongyang: A Journey in North Korea', 'Guy Delisle', 'Canada', 2003, 'Political Documentary', 'pyongyang.jpg', 'L’Association', 5, 'A humorous and insightful account of life in North Korea through a cartoonist’s perspective.'),

-- =========================
-- OLD_CLASSIC_READER (6)
-- =========================
(DEFAULT, 'ISBN-C-001', 'Tintin in Tibet', 'Hergé', 'Belgium', 1960, 'Adventure', 'tintin.jpg', 'Casterman', 6, 'Tintin travels to Tibet to rescue his friend Chang in a thrilling adventure.'),
(DEFAULT, 'ISBN-C-002', 'Spirou and Fantasio', 'André Franquin', 'Belgium', 1958, 'Adventure', 'spirou.jpg', 'Dupuis', 6, 'Two friends embark on exciting adventures filled with humor and creativity.'),
(DEFAULT, 'ISBN-C-003', 'Lucky Luke', 'Morris & René Goscinny', 'Belgium', 1955, 'Western', 'luckyluke.jpg', 'Dupuis', 6, 'A quick-drawing cowboy confronts outlaws in humorous and classic Western tales.'),
(DEFAULT, 'ISBN-C-004', 'Flash Gordon', 'Alex Raymond', 'USA', 1934, 'Sci-Fi', 'flashgordon.jpg', 'King Features', 6, 'A hero battles evil in space, defending humanity from cosmic threats.'),
(DEFAULT, 'ISBN-C-005', 'The Phantom', 'Lee Falk', 'USA', 1936, 'Adventure', 'phantom.jpg', 'King Features', 6, 'The masked crime-fighter protects his jungle homeland from criminals and injustice.');
