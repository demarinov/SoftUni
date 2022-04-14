"use strict";

var express = require('express');

var router = express.Router();

var _require = require('../controllers'),
    authController = _require.authController;

var _require2 = require('../utils'),
    auth = _require2.auth;

router.get('/profile', auth(), authController.getProfileInfo);
router.put('/profile', auth(), authController.editProfileInfo);
module.exports = router;
//# sourceMappingURL=users.dev.js.map
