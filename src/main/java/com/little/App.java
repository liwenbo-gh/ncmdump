package com.little;

import com.little.util.DefaultFileIterator;
import com.little.util.FileIterator;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    public final static String SAVE_FILE_PATH = "/music";
    private static FileIterator fileIterator = new DefaultFileIterator();

    public static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    public static void main(String[] args) throws Exception {
        if (args == null || args.length == 0) {
            System.out.println("输入参数为空，请输入参数");
            return;
        }
        Logger.getLogger("org.jaudiotagger").setLevel(Level.WARNING);
        for (String arg : args) {
            File file = new File(arg);
            if (!file.exists()) {
                System.out.println("参数中相关文件未找到,请检查相关路径是否存在权限问题：" + file.getPath());
                continue;
            }
            if (file.isDirectory()){
                File newFile = new File(file.getPath() + SAVE_FILE_PATH);
                if (!newFile.exists()) {
                    newFile.mkdir();
                }
            }
            iteratorFile(file);
        }
        cachedThreadPool.shutdown();
    }

    private static void iteratorFile(File rootFile) throws Exception {
        FileFilter filter = pathname -> pathname.getName().endsWith(".ncm");
        fileIterator.iterator(rootFile, filter);
    }
}
