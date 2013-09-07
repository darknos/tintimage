package miga.tintimage;


import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.annotations.Kroll;

import org.appcelerator.titanium.TiBlob;
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
	
	
	private Bitmap tintImage(Bitmap image)	{
		return Tintimage.tint(image);
	}
	
	
	@Kroll.method
	public TiBlob tint(TiBlob blob) {
		TiDrawableReference ref = TiDrawableReference.fromBlob(getActivity(), blob);
		Bitmap img = tintImage(ref.getBitmap());
	
		TiBlob result = TiBlob.blobFromImage(img);
		return result;
	}
	
}
