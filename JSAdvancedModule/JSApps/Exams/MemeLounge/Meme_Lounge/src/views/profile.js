import { data, until,getNavigation, getFooter, html} from "../lib.js";


const profileTemplate = (articleTemplate, memesPromise) => html `
${getNavigation()}
<section id="user-profile-page" class="user-profile">
${articleTemplate}
<h1 id="user-listings-title">User Memes</h1>
<div class="user-meme-listings">
    <!-- Display : All created memes by this user (If any) --> 
    ${until(memesPromise, html `Loading memes &hellip`)} 
    
</div>
</section>
${getFooter()}
`;

const articleTemplate = (user, email, memeCount, gender) => {
    return html `
    <article class="user-info">
    <img id="user-avatar-url" alt="user-profile" src="/images/${gender === 'male' ? 'male.png' : 'female.png'}">
    <div class="user-content">
        <p>Username: ${user}</p>
        <p>Email: ${email}</p>
        <p>My memes count: ${memeCount}</p>
    </div>
</article>
    `;
};

const memeTemplate = (meme) => {
    return html `
    <div class="user-meme">
    <p class="user-meme-title">${meme.title}</p>
    <img class="userProfileImage" alt="meme-img" src="${meme.imageUrl}">
    <a class="button" href="/details/${meme._id}">Details</a>
    </div>
    `;
}

export async function profileSet(ctx) {
    console.log('profileSet');

    const user = sessionStorage.getItem('username');
    const email = sessionStorage.getItem('email');
    const userId = sessionStorage.getItem('userId');
    // due to e2e tests meme count should be done differently
    // const memeCount = await data.getUserMemeCount(userId);
    let memeCount = (await getUserMemes(userId)).length;
    const gender = sessionStorage.getItem('gender');

    if (memeCount === '' || memeCount == null) {
        memeCount = 0;
    }
    console.log(memeCount);
    ctx.render(profileTemplate(articleTemplate(user,email, 
        memeCount,gender), getUserMemes(userId)));
    ctx.updateNavigation('profile');

}

async function getUserMemes(userId) {

    let userMemes = await data.getMemesByUserId(userId);
    if (userMemes.length === 0) {

        return html `<p class="no-memes">No memes in database.</p>`;
    }
    
    return userMemes.map(memeTemplate);
}