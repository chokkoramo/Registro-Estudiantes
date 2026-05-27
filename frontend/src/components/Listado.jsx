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
        <div>
            <h2>{esRanking ? 'Ranking de Mejores Estudiantes' : 'Todos los Estudiantes'}</h2>

            <button onClick={() => cargarDatos(false)}>Ver Todos</button>
            <button onClick={() => cargarDatos(true)} style={{ marginLeft: '10px' }}>Ver Ranking</button>

            <table border="1" style={{ marginTop: '20px', width: '100%', textAlign: 'center' }}>
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
                            {est.aprobado ? 'Aprobado' : 'Reprobado'}
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}