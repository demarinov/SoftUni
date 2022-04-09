const { artworkModel } = require('../models');

function getArtworks(req, res, next) {
    artworkModel.find()
        .populate('userId')
        .then(artworks => res.json(artworks))
        .catch(next);
}

function getTheme(req, res, next) {
    const { themeId } = req.params;

    themeModel.findById(themeId)
        .populate({
            path : 'posts',
            populate : {
              path : 'userId'
            }
          })
        .then(theme => res.json(theme))
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

function subscribe(req, res, next) {
    const themeId = req.params.themeId;
    const { _id: userId } = req.user;
    themeModel.findByIdAndUpdate({ _id: themeId }, { $addToSet: { subscribers: userId } }, { new: true })
        .then(updatedTheme => {
            res.status(200).json(updatedTheme)
        })
        .catch(next);
}

module.exports = {
    createArt,
    getTheme,
    subscribe,
    getArtworks,
}
