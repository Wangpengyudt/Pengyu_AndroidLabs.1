package algonquin.cst2355.wang0532;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Executable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * this page lets the user type in a password
 * author: Pengyu Wang
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {
    float oldSize = 14;

    /**this textView shows the feedback */
    TextView feedbackText;
    /** this is the loginButton at the bottom of the screen*/
    Button loginButton;
    /** */
    EditText passwordText;
    String serverUrl = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=7e943c97096a9784391a981c4d878b22&Units=metric";
    Toolbar myToolbar;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myToolbar = findViewById(R.id.toolbar);


        loginButton = findViewById(R.id.mybutton);
        passwordText = findViewById(R.id.editTextTextPassword);
        feedbackText = findViewById(R.id.textview);

        setSupportActionBar(myToolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, myToolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navView = findViewById(R.id.popout_menu);
        navView.setNavigationItemSelectedListener((item -> {
            switch (item.getItemId()){
                case R.id.show_edit:
                    passwordText.setVisibility(View.VISIBLE);
                    break;
                case R.id.hide_edit:
                    passwordText.setVisibility(View.INVISIBLE);
                    break;

            }
            drawer.closeDrawer(GravityCompat.START);
            return false;
        }));

        BottomNavigationView bottomView = findViewById(R.id.bottomMenu);
        bottomView.setOnItemSelectedListener((item -> {
            switch (item.getItemId()){
                case R.id.show_edit:
                    passwordText.setVisibility(View.VISIBLE);
                    break;
                case R.id.hide_edit:
                    passwordText.setVisibility(View.INVISIBLE);
                    break;
            }
            return  false;
        }));

        loginButton.setOnClickListener((click) -> {
            String cityName = passwordText.getText().toString();

            //must be done on other thread
            Executor newThread = Executors.newSingleThreadExecutor();
            //done on the second processor
            newThread.execute( () ->{
                try {
                    //encode the string
                String fullUrl = String.format(serverUrl, URLEncoder.encode(cityName, "UTF-8"));

                URL url = new URL(String.format(fullUrl));//build the connection
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();//connect to server
                InputStream in = new BufferedInputStream(urlConnection.getInputStream()); //read the information

                    String jsonString = (new BufferedReader(
                            new InputStreamReader(in, StandardCharsets.UTF_8)
                    )).lines()
                            .collect(Collectors.joining("\n"));
                    //the whole JSON object
                    JSONObject theDocument = new JSONObject(jsonString);
                    //the small JSON object which key is "main"
                    JSONObject mainObj = theDocument.getJSONObject("main");
                    //get the value which key is "temp" from the JSON object mainObj
                    double temp = mainObj.getDouble("temp");
                    //get an json array
                    JSONArray weatherArray = theDocument.getJSONArray("weather");
                    //get the json object at the json array position 0
                    JSONObject pos0Obj = weatherArray.getJSONObject(0);
                    //get data from json object
                    String icon = pos0Obj.getString("icon");
                    //can only setText on GUI thread;
                    //;
                    runOnUiThread( () ->{
                        //running on the GUI threa
                        feedbackText.setText("Temperature is : " + temp);
                    });

                }
                catch (JSONException je){
                  Log.e("JSONException", je.getMessage());
                }
                catch (IOException ioe){
                  Log.e("IOException", ioe.getMessage());
                }
            });   //end of the newThread.execute()



//            if(checkPasswowrdComplexity(searchWord)){
//                feedbackText.setText("Your password is complex enough");
//            }
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

            switch (item.getItemId()){
                case R.id.hide_views:
                    loginButton.setVisibility(View.INVISIBLE);
                    break;
                case R.id.refresh:
                    loginButton.setVisibility(View.VISIBLE);
                    break;
                case R.id.increase:
                    oldSize++;
                    passwordText.setTextSize(oldSize);
                    break;
                case R.id.decrease:
                    oldSize--;
                    passwordText.setTextSize(oldSize);
                    break;
            }

        return super.onOptionsItemSelected(item);
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




