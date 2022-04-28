package com.example.twowaybinding.recycleViewBinding;

public class Idol {

    private String enName;
    private String cnName;
    private String imgPath;

    public Idol(String enName, String cnName, String imgPath) {
        this.enName = enName;
        this.cnName = cnName;
        this.imgPath = imgPath;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
