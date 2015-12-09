package cz.monetplus.aterm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import cz.monetplus.aterm.base.Fid;
import cz.monetplus.aterm.adapters.FidArrayAdapter;
import cz.monetplus.aterm.base.MessageTemplate;
import cz.monetplus.aterm.database.control.SqlHandlerControl;

public class CreateMessageActivity extends AppCompatActivity {

    private MessageTemplate messageTemplate;

    private EditText etNewFid;
    private EditText etNewValue;
    private EditText etNewMessageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        messageTemplate = new MessageTemplate("Undefined", 'F', 'O', 0, 0);

        SqlHandlerControl sqlControl = new SqlHandlerControl(getApplicationContext());

        etNewFid = (EditText) findViewById(R.id.etNewFid);
        etNewValue = (EditText) findViewById(R.id.etNewValue);
        etNewMessageName = (EditText) findViewById(R.id.etNewMessageName);

//        String[] values = new String[]{"Android", "iPhone", "WindowsMobile",
//                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
//                "Linux", "OS/2"};

        ListView lvFidList = (ListView) findViewById(R.id.lvFids);

        // use your custom layout
        final FidArrayAdapter<String> adapter = new FidArrayAdapter<>(this,
                R.layout.fid_item, messageTemplate.getFidList());
        lvFidList.setAdapter(adapter);
        lvFidList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                builder.setMessage("Remove FID from message?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
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

                return false;
            }
        });

        Button butAddFid = (Button) findViewById(R.id.butAddFid);
        butAddFid.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                messageTemplate.getFidList().add(new Fid(etNewFid.getText().toString(), etNewValue.getText().toString()));
                adapter.notifyDataSetChanged();
//                messageTemplate.getFidMap().put(etNewFid.getText().toString(), etNewValue.getText().toString());
            }
        });

        Button butAddMessage = (Button) findViewById(R.id.butSaveMessage);
        butAddMessage.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(etNewMessageName.getText().length() > 0) {
                    SqlHandlerControl sqlControl = new SqlHandlerControl(getApplicationContext());
                    messageTemplate.setName(etNewMessageName.getText().toString());
                    long messageId = sqlControl.insert(messageTemplate);
                    for (Fid fid :
                            messageTemplate.getFidList()) {
                        sqlControl.insert(messageId, fid);
                    }

                    etNewFid.getText().clear();
                    etNewValue.getText().clear();
                    etNewMessageName.getText().clear();
//                    messageTemplate.clear();
                    messageTemplate = new MessageTemplate("New message");
                    adapter.clearList();
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(CreateMessageActivity.this, "Fill name of message.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
