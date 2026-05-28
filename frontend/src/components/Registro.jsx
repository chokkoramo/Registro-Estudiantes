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
        <div className="card">
            <h2>Registrar Nuevo Estudiante</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Nombre
                    <input value={nombre} onChange={(e) => setNombre(e.target.value)} required /></label>
                </div>
                <div className="form-group">
                    <label>Programa
                    <input value={programa} onChange={(e) => setPrograma(e.target.value)} required /></label>
                </div>
                <button type="submit">Registrar</button>
            </form>

            {mensaje && (
                <p data-testid="mensaje-notificacion" className="msg-success">
                    {mensaje}
                </p>
            )}
        </div>
    );
}
