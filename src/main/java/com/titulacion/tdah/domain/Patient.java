package com.titulacion.tdah.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A Patient.
 */
@Entity
@Table(name = "patient")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Patient implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotNull
    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "description")
    private String description;

    @Column(name = "diagnostic")
    private String diagnostic;

    @NotNull
    @Column(name = "sex", nullable = false)
    private String sex;

    @NotNull
    @Column(name = "parent", nullable = false)
    private String parent;

    @Column(name = "birthday")
    private Instant birthday;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_parent")
    private String phoneParent;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Patient name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public Patient lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public Patient age(Integer age) {
        this.age = age;
        return this;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public Patient description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public Patient diagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
        return this;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getSex() {
        return sex;
    }

    public Patient sex(String sex) {
        this.sex = sex;
        return this;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getParent() {
        return parent;
    }

    public Patient parent(String parent) {
        this.parent = parent;
        return this;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public Instant getBirthday() {
        return birthday;
    }

    public Patient birthday(Instant birthday) {
        this.birthday = birthday;
        return this;
    }

    public void setBirthday(Instant birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public Patient address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneParent() {
        return phoneParent;
    }

    public Patient phoneParent(String phoneParent) {
        this.phoneParent = phoneParent;
        return this;
    }

    public void setPhoneParent(String phoneParent) {
        this.phoneParent = phoneParent;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Patient)) {
            return false;
        }
        return id != null && id.equals(((Patient) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Patient{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", age=" + getAge() +
            ", description='" + getDescription() + "'" +
            ", diagnostic='" + getDiagnostic() + "'" +
            ", sex='" + getSex() + "'" +
            ", parent='" + getParent() + "'" +
            ", birthday='" + getBirthday() + "'" +
            ", address='" + getAddress() + "'" +
            ", phoneParent='" + getPhoneParent() + "'" +
            "}";
    }
}
