package uk.ac.herc.dhs.service.dto;

import java.io.Serializable;

public class QuestionItemDTO implements Serializable {

    private Long id;

    public Integer order;

    private String name;

    private String label;

    private Boolean mandatory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean getMandatory() {
        return this.mandatory;
    }

    public void setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
    }

    @Override
    public String toString() {
        return "QuestionItemDTO [" +
                "id=" + id +
                ", order=" + order +
                ", label=" + label +
                ", name=" + name +
                ", mandatory=" + mandatory +
                "]";
    }
}
