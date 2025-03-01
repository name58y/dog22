package separate;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dog.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentCamera#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCamera extends FragmentHome {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentCamera() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentCamera.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentCamera newInstance(String param1, String param2) {
        FragmentCamera fragment = new FragmentCamera();
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
        return inflater.inflate(R.layout.fragment_camera, container, false);
    }

    public class ResultActivity extends AppCompatActivity {
        private static final int REQUEST_IMAGE_CAPTURE = 1;
        TextView result, confidence;
        ImageView photoImageView;

        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.fragment_camera);

            result = findViewById(R.id.itCamera);
            confidence = findViewById(R.id.confidence);
            photoImageView = findViewById(R.id.photoImageView);
            //Button retakeButton = findViewById(R.id.btnCamera);
            Button backToMainButton = findViewById(R.id.btnBackToMain);

            String breed = getIntent().getStringExtra("breed");
            String confidenceText = getIntent().getStringExtra("confidence");
            Bitmap image = getIntent().getParcelableExtra("image");
            if (result != null) {
                result.setText(breed);
            }
            if (confidence != null) {
                confidence.setText(confidenceText);
            }
            if (image != null) {
                // กำหนดภาพให้กับ ImageView
                photoImageView.setImageBitmap(image);
            }

            backToMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Navigate back to MainActivity
            Intent intent = new Intent(com.example.dog.FragmentCamera.this, MainActivity.class);
            startActivity(intent);
            }
            });
        }
    }

}
