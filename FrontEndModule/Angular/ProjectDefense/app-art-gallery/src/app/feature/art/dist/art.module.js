"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var common_1 = require("@angular/common");
var art_list_item_component_1 = require("./art-list-item/art-list-item.component");
var art_list_component_1 = require("./art-list/art-list.component");
var forms_1 = require("@angular/forms");
var shared_module_1 = require("src/app/shared/shared.module");
var artwork_page_component_1 = require("./artwork-page/artwork-page.component");
var art_routing_module_1 = require("./art-routing/art-routing.module");
var art_new_page_component_1 = require("./art-new-page/art-new-page.component");
var ArtModule = /** @class */ (function () {
    function ArtModule() {
    }
    ArtModule = __decorate([
        core_1.NgModule({
            declarations: [
                art_list_item_component_1.ArtListItemComponent,
                art_list_component_1.ArtListComponent,
                artwork_page_component_1.ArtworkPageComponent,
                art_new_page_component_1.ArtNewPageComponent
            ],
            imports: [
                common_1.CommonModule,
                art_routing_module_1.ArtRoutingModule,
                shared_module_1.SharedModule,
                forms_1.FormsModule,
            ],
            exports: [
                art_list_component_1.ArtListComponent,
                art_list_item_component_1.ArtListItemComponent,
                artwork_page_component_1.ArtworkPageComponent,
            ]
        })
    ], ArtModule);
    return ArtModule;
}());
exports.ArtModule = ArtModule;

//# sourceMappingURL=art.module.js.map
