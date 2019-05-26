package vivo.chainpaper.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "paper")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Paper {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
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
    ArrayList<Integer> reference_type=new ArrayList<>();

    @Column(name = "writer")
    String writer;
    @Column(name="cooperator")
    ArrayList<String> cooperator=new ArrayList<>();

    @Column (name="index")
    String index="";
    @Column (name="offset")
    String offset="";


    public Paper(String abstractContent, String introduction, String content, String conclusion, ArrayList<String> references,
                 ArrayList<Integer> reference_type, String writer, ArrayList<String> cooperator, String index, String offset) {
        this.abstractContent = abstractContent;
        this.introduction = introduction;
        this.content = content;
        this.conclusion = conclusion;
        this.references = references;
        this.reference_type = reference_type;
        this.writer = writer;
        this.cooperator = cooperator;
        this.index = index;
        this.offset = offset;
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

    public ArrayList<Integer> getReference_type() {
        return reference_type;
    }

    public void setReference_type(ArrayList<Integer> reference_type) {
        this.reference_type = reference_type;
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

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }
}
