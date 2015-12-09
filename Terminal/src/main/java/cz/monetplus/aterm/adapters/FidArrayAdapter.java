package cz.monetplus.aterm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cz.monetplus.aterm.R;
import cz.monetplus.aterm.base.Fid;

/**
 * Created by krajcovic on 11/5/15.
 */
public class FidArrayAdapter<T> extends ArrayAdapter<Fid> {

    private List<Fid> fidList;

    public FidArrayAdapter(Context context, int resource, List<Fid> fidList) {
        super(context, resource, fidList);
        this.fidList = fidList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) super.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.fid_item, parent, false);
        TextView tvFid = (TextView) rowView.findViewById(R.id.tvFid);
        TextView tvValue = (TextView) rowView.findViewById(R.id.tvValue);

        tvFid.setText("FID:" + fidList.get(position).getFid());
        tvValue.setText("Value:" + fidList.get(position).getValue());


        return rowView;
    }

    public void clearList() {
        this.fidList.clear();
    }



    //    private static List<String> getFidList(Map<String, String> fidMap) {
//
//        List<String> fidList = new ArrayList<>();
//
//        for (Map.Entry<String, String> entry :
//                fidMap.entrySet()) {
//            fidList.add(entry.getKey() + " " + entry.getValue());
//        }
//
//        return fidList;
//    }


}
