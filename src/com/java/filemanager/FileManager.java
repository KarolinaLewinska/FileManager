package com.java.filemanager;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileTime;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class FileManager {
    private File countFile = new File("C:\\Users\\lewin\\Desktop\\FileTask\\src\\com\\java\\filemanager\\home\\count.txt");

    public void moveFile(String sourcePath) throws IOException {
        Path filePath = Paths.get(sourcePath);
        File fileToMove = new File(sourcePath);
        long timeInHours = getTimeOfCreationInHours(filePath);

        try {
            if (sourcePath.contains(".jar") && isEven(timeInHours) || sourcePath.contains(".xml")) {
                Files.move(Paths.get(sourcePath), Paths.get("C:\\Users\\lewin\\Desktop\\FileTask\\src\\com\\java\\filemanager\\dev\\" + fileToMove.getName()), StandardCopyOption.REPLACE_EXISTING);
                saveNumberOfFilesToCountFile();
            } else if (sourcePath.contains(".jar") && timeInHours % 2 != 0) {
                Files.move(Paths.get(sourcePath), Paths.get("C:\\Users\\lewin\\Desktop\\FileTask\\src\\com\\java\\filemanager\\test\\" + fileToMove.getName()), StandardCopyOption.REPLACE_EXISTING);
                saveNumberOfFilesToCountFile();            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    private long getTimeOfCreationInHours(Path filePath) throws IOException {
        FileTime creationTime = (FileTime) Files.getAttribute(filePath, "creationTime");
        return creationTime.to(TimeUnit.HOURS);
    }

    private boolean isEven(long number) {
        return number % 2 == 0;
    }

    private void saveNumberOfFilesToCountFile() {
        int devFilesTotalNum = Objects.requireNonNull(new File("C:\\Users\\lewin\\Desktop\\FileTask\\src\\com\\java\\filemanager\\dev\\").list()).length;
        int testFilesTotalNum = Objects.requireNonNull(new File("C:\\Users\\lewin\\Desktop\\FileTask\\src\\com\\java\\filemanager\\test\\").list()).length;
        int totalFilesNumber = devFilesTotalNum + testFilesTotalNum;
        String totalFilesNumText = "Total number of files equals: " + totalFilesNumber + "\n"
                + "Total number of files in dev directory equals: " + devFilesTotalNum + "\n" +
                "Total number of files in test directory equals: " + testFilesTotalNum + "\n";

        try {
            Files.write(this.countFile.toPath(), totalFilesNumText.getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.WRITE);

        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }
}