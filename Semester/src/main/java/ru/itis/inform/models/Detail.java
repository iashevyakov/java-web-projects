package ru.itis.inform.models;


public class Detail {

    private String detailName;

    private String firmName;

    private String nodeName;

    public Detail(String detailName,String firmName, String nodeName){this.firmName=firmName;this.detailName=detailName;this.nodeName=nodeName;}

    private int quantity;

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public String getDetailName() {
        return detailName;
    }

    public void setDetailName(String detailName) {
        this.detailName = detailName;
    }
}