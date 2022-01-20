import { html } from "../lib.js";

const navTemplate = () => html
`
<header>
<!-- Navigation -->
<h1><a class="home" href="/home">GamesPlay</a></h1>
<nav>
    <a href="/games">All games</a>
    <!-- Logged-in users -->
    <div id="user">
        <a href="/create">Create Game</a>
        <a href="/logout">Logout</a>
    </div>
    <!-- Guest users -->
    <div id="guest">
        <a href="/login">Login</a>
        <a href="/register">Register</a>
    </div>
</nav>
</header>
`;


export function getNavigation() {

    return navTemplate();
}
