package ws.com.media;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.os.Build;
import android.os.Bundle;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import ws.com.media.view.AutoFitTextureView;

public class cameraActivity extends AppCompatActivity implements View.OnClickListener {

    private static final SparseIntArray ORIENTATIONS = new SparseIntArray();

    static {
        ORIENTATIONS.append(Surface.ROTATION_0, 90);
        ORIENTATIONS.append(Surface.ROTATION_90, 0);
        ORIENTATIONS.append(Surface.ROTATION_180, 270);
        ORIENTATIONS.append(Surface.ROTATION_270, 180);
    }

    //定义界面上的根布局管理器
    private FrameLayout rootLayout;
    //定义自定义的AutoFitTextureView组件，用于预览摄像头照片
    private AutoFitTextureView textureView;
    //摄像头id 0代码后置，1代表前置
    private String mCameraId = "0";
    //定义代表摄像头的成员变量
    private CameraDevice cameraDevice;
    //预览尺寸
    private Size previewSize;
    private CaptureRequest.Builder previewRequestBuilder;
    //定义用于预览照片的捕获请求
    private CaptureRequest previewRequest;
    //定义CameraCaptureSession 成员变量
    private CameraCaptureSession cameraCaptureSession;
    private ImageReader imageReader;
    private CameraManager manager;
    private TextureView.SurfaceTextureListener mSurfaceTextureListener = new TextureView.SurfaceTextureListener() {
        @Override
        public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surfaceTexture, int width, int height) {
            //当TextureView可用时，打开摄像头
            openCamera(mCameraId,width, height);
        }
        //SurfaceTexture当的缓冲区大小更改时调用。
        @Override
        public void onSurfaceTextureSizeChanged(@NonNull SurfaceTexture surfaceTexture, int width, int height) {
            configureTransform(width, height);
        }
        //SurfaceTexture当指定的即将被销毁时调用
        @Override
        public boolean onSurfaceTextureDestroyed(@NonNull SurfaceTexture surfaceTexture) {
            return true;
        }

