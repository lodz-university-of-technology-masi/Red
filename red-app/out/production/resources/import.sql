INSERT INTO masi.job_title(id, name, active) VALUES (1, 'Junior Java Developer', true);
INSERT INTO masi.job_title(id, name, active) VALUES (2, 'Senior Java Developer', true);
INSERT INTO masi.job_title(id, name, active) VALUES (3, 'Junior Software Tester', true);
INSERT INTO masi.job_title(id, name, active) VALUES (4, 'Senior Software Tester', true);

ALTER SEQUENCE masi.job_title_seq RESTART WITH 5;

INSERT INTO masi.role(id, name, active) VALUES (1, 'MODERATOR', true);
INSERT INTO masi.role(id, name, active) VALUES (2, 'EDITOR', true);
INSERT INTO masi.role(id, name, active) VALUES (3, 'CANDIDATE', true);

ALTER SEQUENCE masi.role_seq RESTART WITH 4;

INSERT INTO masi.question(id, content, creation_time) VALUES (1, 'How long is your experience in Java?', NOW());
INSERT INTO masi.question(id, content, creation_time) VALUES (2, 'Can boolean be null in Java?', NOW());
INSERT INTO masi.question(id, content, creation_time) VALUES (3, 'How to compare Strings for structural equality?', NOW());
INSERT INTO masi.question(id, content, creation_time) VALUES (4, 'What is 2+2?', NOW());

ALTER SEQUENCE masi.question_seq RESTART WITH 5;

INSERT INTO masi.test(id, creation_time, job_title_id) VALUES (1, NOW(), 1);
INSERT INTO masi.test(id, creation_time, job_title_id) VALUES (2, NOW(), 2);

ALTER SEQUENCE masi.test_seq RESTART WITH 3;

INSERT INTO masi.test_question(test_id, question_id) VALUES (1, 1);
INSERT INTO masi.test_question(test_id, question_id) VALUES (1, 2);
INSERT INTO masi.test_question(test_id, question_id) VALUES (2, 3);
INSERT INTO masi.test_question(test_id, question_id) VALUES (2, 4);

