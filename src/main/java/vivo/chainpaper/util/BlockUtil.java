package vivo.chainpaper.util;


import net.sf.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import vivo.chainpaper.dto.Block;

public class BlockUtil {
    private BlockUtil(){

    }
    private static final String masterIp ="http://localhost:8002";
    public static Block sendDataToChainStore(String data){
        String data_json="{ \"info\" : \""+data+"\" }"; // change data to json
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        HttpEntity<String> formEntity = new HttpEntity<>(data_json, headers);

        String result = restTemplate.postForObject(masterIp+"/saveInfo", formEntity, String.class);
        JSONObject jsonObject= JSONObject.fromObject(result);
        long blockIndex=jsonObject.getLong("blockIndex");
        long offset=jsonObject.getLong("blockOffset");
        return new Block(blockIndex,offset);

    }
}
