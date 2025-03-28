import { useState } from 'react';

import UniversalForm from '../common/UniversalForm.jsx';
import {useNavigate} from "react-router-dom";
import useAuthContext from "../../hooks/useAuthContext.js";

const LoginForm = () => {
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(false);
    const [formData, setFormData] = useState({
        email: '',
        password: '',

    });
    const navigate = useNavigate();
const {saveUser} = useAuthContext();
    console.log("saveUser function:", saveUser);


    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value, username: formData.email });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setLoading(true);
        console.log("hi");
        console.log("saveUser function:", saveUser);


        try {
            const response = await fetch('/api/auth/login', {
                method: 'POST',
                body: JSON.stringify(formData),
                headers: {
                    'Content-Type': 'application/json',
                },
            });

            console.log("Raw response:", response);

            if (!response.ok) {
                // Try to extract error message if possible
                let errorMessage = "Login failed";
                try {
                    const errorData = await response.json();
                    errorMessage = errorData.error?.message || errorMessage;
                } catch (jsonError) {
                    console.error("Error parsing error response:", jsonError);
                }
                setError(errorMessage);
                return;
            }

            const data = await response.json();
            console.log("Parsed response:", data);

            if (!data.jwtToken) {
                setError("Login failed: No token received");
                return;
            }

            saveUser(data);

            console.log("Good job! Redirecting...");
            navigate(`/1`);
        } catch (error) {
            console.error("Request failed:", error);
            setError('Something went wrong. Please try again.');
        }}


            return (
        <UniversalForm
            onSubmit={handleSubmit}
            formData={formData}
            onChange={handleChange}
            loading={loading}
            error={error}
            submitText="Login"
        />
    );
};

export default LoginForm;
