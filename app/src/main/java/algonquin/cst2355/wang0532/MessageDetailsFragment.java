package algonquin.cst2355.wang0532;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import org.w3c.dom.Text;

public class MessageDetailsFragment extends Fragment {
    TextView tv;
    MessageListFragment.Message chosenMessage;
    int position;
    public MessageDetailsFragment(MessageListFragment.Message clicked, int pos){
        chosenMessage = clicked;
        position = pos;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //load the layout
        View detailsLayout = inflater.inflate(R.layout.details_layout, container, false);
        tv = detailsLayout.findViewById(R.id.textView);

        tv.setText("Message" + chosenMessage.getMessageTyped());

        return detailsLayout;
    }
}
