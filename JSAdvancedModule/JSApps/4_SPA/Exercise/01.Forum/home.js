import * as post from "./post.js";
import * as comment from "./comment.js";
import * as utils from "./utils.js";


function doHome() {
    const cancelBtn = document.querySelector('.container main form .cancel');

    cancelBtn.addEventListener('click', clearInput);
    const form = document.querySelector('.container main form');

    function clearInput(e) {

        e.preventDefault();
        utils.clearForm(form);
    }

    const topicContainer = document.querySelector('.topic-container');
    const body = document.querySelector('body');

    post.setupPost(body, topicContainer);
    comment.setupComment(body);

    post.showPosts(topicContainer);
}

export {doHome};