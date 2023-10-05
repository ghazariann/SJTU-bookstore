import React from 'react';
import WrappedLoginForm from '../components/LoginForm';
import 'antd/dist/antd.css';
import '../css/main.css';

function LoginView() {
    return (
        <div className="login-page">
            <div className="login-container">
                <div className="login-box">
                    <h1 className="page-title">Login</h1>
                    <div className="login-content">
                        <WrappedLoginForm />
                    </div>
                </div>
            </div>
        </div>
    );
}

export default LoginView;