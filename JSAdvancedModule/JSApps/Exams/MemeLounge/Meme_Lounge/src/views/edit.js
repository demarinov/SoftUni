import { html, data, triggerError, getNotification,getNavigation, 
    getFooter} from '../lib.js';


const editTemplate = (onSubmit) => html`
${getNotification()}
${getNavigation()}
<section id="edit-meme">
            <form id="edit-form" @submit=${onSubmit}>
                <h1>Edit Meme</h1>
                <div class="container">
                    <label for="title">Title</label>
                    <input id="title" type="text" placeholder="Enter Title" name="title">
                    <label for="description">Description</label>
                    <textarea id="description" placeholder="Enter Description" name="description">
                            Programming is often touted as a smart and lucrative career path.
                            It's a job that (sometimes) offers flexibility and great benefits.
                            But it's far from sunshine and Nyan Cat rainbows. The hours are long.
                            The mistakes are frustrating. And your eyesight is almost guaranteed to suffer.
                            These memes cover most of the frustration (and funny moments) of programming.
                            At least we can laugh through the pain. 
                        </textarea>
                    <label for="imageUrl">Image Url</label>
                    <input id="imageUrl" type="text" placeholder="Enter Meme ImageUrl" name="imageUrl">
                    <input type="submit" class="registerbtn button" value="Edit Meme">
                </div>
            </form>
        </section>
        ${getFooter()}
    `;

export async function editSet(ctx) {
    console.log('editSet');

    ctx.render(editTemplate(onSubmit));
    ctx.updateNavigation();

    const id = ctx.params.id;
    prefillForm();

    async function prefillForm() {

        const meme = await data.getMemeById(id);

        const title = document.querySelector('#title');
        const imageUrl = document.querySelector('#imageUrl');
        const description = document.querySelector('#description');

        title.value = meme.title;
        imageUrl.value = meme.imageUrl;
        description.value = meme.description;

    }

    async function onSubmit(ev) {
        ev.preventDefault();

        console.log('Submit edit form');

        const formData = new FormData(document.querySelector('#edit-form'));

        const title = formData.get('title');
        const imageUrl = formData.get('imageUrl');
        const description = formData.get('description');

        // apply validation rules


        if (title === ''
            || imageUrl === ''
            || description === '') {
            console.error('Invalid or empty fields!');
            // alert('Invalid or empty fields!');
            triggerError('Invalid or empty fields!', null);
            return;
        }

        const meme = Object.fromEntries(formData);

        let error = false;
        let msg = '';
        await data.editMeme(id, meme).catch(e => {error = true; msg=e.message;});

        console.log(error);
        if (error) {
            triggerError(msg, null);
            return;
        }

        ev.target.reset();
        ctx.page.redirect('/details/'+id);
    }
}