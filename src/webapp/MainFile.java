package webapp;

import java.io.File;
import java.util.Objects;


public class MainFile {
    public static void main(String[] args) {
//        String filePath = ".\\.gitignore";
//
//        File file = new File(filePath);
//        try {
//            System.out.println(file.getCanonicalPath());
//        } catch (IOException e) {
//            throw new RuntimeException("Error", e);
//        }
//
//        File dir = new File(".\\src\\webapp\\storage");
//        System.out.println(dir.isDirectory());
//        String[] list = dir.list();
//        if (list != null) {
//            for (String name : list) {
//                System.out.println(name);
//            }
//        }
//
//        try (FileInputStream fis = new FileInputStream(filePath)) {
//            System.out.println(fis.read());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        File direct = new File("C:\\Users\\User\\IdeaProjects\\basejava\\src");
        recursivePrint(direct);
//        System.out.println(direct.isDirectory());

    }

    public static void recursivePrint(File directory) {
        if (directory.isDirectory()) {
            System.out.println("\ndirectory name: " + directory);
            File[] subDirectories = directory.listFiles();
            for (File file : Objects.requireNonNull(subDirectories)) {
                recursivePrint(file);
            }
        } else {
            System.out.println("filename: " + directory.getName());
        }

    }
}