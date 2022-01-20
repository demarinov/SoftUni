function solve() {
    const addMovieList = Array.from(document.querySelectorAll('#container input'));
    const addMovieButton = document.querySelector('#container button');

    const moviesOnScreenList = document.querySelector('#movies ul');

    addMovieButton.addEventListener('click',addMovieToScreen, false);

    function addMovieToScreen(e) {

        const movieName = addMovieList[0].value;
        const movieHall = addMovieList[1].value;
        const moviePrice = Number(addMovieList[2].value).toFixed(2);

        e.preventDefault();

        function validateMovieInput() {

            if (addMovieList[0].value !== '' 
                && addMovieList[2].value !== ''
                && addMovieList[1].value !== ''
                && !isNaN(addMovieList[2].value)) {
                    return true;
                }

            return false;    
        };

        if (validateMovieInput()) {
            const li = document.createElement('li');
            const span = document.createElement('span');
            span.textContent = movieName;
            const strong = document.createElement('strong');
            strong.textContent = 'Hall: '+movieHall;
            li.appendChild(span);
            li.appendChild(strong);

            const div = document.createElement('div');
            const strongTwo = document.createElement('strong');
            strongTwo.textContent = moviePrice;
            const input = document.createElement('input');
            input.placeholder = "Tickets Sold";
            const buttonArchive = document.createElement('button');
            buttonArchive.textContent = "Archive";


            buttonArchive.addEventListener('click',addMovieToArchive);

            const archiveList = document.querySelector('#archive ul');

            function addMovieToArchive(e) {

                if (input.value !== '' && !isNaN(input.value)) {
                    const liArchive = document.createElement('li');
                    const spanArchive = document.createElement('span');
                    spanArchive.textContent = span.textContent;
                    const strongArchive = document.createElement('strong');
                    const buttonDelete = document.createElement('button');
                    buttonDelete.textContent = "Delete";

                    const totalPrice = (Number(input.value) * Number(strongTwo.textContent)).toFixed(2);
                    strongArchive.textContent = 'Total amount: '+ totalPrice;

                    liArchive.appendChild(spanArchive);
                    liArchive.appendChild(strongArchive);
                    liArchive.appendChild(buttonDelete);
                    archiveList.appendChild(liArchive);
                    
                    moviesOnScreenList.removeChild(li);

                    buttonDelete.addEventListener('click', deleteMovie);

                    function deleteMovie(e) {

                        archiveList.removeChild(liArchive);
                    }
                }

            }

            div.appendChild(strongTwo);
            div.appendChild(input);
            div.appendChild(buttonArchive);
            li.appendChild(div);

            moviesOnScreenList.appendChild(li);

            addMovieList[0].value = '';
            addMovieList[1].value = '';
            addMovieList[2].value = '';
        }
    };

    const archiveClear = document.querySelector('#archive button');
    

    archiveClear.addEventListener('click',clearArchive);

    
    function clearArchive(e) {

        const archiveList = document.querySelector('#archive ul');
        archiveList.innerHTML = '';
    }

}