package com.belstu.drevoten.DB_KP.model;

public class UserType {
    private String AdminID;
    private String Type;

    public String getAdminID() {
        return AdminID;
    }
    public void setAdminID(String adminID) {
        AdminID = adminID;
    }
    public String getType() {
        return Type;
    }
    public void setType(String type) {
        Type = type;
    }

    public UserType(String adminID, String type) {
        AdminID = adminID;
        Type = type;
    }
}
