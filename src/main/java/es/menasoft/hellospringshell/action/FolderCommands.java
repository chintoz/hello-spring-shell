package es.menasoft.hellospringshell.action;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import java.io.File;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ShellComponent
public class FolderCommands {

    private boolean connected;

    @ShellMethod(key = "list-folder")
    public String listFolder(@ShellOption(defaultValue = "./") String folder) {
        return Stream.of(Objects.requireNonNull(new File(folder).listFiles()))
                .map(file -> (file.isDirectory() ? "<f> %s" : "<d> %s").formatted(file.getName()))
                .collect(Collectors.joining("\n"));
    }

    @ShellMethod("Connect to server")
    public String connect() {
        connected = true;
        return "Connected";
    }

    @ShellMethod("Download from server")
    @ShellMethodAvailability("downloadAvailability")
    public String download() {
        return "Downloading ...";
    }

    public Availability downloadAvailability() {
        return connected ? Availability.available() : Availability.unavailable("Not connected");
    }

}
