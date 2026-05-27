import { useState } from 'react';
import { authApi } from '../services/api';
import PropTypes from 'prop-types';

export default function Login({ onLogin }) {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [esRegistro, setEsRegistro] = useState(false);
    const [mensaje, setMensaje] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        setMensaje('');

        if (esRegistro) {
            const res = await authApi.registro(username, password);
            if (res.ok) {
                setMensaje('Usuario registrado. Ahora inicia sesión.');
                setEsRegistro(false);
            } else {
                setMensaje(res.data.mensaje || 'Error al registrar');
            }
        } else {
            const res = await authApi.login(username, password);
            if (res.ok) {
                onLogin(res.data);
            } else {
                setMensaje('Credenciales incorrectas');
            }
        }
    };

    return (
        <div style={{ maxWidth: '400px', margin: '80px auto', padding: '20px', border: '1px solid #ccc', borderRadius: '8px' }}>
            <h2>{esRegistro ? 'Registro de Usuario' : 'Iniciar Sesión'}</h2>
            <form onSubmit={handleSubmit}>
                <div style={{ marginBottom: '10px' }}>
                    <label>Usuario: <input
                            type="text"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                            style={{ display: 'block', width: '100%', marginTop: '4px' }}
                        />
                    </label>
                </div>
                <div style={{ marginBottom: '10px' }}>
                    <label>Contraseña: <input
                            type="password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            style={{ display: 'block', width: '100%', marginTop: '4px' }}
                        />
                    </label>
                </div>
                <button type="submit" style={{ marginRight: '10px' }}>
                    {esRegistro ? 'Registrarse' : 'Ingresar'}
                </button>
                <button
                    type="button"
                    onClick={() => { setEsRegistro(!esRegistro); setMensaje(''); }}
                >
                    {esRegistro ? 'Ya tengo cuenta' : 'Crear cuenta'}
                </button>
            </form>

            {mensaje && <p style={{ marginTop: '10px', color: 'red' }}>{mensaje}</p>}
        </div>
    );
}

Login.propTypes = {
    onLogin: PropTypes.func.isRequired
};