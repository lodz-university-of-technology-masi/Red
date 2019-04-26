
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

INSERT INTO masi.test(id, creation_time, job_title_id, language) VALUES (1, NOW(), 1, 'EN');
INSERT INTO masi.test(id, creation_time, job_title_id, language) VALUES (2, NOW(), 2, 'EN');

ALTER SEQUENCE masi.test_seq RESTART WITH 3;

INSERT INTO masi.test_question(test_id, question_id) VALUES (1, 1);
INSERT INTO masi.test_question(test_id, question_id) VALUES (1, 2);
INSERT INTO masi.test_question(test_id, question_id) VALUES (2, 3);
INSERT INTO masi.test_question(test_id, question_id) VALUES (2, 4);

INSERT INTO masi.users(id, email, first_name, last_name, password, username) VALUES (1, 'test@test.com', 'test', 'test', '$2a$11$koESAmGxKKtlIk4dT8z6peg17Fw3A/WThS.bYLBBuekreP4j/mo9m', 'test');

ALTER SEQUENCE masi.user_seq RESTART WITH 2;

INSERT INTO masi.user_role(user_id, role_id) VALUES (1, 1);