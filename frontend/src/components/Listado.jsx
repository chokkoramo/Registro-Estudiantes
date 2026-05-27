import { useState, useEffect } from 'react';
import { api } from '../services/api';

export default function Listados() {
    const [estudiantes, setEstudiantes] = useState([]);
    const [esRanking, setEsRanking] = useState(false);

    const cargarDatos = async (ranking) => {
        setEsRanking(ranking);
        const data = ranking ? await api.obtenerRanking() : await api.listarTodos();
        setEstudiantes(data);
    };

    useEffect(() => {
        cargarDatos(false); // Carga la lista normal al inicio
    }, []);

    return (
        <div className="card">
            <h2>{esRanking ? 'Ranking de Mejores Estudiantes' : 'Todos los Estudiantes'}</h2>

            <div className="form-actions">
                <button className={!esRanking ? undefined : 'btn-secondary'} onClick={() => cargarDatos(false)}>Ver Todos</button>
                <button className={esRanking ? undefined : 'btn-secondary'} onClick={() => cargarDatos(true)}>Ver Ranking</button>
            </div>

            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Programa</th>
                    <th>Promedio</th>
                    <th>Estado</th>
                </tr>
                </thead>
                <tbody>
                {estudiantes.map((est) => (
                    <tr key={est.id} data-testid="fila-estudiante">
                        <td>{est.id}</td>

                        <td data-testid="nombre-estudiante">
                            {est.nombre}
                        </td>

                        <td data-testid="programa-estudiante">
                            {est.programa}
                        </td>

                        <td>{est.promedio.toFixed(2)}</td>

                        <td data-testid="estado-estudiante">
                            <span className={`badge ${est.aprobado ? 'badge-aprobado' : 'badge-reprobado'}`}>
                                {est.aprobado ? 'Aprobado' : 'Reprobado'}
                            </span>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}