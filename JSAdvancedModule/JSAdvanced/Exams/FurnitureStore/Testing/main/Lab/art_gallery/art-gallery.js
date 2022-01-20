class ArtGallery {


    constructor(creator) {

        this.creator = creator;
        this.possibleArticles = {
            'picture': 200,
            "photo": 50,
            "item": 250
        };

        this.listOfArticles = [];
        this.guests = [];
    };

    addArticle(articleModel, articleName, quantity) {
        articleModel = articleModel.toLocaleLowerCase();
        quantity = Number(quantity);

        if (!(articleModel in this.possibleArticles)) {
            throw new Error(`This article model is not included in this gallery!`);
        }

        let article = this.listOfArticles.find(a => a.articleName === articleName);

        if (article !== undefined) {
            if (article.articleModel === articleModel) {
                article.quantity += Number(quantity);
            } else {
                this.listOfArticles.push({ articleModel, articleName, quantity });
            }
        } else {
            this.listOfArticles.push({ articleModel, articleName, quantity });
        }

        return `Successfully added article ${articleName} with a new quantity- ${quantity}.`;
    };

    inviteGuest(guestName, personality) {

        let guest = this.guests.find(g => g.guestName === guestName);

        if (guest !== undefined) {

            throw new Error(`${guestName} has already been invited.`);
        }

        const personalityTable = {
            "Vip": 500,
            "Middle": 250
        };

        let personalityPoints = 50;

        if ((personality in personalityTable)) {
            personalityPoints += personalityTable[personality];
        }

        this.guests.push({
            guestName,
            'points': personalityPoints,
            purchaseArticle: 0
        });

        return `You have successfully invited ${guestName}!`;
    };

    buyArticle(articleModel, articleName, guestName) {
        let article = this.listOfArticles.find(a => a.articleName === articleName);

        if (article === undefined || article.articleModel !== articleModel) {
            throw new Error("This article is not found.");
        }

        if (article.quantity === 0) {
            return `The ${articleName} is not available.`;
        }

        if (!(this.guests.some(g => g.guestName === guestName))) {

            return "This guest is not invited.";
        }

        let guest = this.guests.find(g => g.guestName === guestName);

        let guestPoints = Number(guest.points);
        let possibleArticlePoints = Number(this.possibleArticles[articleModel]);

        if (guestPoints < possibleArticlePoints) {

            return `You need to more points to purchase the article.`;
        }

        guestPoints -= possibleArticlePoints;
        guest.points = guestPoints;
        guest.purchaseArticle++;

        article.quantity--;

        return `${guestName} successfully purchased the article worth ${possibleArticlePoints} points.`;

    };

    showGalleryInfo(criteria) {

        //•	 If the criterion is-"article"- then you need to return all 
        // the information about the articles saved in listOfArticle array 
        // in following format:
        // On first line show the following message:
        // "Articles information:"
        // On the lines, display information about each article:
        //{articleModel} - {articleName} - {quantity}
        //	If the criterion is-"guest"- then you need to return all the information about the guests saved in guest array in following format:
        //	On first line show the following message:
        //  "Guests information:"
        //	On the lines, display information about each guest:
        //                   {guestName} - {purchaseArticle}

        let output = '';
        if (criteria === 'article') {
            output += 'Articles information:';

            this.listOfArticles.forEach(a => {
                output += `\n${a.articleModel} - ${a.articleName} - ${a.quantity}`;
            });


        } else if (criteria === 'guest') {
            output += 'Guests information:';

            this.guests.forEach(g => {

                output += `\n${g.guestName} - ${g.purchaseArticle}`;
            });
        }

        return output;

    }

}

function test1() {
    const artGallery = new ArtGallery('Curtis Mayfield');
    console.log(artGallery.addArticle('picture', 'Mona Liza', 3));
    console.log(artGallery.addArticle('Item', 'Ancient vase', 2));
    console.log(artGallery.addArticle('PICTURE', 'Mona Liza', 1));

    console.log();
}

function test2() {
    const artGallery = new ArtGallery('Curtis Mayfield');
    console.log(artGallery.inviteGuest('John', 'Vip'));
    console.log(artGallery.inviteGuest('Peter', 'Middle'));
    console.log(artGallery.inviteGuest('John', 'Middle'));

    console.log();
}

function test3() {
    const artGallery = new ArtGallery('Curtis Mayfield');
    artGallery.addArticle('picture', 'Mona Liza', 3);
    artGallery.addArticle('Item', 'Ancient vase', 2);
    artGallery.addArticle('picture', 'Mona Liza', 1);
    artGallery.inviteGuest('John', 'Vip');
    artGallery.inviteGuest('Peter', 'Middle');
    console.log(artGallery.buyArticle('picture', 'Mona Liza', 'John'));
    console.log(artGallery.buyArticle('item', 'Ancient vase', 'Peter'));
    console.log(artGallery.buyArticle('item', 'Mona Liza', 'John'));

    console.log();

}

function test4() {
    const artGallery = new ArtGallery('Curtis Mayfield');
    artGallery.addArticle('picture', 'Mona Liza', 3);
    artGallery.addArticle('Item', 'Ancient vase', 2);
    artGallery.addArticle('picture', 'Mona Liza', 1);
    artGallery.inviteGuest('John', 'Vip');
    artGallery.inviteGuest('Peter', 'Middle');
    artGallery.buyArticle('picture', 'Mona Liza', 'John');
    artGallery.buyArticle('item', 'Ancient vase', 'Peter');
    console.log(artGallery.showGalleryInfo('article'));
    console.log(artGallery.showGalleryInfo('guest'));

}

test1();
// test2();
// test3();

test4();