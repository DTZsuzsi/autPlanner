import UserProfile from "../components/user/UserProfile.jsx";
import useAuthContext from "../hooks/useAuthContext.js";
import { useEffect, useState } from "react";
import ChildProfile from "../components/children/ChildProfile.jsx";
import Button from "../components/common/Button.jsx";
import {Link} from "react-router-dom";

function MyAccount() {
    let { user } = useAuthContext();
    user = { firstName: "Zsuzsa", lastName: "Ditrói-Tóth", email: "zsuzsa@gmail.com", childrenId: [1, 2, 3] };

    const [children, setChildren] = useState([]);

    useEffect(() => {
        async function fetchChild(id) {
            const response = await fetch(`/api/child/${id}`);
            const data = await response.json();
            console.log("Fetched child:", data);
            setChildren((prevChildren) => [...prevChildren, data]);
        }

        user.childrenId.forEach((id) => {
            fetchChild(id);
        });
    }, []); // Dependency array ensures this runs only when `user.childrenId` changes


    return (
        <div>
            <UserProfile user={user} />
            {children.map((child,index) => (
                <ChildProfile key={index} child={child} />
            ))}
            <Link to={`/newChild`}>
            <Button> Add new child </Button>
            </Link>
        </div>
    );
}

export default MyAccount;
