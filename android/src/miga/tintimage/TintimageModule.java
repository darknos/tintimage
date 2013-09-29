package miga.tintimage;


import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.annotations.Kroll;
import java.util.HashMap;

import org.appcelerator.titanium.TiBlob;
import org.appcelerator.titanium.view.TiUIView;
import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.titanium.view.TiDrawableReference;

import android.graphics.Bitmap;
import android.util.Log;

@Kroll.module(name="Tintimage", id="miga.tintimage")
public class TintimageModule extends KrollModule
{
	// Standard Debugging variables
	private static final String LCAT = "TintimageModule";


	public TintimageModule() {
		super();
	}

	// Image Transform Helpers
	
	
	private Bitmap tintImage(Bitmap image,KrollDict args)	{
		return Tintimage.tint(image,args);
	}
	
	
	
	private Bitmap tintImage(Bitmap image,Bitmap image2,KrollDict args)	{
		return Tintimage.tint(image,image2,args);
	}
	
	

	
	@Kroll.method
	public TiBlob tint(TiBlob blob,HashMap args) {
		TiDrawableReference ref = TiDrawableReference.fromBlob(getActivity(), blob);
		Bitmap img = tintImage(ref.getBitmap(),new KrollDict(args));
	
		TiBlob result = TiBlob.blobFromImage(img);
		return result;
	}

	@Kroll.method
	public TiBlob tint(TiBlob blob,TiBlob blob2,HashMap args) {
		TiDrawableReference ref = TiDrawableReference.fromBlob(getActivity(), blob);
		TiDrawableReference ref2 = TiDrawableReference.fromBlob(getActivity(), blob2);
		Bitmap img = tintImage(ref.getBitmap(),ref2.getBitmap(),new KrollDict(args));
	
		TiBlob result = TiBlob.blobFromImage(img);
		return result;
	}

	
}
