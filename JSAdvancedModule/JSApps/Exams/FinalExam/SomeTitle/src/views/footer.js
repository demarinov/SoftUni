import { html } from "../lib.js";


const footerTemplate = () => html
`
<footer>
            <div>
                &copy;SoftUni Team 2021. All rights reserved.
            </div>
</footer>
`;

export function getFooter() {

    return footerTemplate();
}