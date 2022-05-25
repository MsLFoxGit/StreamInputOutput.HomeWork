package Netology.JavaCore.StreamInputOutput.HomeWork;

import java.io.*;
import java.util.zip.*;

public class Task3 {

    private static String pathToLastUnZipGame;

    public static void startTask() {
        Task1.addLog(" \"Задача 3: Загрузка (со звездочкой *)\" Приступил к выполнению.");
        openZip(Task2.pathToZip, Task2.pathToSave);
        System.out.println(openProgress(pathToLastUnZipGame));
        Task1.addLog(" \"Задача 3: Загрузка (со звездочкой *)\" Выполнена.");
        Task1.logsSave(Task1.logs, Task1.tempFile, true);
    }

    public static void openZip(String pathToZipFile, String folderToUnzipFiles) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(pathToZipFile))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                FileOutputStream fout = new FileOutputStream(pathToLastUnZipGame = folderToUnzipFiles + "\\un" + entry.getName());
                int c;
                while ((c = zin.read()) != -1) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
                Task1.addLog(" Файл извлечен из архива: " + pathToLastUnZipGame);
            }
        } catch (IOException ex) {
            Task1.addLog(pathToLastUnZipGame + " " + ex.getMessage());
        }
    }

    public static GameProgress openProgress(String pathToFile) {
        try (FileInputStream fis = new FileInputStream(pathToFile);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            GameProgress gameprogress = (GameProgress) ois.readObject();
            Task1.addLog(" Объект класса " + gameprogress.getClass().getSimpleName() + " извлечен из файла: " + pathToLastUnZipGame);
            return gameprogress;
        } catch (Exception ex) {
            Task1.addLog(ex.getMessage());
            return null;
        }
    }
}
