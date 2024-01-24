import React, { useState, useEffect, useContext } from 'react';
import { Layout, Table, Divider, Input, DatePicker,Button } from 'antd';
import moment from 'moment';
import 'antd/dist/antd.css';
import '../css/main.css';
import StatisticsPopup from '../components/Statistics';

import { UserContext } from '../UserContext';
const { Content } = Layout;

function OrderView() {
    const { user } = useContext(UserContext);
    const [orders, setOrders] = useState([]);
    const [searchText, setSearchText] = useState('');
    const [startDate, setStartDate] = useState(null);
    const [endDate, setEndDate] = useState(null);
    const { RangePicker } = DatePicker;

    const itemColumns = [
        {
            title: 'Book Title',
            dataIndex: ['book', 'name'],
            key: 'name',
        },
        {
            title: 'Cover',
            dataIndex: ['book', 'coverImage'],
            key: 'cover',
            render: (img) => (
                <img width="80" height="100" src={img} alt="Product" />
            ),
        },
        {
            title: 'Quantity',
            dataIndex: 'quantity',
            key: 'quantity',
        },
        {
            title: 'Price',
            dataIndex: 'price',
            key: 'price',
        },
        // ... add more fields as necessary
    ];

    useEffect(() => {
        fetch('http://localhost:8080/orders')
            .then(response => response.json())
            .then(data => {
                const userOrders = data.filter(order => order.user.id === user.id);
                setOrders(userOrders);
            })
            .catch(error => console.log(error));
    }, [user.id]);

    const isSearched = (item) => {
        if (!searchText) return true;
        if (item.book.name.toLowerCase().includes(searchText.toLowerCase())) return true;
        return false;
    };

    const isWithinDate = (order) => {
        const orderDate = moment(order.orderDate);
        if (startDate && orderDate.isBefore(startDate)) return false;
        if (endDate && orderDate.isAfter(endDate)) return false;
        return true;
    };
    const [statsVisible, setStatsVisible] = useState(false);
    const showStats = () => setStatsVisible(true);
    const hideStats = () => setStatsVisible(false);
    return (
        <div>
            <Layout className="layout">
                <Content style={{ padding: '0 50px' }}>
                    <div className="form-header">My Orders</div>
                    <Input placeholder="Search book name" onChange={(e) => setSearchText(e.target.value)} style={{ width: 200 }} />
                    <RangePicker onChange={(dates) => {
                        setStartDate(dates[0]);
                        setEndDate(dates[1]);
                    }} />

                    {orders.filter(order => isWithinDate(order)).slice().reverse().map((order, index) => (
                        <div key={index} className="order-container">
                            <p><strong>Order Date:</strong> {moment(order.orderDate).format('YYYY-MM-DD')}</p>
                            <p><strong>Total Price:</strong> {order.totalPrice}</p>
                            <p><strong>Shipping Address:</strong> {order.shippingAddress}</p>
                            <Table
                                dataSource={order.orderItems.filter(item => isSearched(item))}
                                columns={itemColumns}
                                pagination={false}
                            />
                            {index < orders.length - 1 && <Divider />}
                        </div>
                    ))}
                </Content>
            </Layout>
            <Button type="primary" onClick={showStats}>Statistics</Button>
            <StatisticsPopup
                visible={statsVisible}
                handleClose={hideStats}
                orders={orders}
            />
        </div>
    );
}

export default OrderView;
