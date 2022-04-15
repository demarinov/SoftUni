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
var utils_1 = require("src/app/utils");
var LoginComponent = /** @class */ (function () {
    function LoginComponent(formBuilder, authService, router) {
        this.formBuilder = formBuilder;
        this.authService = authService;
        this.router = router;
        this.loginFormGroup = this.formBuilder.group({
            'email': new forms_1.FormControl('', [forms_1.Validators.required, utils_1.emailValidator]),
            'password': new forms_1.FormControl('', [forms_1.Validators.required, forms_1.Validators.minLength(5)])
        });
    }
    LoginComponent.prototype.ngOnInit = function () {
        this.loginHandler();
    };
    LoginComponent.prototype.loginHandler = function () {
        // this.userService.login();
        // this.router.navigate(['/home']);
    };
    LoginComponent.prototype.handleLogin = function () {
        var _this = this;
        console.log('form is submitted');
        this.errorMessage = '';
        this.authService.login$(this.loginFormGroup.value).subscribe({
            next: function (user) {
                console.log(user);
                _this.router.navigate(['/home']);
            },
            complete: function () {
                console.log('complete');
            },
            error: function (error) {
                console.error(error);
                if (error.status >= 500) {
                    _this.errorMessage = utils_1.SERVICE_UNAVAILABLE_ERROR;
                }
                else {
                    _this.errorMessage = error.error.message;
                }
            }
        });
    };
    LoginComponent = __decorate([
        core_1.Component({
            selector: 'app-login',
            templateUrl: './login.component.html',
            styleUrls: ['./login.component.css']
        })
    ], LoginComponent);
    return LoginComponent;
}());
exports.LoginComponent = LoginComponent;

//# sourceMappingURL=login.component.js.map
