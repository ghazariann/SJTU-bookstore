import React from 'react';
import { Dropdown, Menu, message} from 'antd';
import '../css/index.css'
import { UserContext } from '../UserContext'; 


export class UserAvatar extends React.Component {
    static contextType = UserContext;
    
    handleLogout = async () => {

        try {
          const response = await fetch('http://127.0.0.1:8080/logout', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
            },
            credentials: 'include'
          });
      
          if (response.status === 200) {
            const data = await response.json();  
            
            if (data && data.elapsedTime) {
              message.success(`Elapsed time: ${data.elapsedTime} s`);
            } else {
              message.error("Elapsed time is not available");
            }
            this.context.setUser(null);  // Clear the user context
            setTimeout(() => {
              window.location.href = "/login"; // bug
            }, 1000); 
          } else {
            message.error('Logout failed');
          }
        } catch (error) {
          console.error("Error:", error);
        }
      };

    render() {

        const menu = (
            <Menu>
                <Menu.Item>
                    <a target="_blank" rel="noopener noreferrer" href="./profile">
                        Show Profile
                    </a>
                </Menu.Item>
                <Menu.Item>
                    <a onClick={this.handleLogout} href="#"> 
                        Log Out
                    </a>
                </Menu.Item>
            </Menu>
        );

        const {user} = this.props;

        return(
            <div id="avatar">
               
                <Dropdown overlay={menu} placement="bottomRight">
                <span className="name">Hi, {user.name}</span>
                    {}
                </Dropdown>
            </div>
        );
    }
}