package ws.com.media.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.TextureView;

import androidx.annotation.NonNull;

public class AutoFitTextureView extends TextureView {

    private int mRatioWidth;
    private int mRationHeight;

    public AutoFitTextureView(@NonNull Context context, AttributeSet attributeSet) {
        super(context,attributeSet);
    }

    public void setAspectRatio(int width,int height){
        mRatioWidth = width;
        mRationHeight = height;
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        if(0==mRatioWidth||0==mRationHeight){
            setMeasuredDimension(width,height);
        }else{
            if(width<height*mRatioWidth/mRationHeight){
                setMeasuredDimension(width,width*mRationHeight/mRatioWidth);
            }else{
                setMeasuredDimension(height*mRatioWidth/mRationHeight,height);
            }
        }
    }
}
