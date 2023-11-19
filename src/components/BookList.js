import React, { useState, useEffect } from 'react';
import { List, Input, Button } from 'antd';
import Book from './Book';

export function BookList() {
    const [books, setBooks] = useState([]);
    const [searchText, setSearchText] = useState('');
    const [tagSearchText, setTagSearchText] = useState('');

    useEffect(() => {
        fetch('http://localhost:8080/books')
            .then(response => response.json())
            .then(data => setBooks(data));
    }, []);

    const filteredBooks = books.filter(book =>
        book.name.toLowerCase().includes(searchText.toLowerCase())
    );

    const handleSearchByTag = () => {
        const requestOptions = {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ tag: tagSearchText })
        };

        fetch('http://localhost:8080/books/filterByTag', requestOptions)
            .then(response => response.json())
            .then(data => setBooks(data))
            .catch(error => console.error('Error:', error));
    };

    return (
        <div>
            <Input
                placeholder="Search book name"
                onChange={(e) => setSearchText(e.target.value)}
                style={{ width: 200, marginBottom: '20px' }}
            />
            <Input
                placeholder="Search by tag"
                onChange={(e) => setTagSearchText(e.target.value)}
                style={{ width: 200, marginRight: '10px' }}
            />
            <Button onClick={handleSearchByTag} type="primary">
                Search by Tag
            </Button>
            <List
                grid={{ gutter: 10, column: 4 }}
                dataSource={filteredBooks}
                pagination={{
                    onChange: page => {
                        // console.log(page);
                    },
                    pageSize: 16,
                }}
                renderItem={item => (
                    <List.Item>
                        <Book info={item} />
                    </List.Item>
                )}
            />
        </div>
    );
}

export default BookList;