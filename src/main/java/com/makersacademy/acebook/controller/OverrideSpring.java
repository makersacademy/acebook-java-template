package com.makersacademy.acebook.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class OverrideSpring implements WebMvcConfigurer {
 @Override
 public void addResourceHandlers(ResourceHandlerRegistry registry) {
  exposeDirectory("src/main/resources/static/image", registry);
 }

 private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
  Path uploadDir = Paths.get(dirName);
  String uploadPath = uploadDir.toFile().getAbsolutePath();

  if (dirName.startsWith("../"))
   dirName = dirName.replace("../", "");

  registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:/" + uploadPath + "/");
 }

 @Override
 public void addViewControllers(ViewControllerRegistry registry) {
  registry.addViewController("/login").setViewName("login");
  registry.addViewController("/logout").setViewName("logout");
 }

}