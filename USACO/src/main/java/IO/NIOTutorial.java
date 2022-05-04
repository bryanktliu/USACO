package IO;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.*;

public class NIOTutorial {

    public static void main(String[] args) throws IOException {
        Path fileI1 = Paths.get("Downloads/in");
        Path fileI = Paths.get("Downloads/out/in.zip");
        Path fileO = Paths.get("/Users/frankliu/Downloads/out");
        zip(fileI1, fileO);
        unzip(fileI, fileO);
    }

    public static void zip(Path fileI, Path fileO) throws IOException {
        int len = fileI.getParent().toRealPath().toString().length() + 1;
        Path outPath = fileO.resolve(fileI.getFileName().toString() + ".zip");
        try (ZipOutputStream out = new ZipOutputStream(Files.newOutputStream(outPath))) {
            if (Files.isDirectory(fileI)) {
                List<Path> files = getFileList(fileI);
                for (Path file : files) {
                    if (Files.isDirectory(file)) {
                        String name = file.toRealPath().toString().substring(len) + "/";
                        out.putNextEntry(new ZipEntry(name));
                    } else {
                        out.putNextEntry(new ZipEntry(file.toRealPath().toString().substring(len)));
                        Files.copy(file, out);
                    }
                }
            } else {
                out.putNextEntry(new ZipEntry(fileI.getFileName().toString()));
                Files.copy(fileI, out);
            }
        }
    }

    public static void unzip(Path fileI, Path fileO) throws IOException {
        try (ZipInputStream in = new ZipInputStream(Files.newInputStream(fileI))) {
            ZipEntry entry = in.getNextEntry();
            while (entry != null) {
                if (entry.isDirectory()) {
                    Files.createDirectories(fileO.resolve(entry.getName()));
                } else {
                    Path f = Paths.get(fileO.toRealPath().toString(), entry.getName());
                    Path p = f.getParent();
                    if (!Files.exists(p)) {
                        Files.createDirectories(p);
                    }
                    Files.copy(in, f, StandardCopyOption.REPLACE_EXISTING);
                }
                entry = in.getNextEntry();
            }
        }
    }

    public static List<Path> getFileList(Path fileI) throws IOException {
        List<Path> files = new ArrayList<>();
        if (Files.isHidden(fileI)) {
            return Collections.emptyList();
        }
        files.add(fileI);
        if (Files.isDirectory(fileI)) {
            for (Path file : Files.list(fileI).toArray(Path[]::new)) {
                files.addAll(getFileList(file));
            }
        }
        return files;
    }
}
