/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Time;
import java.util.List;
import javax.swing.SwingUtilities;

/**
 *
 * @author Admin
 */
public class Connection {

    protected Socket mSocket;
    protected BufferedReader mInput;
    protected PrintWriter mOuput;
    protected javax.swing.JTextArea mNotification;
    protected javax.swing.JTextArea mChatArea;
    protected Thread mThreadRead;
    protected boolean isRunning;
    protected int mPort;

    public Connection(int port, javax.swing.JTextArea notification, javax.swing.JTextArea chatArea) {
        mPort = port;
        mNotification = notification;
        mChatArea = chatArea;
    }

    public void AppendNotification(String text) {
        mNotification.append(text + "\n");
    }

    public void ReceiveLoop() {
        mThreadRead = new Thread(() -> {
            while (isRunning) {
                try {
                    final String msg = mInput.readLine();
                    if (msg == null) {
                        continue;
                    }
                    if (msg == "!!!OVER!!!") {
                        stop();
                    }
                    mChatArea.append("RECEIVED: " + msg + "\n\n");
                } catch (Exception e) {
                    e.printStackTrace();
                    stop();
                }
            }
        });
        mThreadRead.start();
    }

    public void Send(String msg) {
        if (isRunning) {
            try {
                mOuput.println(msg);
                mOuput.flush();
                mChatArea.append("SEND: " + msg + "\n\n");
            } catch (Exception e) {
                e.printStackTrace();
                stop();
            }

        }
    }

    public void start() {
    }

    public void stop() {
        if (mSocket == null) {
            return;
        }
        try {
            mSocket.close();
            mSocket = null;
            isRunning = false;
            AppendNotification("Disconnected");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
