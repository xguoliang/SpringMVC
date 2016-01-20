package com.common.httpHelp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class httpURL {
    private String urlStr;
    private URL url;
    private HttpURLConnection url_con;
    private String response_content;
    private String contentStr;
    public String getContentStr() {
        return contentStr;
    }
    private void setContentStr(String contentStr) {
        this.contentStr = contentStr;
    }
    public void setUrlStr(String urlStr) {
        this.urlStr = urlStr;
    }
    public String getResponse_content() {
        return response_content;
    }
    private  void setResponse_content(String response_content) {
        this.response_content = response_content;
    }
    public void send_url(String mobile_number){
        try{
            url = new URL(urlStr);
            url_con=(HttpURLConnection)url.openConnection();
            url_con.setRequestMethod("POST");
            url_con.setDoOutput(true);//打开读写属性，默认均为false 
            url_con.setDoInput(true); //打开读写属性，默认均为false 
            url_con.setUseCaches(false);// Post 请求不能使用缓存 
            String param="action=mobile&mobile="+mobile_number;
           
            url_con.getOutputStream().write(param.getBytes());
            url_con.getOutputStream().flush();
            url_con.getOutputStream().close();
            InputStream in= url_con.getInputStream();
            BufferedReader   rd = new BufferedReader(new InputStreamReader(in));
            StringBuilder tempStr=new StringBuilder();
            while(rd.read()!=-1){
                tempStr.append(rd.readLine());
            }
           setResponse_content(new String(tempStr));
    } catch(Exception e){
            e.printStackTrace();
        }
        finally{
            if(url_con!=null)
            url_con.disconnect();
        }
    }

    public void send_url(){
        try{
            StringBuilder temp = new StringBuilder();
// 拼凑get请求的URL字串，使用URLEncoder.encode对特殊和不可见字符进行编码 
        //String getURL = GET_URL + " ?username= " + URLEncoder.encode("fat man", " utf-8 "); 
         url = new  URL(urlStr);
         url_con= (HttpURLConnection)url.openConnection();
         url_con.setDoOutput(true);//打开读写属性，默认均为false 
         url_con.setRequestMethod("GET");// 设置请求方式，默认为GE
         url_con.getOutputStream().flush();
         url_con.getOutputStream().close();
            InputStream in =url_con.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(in));
            while(rd.read()!=-1){
                temp.append(rd.readLine());
            }
            setContentStr(new String (temp));
    }       catch (Exception e){
            e.printStackTrace();
        }  finally{
            if(url_con!=null){
                url_con.disconnect();
            }
        }
    }
}
