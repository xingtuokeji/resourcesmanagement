package com.simtop.entity;

/**
 * 统计相关的实体类
 */
public class Count {
    private Integer id;//主键
    private Image image;//图片id
    private Integer imageDownloadNums;//图片下载数据量


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Integer getImageDownloadNums() {
        return imageDownloadNums;
    }

    public void setImageDownloadNums(Integer imageDownloadNums) {
        this.imageDownloadNums = imageDownloadNums;
    }

    @Override
    public String toString() {
        return "Count{" +
                "id=" + id +
                ", image=" + image +
                ", imageDownloadNums=" + imageDownloadNums +
                '}';
    }
}
