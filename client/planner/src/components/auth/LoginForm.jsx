import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import UniversalForm from '../common/UniversalForm.jsx';
import useAuthContext from "../hooks/useAuthContext.jsx";

const LoginForm = () => {
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(false);
    const [formData, setFormData] = useState({
        email: '',
        password: '',
    });
    const navigate = useNavigate();
    const { saveToken } = useAuthContext();

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setLoading(true);
        try {
            const response = await fetch('/api/auth/login', {
                method: 'POST',
                body: JSON.stringify(formData),
                headers: {
                    'Content-Type': 'application/json',
                },
            });

            const contentType = response.headers.get('content-type');
            let data;
            if (contentType && contentType.includes('application/json')) {
                data = await response.json();
            } else {
                data = await response.text();
                data = { message: data };
            }

            console.log(data);
            if (!response.ok) {
                setError(data.message || 'Login failed');
                return;
            }

            saveToken(data.token);
            navigate("/1");

        } catch (error) {
            setError('Something went wrong. Please try again. ' + error.message);
        } finally {
            setLoading(false);
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
