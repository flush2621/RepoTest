package com.njit;

import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FileUploadController {
    private final String fileBaseDirector="c:\\test\\";

    private final Path fileBasePath;

    public FileUploadController(){
        this.fileBasePath = Path.of(fileBaseDirector);
    }

    @PostMapping("/upload")
    public ResponseEntity upload(@RequestParam("file") MultipartFile multipartFile){
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        Path path = Path.of(fileBaseDirector+fileName);
        try {
            Files.copy(multipartFile.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e){
            e.printStackTrace();
        }
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/load/{filename}")
    public ResponseEntity loadFile(@PathVariable String filename){
        Path path = fileBasePath.resolve(filename);
        Resource resource = null;
        try {
            resource = new UrlResource(path.toUri());
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=\""+resource.getFilename()+"\"")
                .body(resource);
    }

    @DeleteMapping("/del/{filename}")
    public ResponseEntity delFile(@PathVariable String filename){
        Path delPath = fileBasePath.resolve(filename);
        Boolean isDel = null;
        try {
            isDel = FileSystemUtils.deleteRecursively(delPath);
        }catch (IOException e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(isDel);
    }

    @GetMapping("/getAllFiles")
    public ResponseEntity loadAll() throws IOException{
        List<String> urlAll = Files.walk(fileBasePath,1)
                .filter(path -> !path.equals(fileBasePath))
                .map(path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,"loadFile",path.getFileName().toString())
                .build().toString())
                .collect(Collectors.toList());
        return ResponseEntity.ok(urlAll);

    }
}
