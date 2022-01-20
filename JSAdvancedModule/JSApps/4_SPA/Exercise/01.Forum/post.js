import * as utils from "./utils.js";
import * as comment from "./comment.js";

async function publishPost(data) {

    const response = await fetch('http://localhost:3030/jsonstore/collections/myboard/posts', {

        method: 'post',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    });

    if (response.status === 200) {
        const pureResponse = await response.json();

    } else {
        console.error(await response.text());
    }
}

function setupPost(body, targetElement) {
    console.log("setupPost()");

    const form = body.querySelector('.container main .new-topic-border form');

    form.addEventListener('submit', async function (e) {
        e.preventDefault();

        const formData = new FormData(form);

        const topicName = formData.get('topicName');
        const userName = formData.get('username');
        const postText = formData.get('postText');

        if (topicName === '' || userName === ''
            || postText === '') {
            console.error('Empty fields in post input!');
            return;
        }

        const date = new Date();

        const uniqueId = topicName + userName + '|' + date.getTime();

        const data = {
            topicName,
            userName,
            postText,
            uniqueId,
            date
        }

        await publishPost(data);

        // showPost(targetElement, postEntry['_id']);
        showPosts(targetElement);
        //clear input
        e.target.reset();
    });


}

async function showPost(targetElement, id) {
    console.log("showPost()");

    const postDiv = utils.e('div', { className: 'topic' },
        utils.e('h2', {}, ''),
        utils.e('p', {},
            utils.e('span', {}, ''), ''),
        utils.e('p', {}, ''));

    const post = await getPostById(id);
}

async function showPosts(targetElement) {
    console.log("showPosts()");

    targetElement.innerHTML = '';
    const posts = await getAllPosts();

    if (Object.entries(posts).length === 0) {
        return;
    }



    Object.entries(posts).forEach(e => {
        const postDiv = createPost();
        targetElement.appendChild(postDiv);

        postDiv.querySelector('h2').textContent = e[1].topicName;
        postDiv.querySelector('.columns p:nth-child(1) span').textContent = e[1].date;
        postDiv.querySelector('.columns p:nth-child(2) span')
            .textContent = e[1].userName;
        
        postDiv.querySelector('.normal').addEventListener('click', function(e) {
            
            const selectedTopic = postDiv;
            comment.showComments(selectedTopic, targetElement);

        });
    })
}

function createPost() {

    return utils.e('div',
        {className:'topic-name-wrapper'},
        utils.e('div', 
        { className: 'topic-name' }, utils.e('a', {href:"javascript:void(0)", className:'normal'},
        utils.e('h2', {className: ''}, '')),
        utils.e('div',{className:"columns"},
            utils.e('div',{},
                utils.e('p', {className: ''}, 'Date: ',
                    utils.e('span',{},'')),
                utils.e('p', { className: '' }, 'Username: ',
                     utils.e('span', {}, ''))
                    )
                )));
}

async function getPostById(id) {
    const response = await fetch('http://localhost:3030/jsonstore/collections/myboard/posts/' + id, {

        method: 'get',
        headers: {
            "Content-Type": "application/json"
        }
    });

    if (response.status === 200) {
        const posts = await response.json();

        console.log(posts);

        return posts;
    } else {
        console.error(JSON.stringify(await response.text()));
    }

}

async function getAllPosts() {
    const response = await fetch('http://localhost:3030/jsonstore/collections/myboard/posts', {

        method: 'get',
        headers: {
            "Content-Type": "application/json"
        }
    });

    if (response.status === 200) {
        const posts = await response.json();

        console.log(posts);

        return posts;
    } else {
        console.error(JSON.stringify(await response.text()));
    }

}

export { setupPost, showPost, showPosts, getAllPosts};