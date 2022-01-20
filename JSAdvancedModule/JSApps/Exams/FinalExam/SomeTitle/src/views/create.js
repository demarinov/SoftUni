import { html, data, getNavigation, 
    getFooter} from '../lib.js';

const createTemplate = (onSubmit) => {
    return html
        ` 
        ${getNavigation()}
        <section class="createPage">
            <form @submit=${onSubmit}>
                <fieldset>
                    <legend>Add Album</legend>

                    <div class="container">
                        <label for="name" class="vhide">Album name</label>
                        <input id="name" name="name" class="name" type="text" placeholder="Album name">

                        <label for="imgUrl" class="vhide">Image Url</label>
                        <input id="imgUrl" name="imgUrl" class="imgUrl" type="text" placeholder="Image Url">

                        <label for="price" class="vhide">Price</label>
                        <input id="price" name="price" class="price" type="text" placeholder="Price">

                        <label for="releaseDate" class="vhide">Release date</label>
                        <input id="releaseDate" name="releaseDate" class="releaseDate" type="text" placeholder="Release date">

                        <label for="artist" class="vhide">Artist</label>
                        <input id="artist" name="artist" class="artist" type="text" placeholder="Artist">

                        <label for="genre" class="vhide">Genre</label>
                        <input id="genre" name="genre" class="genre" type="text" placeholder="Genre">

                        <label for="description" class="vhide">Description</label>
                        <textarea name="description" class="description" placeholder="Description"></textarea>

                        <button class="add-album" type="submit">Add New Album</button>
                    </div>
                </fieldset>
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

        const formData = new FormData(document.querySelector('.createPage form'));

        const name = formData.get('name');
        const price = formData.get('price');
        const releaseDate = formData.get('releaseDate');
        const artist = formData.get('artist');
        const genre = formData.get('genre');
        
        const imageUrl = formData.get('imgUrl');
        const description = formData.get('description');

        // apply validation rules

        if (name === '' || price === '' || releaseDate === ''
            || imageUrl === ''
            || artist === '' || genre === '' 
            || description === '') {
            console.error('Invalid or empty fields!');
            alert('Invalid or empty fields!');
            // triggerError('Invalid or empty fields!',null);
            return;
        }

        const album = Object.fromEntries([...formData.entries()]);

        let error = false;
        let msg = '';
        await data.createAlbum(album).catch(e => {error = true; msg = e.message;});

        if (error) {
            alert(msg);
            return;
        }

        ev.target.reset();
        ctx.page.redirect('/catalog');
        
    }
}

