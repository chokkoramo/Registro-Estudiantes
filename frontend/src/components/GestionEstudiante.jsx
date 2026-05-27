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

    const handleEliminar = async () => {
        if (!id) return;
        const ok = await api.eliminar(id);
        if (ok) {
            setResultado(`Estudiante con ID ${id} eliminado exitosamente`);
            setId('');
            setNotasStr('');
        } else {
            setResultado('Error al eliminar el estudiante');
        }
    };

    return (
        <div className="card">
            <h2>Gestión Individual por ID</h2>

            <div className="form-group">
                <label>ID del Estudiante<input type="number" min={1} value={id} onChange={(e) => setId(e.target.value)} />
                </label>
            </div>

            <div className="section-box">
                <h3>Asignar Notas</h3>
                <div className="form-group">
                    <input
                        placeholder="Ej: 4.5, 3.2, 5.0"
                        value={notasStr}
                        onChange={(e) => setNotasStr(e.target.value)}
                    />
                </div>
                <button onClick={handleAsignarNotas}>Guardar Notas</button>
            </div>

            <div className="form-actions" style={{ marginTop: '24px' }}>
                <button onClick={handleVerPromedio}>Consultar Promedio</button>
                <button onClick={handleVerEstado}>Consultar Estado</button>
                <button className="btn-danger" onClick={handleEliminar}>Eliminar Estudiante</button>
            </div>

            {resultado && <p className="msg-info"><strong>{resultado}</strong></p>}
        </div>
    );
}
