package com.ups.oop.dto;

public class Person {
    private String id;
    private String name;
    private String lastname;
    private int age;

    public Person(){
    }

    public Person(String id, String name, String lastname, int age){
        this.id = id;
        this.name= name;
        this.lastname = lastname;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                '}';
    }
}
