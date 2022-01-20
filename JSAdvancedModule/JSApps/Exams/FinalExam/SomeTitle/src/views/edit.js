import { html, data, getNavigation, getFooter} from '../lib.js';


const editTemplate = (onSubmit) => html`
${getNavigation()}
<section class="editPage">
<form @submit=${onSubmit}> 
    <fieldset>
        <legend>Edit Album</legend>

        <div class="container">
            <label for="name" class="vhide">Album name</label>
            <input id="name" name="name" class="name" type="text" value="In These Silent Days">

            <label for="imgUrl" class="vhide">Image Url</label>
            <input id="imgUrl" name="imgUrl" class="imgUrl" type="text" value="./img/BrandiCarlile.png">

            <label for="price" class="vhide">Price</label>
            <input id="price" name="price" class="price" type="text" value="12.80">

            <label for="releaseDate" class="vhide">Release date</label>
            <input id="releaseDate" name="releaseDate" class="releaseDate" type="text" value="October 1, 2021">

            <label for="artist" class="vhide">Artist</label>
            <input id="artist" name="artist" class="artist" type="text" value="Brandi Carlile">

            <label for="genre" class="vhide">Genre</label>
            <input id="genre" name="genre" class="genre" type="text" value="Low Country Sound Music">

            <label for="description" class="vhide">Description</label>
            <textarea name="description" class="description" rows="10"
                cols="10">Upon release, In These Silent Days received positive reviews from critics. At Metacritic, which assigns a normalized rating out of 100 to reviews from mainstream critics, the album has an average score of 87 out of 100, which indicates 'universal acclaim'.</textarea>

            <button class="edit-album" type="submit">Edit Album</button>
        </div>
    </fieldset>
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

        const album = await data.getAlbumById(id);

        const name = document.querySelector('input[name="name"]');
        const price = document.querySelector('input[name="price"]');
        const releaseDate = document.querySelector('input[name="releaseDate"]');
        const artist = document.querySelector('input[name="artist"]');
        const genre = document.querySelector('input[name="genre"]');
        const description = document.querySelector('textarea[name="description"]');
        const imageUrl = document.querySelector('input[name="imgUrl"]');

        name.value = album.name;
        price.value = album.price;
        releaseDate.value = album.releaseDate;
        artist.value = album.artist;
        imageUrl.value = album.imgUrl;
        description.value = album.description;
        genre.value = album.genre;


    }

    async function onSubmit(ev) {
        ev.preventDefault();

        console.log('Submit edit form');

        const formData = new FormData(document.querySelector('.editPage form'));

        const name = formData.get('name');
        const price = formData.get('price');
        const artist = formData.get('artist');
        const releaseDate = formData.get('releaseDate');
        const imgUrl = formData.get('imgUrl');
        const description = formData.get('description');
        const genre = formData.get('genre');

        // apply validation rules


        if (name === '' || price === ''
            || imgUrl === '' || releaseDate === ''
            || artist === '' || description === '' || genre === '') {
            console.error('Invalid or empty fields!');
            alert('Invalid or empty fields!');
            // triggerError('Invalid or empty fields!', null);
            return;
        }
          
        const album = Object.fromEntries(formData);

        let error = false;
        let msg = '';
        await data.editAlbum(id, album).catch(e => {error = true; msg=e.message;});

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