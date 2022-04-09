"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var rxjs_1 = require("rxjs");
var environment_1 = require("src/environments/environment");
var UserService = /** @class */ (function () {
    function UserService(httpClient) {
        this.httpClient = httpClient;
        this.isLoggedIn = this.isUserLogged;
    }
    UserService.prototype.logout = function () {
        // localStorage.setItem('isLogged', "true");
    };
    UserService.prototype.login$ = function (userData) {
        var _this = this;
        // localStorage.setItem('isLogged', "false");
        return this.httpClient.
            post(environment_1.environment.apiUrl + "/login", userData, { withCredentials: true, observe: 'response' })
            .pipe(rxjs_1.tap(function (response) { return console.log(response); }), rxjs_1.map(function (response) { return response.body; }), rxjs_1.tap(function (user) { return _this.currentUser = user; }));
    };
    Object.defineProperty(UserService.prototype, "isUserLogged", {
        get: function () {
            return !!this.currentUser;
        },
        enumerable: true,
        configurable: true
    });
    UserService.prototype.register$ = function (userData) {
        return this.httpClient.post(environment_1.environment.apiUrl + "/register", userData, { withCredentials: true });
    };
    UserService.prototype.getProfile$ = function () {
        var _this = this;
        return this.httpClient.
            get(environment_1.environment.apiUrl + "/users/profile", { withCredentials: true }).pipe(rxjs_1.tap(function (user) { return _this.currentUser = user; }));
    };
    UserService = __decorate([
        core_1.Injectable({
            providedIn: 'root'
        })
    ], UserService);
    return UserService;
}());
exports.UserService = UserService;

//# sourceMappingURL=user.service.js.map
