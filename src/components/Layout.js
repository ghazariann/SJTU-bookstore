import React from 'react';
import {Layout} from 'antd'
import HeaderInfo from "./HeaderInfo";
import WrappedSideBar from "./SideBar";
import 'antd/dist/antd.css';

const { Header} = Layout;

function MainLayout() {
    
    // render(){
        return(
            <Layout className="layout">
                <Header>
                    <HeaderInfo />
                </Header>
                <Layout>
                    <WrappedSideBar />
                </Layout>
            </Layout>
        );
    }
// }

export default MainLayout;