package algonquin.cst2355.wang0532;

import android.content.Context;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ChatRoom extends AppCompatActivity {
//    ArrayList<ChatMessage> messages = new ArrayList<>();
//
       Button submit;
//    Button buttonReceive;
      EditText edit;
      RecyclerView rView;
      MyAdapter theAdapter;




    @Override
    protected void onSaveInstanceState(Bundle outState){

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
    }
    @Override
    protected void onCreate(Bundle previousInstanceState) {
        super.onCreate(previousInstanceState);
        //load XML:
        setContentView(R.layout.chatroom);

        submit = findViewById(R.id.submitButton);  //send button
//
//        buttonReceive = findViewById(R.id.buttonReceive); //receive button
//
        edit = findViewById(R.id.editText);  //edit text
//
        rView = findViewById(R.id.myRecyclerView);  //recycler view

        theAdapter = new MyAdapter();
        rView.setAdapter(theAdapter);
    }

        public class MyAdapter extends RecyclerView.Adapter {

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return null;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 0;
            }
        }



    public class Message{
        String messageTyped;
        String timeSent;

        public Message(String messageTyped, String timeSent) {
            this.messageTyped = messageTyped;
            this.timeSent = timeSent;
        }

        public String getMessageTyped() {
            return messageTyped;
        }

        public String getTimeSent() {
            return timeSent;
        }
    }
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



    }

