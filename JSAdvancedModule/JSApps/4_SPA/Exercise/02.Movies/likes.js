// like messages to server

import * as request from "./requests.js";

// •	Get number of likes for a movie: /data/likes?where=movieId%3D%22{movieId}%22&distinct=_ownerId&count (GET)
async function getLikesByMovieId(movieId) {
    return await request.getLikesByMovieIdRequest(movieId);
}
// •	Get like for a movie from specific user: /data/likes?where=movieId%3D%22{movieId}%22%20and%20_ownerId%3D%22{userId}%22 (GET)
async function getLikesByMovieIdAndUser(movieId, userId) {
    
    return await request.getLikesByMovieIdAndUser(movieId, userId);
}
// •	Add a like: /data/likes (POST)
async function addLike(data) {

    return await request.addLikeRequest(data);
}
// •	Revoke a like: /data/likes/:id (DELETE)
async function revokeLike(id) {

    await request.deleteLikeRequest(id);
}

export {addLike, getLikesByMovieIdAndUser, revokeLike, getLikesByMovieId};