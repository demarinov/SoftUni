
async function fplLogin() {

    const url = 'https://users.premierleague.com/accounts/login/'
    const payload = {
        'password': '',
        'login': '',
        'redirect_uri': 'https://fantasy.premierleague.com/a/login',
        'app': 'plfpl-web'
    }


    const response = await fetch(url, {
        method: "post",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(payload)
    });

    if (response.ok) {

        const data = await response.json();

        console.log(data);
    } else {
        console.log('Error login!');
    }
}

fplLogin();