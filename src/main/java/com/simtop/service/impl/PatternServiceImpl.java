package com.simtop.service.impl;

import com.simtop.dao.PatternDao;
import com.simtop.entity.Pattern;
import com.simtop.service.PatternService;
import com.simtop.util.FileUtil;
import com.simtop.util.PathUtil;
import com.simtop.vo.PatternParamsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@Service
@Transactional
public class PatternServiceImpl implements PatternService {

    @Autowired
    private PatternDao patternDao;

    @Override
    public List<Pattern> findAll() {
        return patternDao.selectAll();
    }

//    @Override
//    public int add(PatternDto patternDto) throws IOException {
//        String fileName = patternDto.getFileName();
//        String newFileName = FileUtil.renameFile(fileName);
//        PathUtil.makeDirPath(PathUtil.getPatternPathByPatternTypeId(patternDto.getPatternTypeId()));
//        //上传模型流到服务器
//        StreamUtils.copy(patternDto.getInputStream(),new FileOutputStream(PathUtil.getImageBasePath()+PathUtil.getPatternPathByPatternTypeId(patternDto.getPatternTypeId())+newFileName));
//
//        //保存模型路径至数据库
//        Pattern pattern = new Pattern();
//        pattern.setPatternName(patternDto.getPatternName());
//        pattern.setPatternDesc(patternDto.getPatternDesc());
//        pattern.setPatternUrl(PathUtil.getPatternPathByPatternTypeId(patternDto.getPatternTypeId())+newFileName);
//        PatternType patternType = new PatternType();
//        patternType.setId(patternDto.getPatternTypeId());
//        pattern.setPatternType(patternType);
//        pattern.setPatternUploader(patternDto.getPatternUploader());
//        return patternDao.add(pattern);
//    }

    @Override
    public Pattern findById(Integer id) {
        return patternDao.selectById(id);
    }

    @Override
    public String findPatternUrlById(int id) {
        return patternDao.selectPatternUrlById(id);
    }

    @Override
    public void getPattern(String absolutePath, OutputStream outputStream) throws IOException {
        StreamUtils.copy(new FileInputStream(absolutePath),outputStream);
    }

    @Override
    public int removeById(Integer id) {
        return patternDao.deleteById(id);
    }

    @Override
    public List<Pattern> findByParams(PatternParamsVo params) {
        return patternDao.selectByParams(params);

    }

//    @Override
//    public int modify(PatternDto patternDto) throws IOException {
//        String fileName = patternDto.getFileName();
//        String newFileName = FileUtil.renameFile(fileName);
//        PathUtil.makeDirPath(PathUtil.getPatternPathByPatternTypeId(patternDto.getPatternTypeId()));
//        //上传模型流到服务器
//        StreamUtils.copy(patternDto.getInputStream(),new FileOutputStream(PathUtil.getImageBasePath()+PathUtil.getPatternPathByPatternTypeId(patternDto.getPatternTypeId())+newFileName));
//
//        //保存模型路径至数据库
//        Pattern pattern = new Pattern();
//        pattern.setPatternName(patternDto.getPatternName());
//        pattern.setPatternDesc(patternDto.getPatternDesc());
//        pattern.setPatternUrl(PathUtil.getPatternPathByPatternTypeId(patternDto.getPatternTypeId())+newFileName);
//        PatternType patternType = new PatternType();
//        patternType.setId(patternDto.getPatternTypeId());
//        pattern.setPatternType(patternType);
//        pattern.setPatternUploader(patternDto.getPatternUploader());
//        pattern.setId(patternDto.getId());
//        return patternDao.updateSome(pattern);
//    }

    @Override
    public int add(Pattern pattern, MultipartFile[] files) throws IOException {
        System.out.println("进入业务层");
        //上传模型（2个文件至服务器）
        for(int i=0;i<files.length;i++){
            String fileName = files[i].getOriginalFilename();
            String newFileName = FileUtil.renameFile(fileName);
            PathUtil.makeDirPath(PathUtil.getPatternPathByPatternTypeId(pattern.getPatternType().getId()));
            StreamUtils.copy(files[i].getInputStream(),new FileOutputStream(PathUtil.getImageBasePath()+PathUtil.getPatternPathByPatternTypeId(pattern.getPatternType().getId())+newFileName));
            if(i==0){
                //保存文件路径至数据库
                String patternUrl = PathUtil.getPatternPathByPatternTypeId(pattern.getPatternType().getId())+newFileName;
                pattern.setPatternUrl(patternUrl);
            }
            if(i==1){
                String patternFileUrl = PathUtil.getPatternPathByPatternTypeId(pattern.getPatternType().getId())+newFileName;
                pattern.setPatternFileUrl(patternFileUrl);
            }
        }
        return patternDao.add(pattern);
    }

    @Override
    public int modify(Pattern pattern, MultipartFile[] files) throws IOException {
        System.out.println("进入业务层");
        //上传模型（2个文件至服务器）
        for(int i=0;i<files.length;i++){
            String fileName = files[i].getOriginalFilename();
            String newFileName = FileUtil.renameFile(fileName);
            PathUtil.makeDirPath(PathUtil.getPatternPathByPatternTypeId(pattern.getPatternType().getId()));
            StreamUtils.copy(files[i].getInputStream(),new FileOutputStream(PathUtil.getImageBasePath()+PathUtil.getPatternPathByPatternTypeId(pattern.getPatternType().getId())+newFileName));
            if(i==0){
                //保存文件路径至数据库
                String patternUrl = PathUtil.getPatternPathByPatternTypeId(pattern.getPatternType().getId())+newFileName;
                pattern.setPatternUrl(patternUrl);
            }
            if(i==1){
                String patternFileUrl = PathUtil.getPatternPathByPatternTypeId(pattern.getPatternType().getId())+newFileName;
                pattern.setPatternFileUrl(patternFileUrl);
            }
        }
        return patternDao.updateSome(pattern);
    }
}
