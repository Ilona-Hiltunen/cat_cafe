package cat.cat_cafe.web;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

//Creating a class, which handles the file upload from uploading a cat picture

public class FileUploadUtil {

    public static void saveFile(String filename, MultipartFile multipartFile) throws IOException {

        Path path = (Path)Paths.get("src/main/resources/static/images");

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = path.resolve(filename);
            System.out.println(filePath);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("Couldn't save file:" + filename, e);
        }
    }

}
