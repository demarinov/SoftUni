const { artworkModel } = require('../models');

function getArtworks(req, res, next) {
    artworkModel.find()
        .populate('userId')
        .then(artworks => res.json(artworks))
        .catch(next);
}

function getArt(req, res, next) {
    const { artId } = req.params;

    artworkModel.findById(artId)
        .populate( 'userId')
        .then(art => res.json(art))
        .catch(next);
}

function createArt(req, res, next) {
    const { name, imageUrl, price} = req.body;
    const { _id: userId } = req.user;

    artworkModel.create({ name, userId, imageUrl, price})
        .then(art => {
            res.status(200).json(art);
        })
        .catch(next);
}

module.exports = {
    createArt,
    getArt,
    getArtworks,
}
