package com.simtop.vo;

public class ImageParamsVo {
    private String imageName;
    private Integer imageTypeId;

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Integer getImageTypeId() {
        return imageTypeId;
    }

    public void setImageTypeId(Integer imageTypeId) {
        this.imageTypeId = imageTypeId;
    }

    @Override
    public String toString() {
        return "ImageParamsVo{" +
                "imageName='" + imageName + '\'' +
                ", imageTypeId=" + imageTypeId +
                '}';
    }
}
