package com.little.util;

import java.io.File;
import java.io.FileFilter;

public interface FileIterator {

    /**
     * 遍历方法
     *
     * @param rootFile 开始遍历的根目录
     * @param filter   过滤制定特征的文件
     */
    void iterator(File rootFile, FileFilter filter);

}
