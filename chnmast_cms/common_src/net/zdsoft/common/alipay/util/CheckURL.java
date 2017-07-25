package net.zdsoft.common.alipay.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CheckURL {
    /**
     * 对字符串进行MD5加密
     * 
     * @param myUrl
     * 
     * @param url
     * 
     * @return 获取url内容
     */
    public static String check(String urlvalue) {
        String inputLine = "";
        try {
            URL url = new URL(urlvalue);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            inputLine = in.readLine().toString();
        }
        catch (Exception e) {
            inputLine = "GATE_ERROR";
            e.printStackTrace();
        }
        return inputLine;
    }

    public static void main(String[] args) {
        String object = "id,email,isguardian,identitycard,linkaddress,mobilephone,postalcode,regioncode,schoolid,sex,relation,birthday,realname,studentid,isdeleted,chargenumber,chargenumbertype,company,duty,workcode,linkphone,professioncode,dutylevel,maritalstatus,emigrationplace,politicalstatus,culture,nation,homepage,remark,officetel,isleaveschool,eventsource";
        String[] array = object.split(",");
        for (String s : array) {
            System.out.println("map.put(\"" + s.trim() + "\", dto.getId());");
        }

    }
}
