import { useState } from 'react';
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import Registro from './components/Registro.jsx';
import Listado from './components/Listado.jsx';
import GestionEstudiante from './components/GestionEstudiante.jsx';
import Login from './components/Login.jsx';

const Inicio = () => <h2>Bienvenido al Sistema de Estudiantes</h2>;

export default function App() {
    const [usuario, setUsuario] = useState(null);

    const handleLogout = () => setUsuario(null);

    if (!usuario) {
        return <Login onLogin={setUsuario} />;
    }

    return (
        <BrowserRouter>
            <nav style={{ padding: '10px', background: '#f0f0f0', marginBottom: '20px', display: 'flex', alignItems: 'center' }}>
                <Link to="/" style={{ marginRight: '15px' }}>Inicio</Link>
                <Link to="/registro" style={{ marginRight: '15px' }}>Registrar Estudiante</Link>
                <Link to="/listado" style={{ marginRight: '15px' }}>Ver Listado</Link>
                <Link to="/GestionEstudiante" style={{ marginRight: '15px' }}>Gestionar Estudiantes</Link>
                <span style={{ marginLeft: 'auto', marginRight: '10px' }}>Hola, {usuario.username}</span>
                <button onClick={handleLogout}>Cerrar Sesión</button>
            </nav>

            <Routes>
                <Route path="/" element={<Inicio />} />
                <Route path="/registro" element={<Registro />} />
                <Route path="/listado" element={<Listado />} />
                <Route path="/GestionEstudiante" element={<GestionEstudiante />} />
            </Routes>
        </BrowserRouter>
    );
}
