function calc() {
    const num1 = document.querySelector('#num1').value;
    const num2 = document.querySelector('#num2').value;

    const sum = document.querySelector('#sum');

    sum.value = Number(num1)+Number(num2);
}
