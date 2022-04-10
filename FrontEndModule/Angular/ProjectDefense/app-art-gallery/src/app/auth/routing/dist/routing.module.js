"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var router_1 = require("@angular/router");
var login_component_1 = require("../login/login.component");
var register_component_1 = require("../register/register.component");
var profile_component_1 = require("../profile/profile.component");
var auth_guard_1 = require("src/app/core/guards/auth.guard");
var routes = [
    {
        path: "register",
        // pathMatch: "full",
        component: register_component_1.RegisterComponent
    },
    {
        path: "login",
        // pathMatch: "full",
        component: login_component_1.LoginComponent
    },
    {
        path: "profile",
        canActivate: [auth_guard_1.AuthGuard],
        component: profile_component_1.ProfileComponent
    }
];
var RoutingModule = /** @class */ (function () {
    function RoutingModule() {
    }
    RoutingModule = __decorate([
        core_1.NgModule({
            imports: [
                router_1.RouterModule.forChild(routes)
            ],
            exports: [router_1.RouterModule]
        })
    ], RoutingModule);
    return RoutingModule;
}());
exports.RoutingModule = RoutingModule;

//# sourceMappingURL=routing.module.js.map
