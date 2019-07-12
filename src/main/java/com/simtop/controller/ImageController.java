package com.simtop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 素材管理
 */
@Controller
@RequestMapping("/image")
public class ImageController {

    @RequestMapping(value = "/main",method = RequestMethod.GET)
    public String fileUploadMainJsp(){

        return "fileupload";
    }



}
