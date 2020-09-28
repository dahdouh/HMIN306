package com.supanadit.restsuite.system;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class Restsuite {
    public static String workspaceDirectory = ".restsuite";

    public static Path getWorkspaceDirectory() {
        System.out.println("getWorkspaceDirectory -> getProperty");
        System.out.println("getWorkspaceDirectory -> get");
        return Paths.get(System.getProperty("user.home"), workspaceDirectory);
    }

    public static void createWorkspaceDirectory() {
        System.out.println("createWorkspaceDirectory -> exists");
        System.out.println("createWorkspaceDirectory -> getWorkspaceDirectory");
        Path workspace = getWorkspaceDirectory();
        if (!Files.exists(workspace)) {
            try {
                System.out.println("createWorkspaceDirectory -> createDirectories");
                Files.createDirectories(workspace);
            } catch (IOException e) {
                System.out.println("createWorkspaceDirectory -> printStackTrace");
                e.printStackTrace();
            }
        }
    }
}