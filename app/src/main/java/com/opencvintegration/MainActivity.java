package com.opencvintegration;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ImageView imageView;

    static {
        try {
            OpenCVLoader.initDebug();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("verify",String.valueOf(OpenCVLoader.initDebug()));

        imageView = findViewById(R.id.iv_pic);
        imageView.setImageDrawable(getDrawable(R.drawable.images));

        findViewById(R.id.btn_gray_scale).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertToGrayScale();
            }
        });
    }

    private void convertToGrayScale(){
        try {
            Mat img = Utils.loadResource(this, R.drawable.images);
            Mat gryimg =  new Mat(img.size(), CvType.CV_8U);
            Imgproc.cvtColor(img, gryimg, Imgproc.COLOR_RGB2GRAY);
            Bitmap bm = Bitmap.createBitmap(gryimg.cols(), gryimg.rows(), Bitmap.Config.ARGB_8888);
            Utils.matToBitmap(gryimg, bm);
            imageView.setImageBitmap(bm);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}

//https://medium.com/beesightsoft/setup-opencv-android-without-install-opencv-manager-apk-bea6acf9e2c1