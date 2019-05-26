package vivo.chainpaper.util;


import net.sf.json.JSONObject;
import vivo.chainpaper.dto.Block;
import vivo.chainpaper.exception.SystemException;

public class BlockUtil {
    private static final String masterIp="http://localhost:8002";
    public static Block sendDataToChainStore(String data){
        String data_json="{ \"info\" : \""+data+"\" }"; // change data to json
        try {
            String response=HttpUtil.postData(masterIp+"/saveInfo", data_json, "application/json");
            JSONObject jsonObject= JSONObject.fromObject(response);
            long blockIndex=jsonObject.getLong("blockIndex");
            long offset=jsonObject.getLong("offset");
            Block block=new Block(blockIndex,offset);
            return block;
//            { "info" : "{"abstractContent":"1","conclusion":"1","content":"1","introduction":"1","uid":"law121212"}" }
        }catch (SystemException e0){
            System.out.println("connection timeout!");
        }catch (Exception e){
            //e.printStackTrace();
            System.out.println("Can not find master!");
        }
        return null;
    }
    public static String getInfoFromChainStore(Block block){
        String url=masterIp+"/findBlockInfo?blockIndex="+block.getBlockIndex()+"&&blockOffset="+block.getBlockOffset();
        String response=HttpUtil.getData(url);
        JSONObject jsonObject= JSONObject.fromObject(response);
        if(jsonObject==null){
            System.out.println("Can not find master!");
            return null;
        }
        String data=jsonObject.getString("data");

        return data;
    }
}
