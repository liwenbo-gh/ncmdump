package com.little.util;

import com.little.App;
import com.little.thread.ExecutableThread;

import java.io.File;
import java.io.FileFilter;

/**
 *
 */
public class DefaultFileIterator implements FileIterator {
    @Override
    public void iterator(File rootFile, final FileFilter filter) {
        Executable<File> executable = new DefaultExecutable();
        if (!rootFile.isDirectory()){
            ExecutableThread thread = new ExecutableThread(executable, rootFile);
            App.cachedThreadPool.execute(thread);
            return;
        }

        File[] files = rootFile.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory() || filter.accept(pathname);
            }
        });

        if (files != null && files.length > 0){
            for (File file : files){
                iterator(file,filter);
            }
        }
    }
}
