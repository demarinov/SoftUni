function solve() {
  const textArea = document.querySelector('#input');

  const sentences = textArea.value.split('\.');

  for(let i = 0; i< sentences.length; i++) {
    // remove empty elements;
    sentences[i] = sentences[i].trim();
    if (sentences[i].length == 0) {
      sentences.splice(i,1);
      i--;
    }
  }

  const output = document.querySelector('#output');

  output.innerHTML = '';
  const countDivThree = sentences.length / 3;

  let arr = [];
  let nextCounter = 0;
  for (let i = 1; i <= sentences.length; i++) {
    if (sentences[i-1].length > 0) {
      arr.push(sentences[i - 1]);
    }

    if (i % 3 == 0) {
      output.innerHTML += `<p>${arr.join('.')}.</p>`;
      arr = [];
      nextCounter = i;
    }
  }

  const countRemDivThree = sentences.length % 3;
  arr = [];
  for (let i = nextCounter; i < nextCounter+countRemDivThree; i++) {
    if (sentences[i].length > 0) {
      arr.push(sentences[i]);
    }
  }

  if (arr.length > 0) {
    output.innerHTML += `<p>${arr.join('.')}.</p>`;
  }


}