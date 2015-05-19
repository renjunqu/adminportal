package cn.futuremove.adminportal.cache;


import cn.futuremove.adminportal.util.http.HttpClientHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * Created by figoxu on 15/4/28.
 */
public class UserCacheCleaner {

    public static void main(String[] args) {
        String phoneNum = "185002176542";
        clearCache(phoneNum);
    }

    public static void clearCache(String phoneNum) {
        HashMap<String, String> parameters = new HashMap<String, String>();
        String urlToRequest = "http://123.57.151.176:8082/joymove/cachemgr/triggerUser.c?";
        parameters.put("mobileNo", phoneNum);

        InputStream inputStream = HttpClientHelper.get(urlToRequest, parameters);
        try {
            String val = inputStream2String(inputStream);
            System.out.println(val);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String inputStream2String(InputStream in) throws IOException {
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1; ) {
            out.append(new String(b, 0, n));
        }
        return out.toString();
    }

}
