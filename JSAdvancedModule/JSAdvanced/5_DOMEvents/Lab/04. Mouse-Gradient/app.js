function attachGradientEvents() {
    
    const gradient = document.querySelector('#gradient');

    gradient.addEventListener('mousemove', gradientMove);
    gradient.addEventListener('mouseout', gradientOut);

    const result = document.querySelector('#result');

    function gradientMove(event) {
        console.log(event.offsetX);
        console.log(event.target.clientWidth);

        let power = event.offsetX / (event.target.clientWidth - 1);
        let percent = Math.trunc(power * 100);

        result.textContent = percent + '%';
    }

    function gradientOut(event) {
        result.textContent = '';
    }
}