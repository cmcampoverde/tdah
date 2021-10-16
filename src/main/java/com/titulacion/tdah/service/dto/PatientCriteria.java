package com.titulacion.tdah.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.InstantFilter;

/**
 * Criteria class for the {@link com.titulacion.tdah.domain.Patient} entity. This class is used
 * in {@link com.titulacion.tdah.web.rest.PatientResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /patients?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PatientCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private IntegerFilter id;

    private StringFilter name;

    private StringFilter lastName;

    private IntegerFilter age;

    private StringFilter description;

    private StringFilter diagnostic;

    private StringFilter sex;

    private StringFilter parent;

    private InstantFilter birthday;

    private StringFilter address;

    private StringFilter phoneParent;

    public PatientCriteria() {
    }

    public PatientCriteria(PatientCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.lastName = other.lastName == null ? null : other.lastName.copy();
        this.age = other.age == null ? null : other.age.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.diagnostic = other.diagnostic == null ? null : other.diagnostic.copy();
        this.sex = other.sex == null ? null : other.sex.copy();
        this.parent = other.parent == null ? null : other.parent.copy();
        this.birthday = other.birthday == null ? null : other.birthday.copy();
        this.address = other.address == null ? null : other.address.copy();
        this.phoneParent = other.phoneParent == null ? null : other.phoneParent.copy();
    }

    @Override
    public PatientCriteria copy() {
        return new PatientCriteria(this);
    }

    public IntegerFilter getId() {
        return id;
    }

    public void setId(IntegerFilter id) {
        this.id = id;
    }

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public StringFilter getLastName() {
        return lastName;
    }

    public void setLastName(StringFilter lastName) {
        this.lastName = lastName;
    }

    public IntegerFilter getAge() {
        return age;
    }

    public void setAge(IntegerFilter age) {
        this.age = age;
    }

    public StringFilter getDescription() {
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
    }

    public StringFilter getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(StringFilter diagnostic) {
        this.diagnostic = diagnostic;
    }

    public StringFilter getSex() {
        return sex;
    }

    public void setSex(StringFilter sex) {
        this.sex = sex;
    }

    public StringFilter getParent() {
        return parent;
    }

    public void setParent(StringFilter parent) {
        this.parent = parent;
    }

    public InstantFilter getBirthday() {
        return birthday;
    }

    public void setBirthday(InstantFilter birthday) {
        this.birthday = birthday;
    }

    public StringFilter getAddress() {
        return address;
    }

    public void setAddress(StringFilter address) {
        this.address = address;
    }

    public StringFilter getPhoneParent() {
        return phoneParent;
    }

    public void setPhoneParent(StringFilter phoneParent) {
        this.phoneParent = phoneParent;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PatientCriteria that = (PatientCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(lastName, that.lastName) &&
            Objects.equals(age, that.age) &&
            Objects.equals(description, that.description) &&
            Objects.equals(diagnostic, that.diagnostic) &&
            Objects.equals(sex, that.sex) &&
            Objects.equals(parent, that.parent) &&
            Objects.equals(birthday, that.birthday) &&
            Objects.equals(address, that.address) &&
            Objects.equals(phoneParent, that.phoneParent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        name,
        lastName,
        age,
        description,
        diagnostic,
        sex,
        parent,
        birthday,
        address,
        phoneParent
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PatientCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (lastName != null ? "lastName=" + lastName + ", " : "") +
                (age != null ? "age=" + age + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (diagnostic != null ? "diagnostic=" + diagnostic + ", " : "") +
                (sex != null ? "sex=" + sex + ", " : "") +
                (parent != null ? "parent=" + parent + ", " : "") +
                (birthday != null ? "birthday=" + birthday + ", " : "") +
                (address != null ? "address=" + address + ", " : "") +
                (phoneParent != null ? "phoneParent=" + phoneParent + ", " : "") +
            "}";
    }

}
