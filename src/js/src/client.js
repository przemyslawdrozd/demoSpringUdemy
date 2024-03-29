import fetch from 'unfetch';

export const getAllStudents = () => fetch('api/students');

export const addNewStudent = student  => 
    fetch('api/students', {
        headers: {
            'Content-Type': 'application/json'
        },
        method: 'POST', 
        body: JSON.stringify(student)
    })
        