package miga.tintimage;

import org.appcelerator.kroll.KrollDict;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.ColorFilter;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuff.Mode;

public class Tintimage {
	
	public static Bitmap tint(Bitmap image)
	{
		if (image != null) {
			int width =  image.getWidth();
			int height =  image.getHeight();

			Bitmap resultBitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
			Canvas canvas = new Canvas(resultBitmap);			
			
			Paint paint = new Paint();
			// Color.parseColor("#FF00FF00")
			PorterDuffColorFilter cf = new PorterDuffColorFilter(0xFF00FF00, Mode.MULTIPLY);
			paint.setColorFilter(cf);
			canvas.drawBitmap(image, 0,0, paint);

			return resultBitmap; //Bitmap.createBitmap(image, x, y, width, height);
		}
		
		return null;
	}

}
