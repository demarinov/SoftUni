function solve() {

  const sectionOne = document.querySelector('#quizzie section:nth-child(2)');
  const sectionTwo = document.querySelector('#quizzie section:nth-child(3)');
  const sectionThree = document.querySelector('#quizzie section:nth-child(4)');

  let correctAnswers = [];
  let wrongAnswers = [];

  for (const ul of Array.from(sectionOne.children)) {
    const liQuestionText = Array.from(Array.from(Array.from(ul.children)[0].children)[0].children)[0].textContent;

    const liAnswerOne = Array.from(Array.from(ul.children)[1].children)[0];

    liAnswerOne.addEventListener('click', (e) => {
      correctAnswers.push(true);
      // go to the next section
      sectionOne.style.className = 'hidden';
      sectionOne.style.display = 'none';
      sectionTwo.style.className = '';
      sectionTwo.style.display = 'block';
      
    });

    const liAnswerTwo = Array.from(Array.from(ul.children)[2].children)[0];
    liAnswerTwo.addEventListener('click', (e) => {
      wrongAnswers.push(true);
      // go to the next section
      sectionOne.style.className = 'hidden';
      sectionOne.style.display = 'none';
      sectionTwo.style.className = '';
      sectionTwo.style.display = 'block';
    });

  }


  for (const ul of Array.from(sectionTwo.children)) {
    const liQuestionText = Array.from(Array.from(Array.from(ul.children)[0].children)[0].children)[0].textContent;

    const liAnswerOne = Array.from(Array.from(ul.children)[1].children)[0];

    liAnswerOne.addEventListener('click', (e) => {
      wrongAnswers.push(true);
      // go to the next section
      sectionTwo.style.className = 'hidden';
      sectionTwo.style.display = 'none';
      sectionThree.style.className = '';
      sectionThree.style.display = 'block';
    });

    const liAnswerTwo = Array.from(Array.from(ul.children)[2].children)[0];
    liAnswerTwo.addEventListener('click', (e) => {
      correctAnswers.push(true);
      // go to the next section
      sectionTwo.style.className = 'hidden';
      sectionTwo.style.display = 'none';
      sectionThree.style.className = '';
      sectionThree.style.display = 'block';
    });

  }

  const resultOutput = document.querySelector('#results');

  for (const ul of Array.from(sectionThree.children)) {
    const liQuestionText = Array.from(Array.from(Array.from(ul.children)[0].children)[0].children)[0].textContent;

    const liAnswerOne = Array.from(Array.from(ul.children)[1].children)[0];

    liAnswerOne.addEventListener('click', (e) => {
      correctAnswers.push(true);
      // end the quiz and show result

      sectionOne.style.className = 'hidden';
      sectionOne.style.display = 'none';
      sectionTwo.style.className = 'hidden';
      sectionTwo.style.display = 'none';
      sectionThree.style.className = 'hidden';
      sectionThree.style.display = 'none';

      if (wrongAnswers.length == 0 && correctAnswers.length == 3) {
        Array.from(Array.from(resultOutput.children)[0].children)[0].textContent =
          'You are recognized as top JavaScript fan!';
          
      } else {
        Array.from(Array.from(resultOutput.children)[0].children)[0].textContent =
          `You have ${correctAnswers.length} right answers`;
      }

      resultOutput.style.display = 'block';
    });

    const liAnswerTwo = Array.from(Array.from(ul.children)[2].children)[0];
    liAnswerTwo.addEventListener('click', (e) => {
      wrongAnswers.push(true);
      // end the quiz and show result

      sectionOne.style.className = 'hidden';
      sectionOne.style.display = 'none';
      sectionTwo.style.className = 'hidden';
      sectionTwo.style.display = 'none';
      sectionThree.style.className = 'hidden';
      sectionThree.style.display = 'none';

      resultOutput.style.display = 'block';
      Array.from(Array.from(resultOutput.children)[0].children)[0].textContent =
          `You have ${correctAnswers.length} right answers`;
      
    });

  }

}



