import { useState } from 'react';
import Registro from "./components/Registro.jsx";
import Listados from "./components/Listado.jsx";
import GestionEstudiante from "./components/GestionEstudiante.jsx";

function App() {
  const [vistaActiva, setVistaActiva] = useState('registro');

  return (
      <div style={{ padding: '20px', fontFamily: 'Arial, sans-serif', maxWidth: '800px', margin: 'auto' }}>
        <h1>🎓 Sistema de Registro de Estudiantes</h1>

        {/* Menú de Navegación */}
        <nav style={{ marginBottom: '30px', paddingBottom: '10px', borderBottom: '2px solid black' }}>
          <button onClick={() => setVistaActiva('registro')} style={{ marginRight: '10px' }}>📝 Registro</button>
          <button onClick={() => setVistaActiva('listados')} style={{ marginRight: '10px' }}>📋 Listas y Ranking</button>
          <button onClick={() => setVistaActiva('gestion')}>⚙️ Notas y Consultas</button>
        </nav>

        {/* Renderizado condicional de componentes */}
        <main>
          {vistaActiva === 'registro' && <Registro />}
          {vistaActiva === 'listados' && <Listados />}
          {vistaActiva === 'gestion' && <GestionEstudiante />}
        </main>
      </div>
  );
}

export default App;