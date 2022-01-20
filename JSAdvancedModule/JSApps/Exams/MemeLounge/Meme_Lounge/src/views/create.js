import { html, data, getNotification,getNavigation, 
    getFooter, triggerError} from '../lib.js';

const createTemplate = (onSubmit) => {
    return html
        ` 
        ${getNotification()}
        ${getNavigation()}
        <section id="create-meme">
            <form id="create-form" @submit=${onSubmit}>
                <div class="container">
                    <h1>Create Meme</h1>
                    <label for="title">Title</label>
                    <input id="title" type="text" placeholder="Enter Title" name="title">
                    <label for="description">Description</label>
                    <textarea id="description" placeholder="Enter Description" name="description"></textarea>
                    <label for="imageUrl">Meme Image</label>
                    <input id="imageUrl" type="text" placeholder="Enter meme ImageUrl" name="imageUrl">
                    <input type="submit" class="registerbtn button" value="Create Meme">
                </div>
            </form>
        </section>
        ${getFooter()}
`};

export async function createSet(ctx) {
    console.log('createSet');

    ctx.render(createTemplate(onSubmit));
    ctx.updateNavigation('create');

    async function onSubmit(ev) {
        ev.preventDefault();

        console.log('Submit create form');

        const formData = new FormData(document.querySelector('#create-form'));

        const title = formData.get('title');
        const imageUrl = formData.get('imageUrl');
        const description = formData.get('description');

        // apply validation rules

        if (title === ''
            || imageUrl === ''
            || description === '') {
            console.error('Invalid or empty fields!');
            // alert('Invalid or empty fields!');
            triggerError('Invalid or empty fields!',null);
            return;
        }

        const meme = Object.fromEntries([...formData.entries()]);

        let error = false;
        let msg = '';
        await data.createMeme(meme).catch(e => {error = true; msg = e.message;});

        if (error) {
            triggerError(msg, null);
            return;
        }

        ev.target.reset();
        ctx.page.redirect('/memes');
        
    }
}

