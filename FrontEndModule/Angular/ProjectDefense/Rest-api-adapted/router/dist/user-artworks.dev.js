"use strict";

var express = require('express');

var router = express.Router();

var _require = require('../utils'),
    auth = _require.auth;

var _require2 = require('../controllers'),
    themeController = _require2.themeController,
    postController = _require2.postController,
    artworkController = _require2.artworkController;

router.get('/:userId', auth(), artworkController.getArtsByUserId);
module.exports = router;
//# sourceMappingURL=user-artworks.dev.js.map
