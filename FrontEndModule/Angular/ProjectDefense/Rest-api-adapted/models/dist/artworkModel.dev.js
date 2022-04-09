"use strict";

var mongoose = require('mongoose');

var ObjectId = mongoose.Schema.Types.ObjectId;
var artworkSchema = new mongoose.Schema({
  name: {
    type: String
  },
  userId: {
    type: ObjectId,
    ref: "User"
  },
  imageUrl: {
    type: String
  },
  price: {
    type: String
  }
}, {
  timestamps: {
    createdAt: 'created_at'
  }
});
module.exports = mongoose.model('artwork', artworkSchema);
//# sourceMappingURL=artworkModel.dev.js.map
