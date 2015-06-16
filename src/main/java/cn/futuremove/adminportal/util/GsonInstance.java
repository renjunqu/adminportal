package cn.futuremove.adminportal.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.LinkedList;

/**
 * .
 * User: figo.xu
 * Date: 13-3-27
 * Time: 下午2:42
 *
 */
public class GsonInstance {

    final static Logger logger = LoggerFactory.getLogger(GsonInstance.class);


   public static Type listStringType = new TypeToken<LinkedList<String>>(){}.getType();


    public static Gson instance(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");
        gsonBuilder.registerTypeAdapter(Timestamp.class,new TimestampTypeAdapter());
        gsonBuilder.disableHtmlEscaping();
        Gson gson = gsonBuilder.create();
        return gson;
    }

    public static void printBigJson(String json) {
        int pieceSize = 200;
        try {
            Thread.sleep(1000 *5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int length = json.length();
        int piece = (length+ pieceSize -1)/ pieceSize;
        logger.trace("@length:"+length+"   @piece:"+piece);
        for(int i=0;i<piece;i++){
            int startPosition = i * pieceSize;
            int endPosition = (i + 1) * pieceSize;
            if(endPosition>json.length()){
                endPosition = json.length();
            }
//            logger.trace("@startPosition:"+startPosition+"   @endPosition:"+endPosition);
            System.out.print(json.substring(startPosition, endPosition));

        }
    }
}
