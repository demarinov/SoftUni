import createApi from './api.js';

const api = createApi(null, null, (msg) => alert(msg));

const endpoints = {
    LIST_CARS: 'data/cars?sortBy='+encodeURIComponent('_createdOn desc'),
    CREATE_CAR: 'data/cars',
    CAR_DETAILS_BY_ID: 'data/cars/',
    UPDATE_CAR: 'data/cars/',
    DELETE_CAR: 'data/cars/',
    USER_CARS: 'data/cars?where='+encodeURIComponent('_ownerId='),
    CARS_BY_YEAR: 'data/cars?where='+encodeURIComponent('year=')
};

export const login = api.login.bind(api);
export const regster = api.register.bind(api);
export const logout = api.logout.bind(api);

export async function getCars() {
    return await api.get(endpoints.LIST_CARS);
}

export async function getCarById(id) {
    return await api.get(endpoints.CAR_DETAILS_BY_ID + id);
}

export async function createCar(car) {
    return await api.post(endpoints.CREATE_CAR, car);
}

export async function editCar(id, car) {
    return await api.put(endpoints.UPDATE_CAR + id, car);
}

export async function deleteCarById(id) {
    return await api.delete(endpoints.DELETE_CAR + id);
}

export async function getCarsByUserId(userId) {
    const finalUrl = endpoints.USER_CARS + encodeURIComponent(`"${userId}"`) + '&sortBy='+encodeURIComponent('_createdOn desc');

    console.log(finalUrl);
    return await api.get(finalUrl);
}

export async function getCarsByYear(year) {
    const finalUrl = endpoints.CARS_BY_YEAR + year;

    return await api.get(finalUrl);
}


