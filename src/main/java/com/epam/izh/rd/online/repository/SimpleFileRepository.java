package com.epam.izh.rd.online.repository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;



public class SimpleFileRepository implements FileRepository {

    public final static String PATH = "src" + File.separator + "main" + File.separator + "resources" + File.separator;
    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директори
     * @return файлов, в том числе скрытых
     */
    @Override
    public long countFilesInDirectory(String path) {
        File folder = new File(PATH + path);
        long count = 0;
        File [] folderList = folder.listFiles();
        if (folderList == null) {
            return 0;
        }
        for (File elements: folderList) {
            if (elements.isDirectory()){
                count += countFilesInDirectory(path + File.separator + elements.getName());
            } else {
                count++;
            }
        }

    return count;
    }

    /**
     * Метод рекурсивно подсчитывает количество папок в директории, считая корень
     *
     * @param path путь до директории
     * @return число папок
     */
    @Override
    public long countDirsInDirectory(String path) {
        File folder = new File(PATH + path);
        long count = 0;
        if (folder.isDirectory()){
            for (File elements: folder.listFiles()) {
                count += countDirsInDirectory(path +File.separator + elements.getName());
            }
            count++;
        }
        return count;
    }

    /**
     * Метод копирует все файлы с расширением .txt
     *
     * @param from путь откуда
     * @param to   путь куда
     */
    @Override
    public void copyTXTFiles(String from, String to) {
    File directoryFrom = new File(from);
        for (File elements: Objects.requireNonNull(directoryFrom.listFiles())) {
            if (elements.getName().endsWith(".txt")){
                try {
                    Files.copy(elements.toPath(), new File(to).toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }



    /**
     * Метод создает файл на диске с расширением txt
     *
     * @param path путь до нового файла
     * @param name имя файла
     * @return был ли создан файл
     */
    @Override
    public boolean createFile(String path, String name){
        File file = new File(PATH + path + File.separator + name);
        try {
            file.getParentFile().mkdirs();
            return file.createNewFile();
        } catch (IOException e) {
            if (file.exists()) {
                file.delete();
            }
            return false;
        }

    }




    /**
     * Метод считывает тело файла .txt из папки src/main/resources
     *
     * @param fileName имя файла
     * @return контент
     */
    @Override
    public String readFileFromResources(String fileName) {
        String inFile = "";
        try {
            inFile = Files.lines(Paths.get(PATH + fileName)).reduce("", String::concat);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inFile;
    }
}
