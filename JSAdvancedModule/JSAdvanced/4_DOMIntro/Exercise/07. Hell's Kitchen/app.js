function solve() {
   document.querySelector('#btnSend').addEventListener('click', onClick);
   
   function onClick () {
      const input = document.querySelector('#inputs textarea').value;

      if (input.length == 0) {
         return;
      }
      let arr = JSON.parse(input);
      console.log(arr);
      let bestRestaurant = {name:'',averageSalary: 0, maxSalary: 0, workers:[]};

      console.log(bestRestaurant);
      for(const restaurant of arr) {
         const restaurantData = restaurant.split('-');
         const restaurantName = restaurantData[0].trim();
         const workerData = restaurantData[1].trim().split(',');

         if (bestRestaurant.name === restaurantName) {

            bestRestaurant.workers.push.apply(bestRestaurant.workers,workerData);
            let [workerAvgSalary, workerMaxSalary] = getAvgMaxSalary(bestRestaurant.workers);
            bestRestaurant.averageSalary = workerAvgSalary;
            bestRestaurant.maxSalary = workerMaxSalary;
            continue;
         }

         function getAvgMaxSalary(workerData) {

            let avgSalary = 0;
            let maxSalary = 0;
            for(const worker of workerData) {

               const workerArr = worker.trim().split(' ');
               const name = workerArr[0].trim();
               const salary = Number(workerArr[1]);

               if (maxSalary < salary) {
                  maxSalary = salary;
               }

               avgSalary += salary;   
            }

            avgSalary /= workerData.length;
            console.log("avg: "+avgSalary+" max:"+maxSalary);
            return [avgSalary, maxSalary];
         }

         let [workerAvgSalary, workerMaxSalary] = getAvgMaxSalary(workerData);

         console.log(workerAvgSalary);
         console.log(bestRestaurant.averageSalary);
         if (bestRestaurant.averageSalary < workerAvgSalary) {
 
            bestRestaurant.name = restaurantName;
            bestRestaurant.averageSalary = workerAvgSalary;
            bestRestaurant.maxSalary = workerMaxSalary;
            bestRestaurant.workers = workerData;
         }
      }
      
      console.log(bestRestaurant);

      const restaurantStr = `
      Name: ${bestRestaurant.name} Average Salary: ${bestRestaurant.averageSalary.toFixed(2)} Best Salary: ${bestRestaurant.maxSalary.toFixed(2)}`;

      console.log(restaurantStr);

      const outputRestaurant = document.querySelector('#bestRestaurant p');

      outputRestaurant.textContent = restaurantStr;

      let outputWorkerStr = '';

      bestRestaurant.workers.sort((w1, w2) => 
      Number(w2.trim().split(' ')[1]) - Number(w1.trim().split(' ')[1]));
      
      for(const worker of bestRestaurant.workers) {
         const name = worker.trim().split(' ')[0];
         const salary = worker.trim().split(' ')[1];

         console.log(worker);
         outputWorkerStr +=`Name: ${name} With Salary: ${salary} `;
      }

      outputWorkerStr = outputWorkerStr.trim();

      const outputWorkerElement = document.querySelector('#workers p');

      outputWorkerElement.textContent = outputWorkerStr;
   }
}



function solveV2() {
   const html = {
       inputField: document.querySelector("#inputs textarea"),
       outputBestName: document.querySelector("#bestRestaurant p"),
       outputBestWorkers: document.querySelector("#workers p"),
   }

   const getBest = data =>
       Object.entries(data).sort(
           (x, y) => getAverage(y[1]) - getAverage(x[1])
       )[0]

   const getAverage = workersData =>
       workersData.reduce((a, v) => a + v[1], 0) / workersData.length

   function deserialize(data) {
       const getWorkers = data =>
           data
               .split(", ")
               .map(x => x.split(" ").map(y => (isNaN(y) ? y : Number(y))))

       return JSON.parse(data)
           .map(x => x.split(" - "))
           .reduce((a, v) => {
               const [name, workers] = v

               a[name] = a[name]
                   ? [...a[name], ...getWorkers(workers)]
                   : getWorkers(workers)
               return a
           }, {})
   }

   function displayResult(data) {
       let [name, workers] = data
       workers = workers.sort((x, y) => y[1] - x[1])

       html.outputBestName.innerHTML = `Name: ${name} Average Salary: ${getAverage(
           workers
       ).toFixed(2)} Best Salary: ${workers[0][1].toFixed(2)}`

       html.outputBestWorkers.innerHTML = `${workers
           .map(x => `Name: ${x[0]} With Salary: ${x[1]}`)
           .join(" ")}`
   }

   document
       .getElementById("btnSend")
       .addEventListener("click", () =>
           displayResult(getBest(deserialize(html.inputField.value)))
       )
}