import { html } from "../lib.js";

const navTemplate = () => html
`
<nav>
                <a class="active" href="/home">Home</a>
                <a href="/listings">All Listings</a>
                <a href="/year">By Year</a>
                <!-- Guest users -->
                <div id="guest">
                    <a href="/login">Login</a>
                    <a href="/register">Register</a>
                </div>
                <!-- Logged users -->
                <div id="profile">
                    <a>Welcome username</a>
                    <a href="/profile">My Listings</a>
                    <a href="/create">Create Listing</a>
                    <a id="logoutBtn" href="/logout">Logout</a>
                </div>
            </nav>
`;


export function getNavigation() {

    return navTemplate();
}
