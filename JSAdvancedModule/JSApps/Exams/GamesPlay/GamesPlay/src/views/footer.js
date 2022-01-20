import { html } from "../lib.js";


const footerTemplate = () => html
`
<footer class="footer">
<p>&copy; All rights reserved</p>
</footer>
`;

export function getFooter() {

    return footerTemplate();
}