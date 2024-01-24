import React, { useState, useEffect } from 'react';
import { List, Input, Button, Modal } from 'antd';
import Book from './Book';

export function BookList() {
    const [books, setBooks] = useState([]);
    const [searchText, setSearchText] = useState('');
    const [filteredBooks, setFilteredBooks] = useState([]); // New state for filtered books
    const [tagSearchText, setTagSearchText] = useState('');
    const [isModalVisible, setIsModalVisible] = useState(false);
    // New states for Spark job
    const [sparkKeywords, setSparkKeywords] = useState('');
    const [sparkResult, setSparkResult] = useState('');

    useEffect(() => {
        fetch('http://localhost:8080/books')
            .then(response => response.json())
            .then(data => {setBooks(data);
                             setFilteredBooks(data);} );
    }, []);

    const handleModalOk = () => {
        setIsModalVisible(false);
    };
    const handleSearchByNames = () => {
        const filtered = books.filter(book => 
            book.name.toLowerCase().includes(searchText.toLowerCase())
        );
        setFilteredBooks(filtered); // Update the filteredBooks state
    };
    
    const handleSearchByName = async () => {
        // GraphQL query with variable
        const query = `
            query searchBook($name: String!) {
                searchBooksByName(name: $name) {
                    id
                    name
                    type
                    author
                    price
                    description
                    inventory
                }
            }
        `;
    
        // Request options for GraphQL
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                query: query,
                variables: { name: searchText }
            })
        };
    
        try {
            const response = await fetch('http://localhost:8080/graphql', requestOptions);
            const data = await response.json();
            const books = data.data.searchBooksByName;
    
            if (Array.isArray(books)) {
                setBooks(books);
            } else {
                // Handle null or empty response
                setIsModalVisible(true);
            }
        } catch (error) {
            console.error('Error:', error);
            setIsModalVisible(true);
            
        }
    };
    const handleSearchByTag = async () => {
        const requestOptions = {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ tag: tagSearchText })
        };
    
        try {
            const response = await fetch('http://localhost:8080/books/filterByTag', requestOptions);
            const data = await response.json();
            if (Array.isArray(data)) {
                setBooks(data); // This updates the state for the next render cycle
                setFilteredBooks(data); // If you want to update the displayed list immediately
                console.log('Data received:', data); // Check the received data
            } else {
                // Handle null response
                setIsModalVisible(true);
            }
        } catch (error) {
            console.error('Error:', error);
            setIsModalVisible(true);
            handleSearchByName();
        }
    };
    const handleSparkSubmit = async () => {
        const keywordArray = sparkKeywords.split(',').map(keyword => keyword.trim()); // Trim whitespace
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ keywords: keywordArray }) // Convert the object to a JSON string
        };
    
        try {
            const response = await fetch('http://localhost:8080/spark/submit', requestOptions);
            const data = await response.text();
            setSparkResult(data);
            setIsModalVisible(true);
        } catch (error) {
            console.error('Error:', error);
            setIsModalVisible(true);
        }
    };

    return (
        <div>
            <Input
                placeholder="Search book name"
                onChange={(e) => setSearchText(e.target.value)}
                style={{ width: 200, marginBottom: '20px' }}
            />
            <Button onClick={handleSearchByNames} type="primary">
                Search by Name
            </Button>
            <Input
                placeholder="Search by tag"
                onChange={(e) => setTagSearchText(e.target.value)}
                style={{ width: 200, marginRight: '10px' }}
            />
            <Button onClick={handleSearchByTag} type="primary">
                Search by Tag
            </Button>
            <Modal
                title="Service Unavailable"
                visible={isModalVisible}
                onOk={handleModalOk}
                onCancel={handleModalOk}
                >
                <p>Sorry, the service is not available right now. Please try again later.</p>
            </Modal>
            <Input
                placeholder="Enter Spark keywords"
                onChange={(e) => setSparkKeywords(e.target.value)}
                style={{ width: 200, marginRight: '10px' }}
            />
            <Button onClick={handleSparkSubmit} type="primary">
                Submit Spark Job
            </Button>

            <Modal
                title="Spark Job Result"
                visible={isModalVisible}
                onOk={handleModalOk}
                onCancel={handleModalOk}
            >
                {sparkResult ? <p>{sparkResult}</p> : <p>Loading...</p>}
            </Modal>
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