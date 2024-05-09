import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.*;

public class DirectoryToXML {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập đường dẫn thư mục: ");
        String directoryPath = scanner.nextLine();

        try {
            FileWriter fileWriter = new FileWriter("cau_truc_thu_muc.xml");
            fileWriter.write("<cau_truc_thu_muc>\n");
            lietKeThuMuc(directoryPath, fileWriter, 1);
            fileWriter.write("</cau_truc_thu_muc>\n");
            fileWriter.close();
            System.out.println("File XML được tạo thành công!");
        } catch (IOException e) {
            System.err.println("Lỗi khi ghi vào file: " + e.getMessage());
        }
    }

    private static void lietKeThuMuc(String directoryPath, FileWriter fileWriter, int depth) throws IOException {
        Path dir = Paths.get(directoryPath);
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path entry : stream) {
                String khoangTrang = " ".repeat(depth * 2);
                if (Files.isDirectory(entry)) {
                    fileWriter.write(khoangTrang + "<" + entry.getFileName() + ">\n");
                    lietKeThuMuc(entry.toString(), fileWriter, depth + 1);
                    fileWriter.write(khoangTrang + "</" + entry.getFileName() + ">\n");
                } else if (Files.isRegularFile(entry)) {
                    fileWriter.write(khoangTrang + "<file>" + entry.getFileName() + "</file>\n");
                }
            }
        } catch (IOException | DirectoryIteratorException x) {
            System.err.println(x);
        }
    }
}
