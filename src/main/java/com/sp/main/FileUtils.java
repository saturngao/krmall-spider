package com.sp.main;

import java.io.File;

/**
 * @author 97646
 * @date 2020/12/11
 */
public class FileUtils {

    public void mkdirs(String path){
        File dir = new File(path);
        if (!dir.exists()){
            dir.mkdir();
        }
    }

    public static void createFiles(String filePath){

    }
}
