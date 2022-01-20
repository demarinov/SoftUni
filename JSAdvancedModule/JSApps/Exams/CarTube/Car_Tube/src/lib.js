import { html, render } from '../node_modules/lit-html/lit-html.js';
import * as data from './api/data.js';
import { until } from '../node_modules/lit-html/directives/until.js';
import page from "../node_modules/page/page.mjs";
import * as utils from './utils.js';
import { getNavigation } from './views/navigation.js';
import { getFooter } from './views/footer.js';
import { getNotification } from './views/notification.js';

function triggerError(msg,path) {
    const notification = document.querySelector('.notification');

    notification.style.display = 'block';
    notification.querySelector('span').textContent = msg;
    setTimeout(function () {
        notification.style.display = 'none';
        if (path != null) {
            page.redirect(path);
        }
    }, 3000);
}

export {
    html, data, until, page,
    render, utils,
    getNavigation, getFooter, getNotification, triggerError
};