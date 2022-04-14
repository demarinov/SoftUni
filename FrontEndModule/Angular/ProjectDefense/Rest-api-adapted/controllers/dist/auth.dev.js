"use strict";

function _slicedToArray(arr, i) { return _arrayWithHoles(arr) || _iterableToArrayLimit(arr, i) || _nonIterableRest(); }

function _nonIterableRest() { throw new TypeError("Invalid attempt to destructure non-iterable instance"); }

function _iterableToArrayLimit(arr, i) { if (!(Symbol.iterator in Object(arr) || Object.prototype.toString.call(arr) === "[object Arguments]")) { return; } var _arr = []; var _n = true; var _d = false; var _e = undefined; try { for (var _i = arr[Symbol.iterator](), _s; !(_n = (_s = _i.next()).done); _n = true) { _arr.push(_s.value); if (i && _arr.length === i) break; } } catch (err) { _d = true; _e = err; } finally { try { if (!_n && _i["return"] != null) _i["return"](); } finally { if (_d) throw _e; } } return _arr; }

function _arrayWithHoles(arr) { if (Array.isArray(arr)) return arr; }

function _objectWithoutProperties(source, excluded) { if (source == null) return {}; var target = _objectWithoutPropertiesLoose(source, excluded); var key, i; if (Object.getOwnPropertySymbols) { var sourceSymbolKeys = Object.getOwnPropertySymbols(source); for (i = 0; i < sourceSymbolKeys.length; i++) { key = sourceSymbolKeys[i]; if (excluded.indexOf(key) >= 0) continue; if (!Object.prototype.propertyIsEnumerable.call(source, key)) continue; target[key] = source[key]; } } return target; }

function _objectWithoutPropertiesLoose(source, excluded) { if (source == null) return {}; var target = {}; var sourceKeys = Object.keys(source); var key, i; for (i = 0; i < sourceKeys.length; i++) { key = sourceKeys[i]; if (excluded.indexOf(key) >= 0) continue; target[key] = source[key]; } return target; }

var _require = require('../models'),
    userModel = _require.userModel,
    tokenBlacklistModel = _require.tokenBlacklistModel;

var utils = require('../utils');

var _require2 = require('../app-config'),
    authCookieName = _require2.authCookieName;

var bsonToJson = function bsonToJson(data) {
  return JSON.parse(JSON.stringify(data));
};

var removePassword = function removePassword(data) {
  var password = data.password,
      __v = data.__v,
      userData = _objectWithoutProperties(data, ["password", "__v"]);

  return userData;
};

function register(req, res, next) {
  var _req$body = req.body,
      tel = _req$body.tel,
      email = _req$body.email,
      username = _req$body.username,
      password = _req$body.password,
      repeatPassword = _req$body.repeatPassword;
  return userModel.create({
    tel: tel,
    email: email,
    username: username,
    password: password
  }).then(function (createdUser) {
    createdUser = bsonToJson(createdUser);
    createdUser = removePassword(createdUser);
    var token = utils.jwt.createToken({
      id: createdUser._id
    });

    if (process.env.NODE_ENV === 'production') {
      res.cookie(authCookieName, token, {
        httpOnly: true,
        sameSite: 'none',
        secure: true
      });
    } else {
      res.cookie(authCookieName, token, {
        httpOnly: true
      });
    }

    res.status(200).send(createdUser);
  })["catch"](function (err) {
    if (err.name === 'MongoError' && err.code === 11000) {
      var field = err.message.split("index: ")[1];
      field = field.split(" dup key")[0];
      field = field.substring(0, field.lastIndexOf("_"));
      res.status(409).send({
        message: "This ".concat(field, " is already registered!")
      });
      return;
    }

    next(err);
  });
}

function login(req, res, next) {
  var _req$body2 = req.body,
      email = _req$body2.email,
      password = _req$body2.password;
  userModel.findOne({
    email: email
  }).then(function (user) {
    return Promise.all([user, user ? user.matchPassword(password) : false]);
  }).then(function (_ref) {
    var _ref2 = _slicedToArray(_ref, 2),
        user = _ref2[0],
        match = _ref2[1];

    if (!match) {
      res.status(401).send({
        message: 'Wrong email or password'
      });
      return;
    }

    user = bsonToJson(user);
    user = removePassword(user);
    var token = utils.jwt.createToken({
      id: user._id
    });

    if (process.env.NODE_ENV === 'production') {
      res.cookie(authCookieName, token, {
        httpOnly: true,
        sameSite: 'none',
        secure: true
      });
    } else {
      res.cookie(authCookieName, token, {
        httpOnly: true
      });
    }

    res.status(200).send(user);
  })["catch"](next);
}

function logout(req, res) {
  var token = req.cookies[authCookieName];
  tokenBlacklistModel.create({
    token: token
  }).then(function () {
    res.clearCookie(authCookieName).status(204).send({
      message: 'Logged out!'
    });
  })["catch"](function (err) {
    return res.send(err);
  });
}

function getProfileInfo(req, res, next) {
  var userId = req.user._id;
  userModel.findOne({
    _id: userId
  }, {
    password: 0,
    __v: 0
  }) //finding by Id and returning without password and __v
  .then(function (user) {
    res.status(200).json(user);
  })["catch"](next);
}

function editProfileInfo(req, res, next) {
  var userId = req.user._id;
  var _req$body3 = req.body,
      tel = _req$body3.tel,
      username = _req$body3.username,
      email = _req$body3.email;
  userModel.findOneAndUpdate({
    _id: userId
  }, {
    tel: tel,
    username: username,
    email: email
  }, {
    runValidators: true,
    "new": true
  }).then(function (x) {
    res.status(200).json(x);
  })["catch"](next);
}

module.exports = {
  login: login,
  register: register,
  logout: logout,
  getProfileInfo: getProfileInfo,
  editProfileInfo: editProfileInfo
};
//# sourceMappingURL=auth.dev.js.map
