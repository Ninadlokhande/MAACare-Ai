package com.sigma.model;

public class DashboardModel {

    private int pregnantWomen;
    private int children;
    private int homeVisits;
    private int immunizations;

    public DashboardModel() {
    }

    public int getPregnantWomen() {
        return pregnantWomen;
    }

    public void setPregnantWomen(int pregnantWomen) {
        this.pregnantWomen = pregnantWomen;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public int getHomeVisits() {
        return homeVisits;
    }

    public void setHomeVisits(int homeVisits) {
        this.homeVisits = homeVisits;
    }

    public int getImmunizations() {
        return immunizations;
    }

    public void setImmunizations(int immunizations) {
        this.immunizations = immunizations;
    }
}