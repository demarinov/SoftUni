
import * as utils from "./utils.js";
import * as post from "./post.js";

async function publishComment(data) {

    const response = await fetch('http://localhost:3030/jsonstore/collections/myboard/comments', {

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

async function fetchComments() {

    const response = await fetch('http://localhost:3030/jsonstore/collections/myboard/comments', {

        method: 'get',
        headers: {
            "Content-Type": "application/json"
        }
    });

    if (response.status === 200) {
        const comments = await response.json();

        return comments;
    } else {
        console.error(await response.text());
    }
}

let mainPage;
function setupComment(main) {
    console.log("setupComment()");

    mainPage = main;
}

async function showComments(selectedTopic, targetElement) {
    console.log("showComments()");

    const posts = Object.entries(await post.getAllPosts());

    const topicName = selectedTopic
    .querySelector('.topic-name h2')
    .textContent;

    const userName = selectedTopic
    .querySelector('.columns div p:nth-child(2) span')
    .textContent;

    const dateMillis = Date.parse(selectedTopic
        .querySelector('.columns div p:nth-child(1) span')
        .textContent);

    const uniqueId = topicName + userName + '|'+dateMillis;

    let postEntry;
    posts.forEach(pe => {
        if (pe[1]['uniqueId'] === uniqueId) {
            postEntry = pe[1];
        }
    });

    const comment = createComment();

    // create comment page
    comment.querySelector('.header p:nth-child(2) span')
    .textContent = userName;

    const dateText = selectedTopic
    .querySelector('.columns div p:nth-child(1) span')
    .textContent.replace(/T/g, ' ');
    comment.querySelector('.header p:nth-child(2) time')
    .textContent = dateText.substring(0, dateText.length-5);

    comment.querySelector('.header .post-content')
    .textContent = postEntry.postText;


    const topicHeader = utils.e('h2',{},topicName);

    targetElement.innerHTML = '';
    targetElement.appendChild(topicHeader);
    targetElement.appendChild(comment);

    // create current user form
    const userCommentForm = createUserCommentForm();
    targetElement.appendChild(userCommentForm);

    mainPage.innerHTML = '';

    mainPage.appendChild(targetElement);

    // fetch comments
    let comments = await fetchComments();

    comments = Object.values(comments).filter(c => {
        return c['postId'] === postEntry['_id'];
    })

    // show comments under post
    showUserComments(targetElement.querySelector('.comment'), comments);

    // when posting comment ...
    const postCommentForm = userCommentForm.querySelector('#postCommentForm');

    postCommentForm.addEventListener('submit',async function(e) {
        e.preventDefault();
        
        const data = {
            postId:postEntry['_id'],
            user: e.target.querySelector('input').value,
            comment: e.target.querySelector('textarea').value,
            date: new Date().getTime()
        }
        // publish comment
        await publishComment(data);

        // fetch comments
        let comments = await fetchComments();

        comments = Object.values(comments).filter(c => {
            return c['postId'] === postEntry['_id'];
        })

        // clear form
        utils.clearForm(postCommentForm);

        // show comments under post
        showUserComments(targetElement.querySelector('.comment'), comments);
    })
    

}

function showUserComments(targetElement, comments) {

    // delete current user comments
    const userComments = Array.from(targetElement.querySelectorAll('.user-comment'));
    userComments.forEach(uc => {
        uc.parentElement.removeChild(uc);
    });

    // most recent commment on top...
    comments.sort((c1,c2) => Number(c2.date)-Number(c1.date)).forEach(c => {
        const userComment = createUserComment();

        userComment.querySelector('.topic-name span')
        .textContent = c.user;
        userComment.querySelector('.topic-name time')
        .textContent = new Date(c.date).toLocaleString();

        userComment.querySelector('.post-content')
        .textContent = c.comment;

        targetElement.appendChild(userComment);
    })
    
}

function createUserCommentForm() {

    return utils.e('div',{className:"answer-comment"}, 
    utils.e('p',{}, utils.e('span',{},'currentUser'), ' comment:'),
        utils.e('div',{className:"answer"}, 
            utils.e('form',{id:"postCommentForm"}, 
                utils.e('textarea', {id:'userComment',name:'userComment'},''),
                utils.e('div' , {},
                utils.e('label',{},'Username * '),
                utils.e('input',{type:"text", name: "userName"},'')),
                utils.e('button', {},'Post'))));
}

function createUserComment() {

    return utils.e('div',
        {className:'user-comment'},
        utils.e('div', 
        { className: 'topic-name-wrapper' },
            utils.e('div',{className:'topic-name'},
                utils.e('p',{},utils.e('span',{},''),' commented on ',
                utils.e('time',{},'')),
            utils.e('div', {className:'post-content'},
                utils.e('p',{},''),   
        ))));
}

function createComment() {

    return utils.e('div',
        {className:'comment'},
        utils.e('div', 
        { className: 'header' },
            utils.e('img', {src:"./static/profile.png", alt:"avatar"},''),
            utils.e('p',{},
                utils.e('span',{},''),' posted on ',utils.e('time',{},'')),
            utils.e('p',{className:"post-content"},'')     
        ));

}

export {setupComment, showComments};