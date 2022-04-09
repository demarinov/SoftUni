"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
exports.__esModule = true;
var core_1 = require("@angular/core");
var ArtListItemComponent = /** @class */ (function () {
    function ArtListItemComponent(artService) {
        this.artService = artService;
        this.isLoggedIn = false;
        this.canSubscribe = false;
    }
    ArtListItemComponent.prototype.ngOnInit = function () {
    };
    ArtListItemComponent.prototype.handleSubscriber = function () {
    };
    __decorate([
        core_1.Input('art')
    ], ArtListItemComponent.prototype, "art");
    ArtListItemComponent = __decorate([
        core_1.Component({
            selector: 'app-art-list-item',
            templateUrl: './art-list-item.component.html',
            styleUrls: ['./art-list-item.component.css']
        })
    ], ArtListItemComponent);
    return ArtListItemComponent;
}());
exports.ArtListItemComponent = ArtListItemComponent;

//# sourceMappingURL=art-list-item.component.js.map
