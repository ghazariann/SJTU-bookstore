import React, { useState, useContext } from 'react';
import { Input, Button, message } from 'antd';
import '../css/profile.css';
import { UserContext } from '../UserContext';

function ProfileView() {
  const { user, setUser } = useContext(UserContext);
  const [isEditing, setIsEditing] = useState({});

  const [editableUser, setEditableUser] = useState({
    name: user.name,
    address: user.address,
    telephone: user.telephone,
    email: user.userAuth.email,
    password: user.userAuth.password,
  });

  const handleInputChange = (event) => {
    setEditableUser({ ...editableUser, [event.target.name]: event.target.value });
  };
  const handleDiscard = () => {
    setEditableUser({
      name: user.name,
      address: user.address,
      telephone: user.telephone,
      email: user.userAuth.email,
      password: user.userAuth.password,
    });
    setIsEditing({});
  };

  const handleSubmit = () => {
    const changes = Object.keys(editableUser).reduce((acc, field) => {
      if (editableUser[field] !== user[field]) {
        if (field === 'email' || field === 'password') {
          if (!acc.userAuth) {
            acc.userAuth = {};
          }
          acc.userAuth[field] = editableUser[field];
        } else {
          acc[field] = editableUser[field];
        }
      }
      return acc;
    }, {});
  
    fetch(`http://127.0.0.1:8080/users/${user.id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(changes),
    })
      .then((response) => response.json())
      .then((data) => {
        setUser(data);
        setIsEditing({});
      })
      .catch((error) => console.error('Error:', error));
      message.success('Successfully Updated Profile Information');
  };
  return (
    <div className="profile-form">
      <div className="form-header">My Profile</div>

      {Object.keys(editableUser).map((field, index) => (
        <div key={index} className="form-field">
          <label>{field.charAt(0).toUpperCase() + field.slice(1)}</label>
          {isEditing[field] ? (
            <Input
              name={field}
              value={editableUser[field]}
              onChange={handleInputChange}
              style={{ width: '60%' }}
              type={'text'} 
            />
          ) : (
            <>
              <div>{field === 'password' ? '***' : editableUser[field]}</div>
              <Button className="change-btn" onClick={() => setIsEditing({ ...isEditing, [field]: true })}>
                Change
              </Button>
            </>
          )}
        </div>
      ))}
      <Button className="save-btn" onClick={handleSubmit}>
        Save
      </Button>
      <Button className="discard-btn" onClick={handleDiscard}>
        Discard
      </Button>
    </div>
  );
}

export default ProfileView;