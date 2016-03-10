package com.ent_ubp_android.app.model.formation;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonPropertyOrder({"id"})
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes(
        {
                @JsonSubTypes.Type(value = FormationComposite.class, name = "composite"),
                @JsonSubTypes.Type(value = FormationLeaf.class, name = "leaf")
        }
)
public interface FormationComponent {
    Boolean isLeaf();
}
