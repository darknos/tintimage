var tintimage = require('miga.tintimage');

var f = Ti.Filesystem.getFile(Ti.Filesystem.resourcesDirectory, "..", "..", 'images', 'image.jpg');
var blob = f.read();

$.imgAfter.image = tintimage.tint(blob, {
    color : "#ffff00", mode : "darken"
});

$.index.open();
