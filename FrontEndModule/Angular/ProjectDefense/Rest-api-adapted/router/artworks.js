const express = require('express');
const router = express.Router();
const { auth } = require('../utils');
const { themeController, postController, artworkController } = require('../controllers');

// middleware that is specific to this router

router.get('/', artworkController.getArtworks);
router.post('/', auth(), artworkController.createArt);

router.get('/:artId', artworkController.getArt);

module.exports = router