import { useState } from 'react';
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import Registro from './components/Registro.jsx';
import Listado from './components/Listado.jsx';
import GestionEstudiante from './components/GestionEstudiante.jsx';
import Login from './components/Login.jsx';

const Inicio = () => (
    <div className="welcome">
        <h2>Bienvenido al Sistema de Estudiantes</h2>
        <p>Selecciona una opción del menú para comenzar.</p>
    </div>
);

export default function App() {
    const [usuario, setUsuario] = useState(null);

    const handleLogout = () => setUsuario(null);

    if (!usuario) {
        return <Login onLogin={setUsuario} />;
    }

    return (
        <BrowserRouter>
            <nav className="nav">
                <Link to="/">Inicio</Link>
                <Link to="/registro">Registrar Estudiante</Link>
                <Link to="/listado">Ver Listado</Link>
                <Link to="/GestionEstudiante">Gestionar Estudiantes</Link>
                <span className="user-info">Hola, {usuario.username}</span>
                <button className="btn-logout" onClick={handleLogout}>Cerrar Sesión</button>
            </nav>

            <div className="page-container">
                <Routes>
                    <Route path="/" element={<Inicio />} />
                    <Route path="/registro" element={<Registro />} />
                    <Route path="/listado" element={<Listado />} />
                    <Route path="/GestionEstudiante" element={<GestionEstudiante />} />
                </Routes>
            </div>
        </BrowserRouter>
    );
}
