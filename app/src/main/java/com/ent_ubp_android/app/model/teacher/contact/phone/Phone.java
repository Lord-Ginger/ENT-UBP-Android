package com.ent_ubp_android.app.model.teacher.contact.phone;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.base.Objects;

@JsonPropertyOrder({"id"})
public class Phone {

    private Long id;
    private PhoneDetails details;

    @JsonCreator
    public Phone(@JsonProperty("details") PhoneDetails details) {
        if (details == null) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " without a phone");
        }
        this.details = details;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PhoneDetails getDetails() {
        return details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return Objects.equal(details, phone.details);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(details);
    }

}
