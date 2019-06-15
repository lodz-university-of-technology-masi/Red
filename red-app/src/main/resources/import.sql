
INSERT INTO masi.job_title(id, name, active) VALUES (1, 'Junior Java Developer', true);
INSERT INTO masi.job_title(id, name, active) VALUES (2, 'Senior Java Developer', true);
INSERT INTO masi.job_title(id, name, active) VALUES (3, 'Junior Software Tester', true);
INSERT INTO masi.job_title(id, name, active) VALUES (4, 'Senior Software Tester', true);

ALTER SEQUENCE masi.job_title_seq RESTART WITH 5;

INSERT INTO masi.role(id, name, active) VALUES (1, 'MODERATOR', true);
INSERT INTO masi.role(id, name, active) VALUES (2, 'EDITOR', true);
INSERT INTO masi.role(id, name, active) VALUES (3, 'CANDIDATE', true);

ALTER SEQUENCE masi.role_seq RESTART WITH 4;

INSERT INTO masi.question(id, content, creation_time, language, original_question_id) VALUES (1, 'How long is your experience in Java (years)?', NOW(), 'EN', 1);
INSERT INTO masi.question(id, content, creation_time, language, original_question_id, suggested_answer) VALUES (2, 'Can boolean be null in Java?', NOW(), 'EN', 2, 'NO');
INSERT INTO masi.question(id, content, creation_time, language, original_question_id, suggested_answer) VALUES (3, 'How to compare Strings for structural equality?', NOW(), 'EN', 3, 'By equals()');
INSERT INTO masi.question(id, content, creation_time, language, original_question_id, suggested_answer) VALUES (4, 'What is 2+2?', NOW(), 'EN', 4, '4');

INSERT INTO masi.scale_question(id, min_value, max_value, interval) VALUES (1, 0, 20, 0.5);
INSERT INTO masi.single_choice_question(id) VALUES (2);
INSERT INTO masi.open_question(id) VALUES (3);
INSERT INTO masi.numeric_question(id) VALUES (4);

INSERT INTO masi.single_choice_question_possible_answers(single_choice_question_id, possible_answers) VALUES (2, 'YES');
INSERT INTO masi.single_choice_question_possible_answers(single_choice_question_id, possible_answers) VALUES (2, 'NO');

ALTER SEQUENCE masi.question_seq RESTART WITH 5;

INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (1, 'test@test.com', 'test', 'test', '$2a$11$koESAmGxKKtlIk4dT8z6peg17Fw3A/WThS.bYLBBuekreP4j/mo9m', 'test');

ALTER SEQUENCE masi.user_seq RESTART WITH 2;

INSERT INTO masi.user_role(user_id, role_id) VALUES (1, 1);
INSERT INTO masi.user_role(user_id, role_id) VALUES (1, 2);

INSERT INTO masi.test(id, creation_time, job_title_id, language, user_id) VALUES (1, NOW(), 1, 'EN', 1);
INSERT INTO masi.test(id, creation_time, job_title_id, language, user_id) VALUES (2, NOW(), 2, 'EN', 1);

ALTER SEQUENCE masi.test_seq RESTART WITH 3;

INSERT INTO masi.test_question(test_id, question_id) VALUES (1, 1);
INSERT INTO masi.test_question(test_id, question_id) VALUES (1, 2);
INSERT INTO masi.test_question(test_id, question_id) VALUES (2, 3);
INSERT INTO masi.test_question(test_id, question_id) VALUES (2, 4);


-- DODATKOWI UZYTKOWNICY DLA TESTOW

-- BLUE
INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (2, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'ablue1');
INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (3, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'rblue1');

INSERT INTO masi.user_role(user_id, role_id) VALUES (2, 1);
INSERT INTO masi.user_role(user_id, role_id) VALUES (3, 2);

INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (4, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'ablue2');
INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (5, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'rblue2');

INSERT INTO masi.user_role(user_id, role_id) VALUES (4, 1);
INSERT INTO masi.user_role(user_id, role_id) VALUES (5, 2);

INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (6, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'ablue3');
INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (7, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'rblue3');

INSERT INTO masi.user_role(user_id, role_id) VALUES (6, 1);
INSERT INTO masi.user_role(user_id, role_id) VALUES (7, 2);

