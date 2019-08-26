package itmayi.stream;

import java.util.Objects;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 *
 * @author songjd
 * @date 9:47 2019/8/26.
 */
public class Student {
    private Long id;

    private String name;

    private int age;

    private String address;

    public Student() {
    }

    public Student(Long id, String name, int age, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age &&
                Objects.equals(id, student.id) &&
                Objects.equals(name, student.name) &&
                Objects.equals(address, student.address);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, age, address);
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
