package com.maxton.jpa.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 实体类
 *
 * @Author maxton.zhang
 */
@Entity
@Table(name = "people")
public class People implements Serializable {
    @Id
    private String pid;//
    private String pname;//
    private String sex;//

    @OneToMany(mappedBy = "people")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Article> articleList;

    public People() {
    }

    public People(String pid, String pname, String sex, List<Article> articleList) {
        this.pid = pid;
        this.pname = pname;
        this.sex = sex;
        this.articleList = articleList;
    }


    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


}
