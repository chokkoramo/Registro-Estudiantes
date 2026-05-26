import { useState } from 'react';
import { api } from '../services/api';

export default function GestionEstudiante() {
    const [id, setId] = useState('');
    const [notasStr, setNotasStr] = useState('');
    const [resultado, setResultado] = useState(null);

    const handleAsignarNotas = async () => {
        const arrayNotas = notasStr.split(',').map(n => n.trim());
        const res = await api.asignarNotas(id, arrayNotas);
        setResultado(`Éxito: ${res.mensaje}`);
    };

    const handleVerPromedio = async () => {
        const res = await api.obtenerPromedio(id);
        setResultado(`El promedio del estudiante ${res.id} es: ${res.promedio}`);
    };

    const handleVerEstado = async () => {
        const res = await api.obtenerEstado(id);
        setResultado(`El estudiante ${res.id} está: ${res.estado}`);
    };

    return (
        <div>
            <h2>Gestión Individual por ID</h2>

            <div>
                <label>ID del Estudiante: </label>
                <input type="number" value={id} onChange={(e) => setId(e.target.value)} />
            </div>

            <div style={{ marginTop: '20px', padding: '10px', border: '1px solid gray' }}>
                <h3>Asignar Notas</h3>
                <input
                    placeholder="Ej: 4.5, 3.2, 5.0"
                    value={notasStr}
                    onChange={(e) => setNotasStr(e.target.value)}
                />
                <button onClick={handleAsignarNotas} style={{ marginLeft: '10px' }}>Guardar Notas</button>
            </div>

            <div style={{ marginTop: '20px' }}>
                <button onClick={handleVerPromedio}>Consultar Promedio</button>
                <button onClick={handleVerEstado} style={{ marginLeft: '10px' }}>Consultar Estado</button>
            </div>

            {resultado && <div style={{ marginTop: '20px', color: 'blue' }}><strong>{resultado}</strong></div>}
        </div>
    );
}