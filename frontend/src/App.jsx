import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import Registro from './components/Registro.jsx';
import Listado from './components/Listado.jsx';
import GestionEstudiante from './components/GestionEstudiante.jsx';

const Inicio = () => <h2>Bienvenido al Sistema de Estudiantes</h2>;

export default function App() {
    return (
        <BrowserRouter>
            {/* Opcional: Un menú de navegación rápido para probar */}
            <nav style={{ padding: '10px', background: '#f0f0f0', marginBottom: '20px' }}>
                <Link to="/" style={{ marginRight: '15px' }}>Inicio</Link>
                <Link to="/registro">Registrar Estudiante</Link>
                <Link to="/listado">Ver Listado</Link>
                <Link to="/GestionEstudiante">Gestionar Estudiantes</Link>
            </nav>

            {/* Aquí definimos qué componente se renderiza en cada URL */}
            <Routes>
                {/* Cuando la URL sea localhost:3000/ */}
                <Route path="/" element={<Inicio />} />

                {/* Cuando la URL sea localhost:3000/registro */}
                <Route path="/registro" element={<Registro />} />

                {}
                <Route path="/listado" element={<Listado />} />

                {}
                <Route path="/GestionEstudiante" element={<GestionEstudiante />} />
            </Routes>
        </BrowserRouter>
    );
}