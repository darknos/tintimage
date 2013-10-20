var tint = require("miga.tintimage");

/*
// two images and color
$.img_final.image = tint.tint({
image : $.img1.toBlob(), imageOverlay : $.img2.toBlob(), color : "#ff0000", modeColor : "overlay", modeImage : "overlay"
});
*/

/*
// mask
tint.mask({
  image : img1.toBlob(), mask: img2.toBlob()
});
*/

// color overlay
$.img_final.image = tint.tint({
    image : $.img1.toBlob(), color : "#ff0000", modeColor : "multiply"
});



$.index.open();
