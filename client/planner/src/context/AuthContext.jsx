import React, { createContext, useState, useEffect } from "react";

export const AuthContext = createContext({
    user: null,
    saveUser: () => {},
    logout: () => {},
    isLoggedIn: () => false,
    getUser: () => {},
});

// eslint-disable-next-line react/prop-types
export const AuthProvider = ({ children }) => {
    const [user, setUser] = useState(null);

    useEffect(() => {
        getUser();
    }, []);

    async function getUser() {
        const storedUsername = localStorage.getItem("username");
        if (!storedUsername) {
            return;
        }

        try {
            const username = JSON.parse(storedUsername);
            const response = await fetch(`/api/user/api/users/email/${username}`);

            if (!response.ok) {
                throw new Error("Failed to fetch user data");
            }

            const json = await response.json();
            setUser(json);
        } catch (error) {
            console.error("Error fetching user:", error);
            setUser(null);
        }
    }

    const saveUser = (userData) => {
        setUser(userData);
        localStorage.setItem("username", JSON.stringify(userData.username));
        localStorage.setItem("jwtToken", JSON.stringify(userData.jwtToken));
    };

    const isLoggedIn = () => {
        return !!localStorage.getItem("username");
    };

    const logout = () => {
        setUser(null);
        localStorage.removeItem("username");
        localStorage.removeItem("jwtToken");
    };

    return (
        <AuthContext.Provider value={{ user, saveUser, logout, isLoggedIn, getUser }}>
            {children}
        </AuthContext.Provider>
    );
};
