import React from 'react';
import { useState, useEffect,useContext } from "react";

import { Button, Descriptions, Layout, List } from 'antd'
import '../css/book.css'
import { useNavigate, useLocation, Link } from "react-router-dom";
import { UserContext } from '../UserContext';

const { Content } = Layout;

function BookView() {
    const location = useLocation();
    const [book, setBook] = useState(null);
    const navigate = useNavigate();
    const [quantity, setQuantity] = useState(1);
    const { user } = useContext(UserContext);
   
    const handleAddToCart = async () => {
       
        const data = {
            book: {
                id: book.id,
            },
            user: {
                id: user.id,
            },
            quantity: quantity,
            price: quantity * book.price,
        }
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        };
        // console.log(data)
        await fetch('http://localhost:8080/cartItems', requestOptions);
        navigate('/cart');
    };
   
    useEffect(() => {
        const fetchBook = async () => {

            const bookId = new URLSearchParams(location.search).get("id");
            const response = await fetch(`http://localhost:8080/books/${bookId}`);
            const data = await response.json();
            // console.log(response)
            setBook(data);

        };
        fetchBook();
    }, []);
    if (book === null) return (<div>Loading...</div>);
    return (
        <Layout className="layout">
            <Layout>
                <Content style={{ padding: '0 50px' }}>
                    <div className="home-content">

                        <div className="content">
                            <Button type="primary" style={{ marginRight: "800px", marginBottom: '20px' }}>
                                <Link to="/">Back</Link>
                            </Button>
                            <List
                                grid={{ gutter: 16, column: 2 }}
                                dataSource={[
                                    {
                                        title: '',
                                        // content: <img alt="book cover" src={book.image} style={{ width: '100%' }} />,
                                        content: <img alt="book cover" src={book.coverImage.image} style={{ width: '100%' }} />,
                                    },
                                    {
                                        title: 'Name',
                                        content: book.name,
                                    },
                                    {
                                        title: 'Author',
                                        content: book.author,
                                    },
                                    {
                                        title: 'Type',
                                        content: book.type,
                                    },
                                    {
                                        title: 'Price',
                                        content: <span className="price">{'¥' + (quantity * book.price)}</span>,
                                    },
                                    {
                                        title: 'Quantity',
                                        content: <input type="number" min="1" max={book.inventory} value={quantity} onChange={(e) => setQuantity(e.target.value)} />,

                                    },
                                    {
                                        title: 'State',
                                        content: book.inventory !== 0 ? (
                                            <span>
                                                In stock <span className="inventory">{book.inventory} left</span>
                                            </span>
                                        ) : (
                                            <span className="status">Sold Out</span>
                                        ),
                                    },

                                    {
                                        title: 'Introduction',
                                        content: book.description,
                                    },
                                 
                                
                                ]}
                                renderItem={(item) => (
                                    <List.Item>
                                        <Descriptions title={item.title} bordered>
                                            <Descriptions.Item span={3}>{item.content}</Descriptions.Item>
                                        </Descriptions>
                                    </List.Item>
                                )}
                            />
                    
                            <div className="button-groups">
                                <Button type="danger" icon="shopping-cart" size="large" onClick={handleAddToCart} >
                                    Add to Cart
                                </Button>
                                {/* <Button type="danger" icon="pay-circle" size="large" style={{ marginLeft: '15%' }} onClick={handlePayment} ghost>
                                    Buy it now
                                </Button> */}
                            </div>
                        </div>
                        <div className={"foot-wrapper"}>
                        </div>
                    </div>
                </Content>
            </Layout>
        </Layout>
    );
}

export default BookView;