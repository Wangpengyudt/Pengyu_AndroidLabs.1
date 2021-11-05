package algonquin.cst2355.wang0532;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

/**
 * this page lets the user type in a password
 * author: Pengyu Wang
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    /**this textView shows the feedback */
    TextView feedbackText;
    /** this is the loginButton at the bottom of the screen*/
    Button loginButton;
    /** */
    EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.mybutton);
        passwordText = findViewById(R.id.editTextTextPassword);
        feedbackText = findViewById(R.id.textview);

        loginButton.setOnClickListener((click) -> {
            String password = passwordText.getText().toString();

            if(checkPasswowrdComplexity(password)){
                feedbackText.setText("Your password is complex enough");
            }
        });




    }
    /**this function checks if the password has upper case, lower case, number, and special character.
     *
     * @param password non-null string of the user typed in the edittext.
     * @return True if all conditions are met, falses otherwise
     */

    private boolean checkPasswowrdComplexity(@NonNull String password) {
        boolean foundUpperCase, foundLowerCase, foundNumber, foundSpecial;
        foundUpperCase = foundLowerCase = foundNumber = foundSpecial = false;

        //start looping;
        for (int i = 0; i < passwordText.length(); i++) {

            char c = password.charAt(i);
            Log.i("Looking at char:", "" + c);
            if (Character.isLowerCase(c))
                foundLowerCase = true;
            else if (isSpecialCharacter(c)) {
                foundSpecial = true;


            }


        }



        if(!foundLowerCase)
            Toast.makeText( MainActivity.this, "Missing lower case character", Toast.LENGTH_LONG).show();
        if(!foundSpecial)
            passwordText.setError("Missing special character");

        //if anything is false, then it is not in the password;
        return foundLowerCase && foundSpecial;   //&& foundNumber && foundUpperCase;
    }

    /**this return true if c is one of (#%$^@!?), false otherwise
         *
         * @param c the character we are checking
         * @return true if c is one of (#%$^@!?), false otherwise
         */
        private boolean isSpecialCharacter(char c) {
            switch (c){
                case '#':
                    return true;
                    //break;
                case '$':
                    return true;
                    //break;
                case '%':
                    return true;
                    //break;
                case '^':
                    return true;
                    //break;
                case '&':
                    return true;
                    //break;
                case '*':
                    return true;
                    //break;
                case '@':
                    return true;
                    //break;
                case '!':
                    return true;
                    //break;
                case '?':
                    return true;
                    //break;// unreachable code because you return above
                default:
                    return false;

            }
        }
//        TextView mytext = findViewById(R.id.textview);
//        Button myButton = findViewById(R.id.mybutton);
        //EditText myedit = findViewById(R.id.myedittext);
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


//        Button myCheckBoxButton2 = findViewById(R.id.myCheckBoxbutton2);
//        myCheckBoxButton2.setText("CheckBox2");
//        myCheckBoxButton2.setOnClickListener(   vw  ->  mytext.setText("CheckBox2"));
//
//        Button mySwitch1 = findViewById(R.id.mySwitch1);
//        mySwitch1.setText("Switch 1");
//        mySwitch1.setOnClickListener(   vw  ->  mytext.setText("Switch1"));
//
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
//
//        Context context = getApplicationContext();
//        CharSequence text = "Hello toast!";
//        int duration = Toast.LENGTH_LONG;

//        Toast toast = Toast.makeText(context, text, duration);
//        toast.show();
//
//        ImageView myimage = findViewById(R.id.myImage);
//
//        ImageButton imgbtn = findViewById( R.id.myImagebutton );



//        Context context1 = getApplicationContext();
//        CharSequence text1 = "The width = " + imgbtn.getWidth() + " and height = " + imgbtn.getHeight();
//        imgbtn.setOnClickListener(   vw  ->  mytext.setText(text1));
//        int duration1 = Toast.LENGTH_LONG;

    }




