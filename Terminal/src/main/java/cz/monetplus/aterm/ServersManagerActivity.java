package cz.monetplus.aterm;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import cz.monetplus.aterm.adapters.ServersArrayAdapter;
import cz.monetplus.aterm.base.MessageTemplate;
import cz.monetplus.aterm.base.Server;
import cz.monetplus.aterm.database.control.SqlHandlerControl;

public class ServersManagerActivity extends AppCompatActivity {

    private ListView lvServers;

    private ServersArrayAdapter<Server> adapter;

    private SqlHandlerControl sqlControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servers_manager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        sqlControl = new SqlHandlerControl(getApplicationContext());

        lvServers = (ListView) findViewById(R.id.lvServers);
        adapter = new ServersArrayAdapter<Server>(this,
                R.layout.message_item, sqlControl.fetchAllServers());
        lvServers.setAdapter(adapter);


        ImageButton addButton = (ImageButton) findViewById(R.id.ibAddServer);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etHost = (EditText) findViewById(R.id.etHost);
                EditText etPort = (EditText) findViewById(R.id.etPort);

                if(etHost.getText().length() == 0) {
                    Toast.makeText(ServersManagerActivity.this, "Fill host of message.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(etPort.getText().length() == 0) {
                    Toast.makeText(ServersManagerActivity.this, "Fill port of message.", Toast.LENGTH_SHORT).show();
                    return;
                }

                Server server = new Server(etHost.getText().toString(), Integer.valueOf(etPort.getText().toString()));
                sqlControl.insert(server);
                adapter.getServers().add(server);
                adapter.notifyDataSetChanged();
            }
        });

        registerForContextMenu(lvServers);
        lvServers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
        inflater.inflate(R.menu.context_menu_manage_servers, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
//        return super.onContextItemSelected(item);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.action_remove:
                removeServer(info);
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }

    private void removeServer(AdapterView.AdapterContextMenuInfo info) {
        Server server = adapter.getItem(info.position);
//                Toast.makeText(getApplicationContext(), "Removing :" + info.id + "-" + info.position, Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "Removing :" + server.getHost() + ":" + server.getPort(), Toast.LENGTH_SHORT).show();
        sqlControl.remove(server);
        adapter.remove(server);
        adapter.notifyDataSetChanged();
    }

}
