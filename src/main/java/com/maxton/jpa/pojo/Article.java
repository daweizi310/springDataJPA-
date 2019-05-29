package com.maxton.jpa.pojo;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 实体类
 *
 * @Author maxton.zhang
 */
@Entity
@Table(name = "article")
public class Article implements Serializable {
    @Id
    private String aid;//

    private String title;//
    private String content;//
//    private String pid;//

    @ManyToOne
    @JoinColumn(name = "pid") // 上面注释掉的id
    @NotFound(action = NotFoundAction.IGNORE)  // 为防止作者名下没有文章,会报错
    private People people;

    public Article() {
    }

    public Article(String aid, String title, String content, People people) {
        this.aid = aid;
        this.title = title;
        this.content = content;
        this.people = people;
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
