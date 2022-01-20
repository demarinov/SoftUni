
class Story {

    constructor(title, creator) {

        this.title = title;
        this.creator = creator;
        this._comments = [];
        this._likes = [];
    }

    get likes() {
        if (this._likes.length === 0) {
            return `${this.title} has 0 likes`;
        }

        if (this._likes.length === 1) {
            return `${this._likes[0]} likes this story!`;
        }

        return `${this._likes[0]} and ${this._likes.length - 1} others like this story!`;
    }

    like(username) {

        if (this._likes.some(e => e === username)) {

            throw new Error("You can't like the same story twice!");
        }

        if (this.creator === username) {
            throw new Error("You can't like your own story!");
        }

        this._likes.push(username);

        return `${username} liked ${this.title}!`;
    }

    dislike(username) {

        if (!this._likes.some(e => e === username)) {
            throw new Error("You can't dislike this story!");
        }

        const index = this._likes.indexOf(username);
        this._likes.splice(index, 1);

        return `${username} disliked ${this.title}`;
    }

    comment(username, content, id) {

        let reply = {
            // id
            // username
            // content
        };

        let comment = {
            // id
            // username
            // content
            // replies []
        }

        if (id === undefined || !this._comments.some(c => c.id === id)) {
            comment = {
                'id': this._comments.length+1,
                'username': username,
                'content': content,
                'replies': []
            }

            this._comments.push(comment);

            return `${username} commented on ${this.title}`;
        }

        comment = this._comments.find(c => c.id === id);
        const countReplies = comment.replies.length;
        reply = {
            'id': (comment.id + '.' + (countReplies + 1)),
            'username': username,
            'content': content
        };

        comment.replies.push(reply);

        return "You replied successfully";
    };

    toString(sortingType) {

        switch (sortingType) {
            case 'asc':
                // sort comments
                this._comments.sort((c1, c2) => c1.id - c2.id);

                // sort replies
                this._comments.forEach(c => c.replies.sort((r1, r2) => r1.id - r2.id));

                break;
            case 'desc':
                // sort comments
                this._comments.sort((c1, c2) => c2.id - c1.id);

                // sort replies
                this._comments.forEach(c => c.replies.sort((r1, r2) => r2.id - r1.id));
                break;
            case 'username':
                // sort comments
                this._comments.sort((c1, c2) => c1.username.localeCompare(c2.username));

                // sort replies
                this._comments.
                    forEach(c => c.replies.sort((r1, r2) => r1.username.localeCompare(r2.username)));
                break;
            default:
                break;
        }

        let output = `Title: ${this.title}\n`;
        output += `Creator: ${this.creator}\n`;
        output += `Likes: ${this._likes.length}\n`;
        output += 'Comments:';

        this._comments.forEach(c => {

            output += `\n-- ${c.id}. ${c.username}: ${c.content}`;

            c.replies.forEach(r => {
                output += `\n--- ${r.id}. ${r.username}: ${r.content}`;
            });
        });


        return output;
    }


}

function test() {
    let art = new Story("My Story", "Anny");
    art.like("John");
    console.log(art.likes);
    art.dislike("John");
    console.log(art.likes);
    art.comment("Sammy", "Some Content");
    console.log(art.comment("Ammy", "New Content"));
    art.comment("Zane", "Reply", 1);
    art.comment("Jessy", "Nice :)");
    console.log(art.comment("SAmmy", "Reply@", 1));
    console.log()
    console.log(art.toString('username'));
    console.log()
    art.like("Zane");
    console.log(art.toString('desc'));

}

test();