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
var ArtDetailPageComponent = /** @class */ (function () {
    function ArtDetailPageComponent(artService, activatedRoute) {
        this.artService = artService;
        this.activatedRoute = activatedRoute;
    }
    ArtDetailPageComponent.prototype.ngOnInit = function () {
        var _this = this;
        var artId = this.activatedRoute.snapshot.params["artId"];
        console.log(artId);
        this.artService.loadArtById(artId).subscribe({
            next: function (art) {
                _this.art = art;
                console.log(_this.art);
            },
            error: function (err) {
                console.log(err);
                _this.errorMessage = utils_1.SERVICE_ERROR;
            }
        });
    };
    ArtDetailPageComponent = __decorate([
        core_1.Component({
            selector: 'app-art-detail-page',
            templateUrl: './art-detail-page.component.html',
            styleUrls: ['./art-detail-page.component.css']
        })
    ], ArtDetailPageComponent);
    return ArtDetailPageComponent;
}());
exports.ArtDetailPageComponent = ArtDetailPageComponent;

//# sourceMappingURL=art-detail-page.component.js.map
