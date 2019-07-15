package com.simtop.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * 文件处理相关工具类
 */
public class FileUtil {

    /**
     * 生成由当前系统时间yyyyMMyyHHmmss+5位随机数+原始文件名组成的新文件名
     * @param fileName
     * @return
     */
    public static String renameFile(String fileName){
        String newFileName = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timeStr = sdf.format(new Date());
        String sixRandomNum = generateSixRandom();
        newFileName = timeStr+"_"+sixRandomNum+"_"+fileName;
        return newFileName;
    }

    private static String generateSixRandom() {
        Random random = new Random();
        int a = random.nextInt(899999)+1000000;
        return String.valueOf(a);
    }

    public static void main(String[] args) {
        System.out.println("测试新文件的生成");
        String fileName = renameFile("background.png");
        System.out.println(fileName);//20190715145954_1352585_background.png
        System.out.println(UUID.randomUUID().toString());//8a140105-5966-4454-99c6-af53639ffb5d 36字符
    }


}
