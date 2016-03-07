package com.bluetooth.tomtom.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Bluetooth bluetooth;
    private Button searchDevices;
    private ArrayList<String> list;
    public BroadcastReceiver mReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.showOverflowMenu();

        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitle(R.string.app_name);

        bluetooth = new Bluetooth();
        bluetooth.startBluetoothSession();

        // Create a BroadcastReceiver for ACTION_FOUND
        mReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                list = new ArrayList<String>();
                String action = intent.getAction();
                // When discovery finds a device
                if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                    // Get the BluetoothDevice object from the Intent
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    // Add the name and address to an array adapter to show in a ListView
                    list.add(device.getName() + "\n" + device.getAddress());
                }
            }
        };

        // Create a BroadcastReceiver for ACTION_FOUND
        mReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                list = new ArrayList<String>();
                String action = intent.getAction();
                // When discovery finds a device
                if (BluetoothDevice.ACTION_DISCOVERY_FINISHED.equals(action)) {
                    // Get the BluetoothDevice object from the Intent
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    // Add the name and address to an array adapter to show in a ListView
                    list.add(device.getName() + "\n" + device.getAddress());
                }
            }
        };

        // Register the BroadcastReceiver
        IntentFilter found = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        IntentFilter finished = new IntentFilter(BluetoothDevice.AC);
        registerReceiver(mReceiver, found); // Don't forget to unregister during onDestroy
        registerReceiver(found, finished); // Don't forget to unregister during onDestroy


        // Register 'search for devices button'
        searchDevices = (Button)findViewById(R.id.searchButton);
        searchDevices.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                bluetooth.startBluetoothDiscovery();
                setListView();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected void setListView() {
        final ListView listview = (ListView) findViewById(R.id.listView);

        String[] mStringArray = new String[list.size()];
        mStringArray = list.toArray(mStringArray);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, mStringArray);

        // Assign adapter to ListView
        listview.setAdapter(adapter);
    }
}
