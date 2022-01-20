function subtract() {
    const result = document.querySelector('#result');
    const firstNum = document.querySelector('#firstNumber').value;
    const secondNum = document.querySelector('#secondNumber').value;

    result.textContent = Number(firstNum) - Number(secondNum);
}