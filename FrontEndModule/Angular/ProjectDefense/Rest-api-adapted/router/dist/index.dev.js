"use strict";

var router = require('express').Router();

var users = require('./users');

var themes = require('./themes');

var posts = require('./posts');

var likes = require('./likes');

var test = require('./test');

var artworks = require('./artworks');

var user_artworks = require('./user-artworks');

var _require = require('../controllers'),
    authController = _require.authController;

router.post('/register', authController.register);
router.post('/login', authController.login);
router.post('/logout', authController.logout);
router.use('/users', users);
router.use('/themes', themes);
router.use('/artworks', artworks);
router.use('/user/artworks', user_artworks);
router.use('/posts', posts);
router.use('/likes', likes);
router.use('/test', test);
module.exports = router;
//# sourceMappingURL=index.dev.js.map
