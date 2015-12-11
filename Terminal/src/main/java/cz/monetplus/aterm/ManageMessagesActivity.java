package cz.monetplus.aterm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import cz.monetplus.aterm.adapters.MessageArrayAdapter;
import cz.monetplus.aterm.base.MessageTemplate;
import cz.monetplus.aterm.database.control.SqlHandlerControl;

public class ManageMessagesActivity extends AppCompatActivity {

    private ListView lvMessageList;

    private SqlHandlerControl sqlControl;

    private MessageArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_messages);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sqlControl = new SqlHandlerControl(getApplicationContext());
//        sqlControl.upgradeTables();

        lvMessageList = (ListView) findViewById(R.id.lvMessages);

        // use your custom layout
        adapter = new MessageArrayAdapter<String>(this,
                R.layout.message_item, sqlControl.fetchAllMessages());
        lvMessageList.setAdapter(adapter);

//        lvMessageList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
//                builder.setMessage("Remove message?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        adapter.remove(adapter.getItem(position));
//                        adapter.notifyDataSetChanged();
//                    }
//                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                }).show();
//
//                ;
//                return false;
//            }
//        });

        registerForContextMenu(lvMessageList);
        lvMessageList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.showContextMenu();
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu_manage_messages, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
//        return super.onContextItemSelected(item);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.action_send:
                //editNote(info.id);
                Toast.makeText(getApplicationContext(), "Send position: " + info.id + "-" + info.position, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_detail:
                //editNote(info.id);
                Toast.makeText(getApplicationContext(), "Action position: " + info.id + "-" + info.position, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_edit:
                Toast.makeText(getApplicationContext(), "Edit position:" + info.id + "-" + info.position, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_remove:
                removeMessage(info);
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }

    private void removeMessage(AdapterView.AdapterContextMenuInfo info) {
        MessageTemplate messageTemplate = adapter.getItem(info.position);
//                Toast.makeText(getApplicationContext(), "Removing :" + info.id + "-" + info.position, Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "Removing :" + messageTemplate.getName(), Toast.LENGTH_SHORT).show();
        sqlControl.remove(messageTemplate);
        adapter.remove(messageTemplate);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu_default, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings: {
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
                return true;
            }
            case R.id.help: {
                Intent intent = new Intent(getApplicationContext(), AdSupportMeActivity.class);
                startActivity(intent);
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
