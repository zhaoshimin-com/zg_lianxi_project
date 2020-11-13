package com.example.zg.zglianxi01.utils;


import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class FileUtils {

    /**
     * 下载文件
     * @param request
     * @param response
     * @param fileName
     * @return
     */
    public static String downloadFiles(HttpServletRequest request, HttpServletResponse response,String fileName){

        if(StringUtils.isEmpty(fileName)){
            return "文件名称为空";
        }

        //设置路径
        ClassPathResource classPathResource = new ClassPathResource("templates/"+fileName);
        File file = null;
        try {
            file = classPathResource.getFile();
        }catch (IOException e) {
            e.printStackTrace();
            return "文件不存在";
        }

        response.setHeader("content-type","application/octet-stream");
        //设置强制下载不打开
        response.setContentType("application/force-download");
        //设置文件名
        response.addHeader("Content-Disposition","attachment;fileName=" + fileName);

        byte[] buffer = new byte[1024];
        InputStream fis = null;
        BufferedInputStream bis = null;

        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "文件下载成功";
    }

    /**
     * 判断文件大小
     *
     * @param file
     * @param size
     * @param unit
     * @return
     */
    public static boolean checkFileSize(MultipartFile file, int size, String unit) {
        if (file.isEmpty() || StringUtils.isEmpty(size) || StringUtils.isEmpty(unit)) {
            return false;
        }
        long len = file.getSize();
        double fileSize = 0;
        if ("B".equals(unit.toUpperCase())) {
            fileSize = (double) len;
        } else if ("K".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1024;
        } else if ("M".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1048576;
        } else if ("G".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1073741824;
        }
        if (fileSize > size) {
            return false;
        }
        return true;
    }
}
