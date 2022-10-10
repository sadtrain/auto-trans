package com.sadtrain.autotrans.config;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Student implements Serializable {
    private String name;
    private String age;
    private transient Country country;
    private boolean isStarted;

    public Student(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        if(!isStarted){
            isStarted = true;
            this.country = new Country();
            country.setName("China");
        }
        final StringBuilder sb = new StringBuilder("Student{");
        sb.append("name='").append(name).append('\'');
        sb.append(", age='").append(age).append('\'');
        sb.append(", country=").append(country);
        sb.append('}');
        return sb.toString();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Country country = new Country();
        country.setName("中国");
        Student student = new Student("张三", "20");
//        System.out.println(student);
        ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get("/Users/zgs/IdeaProjects/spl/master/yotta_spl/spl_core/src/main/java/cn/yottabyte/pipe/scheduler/job/report/student.txt")));
        oos.writeObject(student);
        oos.close();
        ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get("/Users/zgs/IdeaProjects/spl/master/yotta_spl/spl_core/src/main/java/cn/yottabyte/pipe/scheduler/job/report/student.txt")));
    }
}
