import createApi from './api.js';

const api = createApi(null, null, (msg) => alert(msg));

const endpoints = {
    LIST_GAMES: 'data/games?sortBy='+encodeURIComponent('_createdOn desc'),
    CREATE_GAME: 'data/games',
    GAME_DETAILS_BY_ID: 'data/games/',
    UPDATE_GAME: 'data/games/',
    DELETE_GAME: 'data/games/',
    USER_GAMES: 'data/games?where='+encodeURIComponent('_ownerId='),
    LIST_GAME_COMMENTS: 'data/comments?where='+encodeURIComponent('gameId='),
    CREATE_GAME_COMMENT: 'data/comments'
};

export const login = api.login.bind(api);
export const regster = api.register.bind(api);
export const logout = api.logout.bind(api);

export async function getGames() {
    return await api.get(endpoints.LIST_GAMES);
}

export async function getGameById(id) {
    return await api.get(endpoints.GAME_DETAILS_BY_ID + id);
}

export async function createGame(game) {
    return await api.post(endpoints.CREATE_GAME, game);
}

export async function getDistinctGames() {

    return await api.get(endpoints.LIST_GAMES + '&distinct=category');
}

export async function editGame(id, game) {
    return await api.put(endpoints.UPDATE_GAME + id, game);
}

export async function deleteGameById(id) {
    return await api.delete(endpoints.DELETE_GAME + id);
}

export async function getGamesByUserId(userId) {
    const finalUrl = endpoints.USER_GAMES + encodeURIComponent(`"${userId}"`) + '&sortBy='+encodeURIComponent('_createdOn desc');

    console.log(finalUrl);
    return await api.get(finalUrl);
}

export async function getGameComments(gameId) {
    const finalUrl = endpoints.LIST_GAME_COMMENTS + encodeURIComponent(`"${gameId}"`);

    return await api.get(finalUrl);
}

export async function createGameComment(comment) {

    return await api.post(endpoints.CREATE_GAME_COMMENT, comment);
}


