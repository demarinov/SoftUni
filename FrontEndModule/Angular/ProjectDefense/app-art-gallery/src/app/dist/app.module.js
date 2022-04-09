"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var platform_browser_1 = require("@angular/platform-browser");
var app_routing_module_1 = require("./app-routing.module");
var app_component_1 = require("./app.component");
var shared_module_1 = require("./shared/shared.module");
var pages_module_1 = require("./feature/pages/pages.module");
var auth_module_1 = require("./auth/auth.module");
var http_1 = require("@angular/common/http");
var header_component_1 = require("./core/header/header.component");
var footer_component_1 = require("./core/footer/footer.component");
var core_module_1 = require("./core/core.module");
var art_module_1 = require("./feature/art/art.module");
var AppModule = /** @class */ (function () {
    function AppModule() {
    }
    AppModule = __decorate([
        core_1.NgModule({
            declarations: [
                app_component_1.AppComponent,
            ],
            imports: [
                platform_browser_1.BrowserModule,
                shared_module_1.SharedModule,
                pages_module_1.PagesModule,
                auth_module_1.AuthModule,
                art_module_1.ArtModule,
                app_routing_module_1.AppRoutingModule,
                http_1.HttpClientModule,
                core_module_1.CoreModule,
            ],
            providers: [],
            bootstrap: [app_component_1.AppComponent, header_component_1.HeaderComponent, footer_component_1.FooterComponent]
        })
    ], AppModule);
    return AppModule;
}());
exports.AppModule = AppModule;

//# sourceMappingURL=app.module.js.map
