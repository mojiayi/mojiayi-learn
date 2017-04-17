package list;

/**
 * Created by Administrator on 2016/12/26.
 */
public class Person {
    private Integer age;
    private String name;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void modifyAge() {
        this.setAge(this.age-1);
    }

    public void modifyAge(Integer age) {
        age = age-1;
    }

    public void modifyAge(int age) {
        age = age-1;
    }
}
