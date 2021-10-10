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
import java.net.ServerSocket;

/**
 *
 * @author Admin
 */
public class Server extends Connection {

    private ServerSocket mServer;

    /**
     *
     * @param port
     * @param notification
     */
    public Server(int port, javax.swing.JTextArea notification, javax.swing.JTextArea chatArea) {
        super(port, notification, chatArea);
    }

    @Override
    public void start() {
        try {
            if (mPort != 0) {
                mServer = new ServerSocket(mPort);
            } else {
                mServer = new ServerSocket();
            }
            AppendNotification("Server is waiting to accept user...");
            mSocket = mServer.accept();
            AppendNotification("Client accepted");
            mInput = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
            mOuput = new PrintWriter(mSocket.getOutputStream());
            isRunning = true;
            ReceiveLoop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
