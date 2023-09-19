import React from 'react';
import { Form, Icon, Input, Button, Checkbox,message  } from 'antd';
import 'antd/dist/antd.css';
import '../css/login.css'
import { UserContext } from '../UserContext';

class LoginForm extends React.Component {

    static contextType = UserContext;

    handleSubmit = e => {
        e.preventDefault();
        this.props.form.validateFields(async (err, values) => {
          if (!err) {
            console.log('Received values of form: ', values);
      
            try {
              const response = await fetch('http://127.0.0.1:8080/users');
              const users = await response.json();
      
              const user = users.find(u => 
                u.userAuth.email === values.username && 
                u.userAuth.password === values.password
              );
      
            if (user) {
                if (user.userAuth.disabled === true){
                    message.error('Your account has been disabled');
                }
                else{
                    message.success('Successfully Logged In');
                    this.context.setUser(user);
                    window.location.href = "/";
                }
                
              } 
            else{
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
            <Form onSubmit={this.handleSubmit} className="login-form">
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
                    <Button type="primary" htmlType="submit" className="login-form-button">
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
