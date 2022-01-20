function loadRepos() {

    const buttonLoad = document.querySelector('button');

    buttonLoad.addEventListener('click', function(e) {

        const username = document.querySelector('#username').value;
        const url = 'https://api.github.com/users/'+username;

        let urlDataList = [];
        fetch(url)
        .then((response) => response.json())
        .then (data => urlDataList.push(data))
        .catch(e => console.log(e));

        const repos = document.querySelector('#repos');
        
        while(repos.firstChild) {
            repos.removeChild(repos.lastChild);
        }

        for(let urlData of urlDataList) {
            
        
            const link = document.createElement('a');

            const name = username + '/'+ urlData.full_name;

            link.textContent = name;

            liItem.appendChild(link);
            repos.appendChild(liItem);
        }

        // corner case
        if (urlDataList.length == 0) {
            // append
            const liItem = document.createElement('li');
            liItem.textContent = 'current';
            repos.appendChild(liItem);
        }
    });

}