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
import cz.monetplus.aterm.base.Server;

/**
 * Created by krajcovic on 11/10/15.
 */
public class ServersArrayAdapter<T>  extends ArrayAdapter<Server> {

    private List<Server> servers;

    public ServersArrayAdapter(Context context, int resource, List<Server> servers) {
        super(context, resource, servers);
        this.servers = servers;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) super.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.server_item, parent, false);
        TextView tvHost = (TextView) rowView.findViewById(R.id.tvHost);
        TextView tvPort = (TextView) rowView.findViewById(R.id.tvPort);

        tvHost.setText(servers.get(position).getHost().toString());
        tvPort.setText(servers.get(position).getPort().toString());


        return rowView;
    }

    public List<Server> getServers() {
        return servers;
    }

    public void setServers(List<Server> servers) {
        this.servers = servers;
    }
}
