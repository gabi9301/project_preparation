package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Parent {
    @Id @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Child> childList = new ArrayList<>();

    public void addChild(Child child) { //양방향 연관관계 셋팅해주는 편의 메서드
        childList.add(child);   
        child.setParent(this);
    }

    public List<Child> getChildList() {
        return childList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
