package hair.utils.service.impl;

import hair.utils.service.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

@Service
public class FileServiceImpl implements FileService {

    public String getIP() {
        String IPAddress = "";
        try {
            // 获取所有网络接口
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

            // 遍历所有网络接口
            while (interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = interfaces.nextElement();

                // 获取网络接口的所有 IP 地址
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();

                // 遍历所有 IP 地址
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();

                    // 跳过回环地址
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof java.net.Inet4Address) {
                        // 返回非回环地址的 IPv4 地址
                        System.out.println(inetAddress.getHostAddress());
                        IPAddress = inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            return e.getMessage();
        }
        return IPAddress;
    }

    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, String uploadDir) {
        try {
            Path filePath = Paths.get(uploadDir, file.getOriginalFilename());
            file.transferTo(filePath.toFile());

            return ResponseEntity.ok("File uploaded successfully!");

        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to upload file: " + e.getMessage());
        }
    }

    public ResponseEntity<List<String>> listFiles(String uploadDir) {
        File directory = new File(uploadDir);
        if (!directory.exists() || !directory.isDirectory()) {
            return ResponseEntity.status(404).body(Arrays.asList("Directory not found"));
        }

        String[] files = directory.list();
        return ResponseEntity.ok(Arrays.asList(files));
    }

    public ResponseEntity<byte[]> downloadFile(@RequestParam("filename") String filename,String uploadDir){
        Path filePath = Paths.get(uploadDir, filename);
        if (!Files.exists(filePath)) {
            return ResponseEntity.status(404).build();
        }
        try {

            byte[] fileContent = Files.readAllBytes(filePath);
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=\"" + filename + "\"")
                    .body(fileContent);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
