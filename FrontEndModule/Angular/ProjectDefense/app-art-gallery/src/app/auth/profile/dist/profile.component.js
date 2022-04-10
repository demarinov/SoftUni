"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var ProfileComponent = /** @class */ (function () {
    function ProfileComponent(userService, router) {
        this.userService = userService;
        this.router = router;
        this.inEditMode = false;
    }
    ProfileComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.userService.getProfile$().subscribe({
            next: function (user) {
                _this.currentUser = user;
                console.log(user);
            },
            error: function (error) {
                console.log(error);
                _this.router.navigate(['/home']);
            }
        });
    };
    ProfileComponent.prototype.enterEditMode = function () {
        var _this = this;
        this.inEditMode = true;
        setTimeout(function () {
            _this.editProfileForm.form.patchValue({
                email: _this.currentUser.email,
                username: _this.currentUser.username,
                'select-tel': _this.currentUser.tel && _this.currentUser.tel.length > 6
                    ? _this.currentUser.tel.substring(0, 4) : '',
                tel: _this.currentUser.tel && _this.currentUser.tel.length > 6
                    ? _this.currentUser.tel.substring(4) : _this.currentUser.tel
            });
        });
    };
    ProfileComponent.prototype.updateProfile = function () {
        var _this = this;
        console.log(this.editProfileForm.value);
        this.inEditMode = false;
        // make put request
        this.userService.editProfile$(this.editProfileForm.value).subscribe({
            next: function (user) {
                _this.currentUser = user;
                console.log(user);
            },
            error: function (error) {
                console.log(error);
                _this.router.navigate(['/home']);
            }
        });
    };
    __decorate([
        core_1.ViewChild('editProfileForm')
    ], ProfileComponent.prototype, "editProfileForm");
    ProfileComponent = __decorate([
        core_1.Component({
            selector: 'app-profile',
            templateUrl: './profile.component.html',
            styleUrls: ['./profile.component.css']
        })
    ], ProfileComponent);
    return ProfileComponent;
}());
exports.ProfileComponent = ProfileComponent;

//# sourceMappingURL=profile.component.js.map
