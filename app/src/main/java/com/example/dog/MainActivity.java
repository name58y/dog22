package com.example.dog;

import static com.example.dog.R.*;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;




import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;




import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;


import com.example.dog.ml.Model1;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;




import android.widget.ScrollView;


import adapter.AdapterViewPager;
import separate.FragmentCamera;
import separate.FragmentDog1;
import separate.FragmentHome;



public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView imageView;
    Button picture;
    TextView result, confidence;
    int imageSize = 224;


    ViewPager2 pangerMain;
    ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    BottomNavigationView bottomnav;



    private int selectedTab = 1;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //แถบ
        fragmentArrayList.add(new FragmentHome());
        fragmentArrayList.add(new FragmentCamera());
        fragmentArrayList.add(new FragmentDog1());


        AdapterViewPager adapterViewPager = new AdapterViewPager(this, fragmentArrayList);
        pangerMain.setAdapter(adapterViewPager);
        pangerMain.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        bottomnav.setSelectedItemId(R.id.itHome);
                        break;
                    case 1:
                        bottomnav.setSelectedItemId(R.id.itCamera);
                        break;
                    case 2:
                        bottomnav.setSelectedItemId(R.id.itDog1);
                        break;
                }

                super.onPageSelected(position);
            }
        });

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            int dimension = Math.min(image.getWidth(), image.getHeight());
            image = ThumbnailUtils.extractThumbnail(image, dimension, dimension);
            imageView.setImageBitmap(image);

            image = Bitmap.createScaledBitmap(image, imageSize, imageSize, false);
            Imagedog(image);

            String breed = result.getText().toString();
            String confidenceText = confidence.getText().toString();

            // ส่งภาพและข้อมูลไปยัง ResultActivity
            Intent intent = new Intent(MainActivity.this, FragmentHome.class);
            intent.putExtra("breed", breed);
            intent.putExtra("confidence", confidenceText);
            intent.putExtra("image", image);
            startActivity(intent);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}








