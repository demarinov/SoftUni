"use strict";

var express = require('express');

var router = express.Router();

var _require = require('../utils'),
    auth = _require.auth;

var _require2 = require('../controllers'),
    themeController = _require2.themeController,
    postController = _require2.postController,
    artworkController = _require2.artworkController; // middleware that is specific to this router


router.get('/', artworkController.getArtworks);
router.post('/', auth(), artworkController.createArt); // router.get('/:themeId', themeController.getTheme);
// router.post('/:themeId', auth(), postController.createPost);
// router.put('/:themeId', auth(), themeController.subscribe);
// router.put('/:themeId/posts/:postId', auth(), postController.editPost);
// router.delete('/:themeId/posts/:postId', auth(), postController.deletePost);
// router.get('/my-trips/:id/reservations', auth(), themeController.getReservations);

module.exports = router;
//# sourceMappingURL=artworks.dev.js.map
