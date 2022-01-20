function attachEvents() {

    displayPosts();

    function createElement(type, clazz, value) {

        const element = document.createElement(type);

        if (clazz !== '') {
            element.className = clazz;
        }

        element.textContent = value;

        return element;
    }

    async function displayPosts() {

        const loadButton = document.querySelector('#btnLoadPosts');
        const viewButton = document.querySelector('#btnViewPost');
        const selectPosts = document.querySelector('#posts');
        let postsCache = {};

        loadButton.addEventListener('click', async function (e) {

            const posts = await getData('/posts');

            selectPosts.innerHTML = '';

            for(const postKey in posts) {

                const option = createElement('option','',posts[postKey].title);
                option.value = postKey;

                selectPosts.appendChild(option);
            }

            postsCache = posts;

        });

        viewButton.addEventListener('click', async function (e) {

            const comments = await getData('/comments');

            const selectedPost = selectPosts.options[selectPosts.selectedIndex];

            let requestedComments = [];
            for(const commentKey in comments) {
                if (comments[commentKey].postId === selectedPost.value) {
                    requestedComments.push(comments[commentKey]);
                }
            }

            // display post and comments
            const postTitle = document.querySelector('#post-title');
            postTitle.textContent = selectedPost.textContent;

            const postList = document.querySelector('#post-body');
            postList.innerHTML = '';
        
            const postItem = createElement('li','',postsCache[selectedPost.value].body);

            postList.appendChild(postItem);

            const commentList = document.querySelector('#post-comments');

            commentList.innerHTML = '';

            for(const comment of requestedComments) {

                const commentItem = createElement('li','',comment.text);

                commentList.appendChild(commentItem);
            }

        });



    }

    async function getData(url) {
        const baseUrl = 'http://localhost:3030/jsonstore/blog';
        const response = await fetch(baseUrl + url);

        if (!response.ok) {
            throw new Error('Error');
        }

        const data = response.json();

        return data;
    }

}

attachEvents();