function solve() {

    const secondParam = document.querySelector('#naming-convention').value;
    const firstParam = document.querySelector('#text').value;

    const cases = {

            "Camel Case": () => {
                let arr = firstParam.toLowerCase().split(' ');

                let result = arr[0];

                for(let i = 1; i < arr.length; i++) {
                    result += arr[i].charAt(0).toUpperCase()+arr[i].substring(1);
                }
                return result;
            },
            "Pascal Case": () => {
              let arr = firstParam.toLowerCase().split(' ');
              let result = arr[0].charAt(0).toUpperCase() + arr[0].substring(1);

              for(let i = 1; i < arr.length; i++) {
                  result += arr[i].charAt(0).toUpperCase()+arr[i].substring(1);
              }
              return result;
            }
    }


    const span = document.querySelector('#result');
    if (secondParam !== 'Camel Case' && secondParam !== 'Pascal Case') {
      span.textContent = 'Error!';
    } else {
      span.textContent = cases[secondParam]();
    }

}