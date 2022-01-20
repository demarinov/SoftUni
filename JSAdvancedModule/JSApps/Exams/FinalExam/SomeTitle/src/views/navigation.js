import { html } from "../lib.js";

const navTemplate = () => html
`
<header>
            <nav>
                <img src="./images/headphones.png">
                <a href="/home">Home</a>
                <ul>
                    <!--All user-->
                    <li><a href="/catalog">Catalog</a></li>
                    <li><a href="/search">Search</a></li>
                    <!--Only guest-->
                    <li><a href="/login">Login</a></li>
                    <li><a href="/register">Register</a></li>
                    <!--Only user-->
                    <li><a href="/create">Create Album</a></li>
                    <li><a href="/logout">Logout</a></li>
                </ul>
            </nav>
        </header>
`;


export function getNavigation() {

    return navTemplate();
}
