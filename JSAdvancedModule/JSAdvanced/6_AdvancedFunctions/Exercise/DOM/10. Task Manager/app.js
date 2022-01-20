function solve() {
    const mainDivSections = Array.from(document.querySelectorAll('.wrapper section'));
    const taskAddButton = document.querySelector('#add');

    taskAddButton.addEventListener('click',addTask);

    function addTask(e) {

        e.preventDefault();
        const taskText = document.querySelector('#task');
        const taskDescription = document.querySelector('#description');
        const taskDate = document.querySelector('#date');
        const openSection = Array.from(mainDivSections[1].children)[1];

        if (taskDate.value === '' || taskDescription.value === '' || taskText.value === '') {
            return;
        }

        
        const taskArticle = document.createElement('article');
        const taskH3 = document.createElement('h3');
        taskH3.textContent = taskText.value;
        const taskPOne = document.createElement('p');
        taskPOne.textContent = 'Description: ' + taskDescription.value;
        const taskPTwo = document.createElement('p');
        taskPTwo.textContent = 'Due Date: '+taskDate.value;
        const taskDiv = document.createElement('div');
        taskDiv.className = 'flex';

        const taskButtonOne = document.createElement('button');
        const taskButtonТwo = document.createElement('button');

        taskButtonOne.className = 'green';
        taskButtonOne.textContent = 'Start';

        taskButtonOne.addEventListener('click', function startTask(e) {

            const progressSection = document.querySelector('#in-progress');

            const progressArticle = document.createElement('article');
            const progressH3 = document.createElement('h3');
            progressH3.textContent = Array.from(taskArticle
                .getElementsByTagName('h3'))[0].textContent;
            const progressPOne = document.createElement('p');
            const progressPArr = Array.from(taskArticle.getElementsByTagName('p'));
            progressPOne.textContent = progressPArr[0].textContent;
            const progressPTwo = document.createElement('p');
            progressPTwo.textContent = progressPArr[1].textContent;
            const progressDiv = document.createElement('div');
            progressDiv.className = 'flex';
            
            progressArticle.appendChild(progressH3);
            progressArticle.appendChild(progressPOne);
            progressArticle.appendChild(progressPTwo);
            progressArticle.appendChild(progressDiv);

            progressSection.appendChild(progressArticle);
            taskButtonТwo.click();

            const progressButtonOne = document.createElement('button');
            progressButtonOne.textContent = 'Delete';
            progressButtonOne.className = 'red';

            progressButtonOne.addEventListener('click', (e) => {
                progressSection.removeChild(progressArticle);
            });

            const progressButtonTwo = document.createElement('button');
            progressButtonTwo.textContent = 'Finish';
            progressButtonTwo.className = 'orange';
            progressButtonTwo.addEventListener('click',(e) => {
                const closedSection = Array.from(mainDivSections[3].children)[1];

                closedSection.appendChild(progressArticle);
                progressArticle.removeChild(progressDiv);
            })

            progressDiv.appendChild(progressButtonOne);
            progressDiv.appendChild(progressButtonTwo);
        });

        taskButtonТwo.className = 'red';
        taskButtonТwo.textContent = 'Delete';

        taskButtonТwo.addEventListener('click', function deleteTask(e) {
            openSection.removeChild(taskArticle);
        });

        taskDiv.appendChild(taskButtonOne);
        taskDiv.appendChild(taskButtonТwo);
        
        taskArticle.appendChild(taskH3);
        taskArticle.appendChild(taskPOne);
        taskArticle.appendChild(taskPTwo);
        taskArticle.appendChild(taskDiv);

        openSection.appendChild(taskArticle);

        // clear input content
        taskText.value = '';
        taskDescription.value = '';
        taskDate.value = '';
    };

}

