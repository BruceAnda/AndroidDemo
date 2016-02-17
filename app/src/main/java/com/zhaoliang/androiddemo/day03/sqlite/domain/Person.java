package com.zhaoliang.androiddemo.day03.sqlite.domain;

/**
 * Created by pro on 16/2/3.
 */
public class Person {

    public int id;
    public String name;
    public String sex;
    public String salary;
    public String address;

    public Person() {
    }

    public Person(int id, String name, String sex, String salary, String address) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.salary = salary;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", salary='" + salary + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
