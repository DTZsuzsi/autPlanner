import UniversalForm from "../common/UniversalForm.jsx";
import {useState} from "react";

function NewChildForm({user}){
   const [newChild, setNewChild] = useState({firstName:"", lastName:"", birthday:Date.now()});

    const handleChange = (e) => {
        setNewChild({ ...newChild, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const response= await fetch('/api/child',{
            method: "POST",
            body: JSON.stringify({...newChild, parentEmail: user.email}),
            headers: {'Content-Type': 'application/json'},
        })
        const data = await response.json();
        console.log(data);

    }

    return (
        <UniversalForm
            onSubmit={handleSubmit}
            formData={newChild}
            onChange={handleChange}
            submitText="Add new child"
        />
    )
}

export default NewChildForm;