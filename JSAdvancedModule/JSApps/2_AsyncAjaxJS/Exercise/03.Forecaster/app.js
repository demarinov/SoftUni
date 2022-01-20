function attachEvents() {
    const degrees = `&#176`;


    getForecasts();

    function handleError() {
        const forecast = document.querySelector('#forecast');
        const current = document.querySelector('#current');
        forecast.style.display = 'block';
        const spanError = createElement('span', 'error', 'Error');
        current.appendChild(spanError);
    }

    function getForecasts() {

        const submitButton = document.querySelector('#submit');
        submitButton.addEventListener('click', async function (e) {
            const locationInputName = document.querySelector('#location').value;
            const forecast = document.querySelector('#forecast');

            forecast.style.display = 'none';
            clearForecasts();

            try {
                const locationProm = getLocations();

                const location = await findLocation(locationProm, locationInputName);

                renderCurrentForecast(location);

                render3DaysForecast(location);
            } catch (e) {
                console.log(e + ' msn');
                handleError();
            }

        });
    };

    function createElement(type, classValue, value) {

        const element = document.createElement(type);

        element.className = classValue;
        element.innerHTML = value;

        return element;
    }

    const clearForecasts = () => {
        document.getElementById('current').innerHTML = `<div class="label">Current conditions</div>`
        document.getElementById('upcoming').innerHTML = `<div class="label">Three-day forecast</div>`
    }

    async function threeDayForecast(code) {
        const result = await fetch('http://localhost:3030/jsonstore/forecaster/upcoming/' + code);
        return result.json();
    }

    async function currentCondition(code) {
        const result = await fetch('http://localhost:3030/jsonstore/forecaster/today/' + code);

        return result.json();
    }

    async function findLocation(locationProm, name) {


        let locationResult = await locationProm.then(response => {

            let result;
            for (const loci of response) {
                if (loci.name === name) {
                    result = loci;
                    // console.log(name);
                    break;
                }
            }

            return result;
        }).catch(e => {
            // handleError();
            console.log(e);
        });

        return locationResult;

    }

    async function getLocations() {

        const result =
            await fetch('http://localhost:3030/jsonstore/forecaster/locations');

        if (!result.ok) {
            throw new Error('Error');
        }

        const data = result.json();
        if (!data) {
            throw new Error('Error');
        }

        return data;
    }

    function renderCurrentForecast(location) {

        if (location === undefined) {
            throw new Error('Error');
        }

        try {
            const loci = location;
            let currentForecast = currentCondition(loci.code);

            const forecast = document.querySelector('#forecast');
            forecast.style.display = 'block';

            currentForecast.then(condition => {

                const current = document.querySelector('#current');

                if (current.children[1]) {
                    current.removeChild(current.children[1]);
                }
                // current.textContent = JSON.stringify(condition);

                let symbol = getSymbol(condition.forecast.condition);

                const divForecasts = createElement('div', 'forecasts', '');
                const spanSymbol = createElement('span', 'condition symbol', symbol);
                const spanCondition = createElement('span', 'condition', '');
                const spanCity = createElement('span', 'forecast-data', condition.name);
                const spanGrads = createElement('span', 'forecast-data',
                    condition.forecast.low + degrees + '/' + condition.forecast.high + degrees);
                const spanWeather = createElement('span', 'forecast-data'
                    , condition.forecast.condition);

                spanCondition.appendChild(spanCity);
                spanCondition.appendChild(spanGrads);
                spanCondition.appendChild(spanWeather);

                divForecasts.appendChild(spanSymbol);
                divForecasts.appendChild(spanCondition);
                current.appendChild(divForecasts);
            });

        } catch (e) {
            throw new Error('Error');
        }
    }

    function render3DaysForecast(location) {

        if (location === undefined) {
            throw new Error('Error');
        }

        try {
            const loci = location;
            let threeDaysCondition = threeDayForecast(loci.code);

            threeDaysCondition.then(forecast => {
                conditions = forecast.forecast;

                const upcoming = document.querySelector('#upcoming');
                const forecastInfo =
                    createElement('div', 'forecast-info', '');

                for (const condition of conditions) {

                    const spanUpcoming =
                        createElement('span', 'upcoming', '');

                    let symbol = getSymbol(condition.condition);

                    const spanSymbol =
                        createElement('span', 'symbol', symbol);

                    const spanGrads = createElement('span', 'forecast-data',
                        condition.low + degrees + '/' + condition.high + degrees);
                    const spanWeather = createElement('span', 'forecast-data'
                        , condition.condition);

                    spanUpcoming.appendChild(spanSymbol);
                    spanUpcoming.appendChild(spanGrads);
                    spanUpcoming.appendChild(spanWeather);

                    forecastInfo.appendChild(spanUpcoming);

                }

                upcoming.appendChild(forecastInfo);
                // upcoming.textContent = JSON.stringify(condition);
            });

        } catch (e) {
            throw e;
        }
    };

    function getSymbol(condition) {

        let symbol = '';
        if (condition.toLocaleLowerCase() === 'sunny') {
            symbol = `&#x2600;`;
        } else if (condition.toLocaleLowerCase() === 'partly sunny') {
            symbol = `&#x26C5;`;
        } else if (condition.toLocaleLowerCase() === 'overcast') {
            symbol = `&#x2601;`;
        } else if (condition.toLocaleLowerCase() === 'rain') {
            symbol = `&#x2614;`;
        } else if (condition.toLocaleLowerCase() === 'degrees') {
            symbol = `&#176;`;
        }

        return symbol;
    }
}

attachEvents();