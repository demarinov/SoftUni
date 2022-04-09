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
var footer_component_1 = require("../core/footer/footer.component");
var header_component_1 = require("../core/header/header.component");
var router_1 = require("@angular/router");
var CoreModule = /** @class */ (function () {
    function CoreModule() {
    }
    CoreModule = __decorate([
        core_1.NgModule({
            declarations: [
                header_component_1.HeaderComponent,
                footer_component_1.FooterComponent,
            ],
            imports: [
                common_1.CommonModule,
                router_1.RouterModule,
            ],
            exports: [
                header_component_1.HeaderComponent,
                footer_component_1.FooterComponent,
            ]
        })
    ], CoreModule);
    return CoreModule;
}());
exports.CoreModule = CoreModule;

//# sourceMappingURL=core.module.js.map
