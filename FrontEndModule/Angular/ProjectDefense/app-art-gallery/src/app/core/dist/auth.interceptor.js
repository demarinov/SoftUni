"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var http_1 = require("@angular/common/http");
var rxjs_1 = require("rxjs");
var AuthInterceptor = /** @class */ (function () {
    function AuthInterceptor(authService) {
        this.authService = authService;
    }
    AuthInterceptor.prototype.intercept = function (request, next) {
        var _this = this;
        console.log('auth interceptor');
        return next.handle(request).pipe(rxjs_1.tap(function (response) {
            if (response instanceof http_1.HttpResponse) {
                if (response.url.endsWith("login") || response.url.endsWith("register")) {
                    var newlyLoggedUser = response.body;
                    _this.authService.handleLogin(newlyLoggedUser);
                }
                else if (response.url.endsWith("logout")) {
                    _this.authService.handleLogout(undefined);
                }
            }
        }));
    };
    AuthInterceptor = __decorate([
        core_1.Injectable()
    ], AuthInterceptor);
    return AuthInterceptor;
}());
exports.AuthInterceptor = AuthInterceptor;

//# sourceMappingURL=auth.interceptor.js.map
