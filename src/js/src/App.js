import React, { Component } from 'react';
import Container from './Container';
import Footer from './Footer';
import './App.css';
import { getAllStudents } from './client'; // get from client.js
import AddstudentForm from './forms/AddStudentForm';
import { 
  Table, 
  Avatar, 
  Spin, 
  Icon, 
  Modal
} from 'antd';

const getIndicatorAntIcon = <Icon type="loading" style={{ fontSize: 24 }} spin />;

class App extends Component {

  state = {
    students : [],
    isFetching: false,
    isAddStudentModalVisible: false
  }

  componentDidMount () {
    this.fetchStudents();
  }

  openAddStudentModalVisible = () => this.setState({isAddStudentModalVisible: true})

  closeAddStudentModalVisible = () => this.setState({isAddStudentModalVisible: false})

  fetchStudents = () => {
    this.setState({
      isFetching: true
    });
  
    getAllStudents()
      .then(res => res.json()
      .then(students => {
        console.log(students);
        this.setState({
          students,
          isFetching: false
        });
      }));
  }

    render() {

      const { students, isFetching, isAddStudentModalVisible } = this.state;

      if(isFetching) {
        return (
          <Container>
            <Spin indicator={getIndicatorAntIcon}/>
          </Container>
        )
      }

      if (students && students.length) {
        
        const columns = [
          {
            title: '',
            key: 'avatar',
            render: (text, student) => (
              <Avatar siz='large'>
                {`${student.firstName.charAt(0).toUpperCase()}${student.lastName.charAt(0).toUpperCase()}`}
              </Avatar>
            )
          },
          {
            title: 'Student Id',
            dataIndex: 'studentId',
            key: 'studentId'
          },
          {
            title: 'First Name',
            dataIndex: 'firstName',
            key: 'firstName'
          },
          {
            title: 'Last Name',
            dataIndex: 'lastName',
            key: 'lastName'
          },
          {
            title: 'Email',
            dataIndex: 'email',
            key: 'email'
          },
          {
            title: 'Gender',
            dataIndex: 'gender',
            key: 'gender'
          },
          {
            title: 'Age',
            dataIndex: 'age',
            key: 'age'
          }
        ];

        return (
          <Container>
            <Table 
            style ={ {marginBottom: '100px'}}
              dataSource={students} 
              columns={columns} 
              pagination={false}
              rowKey='studentId'/>
              <Modal
                title='Add new Student'
                visible={isAddStudentModalVisible}
                onOk={this.closeAddStudentModalVisible}
                onCancel={this.closeAddStudentModalVisible}
                width={1000}>
                  <AddstudentForm/>
              </Modal>
              <Footer numberOfStudents={students.length}
              handleAddStudentClickEvent={this.openAddStudentModalVisible}/>
          </Container>
        );
      }
      return <h1>No Students found</h1>
    }
}

export default App;
