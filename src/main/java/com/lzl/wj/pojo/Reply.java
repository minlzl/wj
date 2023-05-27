package com.lzl.wj.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "reply")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Reply implements Comparable<Reply>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private Integer comment_id;
    private Integer reply_id;
    private String content;
    private Integer from_id;
    private Integer to_id;
    private Timestamp pubtime;

    @Override
    public String toString() {
        return "Reply{" +
                "id=" + id +
                ", comment_id=" + comment_id +
                ", reply_id=" + reply_id +
                ", content='" + content + '\'' +
                ", from_id=" + from_id +
                ", to_id=" + to_id +
                ", pubtime=" + pubtime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getComment_id() {
        return comment_id;
    }

    public void setComment_id(Integer comment_id) {
        this.comment_id = comment_id;
    }

    public Integer getReply_id() {
        return reply_id;
    }

    public void setReply_id(Integer reply_id) {
        this.reply_id = reply_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getFrom_id() {
        return from_id;
    }

    public void setFrom_id(Integer from_id) {
        this.from_id = from_id;
    }

    public Integer getTo_id() {
        return to_id;
    }

    public void setTo_id(Integer to_id) {
        this.to_id = to_id;
    }

    public Timestamp getPubtime() {
        return pubtime;
    }

    public void setPubtime(Timestamp pubtime) {
        this.pubtime = pubtime;
    }

    public Reply(Integer id, Integer comment_id, Integer reply_id, String reply_type, String content, Integer from_id, Integer to_id, Timestamp pubtime) {
        this.id = id;
        this.comment_id = comment_id;
        this.reply_id = reply_id;
        this.content = content;
        this.from_id = from_id;
        this.to_id = to_id;
        this.pubtime = pubtime;
    }

    public Reply() {
    }

    @Override
    public int compareTo(Reply o) {
        if(pubtime.getTime()>o.pubtime.getTime())
            return 1;
        return -1;
    }
}
