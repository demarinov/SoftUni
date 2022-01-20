// user requests

async function sendRegistrationPostRequest(data) {
    const url = 'http://localhost:3030/users/register';

    console.log('sendRegistrationPostRequest');

    const result = await fetch(url, {
        method: "post",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    });

    if (result.status === 200) {
        const response = await result.json();
        const authToken = response.accessToken;
        sessionStorage.setItem('authToken', authToken);
        sessionStorage.setItem('userId', response._id);
        sessionStorage.setItem('userEmail', response.email);

    } else {
        console.error(result.status);
        throw new Error(await result.text());
    }

}

async function sendDeleteMovieRequest(id) {

    const token = sessionStorage.getItem('authToken');

    const response = await fetch('http://localhost:3030/data/movies/' + id, {
        method: 'delete',
        headers: {
            "Content-Type": "application/js",
            "X-Authorization": token
        }
    });

    return await response.json();
}

async function sendUpdateMoviePutRequest(data, id){
    const token = sessionStorage.getItem('authToken');

    const response = await fetch('http://localhost:3030/data/movies/' + id, {
        method: 'put',
        headers: {
            "Content-Type": "application/js",
            "X-Authorization": token
        },
        body: JSON.stringify(data)
    });

    return await response.json();
}

async function sendLoginPostRequest(data) {
    const url = 'http://localhost:3030/users/login';

    console.log('sendLoginPostRequest');

    const result = await fetch(url, {
        method: "post",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    });

    if (result.status === 200) {
        const response = await result.json();
        const authToken = response.accessToken;
        sessionStorage.setItem('authToken', authToken);
        sessionStorage.setItem('userId', response._id);
        sessionStorage.setItem('userEmail', response.email);

    } else {
        console.error(result.status);
        throw new Error(await result.text());
    }

}

async function sendLogoutRequest(token) {

    const url = 'http://localhost:3030/users/logout'

    const result = await fetch(url, {
        method: 'get',
        headers: {
            'Content-Type':'application/json',
            'X-Authorization': token
        }

    });

    if (result.status >= 200 && result.status < 300) {
        sessionStorage.removeItem('authToken');
        sessionStorage.removeItem('userId');
        sessionStorage.removeItem('userEmail');

    } else {
        throw new Error(result.status);
    }

}

async function createMovieRequest(data, token) {
    const response = await fetch('http://localhost:3030/data/movies', {
        method: 'post',
        headers: {
            "Content-Type" : "application/js",
            "X-Authorization" : token
        },
        body : JSON.stringify(data)
    });

    return await response.json();
}

async function deleteLikeRequest(id) {

    const token = sessionStorage.getItem('authToken');

    const response = await fetch('http://localhost:3030/data/likes/'+id, {
        method: 'delete',
        headers: {
            "Content-Type" : "application/js",
            "X-Authorization" : token
        }
    });

    return await response.json();
}

async function addLikeRequest(data) {
    const token = sessionStorage.getItem('authToken');

    const response = await fetch('http://localhost:3030/data/likes', {
        method: 'post',
        headers: {
            "Content-Type" : "application/js",
            "X-Authorization" : token
        },
        body : JSON.stringify(data)
    });

    return await response.json();
}

async function getLikesByMovieIdAndUserRequest(movieId, userId) {
    const response = await fetch(`http://localhost:3030/data/likes?where=movieId%3D%22${movieId}%22%20and%20_ownerId%3D%22${userId}%22`, {
        method: 'get',
        headers: {
            "Content-Type": "application/js"
        }
    });

    return await response.json();
}

async function getLikesByMovieIdRequest(movieId) {
    const response = await fetch(`http://localhost:3030/data/likes?where=movieId%3D%22${movieId}%22&distinct=_ownerId&count`, {
        method: 'get',
        headers: {
            "Content-Type": "application/js"
        }
    });

    return await response.json();
}

async function getNumberOfLikes() {
    const response = await fetch(`http://localhost:3030/data/likes?count`, {
        method: 'get',
        headers: {
            "Content-Type": "application/js"
        }
    });

    return await response.json();
}

export { sendRegistrationPostRequest, sendLogoutRequest, 
    sendLoginPostRequest, createMovieRequest,
    sendUpdateMoviePutRequest, sendDeleteMovieRequest,
    deleteLikeRequest, addLikeRequest,
    getLikesByMovieIdAndUserRequest, getLikesByMovieIdRequest};