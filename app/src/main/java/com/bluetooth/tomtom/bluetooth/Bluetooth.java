package com.bluetooth.tomtom.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Set;

public class Bluetooth extends AppCompatActivity {

    private BluetoothAdapter mBluetoothAdapter;

    public void startBluetoothSession() {
        // Check for Bluetooth support in the first place.
        // Emulator doesn't support Bluetooth and will return null.
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            Log.d("Bluetooth Experiment", "\nBluetooth NOT supported. Aborting.");
            return;
        }

        // Check if Bluetooth enable.
        if (mBluetoothAdapter.disable()) {
            mBluetoothAdapter.enable();
        }
    }

    public void endBluetoothSession() {
        // Check if Bluetooth enable.
        if (mBluetoothAdapter.enable()) {
            mBluetoothAdapter.disable();
        }
    }

    public void startBluetoothDiscovery() {
        // Starting the device discovery
        mBluetoothAdapter.startDiscovery();
    }

    public Set<BluetoothDevice> pairedBluetoothDevices() {
        // Listing paired devices
        Set<BluetoothDevice> devices = mBluetoothAdapter.getBondedDevices();
        //for (BluetoothDevice device : devices) {
            //Log.d("\nFound device: " + device);
        //}

        return devices;
    }
}
