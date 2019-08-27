import React, { Component } from 'react';
import { Formik } from 'formik';
import { Input, Button, Tag  } from 'antd';
import { addNewStudent } from '../client'

const inputBottomMargin = {marginBottom: '8px'};
const tagStyle = { backgroundColor: '#f50', color: 'white', ...inputBottomMargin};

class AddStudentForm extends Component {
    render () {
        return (
            <Formik
            initialValues={{ firstName: '', lastName: '', email: '', gender: '', age : ''}}
            validate={values => {
                let errors = {};

                if(!values.firstName) {
                    errors.firstName = 'First Name Required'
                }

                if(!values.lastName) {
                    errors.lastName = 'Last Name Required'
                }

                if(!values.gender) {
                    errors.gender = 'Gender Required'
                } else if(!['MALE', 'male', 'FEMALE', 'female'].includes(values.gender)) {
                    errors.gender = 'Gander must be (MALE, male, FEMALE, female)'
                }
                if (!values.email) {
                    errors.email = 'Email Required';
                } else if (
                !/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i.test(values.email)
                ) {
                    errors.email = 'Invalid email address';
                }

                if (!values.age) {
                    errors.age = 'Age Required'
                }
    
                return errors;
            }}
            onSubmit={(student, { setSubmitting }) => {
                addNewStudent(student).then(() => {
                    alert(JSON.stringify(student));
                    setSubmitting(false);
                })
            }}>
            {({
                values,
                errors,
                touched,
                handleChange,
                handleBlur,
                handleSubmit,
                isSubmitting,
                submitForm,
                isValid
                /* and other goodies */
            }) => (
                <form onSubmit={handleSubmit}>
                <Input
                    style={inputBottomMargin}
                    name="firstName"
                    onChange={handleChange}
                    onBlur={handleBlur}
                    value={values.firstName}
                    placeholder='First Name. E.g John'
                />
                {errors.firstName && touched.firstName
                     && <Tag style={tagStyle}>{errors.firstName}</Tag>}
                <Input
                    style={inputBottomMargin}
                    name="lastName"
                    onChange={handleChange}
                    onBlur={handleBlur}
                    value={values.lastName}
                    placeholder='Last Name. E.g Johnes'
                />
                {errors.lastName && touched.lastName
                    && <Tag style={tagStyle}>{errors.lastName}</Tag>}
                <Input
                    style={inputBottomMargin}
                    name="email"
                    type='email'
                    onChange={handleChange}
                    onBlur={handleBlur}
                    value={values.email}
                    placeholder='E-mail. E. g. example@gmail.com'
                />
                {errors.email && touched.email 
                    && <Tag style={tagStyle}>{errors.email}</Tag>}
                <Input
                    style={inputBottomMargin}
                    name="gender"
                    onChange={handleChange}
                    onBlur={handleBlur}
                    value={values.gender}
                    placeholder='Gender. E.g. Male or Female'
                />
                {errors.gender && touched.gender
                    && <Tag style={tagStyle}>{errors.gender}</Tag>}
                <Input
                    style={inputBottomMargin}
                    name="age"
                    onChange={handleChange}
                    onBlur={handleBlur}
                    value={values.age}
                    placeholder='Age'
                />
                {errors.age && touched.age
                    && <Tag style={tagStyle}>{errors.age}</Tag>}
                <Button 
                    onClick={() => submitForm()} 
                    type="submit" 
                    disabled={isSubmitting | (touched && !isValid)}>
                    Submit
                </Button>
                </form>
            )}
            </Formik>
        );
    }
}

export default AddStudentForm;