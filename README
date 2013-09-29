Titanium module to tint an image

Binary inside android/dist/ folder

![ScreenShot](https://raw.github.com/m1ga/tintimage/master/android/example/demo.jpg)


Example:

  xml:
    <ImageView id="myImage"/>

  controller:
    var tintimage = require('miga.tintimage');
    var f = Ti.Filesystem.getFile('image.jpg');
    var blob = f.read();
    $.myImage.image = tintimage.tint(blob,{color:"#ff0000", mode:"multiply"});


Modes:

  add  		Saturate(S + D)  
  clear  	[0, 0]  
  darken  	[Sa + Da - Sa*Da, Sc*(1 - Da) + Dc*(1 - Sa) + min(Sc, Dc)]  
  dst  		[Da, Dc]  
  dst_atoP  	[Sa, Sa * Dc + Sc * (1 - Da)]  
  dst_in  	[Sa * Da, Sa * Dc]  
  dst_out  	[Da * (1 - Sa), Dc * (1 - Sa)]  
  dst_oveR  	[Sa + (1 - Sa)*Da, Rc = Dc + (1 - Da)*Sc]  
  lighten  	[Sa + Da - Sa*Da, Sc*(1 - Da) + Dc*(1 - Sa) + max(Sc, Dc)]  
  multiplY  	[Sa * Da, Sc * Dc]  
  overlay  	 
  screen  	[Sa + Da - Sa * Da, Sc + Dc - Sc * Dc]  
  src  		[Sa, Sc]  
  src_atoP  	[Da, Sc * Da + (1 - Sa) * Dc]  
  src_in  	[Sa * Da, Sc * Da]  
  src_out  	[Sa * (1 - Da), Sc * (1 - Da)]  
  src_oveR  	[Sa + (1 - Sa)*Da, Rc = Sc + (1 - Sa)*Dc]  
  xor  		[Sa + Da - 2 * Sa * Da, Sc * (1 - Da) + (1 - Sa) * Dc]  

  see http://developer.android.com/reference/android/graphics/PorterDuff.Mode.html