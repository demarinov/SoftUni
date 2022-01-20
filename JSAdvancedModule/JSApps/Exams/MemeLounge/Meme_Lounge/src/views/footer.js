import { html } from "../lib.js";


const footerTemplate = () => html
`
<footer class="footer">
<p>Created by SoftUni Delivery Team</p>
</footer>
`;

export function getFooter() {

    return footerTemplate();
}