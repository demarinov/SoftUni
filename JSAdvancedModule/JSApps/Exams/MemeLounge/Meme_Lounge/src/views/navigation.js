import { html } from "../lib.js";

const navTemplate = () => html
`
<nav>
            <a href="/memes">All Memes</a>
             <!-- Logged users -->
            <div class="user">
                <a href="/create">Create Meme</a>
                <div class="profile">
                    <span>Welcome, {email}</span>
                    <a href="/profile">My Profile</a>
                    <a id="logoutBtn" href="/logout">Logout</a>
                </div>
            </div>
            <!-- Guest users -->
            <div class="guest">
                <div class="profile">
                    <a href="/login">Login</a>
                    <a href="/register">Register</a>
                </div>
                <a href="/home">Home Page</a>
            </div>
        </nav>
`;


export function getNavigation() {

    return navTemplate();
}
