package algonquin.cst2355.wang0532;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onStart() {      //the application is now visible on screen

        super.onStart();
        Log.w( "MainActivity", "In onStart() - Loading Widgets" );    //copy from week4
    }

    @Override
    protected void onResume() {    //the application is now responding to user input
        super.onResume();
        Log.w( "MainActivity", "In onResume() - Loading Widgets" );    //copy from week4
    }

    @Override
    protected void onPause() {   //the application no longer responds to user input
        super.onPause();
        Log.w( "MainActivity", "In onPause() - Loading Widgets" );    //copy from week4
    }

    @Override
    protected void onStop() {   //the application is no longer visible
        super.onStop();
        Log.w( "MainActivity", "In onStop() - Loading Widgets" );    //copy from week4
    }

    @Override
    protected void onDestroy() {  //any memory used by the application is freed
        super.onDestroy();
        Log.w( "MainActivity", "In onDestroy() - Loading Widgets" );    //copy from week4
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.w( "MainActivity", "In onCreate() - Loading Widgets" );    //copy from week4

        Button loginButton = findViewById(R.id.button);   //get login button
        Intent nextPage = new Intent( MainActivity.this,SecondActivity.class);    //transit first page to second page
        EditText email = findViewById(R.id.editTextTextEmailAddress);
        nextPage.putExtra("EmailAddress", email.getText().toString());

        loginButton.setOnClickListener(clk ->{startActivity(nextPage);});    //lab4   add listener for button,
        //nextPage.getStringExtra("EmailAddress");

//        Intent nextPage = new Intent( MainActivity.this, SecondActivity.class);
//
//        startActivity(nextPage);


//        TextView mytext = findViewById(R.id.textview);
//        Button myButton = findViewById(R.id.mybutton);
//        EditText myedit = findViewById(R.id.myedittext);
//        myButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String editString = myedit.getText().toString();
//                mytext.setText("Your edit text has:" + editString);
//            }
//        });

//        Button myCheckBoxButton1 = findViewById(R.id.myCheckBoxbutton1);
//        myCheckBoxButton1.setText("CheckBox1");
//        myCheckBoxButton1.setOnClickListener(   vw  ->  mytext.setText("CheckBox1"));
//
//
//        Button myCheckBoxButton2 = findViewById(R.id.myCheckBoxbutton2);
//        myCheckBoxButton2.setText("CheckBox2");
//        myCheckBoxButton2.setOnClickListener(   vw  ->  mytext.setText("CheckBox2"));
//
//        Button mySwitch1 = findViewById(R.id.mySwitch1);
//        mySwitch1.setText("Switch 1");
//        mySwitch1.setOnClickListener(   vw  ->  mytext.setText("Switch1"));

//        Button mySwitch2 = findViewById(R.id.mySwitch2);
//        mySwitch2.setText("Switch 2");
//        mySwitch2.setOnClickListener(   vw  ->  mytext.setText("Switch2"));
//
//        Button option1 = findViewById(R.id.myRadioButton1);
//        option1.setText("Option1");
//        option1.setOnClickListener(   vw  ->  mytext.setText("Option1"));
//
//        Button option2 = findViewById(R.id.myRadioButton2);
//        option2.setText("Option2");
//        option2.setOnClickListener(   vw  ->  mytext.setText("Option2"));

        Context context = getApplicationContext();
        CharSequence text = "Hello toast!";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

//        ImageView myimage = findViewById(R.id.myImage);
//
//        ImageButton imgbtn = findViewById( R.id.myImagebutton );



        Context context1 = getApplicationContext();
//        CharSequence text1 = "The width = " + imgbtn.getWidth() + " and height = " + imgbtn.getHeight();
//        imgbtn.setOnClickListener(   vw  ->  mytext.setText("The width = " + imgbtn.getWidth() + " and height = " + imgbtn.getHeight()));
        int duration1 = Toast.LENGTH_LONG;

    }
}
