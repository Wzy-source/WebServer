package com.request;



import com.ContentType;
import com.Cookie;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Request {
    private String requestURL;//请求URL
    //以下为URL的getter
    public String getRequestURL() {
        return requestURL;
    }


    private String  requestMethod;//请求方法
    //以下为RequestMethod的getter
    public String getRequestMethod(){
        return this.requestMethod;
    }


    private Map<String, String>headers;//存储解析后的，请求头中的键值对
    //从解析后的请求头中根据key获取对应的value
    public String getHeadParam(String key){
        return headers.get(key);
    }


    private Map<String,String>body;//存储解析后的，请求体中的键值对
    //从解析后的请求体中根据key获取对应的value
    public String getBodyParam(String key){
        return body.get(key);
    }



    //存储Content-Type字段的值
    private String contentType= ContentType.X_WWW_form_urlencoded;


    private List<String>Accept;//存储请求头里面的Accept字段的值，用于告诉服务器，客户端可以处理的MIME类型



    private List<Cookie>cookies;//存储Cookie字段
    //根据key，从cookies查找到对应的Cookie，返回它的value
    public String getCookieValue(String key){
        for(int i=0;i<cookies.size();i+=1){
            if(cookies.get(i).getKey().equals(key)){
                return cookies.get(i).getValue();
            }
        }
        return null;
    }





    private String requestLine;//存储请求报文的请求行

    private List<String> requestHead;//存储请求报文的请求头

    private List<String> requestBody;//存储请求报文的请求体











    /*
    ------------------------------------------------------------------------------------------------------------------
    以此为界
    上面是提供的接口，可以直接调用上面的方法获取Request中你们想要的信息
    下面是解析方法，解析Http请求报文为Request对象，想看过程可以看，不想看也不影响你使用Request对象
     */



    //构造方法，将Http请求报文信息存储到Request对象中
    public Request(InputStream in){
        Scanner sc=new Scanner(in);
        //Http请求报文第一行一定是请求行
        requestLine=sc.nextLine();

        //用于标记读的是请求头，还是请求体，true——正在读请求头，false——正在读请求体
        boolean isHead=true;

        //如果仍还有下一行存在，说明请求报文中存在请求头，初始化请求头List
        if(sc.hasNext()){
            requestHead=new ArrayList<>();
        }

        while(sc.hasNext()){
            String s=sc.nextLine();

            if(s.equals("")){
                //如果读到了空行，说明接下来要读取请求体，isHead设为true，初始化请求体List
                isHead=false;
                requestBody=new ArrayList<>();

                //continue，防止空行放入请求体List中
                continue;
            }

            if(isHead){
                //正在读请求行
                requestHead.add(s);
            }else{
                //正在读请求体
                requestBody.add(s);
            }
        }
        //解析请求行
        parseRequestLine(requestLine);

        //如果请求头存在，解析请求头
        if(requestHead!=null){
            parseRequestHead(requestHead);
        }

        //如果请求体不为空，解析请求体
        if(requestBody!=null){
            parseRequestBody(requestBody);
        }

    }




    //解析请求行的方法，解析结果存储在requestURL和requestMethod中
    public void parseRequestLine(String requestLine){
        String[]params=requestLine.split(" ");
        requestMethod=params[0];
        requestURL=params[1];
    }




    //解析请求头，解析结果存储在cookies,contentType,headers中
    public void parseRequestHead(List<String>requestHead){
        //这里要特别处理的字段——Content—Type，Cookie，Accept
        for(int i=0;i<requestHead.size();i+=1){
            String str=requestHead.get(i);
            int index=str.indexOf(':');
            String key=str.substring(0,index);
            String value=str.substring(index+2);
            if(key.equals("Accept")){
                //如果为Accept字段就解析Accept
                parseAccept(value);
            }else if(key.equals("Cookie")){
                //如果为Cookie字段就解析Cookie
                parseCookie(value);
            }else if(key.equals("Content-Type")){
                //如果为Content-Type字段就设置Content-Type
                this.contentType=value;
            }else{
                //都不满足的话，向headers中添加键值对
                headers.put(key,value);
            }
        }
    }

    //解析Accept，放入List<String>Accept中
    public void parseAccept(String content){
        String[] strs=content.split(",");
        for(String s:strs){
            if(s.contains(";")){
                Accept.add(s.substring(0,s.indexOf(';')));
            }else{
                Accept.add(s);
            }
        }
    }


    //解析请求头时，如果遇到了Cookie字段，那么说明存在Cookie，进行解析Cookie操作，结果存储在cookies中
    public void parseCookie(String content){
        String[]strs=content.split(";");
        for(String s:strs){
            s=s.trim();
            int index=s.indexOf('=');
            cookies.add(new Cookie(s.substring(0,index),s.substring(index+1)));
        }
    }




    //解析请求头，解析结果存储在Map body中，请求体的原始内容，存储在requestBody中，如何解析取决于Content-Type类型
    public void parseRequestBody(List<String>requestBody){
        int size=requestBody.size();
        if(contentType.equals(ContentType.JSON)){
            //todo
            //解析JSON
        }else if(contentType.equals(ContentType.X_WWW_form_urlencoded)){
            for(int i=0;i<size;i+=1){
                String str=requestBody.get(i);
                String[]params=str.split("&");
                for(String s:params){
                    int index=s.indexOf("=");
                    body.put(s.substring(0,index),s.substring(index+1));
                }
            }
        }else if(contentType.equals(ContentType.DEFAULT_CONTENT_TYPE)){
            //todo
            //解析HTML
        }else if(contentType.equals(ContentType.XML)){
            //todo
            //解析XML
        }
    }


}
