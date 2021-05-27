package com.thread;

import com.request.Request;
import com.response.Response;

import java.io.IOException;
import java.net.Socket;

public class ServerThread implements Runnable{
    private Socket socket;

    @Override
    public void run() {
        Request request=null;
        Response response=null;
        try {
            request=new Request(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            response=new Response(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ServerThread(Socket socket){
        this.socket=socket;
    }
}
