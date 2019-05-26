package vivo.chainpaper.entity;

import org.hibernate.annotations.GenericGenerator;
import vivo.chainpaper.parameters.paper.Reference;
import vivo.chainpaper.util.TimeUtil;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "paper")
public class Paper {
    @Id
    @Column(name="id")
    private String id;
    @Column(name="abstract_content")
    String abstractContent;
    @Column(name="introduction")
    String introduction;
    @Column(name="content")
    String content;
    @Column(name="conclusion")
    String conclusion;


    @ElementCollection
    @CollectionTable(name = "refs")
    List<String> refs=new ArrayList<>();
//
//    @Column(name="reference_type")
    @ElementCollection
    @CollectionTable(name = "reference_type")
    List<String> reference_type=new ArrayList<>();
//
    @Column(name = "writer")
    String writer;

//    @Column(name="cooperator")
    @ElementCollection
    @CollectionTable(name = "cooperator")
    List<String> cooperator=new ArrayList<>();

    @Column(name="title")
    String title="";

    @Column(name="keywords")
    String keywords="";

    @Column(name="acknowledgement")
    String acknowledgement="";
//
    @Column (name="indexs")
    long indexs=0;

    @Column (name="offsets")
    long offsets=0;

    @Column (name="c_time")
    String c_time="";

    @Column(name="paper_state")
    int paper_state=0;// 0 讨论中，1已提交

    public Paper(String abstractContent, String introduction, String content, String conclusion, Reference[] refs, String writer,String title,String keywords,String acknowledgement, String t) {
        this.abstractContent = abstractContent;
       this.introduction = introduction;
        this.content = content;
        this.conclusion = conclusion;
        this.writer = writer;
        cooperator.add(writer);//合作者第一项是作者自己
        this.c_time = t;
        this.id= TimeUtil.getTimeStamp() + (int)(Math.random()*100000);
        this.title=title;
        this.keywords=keywords;
        this.acknowledgement=acknowledgement;
        for(Reference ref:refs){
            this.reference_type.add(ref.getType());
            if(ref.getType()=="published"){
                this.refs.add(ref.getDoi());
            }else{
                this.refs.add(ref.getPaperId());
            }
        }
        this.paper_state=0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAbstractContent() {
        return abstractContent;
    }

    public void setAbstractContent(String abstractContent) {
        this.abstractContent = abstractContent;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public List<String> getRefs() {
        return refs;
    }

    public void setRefs(List<String> refs) {
        this.refs = refs;
    }

    public List<String> getReference_type() {
        return reference_type;
    }

    public void setReference_type(List<String> reference_type) {
        this.reference_type = reference_type;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public List<String> getCooperator() {
        return cooperator;
    }

    public void setCooperator(List<String> cooperator) {
        this.cooperator = cooperator;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getAcknowledgement() {
        return acknowledgement;
    }

    public void setAcknowledgement(String acknowledgement) {
        this.acknowledgement = acknowledgement;
    }

    public long getIndexs() {
        return indexs;
    }

    public void setIndexs(long indexs) {
        this.indexs = indexs;
    }

    public long getOffsets() {
        return offsets;
    }

    public void setOffsets(long offsets) {
        this.offsets = offsets;
    }

    public String getC_time() {
        return c_time;
    }

    public void setC_time(String c_time) {
        this.c_time = c_time;
    }

    public int getPaper_state() {
        return paper_state;
    }

    public void setPaper_state(int paper_state) {
        this.paper_state = paper_state;
    }
}
