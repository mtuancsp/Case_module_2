package read_write;

import java.io.*;
import java.util.List;

public class ReadWrite {
    public static <T> List<T> read(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filePath)))) {
            return (List<T>) ois.readObject();
        }
    }

    public static <T> void write(String filePath, List<T> list) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filePath)))) {
            oos.writeObject(list);
        }
    }

}
