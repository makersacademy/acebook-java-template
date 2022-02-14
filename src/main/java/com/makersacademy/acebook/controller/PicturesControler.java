package com.makersacademy.acebook.controller;

import java.io.IOException;
import java.util.List;

import com.makersacademy.acebook.services.FileUploadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class PicturesControler {

  @Autowired
  FileUploadService uploadService;

  @GetMapping("/pictures")
  public String index(Model model) {
    List<String> picturePaths = null;
    try {
      picturePaths = uploadService.loadAll();
    } catch (IOException e) {
      e.printStackTrace();
    }
    model.addAttribute("picturePaths", picturePaths);        
    return "pictures/index";
  }

  @PostMapping("/pictures")
  public RedirectView upload(@RequestParam("file") MultipartFile file, Authentication auth) {
    String userName = auth.getName();
    uploadService.store(file, userName);
    return new RedirectView("/pictures");
  }
    
}
