CREATE TYPE gender AS ENUM ('MALE', 'FEMALE', 'male', 'female');

ALTER TABLE student
ALTER COLUMN gender TYPE gender
USING (gender::gender);