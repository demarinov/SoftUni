import {html} from '../lib.js';

const errorTemplate = () => html  `<section id="notifications">
<div id="errorBox" class="notification">
    <span>MESSAGE</span>
</div>
</section>
`;

export function getNotification() {

    return errorTemplate();
}