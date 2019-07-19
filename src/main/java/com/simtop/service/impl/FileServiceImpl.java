package com.simtop.service.impl;

import com.simtop.dao.FileDao;
import com.simtop.dto.FileDto;
import com.simtop.entity.FilePojo;
import com.simtop.service.FileService;
import com.simtop.util.FileUtil;
import com.simtop.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileDao fileDao;

    @Override
    public List<FilePojo> findAll() {
         return fileDao.selectAll();
    }

    @Override
    public int add(FileDto fileDto) {
        //step1 将文件上传至服务器
        String fileName = fileDto.getUploadFileName();
        String newFileName = FileUtil.renameFile(fileName);
        String uploadRootPath = PathUtil.getFilePath();//文件存储的目录
        String basePath = PathUtil.getImageBasePath();//根目录
        PathUtil.makeDirPath(uploadRootPath);
        try {
            StreamUtils.copy(fileDto.getInputStream(),new FileOutputStream(basePath+uploadRootPath+newFileName));
            System.out.println("上传文件成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //step2 保存相对路径数据到数据库
        String uploadPath = uploadRootPath + newFileName;
        FilePojo file = new FilePojo();
        file.setFileName(fileDto.getFileName());
        file.setFileUrl(uploadPath);
        file.setFileUploader(fileDto.getFileUploader());
        return fileDao.insert(file);


    }

    @Override
    public FilePojo selectById(int id) {
        return fileDao.findById(id);
    }

    @Override
    public int modify(FileDto fileDto) {
        //step1 将文件上传至服务器
        String fileName = fileDto.getUploadFileName();
        String newFileName = FileUtil.renameFile(fileName);
        String uploadRootPath = PathUtil.getFilePath();//文件存储的目录
        String basePath = PathUtil.getImageBasePath();//根目录
        PathUtil.makeDirPath(uploadRootPath);
        try {
            StreamUtils.copy(fileDto.getInputStream(),new FileOutputStream(basePath+uploadRootPath+newFileName));
            System.out.println("上传文件成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //step2 保存相对路径数据到数据库
        String uploadPath = uploadRootPath + newFileName;
        FilePojo file = new FilePojo();
        file.setFileName(fileDto.getFileName());
        file.setFileUrl(uploadPath);
        file.setId(fileDto.getId());
        return fileDao.update(file);
    }
}
