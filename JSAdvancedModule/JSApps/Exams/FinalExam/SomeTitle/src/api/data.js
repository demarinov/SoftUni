import createApi from './api.js';

const api = createApi(null, null, (msg) => alert(msg));

const endpoints = {
    LIST_ALBUMS: 'data/albums?sortBy='+encodeURIComponent('_createdOn desc')+'&distinct=name',
    CREATE_ALBUM: 'data/albums',
    ALBUM_DETAILS_BY_ID: 'data/albums/',
    UPDATE_ALBUM: 'data/albums/',
    DELETE_ALBUM: 'data/albums/',
    SEARCH_ALBUMS: 'data/albums?where='+encodeURIComponent('name LIKE ')
    
};

export const login = api.login.bind(api);
export const regster = api.register.bind(api);
export const logout = api.logout.bind(api);

export async function getAlbums() {
    return await api.get(endpoints.LIST_ALBUMS);
}

export async function getAlbumById(id) {
    return await api.get(endpoints.ALBUM_DETAILS_BY_ID + id);
}

export async function createAlbum(album) {
    return await api.post(endpoints.CREATE_ALBUM, album);
}

export async function getDistinctAlbums() {

    return await api.get(endpoints.LIST_ALBUMS + '&distinct=category');
}

export async function editAlbum(id, album) {
    return await api.put(endpoints.UPDATE_ALBUM + id, album);
}

export async function deleteAlbumById(id) {
    return await api.delete(endpoints.DELETE_ALBUM + id);
}

export async function getAlbumsByUserId(userId) {
    const finalUrl = endpoints.USER_ALBUM + encodeURIComponent(`"${userId}"`) + '&sortBy='+encodeURIComponent('_createdOn desc');

    console.log(finalUrl);
    return await api.get(finalUrl);
}

export async function getSearchAlbums(query) {
    const finalUrl = endpoints.SEARCH_ALBUMS + encodeURIComponent(`"${query}"`);

    return await api.get(finalUrl);
}


