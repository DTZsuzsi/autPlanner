import {Link} from "react-router-dom";

const TopNavBar = () => {
   // const { user, isAuthenticated } = useContext(AuthContext);

    return (
        <div className="bg-blue-200 w-full h-12 flex z-10 top-0">
            <img
               // src="https://www.camc.org/sites/default/files/2023-07/Autism-Logo.png"
                className="h-6 w-auto mx-2 my-auto"
                alt="logo"
            />
            <h1 className="text-white bg-cyan-600 bg-opacity-80 p-1 pt-2 px-4 text-xl ml-2 m-auto tracking-wide rounded-2xl font-semibold">
                <Link to="/planner">Planner</Link>
            </h1>
            <div className="bg-red-500 text-white p-4">
                If you see red, Tailwind is working!
            </div>

        </div>
    );
};

export default TopNavBar;