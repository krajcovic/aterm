package cz.monetplus.aterm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import cz.monetplus.aterm.R;
import cz.monetplus.aterm.base.MessageTemplate;

/**
 * Created by krajcovic on 11/10/15.
 */
public class MessageArrayAdapter<T>  extends ArrayAdapter<MessageTemplate> {

    List<MessageTemplate> listMessages;

    public MessageArrayAdapter(Context context, int resource, List<MessageTemplate> listMessages) {
        super(context, resource, listMessages);
        this.listMessages = listMessages;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) super.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.message_item, parent, false);
        TextView tvMessageName = (TextView) rowView.findViewById(R.id.tvMessageName);
        TextView tvDescription = (TextView) rowView.findViewById(R.id.tvDescription);

        tvMessageName.setText(listMessages.get(position).getName());
        tvDescription.setText(listMessages.get(position).getDescription());


        return rowView;
    }
}
