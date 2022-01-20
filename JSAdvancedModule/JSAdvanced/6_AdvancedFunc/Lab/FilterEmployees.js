function filterEmployees(data, criteria) {
    // sorting ???
    const arr = JSON.parse(data);
    const crit = criteria.split('-');

    if (criteria === 'all') {
        printEmployee(arr);  
    } else {
        let result = arr.filter((a) => {
            return a[crit[0]] === crit[1];
        });
        printEmployee(result);
    }


    function printEmployee(arr) {

        let result = arr.map((emp,count) => `${count++}. ${emp['first_name']} ${emp['last_name']} - ${emp['email']}`)
        .join('\n');

        console.log(result);

    };
}

function test() {
    filterEmployees(`[{
        "id": "1",
        "first_name": "Ardine",
        "last_name": "Bassam",
        "email": "abassam0@cnn.com",
        "gender": "Female"
      }, {
        "id": "2",
        "first_name": "Kizzee",
        "last_name": "Jost",
        "email": "kjost1@forbes.com",
        "gender": "Female"
      },  
    {
        "id": "3",
        "first_name": "Evanne",
        "last_name": "Maldin",
        "email": "emaldin2@hostgator.com",
        "gender": "Male"
      }]`, 
    'gender-Female'
    );

    filterEmployees(`[{
        "id": "1",
        "first_name": "Kaylee",
        "last_name": "Johnson",
        "email": "k0@cnn.com",
        "gender": "Female"
      }, {
        "id": "2",
        "first_name": "Kizzee",
        "last_name": "Johnson",
        "email": "kjost1@forbes.com",
        "gender": "Female"
      }, {
        "id": "3",
        "first_name": "Evanne",
        "last_name": "Maldin",
        "email": "emaldin2@hostgator.com",
        "gender": "Male"
      }, {
        "id": "4",
        "first_name": "Evanne",
        "last_name": "Johnson",
        "email": "ev2@hostgator.com",
        "gender": "Male"
      }]`,
     'last_name-Johnson'
    );

    console.log('Print all');

    filterEmployees(`[{
      "id": "1",
      "first_name": "Kaylee",
      "last_name": "Johnson",
      "email": "k0@cnn.com",
      "gender": "Female"
    }, {
      "id": "2",
      "first_name": "Kizzee",
      "last_name": "Johnson",
      "email": "kjost1@forbes.com",
      "gender": "Female"
    }, {
      "id": "3",
      "first_name": "Evanne",
      "last_name": "Maldin",
      "email": "emaldin2@hostgator.com",
      "gender": "Male"
    }, {
      "id": "4",
      "first_name": "Evanne",
      "last_name": "Johnson",
      "email": "ev2@hostgator.com",
      "gender": "Male"
    }]`,
   'all'
  );

  console.log('Other cases');

    filterEmployees(`[{
      "id": "1",
      "first_name": "Kaylee",
      "last_name": "Johnson",
      "email": "k0@cnn.com",
      "gender": "Female"
    }, {
      "id": "2",
      "first_name": "Kizzee",
      "last_name": "Johnson",
      "email": "kjost1@forbes.com",
      "gender": "Female"
    }, {
      "id": "3",
      "first_name": "Evanne",
      "last_name": "Maldin",
      "email": "emaldin2@hostgator.com",
      "gender": "Male"
    }, {
      "id": "4",
      "first_name": "Evanne",
      "last_name": "Johnson",
      "email": "ev2@hostgator.com",
      "gender": "Male"
    }]`,
   'gender'
  );
}

test();