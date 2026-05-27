const API_URL = 'https://registro-estudiantes.onrender.com/api/estudiantes';

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
    }
};