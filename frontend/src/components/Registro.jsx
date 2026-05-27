import { useState } from 'react';
import { api } from '../services/api';

export default function Registro() {
    const [nombre, setNombre] = useState('');
    const [programa, setPrograma] = useState('');
    const [mensaje, setMensaje] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        setMensaje('');
        try {
            await api.registrar({ nombre, programa });
            setMensaje('¡Estudiante registrado con éxito!');
            setNombre('');
            setPrograma('');
        } catch (error) {
            console.log(error);
            setMensaje('Error al registrar estudiante');
        }
    };

    return (
        <div>
            <h2>Registrar Nuevo Estudiante</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Nombre:<input value={nombre} onChange={(e) => setNombre(e.target.value)} required />
                    </label>
                </div>
                <div style={{ marginTop: '10px' }}>
                    <label>Programa:<input value={programa} onChange={(e) => setPrograma(e.target.value)} required />
                    </label>
                </div>
                <button type="submit" style={{ marginTop: '15px' }}>Registrar</button>
            </form>

            {mensaje && (
                <p data-testid="mensaje-notificacion" style={{ marginTop: '20px', color: 'green', fontWeight: 'bold' }}>
                    {mensaje}
                </p>
            )}
        </div>
    );
}