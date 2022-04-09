"use strict";

function _slicedToArray(arr, i) { return _arrayWithHoles(arr) || _iterableToArrayLimit(arr, i) || _nonIterableRest(); }

function _nonIterableRest() { throw new TypeError("Invalid attempt to destructure non-iterable instance"); }

function _iterableToArrayLimit(arr, i) { if (!(Symbol.iterator in Object(arr) || Object.prototype.toString.call(arr) === "[object Arguments]")) { return; } var _arr = []; var _n = true; var _d = false; var _e = undefined; try { for (var _i = arr[Symbol.iterator](), _s; !(_n = (_s = _i.next()).done); _n = true) { _arr.push(_s.value); if (i && _arr.length === i) break; } } catch (err) { _d = true; _e = err; } finally { try { if (!_n && _i["return"] != null) _i["return"](); } finally { if (_d) throw _e; } } return _arr; }

function _arrayWithHoles(arr) { if (Array.isArray(arr)) return arr; }

var _require = require('../models'),
    userModel = _require.userModel,
    themeModel = _require.themeModel,
    postModel = _require.postModel;

function newPost(text, userId, themeId) {
  return postModel.create({
    text: text,
    userId: userId,
    themeId: themeId
  }).then(function (post) {
    return Promise.all([userModel.updateOne({
      _id: userId
    }, {
      $push: {
        posts: post._id
      },
      $addToSet: {
        themes: themeId
      }
    }), themeModel.findByIdAndUpdate({
      _id: themeId
    }, {
      $push: {
        posts: post._id
      },
      $addToSet: {
        subscribers: userId
      }
    }, {
      "new": true
    })]);
  });
}

function getLatestsPosts(req, res, next) {
  var limit = Number(req.query.limit) || 0;
  postModel.find().sort({
    created_at: -1
  }).limit(limit).populate('themeId userId').then(function (posts) {
    res.status(200).json(posts);
  })["catch"](next);
}

function createPost(req, res, next) {
  var themeId = req.params.themeId;
  var userId = req.user._id;
  var postText = req.body.postText;
  newPost(postText, userId, themeId).then(function (_ref) {
    var _ref2 = _slicedToArray(_ref, 2),
        _ = _ref2[0],
        updatedTheme = _ref2[1];

    return res.status(200).json(updatedTheme);
  })["catch"](next);
}

function editPost(req, res, next) {
  var postId = req.params.postId;
  var postText = req.body.postText;
  var userId = req.user._id; // if the userId is not the same as this one of the post, the post will not be updated

  postModel.findOneAndUpdate({
    _id: postId,
    userId: userId
  }, {
    text: postText
  }, {
    "new": true
  }).then(function (updatedPost) {
    if (updatedPost) {
      res.status(200).json(updatedPost);
    } else {
      res.status(401).json({
        message: "Not allowed!"
      });
    }
  })["catch"](next);
}

function deletePost(req, res, next) {
  var _req$params = req.params,
      postId = _req$params.postId,
      themeId = _req$params.themeId;
  var userId = req.user._id;
  Promise.all([postModel.findOneAndDelete({
    _id: postId,
    userId: userId
  }), userModel.findOneAndUpdate({
    _id: userId
  }, {
    $pull: {
      posts: postId
    }
  }), themeModel.findOneAndUpdate({
    _id: themeId
  }, {
    $pull: {
      posts: postId
    }
  })]).then(function (_ref3) {
    var _ref4 = _slicedToArray(_ref3, 3),
        deletedOne = _ref4[0],
        _ = _ref4[1],
        __ = _ref4[2];

    if (deletedOne) {
      res.status(200).json(deletedOne);
    } else {
      res.status(401).json({
        message: "Not allowed!"
      });
    }
  })["catch"](next);
}

function like(req, res, next) {
  var postId = req.params.postId;
  var userId = req.user._id;
  console.log('like');
  postModel.updateOne({
    _id: postId
  }, {
    $addToSet: {
      likes: userId
    }
  }, {
    "new": true
  }).then(function () {
    return res.status(200).json({
      message: 'Liked successful!'
    });
  })["catch"](next);
}

module.exports = {
  getLatestsPosts: getLatestsPosts,
  newPost: newPost,
  createPost: createPost,
  editPost: editPost,
  deletePost: deletePost,
  like: like
};
//# sourceMappingURL=postController.dev.js.map
