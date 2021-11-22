package algonquin.cst2355.wang0532;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

//like an activity, but doesn't need to coever whole screen
public class MessageListFragment extends Fragment {
    //has onCreate, onStart, onResume, onPause, onStop, onDestroy
    //onCreateView --load the XML layout, onAttach, onDetach


    //
    Button submit;
    //    Button buttonReceive;
    EditText edit;
    RecyclerView rView;
    //ChatRoom.MyAdapter theAdapter;
    MyOpenHelper myOpenHelper;
    SQLiteDatabase theDatabase;
    MyAdapter theAdapter;
    ArrayList<Message> messages = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View chatLayout = inflater.inflate(R.layout.chatroom, container, false);

        submit = chatLayout.findViewById(R.id.submitButton);  //send button
//
//        buttonReceive = findViewById(R.id.buttonReceive); //receive button
//
        edit = chatLayout.findViewById(R.id.editText);  //edit text
//
        rView = chatLayout.findViewById(R.id.myRecyclerView);  //recycler view

        //RecyclerView theList = chatLayout.findViewById(R.id.myRecyclerView);
        //get the button, editText, and recyclerView





        myOpenHelper = new MyOpenHelper(getContext());

        theDatabase = myOpenHelper.getWritableDatabase();


        //load from the database:
        Cursor results = theDatabase.rawQuery("Select * from " + MyOpenHelper.TABLE_NAME + ";", null);

        //convert column names to indices:
        int idIndex = results.getColumnIndex(MyOpenHelper.COL_ID);
        int messageIndex = results.getColumnIndex(MyOpenHelper.COL_MESSAGE);
        int sOrRIndex = results.getColumnIndex(MyOpenHelper.COL_SEND_RECEIVE);
        int timeIndex = results.getColumnIndex(MyOpenHelper.COL_TIME_SENT);

        //cursor is pointing to row - 1
        while (results.moveToNext())
        {
            //point to row 0
            int id = results.getInt(idIndex);
            String message = results.getString(messageIndex);
            int sendOrReceive = results.getInt(sOrRIndex);
            String time = results.getString(timeIndex);

            //add to arraylist
            messages.add(new Message(message, time, id));
        }



        submit.setOnClickListener(click ->{
            String whatIsTyped = edit.getText().toString();
            Date timeNow = new Date();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());

            String currentDateandTime = sdf.format( timeNow );
            ContentValues newRow = new ContentValues();




            newRow.put(MyOpenHelper.COL_MESSAGE, whatIsTyped);
            newRow.put(MyOpenHelper.COL_SEND_RECEIVE, 1);
            newRow.put(MyOpenHelper.COL_TIME_SENT, currentDateandTime);

            long id = theDatabase.insert( MyOpenHelper.TABLE_NAME, MyOpenHelper.COL_SEND_RECEIVE, newRow);  //returns the id
            //adding a new message to our history
            Message cm = new Message(whatIsTyped, currentDateandTime, id);
            messages.add( cm );//what is the database id?

            edit.setText("");//clear the text
            theAdapter.notifyItemInserted(messages.size() - 1);




            //send or receive column


            //now that column are full, you insert



        });
        theAdapter = new MyAdapter();
        rView.setAdapter(theAdapter);
        rView.setLayoutManager(new LinearLayoutManager(getContext()));
        //return super.onCreateView(inflater, container, savedInstanceState);
        return chatLayout;
    }
//    }
//
        public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

            @Override
            public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                LayoutInflater li = getLayoutInflater();
                View thisRow = li.inflate(R.layout.sent_message, parent, false);

                return new MyViewHolder( thisRow );
            }



            @Override
            public void onBindViewHolder(MyViewHolder holder, int position) {//need an arraylist to hold all the message.

                //what message object is at position
                Message thisRow = messages.get(position);

                holder.timeView.setText(thisRow.getTimeSent());
                holder.messageView.setText(thisRow.getMessageTyped());

            }

            public int getItemViewType(int position){
                return 5;
                //messages.get(position).getSentOrReceived();

            }

            @Override
            public int getItemCount() {
                return messages.size();
            }
        }

        public class MyViewHolder extends RecyclerView.ViewHolder{

            TextView timeView;
            TextView messageView;


            public MyViewHolder(View itemView){
                super(itemView);
                itemView.setOnClickListener( click ->{
                    ChatRoom parentActivity = (ChatRoom)getContext();
                    int position = getAbsoluteAdapterPosition();

                    parentActivity.userClickedMessage(messages.get(position), position);//tell which message was clicked
                    //only activities can load fragment
                    //deletion code

                    /*
                    int position = getAbsoluteAdapterPosition();

                    Message whatWasClicked = messages.get(position);
                            //which activity loaded the fragment?
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Question:");
                    builder.setMessage("Do you want to delete this:" + whatWasClicked.getMessageTyped())
                            .setNegativeButton("Negative", (dialog, click1)->{})
                            .setPositiveButton("Positive", (dialog, click2)->{})
                            .setNeutralButton("Neutral", (dialog, click3)->{}).create().show();
                    messages.remove(position);
                    theAdapter.notifyItemRemoved(position);
                    Snackbar.make(submit, "You removed itme#" + position, Snackbar.LENGTH_LONG)
                            .setAction("Undo", (click4)->{
                                messages.add(position, whatWasClicked);
                                theAdapter.notifyItemInserted(position);
                                theDatabase.execSQL(String.format("Insert into %s value( \"%d\", \"%s\", \"%d\", \"%s\" );"
                               , MyOpenHelper.TABLE_NAME,   whatWasClicked.getId(), whatWasClicked.getMessageTyped(), 1, whatWasClicked.getTimeSent()));

                            })
                            .show();
                    theDatabase.delete(MyOpenHelper.TABLE_NAME, MyOpenHelper.COL_ID + "=?", new String[]{Long.toString(whatWasClicked.getId())});
*/

                });

                timeView = itemView.findViewById(R.id.time);
                messageView = itemView.findViewById(R.id.message);

            }

        }



    public class Message{
        String messageTyped;
        String timeSent;
        long id;

        public Message(String messageTyped, String timeSent, long _id) {
            this.messageTyped = messageTyped;
            this.timeSent = timeSent;
            id = _id;

        }

        public String getMessageTyped() {
            return messageTyped;
        }

        public String getTimeSent() {
            return timeSent;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

    }

}
