package separate;

import static androidx.core.content.ContextCompat.startActivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dog.R;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.BreakIterator;

public class FragmentHome extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentHome() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public FragmentHome newInstance(String param1, String param2) {
        FragmentHome fragment = new FragmentHome();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
         if (requestCode == FRSGMENT_HOME_CAPTURE && resultCode == FRSGMENT_OK) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            int dimension = Math.min(image.getWidth(), image.getHeight());
            image = ThumbnailUtils.extractThumbnail(image, dimension, dimension);
            imageView.setImageBitmap(image);


            image = Bitmap.createScaledBitmap(image, imageSize, imageSize, false);


            BreakIterator result;
            DogBreedUtil.processImageAndDisplayResults(this, image, result, confidence);


            String breed = result.getText().toString();
            String confidenceText = confidence.getText().toString();


            // ส่งภาพและข้อมูลไปยัง ResultActivity
            Intent intent = new Intent(FragmentHome.this, FragmentHome.class);
            intent.putExtra("breed", breed);
            intent.putExtra("confidence", confidenceText);
            intent.putExtra("image", image);
            startActivity(intent);
        }
        super.notifyAll(requestCode, resultCode, data);
    }

