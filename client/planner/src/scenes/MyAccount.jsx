import UserProfile from "../components/user/UserProfile.jsx";
import useAuthContext from "../hooks/useAuthContext.js";

function MyAccount() {
    const { currentUserId} = useAuthContext();

    return(
        <div>
           <UserProfile userId={currentUserId}/>
        </div>
    )
}
export default MyAccount;