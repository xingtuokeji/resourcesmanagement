package com.simtop.util;

import java.io.File;

/**
 * 不同的操作系统对应的图片存储路径
 */
public class PathUtil {
    public static final String separator = System.getProperty("file.separator");

    /**
     * 获取图片存储的服务器根目录
     * 以windows为例 D:/software/apache-tomcat-8.5.42/upload
     * @return
     */
    public static String getImageBasePath(){
        String baseImagePath = "";
        if(System.getProperty("os.name").toLowerCase().startsWith("win")){
            baseImagePath = "D:/software/apache-tomcat-8.5.42";
        }else{
            baseImagePath = "/Users/images";
        }
        return baseImagePath.replace("/",separator);
    }

    //如果目标路径不存在 自动创建
    public static void makeDirPath(String targetAddr) {
        String realFileParentPath = PathUtil.getImageBasePath() + targetAddr;
        File dirPath = new File(realFileParentPath);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
    }

    public static String getImagePathByImageTypeId(Integer imagTypeId){
        return ("/upload/image/"+imagTypeId+"/").replace("/",separator);
    }

    public static String getFilePath(){
        return ("/upload/file/").replace("/",separator);
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("os.name"));
    }
}
