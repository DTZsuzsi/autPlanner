
import React, { createContext, useState, useEffect } from "react";

export const AuthContext = createContext({
user:null,
    saveUser:()=>{},
    logout:()=>{},
    isLoggedIn:false,
    getUser:()=>{},

});

// eslint-disable-next-line react/prop-types
export const AuthProvider = ({ children }) => {
    const [user, setUser] = useState(null);

    useEffect(() => {
        getUser();
    }, []);

    const getUser = () => {
        if (user) {
            return user;
        }

        const username = JSON.parse(localStorage.getItem("username"));
        const jwtToken = JSON.parse(localStorage.getItem("jwtToken"));

        const storageUser = { username, jwtToken };
        if (storageUser) {
            setUser(storageUser);
        }

        return storageUser;
    };

    const saveUser = (userData) => {
        setUser(userData);
        localStorage.setItem("username", JSON.stringify(userData.userName));
        localStorage.setItem("jwtToken", JSON.stringify(userData.jwtToken));
    };

    const isLoggedIn = () => {
        const user = localStorage.getItem("username");
        return !!user;
    };

    const logout = () => {
        setUser(null);
        localStorage.removeItem("username");
        localStorage.removeItem("jwtToken");
        localStorage.removeItem("roles");
    };

    return (
        <AuthContext.Provider
            value={{
               user, saveUser, logout,isLoggedIn,getUser
            }}
        >
            {children}
        </AuthContext.Provider>
    );
};
