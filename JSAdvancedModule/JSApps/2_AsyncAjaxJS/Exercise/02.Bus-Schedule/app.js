function solve() {
    let stopInfo = { 'next': 'depot' };
    function depart() {
        const info = document.querySelector('#info');
        let stopId = stopInfo.next;
        const result = getNextStop(stopId);
        
        result
        .then(response => {
            stopInfo = response;
            info.textContent = 'Next stop ' + stopInfo.name;
            const departBut = document.querySelector('#depart');
            const arriveBut = document.querySelector('#arrive');

            departBut.disabled = true;
            arriveBut.disabled = false;

            return stopInfo;
        })
        .catch(e => {
            console.log(e);

            info.textContent = 'Error';
            const departBut = document.querySelector('#depart');
            const arriveBut = document.querySelector('#arrive');

            departBut.disabled = true;
            arriveBut.disabled = true;
        });

        async function getNextStop(stopId) {
            const result = await fetch('http://localhost:3030/jsonstore/bus/schedule/' + stopId);
            return result.json();

        }

    }

    function arrive() {

        info.textContent = `Arriving at ${stopInfo.name}`;
        const departBut = document.querySelector('#depart');
        const arriveBut = document.querySelector('#arrive');

        departBut.disabled = false;
        arriveBut.disabled = true;
    }

    return {
        depart,
        arrive
    };
}

let result = solve();