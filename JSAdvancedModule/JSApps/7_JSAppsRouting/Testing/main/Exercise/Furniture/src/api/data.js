import createApi from './api.js';

const api = createApi(null, null, (msg) => alert(msg));

const endpoints = {
    LIST_FURNITURE: 'data/catalog',
    CREATE_FURNITURE: 'data/catalog',
    FURNITURE_DETAILS_BY_ID: 'data/catalog/',
    UPDATE_FURNITURE: 'data/catalog/',
    DELETE_FURNITURE: 'data/catalog/',
    USER_FURNITURE: 'data/catalog?where=' + encodeURIComponent('_ownerId='),
};

export const login = api.login.bind(api);
export const regster = api.register.bind(api);
export const logout = api.logout.bind(api);

export async function getFurnitures() {
    return await api.get(endpoints.LIST_FURNITURE);
}

export async function getFurnitureById(id) {
    return await api.get(endpoints.FURNITURE_DETAILS_BY_ID + id);
}

export async function createFurniture(furniture) {
    return await api.post(endpoints.CREATE_FURNITURE, furniture);
}

export async function editFurniture(id, furniture) {
    return await api.put(endpoints.UPDATE_FURNITURE + id, furniture);
}

export async function deleteFurnitureById(id) {
    return await api.delete(endpoints.DELETE_FURNITURE + id);
}

export async function getFurnituresByUserId(userId) {
    console.log(endpoints.USER_FURNITURE + encodeURIComponent(`"${userId}"`));
    return await api.get(endpoints.USER_FURNITURE + encodeURIComponent(`"${userId}"`));
}
