package edu.qingtai.pubandcollect.event;

import java.io.Serializable;
import java.sql.Date;


public class Infer implements Serializable {
    private String uuid;

    private String title;

    private String images;

    private Date inserttime;

    private String label;

    private Integer favorite;

    private String content;

    public Infer(String uuid, String title, String images, Date inserttime, String label, Integer favorite, String content) {
        this.uuid = uuid;
        this.title = title;
        this.images = images;
        this.inserttime = inserttime;
        this.label = label;
        this.favorite = favorite;
        this.content = content;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images == null ? null : images.trim();
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

    public Integer getFavorite() {
        return favorite;
    }

    public void setFavorite(Integer favorite) {
        this.favorite = favorite;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}
