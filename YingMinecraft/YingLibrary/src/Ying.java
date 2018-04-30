import static java.text.MessageFormat.format;
import static java.util.concurrent.TimeUnit.DAYS;
import static java.util.stream.Collectors.toList;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Stream;


public final class Ying {

    private static final String CONFIG_PATH = "./application.yaml";
    private static final String DEFAULT_CONFIG_PATH = "/default-application.yaml";

    private String url;

    public static void main(String[] args) {

    }


    private static void YingCopyDefaultConfig(Path target) throws IOException {
        if(!Files.exists(Paths.get(CONFIG_PATH))) {
            try (InputStream in = Ying.class.getResourceAsStream(DEFAULT_CONFIG_PATH)) {
                if (in == null)
                    throw new FileNotFoundException(DEFAULT_CONFIG_PATH);

                Files.copy(in, target);
            }
        }
    }



}


