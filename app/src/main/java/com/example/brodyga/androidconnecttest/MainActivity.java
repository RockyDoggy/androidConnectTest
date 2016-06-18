package com.example.brodyga.androidconnecttest;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private DataOutputStream send_msg;
    private DataInputStream read_msg;

    private Socket sock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            InetAddress inet = InetAddress.getByName("95.46.198.26");
            sock = new Socket(inet, 7802);
            send_msg = new DataOutputStream(sock.getOutputStream());
            read_msg = new DataInputStream(sock.getInputStream());
            //send_msg = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sock.getOutputStream())), true);
            //read_msg = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void Send(View view) {
        EditText text1 = (EditText) findViewById(R.id.editText);
        EditText text2 = (EditText) findViewById(R.id.editText2);
        Editable t1 = text1.getText();
        try {
            send_msg.writeUTF(t1.toString());
            text2.setText(read_msg.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}