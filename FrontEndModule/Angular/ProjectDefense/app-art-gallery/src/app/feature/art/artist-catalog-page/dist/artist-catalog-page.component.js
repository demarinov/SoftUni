"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var utils_1 = require("src/app/utils");
var ArtistCatalogPageComponent = /** @class */ (function () {
    function ArtistCatalogPageComponent(artService, userService, router) {
        this.artService = artService;
        this.userService = userService;
        this.router = router;
    }
    ArtistCatalogPageComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.userService.getProfile$().subscribe({
            next: function (user) {
                _this.currentUser = user;
                console.log(user);
                _this.handleArtistCatalogEvent();
            },
            error: function (error) {
                console.log(error);
                _this.errorMessage = utils_1.SERVICE_UNAVAILABLE_ERROR;
                _this.router.navigate(['/login']);
            }
        });
    };
    ArtistCatalogPageComponent.prototype.handleArtistCatalogEvent = function () {
        var _this = this;
        var userId = this.currentUser._id;
        this.artService.loadArtsByUserId(userId).subscribe({
            next: function (arts) {
                console.log(arts);
                _this.artList = arts;
            },
            error: function (err) {
                console.error(err);
                _this.errorMessage = utils_1.SERVICE_UNAVAILABLE_ERROR;
            }
        });
    };
    ArtistCatalogPageComponent = __decorate([
        core_1.Component({
            selector: 'app-artist-catalog-page',
            templateUrl: './artist-catalog-page.component.html',
            styleUrls: ['./artist-catalog-page.component.css']
        })
    ], ArtistCatalogPageComponent);
    return ArtistCatalogPageComponent;
}());
exports.ArtistCatalogPageComponent = ArtistCatalogPageComponent;

//# sourceMappingURL=artist-catalog-page.component.js.map
