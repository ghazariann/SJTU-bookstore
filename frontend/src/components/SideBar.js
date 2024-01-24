import React, {useContext} from 'react'
import { Menu, Layout, Icon } from 'antd'
import { Outlet, Link } from "react-router-dom";
import '../css/index.css'
import { useLocation } from 'react-router-dom';
import { UserContext } from '../UserContext';

const { Sider } = Layout;

export class SideBar extends React.Component {

    state = {
        collapsed: false,
    };

    onCollapse = collapsed => {
        if (collapsed) {

        }
        // console.log(collapsed);
        this.setState({ collapsed });
    };


    render() {
        const { currentPath, userType } = this.props;
        let selectedKey = "1";
        if (currentPath === '/cart') {
            selectedKey = "2";
        } else if (currentPath === '/orders') {
            selectedKey = "3";
        } else if (currentPath === '/profile') {
            selectedKey = "4";
        }
        const menuItemsAdmin = [
            { key: "1", icon: "book", text: "Book List", link: "/book-list" },
            { key: "2", icon: "usergroup-add", text: "User List", link: "/users-list" },
            { key: "3", icon: "unordered-list", text: "Orders List", link: "/orders-list" },
            { key: "4", icon: "user", text: "Profile", link: "/profile" },
        ];

        const menuItemsUser = [
            { key: "1", icon: "book", text: "Books", link: "/" },
            { key: "2", icon: "shopping-cart", text: "My Cart", link: "/cart" },
            { key: "3", icon: "unordered-list", text: "Orders", link: "/orders" },
            { key: "4", icon: "user", text: "My Profile", link: "/profile" },
        ];

        const menuItems = userType === 0 ? menuItemsAdmin : menuItemsUser;

        return (
            <>
            <div style={{ width: this.state.collapsed ? "80px" : "180px", maxWidth: this.state.collapsed ? "80px" : "180px", minWidth: this.state.collapsed ? "80px" : "180px" }}>
                <div className="mySider">
                    <Sider collapsible collapsed={this.state.collapsed} width="180px" onCollapse={this.onCollapse} className="sider" style={{ background: '#fff', flexDirection: 'column-reverse' }} reverseArrow>
                    <Menu defaultSelectedKeys={[selectedKey]} mode="inline" style={ {marginTop: '60px'}}>
                    {menuItems.map(item => (
                        <Menu.Item key={item.key}>
                            <Icon type={item.icon} style={{ fontSize: '18px' }} />
                            <span style={{ fontSize: '16px'}}>
                                {item.text}
                            </span>
                            <Link style={{ color: 'gray' }} to={item.link}></Link>
                        </Menu.Item>
                    ))}
                </Menu>
                    </Sider>
                </div>
            </div>
            
            <div id="detail">
        <Outlet />
        </div>
            </>

        );
    }

}
export default function WrappedSideBar() {
    const location = useLocation();
    const { user } = useContext(UserContext);
    const userType = user?.type ?? 1;
    return <SideBar currentPath={location.pathname} userType={userType} />
}