package server.server.repository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileStorage implements Repository {
    private String filePath;

    public FileStorage(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void save(String data) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(data + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String load() {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader = new FileReader(filePath)) {
            int c;
            while ((c = reader.read()) != -1) {
                stringBuilder.append((char) c);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
