"use strict";

var _require = require('../models'),
    artworkModel = _require.artworkModel;

function getArtworks(req, res, next) {
  artworkModel.find().populate('userId').then(function (artworks) {
    return res.json(artworks);
  })["catch"](next);
}

function getTheme(req, res, next) {
  var themeId = req.params.themeId;
  themeModel.findById(themeId).populate({
    path: 'posts',
    populate: {
      path: 'userId'
    }
  }).then(function (theme) {
    return res.json(theme);
  })["catch"](next);
}

function createArt(req, res, next) {
  var _req$body = req.body,
      name = _req$body.name,
      imageUrl = _req$body.imageUrl,
      price = _req$body.price;
  var userId = req.user._id;
  artworkModel.create({
    name: name,
    userId: userId,
    imageUrl: imageUrl,
    price: price
  }).then(function (art) {
    res.status(200).json(art);
  })["catch"](next);
}

function subscribe(req, res, next) {
  var themeId = req.params.themeId;
  var userId = req.user._id;
  themeModel.findByIdAndUpdate({
    _id: themeId
  }, {
    $addToSet: {
      subscribers: userId
    }
  }, {
    "new": true
  }).then(function (updatedTheme) {
    res.status(200).json(updatedTheme);
  })["catch"](next);
}

module.exports = {
  createArt: createArt,
  getTheme: getTheme,
  subscribe: subscribe,
  getArtworks: getArtworks
};
//# sourceMappingURL=artworkController.dev.js.map
