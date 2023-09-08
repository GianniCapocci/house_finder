package gr.hua.dit.house_finder.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PhotoService {
    public String savePhoto(MultipartFile file) throws IOException;
}
