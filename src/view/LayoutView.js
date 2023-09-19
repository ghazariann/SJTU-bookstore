import React from 'react';
import {Layout} from 'antd'
import HeaderInfo from "../components/HeaderInfo";
import WrappedSideBar from "../components/SideBar";
import 'antd/dist/antd.css';

const { Header} = Layout;

function LayoutView() {
    
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

export default LayoutView;