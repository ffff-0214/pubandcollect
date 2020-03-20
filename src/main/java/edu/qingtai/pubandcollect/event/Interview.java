package edu.qingtai.pubandcollect.event;


import java.io.Serializable;
import java.sql.Date;

public class Interview implements Serializable {
    private String uuid;

    private String title;

    private Date inserttime;

    private Integer favorite;

    private String images;

    private String content;

    public Interview(String uuid, String title, Date inserttime, Integer favorite, String images, String content) {
        this.uuid = uuid;
        this.title = title;
        this.inserttime = inserttime;
        this.favorite = favorite;
        this.images = images;
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

    public Date getInserttime() {
        return inserttime;
    }

    public void setInserttime(Date inserttime) {
        this.inserttime = inserttime;
    }

    public Integer getFavorite() {
        return favorite;
    }

    public void setFavorite(Integer favorite) {
        this.favorite = favorite;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images == null ? null : images.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}
