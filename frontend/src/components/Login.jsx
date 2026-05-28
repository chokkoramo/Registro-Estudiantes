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
        <div className="login-container">
            <div className="card">
                <h2>{esRegistro ? 'Registro de Usuario' : 'Iniciar Sesión'}</h2>
                <form onSubmit={handleSubmit}>
                    <div className="form-group">
                        <label>Usuario</label>
                        <input
                            type="text"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                        />
                    </div>
                    <div className="form-group">
                        <label>Contraseña</label>
                        <input
                            type="password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                        />
                    </div>
                    <div className="form-actions">
                        <button type="submit">
                            {esRegistro ? 'Registrarse' : 'Ingresar'}
                        </button>
                        <button
                            type="button"
                            className="toggle-link"
                            onClick={() => { setEsRegistro(!esRegistro); setMensaje(''); }}
                        >
                            {esRegistro ? 'Ya tengo cuenta' : 'Crear cuenta'}
                        </button>
                    </div>
                </form>

                {mensaje && <p className="msg-error">{mensaje}</p>}
            </div>
        </div>
    );
}

Login.propTypes = {
    onLogin: PropTypes.func.isRequired
};
