package algonquin.cst2355.wang0532;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


//1080*2220
//width height
//2220*1080
//height*width
//smallest is width
public class ChatRoom extends AppCompatActivity {
//  ArrayList<Message> messages = new ArrayList<>();
////
//       Button submit;
////    Button buttonReceive;
//      EditText edit;
//      RecyclerView rView;
//      MyAdapter theAdapter;
//      MyOpenHelper myOpenHelper;
//        SQLiteDatabase theDatabase;





    boolean isTablet = false;
    @Override
    protected void onCreate(Bundle previousInstanceState) {
        super.onCreate(previousInstanceState);
        setContentView(R.layout.empty_layout); //loads the FrameLayout
        isTablet = findViewById(R.id.detailsRoom) != null;

        MessageListFragment chatFragment = new MessageListFragment();//create fragment object
        //load XML just a FrameLayout for loading fragment:

//
//
        FragmentManager fMgr = getSupportFragmentManager();
//
        FragmentTransaction tx = fMgr.beginTransaction();
//        //load the fragment:
        tx.add(R.id.fragmentRoom, chatFragment);//where to load it? load chatFragment into framLayout with id fragmentRoom
        tx.addToBackStack(null);//change what the back arrow does
        tx.commit();//now load it

    }
    public void userClickedMessage(MessageListFragment.Message message, int position){
        MessageDetailsFragment details = new MessageDetailsFragment(message, position);

        if(isTablet){
            FragmentManager fMgr = getSupportFragmentManager();
//
            FragmentTransaction tx = fMgr.beginTransaction();
//        //load the fragment:
            tx.replace(R.id.detailsRoom, details);//where to load it? load chatFragment into framLayout with id fragmentRoom
            //tx.addToBackStack(null);//change what the back arrow does
            tx.commit();//now load it
        }
        else{
            FragmentManager fMgr = getSupportFragmentManager();
//
            FragmentTransaction tx = fMgr.beginTransaction();
//        //load the fragment:
            tx.add(R.id.fragmentRoom, details);//where to load it? load chatFragment into framLayout with id fragmentRoom
            tx.addToBackStack(null);//change what the back arrow does
            tx.commit();//now load it
        }


    }
//        myOpenHelper = new MyOpenHelper(this);
//
//         theDatabase = myOpenHelper.getWritableDatabase();
//
//
//        //load from the database:
//        Cursor results = theDatabase.rawQuery("Select * from " + MyOpenHelper.TABLE_NAME + ";", null);
//
//        //convert column names to indices:
//        int idIndex = results.getColumnIndex(MyOpenHelper.COL_ID);
//        int messageIndex = results.getColumnIndex(MyOpenHelper.COL_MESSAGE);
//        int sOrRIndex = results.getColumnIndex(MyOpenHelper.COL_SEND_RECEIVE);
//        int timeIndex = results.getColumnIndex(MyOpenHelper.COL_TIME_SENT);
//
//        //cursor is pointing to row - 1
//        while (results.moveToNext())
//        {
//            //point to row 0
//            int id = results.getInt(idIndex);
//            String message = results.getString(messageIndex);
//            int sendOrReceive = results.getInt(sOrRIndex);
//            String time = results.getString(timeIndex);
//
//            //add to arraylist
//            messages.add(new Message(message, time, id));
//        }
//        submit = findViewById(R.id.submitButton);  //send button
////
////        buttonReceive = findViewById(R.id.buttonReceive); //receive button
////
//        edit = findViewById(R.id.editText);  //edit text
////
//        rView = findViewById(R.id.myRecyclerView);  //recycler view
//
//        theAdapter = new MyAdapter();
//        rView.setAdapter(theAdapter);
//        rView.setLayoutManager(new LinearLayoutManager(this));
//
//
//
//
//
//
//        submit.setOnClickListener(click ->{
//            String whatIsTyped = edit.getText().toString();
//            Date timeNow = new Date();
//
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
//
//            String currentDateandTime = sdf.format( timeNow );
//            ContentValues newRow = new ContentValues();
//
//
//
//
//            newRow.put(MyOpenHelper.COL_MESSAGE, whatIsTyped);
//            newRow.put(MyOpenHelper.COL_SEND_RECEIVE, 1);
//            newRow.put(MyOpenHelper.COL_TIME_SENT, currentDateandTime);
//
//            long id = theDatabase.insert( MyOpenHelper.TABLE_NAME, MyOpenHelper.COL_SEND_RECEIVE, newRow);  //returns the id
//            //adding a new message to our history
//            Message cm = new Message(whatIsTyped, currentDateandTime, id);
//            messages.add(cm );//what is the database id?
//
//            edit.setText("");//clear the text
//            theAdapter.notifyItemInserted(messages.size() - 1);




            //send or receive column