-- YELLOW
INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (8, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'ayellow1');
INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (9, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'ryellow1');

INSERT INTO masi.user_role(user_id, role_id) VALUES (8, 1);
INSERT INTO masi.user_role(user_id, role_id) VALUES (9, 2);

INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (10, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'ayellow2');
INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (11, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'ryellow2');

INSERT INTO masi.user_role(user_id, role_id) VALUES (10, 1);
INSERT INTO masi.user_role(user_id, role_id) VALUES (11, 2);

INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (12, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'ayellow3');
INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (13, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'ryellow3');

INSERT INTO masi.user_role(user_id, role_id) VALUES (12, 1);
INSERT INTO masi.user_role(user_id, role_id) VALUES (13, 2);

INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (14, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'ayellow4');
INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (15, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'ryellow4');

INSERT INTO masi.user_role(user_id, role_id) VALUES (14, 1);
INSERT INTO masi.user_role(user_id, role_id) VALUES (15, 2);

-- GREEN
INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (16, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'agreen1');
INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (17, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'rgreen1');

INSERT INTO masi.user_role(user_id, role_id) VALUES (16, 1);
INSERT INTO masi.user_role(user_id, role_id) VALUES (17, 2);

INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (18, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'agreen2');
INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (19, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'rgreen2');

INSERT INTO masi.user_role(user_id, role_id) VALUES (18, 1);
INSERT INTO masi.user_role(user_id, role_id) VALUES (19, 2);

INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (20, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'agreen3');
INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (21, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'rgreen3');

INSERT INTO masi.user_role(user_id, role_id) VALUES (20, 1);
INSERT INTO masi.user_role(user_id, role_id) VALUES (21, 2);

-- RED
INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (22, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'ared1');
INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (23, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'rred1');

INSERT INTO masi.user_role(user_id, role_id) VALUES (22, 1);
INSERT INTO masi.user_role(user_id, role_id) VALUES (23, 2);

INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (24, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'ared2');
INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (25, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'rred2');

INSERT INTO masi.user_role(user_id, role_id) VALUES (24, 1);
INSERT INTO masi.user_role(user_id, role_id) VALUES (25, 2);

INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (26, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'ared3');
INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (27, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'rred3');

INSERT INTO masi.user_role(user_id, role_id) VALUES (26, 1);
INSERT INTO masi.user_role(user_id, role_id) VALUES (27, 2);

INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (28, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'ared4');
INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (29, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'rred4');

INSERT INTO masi.user_role(user_id, role_id) VALUES (28, 1);
INSERT INTO masi.user_role(user_id, role_id) VALUES (29, 2);

-- ORANGE
INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (30, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'aorange1');
INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (31, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'rorange1');

INSERT INTO masi.user_role(user_id, role_id) VALUES (30, 1);
INSERT INTO masi.user_role(user_id, role_id) VALUES (31, 2);

INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (32, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'aorange2');
INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (33, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'rorange2');

INSERT INTO masi.user_role(user_id, role_id) VALUES (32, 1);
INSERT INTO masi.user_role(user_id, role_id) VALUES (33, 2);

INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (34, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'aorange3');
INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (35, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'rorange3');

INSERT INTO masi.user_role(user_id, role_id) VALUES (34, 1);
INSERT INTO masi.user_role(user_id, role_id) VALUES (35, 2);

INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (36, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'aorange4');
INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (37, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'rorange4');

INSERT INTO masi.user_role(user_id, role_id) VALUES (36, 1);
INSERT INTO masi.user_role(user_id, role_id) VALUES (37, 2);

-- BLACK
INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (38, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'ablack1');
INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (39, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'rblack1');

INSERT INTO masi.user_role(user_id, role_id) VALUES (38, 1);
INSERT INTO masi.user_role(user_id, role_id) VALUES (39, 2);

INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (40, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'ablack2');
INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (41, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'rblack2');

INSERT INTO masi.user_role(user_id, role_id) VALUES (40, 1);
INSERT INTO masi.user_role(user_id, role_id) VALUES (41, 2);

INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (42, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'ablack3');
INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (43, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'rblack3');

