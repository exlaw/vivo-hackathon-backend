package vivo.chainpaper.util;


import net.sf.json.JSONObject;
import vivo.chainpaper.dto.Block;

public class BlockUtil {
    private static final String masterIp="localhost:8000";
    public static Block sendDataToChainStore(String data){
        String data_json="{ \"info\" : \""+data+"\" }"; // change data to json
        try {
            String response=HttpUtil.postData(masterIp, data_json, "application/json");
            JSONObject jsonObject= JSONObject.fromObject(response);
            long blockIndex=jsonObject.getLong("blockIndex");
            long offset=jsonObject.getLong("offset");
            Block block=new Block(blockIndex,offset);
            return block;

        }catch (Exception e){
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
