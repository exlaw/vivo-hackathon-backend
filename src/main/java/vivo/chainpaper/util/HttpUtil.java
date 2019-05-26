package vivo.chainpaper.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import vivo.chainpaper.exception.SystemException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

public class HttpUtil {
    //private static final Log logger = Logs.get();
    private final static int CONNECT_TIMEOUT = 5000; // in milliseconds
    private final static String DEFAULT_ENCODING = "UTF-8";

    public static String postData(String urlStr, String data) throws SystemException {
        return postData(urlStr, data, null);
    }

    public static String postData(String urlStr, String data, String contentType) throws SystemException {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlStr);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setConnectTimeout(CONNECT_TIMEOUT);
            conn.setReadTimeout(CONNECT_TIMEOUT);
            if (contentType != null)
                conn.setRequestProperty("content-type", contentType);
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream(), DEFAULT_ENCODING);
            if (data == null)
                data = "";
            writer.write(data);
            writer.flush();
            writer.close();

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), DEFAULT_ENCODING));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\r\n");
            }
            return sb.toString();
        } catch (IOException e) {
            //logger.error("Error connecting to " + urlStr + ": " + e.getMessage());
            //e.printStackTrace();
            throw new SystemException();
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException ignored) {
            }
        }
    }

//    public static String getData(String urlStr){
//        try {
//            CloseableHttpClient client = null;
//            CloseableHttpResponse response = null;
//            try {
//                HttpGet httpGet = new HttpGet(urlStr);
//
//                client = HttpClients.createDefault();
//                response = client.execute(httpGet);
//                HttpEntity entity = response.getEntity();
//                String result = EntityUtils.toString(entity);
//                return result;
//            } finally {
//                if (response != null) {
//                    response.close();
//                }
//                if (client != null) {
//                    client.close();
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
