package vivo.chainpaper.util;

import org.junit.Test;
import vivo.chainpaper.dto.Block;

import static org.junit.Assert.*;

public class BlockUtilTest {
    @Test
    public void sendHttpRequest(){
        Block block=BlockUtil.sendDataToChainStore("i am a good boy");
    }
}