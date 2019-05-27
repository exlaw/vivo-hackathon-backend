package vivo.chainpaper.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import vivo.chainpaper.MainApplication;
import vivo.chainpaper.dto.Block;

import javax.transaction.Transactional;

import static org.junit.Assert.*;
@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainApplication.class)
public class BlockUtilTest {

    @Test
    public void name() {
        Block block=BlockUtil.sendDataToChainStore("hhhhhh");
        System.out.println(block.getBlockIndex());
        System.out.println(block.getBlockOffset());
    }
}