import React from 'react';
import { Table, Input, DatePicker } from 'antd';
import moment from 'moment';

class OrderListView extends React.Component {
    state = {
        orders: [],
        searchText: '',
        startDate: null,
        endDate: null,
    };

    componentDidMount() {
        fetch('http://127.0.0.1:8080/orders')
            .then(res => res.json())
            .then(data => this.setState({ orders: data }))
            .catch(err => console.log(err));
    }

    onInputChange = (e) => {
        this.setState({ searchText: e.target.value });
    };

    onStartDateChange = (date) => {
        this.setState({ startDate: date });
    };

    onEndDateChange = (date) => {
        this.setState({ endDate: date });
    };

    isSearched = (item) => {
        const { searchText } = this.state;
        if (!searchText) return true;
        if (item.book.name.toLowerCase().includes(searchText.toLowerCase())) return true;
        return false;
    };

    isWithinDate = (order) => {
        const { startDate, endDate } = this.state;
        const orderDate = moment(order.orderDate);
        if (startDate && orderDate.isBefore(startDate)) return false;
        if (endDate && orderDate.isAfter(endDate)) return false;
        return true;
    };

    render() {
        const { RangePicker } = DatePicker;
        const filteredData = this.state.orders.filter(order => this.isWithinDate(order));

        const columns = [
            {
                title: 'User',
                dataIndex: ['user', 'name'],
                key: 'user',
            },
            {
                title: 'Order Date',
                dataIndex: 'orderDate',
                key: 'orderDate',
            },
            {
                title: 'Total Price',
                dataIndex: 'totalPrice',
                key: 'totalPrice',
            },
            {
                title: 'Shipping Address',
                dataIndex: 'shippingAddress',
                key: 'shippingAddress',
            },
        ];

        const expandedRowRender = (record) => {
            const itemColumns = [
                {
                    title: 'Book ID',
                    dataIndex: ['book', 'id'],
                    key: 'bookId',
                },
                {
                    title: 'Book Name',
                    dataIndex: ['book', 'name'],
                    key: 'bookName',
                },
                { title: 'Quantity', dataIndex: 'quantity', key: 'quantity', }, { title: 'Price', dataIndex: 'price', key: 'price', },
            ];
            return <Table columns={itemColumns} dataSource={record.orderItems.filter(this.isSearched)} pagination={false} />;
        };

        return (
            <div>
                <Input placeholder="Search book name" onChange={this.onInputChange} style={{ width: 200 }} />
                <RangePicker onChange={(dates) => {
                    this.onStartDateChange(dates[0]);
                    this.onEndDateChange(dates[1]);
                }} />
                <Table
                    className="components-table-demo-nested"
                    columns={columns}
                    expandedRowRender={expandedRowRender}
                    dataSource={filteredData}
                />
            </div>
        );
    }
}

export default OrderListView;
