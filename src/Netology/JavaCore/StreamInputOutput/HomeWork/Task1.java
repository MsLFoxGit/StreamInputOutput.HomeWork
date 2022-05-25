package Netology.JavaCore.StreamInputOutput.HomeWork;

import java.io.*;
import java.util.Date;

public class Task1 {
    protected static File saveGamesFolder;
    protected static File tempFile;
    protected static StringBuilder logs = new StringBuilder();

    public static void startTask() {
        addLog(" \"Задача 1: Установка.\" Приступил к выполнению.");
        File dirGames = addDir(new File("D://Games"));
        File src = addDir(new File(dirGames, "/src"));
        File res = addDir(new File(dirGames, "/res"));
        saveGamesFolder = addDir(new File(dirGames, "/savegames"));
        File temp = addDir(new File(dirGames, "/temp"));
        File main = addDir(new File(src, "/main"));
        File test = addDir(new File(src, "/test"));

        File drawables = addDir(new File(res, "/drawables"));
        File vectors = addDir(new File(res, "/vectors"));
        File icons = addDir(new File(res, "/icons"));

        File mainFile = addFile(new File(main, "Main.java"));
        File utils = addFile(new File(main, "Utils.java"));
        tempFile = addFile(new File(temp, "temp.txt"));
        File logFile = tempFile;
        addLog(" \"Задача 1: Установка.\" Выполнена.");
        logsSave(logs, tempFile, false);
    }
    public static File addDir(File newDir) {
        if (newDir.mkdir())
            addLog(" Cоздан каталог " + newDir);
        else
            addLog(" Ошибка создания каталога " + newDir);
        return newDir;
    }

    public static File addFile(File newFile) {
        try {
            newFile.createNewFile();
            addLog(" Cоздан файл " + newFile);
        } catch (IOException ex) {
            addLog(" Ошибка создания файла " + newFile);
        }
        return newFile;
    }
    public static void addLog(String logMassage) {
        logs.append(new Date() + logMassage + "\n");
    }

    public static void logsSave(StringBuilder log , File filename, boolean rewrite)  {
        try( FileWriter fW = new FileWriter(filename,rewrite)) {
            fW.write(logs.toString());
            fW.write(new Date()  + " Лог сохранен");
        }catch (IOException ex){
            addLog(ex.getMessage());
        }
        log.delete(0,log.length()-1);
    }

}
