package miga.tintimage;

import org.appcelerator.kroll.KrollDict;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.ColorFilter;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuff.Mode;
import org.appcelerator.kroll.common.Log;

public class Tintimage {
	private static final String LCAT = "TintimageModule";
	
	public static Bitmap tint(Bitmap image,KrollDict args)
	{
		if (image != null) {
			String col = args.optString("color", "#FF00FF");
			String mod = args.optString("mode", "multiply");
			Mode filterMode = Mode.MULTIPLY;
			int width =  image.getWidth();
			int height =  image.getHeight();

			Bitmap resultBitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
			Canvas canvas = new Canvas(resultBitmap);			
			
			Paint paint = new Paint();
			
		      
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
			
			PorterDuffColorFilter cf = new PorterDuffColorFilter(Color.parseColor(col), filterMode);
			paint.setColorFilter(cf);
			canvas.drawBitmap(image, 0,0, paint);

			return resultBitmap;
		}
		
		return null;
	}

}
