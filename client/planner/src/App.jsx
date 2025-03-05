import './App.css';
import { BrowserRouter, Route, Routes } from "react-router-dom";
import HomePage from "./scenes/HomePage.jsx";
import RegistrationPage from "./scenes/RegistrationPage.jsx";
import MyAccount from "./scenes/MyAccount.jsx";

function App() {
    return (
        <div className="App">
            <BrowserRouter>
                <Routes>
                    <Route path="/login" element={<HomePage />} />
                    <Route path="/register" element={<RegistrationPage />} />
                    <Route path="/:id" element={<MyAccount />} />
                </Routes>
            </BrowserRouter>
        </div>
    );
}

export default App;

