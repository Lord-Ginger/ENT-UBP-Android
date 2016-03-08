package com.ent_ubp_android.app.model.formation;

import java.util.Objects;

public class FormationLeaf implements FormationComponent {

    private final String name;
    private Long id;

    public FormationLeaf(final String name) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("Cannot build a " + getClass().getName() + " without a name");
        }
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public final Boolean isLeaf() {
        return Boolean.TRUE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormationLeaf other = (FormationLeaf) o;
        if (this.getId() == null || other.getId() == null) return false;
        return Objects.equals(this.getId(), other.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getId());
    }

}
