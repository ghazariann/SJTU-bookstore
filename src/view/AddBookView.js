import React, { useState, useContext } from 'react';
import { Form, Input, Button, message } from 'antd';
import { UserContext } from '../UserContext';
import '../css/addBook.css';

function AddBookView() {
    const { user } = useContext(UserContext);
    
    const [formValues, setFormValues] = useState({
        name: '',
        author: '',
        description: '',
        price: '',
        image: '',
        type: '',
    });

    const handleChange = (e) => {
        setFormValues({
            ...formValues,
            [e.target.name]: e.target.value,
        });
    };

    const handleSubmit = () => {
        fetch('http://localhost:8080/books', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(formValues),
        })
            .then((res) => {
                if (!res.ok) throw new Error(res.status);
                else return res.json();
            })
            .then(() => {
                message.success('Book added successfully');
            })
            .catch((error) => {
                console.log('error', error);
                message.error('Failed to add book');
            });
    };

    if (user.type !== 0) {
        return (
            <div className="no-permission">
                <h2>No Permissions</h2>
                <p>You don't have the permissions to access this page.</p>
            </div>
        );
    }

    return (
        <div className="add-book-form-container">
            <h2>Add a new book</h2>
            <Form name="addBook" autoComplete="off">
                <Form.Item>
                    <Input name="name" value={formValues.name} onChange={handleChange} placeholder="Book Name" />
                </Form.Item>
                <Form.Item>
                    <Input name="author" value={formValues.author} onChange={handleChange} placeholder="Author Name" />
                </Form.Item>
                <Form.Item>
                    <Input name="description" value={formValues.description} onChange={handleChange} placeholder="Description" />
                </Form.Item>
                <Form.Item>
                    <Input name="price" value={formValues.price} onChange={handleChange} placeholder="Price" />
                </Form.Item>
                <Form.Item>
                    <Input name="image" value={formValues.image} onChange={handleChange} placeholder="Image URL" />
                </Form.Item>
                <Form.Item>
                    <Input name="type" value={formValues.type} onChange={handleChange} placeholder="Type" />
                </Form.Item>
                <Form.Item>
                    <Button type="primary" onClick={handleSubmit}>
                        Add Book
                    </Button>
                </Form.Item>
            </Form>
        </div>
    );
};

export default AddBookView;
