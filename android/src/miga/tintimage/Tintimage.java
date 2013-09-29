package miga.tintimage;

import org.appcelerator.kroll.KrollDict;

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
import org.appcelerator.kroll.common.Log;

public class Tintimage {

    private static final String LCAT = "TintimageModule";
	
	
	
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
	
	
	public static Bitmap tint(Bitmap image,KrollDict args) {
		if (image != null) {
			String col = args.optString("color", "#FF00FF");
			String mod = args.optString("mode", "multiply");
			
			
			int width =  image.getWidth();
			int height =  image.getHeight();

			Bitmap resultBitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
			Canvas canvas = new Canvas(resultBitmap);			
			
			Paint paint = new Paint();
			
			Mode filterMode = getFilter(mod);
	
			
			PorterDuffColorFilter cf = new PorterDuffColorFilter(Color.parseColor(col), filterMode);
			paint.setColorFilter(cf);
			canvas.drawBitmap(image, 0,0, paint);

			return resultBitmap;
		}
		
		return null;
	}

	public static Bitmap tint(Bitmap image,Bitmap image2,KrollDict args) {
		if (image != null) {
			String col = args.optString("color", "#FF00FF");
			String mod1 = args.optString("mode1", "multiply");
			String mod2 = args.optString("mode2", "multiply");
			Boolean grad = args.optBoolean("vignette", false);
			
			Mode filterMode1 = getFilter(mod1);
			Mode filterMode2 = getFilter(mod2);
			int width =  image2.getWidth();
			int height =  image2.getHeight();

			Bitmap workingBitmap = Bitmap.createScaledBitmap(image,width,height,true);
			Bitmap mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888, true);
			Canvas canvas = new Canvas(mutableBitmap);			
			
			Bitmap resultBitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
			Canvas canvas2 = new Canvas(resultBitmap);			
			
			Paint Compose = new Paint();
			Compose.setXfermode(new PorterDuffXfermode(filterMode1));
			canvas.drawBitmap(image2, 0,0, Compose);

			
			Paint paint = new Paint();
			PorterDuffColorFilter cf = new PorterDuffColorFilter(Color.parseColor(col), filterMode2);
			paint.setColorFilter(cf);
			
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
	/*
	public static Bitmap lomo(Bitmap img){
		int width = img.getWidth();
		int height = img.getHeight();
	  	Bitmap returnBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(returnBitmap);
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		
	  	float[] mat = new float[]{1.7f, 0.1f, 0.1f, 0, -73.1f,0, 1.7f, 0.1f, 0, -73.1f,0, 0.1f, 1.6f, 0, -73.1f,0, 0, 0, 1.0f, 0 };
		
		
		
		ColorMatrix col1= new ColorMatrix(mat);
		
		paint.setColorFilter(new ColorMatrixColorFilter(col1));
		canvas.drawBitmap(img, 0, 0, paint);
	      
	  return returnBitmap;
	}*/
	
	
}
