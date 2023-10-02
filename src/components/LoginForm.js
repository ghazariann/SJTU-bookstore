import React from 'react';
import { Form, Icon, Input, Button, Checkbox,message  } from 'antd';
import 'antd/dist/antd.css';
import '../css/login.css'
import { UserContext } from '../UserContext';
import axios from 'axios';

class LoginForm extends React.Component {

    static contextType = UserContext;

    handleSubmit = e => {
      document.cookie = "key=value; SameSite=None; Secure";

        e.preventDefault();
        // alert('Received values of form: ');  
        this.props.form.validateFields(async (err, values) => {
          if (!err) {
            try {
              const response = await fetch('http://127.0.0.1:8080/login', {
                method: 'POST',
                headers: {
                  'Accept': 'application/json',
                  'Content-Type': 'application/json',
                  'Cache': 'no-cache'
                },
                credentials: 'include',
                
                body: JSON.stringify({
                  email: values.username,
                  password: values.password,
                }),
              
              });
              
              if (response.status === 200) {
                const user = await response.json(); // Get the user object
                message.success('Successfully  In');
                // alert('Successfully  In');
                this.context.setUser(user); // Set the user in the context
                setTimeout(() => {
                  window.location.href = "/";
                }, 500); // Wait 2 seconds
              } else if (response.status === 403) {
                message.error('Your account has been disabled');
              } else {
                message.error('Username or Password is incorrect');
              }
      
            } catch (error) {
              console.error("Error:", error);
            }
          }
        });
      };
      

    render() {
        const { getFieldDecorator } = this.props.form;
        return (
          <Form className="login-form">
              <Form.Item>
                  {getFieldDecorator('username', {
                      rules: [{ required: true, message: 'Please input your username!' }],
                  })(
                      <Input
                          prefix={<Icon type="user" style={{ color: 'rgba(0,0,0,.25)' }} />}
                          placeholder="Username"
                      />,
                  )}
              </Form.Item>
              <Form.Item>
                  {getFieldDecorator('password', {
                      rules: [{ required: true, message: 'Please input your Password!' }],
                  })(
                      <Input
                          prefix={<Icon type="lock" style={{ color: 'rgba(0,0,0,.25)' }} />}
                          type="password"
                          placeholder="Password"
                      />,
                  )}
              </Form.Item>
              <Form.Item>
                  {getFieldDecorator('remember', {
                      valuePropName: 'checked',
                      initialValue: true,
                  })(<Checkbox>Remember me</Checkbox>)}
                  <a className="login-form-forgot" href="">
                      Forgot password
                  </a>
                  <Button type="primary" onClick={this.handleSubmit} className="login-form-button">
                      Log in
                  </Button>
                  Or <a href="/register">register now!</a>
              </Form.Item>
          </Form>
      );
    }
}

const WrappedLoginForm = Form.create({ name: 'normal_login' })(LoginForm);

export default WrappedLoginForm
