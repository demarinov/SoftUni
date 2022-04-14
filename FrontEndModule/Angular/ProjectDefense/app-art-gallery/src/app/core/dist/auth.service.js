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
var _store_1 = require("../+store");
var AuthService = /** @class */ (function () {
    function AuthService(httpClient, store) {
        this.httpClient = httpClient;
        this.store = store;
        this.currentUser$ = this.store.select(function (globalState) { return globalState.currentUser; });
        this.isLoggedIn$ = this.currentUser$.pipe(rxjs_1.map(function (user) { return !!user; }));
    }
    AuthService.prototype.handleLogin = function (newUser) {
        this.store.dispatch(_store_1.login({ user: newUser }));
    };
    AuthService.prototype.handleLogout = function (newUser) {
        this.store.dispatch(_store_1.logout());
    };
    AuthService.prototype.login$ = function (userData) {
        return this.httpClient.
            post(environment_1.environment.apiUrl + "/login", userData, { withCredentials: true, observe: 'response' })
            .pipe(rxjs_1.tap(function (response) { return console.log(response); }), rxjs_1.map(function (response) { return response.body; }));
    };
    AuthService.prototype.authenticate$ = function () {
        var _this = this;
        return this.httpClient.
            get(environment_1.environment.apiUrl + "/users/profile", { withCredentials: true })
            .pipe(rxjs_1.tap(function (currentProfile) { return _this.handleLogin(currentProfile); }), rxjs_1.catchError(function (err) {
            return rxjs_1.EMPTY;
        }));
    };
    AuthService.prototype.logout$ = function () {
        console.log('logout called');
        return this.httpClient.
            post(environment_1.environment.apiUrl + "/logout", {}, { withCredentials: true });
    };
    AuthService.prototype.register$ = function (userData) {
        return this.httpClient.post(environment_1.environment.apiUrl + "/register", userData, { withCredentials: true });
    };
    AuthService = __decorate([
        core_1.Injectable({
            providedIn: 'root'
        })
    ], AuthService);
    return AuthService;
}());
exports.AuthService = AuthService;

//# sourceMappingURL=auth.service.js.map
