function getArticleGenerator(articles) {

    let count = 0;
    function showNext() {

        if (articles.length > count) {

            const divContent = document.querySelector('#content');

            const article = document.createElement('article');

            let divArr = Array.from(divContent.children);
            divArr.unshift(article);

            divContent.children = [];

            divArr.forEach(a => divContent.appendChild(a));

            article.textContent += articles[count];
            count++;
        }

    }

    return showNext;
}
