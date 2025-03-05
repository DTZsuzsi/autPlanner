import './App.css';
import { BrowserRouter, Route, Routes } from "react-router-dom";
import HomePage from "./scenes/HomePage.jsx";

function App() {
    return (
        <div className="App">
            <BrowserRouter>
                <Routes>
                    <Route path="/login" element={<HomePage />} />
                </Routes>
            </BrowserRouter>
        </div>
    );
}

export default App;

