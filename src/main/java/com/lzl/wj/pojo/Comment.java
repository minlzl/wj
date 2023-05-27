package com.lzl.wj.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "comments")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Comment implements Comparable<Comment>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private Integer talk_id;
    private String content;
    private Integer from_id;
    private Integer to_id;
    private Timestamp pubtime;

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", talk_id=" + talk_id +
                ", content='" + content + '\'' +
                ", from_id=" + from_id +
                ", to_id=" + to_id +
                ", pubtime=" + pubtime +
                '}';
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTalk_id() {
        return talk_id;
    }

    public void setTalk_id(Integer talk_id) {
        this.talk_id = talk_id;
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

    public Comment(Integer id, Integer talk_id, String content, Integer from_id, Integer to_id, Timestamp pubtime) {
        this.id = id;
        this.talk_id = talk_id;
        this.content = content;
        this.from_id = from_id;
        this.to_id = to_id;
        this.pubtime = pubtime;
    }

    public Comment() {
    }

    @Override
    public int compareTo(Comment o) {
        if(o.getPubtime().getTime()>pubtime.getTime())
            return 1;
        return -1;
    }
}
