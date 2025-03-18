import UserProfile from "../components/user/UserProfile.jsx";
import useAuthContext from "../hooks/useAuthContext.js";
function MyAccount() {
    const { currentUser} =useAuthContext();

    return(
        <div>
           <UserProfile user={currentUser}/>
        </div>
    )
}
export default MyAccount;