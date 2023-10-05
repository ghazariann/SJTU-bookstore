import React, { useContext } from 'react';
import { Form, Icon, Input, Button, Checkbox, message } from 'antd';
import 'antd/dist/antd.css';
import '../css/login.css';
import { UserContext } from '../UserContext';
import { useNavigate } from 'react-router-dom';

const LoginForm = (props) => {
    const { setUser, setWs } = useContext(UserContext);
    const navigate = useNavigate();
    
    const handleSubmit = (e) => {
        document.cookie = "key=value; SameSite=None; Secure";
        
        e.preventDefault();
        
        const createWebSocket = (userId) => {
          const ws = new WebSocket(`ws://127.0.0.1:8080/bookstore/${userId}`);
          
          ws.onopen = function(event) {
              console.log("WebSocket connection opened:", event);
          };
      
          ws.onmessage = function(event) {
            console.log("Received message:", event.data);
            message.success(event.data);
          };
      
          ws.onclose = function(event) {
              console.log("WebSocket connection closed:", event);
          };
          ws.onerror = function(event) {
    console.error("WebSocket Error:", event);
};
          return ws;
      };
      
        props.form.validateFields(async (err, values) => {
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
                        const user = await response.json();
                        message.success('Successfully Logged In');
                        setUser(user);
                        const ws = createWebSocket(user.id);  // Call the function to create a WebSocket
                        setWs(ws); 
                        navigate('/');
                        
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

    const { getFieldDecorator } = props.form;

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
                <Button type="primary" onClick={handleSubmit} className="login-form-button">
                    Log in
                </Button>
                Or <a href="/register">register now!</a>
            </Form.Item>
        </Form>
    );
}

const WrappedLoginForm = Form.create({ name: 'normal_login' })(LoginForm);

export default WrappedLoginForm;