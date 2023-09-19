import React, { useState, useEffect } from 'react';
import { List , Input} from 'antd';
import { Book } from './Book';

export function BookList() {
    const [books, setBooks] = useState([]);
    const [searchText, setSearchText] = useState('');

    useEffect(() => {
        fetch('http://localhost:8080/books')
            .then(function(response) {
                return response.json();
            }).then(function(data) {
                setBooks(data);
            });
    }, []);
    const filteredBooks = books.filter(book =>
        book.name.toLowerCase().includes(searchText.toLowerCase())
    );
    return (
        <div>
            <Input placeholder="Search book name" onChange={(e) => setSearchText(e.target.value)} style={{ width: 200, marginBottom: '20px' }} />
            <List
                grid={{ gutter: 10, column: 4 }}
                dataSource={filteredBooks}
                pagination={{
                    onChange: function(page) {
                        console.log(page);
                    },
                    pageSize: 16,
                }}
                renderItem={function(item) {
                    return (
                        <List.Item>
                            <Book info={item} />
                        </List.Item>
                    );
                }}
            />
        </div>
    );
}
