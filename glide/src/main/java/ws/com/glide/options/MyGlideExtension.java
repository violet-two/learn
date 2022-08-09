package ws.com.glide.options;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;

import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.annotation.GlideExtension;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.annotation.GlideType;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;

import jp.wasabeef.glide.transformations.BlurTransformation;
import ws.com.glide.R;

@GlideExtension
public final class MyGlideExtension {
    private MyGlideExtension(){

    }

    @GlideOption
    public static BaseRequestOptions<?> applyAvatar(BaseRequestOptions<?> options,int size){
        BlurTransformation transformation = new BlurTransformation(25,1);

        return options.placeholder(R.mipmap.img)
                .error(R.mipmap.img)
                .override(size)
//                .fitCenter()
//                .transform(transformation)
//                .circleCrop();
//                .centerCrop();
                .transform(new MultiTransformation<>(transformation,new CircleCrop()));
    }

    @NonNull
    @GlideType(Bitmap.class)
    public static RequestBuilder<Bitmap> fade(RequestBuilder<Bitmap> requestBuilder){
        return requestBuilder
                .transition(BitmapTransitionOptions.withCrossFade());
    }

}
