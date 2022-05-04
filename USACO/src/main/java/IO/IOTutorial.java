package IO;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.*;

public class IOTutorial {

    public static void main(String[] args) throws IOException {
        File fileI1 = new File("/Users/bryan/Downloads/in");
        File fileI = new File("/Users/bryan/Downloads/out/in.zip");
        File fileO = new File("/Users/bryan/Downloads/out");
        zip(fileI1, fileO);
        unzip(fileI, fileO);
    }

    public static void zip(File fileI, File fileO) throws IOException {
        int len = fileI.getParentFile().getCanonicalPath().length() + 1;
        File outPath = new File(fileO, fileI.getName() + ".zip");
        try (ZipOutputStream out =
                new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(outPath)))) {
            if (fileI.isDirectory()) {
                List<File> files = getFileList(fileI);
                for (File file : files) {
                    if (file.isDirectory()) {
                        String name = file.getCanonicalPath().substring(len) + "/";
                        out.putNextEntry(new ZipEntry(name));
                    } else {
                        out.putNextEntry(new ZipEntry(file.getCanonicalPath().substring(len)));
                        try (BufferedInputStream in =
                                new BufferedInputStream(new FileInputStream(file))) {
                            copyStream(in, out);
                        }
                    }
                }
            } else {
                out.putNextEntry(new ZipEntry(fileI.getName()));
                try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(fileI))) {
                    copyStream(in, out);
                }
            }
        }
    }

    public static void unzip(File fileI, File fileO) throws IOException {
        try (ZipInputStream in =
                new ZipInputStream(new BufferedInputStream(new FileInputStream(fileI)))) {
            ZipEntry entry = in.getNextEntry();
            while (entry != null) {
                if (entry.isDirectory()) {
                    new File(fileO, entry.getName()).mkdirs();
                } else {
                    File f = new File(fileO.getCanonicalPath(), entry.getName());
                    File p = f.getParentFile();
                    if (!p.exists()) {
                        p.mkdirs();
                    }
                    try (BufferedOutputStream out =
                            new BufferedOutputStream(new FileOutputStream(f))) {
                        copyStream(in, out);
                    }
                }
                entry = in.getNextEntry();
            }
        }
    }

    public static List<File> getFileList(File fileI) {
        List<File> files = new ArrayList<>();
        if (fileI.getName().startsWith(".")) {
            return Collections.emptyList();
        }
        files.add(fileI);
        if (fileI.isDirectory()) {
            for (File file : fileI.listFiles()) {
                files.addAll(getFileList(file));
            }
        }
        return files;
    }

    public static void copyStream(InputStream in, OutputStream out) throws IOException {
        int input = in.read();
        while (input >= 0) {
            out.write(input);
            input = in.read();
        }
    }

    public static void copyFile(File fileI, File fileO) throws IOException {
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(fileI));
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(fileO))) {
            int input = in.read();
            while (input >= 0) {
                out.write(input);
                input = in.read();
            }
        }
    }

    public static void copy(File fileI, File fileO) throws IOException {
        if (fileI.isDirectory()) {
            if (!fileO.exists()) {
                fileO.mkdirs();
            }
            for (File file : fileI.listFiles()) {
                copy(file, new File(fileO, file.getName()));
            }
        } else {
            copyFile(fileI, fileO);
        }
    }
}
