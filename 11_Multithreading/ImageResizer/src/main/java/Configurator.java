import core.ResizeConfig;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Configurator {
    private File DIR_FOR_READ;
    private File DIR_FOR_WRITE;
    private int compression;
    private static Configurator configurator;
    private boolean buildIsReady = false;

    private Configurator() {
    }

    public static Configurator getConfigurator() {
        if (configurator == null) {
            return new Configurator();
        }
        return configurator;
    }

    public void build() {
        DIR_FOR_READ = getFileForRead();
        DIR_FOR_WRITE = getFileForWrite();
        compression = getCompressionValue();
        buildIsReady = true;
    }

    public ResizeConfig getResizeConfig() {
        if (buildIsReady) {
            return new ResizeConfig(DIR_FOR_READ,DIR_FOR_WRITE,compression);
        } else {
            System.err.println("Ошибка запроса. Необходимо сначала вызвать Configurator.build()");
        }
        return null;
    }

    //----------------------------------------------------------------------------------//

    private File getFileForRead() {
        String message = "Укажите какую папку с изображениями которые необходимо уменьшить";
        System.out.println(message);
        while (true) {
            File file;
            try {
                String path = getStringFromConsole();
                file = new File(path);
                if (!file.canRead()) {
                    throw new IOException();
                }
            } catch (IOException e) {
                System.out.println("Неверно указан путь к чтению, попробуйте еще раз");
                continue;
            }
            return file;
        }

    }

    private File getFileForWrite() {
        String message = "Укажите какую папку куда будут сохранены новые изображения";
        System.out.println(message);
        while (true) {
            File file;
            try {
                String path = getStringFromConsole();
                file = new File(path);
                if (!file.canRead()) {
                    System.out.println("Папка не найдена. Создать новую папку? YES/NO" +
                            "\n\tYES - Будет создана папка " + file.getName() +
                            "\n\t\tNO - Указать другой папку");
                    String command = getStringFromConsole().toUpperCase();
                    if (command.equals("YES")) {
                        file = getNewDIR(path);
                        if (file.canRead()) {
                            System.out.println("Создана папка " + file.getPath());
                            return file;
                        } else {
                            throw new IOException();
                        }
                    } else {
                        throw new IOException();}
                }
            } catch (IOException e) {
                System.out.println(message);
                continue;
            }
            return file;
        }
    }


    private int getCompressionValue() {
        String message = "Укажите параметр сжатия, например: 30% (По умолчанию 0%)";
        System.out.println(message);
        while (true) {
            int compression;
            try {
                compression = getValueFromConsole();
                if (compression < 0 || compression > 100) {
                    throw new IOException();
                }
            } catch (IOException e) {
                System.out.println("Неверное число! Укажите в интервале [0 - 100]");
                continue;
            }
            return compression;
        }
    }


    //----------------------------------------------------------------------------------------//

    private String getStringFromConsole() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        return bufferedReader.readLine();
    }

    private int getValueFromConsole() throws IOException {
        String value = getStringFromConsole().replaceAll("[^0-9]", "").trim();
        if (value.length() == 0) {
            return 0;
        }
        return Integer.parseInt(value);
    }

    private File getNewDIR(String path) {
        File file = new File(path);
        new File(path).mkdirs();
        return file;
    }
}
