package com.doubledd.file.cloud;

import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author kedong
 */
public class FileUtils {
    private FileUtils() {
    }


    public static File mkdirIfNotExist(String path) throws IOException {
        if (StringUtils.isEmpty(path)) {
            return null;
        }

        File file = new File(path);
        if (!file.exists()) {
            boolean mkdir = file.mkdirs();
            if (!mkdir) {
                //创建文件夹失败
                throw new IOException("创建文件夹失败");
            }
        }
        return file;
    }
}
