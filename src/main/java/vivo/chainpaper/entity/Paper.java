package vivo.chainpaper.entity;

import org.hibernate.annotations.GenericGenerator;
import vivo.chainpaper.parameters.paper.Reference;
import vivo.chainpaper.util.TimeUtil;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "paper")
//@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Paper {
    @Id
    //@GeneratedValue(generator = "jpa-uuid")
    @Column(name="id")
    String id;
    @Column(name="abstract_content")
    String abstractContent;
    @Column(name="introduction")
    String introduction;
    @Column(name="content")
    String content;
    @Column(name="conclusion")
    String conclusion;

    @Column(name="references")
    ArrayList<String> references=new ArrayList<>();
    @Column(name="reference_type")
    ArrayList<String> reference_type=new ArrayList<>();

    @Column(name = "writer")
    String writer;
    @Column(name="cooperator")
    ArrayList<String> cooperator=new ArrayList<>();

    @Column(name="title")
    String title="";

    @Column(name="keywords")
    String keywords="";

    @Column(name="acknowledgement")
    String acknowledgement="";



    @Column (name="index")
    long index=0;
    @Column (name="offset")
    long offset=0;
    @Column (name="time")
    String time="";

    public Paper(String abstractContent, String introduction, String content, String conclusion, Reference[] refs, String writer,String title,String keywords,String acknowledgement) {
        this.abstractContent = abstractContent;
        this.introduction = introduction;
        this.content = content;
        this.conclusion = conclusion;
        this.writer = writer;
        this.time= TimeUtil.getTimeStamp();
        this.id=time+(int)(Math.random()*100000);
        this.title=title;
        this.keywords=keywords;
        this.acknowledgement=acknowledgement;
        for(Reference ref:refs){
            this.reference_type.add(ref.getType());
            if(ref.getType()=="published"){
                this.references.add(ref.getDoi());
            }else{
                this.references.add(ref.getPaperId());
            }
        }
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

    public ArrayList<String> getReferences() {
        return references;
    }

    public void setReferences(ArrayList<String> references) {
        this.references = references;
    }

    public ArrayList<String> getReference_type() {
        return reference_type;
    }

    public void setReference_type(ArrayList<String> reference_type) {
        this.reference_type = reference_type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public ArrayList<String> getCooperator() {
        return cooperator;
    }

    public void setCooperator(ArrayList<String> cooperator) {
        this.cooperator = cooperator;
    }

    public long getIndex() {
        return index;
    }

    public void setIndex(long index) {
        this.index = index;
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }
}
