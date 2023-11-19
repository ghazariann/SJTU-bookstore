import React from 'react';
import { Card } from 'antd';
import { Link } from 'react-router-dom';

const { Meta } = Card;

function Book({ info }) {
    return (
        <Link to={{
            pathname: '/bookDetails',
            search: '?id=' + info.id
        }} target="_self">
            <Card
                hoverable
                style={{ width: 150 }}
                cover={<img alt="image" src={info.coverImage.image} className="bookImg" />}
            >
                <Meta title={info.name} description={'¥' + info.price} />
            </Card>
        </Link>
    );
}

export default Book;