package com.cst2335.profileactivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "PROFILE_ACTIVITY";
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    // ImageButton click listener to launch the camera
    public void launchCamera(View view) {
        Log.e(TAG, "In function: launchCamera");
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            myPictureTakerLauncher.launch(takePictureIntent);
        }
    }

    // ActivityResultLauncher for handling the camera result
    private final ActivityResultLauncher<Intent> myPictureTakerLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        Log.e(TAG, "In function: onActivityResult");
                        if (result.getResultCode() == RESULT_OK) {
                            Intent data = result.getData();
                            Bitmap imgBitmap = (Bitmap) data.getExtras().get("data");
                            // Do something with the captured image
                        } else if (result.getResultCode() == RESULT_CANCELED) {
                            Log.i(TAG, "User refused to capture a picture.");
                        }
                    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG, "In function: onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set text for TextViews
        TextView profileText = findViewById(R.id.textViewProfile);
        profileText.setText(R.string.profile_text);

        TextView nameText = findViewById(R.id.textViewName);
        nameText.setText(R.string.your_name);

        TextView emailText = findViewById(R.id.textViewEmail);
        emailText.setText(R.string.your_email);

        TextView pictureText = findViewById(R.id.textViewPicture);
        pictureText.setText(R.string.your_picture);

        TextView clickText = findViewById(R.id.textViewClick);
        clickText.setText(R.string.click_button_to_take_picture);
    }

    @Override
    protected void onStart() {
        Log.e(TAG, "In function: onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.e(TAG, "In function: onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.e(TAG, "In function: onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.e(TAG, "In function: onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.e(TAG, "In function: onDestroy");
        super.onDestroy();
    }
}
