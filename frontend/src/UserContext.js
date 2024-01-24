import React, { useState } from 'react';

export const UserContext = React.createContext();

export function UserProvider({ children }) {
    const [user, setUserState] = useState(JSON.parse(localStorage.getItem("user")) || null);
    const [ws, setWsState] = useState(null);

    const setUser = user => {
        localStorage.setItem("user", JSON.stringify(user));
        setUserState(user);
    };

    const setWs = ws => {
        setWsState(ws);
    };

    return (
        <UserContext.Provider value={{ user, setUser, ws, setWs }}>
            {children}
        </UserContext.Provider>
    );
}