            //now that column are full, you insert



//        });
//    }
//
//        public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
//
//            @Override
//            public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//
//                LayoutInflater li = getLayoutInflater();
//                View thisRow = li.inflate(R.layout.sent_message, parent, false);
//
//                return new MyViewHolder( thisRow );
//            }
//
//
//
//            @Override
//            public void onBindViewHolder(MyViewHolder holder, int position) {//need an arraylist to hold all the message.
//
//                //what message object is at position
//                Message thisRow = messages.get(position);
//
//                holder.timeView.setText(thisRow.getTimeSent());
//                holder.messageView.setText(thisRow.getMessageTyped());
//
//            }
//
//            public int getItemViewType(int position){
//                return 5;
//                //messages.get(position).getSentOrReceived();
//
//            }
//
//            @Override
//            public int getItemCount() {
//                return messages.size();
//            }
//        }
//
//        public class MyViewHolder extends RecyclerView.ViewHolder{
//
//            TextView timeView;
//            TextView messageView;
//
//
//            public MyViewHolder(View itemView){
//                super(itemView);
//                itemView.setOnClickListener( click ->{
//                    int position = getAbsoluteAdapterPosition();
//
//                    Message whatWasClicked = messages.get(position);
//
//                    AlertDialog.Builder builder = new AlertDialog.Builder(ChatRoom.this);
//                    builder.setTitle("Question:");
//                    builder.setMessage("Do you want to delete this:" + whatWasClicked.getMessageTyped())
//                            .setNegativeButton("Negative", (dialog, click1)->{})
//                            .setPositiveButton("Positive", (dialog, click2)->{})
//                            .setNeutralButton("Neutral", (dialog, click3)->{}).create().show();
//                    messages.remove(position);
//                    theAdapter.notifyItemRemoved(position);
//                    Snackbar.make(submit, "You removed itme#" + position, Snackbar.LENGTH_LONG)
//                            .setAction("Undo", (click4)->{
//                                messages.add(position, whatWasClicked);
//                                theAdapter.notifyItemInserted(position);
//                                theDatabase.execSQL(String.format("Insert into %s value( \"%d\", \"%s\", \"%d\", \"%s\" );"
//                               , MyOpenHelper.TABLE_NAME,   whatWasClicked.getId(), whatWasClicked.getMessageTyped(), 1, whatWasClicked.getTimeSent()));
//
//                            })
//                            .show();
//                    theDatabase.delete(MyOpenHelper.TABLE_NAME, MyOpenHelper.COL_ID + "=?", new String[]{Long.toString(whatWasClicked.getId())});
//
//
//                });
//
//                timeView = itemView.findViewById(R.id.time);
//                messageView = itemView.findViewById(R.id.message);
//
//            }
//        }
//
//
//
//    public class Message{
//        String messageTyped;
//        String timeSent;
//        long id;
//
//        public Message(String messageTyped, String timeSent, long _id) {
//            this.messageTyped = messageTyped;
//            this.timeSent = timeSent;
//            id = _id;
//
//        }
//
//        public String getMessageTyped() {
//            return messageTyped;
//        }
//
//        public String getTimeSent() {
//            return timeSent;
//        }
//
//        public long getId() {
//            return id;
//        }
//
//        public void setId(long id) {
//            this.id = id;
//        }

//
//        buttonSend.setOnClickListener( click ->{
//            String whatIsTyped = edit.getText().toString();
//            Date timeNow = new Date(); //get time run
//
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
//
//            String currentDateandTime = sdf.format(timeNow);   //convert date to string
//
//            messages.add(new ChatMessage(whatIsTyped, currentDateandTime));
//            edit.setText("");
//            theAdapter.notifyItemInserted(messages.size() - 1);
//            recyclerView.setAdapter(theAdapter);
//            recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        });
//        theAdapter = new MyAdapter();
//        recyclerView.setAdapter(theAdapter);
//        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(layoutManager);

    }

//        public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
//
//            @Override
//            public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//
//                //load a new row from the layout file
//                LayoutInflater li =  getLayoutInflater();
//                View thisRow;
//                //import layout for a row
//
//                thisRow = li.inflate(R.layout.sent_message, parent, false);
//
//
//                return new MyViewHolder( thisRow );
//            }
            //initialize a row at position
//            @Override
//            public void onBindViewHolder(MyViewHolder holder, int position) {   //need an arraylist to hold all the message.
//                ChatMessage thisRow = messages.get(position);
//
//                holder.timeView.setText(thisRow.getTimeSent()); //what time goes on row position
//                holder.messageView.setText( thisRow.getMessage()); //what message goes on row position
//
//
//            }
//
//
//            @Override
//            public int getItemCount() {
//                return messages.size();
//            }
//        }



    //this holds textViews on a row
//    public class MyViewHolder extends RecyclerView.ViewHolder{
//        TextView timeView;
//        TextView messageView;
//
//        public MyViewHolder(View itemView){
//        super(itemView);
//
//        itemView.setOnClickListener( click -> {
//            int position = getAbsoluteAdapterPosition();
//            ChatMessage whatWasClicked = messages.get(position);
//
//            AlertDialog.Builder builder = new AlertDialog.Builder( ChatRoom.this);
//
//
//            builder.setTitle("Question:")
//                   .setMessage("Do you want to delete this:" + whatWasClicked.getMessage())
//                    .setNegativeButton("Negative", (dialog, e)->{})
//                    .setPositiveButton("Positive", (dialog, e2)->{
//                        messages.remove(position);
//                        theAdapter.notifyItemRemoved(position);
//                        Snackbar.make(buttonSend, "You removed item #" + position, Snackbar.LENGTH_LONG).show();
//                    })
//                    .setNeutralButton("Neutral", (dialog, e3)->{}).create().show();
//
//
//        });

//        timeView = itemView.findViewById(R.id.time);
//        messageView = itemView.findViewById(R.id.message);
//    }
//    }


//    private class MyRowViews extends RecyclerView.ViewHolder{
//
//        TextView messageText;
//        TextView timeText;
//
//        public MyRowViews(View itemView) {
//            super(itemView);
//
//
//        }
//    }


//    private  class ChatMessage{
//        String message;
//       // String receive;
//        String timeSent;
//
//        public String getMessage() {
//            return message;
//        }
//
//        //public String getReceive(){return receive;}
//
//        public String getTimeSent() {
//
//            return timeSent;
//        }
//
//        public ChatMessage(String message, String timeSent) {
//            this.message = message;
//            //this.receive = receive;
//            this.timeSent = timeSent;
//
//
//        }





