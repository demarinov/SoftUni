function solution() {
    const articlesPromise = fetchArticles();

    articlesPromise.then(articleList => {

        const mainSection = document.querySelector('#main');
        
        for (const a of articleList) {
            console.log(a);

            // create article section
            const divAccordion = createElement('div', 'accordion', '');
            const divHead = createElement('div', 'head', '');
            const spanTitle = createElement('span', '', a.title);
            divHead.appendChild(spanTitle);
            const buttonMore = createElement('button', 'button', '');
            buttonMore.id = a['_id'];
            buttonMore.textContent = 'More';
            divHead.appendChild(buttonMore);

            const divExtra = createElement('div','extra','');

            const aContentPromise = fetchArticleContent(buttonMore.id);

            aContentPromise.then(c => {
                const p = createElement('p','',c.content);

                divExtra.appendChild(p);
                divAccordion.appendChild(divExtra);
            })

            buttonMore.addEventListener('click', function(e) {
                if (e.target.textContent === 'More') {
                    e.target.textContent = 'Less';
                    divExtra.style.display = 'block';
                } else if (e.target.textContent === 'Less') {
                    e.target.textContent = 'More';
                    divExtra.style.display = 'none';
                }
            });

            divAccordion.appendChild(divHead);

            mainSection.appendChild(divAccordion);


            
        }

    });

    function createElement(type, clazz, value) {

        const element = document.createElement(type);

        if (clazz !== '') {
            element.className = clazz;
        }

        element.textContent = value;

        return element;
    }

    async function fetchArticles() {

        const result = await fetch('http://localhost:3030/jsonstore/advanced/articles/list');

        return result.json();
    }

    async function fetchArticleContent(id) {
        const result = await fetch('http://localhost:3030/jsonstore/advanced/articles/details/'+id);

        return result.json();
    }
}

solution();