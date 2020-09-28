package com.supanadit.restsuite.system;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class Restsuite {
    public static String workspaceDirectory = ".restsuite";

    public static Path getWorkspaceDirectory() {
        com.supanadit.restsuite.logger.LogWriter.out("getWorkspaceDirectory", "getProperty");
        com.supanadit.restsuite.logger.LogWriter.out("getWorkspaceDirectory", "get");
        return Paths.get(System.getProperty("user.home"), workspaceDirectory);
    }

    public static void createWorkspaceDirectory() {
        com.supanadit.restsuite.logger.LogWriter.out("createWorkspaceDirectory", "exists");
        com.supanadit.restsuite.logger.LogWriter.out("createWorkspaceDirectory", "getWorkspaceDirectory");
        Path workspace = getWorkspaceDirectory();
        if (!Files.exists(workspace)) {
            try {
                com.supanadit.restsuite.logger.LogWriter.out("createWorkspaceDirectory", "createDirectories");
                Files.createDirectories(workspace);
            } catch (IOException e) {
                com.supanadit.restsuite.logger.LogWriter.out("createWorkspaceDirectory", "printStackTrace");
                e.printStackTrace();
            }
        }
    }
}