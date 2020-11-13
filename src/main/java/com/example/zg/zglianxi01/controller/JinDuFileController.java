package com.example.zg.zglianxi01.controller;

import com.example.zg.zglianxi01.utils.InfoMsg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 实现文件上传下载和进度条
 */
@Controller
@RequestMapping("/upload")
public class JinDuFileController {

    private static final String TMP_PATH = "F:/download/file";

    //没有作用
    @GetMapping
    public String fileUp() {
        return "shangchuan";
    }

    @ResponseBody
    @PostMapping
    public InfoMsg fileUpload(@RequestParam("uploadFile") MultipartFile file) {
        InfoMsg infoMsg = new InfoMsg();
        if (file.isEmpty()) {
            infoMsg.setCode("error");
            infoMsg.setMsg("Please select a file to upload");
            return infoMsg;
        }
        try {
            File tmp = new File(TMP_PATH, file.getOriginalFilename());
            if (!tmp.getParentFile().exists()) {
                tmp.getParentFile().mkdirs();
            }
            String[] fileInfo = getFileInfo(tmp);
            File orRenameFile = createOrRenameFile(tmp, fileInfo[0], fileInfo[1]);
            if (tmp.renameTo(orRenameFile)) {
                file.transferTo(orRenameFile);
            }else {
                file.transferTo(tmp);
            }
            infoMsg.setCode("success");
            infoMsg.setMsg("You successfully upload" + file.getOriginalFilename());
        } catch (IOException e) {
            infoMsg.setCode("error");
            infoMsg.setMsg("Uploaded file failed");
        }
        return infoMsg;

    }

    /**
     * 创建或重命名文件
     * ps：sss.jpg    sss(1).jpg
     * @param from
     * @param toPrefix
     * @param toSuffix
     * @return
     */
    public static File createOrRenameFile(File from, String toPrefix, String toSuffix) {
        File directory = from.getParentFile();
        if (!directory.exists()) {
            if (directory.mkdir()) {
                System.out.println("Created directory " + directory.getAbsolutePath());
            }
        }
        File newFile = new File(directory, toPrefix + toSuffix);
        for (int i = 1; newFile.exists() && i < Integer.MAX_VALUE; i++) {
            newFile = new File(directory, toPrefix + "(" + i + ")" + toSuffix);
        }
        if (!from.renameTo(newFile)) {
            System.out.println("Couldn't rename file to " + newFile.getAbsolutePath());
            return from;

        }
        return newFile;
    }

    /**
     * 获取File的   . 前后字串
     * @param from
     * @return
     */
    public static String[] getFileInfo(File from) {
        String fileName = from.getName();
        int index = fileName.lastIndexOf(".");
        String toPrefix = "";
        String toSuffix = "";
        if (index == -1) {
            toPrefix = fileName;
        } else {
            toPrefix = fileName.substring(0, index);
            toSuffix = fileName.substring(index, fileName.length());
        }
        return new String[]{toPrefix, toSuffix};
    }
}
