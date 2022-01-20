window.addEventListener('load', solve);

function solve() {
    

    const genre = document.querySelector('#genre');
    const name = document.querySelector('#name');
    const author = document.querySelector('#author');
    const date = document.querySelector('#date');

    const addBtn = document.querySelector('#add-btn');

    addBtn.addEventListener('click', function(e) {

        e.preventDefault();

        // validate input
        if (genre.value === '' || name.value === ''
        || author.value === '' || date.value === '') {
            return;
        }


        // move items to songs collection
        const songsCollection = document.querySelector('.all-hits-container');

        const songDiv = createElement('div','hits-info','');
        const songImg = createElement('img','','');
        songImg.src = './static/img/img.png';
        const songGenre = createElement('h2','','Genre: '+genre.value);
        const songName = createElement('h2','','Name: '+name.value);
        const songAuthor = createElement('h2','','Author: '+author.value);
        const songDate = createElement('h3','','Date: '+date.value);
        
        const songSaveBtn = createElement('button','save-btn','Save song');
        const songLikeBtn = createElement('button','like-btn','Like song');
        const songDeleteBtn = createElement('button','delete-btn','Delete');

        songDiv.appendChild(songImg);
        songDiv.appendChild(songGenre);
        songDiv.appendChild(songName);
        songDiv.appendChild(songAuthor);
        songDiv.appendChild(songDate);

        songDiv.appendChild(songSaveBtn);
        songDiv.appendChild(songLikeBtn);
        songDiv.appendChild(songDeleteBtn);

        songsCollection.appendChild(songDiv);

        // clear input
        genre.value = '';
        name.value = '';
        author.value = '';
        date.value = '';

        hideLikesSection();

        // Like song    
        songLikeBtn.addEventListener('click', function(e) {
            const likes = document.querySelector('.likes p');

            let likesData = likes.textContent.split(': ');
            let likesNum = Number(likesData[1]);

            likesNum++;

            likes.textContent = likesData[0]+': '+likesNum;
            e.target.disabled = true;
        });

        // Save song
        songSaveBtn.addEventListener('click',function(e) {

            const saveDiv = document.querySelector('.saved-container');

            saveDiv.appendChild(songDiv);

            // songDiv.parentElement.removeChild(songDiv);

            songDiv.removeChild(songSaveBtn);
            songDiv.removeChild(songLikeBtn);

            // may be hide likesSection
            hideLikesSection();
        });

        // Delete song
        songDeleteBtn.addEventListener('click', function(e) {
            songDiv.parentElement.removeChild(songDiv);

            hideLikesSection();
        });
    });

    function createElement(type, clazz, value) {

        const element = document.createElement(type);

        if (clazz !== '') {
            element.className = clazz;
        }

        element.textContent = value;

        return element;
    }

    function hideLikesSection() {
        // may be hide the totalLikes when songCollection is empty
        const likesSection = document.querySelector('#total-likes');
        const songsCollection = document.querySelector('.all-hits-container');
        
        const songsCollectionChildren = Array
        .from(songsCollection.children);
       
        if (songsCollectionChildren.length === 1) {
            likesSection.style.display = 'none';
        } else {
            likesSection.style.display = 'inline';
        }
    }

}