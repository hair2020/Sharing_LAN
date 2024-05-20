package hair.utils.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import hair.utils.service.FileService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/share")
public class FileController {

    @Value("${file.uploadFile.dir}")
    private String uploadDir;

    @Resource
    FileService fileService;

    @RequestMapping(value = "/initUploadDir", method = RequestMethod.GET)
    public String initUploadDir(){
        uploadDir = fileService.initUploadDir(uploadDir);
        return uploadDir;
    }

    @RequestMapping(value = "/getIP", method = RequestMethod.GET)
    public String getIP(){
        return fileService.getIP();
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        return fileService.uploadFile(file,uploadDir);
    }

    @RequestMapping(value = "/listFiles", method = RequestMethod.GET)
    public ResponseEntity<List<String>> listFiles(){
        return fileService.listFiles(uploadDir);
    }

    @RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
    public ResponseEntity<byte[]> downloadFile(@RequestParam("filename") String filename){
        return fileService.downloadFile(filename,uploadDir);
    }

}
