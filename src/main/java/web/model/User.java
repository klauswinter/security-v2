package web.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "job")
    private String job;

    @Column(name = "age")
    private int age;

    public User() {}

    public User(String name, String surname, String job, int age) {
        this.name = name;
        this.surname = surname;
        this.job = job;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User {" +
                "id= " + id +
                ", name= '" + name + '\'' +
                ", surname= '" + surname + '\'' +
                ", job= '" + job + '\'' +
                ", age= " + age +
                '}';
    }
}
