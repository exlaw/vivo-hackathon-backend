package vivo.chainpaper.entity;

import org.hibernate.annotations.GenericGenerator;
import vivo.chainpaper.parameters.paper.Reference;
import vivo.chainpaper.util.TimeUtil;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "paper")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Paper {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
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

    public Paper(String abstractContent, String introduction, String content, String conclusion, Reference[] refs, String writer,String title,String keywords,String acknowledgement, String t) {
//        this.abstractContent = abstractContent;
////        this.introduction = introduction;
//        this.content = content;
//        this.conclusion = conclusion;
//        this.writer = writer;
////        this.c_time = t;
//        this.id= TimeUtil.getTimeStamp() + (int)(Math.random()*100000);
//        this.title=title;
//        this.keywords=keywords;
//        this.acknowledgement=acknowledgement;
//        for(Reference ref:refs){
//            this.reference_type.add(ref.getType());
//            if(ref.getType()=="published"){
//                this.refs.add(ref.getDoi());
//            }else{
//                this.refs.add(ref.getPaperId());
//            }
//        }
    }

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getAbstractContent() {
//        return abstractContent;
//    }
//
//    public void setAbstractContent(String abstractContent) {
//        this.abstractContent = abstractContent;
//    }
//
////    public String getIntroduction() {
////        return introduction;
////    }
////
////    public void setIntroduction(String introduction) {
////        this.introduction = introduction;
////    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public String getConclusion() {
//        return conclusion;
//    }
//
//    public void setConclusion(String conclusion) {
//        this.conclusion = conclusion;
//    }
//
//    public List<String> getReferences() {
//        return refs;
//    }
//
//    public void setReferences(ArrayList<String> references) {
//        this.refs = references;
//    }
//
//    public List<String> getReference_type() {
//        return reference_type;
//    }
//
//    public void setReference_type(ArrayList<String> reference_type) {
//        this.reference_type = reference_type;
//    }
//
////    public String getTime() {
////        return times;
////    }
////
////    public void setTime(String time) {
////        this.times = time;
////    }
//
//    public String getWriter() {
//        return writer;
//    }
//
//    public void setWriter(String writer) {
//        this.writer = writer;
//    }
//
//    public List<String> getCooperator() {
//        return cooperator;
//    }
//
//    public void setCooperator(ArrayList<String> cooperator) {
//        this.cooperator = cooperator;
//    }
//
//    public long getIndex() {
//        return index;
//    }
//
//    public void setIndex(long index) {
//        this.index = index;
//    }
//
//    public long getOffset() {
//        return offset;
//    }
//
//    public void setOffset(long offset) {
//        this.offset = offset;
//    }
}
