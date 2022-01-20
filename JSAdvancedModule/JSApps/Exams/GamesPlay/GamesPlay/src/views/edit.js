import { html, data, getNavigation} from '../lib.js';


const editTemplate = (onSubmit) => html`
${getNavigation()}
<section id="edit-page" class="auth">
            <form id="edit" @submit=${onSubmit}>
                <div class="container">

                    <h1>Edit Game</h1>
                    <label for="leg-title">Legendary title:</label>
                    <input type="text" id="title" name="title" value="">

                    <label for="category">Category:</label>
                    <input type="text" id="category" name="category" value="">

                    <label for="levels">MaxLevel:</label>
                    <input type="number" id="maxLevel" name="maxLevel" min="1" value="">

                    <label for="game-img">Image:</label>
                    <input type="text" id="imageUrl" name="imageUrl" value="">

                    <label for="summary">Summary:</label>
                    <textarea name="summary" id="summary"></textarea>
                    <input class="btn submit" type="submit" value="Edit Game">

                </div>
            </form>
        </section>
    `;

export async function editSet(ctx) {
    console.log('editSet');

    ctx.render(editTemplate(onSubmit));
    ctx.updateNavigation();

    const id = ctx.params.id;
    prefillForm();

    async function prefillForm() {

        const game = await data.getGameById(id);

        const title = document.querySelector('input[name="title"]');
        const category = document.querySelector('input[name="category"]');
        const maxLevel = document.querySelector('input[name="maxLevel"]');
        const summary = document.querySelector('textarea[name="summary"]');
        const imageUrl = document.querySelector('input[name="imageUrl"]');

        title.value = game.title;
        category.value = game.category;
        maxLevel.value = game.maxLevel;
        summary.value = game.summary;
        imageUrl.value = game.imageUrl;

    }

    async function onSubmit(ev) {
        ev.preventDefault();

        console.log('Submit edit form');

        const formData = new FormData(document.querySelector('#edit'));

        const title = formData.get('title');
        const category = formData.get('category');
        const maxLevel = formData.get('maxLevel');
        const summary = formData.get('summary');
        const imageUrl = formData.get('imageUrl');

        // apply validation rules


        if (title === '' || category === ''
            || imageUrl === '' || maxLevel === ''
            || summary === '') {
            console.error('Invalid or empty fields!');
            alert('Invalid or empty fields!');
            // triggerError('Invalid or empty fields!', null);
            return;
        }
          
        const game = Object.fromEntries(formData);

        let error = false;
        let msg = '';
        await data.editGame(id, game).catch(e => {error = true; msg=e.message;});

        console.log(error);
        if (error) {
            // triggerError(msg, null);
            alert(msg);
            return;
        }

        ev.target.reset();
        ctx.page.redirect('/details/'+id);
    }
}