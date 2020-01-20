package com.little.thread;

import com.little.util.Executable;

import java.io.File;

public class ExecutableThread implements Runnable {

    private Executable<File> executable;

    private File file;

    public ExecutableThread(Executable<File> executable, File file) {
        this.executable = executable;
        this.file = file;
    }

    @Override
    public void run() {
        executable.execute(file);
    }
}
