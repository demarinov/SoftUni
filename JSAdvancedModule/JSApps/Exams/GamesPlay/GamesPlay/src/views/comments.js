import { data, html, until, page} from "../lib.js";


const commentsTemplate = (gamesPromise) => html `

<div class="details-comments">
<h2>Comments:</h2>
<ul>
    ${until(gamesPromise, html `Comments loading &hellip;`)}
</ul>
</div>
`;


const gameCommentTemplate = (comment) => html `

    <!-- list all comments for current game (If any) -->
    <li class="comment">
        <p>Content: ${comment}</p>
    </li>
`;

const createCommentFormTemplate = (onSubmit) => html `
<article class="create-comment">
<label>Add new comment:</label>
<form class="form" @submit=${onSubmit}>
    <textarea name="comment" placeholder="Comment......"></textarea>
    <input class="btn submit" type="submit" value="Add Comment">
</form>
</article>
`;

export function createCommentForm(id) {

    return createCommentFormTemplate(onSubmit);

    async function onSubmit(ev) {
        ev.preventDefault();

        const formData = new FormData(document.querySelector('form'));

        const comment = formData.get('comment');

        if (comment === '') {
            console.error('Invalid or empty comment!');
            alert('Invalid or empty comment!');
            // triggerError('Invalid or empty fields!', null);
            return;
        }
          
        const gameComment = {
            gameId: id,
            comment: comment
        };

        let error = false;
        let msg = '';
        await data.createGameComment(gameComment).catch(e => {error = true; msg=e.message;});

        if (error) {
            // triggerError(msg, null);
            alert(msg);
            return;
        }

        ev.target.reset();
        page.redirect('/details/'+id);

    }
}

export function getComments(gameId) {

    return commentsTemplate(getGameComments(gameId));

}

async function getGameComments(gameId) {

    const comments = await data.getGameComments(gameId);

    if (comments == null || comments.length === 0) {

        return html `<p class="no-comment">No comments.</p>`;
    }

    return comments.map(c => c.comment).map(gameCommentTemplate);

}

