import SignUpForm from "../components/auth/SignUpForm.jsx";

function HomePage() {
    return(
        <div
    className = "flex flex-col items-center my-2 mx-auto  rounded-lg min-w-[280px] max-w-[600px]" >
            Hello world!
            <SignUpForm   />
        </div>
    )
}

export default HomePage;