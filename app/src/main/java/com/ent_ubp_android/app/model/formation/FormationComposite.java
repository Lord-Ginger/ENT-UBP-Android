package com.ent_ubp_android.app.model.formation;

import com.ent_ubp_android.app.exception.ModelConstraintViolationException;
import com.fasterxml.jackson.annotation.*;
import com.google.common.base.Objects;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@JsonPropertyOrder({"id"})
public class FormationComposite implements FormationComponent {

    private final String name;
    private final List<FormationComponent> formations;
    private Long id;

    private Boolean isLeafContainer;

    @JsonCreator
    public FormationComposite(@JsonProperty("name") final String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " without a name");
        }
        this.isLeafContainer = Boolean.TRUE;
        this.name = name;
        this.formations = new ArrayList<>();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public List<FormationComponent> getFormations() {
        return Collections.unmodifiableList(formations);
    }

    @Override
    @JsonIgnore
    public final Boolean isLeaf() {
        return Boolean.FALSE;
    }

    public void addFormation(FormationComponent formation) {
        if (formation == null) {
            throw new IllegalArgumentException("Cannot add a null " + FormationComponent.class.getName() + " to a " + getClass().getName());
        }

        if (formation.isLeaf()) {
            this.addElement((FormationLeaf) formation);
        } else {
            this.addElement((FormationComposite) formation);
        }
    }

    private void addElement(FormationComposite formation) {
        if (this.formations.isEmpty()) {
            this.isLeafContainer = Boolean.FALSE;
        }
        if (this.isLeafContainer) {
            throw new ModelConstraintViolationException("A " + getClass().getName() + " can contains only one of types : " + FormationComposite.class.getName() + " and " + FormationLeaf.class.getName());
        }

        this.formations.add(formation);
    }

    private void addElement(FormationLeaf formation) {
        if (this.formations.isEmpty()) {
            this.isLeafContainer = Boolean.TRUE;
        }
        if (!this.isLeafContainer) {
            throw new ModelConstraintViolationException("A " + getClass().getName() + " can contains only one of types : " + FormationComposite.class.getName() + " and " + FormationLeaf.class.getName());
        }

        this.formations.add(formation);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormationComposite other = (FormationComposite) o;
        if (this.getId() == null || other.getId() == null) return false;
        return Objects.equal(this.getId(), other.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getId());
    }

}
