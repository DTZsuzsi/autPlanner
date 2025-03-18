import {useEffect, useState} from "react";
import useAuthRequest from "../../hooks/useAuthRequest.js";


// eslint-disable-next-line react/prop-types
function UserProfile({userId}){
const [user, setUser] = useState(null);
    const { sendRequest } = useAuthRequest();


useEffect(() => {
    async function fetchData(){
        const { userData } = await sendRequest(
            `/api/user/${userId}`,
            'GET',
        );
        setUser(userData);
    }
    fetchData();
}, [userId]);

    return (
        <div className="border-solid border-gray-200">
            <p>
                First name: {user?.firstName}
            </p>
            <p>
                Last name: {user?.lastName}
            </p>
</div>
    )

}

export default UserProfile;