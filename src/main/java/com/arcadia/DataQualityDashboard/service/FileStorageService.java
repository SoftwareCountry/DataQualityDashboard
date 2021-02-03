package com.arcadia.DataQualityDashboard.service;

import com.arcadia.DataQualityDashboard.properties.StorageProperties;
import lombok.SneakyThrows;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageService implements StorageService {

    private final Path rootLocation;

    FileStorageService(StorageProperties storageProperties) {
        this.rootLocation = Paths.get(storageProperties.getLocation());
    }

    @SneakyThrows
    @Override
    @PostConstruct
    public void init() {
        Files.createDirectories(rootLocation);
    }

    @SneakyThrows
    @Override
    public String store(String fileName, String fileContent) {
        String resolvedFileName = rootLocation.resolve(fileName).toString();
        BufferedWriter writer = new BufferedWriter(new FileWriter(resolvedFileName, false));
        writer.write(fileContent);
        writer.close();

        return fileName;
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @SneakyThrows(MalformedURLException.class)
    @Override
    public Resource loadAsResource(String filename) throws FileNotFoundException {
        Path file = load(filename);
        UrlResource resource = new UrlResource(file.toUri());

        if (resource.exists() && resource.isReadable()) {
            return resource;
        } else {
            throw new FileNotFoundException("File not found");
        }
    }
}
