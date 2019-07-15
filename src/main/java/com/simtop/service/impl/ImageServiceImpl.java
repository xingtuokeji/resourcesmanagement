package com.simtop.service.impl;

import com.simtop.dao.ImageDao;
import com.simtop.dto.ImageDto;
import com.simtop.entity.Image;
import com.simtop.entity.ImageType;
import com.simtop.service.ImageService;
import com.simtop.util.FileUtil;
import com.simtop.util.PathUtil;
import com.sun.imageio.plugins.common.ImageUtil;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
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
        StreamUtils.copy(imageDto.getInputStream(),new FileOutputStream(baseImagePath+targetAddr+newFileName));
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
}
