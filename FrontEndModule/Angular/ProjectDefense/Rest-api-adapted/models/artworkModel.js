const mongoose = require('mongoose');
const { ObjectId } = mongoose.Schema.Types;

const artworkSchema = new mongoose.Schema({
    name: {
        type: String,
    },
    userId: {
        type: ObjectId,
        ref: "User"
    },
    imageUrl: {
        type: String,
    },
    price: {
        type: String,
    }
}, { timestamps: { createdAt: 'created_at' } });

module.exports = mongoose.model('artwork', artworkSchema);
