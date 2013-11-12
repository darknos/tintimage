package miga.tintimage;


import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.annotations.Kroll;
import java.util.HashMap;
import org.appcelerator.titanium.TiApplication;
import org.appcelerator.titanium.TiBlob;
import org.appcelerator.titanium.view.TiUIView;
import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.titanium.view.TiDrawableReference;

import android.graphics.Bitmap;
import android.util.Log;
import android.app.Activity;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.BitmapShader;
import android.graphics.drawable.BitmapDrawable;
import org.appcelerator.titanium.view.TiDrawableReference;
import android.graphics.Matrix;


@Kroll.module(name="Tintimage", id="miga.tintimage")
public class TintimageModule extends KrollModule
{
	// Standard Debugging variables
	private static final String LCAT = "TintimageModule";
	boolean tileImage=true;
	Activity activity;
	
	private static Mode getFilter(String mod){
	  Mode filterMode;
	  if (mod.equals("add")) {
	      filterMode = Mode.ADD;
	  } else if (mod.equals("clear")) {
	      filterMode = Mode.CLEAR;
	  } else if (mod.equals("darken")) {
	      filterMode = Mode.DARKEN;
	  } else if (mod.equals("dst")) {
	      filterMode = Mode.DST;
	  } else if (mod.equals("dst_atop")) {
	      filterMode = Mode.DST_ATOP;
	  } else if (mod.equals("dst_in")) {
	      filterMode = Mode.DST_IN;
	  } else if (mod.equals("dst_out")) {
	      filterMode = Mode.DST_OUT;
	  } else if (mod.equals("dst_over")) {
	      filterMode = Mode.DST_OVER;
	  } else if (mod.equals("lighten")) {
	      filterMode = Mode.LIGHTEN;
	  } else if (mod.equals("multiply")) {
	      filterMode = Mode.MULTIPLY;
	  } else if (mod.equals("overlay")) {
	      filterMode = Mode.OVERLAY;
	  } else if (mod.equals("screen")) {
	      filterMode = Mode.SCREEN;
	  } else if (mod.equals("src")) {
	      filterMode = Mode.SRC;
	  } else if (mod.equals("src_atop")) {
	      filterMode = Mode.SRC_ATOP;
	  } else if (mod.equals("src_in")) {
	      filterMode = Mode.SRC_IN;
	  } else if (mod.equals("src_out")) {
	      filterMode = Mode.SRC_OUT;
	  } else if (mod.equals("src_over")) {
	      filterMode = Mode.SRC_OVER;
	  } else if (mod.equals("xor")) {
	      filterMode = Mode.XOR;
	  } else {
	      filterMode = Mode.MULTIPLY;
	  }
	  return filterMode;
	}
	

	public TintimageModule() {
		super();
		TiApplication appContext = TiApplication.getInstance();
		activity = appContext.getCurrentActivity();
		//context=activity.getApplicationContext();
	}

	
	private Bitmap tintImage(Bitmap image, Bitmap image2, KrollDict args) {
		String col = args.optString("color", "");
		String mod1 = args.optString("modeColor", "multiply");
		String mod2 = args.optString("modeImage", "multiply");
		Boolean grad = args.optBoolean("vignette", false);
			
		if (image != null) {
			
			Mode filterMode1 = getFilter(mod1);
			Mode filterMode2 = getFilter(mod2);
			int width =  image.getWidth();
			int height =  image.getHeight();

			Bitmap workingBitmap = Bitmap.createScaledBitmap(image,width,height,true);
			Bitmap mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888, true);
			Canvas canvas = new Canvas(mutableBitmap);			
			
			Bitmap resultBitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
			Canvas canvas2 = new Canvas(resultBitmap);			
			
			// add second image
			if (image2!=null){
			  Paint Compose = new Paint();
			  Compose.setXfermode(new PorterDuffXfermode(filterMode1));
			  canvas.drawBitmap(image2, 0,0, Compose);
			}
			
			Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
			  
			  
			// add color filter
			if (col!=""){
			  PorterDuffColorFilter cf = new PorterDuffColorFilter(Color.parseColor(col), filterMode2);
			  paint.setColorFilter(cf);
			}
			
			// gradient
			if (grad){
			  int[] Colors = {0x00000000, 0xFF000000};
			  float[] ColorPosition = {0.10f, 0.99f};
			  RadialGradient gradient = new RadialGradient(width / 2,height / 2, width - width /2, Colors, ColorPosition, android.graphics.Shader.TileMode.CLAMP);
			  paint.setDither(true);
			  paint.setShader(gradient);
			}
			
			canvas2.drawBitmap(mutableBitmap, 0,0, paint);
			return resultBitmap;
		}
		
		return null;
	}
	
	private Bitmap mask(Bitmap image, Bitmap mask) {
	// todo: image wiederholen und skalierung richtig
	  Bitmap bitmapOut = Bitmap.createBitmap(mask.getWidth(),mask.getHeight(), Bitmap.Config.ARGB_8888);
	  Canvas canvas = new Canvas(bitmapOut);

	  Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	  
	  if (tileImage){
	    BitmapDrawable background = new BitmapDrawable(image);
	    //in this case, you want to tile the entire view
	    background.setBounds(0, 0, mask.getWidth(),mask.getHeight());
	    background.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
	    background.draw(canvas);
	  } else {
	    canvas.drawBitmap(image,(int)(mask.getWidth()*0.5 - image.getWidth()*0.5), 0, paint);
	  }

	  
	  Paint xferPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	  xferPaint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));

	  
	  canvas.drawBitmap(mask, 0, 0, xferPaint);
	  xferPaint.setXfermode(null);
	  return bitmapOut;
	}
	

	

	@Kroll.method
	public TiBlob tint(HashMap args) {
		KrollDict arg = new KrollDict(args);
		TiBlob blob = (TiBlob)arg.get("image");
		TiBlob blob2 = (TiBlob)arg.get("imageOverlay");
		TiDrawableReference ref = TiDrawableReference.fromBlob(activity, blob);
		TiDrawableReference ref2 = TiDrawableReference.fromBlob(activity, blob2);
		
		Bitmap img = tintImage(ref.getBitmap(),ref2.getBitmap(),arg);
	
		TiBlob result = TiBlob.blobFromImage(img);
		return result;
	}
	
	
	@Kroll.method
	public TiBlob mask(HashMap args) {
		KrollDict arg = new KrollDict(args);
		TiBlob blob = (TiBlob)arg.get("image");
		TiBlob blob2 = (TiBlob)arg.get("mask");
		TiDrawableReference ref = TiDrawableReference.fromBlob(activity, blob);
		TiDrawableReference ref2 = TiDrawableReference.fromBlob(activity, blob2);
		
		Bitmap img = mask(ref.getBitmap(),ref2.getBitmap());
	
		TiBlob result = TiBlob.blobFromImage(img);
		return result;
	}

	
}
