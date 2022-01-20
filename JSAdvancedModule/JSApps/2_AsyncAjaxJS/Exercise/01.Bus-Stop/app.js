function getInfo() {

    const stopId = document.querySelector('#stopId').value;

    const result = getBusesByStopId(stopId);
    
    result.then(response => {
        showBuses(response);
    });
    

    function showBuses(result) {
        const stopName = document.querySelector('#stopName');
        const buses = document.querySelector('#buses');
        buses.innerHTML = '';

        if (result == -1) {

            stopName.textContent = 'Error';
            return;
        }

        stopName.textContent = result.name;

        for(const key in result.buses) {

            const li = document.createElement('li');
            li.textContent = `Bus ${key} arrives in ${result.buses[key]}`;
            buses.appendChild(li);
        }

    }

    async function getBusesByStopId(stopId) {

        const resultPromise = await
            fetch('http://localhost:3030/jsonstore/bus/businfo/' + stopId);

        let busInfo = {};
        try {
            busInfo = await resultPromise.json();
        } catch (e) {
            console.log(e.message);
            busInfo = -1;
        }

        return busInfo;
    }
}