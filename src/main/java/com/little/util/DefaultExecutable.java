package com.little.util;

import java.io.File;
import java.util.logging.Logger;

/**
 * @author created by qingchuan.xia
 */
public class DefaultExecutable implements Executable<File> {

    @Override
    public void execute(File file) {
        DefaultUnpackNcm unpackNcm = new DefaultUnpackNcm();
        try {
            unpackNcm.ncm2NormalFormat(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("音乐文件[" + file.getPath() + "]转码完成");
    }
}
