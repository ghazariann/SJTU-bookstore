import React, { useEffect, useState } from 'react';
import { Table, Button, Modal } from 'antd';

function UsersTable() {
  const [users, setUsers] = useState([]);

  useEffect(() => {
    fetch('http://127.0.0.1:8080/users')
      .then(response => response.json())
      .then(data => setUsers(data))
      .catch(error => console.error(error));
  }, []);

  const toggleDisabled = (user) => {
    const userUpdate = {...user, userAuth: {...user.userAuth, disabled: !user.userAuth.disabled}};
    Modal.confirm({
      title: 'Are you sure you want to toggle the ban status?',
      onOk: () => {
        fetch(`http://127.0.0.1:8080/users/${user.id}`, {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(userUpdate),
        })
        .then(() => {
          setUsers(users.map(u => u.id === user.id ? userUpdate : u));
        })
        .catch(error => console.error(error));
      }
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
    },
    {
      title: 'Address',
      dataIndex: 'address',
      key: 'address',
    },
    {
      title: 'Telephone',
      dataIndex: 'telephone',
      key: 'telephone',
    },
    {
      title: 'User',
      dataIndex: 'type',
      key: 'type',
    },
    {
      title: 'Email',
      dataIndex: 'userAuth',
      key: 'email',
      render: userAuth => userAuth.email,
    },
    {
        title: 'Disabled',
        dataIndex: 'userAuth',
        key: 'disabled',
        render: (userAuth, user) => (
          <>
            <p>{userAuth.disabled ? "Yes" : "No"}</p>
          </>
        ),
      },
      {
        title: 'Action',
        dataIndex: 'type',
        key: 'action',
        render: (type, user) => (
          type !== 0 ? <Button onClick={() => toggleDisabled(user)}>{user.userAuth.disabled ? 'Unban' : 'Ban'}</Button> : null
        )
      }
    ];
  
    return (
      <Table columns={columns} dataSource={users} rowKey='id' />
    );
  }
  
  export default UsersTable;
  
  