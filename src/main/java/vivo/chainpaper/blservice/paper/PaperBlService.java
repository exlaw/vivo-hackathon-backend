package vivo.chainpaper.blservice.paper;

import org.springframework.stereotype.Service;
import vivo.chainpaper.dto.Block;
import vivo.chainpaper.entity.Paper;
import vivo.chainpaper.parameters.paper.PaperDraft;

@Service
public interface PaperBlService {
        //论文上链
        Block addPaperToChainStore(PaperDraft paperDraft,String writer);
        //论文入库
        void addPaperToDatabase(Paper paper);
        //论文查找
}
