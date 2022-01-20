function solve() {

    // replaceAll not working in Judge/NodeJS, should be described in the SoftUni docs ???

    const lectureName = document.getElementsByName('lecture-name')[0];
    const lectureDate = document.getElementsByName('lecture-date')[0];
    const lectureDropDown = document.getElementsByName('lecture-module')[0];
    const addLectureBut = document.querySelector('.form-control button');

    addLectureBut.addEventListener('click', function (e) {

        e.preventDefault();

        if (lectureName.value === '') {
            return;
        }

        if (lectureDate.value === '') {
            return;
        }

        const options = Array.from(lectureDropDown.children);

        // console.log(lectureDropDown.value);

        // let selectedModule = lectureDropDown;
        options.forEach(o => {
            if (o.selected) {
                selectedModule = o;
            }
        });

        if (selectedModule.value === 'Select module') {
            return;
        }

        const trainingsSection = document.querySelector('.user-view .modules');



        // create new module
        const newModule = document.createElement('div');

        // create header
        const moduleName = document.createElement('h3');
        moduleName.textContent = selectedModule.value
            .toLocaleUpperCase() + '-MODULE';
        newModule.className = 'module';

        // create list
        const moduleList = document.createElement('ul');
        const moduleListElement = document.createElement('li');
        moduleListElement.className = 'flex';
        const moduleHeaderDate = document.createElement('h4');
        let lectureDateInfo = lectureDate.value.split('T');
        moduleHeaderDate.textContent = lectureName.value
            + ' - ' + lectureDateInfo[0].replace(/-/g,'/') + ' - ' + lectureDateInfo[1];
        const moduleListButton = document.createElement('button');
        moduleListButton.className = 'red';
        moduleListButton.textContent = 'Del';

        // Delete list element
        moduleListButton.addEventListener('click', function (e) {


            // Check if module list is empty and delete module if so
            const modList = Array.from(e.target.parentElement.parentElement.children);

            if (modList.length == 1) {
                const module = e.target.parentElement.parentElement.parentElement;
                trainingsSection.removeChild(module);
            } else {
                moduleListElement.parentElement.removeChild(moduleListElement);
            }
        });

        moduleListElement.appendChild(moduleHeaderDate);
        moduleListElement.appendChild(moduleListButton);


        // check if module with same name exists
        const modules = Array.from(document.querySelectorAll('.modules .module'));

        let foundModule = false;
        for (const module of modules) {

            const moduleChildren = Array.from(module.children);
            const name = moduleChildren[0].textContent;

            if (name === moduleName.textContent) {
                // use existing module
                const existingModuleList = moduleChildren[1];
                existingModuleList.appendChild(moduleListElement);

                // sort module list
                const existingModuleListElements = Array.from(existingModuleList.children);

                existingModuleListElements.sort((e1, e2) => {

                    const e1Header = Array.from(e1.children)[0];
                    const e2Header = Array.from(e2.children)[0];

                    const dateOne = e1Header.textContent.split(' - ');
                    const dateTwo = e2Header.textContent.split(' - ');

                    return (dateOne[1] + dateOne[2])
                        .localeCompare((dateTwo[1] + dateTwo[2]));
                });

                existingModuleListElements.forEach(e =>
                    existingModuleList.removeChild(e));

                existingModuleListElements.forEach(e => existingModuleList.appendChild(e));

                foundModule = true;
                break;
            }
        };

        if (foundModule) {
            lectureName.value = '';
            lectureDate.value = '';
            selectedModule.selected = false;
            // selectedModule.value === 'Select module'
            return;
        }


        moduleList.appendChild(moduleListElement);

        // append new module to trainings section
        newModule.appendChild(moduleName);
        newModule.appendChild(moduleList);
        trainingsSection.appendChild(newModule);

        lectureName.value = '';
        lectureDate.value = '';
        selectedModule.selected = false;
    });

    // test();
}

function test() {
    console.log(document.querySelector('input[name="lecture-name"]'));

let elements = {
    form: document.getElementsByTagName('form')[0],
    name: document.querySelector('input[name="lecture-name"]'),
    date: document.querySelector('input[name="lecture-date"]'),
    module: document.querySelector('select[name="lecture-module"]'),
    addBtn: document.querySelector('form button'),
    modulesDiv: document.querySelector('.modules'),
    moduleList: ()=>Array.from(document.querySelectorAll('.module')),
    listItems: ()=>Array.from(document.querySelectorAll('.flex')),
}

elements.name.value = "DOM";
elements.date.value = "2020-09-28T18:00";
elements.module.value = "Advanced";

elements.addBtn.click();

console.log(elements.moduleList()[0].children[0].textContent == 'ADVANCED-MODULE');

elements.name.value = "Arrays";
elements.date.value = "2020-09-17T18:00";
elements.module.value = "Advanced";

elements.addBtn.click();
console.log(elements.moduleList().length === 1);
console.log(elements.listItems()[0].children[0].textContent === 'Arrays - 2020/09/17 - 18:00');


elements.name.value = "Arrays";
elements.date.value = "2020-09-30T18:30";
elements.module.value = "Fundamentals";

elements.addBtn.click();

console.log(elements.listItems()[0].children[1].className === 'red');
}
