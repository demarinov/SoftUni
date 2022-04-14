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
var ArtService = /** @class */ (function () {
    function ArtService(http) {
        this.http = http;
    }
    ArtService.prototype.loadArtList = function () {
        return this.http.get(apiUrl + "/artworks");
    };
    ArtService.prototype.loadArtById = function (artId) {
        return this.http.get(apiUrl + "/artworks/" + artId);
    };
    ArtService.prototype.loadArtsByUserId = function (userId) {
        return this.http.get(apiUrl + "/user/artworks/" + userId, { withCredentials: true });
    };
    ArtService.prototype.addArt$ = function (body) {
        return this.http.post(apiUrl + "/artworks", body, { withCredentials: true });
    };
    ArtService = __decorate([
        core_1.Injectable({
            providedIn: 'root'
        })
    ], ArtService);
    return ArtService;
}());
exports.ArtService = ArtService;

//# sourceMappingURL=art.service.js.map