INSERT INTO masi.user_role(user_id, role_id) VALUES (42, 1);
INSERT INTO masi.user_role(user_id, role_id) VALUES (43, 2);

INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (44, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'ablack4');
INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (45, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'rblack4');

INSERT INTO masi.user_role(user_id, role_id) VALUES (44, 1);
INSERT INTO masi.user_role(user_id, role_id) VALUES (45, 2);

-- WHITE
INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (46, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'awhite1');
INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (47, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'rwhite1');

INSERT INTO masi.user_role(user_id, role_id) VALUES (46, 1);
INSERT INTO masi.user_role(user_id, role_id) VALUES (47, 2);

INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (48, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'awhite2');
INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (49, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'rwhite2');

INSERT INTO masi.user_role(user_id, role_id) VALUES (48, 1);
INSERT INTO masi.user_role(user_id, role_id) VALUES (49, 2);

INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (50, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'awhite3');
INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (51, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'rwhite3');

INSERT INTO masi.user_role(user_id, role_id) VALUES (50, 1);
INSERT INTO masi.user_role(user_id, role_id) VALUES (51, 2);

-- MAGNETA
INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (52, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'amagneta1');
INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (53, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'rmagneta1');

INSERT INTO masi.user_role(user_id, role_id) VALUES (52, 1);
INSERT INTO masi.user_role(user_id, role_id) VALUES (53, 2);

INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (54, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'amagneta2');
INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (55, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'rmagneta2');

INSERT INTO masi.user_role(user_id, role_id) VALUES (54, 1);
INSERT INTO masi.user_role(user_id, role_id) VALUES (55, 2);

INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (56, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'amagneta3');
INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (57, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'rmagneta3');

INSERT INTO masi.user_role(user_id, role_id) VALUES (56, 1);
INSERT INTO masi.user_role(user_id, role_id) VALUES (57, 2);

INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (58, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'amagneta4');
INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (59, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'rmagneta4');

INSERT INTO masi.user_role(user_id, role_id) VALUES (58, 1);
INSERT INTO masi.user_role(user_id, role_id) VALUES (59, 2);

INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (60, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'amagneta5');
INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (61, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'rmagneta5');

INSERT INTO masi.user_role(user_id, role_id) VALUES (60, 1);
INSERT INTO masi.user_role(user_id, role_id) VALUES (61, 2);

-- VIOLET
INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (62, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'aviolet1');
INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (63, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'rviolet1');

INSERT INTO masi.user_role(user_id, role_id) VALUES (62, 1);
INSERT INTO masi.user_role(user_id, role_id) VALUES (63, 2);

INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (64, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'aviolet2');
INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (65, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'rviolet2');

INSERT INTO masi.user_role(user_id, role_id) VALUES (64, 1);
INSERT INTO masi.user_role(user_id, role_id) VALUES (65, 2);

INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (66, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'aviolet3');
INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (67, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'rviolet3');

INSERT INTO masi.user_role(user_id, role_id) VALUES (66, 1);
INSERT INTO masi.user_role(user_id, role_id) VALUES (67, 2);

INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (68, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'aviolet4');
INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (69, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'rviolet4');

INSERT INTO masi.user_role(user_id, role_id) VALUES (68, 1);
INSERT INTO masi.user_role(user_id, role_id) VALUES (69, 2);

-- ADDITIONAL

INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (70, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'atester1');
INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (71, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'rtester1');

INSERT INTO masi.user_role(user_id, role_id) VALUES (70, 1);
INSERT INTO masi.user_role(user_id, role_id) VALUES (71, 2);

INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (72, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'atester2');
INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (73, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'rtester2');

INSERT INTO masi.user_role(user_id, role_id) VALUES (72, 1);
INSERT INTO masi.user_role(user_id, role_id) VALUES (73, 2);

INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (74, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'atester3');
INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (75, 'test@test.com', 'test', 'test', '$2a$11$953dgTL7.TiZJpgZOQkhkOyGwa0WJm2KdaVEQeIJtnhOzFS8imgv6', 'rtester3');

INSERT INTO masi.user_role(user_id, role_id) VALUES (74, 1);
INSERT INTO masi.user_role(user_id, role_id) VALUES (75, 2);