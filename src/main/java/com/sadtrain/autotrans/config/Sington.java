package com.sadtrain.autotrans.config;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Sington{

    private static Sington instance = new Sington();

    private Sington() {
    }

    public static Sington getInstance() {
        return instance;
    }

    public Object readResolve() {
        return instance;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Sington instance = Sington.getInstance();
        ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get("/Users/zgs/IdeaProjects/spl/master/yotta_spl/spl_core/src/main/java/cn/yottabyte/pipe/scheduler/job/report/sington.txt")));
        oos.writeObject(instance);
        oos.close();
        ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get("/Users/zgs/IdeaProjects/spl/master/yotta_spl/spl_core/src/main/java/cn/yottabyte/pipe/scheduler/job/report/sington.txt")));
        Sington instance2 = (Sington) ois.readObject();
        System.out.println(instance2==instance);
    }
}
