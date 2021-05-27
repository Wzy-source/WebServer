package com.service;

import com.Server;
import com.request.Request;
import com.response.Response;

public abstract class MyService {

    private static String[] strsFor304=new String[]{"If-Match","If-ModifiedSince","If-None-Match","If-Range","If-Unmodified-Since"};

    public abstract void doPost(Request request,Response response);
    public abstract void doGet(Request request,Response response);

    public static void service(Request request, Response response){
        if(Server.ServerIsClosed()){
            //如果服务器已关闭，向Response写入状态码500和错误信息，结束流程
            //todo
        }
        if(!"GET".equals(request.getRequestMethod())&&!"POST".equals(request.getRequestMethod())){
            //如果request的请求方法不为GET和POST，那么服务器不支持请求方法
            //向Response中写入405,结束流程
        }

        if(is304(request)){
            //如果满足304的要求，那么response写入状态码304，结束
        }


        String url= request.getRequestURL();

        //从MyMapping中取得对应的服务类名
        String clazzName=Mapping.getMappingClassName(url);
        if(clazzName!=null){
            //如果服务类名不为空，说明url正确,通过反射创建对应的服务类对象来处理request
            try {
                Class clazz=Class.forName(clazzName);
                MyService service=(MyService) clazz.newInstance();
                if("GET".equals(request.getRequestMethod())){
                    service.doGet(request,response);
                }else {
                    service.doPost(request,response);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }else{
            //如果服务类名为空，那么说名URL有问题
            if(url.equals("/SignUpPageOld")||url.equals("/Old")){
                //Response中的状态码设置为301，Response中添加Location字段
                //todo
            }else if (url.equals("/SignUpPageOther")||url.equals("/Other")){
                //Response中的状态码设置为302，Response中添加Location字段
                //todo

            }else{
                //Response中的状态码设置为404，表示不存在对应的资源
                //todo
            }
        }

    }

    public static boolean is304(Request request){
        String value=null;

        //304必须是GET
        if(!request.getRequestMethod().equals("GET")){
            return false;
        }
        for(String s:strsFor304){
            if(request.getHeadParam(s)!=null){
                value=request.getHeadParam(s);
                break;
            }
        }
        if(value==null){
            return false;
        }
        String username=request.getBodyParam("name");
        //如果请求报文携带的时间跟缓存中的时间一样，那么304
        if(value.equals(Server.getUpdate(username))){
            return true;
        }
        return false;
    }
}
