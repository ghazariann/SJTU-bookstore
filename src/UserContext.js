import React from 'react';

export const UserContext = React.createContext();

export class UserProvider extends React.Component {
    state = {
        user: JSON.parse(localStorage.getItem("user")) || null,
        ws: null,
    };

    setUser = (user) => {
        localStorage.setItem("user", JSON.stringify(user));
        this.setState({ user });
    }

    setWs = (ws) => { 
        this.setState({ ws });
    }

    render() {
        const { user, ws } = this.state;
        return (
            <UserContext.Provider value={{ user, setUser: this.setUser, ws, setWs: this.setWs }}>
                {this.props.children}
            </UserContext.Provider>
        );
    }
}
