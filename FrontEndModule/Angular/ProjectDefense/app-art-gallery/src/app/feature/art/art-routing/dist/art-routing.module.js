"use strict";
exports.__esModule = true;
var router_1 = require("@angular/router");
var auth_guard_1 = require("src/app/core/guards/auth.guard");
var art_detail_page_component_1 = require("../art-detail-page/art-detail-page.component");
var art_new_page_component_1 = require("../art-new-page/art-new-page.component");
var artist_catalog_page_component_1 = require("../artist-catalog-page/artist-catalog-page.component");
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
    {
        path: "artworks/:artId",
        component: art_detail_page_component_1.ArtDetailPageComponent
    },
    {
        path: "user/artworks",
        canActivate: [auth_guard_1.AuthGuard],
        component: artist_catalog_page_component_1.ArtistCatalogPageComponent
    },
];
exports.ArtRoutingModule = router_1.RouterModule.forChild(routes);

//# sourceMappingURL=art-routing.module.js.map
