const router = require('express').Router();
const users = require('./users');
const themes = require('./themes');
const posts = require('./posts');
const likes = require('./likes');
const test = require('./test');
const artworks = require('./artworks');
const user_artworks = require('./user-artworks');
const { authController } = require('../controllers');

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
