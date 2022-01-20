
window.addEventListener('load', loadStudents);

const form = document.querySelector('form');

form.addEventListener('submit', createStudent);

let students = [];

async function createStudent(e) {

    e.preventDefault();
    const firstName = document.querySelector('input[name=firstName]');
    const lastName = document.querySelector('input[name=lastName]');
    const facNumber = document.querySelector('input[name=facultyNumber]');
    const grade = document.querySelector('input[name=grade]');

    if (firstName.value === '' || lastName.value === ''
        || facNumber.value === '' || grade.value === '') {
        console.error('Invalid input field: still empty!');
        return;
    }

    const data = {
        "firstName":firstName.value,
        "lastName":lastName.value,
        "facultyNumber" : facNumber.value,
        "grade": grade.value
    }

    await sendStudentPostRequest(data);

     // clear inputs
     firstName.value = '';
     lastName.value = '';
     facNumber.value = '';
     grade.value = '';

}

async function sendStudentPostRequest(data) {

    const response =
        await fetch('http://localhost:3030/jsonstore/collections/students',
            {
                method: 'post',
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(data)
            });

    if (response.status === 200) {

        const pureResponse = await response.json();

        // console.log(Object.values(pureResponse));

        // students = Object.values(pureResponse);

    } else {
        console.error(response.statusText);
    }

}

async function loadStudents(e) {

    await sendStudentGetRequest();

    if (students.length > 0) {

        const tableResults = document.querySelector('#results tbody');

        students.forEach(s => {
            const tr = createElement('tr', '', '');

            const firstNameCol = createElement('td', '', s.firstName);
            const lastNameCol = createElement('td', '', s.lastName);
            const facultyNumberCol = createElement('td', '', s.facultyNumber);
            const gradeCol = createElement('td', '', s.grade);

            tr.appendChild(firstNameCol);
            tr.appendChild(lastNameCol);
            tr.appendChild(facultyNumberCol);
            tr.appendChild(gradeCol);

            // console.log(JSON.stringify(s));
            tableResults.appendChild(tr);
        });
    }
}

async function sendStudentGetRequest() {
    const response =
        await fetch('http://localhost:3030/jsonstore/collections/students',
            {
                method: 'get',
                headers: {
                    "Content-Type": "application/json"
                }
            });

    if (response.status === 200) {
		
        const pureResponse = await response.json();

        // console.log(Object.values(pureResponse));

        students = Object.values(pureResponse);
    } else {
        console.error(response.statusText);
    }


}

function createElement(tag, clazz, value) {

    const element = document.createElement(tag);

    if (clazz !== '') {
        element.className = clazz;
    }

    element.value = value;
    element.textContent = value;

    return element;
}