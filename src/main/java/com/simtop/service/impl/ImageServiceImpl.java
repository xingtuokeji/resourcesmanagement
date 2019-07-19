package com.simtop.service.impl;

import com.simtop.dao.ImageDao;
import com.simtop.dto.ImageDto;
import com.simtop.entity.Image;
import com.simtop.entity.ImageType;
import com.simtop.service.ImageService;
import com.simtop.util.FileUtil;
import com.simtop.util.PathUtil;
import com.simtop.vo.ImageParamsVo;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Service
@Transactional
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageDao imageDao;

    @Override
    public int add(ImageDto imageDto) throws IOException {
        //将dto -< pojo
        //将文件存入服务器指定位置 todo
        /**
         * private Integer id;
         *     private String imageName;
         *     private Integer imageTypeId;
         *     private String imageDesc;
         *     private InputStream inputStream;//图片文件输入流
         *     private String fileName;//图片名字
         *     private String uploadPath;//图片的上传目录
         */
        System.out.println("获取前台传递过来的文件名称");
        String fileName = imageDto.getFileName();
        System.out.println(fileName);
        System.out.println("对文件名称进行处理");
        String newFileName = FileUtil.renameFile(fileName);
        System.out.println(newFileName);
        String targetAddr = PathUtil.getImagePathByImageTypeId(imageDto.getImageTypeId());
        String baseImagePath = PathUtil.getImageBasePath();
        PathUtil.makeDirPath(targetAddr);
        try {
            // todo 使用StreamUtils传输图片文件很慢
            StreamUtils.copy(imageDto.getInputStream(),new FileOutputStream(baseImagePath+targetAddr+newFileName));
            System.out.println(baseImagePath+targetAddr+newFileName);
            //  D:\\software\\apache-tomcat-8.5.42\\upload\\image\\18\\20190716102219_1239198_logo.png
            System.out.println("文件上传成功！");
        }catch (Exception e){
            throw new RuntimeException("文件传输失败！");
        }
        //将路径存入数据库
        System.out.println("获取文件上传的目录");
        String uploadPath = PathUtil.getImagePathByImageTypeId(imageDto.getImageTypeId())+newFileName;
        //System.out.println("存入数据库的图片相对路径为："+uploadPath);
        //todo 将数据保存到数据库 dto<pojo
        Image image = new Image();
        try {
            PropertyUtils.copyProperties(image,imageDto);
            image.setImageUrl(uploadPath);
            ImageType imageType = new ImageType();
            imageType.setId(imageDto.getImageTypeId());
            image.setImageType(imageType);
            return imageDao.insert(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;//保存失败
    }

    @Override
    public List<Image> findAll() {
        return imageDao.selectAll();
    }

    @Override
    public Image findById(int id) {
        return imageDao.selectById(id);
    }

    @Override
    public void getImage(String path, OutputStream outputStream) {
        try{
            StreamUtils.copy(new FileInputStream(path),outputStream);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public String findImageUrlById(int id) {
        return imageDao.selectImgUrlById(id);
    }

    @Override
    public int modify(ImageDto imageDto) {
        System.out.println("获取前台传递过来的文件名称");
        String fileName = imageDto.getFileName();
        System.out.println(fileName);
        System.out.println("对文件名称进行处理");
        String newFileName = FileUtil.renameFile(fileName);
        System.out.println(newFileName);
        String targetAddr = PathUtil.getImagePathByImageTypeId(imageDto.getImageTypeId());
        String baseImagePath = PathUtil.getImageBasePath();
        PathUtil.makeDirPath(targetAddr);
        try {
            // todo 使用StreamUtils传输图片文件很慢
            StreamUtils.copy(imageDto.getInputStream(),new FileOutputStream(baseImagePath+targetAddr+newFileName));
            System.out.println(baseImagePath+targetAddr+newFileName);
            //  D:\\software\\apache-tomcat-8.5.42\\upload\\image\\18\\20190716102219_1239198_logo.png
            System.out.println("文件上传成功！");
        }catch (Exception e){
            throw new RuntimeException("文件传输失败！");
        }
        //将路径存入数据库
        System.out.println("获取文件上传的目录");
        String uploadPath = PathUtil.getImagePathByImageTypeId(imageDto.getImageTypeId())+newFileName;
        //System.out.println("存入数据库的图片相对路径为："+uploadPath);
        //todo 将数据保存到数据库 dto<pojo
        Image image = new Image();
        try {
            PropertyUtils.copyProperties(image,imageDto);
            image.setImageUrl(uploadPath);
            ImageType imageType = new ImageType();
            imageType.setId(imageDto.getImageTypeId());
            image.setImageType(imageType);
            return imageDao.update(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;//保存失败
    }

    @Override
    public int deleteById(int id) {
        return imageDao.removeById(id);
    }

    @Override
    public List<Image> findByParams(ImageParamsVo params) {
        return imageDao.selectByParams(params);
    }

}
