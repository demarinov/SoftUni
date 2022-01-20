import { html, data, getNotification,getNavigation, 
    getFooter, triggerError} from '../lib.js';

const createTemplate = (onSubmit) => {
    return html
        ` 
        ${getNavigation()}
        <section id="create-page" class="auth">
        <form id="create" @submit=${onSubmit}>
            <div class="container">

                <h1>Create Game</h1>
                <label for="leg-title">Legendary title:</label>
                <input type="text" id="title" name="title" placeholder="Enter game title...">

                <label for="category">Category:</label>
                <input type="text" id="category" name="category" placeholder="Enter game category...">

                <label for="levels">MaxLevel:</label>
                <input type="number" id="maxLevel" name="maxLevel" min="1" placeholder="1">

                <label for="game-img">Image:</label>
                <input type="text" id="imageUrl" name="imageUrl" placeholder="Upload a photo...">

                <label for="summary">Summary:</label>
                <textarea name="summary" id="summary"></textarea>
                <input class="btn submit" type="submit" value="Create Game">
            </div>
        </form>
    </section>
`};

export async function createSet(ctx) {
    console.log('createSet');

    ctx.render(createTemplate(onSubmit));
    ctx.updateNavigation('create');

    async function onSubmit(ev) {
        ev.preventDefault();

        console.log('Submit create form');

        const formData = new FormData(document.querySelector('#create'));

        const title = formData.get('title');
        const maxLevel = formData.get('maxLevel');
        const summary = formData.get('summary');
        
        const imageUrl = formData.get('imageUrl');
        const category = formData.get('category');

        // apply validation rules

        if (title === '' || maxLevel === '' || summary === ''
            || imageUrl === ''
            || category === '') {
            console.error('Invalid or empty fields!');
            alert('Invalid or empty fields!');
            // triggerError('Invalid or empty fields!',null);
            return;
        }

        const game = Object.fromEntries([...formData.entries()]);

        let error = false;
        let msg = '';
        await data.createGame(game).catch(e => {error = true; msg = e.message;});

        if (error) {
            // triggerError(msg, null);
            alert(msg);
            return;
        }

        ev.target.reset();
        ctx.page.redirect('/home');
        
    }
}

