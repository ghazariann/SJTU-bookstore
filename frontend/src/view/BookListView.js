import React, { useEffect, useState } from 'react';
import { Table, Input, Button, Modal, Form, InputNumber } from 'antd';
import { Navigate, useNavigate } from 'react-router-dom';
import { ExclamationCircleOutlined } from '@ant-design/icons';

const EditableCell = ({
    editing,
    dataIndex,
    title,
    inputType,
    record,
    index,
    children,
    tempBookData, // add tempBookData prop
    ...restProps
  }) => {
    let inputNode = null;
    
    if (inputType === 'number') {
      inputNode = <InputNumber />;
    } else if (inputType === 'image') {
      inputNode = <img src={tempBookData ? tempBookData[dataIndex] : ''} width={50} />;
    } else {
      inputNode = <Input />;
    }
  
    return (
      <td {...restProps}>
        {editing ? (
          <Form.Item
            name={dataIndex}
            style={{ margin: 0 }}
            rules={[{ required: true, message: `Please Input ${title}!` }]}
          >
            {React.cloneElement(inputNode, { defaultValue: tempBookData ? tempBookData[dataIndex] : '' })}
          </Form.Item>
        ) : (
          children
        )}
      </td>
    );
  };

function BooksTable() {
  const [books, setBooks] = useState([]);
  const [editingId, setEditingId] = useState(null);
  const [tempBookData, setTempBookData] = useState(null);
  const [searchText, setSearchText] = useState('');

  const navigate = useNavigate();
  useEffect(() => {
    fetch('http://localhost:8080/books')
      .then(response => response.json())
      .then(data => setBooks(data))
      .catch(error => console.error(error));
  }, []);
  const handleDelete = (id) => {
    Modal.confirm({
      title: 'Are you sure delete this book?',
      icon: <ExclamationCircleOutlined />,
      content: 'This action cannot be undone',
      okText: 'Yes',
      okType: 'danger',
      cancelText: 'No',
      onOk() {
        fetch(`http://localhost:8080/books/${id}`, {
          method: 'DELETE',
        })
        .then(() => {
          setBooks(books.filter(book => book.id !== id));
        })
        .catch(error => console.error(error));
      },
    });
  };

  const handleAddBook = () => {
    navigate('/add-book');
  };
  const handleSearch = (e) => {
    setSearchText(e.target.value);
  };

  const filteredBooks = books.filter(book => book.name.toLowerCase().includes(searchText.toLowerCase()));
  const handleEdit = (record) => {
    setTempBookData({ ...record });
    setEditingId(record.id);
  };
  const handleSave = (id) => {
    // console.log(tempBookData);
    fetch(`http://localhost:8080/books/${id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(tempBookData),
    })
      .then(() => {
        setBooks(books.map(book => book.id === id ? tempBookData : book));
        setEditingId(null);
        setTempBookData(null);
      })
      .catch(error => console.error(error));
  };

  const handleCancel = () => {
    setEditingId(null);
    setTempBookData(null);
  };

  const handleChange = (e, key) => {
    setTempBookData({
      ...tempBookData,
      [key]: e.target.value
    });
  };


  const columns = [
    {
      title: 'ID',
      dataIndex: 'id',
      key: 'id',
    },
    {
      title: 'Name',
      dataIndex: 'name',
      key: 'name',
        editable: true,
    },
    {
      title: 'Type',
      dataIndex: 'type',
      key: 'type',
      editable: true,
    },
    {
      title: 'Author',
      dataIndex: 'author',
      key: 'author',
      editable: true,
    },
    {
      title: 'Price',
      dataIndex: 'price',
      key: 'price',
      inputType: 'number', 
      editable: true,

    },
    {
      title: 'Description',
      dataIndex: 'description',
      key: 'description',
      editable: true,

    },
    {
      title: 'Inventory',
      dataIndex: 'inventory',
      key: 'inventory',
      inputType: 'number',
      editable: true,

    },
    {
      title: 'Image',
      dataIndex: 'image',
      key: 'image',
        editable: true,
      render: image => <img width={50} src={image} />,
    },
    {
        title: 'Action',
        dataIndex: 'operation',
        render: (_, record) => {
          return editingId === record.id ? (
            <>
              <Button onClick={() => handleSave(record.id)}>Save</Button>
              <Button onClick={handleCancel}>Cancel</Button>
            </>
          ) : (
            <>
              <Button style={{ marginRight: '10px' }} disabled={editingId !== null} onClick={() => handleEdit(record)}>
                Edit
              </Button>
              <Button type="danger" onClick={() => handleDelete(record.id)}>Delete</Button>
            </>
          );
        },
      },
    ];
    
    const mergedColumns = columns.map(col => {
        if (!col.editable) {
          return col;
        }
    
        return {
          ...col,
          onCell: record => ({
            record,
            inputType: col.dataIndex === 'price' || col.dataIndex === 'inventory' ? 'number' : col.dataIndex === 'image' ? 'image' : 'text',
            dataIndex: col.dataIndex,
            title: col.title,
            editing: record.id === editingId,
          
            onChange: e => handleChange(e, col.dataIndex),
            tempBookData,
          }),
        };
      });
  
    return (
        <>
        <Button style={{ marginBottom: '20px' }} onClick={handleAddBook}>Add Book</Button>
        <Input placeholder="Search books" onChange={handleSearch} style={{ marginBottom: '20px' }}/>
        <Table
          components={{
            body: {
              cell: EditableCell,
            },
          }}
          columns={mergedColumns}
          dataSource={filteredBooks}
          rowKey='id'
        />
      </>
  );
    }
    
    export default BooksTable;
  
