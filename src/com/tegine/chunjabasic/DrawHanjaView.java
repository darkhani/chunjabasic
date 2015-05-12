package com.tegine.chunjabasic;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class DrawHanjaView extends View implements OnTouchListener {

    private static final float STROKE_WIDTH = 5f;

    List<Point> points = new ArrayList<Point>();
    Paint paint = new Paint();
    boolean first = true;
    String passString;
    
    Path mPath;
    Path circlePath;
    
    Paint mBitmapPaint;
    Paint circlePaint;
    
    public int width;
    public  int height;
    private Bitmap  mBitmap;
    private Canvas  mCanvas;

    Context context;
    
    public DrawHanjaView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setFocusable(true);
        setFocusableInTouchMode(true);

        this.setOnTouchListener(this);
        this.setBackgroundColor(Color.BLACK);
        
        mPath = new Path();
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);  
        circlePaint = new Paint();
        circlePath = new Path();
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(Color.BLUE);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeJoin(Paint.Join.MITER);
        circlePaint.setStrokeWidth(4f); 
        
        mCanvas = new Canvas();
        settingPaint();
     
    }

    void settingPaint(){
    	paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(STROKE_WIDTH);
        paint.setColor(Color.argb(128, 255, 255, 0));
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(3);  
        
        
    }
    @Override
    public void onDraw(Canvas canvas) {
//        Path path = new Path();
//        for (Point point : points) {
//            if (first) {
//                first = false;
//                path.moveTo(point.x, point.y);
//            } else {
//                path.lineTo(point.x, point.y);
//                canvas.drawPoint(point.x, point.y, paint);
//            }
//        }
//
////        canvas.drawPath(path, paint);
////        canvas.drawPoint(x, y, paint);
    	
//    	canvas.drawBitmap( mBitmap, 0, 0, mBitmapPaint);

        canvas.drawPath( mPath,  paint);

        canvas.drawPath( circlePath,  circlePaint);
        
    }
   
    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;

    private void touch_start(float x, float y) {
//    mPath.reset();
    mPath.moveTo(x, y);
    mX = x;
    mY = y;
    }
    private void touch_move(float x, float y) {
    float dx = Math.abs(x - mX);
    float dy = Math.abs(y - mY);
    if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
         mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
        mX = x;
        mY = y;

        circlePath.reset();
        circlePath.addCircle(mX, mY, 30, Path.Direction.CW);
    }
    }
    private void touch_up() {
    mPath.lineTo(mX, mY);
    circlePath.reset();
    // commit the path to our offscreen
    mCanvas.drawPath(mPath,  paint);
    // kill this so we don't double draw
//    mPath.reset();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
    float x = event.getX();
    float y = event.getY();

    switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:
            touch_start(x, y);
            invalidate();
            break;
        case MotionEvent.ACTION_MOVE:
            touch_move(x, y);
            invalidate();
            break;
        case MotionEvent.ACTION_UP:
            touch_up();
            invalidate();
            break;
    }
    return true;
    }

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}  
    
}
