CREATE TABLE students (
    student_id SERIAL PRIMARY KEY,
    last_name VARCHAR(255),
    first_name VARCHAR(255),
    middle_name VARCHAR(255),
    admission_year INTEGER,
    study_form VARCHAR(255),
    group_number VARCHAR(255)
);

CREATE TABLE curriculum (
    curriculum_id SERIAL PRIMARY KEY,
    discipline VARCHAR(255),
    semester INTEGER,
    hours INTEGER,
    exam_or_credit VARCHAR(255)
);

CREATE TABLE academic_records (
    record_id SERIAL PRIMARY KEY,
    year INTEGER,
    semester INTEGER,
    student_id INTEGER REFERENCES students(student_id),
    curriculum_id INTEGER REFERENCES curriculum(curriculum_id),
    grade INTEGER
);

INSERT INTO students (last_name, first_name, middle_name, admission_year, study_form, group_number)
VALUES
    ('Иванов', 'Иван', 'Иванович', 2020, 'Очная', 'Группа 101'),
    ('Петров', 'Петр', 'Петрович', 2021, 'Заочная', 'Группа 102'),
    ('Сидоров', 'Сидор', 'Сидорович', 2020, 'Очная', 'Группа 101');

INSERT INTO curriculum (discipline, semester, hours, exam_or_credit)
VALUES
    ('Математика', 1, 90, 'Экзамен'),
    ('Физика', 1, 60, 'Зачет'),
    ('История', 2, 75, 'Экзамен');

INSERT INTO academic_records (year, semester, student_id, curriculum_id, grade)
VALUES
    (2020, 1, 1, 1, 85),
    (2020, 1, 2, 2, NULL), 
    (2021, 1, 3, 1, 92),
    (2021, 2, 1, 3, 78),
    (2021, 2, 2, 3, 90);

CREATE OR REPLACE FUNCTION calculate_student_avg_grade(id_student bigint,sub character varying(255))
RETURNS double precision AS $$
DECLARE
	avg_grade double precision := 0;
    total_grade DOUBLE PRECISION := 0;
    total_count INT := 0;
    rec RECORD;
BEGIN
    FOR rec IN SELECT grade FROM academic_records WHERE student_id = id_student AND curriculum_id IN (SELECT curriculum_id FROM curriculum WHERE discipline = sub)
    LOOP
        total_grade := total_grade + rec.grade;
        total_count := total_count + 1;
    END LOOP;

    IF total_count > 0 THEN
		avg_grade := total_grade / total_count;
		RETURN avg_grade;
    ELSE
        avg_grade := 0;
		RETURN avg_grade;
    END IF;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE PROCEDURE add_student_proc(
    last_name VARCHAR(255),
    first_name VARCHAR(255),
    middle_name VARCHAR(255),
    admission_year INTEGER,
    study_form VARCHAR(255),
    group_number_arg VARCHAR(255) -- Переименовали аргумент
) AS $$
BEGIN
    -- Проверка на уникальность номера группы
    IF NOT EXISTS (
        SELECT 1
        FROM students
        WHERE group_number = group_number_arg
    ) THEN
        INSERT INTO students (last_name, first_name, middle_name, admission_year, study_form, group_number)
        VALUES (last_name, first_name, middle_name, admission_year, study_form, group_number_arg);
    ELSE
        RAISE EXCEPTION 'Группа с номером % уже существует', group_number_arg;
    END IF;
END;
$$ LANGUAGE plpgsql;