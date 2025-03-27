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


    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value, username: formData.email });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
       setLoading(true);
       console.log("hi")
        try {
            const response = await fetch('/api/auth/login', {
                method: 'POST',
                body: JSON.stringify(formData),
                headers: {
                    'Content-Type': 'application/json',
                },
            })

console.log(response);
            const data = await response.json();
            console.log(data);
            if (!response.ok) {
                setError(data.error.message || 'Login failed');
                return;
            }

            saveUser(data);


          navigate(`/1`);

        } catch (error) {
            console.log(error);
            setError('Something went wrong. Please try again.' + error);
        }


    };


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
