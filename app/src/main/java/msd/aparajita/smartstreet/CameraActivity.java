package msd.aparajita.smartstreet;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import msd.com.smartstreet.R;

public class CameraActivity extends AppCompatActivity {

    private static final int PHOTO_TAKEN = 0;
  //  private static final int REQUEST_VIDEO_CAPTURE = 1;
    private static final int BROWSE_GALLERY_REQUEST = 1;
    int REQUEST_CAMERA = 0;
    Uri videoUri;
    private File imageFile, mediaFile;
    private Button galleryButton;

    /**
     * This method calls the methods for photo, audio, video and brows gallery
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (hasCamera())
        {
            addSnapButtonListener();
            //dispatchTakeVideoIntent();
            browswGallery();
            //recordAudio();
        } else
            {
            Toast.makeText(this, "No camera found ", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



    /**
     * This method browses the gallery
     */
    private void browswGallery() {
        galleryButton = (Button) findViewById(R.id.gallery);
        galleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, BROWSE_GALLERY_REQUEST);

            }
        });

    }

    /**
     * This method checks if the device has camera or not
     * @return true or false depending on the availability of the camera
     */
    private boolean hasCamera() {
        if (getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA_FRONT)) {
            return true;
        }
        else {
            return false;
        }
        }



    /**
     * Listener method for the Photo button
     */
    private void addSnapButtonListener() {
        Button snapButton = (Button) findViewById(R.id.snap);
        snapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String imageFileName = "JPEG_" + timeStamp + "_";
                File storageDir = Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES);

                try {
                    imageFile = File.createTempFile(imageFileName, ".jpg", storageDir);
                    Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(i, REQUEST_CAMERA);
                    i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile));
                    i.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                    startActivityForResult(i, PHOTO_TAKEN);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
    }



    /**
     * This method is called upon after some activity
     * @param requestCode
     * @param resultCode
     * @param data
     */




}
