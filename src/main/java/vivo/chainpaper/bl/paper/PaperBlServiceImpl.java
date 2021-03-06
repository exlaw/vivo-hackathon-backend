package vivo.chainpaper.bl.paper;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vivo.chainpaper.blservice.paper.PaperBlService;
import vivo.chainpaper.dao.PaperDao;
import vivo.chainpaper.dto.Block;
import vivo.chainpaper.dto.PaperBlock;
import vivo.chainpaper.entity.Paper;
import vivo.chainpaper.parameters.paper.PaperDraft;
import vivo.chainpaper.util.BlockUtil;

@Service
public class PaperBlServiceImpl implements PaperBlService {

   private final PaperDao paperDao;

    @Autowired
    PaperBlServiceImpl(PaperDao paperDao){
        this.paperDao=paperDao;
    }


    @Override
    public Block addPaperToChainStore(PaperDraft paperDraft, String writer) {
        PaperBlock paperBlock=new PaperBlock(writer,paperDraft.getAbstractContent(),paperDraft.getIntroduction(),paperDraft.getContent(),paperDraft.getConclusion(),paperDraft.getTitle(),paperDraft.getKeywords(),paperDraft.getAcknowledgement());
        JSONObject object = JSONObject.fromObject(paperBlock);
        String paperString=object.toString();
        return BlockUtil.sendDataToChainStore(paperString);
    }

    @Override
    public void addPaperToDatabase(Paper paper) {
        paperDao.save(paper);
    }
}
