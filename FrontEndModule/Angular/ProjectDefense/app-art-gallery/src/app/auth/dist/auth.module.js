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
var register_component_1 = require("./register/register.component");
var login_component_1 = require("./login/login.component");
var profile_component_1 = require("./profile/profile.component");
var forms_1 = require("@angular/forms");
var email_validator_directive_1 = require("./email-validator.directive");
var routing_module_1 = require("./routing/routing.module");
var AuthModule = /** @class */ (function () {
    function AuthModule() {
    }
    AuthModule = __decorate([
        core_1.NgModule({
            declarations: [
                register_component_1.RegisterComponent,
                login_component_1.LoginComponent,
                profile_component_1.ProfileComponent,
                email_validator_directive_1.EmailValidatorDirective
            ],
            imports: [
                common_1.CommonModule,
                routing_module_1.RoutingModule,
                forms_1.ReactiveFormsModule,
                forms_1.FormsModule,
            ]
        })
    ], AuthModule);
    return AuthModule;
}());
exports.AuthModule = AuthModule;

//# sourceMappingURL=auth.module.js.map
