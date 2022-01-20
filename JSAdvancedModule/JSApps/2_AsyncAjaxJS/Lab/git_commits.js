function loadCommits() {

    const commitButton = document.querySelector('#button');

    commitButton.addEventListener('click', function(e) {
        
        const username = document.querySelector('#username');
        const repo = document.querySelector('#repo');

        const url = 'https://api.github.com/repos/'+username+'/'+repo+'/commits';

        let error = null;
        let commits = [];
        fetch(url)
        .then((response) => response.json())
        .then(data => commits.push(data))
        .catch(e => error = e);

        const commitList = document.querySelector('#commits');

        while(commitList.firstChild) {
            commitList.removeChild(commitList.lastChild);
        }

        for(const commitData of commits) {
            // "<commit.author.name>: <commit.message>" 

            const text = commitData.author + ': '+commitData.message;
            const liCommit = document.createElement('li');
            liCommit.textContent = text;
            commitList.appendChild(liCommit);
        }

        if (error === null) {
            const text = `Error: ${error.status} (Not Found)`;
            const liCommit = document.createElement('li');
            liCommit.textContent = text;
            commitList.appendChild(liCommit);
        }
    });
}