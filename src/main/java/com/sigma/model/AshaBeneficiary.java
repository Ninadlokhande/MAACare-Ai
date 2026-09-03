package com.sigma.model;

public class AshaBeneficiary {

    private int id;
    private String name;
    private String category;
    private String age;
    private String village;
    private String status;
    private String lastVisit;


    private String userUid;

    // =========================================================
    // EMPTY CONSTRUCTOR
    // REQUIRED FOR FIRESTORE
    // =========================================================

    public AshaBeneficiary() {
    }

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public AshaBeneficiary(
            int id,
            String name,
            String category,
            String age,
            String village,
            String status,
            String lastVisit) {

        this.id = id;
        this.name = name;
        this.category = category;
        this.age = age;
        this.village = village;
        this.status = status;
        this.lastVisit = lastVisit;
    }

    // =========================================================
    // ID
    // =========================================================

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // =========================================================
    // NAME
    // =========================================================

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // =========================================================
    // CATEGORY
    // =========================================================

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // =========================================================
    // AGE
    // =========================================================

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    // =========================================================
    // VILLAGE
    // =========================================================

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    // =========================================================
    // STATUS
    // =========================================================

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // =========================================================
    // LAST VISIT
    // =========================================================

    public String getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(String lastVisit) {
        this.lastVisit = lastVisit;
    }

    public String getUserUid() {
    return userUid;
}
   public void setUserUid(String userUid) {
    this.userUid = userUid;
}

    // =========================================================
    // TO STRING
    // =========================================================

    @Override
    public String toString() {

        return "AshaBeneficiary{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", age='" + age + '\'' +
                ", village='" + village + '\'' +
                ", status='" + status + '\'' +
                ", lastVisit='" + lastVisit + '\'' +
                '}';
    }
}
