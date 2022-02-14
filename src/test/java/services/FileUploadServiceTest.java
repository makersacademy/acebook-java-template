package services;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.after;

import java.io.IOException;
import java.util.List;

import com.makersacademy.acebook.services.FileUploadService;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

public class FileUploadServiceTest {

  @Test 
  public void FileUploadServiceStoresAFile() throws IOException {
    FileUploadService uploadService = new FileUploadService();
    MockMultipartFile testFile = new MockMultipartFile("testfile", "testfile.txt", "text/plain", "some test file content".getBytes());
    uploadService.store(testFile, "user");
    List<String> list = uploadService.loadAll();
    Boolean fileIsStored = false;
    for (String str : list) {
      if (str.contains("user_" + testFile.getOriginalFilename()))
        fileIsStored = true;
    }
    assertTrue(fileIsStored);

  }
  
}
