import React from 'react';
import {Layout} from 'antd'
import 'antd/dist/antd.css';
import '../css/main.css'
import {BookCarousel} from "../components/BookCarousel";
import {SearchBar} from "../components/SearchBar";
import {BookList} from "../components/BookList";

const {Content } = Layout;


class HomeView extends React.Component{

    // componentDidMount(){
    //     this.setState({user:user});
    // }

    render(){
        return(
            <Layout className="layout">
                    <Content style={{ padding: '0 50px' }}>
                        <div className="home-content">
                            {/* <SearchBar /> */}

                            <BookCarousel />
                            <BookList /> 
                                <div className={"foot-wrapper"}>
                            </div>
                        </div>
                    </Content>
            </Layout>
        );
    }
}

export default HomeView;