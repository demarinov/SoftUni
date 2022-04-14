const express = require('express');
const router = express.Router();
const { auth } = require('../utils');
const { themeController, postController, artworkController } = require('../controllers');


router.get('/:userId', auth(), artworkController.getArtsByUserId);

module.exports = router