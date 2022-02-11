package com.makersacademy.acebook.services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {

  private final Path location = Paths.get("src/main/resources/static/uploads-dir/pictures");

  public void store(MultipartFile file) {
    try {
			Path destinationFile = this.location.resolve(
					Paths.get("user_" + file.getOriginalFilename()))
					.normalize().toAbsolutePath();
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, destinationFile,
					StandardCopyOption.REPLACE_EXISTING);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}        
  }

  public List<String> loadAll() throws IOException {
		return Files.walk(this.location, 1)
				.filter(path -> !path.equals(this.location))
				.map(this.location::relativize)
        .map( path -> "/uploads-dir/pictures/" +  path.toString() )
        .collect(Collectors.toList());
  }
  
}
