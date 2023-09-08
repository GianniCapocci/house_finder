package gr.hua.dit.house_finder.service.impl;

import gr.hua.dit.house_finder.service.PhotoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Value("${image.upload.directory}")
    private String uploadDirectory;
    public String savePhoto(MultipartFile file) throws IOException {
        // Generate a unique filename
        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        String filePath = Paths.get(uploadDirectory, filename).toString();

        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

        return filename;
    }
}
