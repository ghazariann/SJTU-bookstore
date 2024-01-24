import React from 'react';
import { Layout, Table, Button, message, Checkbox  } from 'antd'
import 'antd/dist/antd.css';
import '../css/main.css'
import { useState, useEffect, useContext } from "react";
import { UserContext } from '../UserContext';
import { useNavigate } from 'react-router';


const { Content } = Layout;

function CartView() {
    const navigate = useNavigate();
    const { user } = useContext(UserContext);
    const [selectedCartItems, setSelectedCartItems] = useState([]);

    const columns = [
        {
            title: 'Cover',
            dataIndex: 'cover',
            key: 'cover',
            render: (img) => (
                <img width="80" height="100" src={img} alt="Product" />
            ),
        },
        {
            title: 'Title',
            dataIndex: 'name',
            key: 'name',
            className: 'title-column',
        },
        {
            title: 'Quantity',
            dataIndex: 'quantity',
            key: 'quantity',
            className: 'amount-column',
        },
        {
            title: 'Price',
            dataIndex: 'price',
            key: 'price',
            className: 'price-column',
        },
        {
            title: 'Action',
            dataIndex: 'id',
            key: 'Remove',
            render: (id, record) => (
                <Button type="danger" onClick={() => handleRemove(id, record)}>
                    Remove
                </Button>
            ),
        },
        {
            title: 'Select',
            dataIndex: 'select',
            render: (_, record) => (
                <Checkbox className="custom-checkbox"
                    onChange={e => handleSelectChange(e, record)}
                />
            ), 
        },
    ];

    const [cartItem, setcartItem] = useState([]);
    const handleSelectChange = (e, record) => {
        if (e.target.checked) {
            setSelectedCartItems(prev => [...prev, record]);
        } else {
            setSelectedCartItems(prev => prev.filter(cartItem => cartItem.id !== record.id));
        }
    };
    
    const handleOrder = async () => {
        // Prepare the order
        // console.log(selectedCartItems)
        const orderItems = selectedCartItems.map(cartItem => ({
            book: {
                id: cartItem.bookId
            },
            quantity: cartItem.quantity,
            price: cartItem.price,
        }));

        const order = {
            user: {
                id: user.id,
            },
            orderDate: new Date().toISOString().split('T')[0], 
            totalPrice: 0,// orderItems.reduce((total, item) => total + (item.price ), 0), // * item.quantity
            shippingAddress: user.address,  
            orderItems,
        };

        // POST /orders request
        try {
            const response = await fetch('http://localhost:8080/orders', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(order)
            });

            if (response.ok) {
                // Remove ordered cartItem from the cart
                selectedCartItems.forEach(async (cartItem) => {
                    await fetch(`http://localhost:8080/cartItems/${cartItem.id}`, {
                        method: 'DELETE',
                    });
                });

                // Update the cartItem in the state
                setcartItem(prevcartItem => prevcartItem.filter(cartItem => !selectedCartItems.find(selectedCartItem => selectedCartItem.id === cartItem.id)));
                setSelectedCartItems([]);
                navigate('/orders');
                // message.success('Order placed successfully');
            } else {
                throw new Error('Failed to place the order');
            }
        } catch (error) {
            console.log(error);
            message.error('Failed to place the order');
        }
    };
    useEffect(() => {
    
        fetch('http://localhost:8080/cartItems')
            .then(response => response.json())
            .then(data => {
                // console.log(data)
                const cartItemData = data
                    .filter(cartItem => cartItem.user.id === user.id)
                    .map(cartItem => ({
                        id: cartItem.id,
                        bookId: cartItem.book.id,
                        cover: cartItem.book.image,
                        name: cartItem.book.name,
                        price: cartItem.price,
                        quantity: cartItem.quantity,
                    }));
                setcartItem(cartItemData);
            })
            .catch(error => console.log(error));
    }, []);

    const handleRemove = async (id, record) => {
        try {
            console.log("id - ", id)
            await fetch(`http://localhost:8080/cartItems/${id}`, {
                method: 'DELETE',
            });
            setcartItem(prevcartItem => prevcartItem.filter(book => book.id !== id));
            message.success('Book removed from cart successfully');
        } catch (error) {
            console.log(error);
            message.error('Error removing book from cart');
        }
    };

    return (
            <Layout className="layout">
                <Content style={{ padding: '0 50px' }}>
                    <div className="form-header">My Shopping Cart</div>
                    <div className="home-content">
                        {/* <SearchBar /> */}
                    </div>
                    <Table
                        className="shopping-cart"
                        dataSource={cartItem}
                        columns={columns}
                        pagination={false}
                    />
                    <Button type="primary" onClick={handleOrder} style={{ float: 'right', marginTop: '10px'}}>Order</Button>

                </Content>

            </Layout>
        );
}

export default CartView;