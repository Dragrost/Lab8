package org.dataBaseClass;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student")
public class Student extends PanacheEntity {

    @Column(length = 20)
    private String name;
    @Column(length = 20)
    private String surname;
    @Column
    private String age;

    public Student(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
    public String getInfo(){
        return name + " " + surname;
    }
}
