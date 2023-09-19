import React from 'react';

export const UserContext = React.createContext();

export class UserProvider extends React.Component {
    state = {
        user: JSON.parse(localStorage.getItem("user")) || null,
    };

    setUser = (user) => {
        localStorage.setItem("user", JSON.stringify(user));
        this.setState({ user });
    }

    render() {
        const { user } = this.state;
        return (
            <UserContext.Provider value={{ user, setUser: this.setUser }}>
                {this.props.children}
            </UserContext.Provider>
        );
    }
}