        @Override
        public void onSurfaceTextureUpdated(@NonNull SurfaceTexture surfaceTexture) {

        }
    };

    private final CameraDevice.StateCallback stateCallback = new CameraDevice.StateCallback() {
        //摄像头被打开时激发该方法
        @Override
        public void onOpened(@NonNull CameraDevice cameraDevice) {
            cameraActivity.this.cameraDevice = cameraDevice;
            //开始预览
            createCameraPreviewSession();
        }

        //摄像头断开连接
        @Override
        public void onDisconnected(@NonNull CameraDevice cameraDevice) {
            cameraDevice.close();
            cameraActivity.this.cameraDevice = null;
        }

        //打开摄像头错误
        @Override
        public void onError(@NonNull CameraDevice cameraDevice, int i) {
            cameraDevice.close();
            cameraActivity.this.cameraDevice = null;
            cameraActivity.this.finish();
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        rootLayout = findViewById(R.id.root);
        //请求摄像头权限
        requestPermissions(new String[]{Manifest.permission.CAMERA}, 0x123);
        findViewById(R.id.btn_change_camera).setOnClickListener(this);
    }

    @Override
    //请求权限的回调函数
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0x123 && grantResults.length == 1
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //创建预览摄像头照片的TextureView组件
            textureView = new AutoFitTextureView(cameraActivity.this, null);
            //为textureView组件设置监听器
            textureView.setSurfaceTextureListener(mSurfaceTextureListener);
            rootLayout.addView(textureView);
            findViewById(R.id.capture).setOnClickListener(view -> captureStillPicture());

        }
    }

    private void captureStillPicture() {
        try {
            if (cameraDevice == null) {
                return;
            }
            //创建作为拍照的captureRequest.Builder
            CaptureRequest.Builder captureRequestBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);
            //将imageReader的surface作为CaptureRequest.Builder的目标
            captureRequestBuilder.addTarget(imageReader.getSurface());
            //自动对焦模式
            captureRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);
            //设置自动曝光模式
            captureRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE, CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH);
            //获取设备方向
            int rotation = getWindowManager().getDefaultDisplay().getRotation();
            //根据设备方向计算设置照片的方向
            captureRequestBuilder.set(CaptureRequest.JPEG_ORIENTATION, ORIENTATIONS.get(rotation));
            //停止连续取景
            cameraCaptureSession.stopRepeating();
            //捕获静态图像
            cameraCaptureSession.capture(captureRequestBuilder.build(), new CameraCaptureSession.CaptureCallback() {
                @Override
                public void onCaptureCompleted(@NonNull CameraCaptureSession session, @NonNull CaptureRequest request, @NonNull TotalCaptureResult result) {
                    try {
                        //重设自动对焦模式
                        previewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, CameraMetadata.CONTROL_AF_TRIGGER_CANCEL);
                        //设置自动曝光
                        previewRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE, CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH);
                        //打开连续取景模式
                        cameraCaptureSession.setRepeatingRequest(previewRequest, null, null);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }
            }, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    //根据手机的旋转方向确定预览图像的方向
    private void configureTransform(int width, int height) {
        if (null == previewSize) {
            return;
        }
        //获取手机的旋转方向
        int rotation = getWindowManager().getDefaultDisplay().getRotation();
        Matrix matrix = new Matrix();
        RectF viewRect = new RectF(0, 0, width, height);
        RectF bufferRect = new RectF(0, 0, previewSize.getHeight(), previewSize.getWidth());
        float centerX = viewRect.centerX();
        float centerY = viewRect.centerY();
        //处理手机横屏情况
        if (Surface.ROTATION_90 == rotation || Surface.ROTATION_270 == rotation) {
            bufferRect.offset(centerX - bufferRect.centerX(), centerY - bufferRect.centerY());
            matrix.setRectToRect(viewRect, bufferRect, Matrix.ScaleToFit.FILL);
            float scale = Math.max(
                    (float) height / previewSize.getHeight(),
                    (float) height / previewSize.getWidth());
            matrix.postScale(scale, scale, centerX, centerY);
            matrix.postRotate(90 * (rotation - 2), centerX, centerY);
        }
        //处理手机倒置的情况
        else if (Surface.ROTATION_180 == rotation) {
            matrix.postRotate(180, centerX, centerY);
        }
        //控制Camera显示范围
        textureView.setTransform(matrix);
    }

    //打开摄像头
    private void openCamera(String mCameraId,int width, int height) {
        setUpCameraOutputs(width, height);
        configureTransform(width, height);
        manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            //如果用户没有授权摄像头,则直接返回
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
            }
            //打开摄像头
            manager.openCamera(mCameraId, stateCallback, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

    }



    private void createCameraPreviewSession() {
        try {
            SurfaceTexture texture = textureView.getSurfaceTexture();
            texture.setDefaultBufferSize(previewSize.getWidth(), previewSize.getHeight());
            Surface surface = new Surface(texture);
            //创建作为预览的CaptureRequest.Builder
            previewRequestBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            //将textView的surface作为CaptureRequest.Builder的目标
            previewRequestBuilder.addTarget(new Surface(texture));
            //创建CameraCaptureSession,该对象负责管理处理预览请求和拍照请求
            cameraDevice.createCaptureSession(Arrays.asList(surface, imageReader.getSurface()), new CameraCaptureSession.StateCallback() {
                @Override
                public void onConfigured(@NonNull CameraCaptureSession captureSession) {
                    //如果摄像头为null，直接结束方法
                    if (null == cameraDevice) {
                        return;
                    }
                    //当摄像头已经准备好时，开始显示预览
                    cameraCaptureSession = captureSession;
                    //设置自动对焦模式
                    previewRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);
                    //设置自动曝光
                    previewRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE, CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH);
                    //开始显示相机预览
                    previewRequest = previewRequestBuilder.build();
                    try {
                        //设置预览时连续捕获图像数据
                        captureSession.setRepeatingRequest(previewRequest, null, null);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onConfigureFailed(@NonNull CameraCaptureSession cameraCaptureSession) {
                    Toast.makeText(cameraActivity.this, "配置失败!", Toast.LENGTH_SHORT).show();
                }
            }, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    //相机参数的设置
    private void setUpCameraOutputs(int width, int height) {
        CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            //获取指定摄像头的特性
            CameraCharacteristics characteristics = manager.getCameraCharacteristics(mCameraId);
            //获取摄像头支持的配置属性
            StreamConfigurationMap map = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
            //获取摄像头的最大尺寸
            Size largest = Collections.max(Arrays.asList(map.getOutputSizes(ImageFormat.JPEG)),
                    new CompareSizesByArea());
            //创建一个ImageReader对象，用于获取摄像头的图像数据
            imageReader = ImageReader.newInstance(largest.getWidth(), largest.getHeight(), ImageFormat.JPEG, 2);
            imageReader.setOnImageAvailableListener(reader -> {
                //当照片数据可用时激发该方法
                //获取捕获的照片数据
                Image image = reader.acquireLatestImage();
                ByteBuffer buffer = image.getPlanes()[0].getBuffer();
                byte[] bytes = new byte[buffer.remaining()];
                File file = new File(getExternalFilesDir(null), "pic.jpg");
                buffer.get(bytes);
                try (FileOutputStream output = new FileOutputStream(file)){
                    output.write(bytes);
                    Toast.makeText(cameraActivity.this,"保存："+file,Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    image.close();
                }
            },null);
            //获取最佳的预览尺寸
            previewSize = chooseOptimalSize(map.getOutputSizes(SurfaceTexture.class),width,height,largest);
            //根据选中的预览尺寸来调整预览组件（TextureView）的长宽比
            int orientation = getResources().getConfiguration().orientation;
            if(orientation== Configuration.ORIENTATION_LANDSCAPE){
                textureView.setAspectRatio(previewSize.getWidth(),previewSize.getHeight());
            }else{
                textureView.setAspectRatio(previewSize.getHeight(),previewSize.getWidth());
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }catch (NullPointerException e){
            System.out.println("出现错误");
        }
    }

    //根据摄像头的方向和屏幕方向设置预览的方向以及尺寸
    private Size chooseOptimalSize(Size[] choices, int width, int height, Size aspectRatio) {
        //收集摄像头支持的大过预览Surface的分辨率
        List<Size> bigEnough = new ArrayList<>();
        int w = aspectRatio.getWidth();
        int h = aspectRatio.getHeight();
        for (Size option : choices) {
            if (option.getHeight() == option.getWidth() * h / w && option.getWidth() >= width && option.getHeight() >= height) {
                bigEnough.add(option);
            }
        }
        //如果找到多个预览尺寸，获取其中面积最小的
        if(bigEnough.size()>0){
            return Collections.min(bigEnough,new CompareSizesByArea());
        }else{
            System.out.println("找不到合适的尺寸");
            return choices[0];
        }
    }

    @Override
    public void onClick(View view) {
       switch (view.getId()){
           case R.id.btn_change_camera:
//               mCameraId = mCameraId=="0"?"1":"0";
               break;
           case R.id.btn_close_camera:
               break;
           case R.id.btn_open_camera:
               break;
       }

    }
}