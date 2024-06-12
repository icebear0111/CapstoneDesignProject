package com.mysite.project.Service;

import com.mysite.project.Repository.AccidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class AccidentService {

    @Autowired
    private AccidentRepository accidentRepository;

    @Value("${flask.server.url}")
    private String flaskServerUrl;

    @Value("${upload.dir}")
    private String uploadDir;

    public String processVideo(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Cannot process an empty file.");
        }

        String filename = file.getOriginalFilename();
        Path path = Paths.get(uploadDir, filename);
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        file.transferTo(path);
        return path.toString();
    }

    public void sendVideoFile(String videoFilePath) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = getMultiValueMapHttpEntity(videoFilePath, headers);

        String serverUrl = flaskServerUrl + "/video-upload";
        try {
            String response = restTemplate.postForObject(serverUrl, requestEntity, String.class);
        } catch (Exception e) {
            throw new IOException("Failed to send video to Flask server.", e);
        }
    }

    private static HttpEntity<MultiValueMap<String, Object>> getMultiValueMapHttpEntity(String videoFilePath, HttpHeaders headers) throws IOException {
        File videoFile = new File(videoFilePath);
        if (!videoFile.exists() || !videoFile.canRead()) {
            throw new IOException("File does not exist or cannot be read: " + videoFilePath);
        }

        FileSystemResource fileResource = new FileSystemResource(videoFile);
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", fileResource);

        return new HttpEntity<>(body, headers);
    }

    public void deleteVideo(String filename) throws IOException {
        Path path = Paths.get(uploadDir, filename);
        if (Files.exists(path)) {
            Files.delete(path);
        } else {
            throw new IOException("File does not exist: " + path);
        }
    }
}