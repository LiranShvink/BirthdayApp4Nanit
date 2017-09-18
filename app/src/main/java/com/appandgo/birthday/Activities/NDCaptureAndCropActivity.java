package com.appandgo.birthday.Activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.io.IOException;

import static com.appandgo.birthday.Activities.NDMainActivity.BITMAP_RESAULT;

/**
 * Created by naor on 15/09/2017.
 */

public class NDCaptureAndCropActivity extends AppCompatActivity {

    //keep track of camera capture intent
    final int CAMERA_CAPTURE = 1;
    //keep track of cropping intent
    final int PIC_CROP = 2;

    //captured picture uri
    private Uri imgUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            //use standard intent to capture an image
            Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //we will handle the returned data in onActivityResult
            startActivityForResult(captureIntent, CAMERA_CAPTURE);
        } catch (ActivityNotFoundException anfe) {
            //display an error message
            String errorMessage = "Whoops - your device doesn't support capturing images!";
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {


            if (requestCode == CAMERA_CAPTURE)
            {
                //get the Uri for the captured image
                imgUri = data.getData();
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                Intent resultIntent = new Intent();
                resultIntent.putExtra("data", bitmap);
                setResult(BITMAP_RESAULT, resultIntent);
                finish();
//                performCrop();
            }
            else if(requestCode == PIC_CROP)
            {
                //get the returned data
                Bundle extras = data.getExtras();
                //get the cropped bitmap
                Bitmap bitmap = extras.getParcelable("data");
                Intent resultIntent = new Intent();
                resultIntent.putExtra("data", bitmap);
                setResult(BITMAP_RESAULT, resultIntent);
                finish(); // close the activity
            }
        }
        else
        {
            finish();
        }
    }

    private void performCrop()
    {
        try {
            //call the standard crop action intent (the user device may not support it)
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            //indicate image type and Uri
            cropIntent.setDataAndType(imgUri, "image/*");
            //set crop properties
            cropIntent.putExtra("crop", "true");
            //indicate aspect of desired crop
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
            //indicate output X and Y
            cropIntent.putExtra("outputX", 500);
            cropIntent.putExtra("outputY", 500);
            //retrieve data on return
            cropIntent.putExtra("return-data", true);
            //start the activity - we handle returning in onActivityResult
            startActivityForResult(cropIntent, PIC_CROP);
        }
        catch(ActivityNotFoundException anfe){
            //display an error message
            String errorMessage = "Whoops - your device doesn't support the crop action!";
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

}
