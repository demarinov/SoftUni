"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var forms_1 = require("@angular/forms");
var utils_1 = require("../utils");
var EmailValidatorDirective = /** @class */ (function () {
    function EmailValidatorDirective() {
    }
    EmailValidatorDirective_1 = EmailValidatorDirective;
    EmailValidatorDirective.prototype.validate = function (control) {
        return utils_1.emailValidator(control);
    };
    var EmailValidatorDirective_1;
    EmailValidatorDirective = EmailValidatorDirective_1 = __decorate([
        core_1.Directive({
            selector: '[emailValidator]',
            providers: [{ provide: forms_1.NG_VALIDATORS, useExisting: EmailValidatorDirective_1, multi: true }]
        })
    ], EmailValidatorDirective);
    return EmailValidatorDirective;
}());
exports.EmailValidatorDirective = EmailValidatorDirective;

//# sourceMappingURL=email-validator.directive.js.map
