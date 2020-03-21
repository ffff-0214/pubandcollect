package edu.qingtai.pubandcollect.event;


import java.io.Serializable;
import java.sql.Date;

public class Impression implements Serializable {
    private String uuid;

    private String position;

    private String company;

    private String workplace;

    private String education;

    private String salary;

    private Date inserttime;

    private String label;

    private Integer truth;

    private Integer favorite;

    private String content;

    public Impression(String uuid){
        this.uuid = uuid;
    }

    public Impression(String uuid, Integer favorite){
        this.uuid = uuid;
        this.favorite = favorite;
    }

    public Impression(String uuid, String position, String company, String workplace, String education, String salary, Date inserttime, String label, Integer truth, String content, Integer favorite) {
        this.uuid = uuid;
        this.position = position;
        this.company = company;
        this.workplace = workplace;
        this.education = education;
        this.salary = salary;
        this.inserttime = inserttime;
        this.label = label;
        this.truth = truth;
        this.content = content;
        this.favorite = favorite;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace == null ? null : workplace.trim();
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary == null ? null : salary.trim();
    }

    public Date getInserttime() {
        return inserttime;
    }

    public void setInserttime(Date inserttime) {
        this.inserttime = inserttime;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    public Integer getTruth() {
        return truth;
    }

    public void setTruth(Integer truth) {
        this.truth = truth;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}

