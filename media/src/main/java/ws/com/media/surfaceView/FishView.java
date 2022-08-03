//package ws.com.media.surfaceView;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Matrix;
//import android.util.AttributeSet;
//import android.util.Log;
//import android.view.Surface;
//import android.view.SurfaceHolder;
//import android.view.SurfaceView;
//
//import androidx.annotation.NonNull;
//
//import java.util.Random;
//
//import ws.com.media.R;
//
//public class FishView extends SurfaceView implements SurfaceHolder.Callback {
//    private SurfaceHolder holder;
//    private UpdateViewThread updatethread;
//    private boolean hasSurface;
//    private Bitmap back;
//    private Bitmap[] fishs;
//    private int fishIndex = 0;//绘制第几张鱼
//    //鱼的初始位置
//    private float fishx = 778;
//    private float fishy = 500;
//    private float fishSpeed = 6; //鱼的游动速度
//    //鱼的游动角度
//    private int fishAngle = new Random().nextInt(60);
//    Matrix matrix = new Matrix();
//
//    public FishView(Context context) {
//        super(context);
//        Log.i("mydate" , "开始");
//        //获取surfaceview的surfaceholder，并将该类的实例作为其callback
//        holder = getHolder();
//        holder.addCallback(this);//以自身作为callback,回调方法
//        hasSurface = false;
//        back =  BitmapFactory.decodeResource(context.getResources() , R.drawable.fishbg);//背景
//        int[] id = new int[]{R.drawable.fish0 , R.drawable.fish1 , R.drawable.fish2 ,R.drawable.fish3 ,R.drawable.fish4 ,R.drawable.fish5 ,R.drawable.fish6 ,R.drawable.fish7 ,R.drawable.fish8, R.drawable.fish9};
//        fishs = new Bitmap[10];
//        //初始化 鱼游动的10张图
//        for (int i = 0 ; i < 10 ; i++){
//            try {
//                fishs[i] = BitmapFactory.decodeResource(context.getResources() , id[i]);
//            } catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//        Log.i("mydate" , "结束");
//    }
//
//    public FishView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        Log.i("mydate" , "开始");
//        //获取surfaceview的surfaceholder，并将该类的实例作为其callback
//        holder = getHolder();
//        holder.addCallback(this);//以自身作为callback,回调方法
//        hasSurface = false;
//        back = BitmapFactory.decodeResource(context.getResources() , R.drawable.fishbg);//背景
//        int[] id = new int[]{R.drawable.fish0 , R.drawable.fish1 , R.drawable.fish2 ,R.drawable.fish3 ,R.drawable.fish4 ,R.drawable.fish5 ,R.drawable.fish6 ,R.drawable.fish7 ,R.drawable.fish8, R.drawable.fish9};
//        fishs = new Bitmap[10];
//        //初始化 鱼游动的10张图
//        for (int i = 0 ; i < 10 ; i++){
//            try {
////    int fishId = (Integer) R.drawable.class.getField("fish" + i).get(null);//反射机制获取图片
////    Log.i("mydate" , " "+ fishId);
//                fishs[i] = BitmapFactory.decodeResource(context.getResources() , id[i]);
//            } catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//        Log.i("mydate" , "结束");
//    }
//
//
//    public void resume(){
//        //创建和启动 图片更新线程
//        if (updatethread == null){
//            updatethread = new UpdateViewThread();
//            if (hasSurface == true){
//                updatethread.start();
//            }
//        }
//    }
//
//    public void pause(){
//        //停止 图像更新线程
//        if (updatethread != null){
//            updatethread.requestExitAndWait();
//            updatethread = null;
//        }
//    }
//
//
//    @Override
//    public void surfaceCreated(SurfaceHolder holder) { //surfaceview被创建时回调该方法
//        hasSurface = true;
//        resume(); //开启线程更新
//    }
//
//    @Override
//    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {//surfaceview改变时回调该方法
//        if (updatethread != null){
//            updatethread.onWindowResize(width , height);
//        }
//    }
//
//    @Override
//    public void surfaceDestroyed(SurfaceHolder holder) {//surfaceview销毁时回调该方法
//        hasSurface = false;
//        pause(); //停止线程更新
//    }
//
//    class UpdateViewThread extends Thread{
//        //定义图像是否更新完成的标志
//        private boolean done;
//
//        public UpdateViewThread() {
//            super();
//            done = false;
//        }
//
//        @Override
//        public void run() {
//            SurfaceHolder surfaceholder = holder;
//            //循环绘制，直到线程停止
//            while (!done){
//                Canvas canvas = surfaceholder.lockCanvas();//锁定surfaceview,准备绘制
//                //绘制背景
//                canvas.drawBitmap(back , 0 , 0 , null);
//                //鱼游出屏幕外，重新初始化鱼的位置
//                if (fishx < 0 ){
//                    fishx = 778;
//                    fishy = 500;
//                    fishAngle = new Random().nextInt(60);
//                }
//                if (fishy < 0){
//                    fishx = 778;
//                    fishy = 500;
//                    fishAngle = new Random().nextInt(60);
//                }
//                //用matrix控制鱼的旋转角度和位置
//                matrix.reset();
//                matrix.setRotate(fishAngle);//下面的位置计算看图片的解释如下：
//                matrix.postTranslate(fishx -= fishSpeed * Math.cos(Math.toRadians(fishAngle)) , fishy -= fishSpeed * Math.sin(Math.toRadians(fishAngle)));
//                canvas.drawBitmap(fishs[fishIndex++%fishs.length] , matrix , null);
//                surfaceholder.unlockCanvasAndPost(canvas);//解锁canvas，渲染绘制图像
//                try {
//                    Thread.sleep(60);
//                } catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        public void requestExitAndWait() {
//            //将绘制线程 标记为完成 ,并合并到主线程中
//            done = true;
//            try {
//                join();
//            } catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//
//        public void onWindowResize(int width, int height) {
//            //处理surfaceview的大小改变事件
//        }
//    }
//
//}
