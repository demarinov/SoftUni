"use strict";

var _require = require('../models'),
    artworkModel = _require.artworkModel;

function getArtworks(req, res, next) {
  artworkModel.find().populate('userId').then(function (artworks) {
    return res.json(artworks);
  })["catch"](next);
}

function getArt(req, res, next) {
  var artId = req.params.artId;
  artworkModel.findById(artId).populate('userId').then(function (art) {
    return res.json(art);
  })["catch"](next);
}

function getArtsByUserId(req, res, next) {
  var userId = req.params.userId;
  artworkModel.find().populate('userId').then(function (arts) {
    arts = arts.filter(function (art) {
      return art.userId._id == userId;
    });
    return res.json(arts);
  })["catch"](next);
}

function createArt(req, res, next) {
  var _req$body = req.body,
      name = _req$body.name,
      imageUrl = _req$body.imageUrl,
      price = _req$body.price;
  var userId = req.user._id;
  console.log(userId);
  artworkModel.create({
    name: name,
    userId: userId,
    imageUrl: imageUrl,
    price: price
  }).then(function (art) {
    res.status(200).json(art);
  })["catch"](next);
}

module.exports = {
  createArt: createArt,
  getArt: getArt,
  getArtworks: getArtworks,
  getArtsByUserId: getArtsByUserId
};
//# sourceMappingURL=artworkController.dev.js.map
