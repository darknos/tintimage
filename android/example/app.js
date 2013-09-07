var tintimage = require('miga.tintimage');

var f = Ti.Filesystem.getFile(Ti.Filesystem.resourcesDirectory, "..", "..", 'images', 'image.jpg');
var blob = f.read();

$.myImage.image = tintimage.tint(blob);
$.index.open();
