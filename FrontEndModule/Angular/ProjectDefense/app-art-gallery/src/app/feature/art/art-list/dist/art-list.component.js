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
var ArtListComponent = /** @class */ (function () {
    function ArtListComponent(artService) {
        this.artService = artService;
    }
    ArtListComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.artService.loadArtList().subscribe({
            next: function (arts) {
                console.log(arts);
                _this.artList = arts;
            },
            error: function (err) {
                console.error(err);
                _this.errorMessage = utils_1.SERVICE_ERROR;
            }
        });
    };
    ArtListComponent = __decorate([
        core_1.Component({
            selector: 'app-art-list',
            templateUrl: './art-list.component.html',
            styleUrls: ['./art-list.component.css']
        })
    ], ArtListComponent);
    return ArtListComponent;
}());
exports.ArtListComponent = ArtListComponent;

//# sourceMappingURL=art-list.component.js.map
