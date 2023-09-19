import React from 'react';
import { Card } from 'antd';
import {  } from 'react-router-dom';

import {Link} from 'react-router-dom'

const { Meta } = Card;

export class Book extends React.Component{


    render() {

        const {info} = this.props;

        return (
            <Link to={{
                pathname: '/bookDetails',
                search: '?id=' + info.id}}
                target="_self"
            >
            <Card
                hoverable
                style={{width: 150}}
                cover={<img alt="image" src={info.image} className={"bookImg"}/>}
                // onClick={this.showBookDetails.bind(this, info.bookId)}
            >
                <Meta title={info.name} description={'¥' + info.price}/>
            </Card>
            </Link>
        );
    }

}