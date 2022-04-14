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
var RegisterComponent = /** @class */ (function () {
    function RegisterComponent(formBuilder, authService, router) {
        this.formBuilder = formBuilder;
        this.authService = authService;
        this.router = router;
        this.passwordControl = new forms_1.FormControl(null, [forms_1.Validators.required, forms_1.Validators.minLength(5)]);
        this.registerFormGroup = this.formBuilder.group({
            'username': new forms_1.FormControl(null, [forms_1.Validators.required, forms_1.Validators.minLength(5)]),
            'email': new forms_1.FormControl(null, [forms_1.Validators.required, utils_1.emailValidator]),
            'passwords': new forms_1.FormGroup({
                'password': this.passwordControl,
                "rePassword": new forms_1.FormControl('', [utils_1.passwordMatch(this.passwordControl)])
            }),
            'tel': new forms_1.FormControl(''),
            'telRegion': new forms_1.FormControl('')
        });
    }
    Object.defineProperty(RegisterComponent.prototype, "passwordGroup", {
        get: function () {
            return this.registerFormGroup.controls['passwords'];
        },
        enumerable: true,
        configurable: true
    });
    RegisterComponent.prototype.ngOnInit = function () {
    };
    RegisterComponent.prototype.handleRegister = function () {
        var _this = this;
        console.log(this.registerFormGroup.value);
        var _a = this.registerFormGroup.value, username = _a.username, email = _a.email, passwords = _a.passwords, tel = _a.tel, telRegion = _a.telRegion;
        var body = {
            username: username,
            email: email,
            password: passwords.password
        };
        if (tel) {
            body['tel'] = telRegion + tel;
        }
        console.log(body);
        this.authService.register$(body).subscribe({
            next: function (data) { return _this.router.navigate(['/home']); },
            error: function (err) {
                console.error(err);
                _this.errorMessage = utils_1.SERVICE_UNAVAILABLE_ERROR;
            }
        });
    };
    RegisterComponent = __decorate([
        core_1.Component({
            selector: 'app-register',
            templateUrl: './register.component.html',
            styleUrls: ['./register.component.css']
        })
    ], RegisterComponent);
    return RegisterComponent;
}());
exports.RegisterComponent = RegisterComponent;

//# sourceMappingURL=register.component.js.map
