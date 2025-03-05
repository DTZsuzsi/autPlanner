import {Link} from "react-router-dom";
import Button from "../components/common/Button.jsx";
import LoginForm from "../components/auth/LoginForm.jsx";

function HomePage() {
    return(
        <div
    className = "flex flex-col items-center my-2 mx-auto  rounded-lg min-w-[280px] max-w-[600px]" >
            Hello world!
            <LoginForm   />
            <Link to={"/register"}>
            <Button>
                If you don&#39;t have an account, register here!
                </Button>
                </Link>
        </div>
    )
}

export default HomePage;