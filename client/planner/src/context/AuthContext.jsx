import React, { createContext, useState, useEffect } from "react";

export const AuthContext = createContext({
    user: null,
    saveUser: () => {},
    logout: () => {},
    isLoggedIn: () => false,
    getUser: () => {},
});

export const AuthProvider = ({ children }) => {
    const [user, setUser] = useState(null);

    useEffect(() => {
        const fetchUser = async () => {
            await getUser();
        };
        fetchUser();
    }, []);

    async function getUser() {
        const storedUsername = localStorage.getItem("username");
        if (!storedUsername) return;

        try {
            const response = await fetch(`/api/user/api/users/email/${storedUsername}`);
            if (!response.ok) throw new Error("Failed to fetch user data");

            const json = await response.json();
            setUser(json);
        } catch (error) {
            console.error("Error fetching user:", error);
            setUser(null);
        }
    }

    const getUsernameFromToken = (token) => {
        try {
            const payload = JSON.parse(atob(token.split('.')[1]));
            return payload.sub;
        } catch (e) {
            console.error("Invalid token", e);
            return null;
        }
    };

    const saveUser = (response) => {
        console.log("hello", response);
        if (!response || !response.jwtToken) {
            console.error("Invalid response format:", response);
            return;
        }

        try {
            localStorage.setItem('jwtToken', response.jwtToken);
            console.log("Token saved");

            const username = getUsernameFromToken(response.jwtToken);
            if (username) {
                localStorage.setItem('username', username);
                console.log("Username saved");
                setUser({ username });
            }
        } catch (error) {
            console.error("Failed to store in localStorage:", error);
        }
    };

    const isLoggedIn = () => !!localStorage.getItem("username");

    const logout = () => {
        setUser(null);
        localStorage.removeItem("username");
        localStorage.removeItem("jwtToken");
    };

    console.log("✅ AuthProvider is rendering with user:", user);

    return (
        <AuthContext.Provider value={{ user, saveUser, logout, isLoggedIn, getUser }}>
            {children}
        </AuthContext.Provider>
    );
};
