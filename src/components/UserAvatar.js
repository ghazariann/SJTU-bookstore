import React from 'react';
import { Avatar, Dropdown, Menu} from 'antd';
import '../css/index.css'
import userPng from '../assets/prof-pic.jpg';
import { UserContext } from '../UserContext'; 


export class UserAvatar extends React.Component {
    static contextType = UserContext;
    handleLogout = () => {
        this.context.setUser(null); 
      }

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

        // const imgUrl = config.imgUrl + "/" + user.username + ".jpg";

        return(
            <div id="avatar">
               
                <Dropdown overlay={menu} placement="bottomRight">
                <span className="name">Hi, {user.name}</span>
                    {/* <Avatar src={userPng} style={{cursor:"pointer"}}/> */}
                </Dropdown>
            </div>
        );
    }
}