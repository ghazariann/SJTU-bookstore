import React from 'react';
import { Form, Input, Button, notification } from 'antd';

class RegisterView extends React.Component {
  state = {
    confirmDirty: false,
  };

  fetchUsers = async () => {
    const response = await fetch('http://localhost:8080/users');
    const data = await response.json();
    return data;
  };

  handleSubmit = async (e) => {
    e.preventDefault();
    this.props.form.validateFieldsAndScroll(async (err, values) => {
      if (!err) {
        const users = await this.fetchUsers();
        const isUserExist = users.some((user) => user.name === values.name);

        if (isUserExist) {
          notification.error({
            message: 'Error',
            description: 'Username already exists.',
          });
          return;
        }
        notification.success({
            message: 'Success',
            description: 'User registered successfully.',
          });
        const response = await fetch('http://localhost:8080/users', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(values),
        });

        if (response.ok) {
          notification.success({
            message: 'Success',
            description: 'User registered successfully.',
          });
          this.props.form.resetFields();
        }
      }
    });
  };

  handleConfirmBlur = (e) => {
    const { value } = e.target;
    this.setState({ confirmDirty: this.state.confirmDirty || !!value });
  };

  compareToFirstPassword = (rule, value, callback) => {
    const { form } = this.props;
    if (value && value !== form.getFieldValue('userAuth.password')) {
      callback('The two passwords that you entered do not match!');
    } else {
      callback();
    }
  };

  validateToNextPassword = (rule, value, callback) => {
    const { form } = this.props;
    if (value && this.state.confirmDirty) {
      form.validateFields(['confirm'], { force: true });
    }
    callback();
  };

  render() {
    const { getFieldDecorator } = this.props.form;

    return (
      <div className="register">
        <Form onSubmit={this.handleSubmit} className="register-form">
          <Form.Item>
            {getFieldDecorator('name', {
              rules: [{ required: true, message: 'Please input your username!' }],
            })(<Input placeholder="Username" />)}
          </Form.Item>

          <Form.Item>
            {getFieldDecorator('telephone', {
              rules: [{ required: true, message: 'Please input your telephone number!' }],
            })(<Input placeholder="Telephone" />)}
          </Form.Item>

          <Form.Item>
            {getFieldDecorator('address', {
              rules: [{ required: true, message: 'Please input your address!' }],
            })(<Input placeholder="Address" />)}
          </Form.Item>

          <Form.Item>
            {getFieldDecorator('userAuth.email', {
              rules: [
                { type: 'email', message: 'The input is not a valid email!' },
                { required: true, message: 'Please input your email!' },
              ],
            })(<Input placeholder="Email" />)}
          </Form.Item>

          <Form.Item>
            {getFieldDecorator('userAuth.password', {
              rules: [
                { required: true, message: 'Please input your password!' },
                { validator: this.validateToNextPassword },
              ],
            })(<Input type="password" placeholder="Password" />)}
          </Form.Item>

          <Form.Item>
            {getFieldDecorator('confirm', {
              rules: [
                { required: true, message: 'Please confirm your password!' },
                { validator: this.compareToFirstPassword },
              ],
            })(<Input type="password" onBlur={this.handleConfirmBlur} placeholder="Confirm Password" />)}
          </Form.Item>

          <Form.Item>
            <Button type="primary" htmlType="submit">
              Register
            </Button>
          </Form.Item>
        </Form>
      </div>
    );
  }
}

export default Form.create()(RegisterView);
