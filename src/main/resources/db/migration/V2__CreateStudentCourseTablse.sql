CREATE TABLE IF NOT EXISTS course (

    course_id UUID NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description TEXT NOT NULL,
    department VARCHAR(255),
    teacher VARCHAR(100)

);

CREATE TABLE IF NOT EXISTS student_course (

    student_id UUID NOT NULL REFERENCES student(student_id),
    course_id UUID NOT NULL REFERENCES course(course_id),
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    grade INTEGER CHECK (grade >= 0 AND grade <= 100),
    UNIQUE (student_id, course_id)
);