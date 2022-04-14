"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var environment_1 = require("../../environments/environment");
var apiUrl = environment_1.environment.apiUrl;
var PostService = /** @class */ (function () {
    function PostService(http) {
        this.http = http;
    }
    PostService.prototype.loadPostList = function (limit) {
        return this.http.get(apiUrl + "/posts?" + (limit ? "limit=" + limit : ''));
    };
    PostService = __decorate([
        core_1.Injectable({
            providedIn: 'root'
        })
    ], PostService);
    return PostService;
}());
exports.PostService = PostService;

//# sourceMappingURL=post.service.js.map
