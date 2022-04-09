"use strict";
exports.__esModule = true;
var router_1 = require("@angular/router");
var auth_guard_1 = require("src/app/core/guards/auth.guard");
var art_new_page_component_1 = require("../art-new-page/art-new-page.component");
var artwork_page_component_1 = require("../artwork-page/artwork-page.component");
var routes = [
    {
        path: "artworks",
        pathMatch: "full",
        component: artwork_page_component_1.ArtworkPageComponent
    },
    {
        path: "artworks/new",
        canActivate: [auth_guard_1.AuthGuard],
        component: art_new_page_component_1.ArtNewPageComponent
    },
];
exports.ArtRoutingModule = router_1.RouterModule.forChild(routes);

//# sourceMappingURL=art-routing.module.js.map
