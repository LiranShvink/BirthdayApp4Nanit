package com.appandgo.birthday.Activities;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.appandgo.birthday.R;

import static com.appandgo.birthday.Activities.MainActivity.BITMAP_RESAULT;

/**
 * Created by liran on 27/4/2018.
 */

public class CaptureImageActivity extends AppCompatActivity {

    //keep track of camera capture intent
    public final static int CAMERA_CAPTURE = 1;
    //keep track of cropping intent
    public final static int GALLERY_CAPTURE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selectImage();
        Log.d("test", "test");
    }

    private void selectImage() {
        final CharSequence[] items = {getString(R.string.camera_capture), getString(R.string.gallery_capture),
                getString(R.string.cancel)};

        TextView title = new TextView(this);
        title.setText(getString(R.string.select_image_title));
        title.setBackgroundColor(Color.BLACK);
        title.setPadding(10, 15, 15, 10);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.WHITE);
        title.setTextSize(22);


        AlertDialog.Builder builder = new AlertDialog.Builder(
                CaptureImageActivity.this);


        builder.setCustomTitle(title);

        // builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals(getString(R.string.camera_capture))) {
                    try {
                        //use standard intent to capture an image
                        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        //we will handle the returned data in onActivityResult
                        startActivityForResult(captureIntent, CAMERA_CAPTURE);
                    } catch (ActivityNotFoundException anfe) {
                        //display an error message
                        String errorMessage = "device don't support capturing images!";
                        Toast toast = Toast.makeText(CaptureImageActivity.this, errorMessage, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    dialog.dismiss();

                } else if (items[item].equals(getString(R.string.gallery_capture))) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, "Select Picture"),
                            GALLERY_CAPTURE);
                    dialog.dismiss();

                } else if (items[item].equals(getString(R.string.cancel))) {
                    dialog.dismiss();
                    finish();
                }
            }
        });
        builder.show();
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && data.getExtras() != null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            Intent resultIntent = new Intent();

            if (requestCode == CAMERA_CAPTURE) {
                //get the Uri for the captured image
                resultIntent.putExtra("data", bitmap);
            } else if (requestCode == GALLERY_CAPTURE) {
                Uri selectedImage = data.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = CaptureImageActivity.this.getContentResolver().query(
                        selectedImage, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                bitmap = (BitmapFactory.decodeFile(picturePath));
                resultIntent.putExtra("data", bitmap);
            }

            //get the returned data
            setResult(BITMAP_RESAULT, resultIntent);
            finish(); // close the activity
        } else {
            finish();

        }
    }

}
