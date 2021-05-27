package com;

import com.thread.ServerThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Server {
    //服务器Socket
    private static ServerSocket server;


    //判断服务器是否已关闭
    public static boolean ServerIsClosed() {
        return server.isClosed();
    }



    //Map模拟缓存，存储用户信息
    private static Map<String,String> user=new HashMap<>();
    public static Map<String, String>update=new HashMap<>();
    //向缓存中添加用户信息
    public static void putUser(String key,String value){
        user.put(key,value);
    }
    public static String getUser(String key){ return user.get(key); }
    public static void putUpdate(String key,String date){ update.put(key,date); }
    public static String getUpdate(String key){ return update.get(key); }

    //缓存中是否含有指定的用户信息
    public static boolean contains(String key,String value){
        return value.equals(user.get(key));
    }

    //缓存清理线程，定期清楚，间隔15分钟
    public static class UserMapClearThread implements Runnable{
        @Override
        public void run() {
            while(true){
                try {
                    Thread.sleep(1000*15*60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                user.clear();
                update.clear();
            }
        }
    }


    //启动服务器
    public static void start(int port) throws IOException {
        //创建线程池
        Executor executor=new ThreadPoolExecutor(10,20,10, TimeUnit.MINUTES,new ArrayBlockingQueue<Runnable>(5));
        server=new ServerSocket(port);
        //创建缓存清理线程，定期清楚缓存
        new Thread(new UserMapClearThread()).start();
        while(true){
            Socket socket=server.accept();
            if(socket!=null){
                //接收到请求后，创建ServerThread处理请求
                executor.execute(new ServerThread(socket));
            }
        }
    }

    //程序入口
    public static void main(String[]args) throws IOException {
        int port=8080;
        start(port);
    }



}
