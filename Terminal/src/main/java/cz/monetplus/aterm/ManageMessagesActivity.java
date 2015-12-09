package cz.monetplus.aterm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import cz.monetplus.aterm.adapters.MessageArrayAdapter;
import cz.monetplus.aterm.database.control.SqlHandlerControl;

public class ManageMessagesActivity extends AppCompatActivity {

    private ListView lvMessageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_messages);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SqlHandlerControl sqlControl = new SqlHandlerControl(getApplicationContext());
//        sqlControl.upgradeTables();

        lvMessageList = (ListView) findViewById(R.id.lvMessages);

        // use your custom layout
        final MessageArrayAdapter<String> adapter = new MessageArrayAdapter<String>(this,
                R.layout.message_item, sqlControl.fetchAllMessages());
        lvMessageList.setAdapter(adapter);
        lvMessageList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                builder.setMessage("Remove message?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.remove(adapter.getItem(position));
                        adapter.notifyDataSetChanged();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();

                ;
                return false;
            }
        });
    }
}
