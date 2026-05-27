const API_URL = 'http://web-api:8080/api/estudiantes';
const AUTH_URL = 'http://web-api:8080/api/auth';

export const authApi = {
    login: async (username, password) => {
        const res = await fetch(`${AUTH_URL}/login`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username, password })
        });
        return { ok: res.ok, data: await res.json() };
    },
    registro: async (username, password) => {
        const res = await fetch(`${AUTH_URL}/registro`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username, password })
        });
        return { ok: res.ok, data: await res.json() };
    }
};

export const api = {
    registrar: async (estudiante) => {
        const res = await fetch(API_URL, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(estudiante)
        });
        return res.json();
    },
    listarTodos: async () => {
        const res = await fetch(API_URL);
        return res.json();
    },
    obtenerRanking: async () => {
        const res = await fetch(`${API_URL}/ranking`);
        return res.json();
    },
    asignarNotas: async (id, notasArray) => {
        const res = await fetch(`${API_URL}/${id}/notas`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            // Transforma array de números [4, 5] a objetos [{nota: 4}, {nota: 5}]
            body: JSON.stringify(notasArray.map(n => ({ nota: Number.parseFloat(n) })))
        });
        return res.json();
    },
    obtenerPromedio: async (id) => {
        const res = await fetch(`${API_URL}/${id}/promedio`);
        return res.json();
    },
    obtenerEstado: async (id) => {
        const res = await fetch(`${API_URL}/${id}/estado`);
        return res.json();
    },
    eliminar: async (id) => {
        const res = await fetch(`${API_URL}/${id}`, { method: 'DELETE' });
        return res.ok;
    }
};