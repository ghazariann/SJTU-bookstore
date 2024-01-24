import React, { useContext } from 'react';
import { Dropdown, Menu, message } from 'antd';
import '../css/index.css';
import { UserContext } from '../UserContext';
import { useNavigate } from 'react-router-dom';

const UserAvatar = (props) => {
    const { user, ws, setUser, setWs} = useContext(UserContext);
    const navigate = useNavigate();

    const handleLogout = async () => {
        try {
            const response = await fetch('http://127.0.0.1:8080/logout', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                credentials: 'include',
            });

            if (response.status === 200) {
                const data = await response.json();

                if (data && data.elapsedTime) {
                    message.success(`Elapsed time: ${data.elapsedTime} s`);
                } else {
                    message.error("Elapsed time is not available");
                }

                if (ws) {
                    ws.close();
                    setWs(null);
                }

                setUser(null); // Clear the user context
                navigate("/login");
               
            } else {
                message.error('Logout failed');
            }
        } catch (error) {
            console.error("Error:", error);
        }
    };

    const menu = (
        <Menu>
            <Menu.Item>
                <a target="_blank" rel="noopener noreferrer" href="./profile">
                    Show Profile
                </a>
            </Menu.Item>
            <Menu.Item>
                <a onClick={handleLogout} href="#">
                    Log Out
                </a>
            </Menu.Item>
        </Menu>
    );

    return (
        <div id="avatar">
            <Dropdown overlay={menu} placement="bottomRight">
                <span className="name">Hi, {user.name}</span>
            </Dropdown>
        </div>
    );
};

export default UserAvatar;
