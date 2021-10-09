package algonquin.cst2355.wang0532;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class SecondActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
//        Button loginButton = findViewById(R.id.button);
//        loginButton.setOnClickListener( clk-> { } );
//        Intent nextPage = new Intent( SecondActivity.this, MainActivity.class);

//        EditText phone_number = findViewById(R.id.editTextPhone);  //find edittext by id
//
//        Intent call = new Intent(Intent.ACTION_DIAL);    //build-in function
//
//        call.putExtra("phoneNumber", phone_number.getText().toString());    //get content of edittext
//
//        Button callButton = findViewById(R.id.button2);   //find the call button
//        call.setData(Uri.parse("tel" + phone_number));
//
//        callButton.setOnClickListener(clk ->startActivity(call));
        // startActivity(nextPage);

        EditText tel = (EditText) findViewById(R.id.editTextPhone);
        String phoneNumber_pre = tel.getText().toString();

        //Intent call = new Intent(Intent.ACTION_DIAL);
        //call.putExtra("PhoneNumber", phoneNumber_pre);

        //String phoneNumber = call.getStringExtra("PhoneNumber");
        //call.putExtra("PhoneNumber", phoneNumber);


        //call.setData(Uri.parse("tel:" + phoneNumber_pre));
        //call.setData(Uri.parse("tel:" + 123));
        //call.setData(Uri.parse("phoneNumber:" + phoneNumber));
        Intent nextPage2 = new Intent(SecondActivity.this, ThirdActivity.class);
        Button callButton = findViewById(R.id.button2);
        nextPage2.putExtra("PhoneNumber", phoneNumber_pre);
        callButton.setOnClickListener(clk -> startActivity(nextPage2));
        //String phoneNumber = call.getStringExtra("PhoneNumber");
        //call.setData(Uri.parse("PhoneNumber:" + phoneNumber_pre));

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Button camButton = findViewById(R.id.button3);
        camButton.setOnClickListener(clk -> startActivity(cameraIntent));
        ActivityResultLauncher<Intent> cameraResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();

                            Bitmap thumbnail = data.getParcelableExtra("data");
                            //     profileImage.setImageBitmap(thumbnail);
                        }
                        else if(result.getResultCode() == Activity.RESULT_CANCELED)
                            Log.i("Got bitmap", "User refused the image");
                    }
                });

        //startActivity(cameraIntent);

    }}
//        Intent nextPage = new Intent( MainActivity.this, SecondActivity.class);
