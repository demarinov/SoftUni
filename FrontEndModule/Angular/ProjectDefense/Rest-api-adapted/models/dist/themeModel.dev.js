"use strict";

var mongoose = require('mongoose');

var ObjectId = mongoose.Schema.Types.ObjectId;
var themeSchema = new mongoose.Schema({
  themeName: {
    type: String,
    required: true
  },
  subscribers: [{
    type: ObjectId,
    ref: "User"
  }],
  userId: {
    type: ObjectId,
    ref: "User"
  },
  posts: [{
    type: ObjectId,
    ref: "Post"
  }]
}, {
  timestamps: {
    createdAt: 'created_at'
  }
});
module.exports = mongoose.model('Theme', themeSchema);
//# sourceMappingURL=themeModel.dev.js.map
