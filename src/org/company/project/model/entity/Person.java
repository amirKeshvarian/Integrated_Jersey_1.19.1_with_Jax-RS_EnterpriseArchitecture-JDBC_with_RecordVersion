package org.company.project.model.entity;

import java.io.Serializable;

public class Person implements Serializable {
    private long personId;
    private String name;
    private String family;
    private long salary;
    private long recordVersion;

    public Person() {
    }

    public Person(String name, String family, long salary, long recordVersion) {
        this.name = name;
        this.family = family;
        this.salary = salary;
        this.recordVersion = recordVersion;
    }

    public Person(long personId, String name, String family, long salary, long recordVersion) {
        this.personId = personId;
        this.name = name;
        this.family = family;
        this.salary = salary;
        this.recordVersion = recordVersion;
    }

    public long getPersonId() {
        return personId;
    }

    public Person setPersonId(long personId) {
        this.personId = personId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public String getFamily() {
        return family;
    }

    public Person setFamily(String family) {
        this.family = family;
        return this;
    }

    public long getSalary() {
        return salary;
    }

    public Person setSalary(long salary) {
        this.salary = salary;
        return this;
    }

    public long getRecordVersion() {
        return recordVersion;
    }

    public Person setRecordVersion(long recordVersion) {
        this.recordVersion = recordVersion;
        return this;
    }
}
