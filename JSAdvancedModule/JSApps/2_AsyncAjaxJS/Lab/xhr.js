function loadGitHubRepo() {

    const button = document.querySelector('#button');

    button.addEventListener('click', function (e) {

        const httpRequest = new XMLHttpRequest();

        const url = "https://api.github.com/users/testnakov/repos";

        httpRequest.addEventListener('readystatechange', function (e) {

            if (httpRequest.readyState == 4 && httpRequest.status == 200) {
                document.querySelector('#res').textContent = httpRequest.responseText;
            }
        });

        httpRequest.open(url);
        httpRequest.send();
    });

}