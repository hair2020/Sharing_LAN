package hair.utils.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    String initUploadDir(String uploadDir);

    String getIP();

    ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, String uploadDir);

    ResponseEntity<List<String>> listFiles(String uploadDir);

    ResponseEntity<byte[]> downloadFile(@RequestParam("filename") String filename, String uploadDir);
}
