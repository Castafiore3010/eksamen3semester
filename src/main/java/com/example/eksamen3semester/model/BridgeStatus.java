package com.example.eksamen3semester.model;

import javax.persistence.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

@Entity
@Table(name = "bridge")
public class BridgeStatus {
    @Id
    private Long bridge_id;

    private String status;

    @Transient
    private PropertyChangeSupport support;




    public BridgeStatus() {
        support = new PropertyChangeSupport(this);
    }
    public BridgeStatus(Long bridge_id, String status) {
        this.bridge_id = bridge_id;
        this.status = status;
        support = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener) {
        support.addPropertyChangeListener(propertyChangeListener);
    }

    public void removePropertyChangeListener(PropertyChangeListener propertyChangeListener) {
        support.removePropertyChangeListener(propertyChangeListener);
    }

    public void setUp(String status) {
        support.firePropertyChange("isUp",this.status, status);
        this.status = status;
    }

    public Long getBridge_id() {
        return bridge_id;
    }

    public void setBridge_id(Long bridge_id) {
        this.bridge_id = bridge_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
