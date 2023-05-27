package com.lzl.wj.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "talk")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Talk implements Comparable<Talk>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "talk_id")
    private Integer talk_id;
    private Integer user_id;
    private Timestamp publish_data;
    private String content;
    private Integer comment_number;
    private Integer love_number;

    @Override
    public int compareTo(Talk o) {
        if(o.getPublish_data().getTime()>publish_data.getTime())
            return 1;
        return -1;
    }
}
