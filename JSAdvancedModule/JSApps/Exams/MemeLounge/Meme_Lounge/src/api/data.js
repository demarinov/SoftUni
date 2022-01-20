import createApi from './api.js';

const api = createApi(null, null, (msg) => alert(msg));

const endpoints = {
    LIST_MEME: 'data/memes?sortBy='+encodeURIComponent('_createdOn desc'),
    CREATE_MEME: 'data/memes',
    MEME_DETAILS_BY_ID: 'data/memes/',
    UPDATE_MEME: 'data/memes/',
    DELETE_MEME: 'data/memes/',
    USER_MEMES: 'data/memes?where='+encodeURIComponent('_ownerId=')
};

export const login = api.login.bind(api);
export const regster = api.register.bind(api);
export const logout = api.logout.bind(api);

export async function getMemes() {
    return await api.get(endpoints.LIST_MEME);
}

export async function getMemeById(id) {
    return await api.get(endpoints.MEME_DETAILS_BY_ID + id);
}

export async function createMeme(meme) {
    return await api.post(endpoints.CREATE_MEME, meme);
}

export async function editMeme(id, meme) {
    return await api.put(endpoints.UPDATE_MEME + id, meme);
}

export async function deleteMemeById(id) {
    return await api.delete(endpoints.DELETE_MEME + id);
}

export async function getMemesByUserId(userId) {
    const finalUrl = endpoints.USER_MEMES + encodeURIComponent(`"${userId}"`) + '&sortBy='+encodeURIComponent('_createdOn desc');

    console.log(finalUrl);
    return await api.get(finalUrl);
}

export async function getUserMemeCount(userId) {
    const finalUrl = endpoints.USER_MEMES + encodeURIComponent(`"${userId}"`)+'&count';

    return await api.get(finalUrl);
}
