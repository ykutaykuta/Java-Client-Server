/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 *
 * @author Admin
 */
public class Client extends Connection {

    private String mServerAddr;
    private int mServerPort;

    public Client(String serverAddr, int serverPort, javax.swing.JTextArea notification, javax.swing.JTextArea chatArea) {
        super(0, notification, chatArea);
        mServerAddr = serverAddr;
        mServerPort = serverPort;
    }

    @Override
    public void start() {
        try {
            mSocket = new Socket(mServerAddr, mServerPort);
            AppendNotification("Connected to server " + mServerAddr + ":" + mServerPort);
            mInput = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
            mOuput = new PrintWriter(mSocket.getOutputStream());
            isRunning = true;
            ReceiveLoop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
