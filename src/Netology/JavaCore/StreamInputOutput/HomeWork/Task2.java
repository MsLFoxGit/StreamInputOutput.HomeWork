package Netology.JavaCore.StreamInputOutput.HomeWork;

import java.io.*;
import java.util.*;
import java.util.zip.*;

public class Task2 {

    private static List<String> savedFilesList = new ArrayList<>();
    protected static String pathToZip;
    protected static String pathToSave;

    protected static void startTask() {
        Task1.addLog(" \"Задача 2: Сохранение.\" Приступил к выполнению.");
        GameProgress game10 = new GameProgress(10, 10, 10, 10.0);
        GameProgress game20 = new GameProgress(20, 20, 20, 20.0);
        GameProgress game30 = new GameProgress(30, 30, 30, 30.0);

        pathToSave = Task1.saveGamesFolder.toString();
        pathToZip =  pathToSave + "\\games.zip";

        savegames(pathToSave, game10);
        savegames(pathToSave, game20);
        savegames(pathToSave, game30);

        zipFiles(pathToZip);
        deleteSavedFiles(savedFilesList);

        Task1.addLog(" \"Задача 2: Сохранение.\" Выполнена.");
        Task1.logsSave(Task1.logs, Task1.tempFile, true);
    }

    public static void savegames(String pathToSave, GameProgress game) {
        String filePath = pathToSave + "\\save" + savedFilesList.size() + ".dat";
        try (FileOutputStream fos = new FileOutputStream(filePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(game);
            Task1.addLog(" Объект класса: " + game.getClass().getSimpleName() + " сохранен в файл " + filePath);
            savedFilesList.add(filePath);
        } catch (Exception ex) {
            Task1.addLog(" Ошибка сохранения : " + game);
        }
    }

    public static void zipFiles(String pathToZip) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(pathToZip))) {
            for (int i = 0; i < savedFilesList.size(); i++) {
                FileInputStream fis = new FileInputStream(savedFilesList.get(i));
                ZipEntry entry = new ZipEntry("packedSave" + i + ".dat");
                zout.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.write(buffer);
                zout.closeEntry();
                fis.close();
                Task1.addLog(" В архив " + pathToZip + " добавлен файл: " + entry.getName());
            }
        } catch (IOException ex) {
            Task1.addLog(ex.getMessage());
        }
    }

    public static void deleteSavedFiles(List<String> savedFiles) {
        for (int i = 0; i < savedFiles.size(); i++) {
            File file = new File(savedFiles.get(i));
            if (file.delete()) Task1.addLog(" Удален файл: " + file);
            else Task1.addLog(" Ошибка удаления файла: " + file);
        }
    }
}

