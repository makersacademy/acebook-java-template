package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Authority;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.AuthoritiesRepository;
import com.makersacademy.acebook.repository.UserRepository;
import org.postgresql.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import java.io.*;
import java.sql.*;
import java.util.Optional;

@Controller
public class UsersController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthoritiesRepository authoritiesRepository;
    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/users/new")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "users/new";
    }

    @PostMapping("/users")
    public RedirectView signup(@ModelAttribute User user, RedirectAttributes redirect) {
        try {
            if (user.getPassword().length() > 0) {
                user.setPassword(encoder.encode(user.getPassword()));
                userRepository.save(user);
                Authority authority = new Authority(user.getUsername(), "ROLE_USER");
                authoritiesRepository.save(authority);
                return new RedirectView("/login");
            } else {
                return new RedirectView("/users/new");
            }
        } catch (Exception e) {
            Iterable<User> usernames = userRepository.findAll();
            for (User users : usernames) {
                if (users.getUsername().contains(user.getUsername())) {
                    redirect.addFlashAttribute("error", "User already exists");
                    return new RedirectView("/users/new/");
                }
            }
            redirect.addFlashAttribute("error", "Error During Signup");
            return new RedirectView("/users/new/");
        }
    }

    @GetMapping("/users/profilePicture")
    public String addProfilePic(Model model) {
//        System.out.println("-------In profile route-------");
        model.addAttribute("showLogout", true);
        return "/users/profilePicture";
    }


    @PostMapping("/users/profilePicture")

    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                         RedirectAttributes redirectAttributes) throws IOException, SQLException {

        File newFile = multipartToFile(file, file.getOriginalFilename());

        //Add the picture to the user profile DB as a url
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal instanceof UserDetails ? ((UserDetails) principal).getUsername() : principal.toString();
        Connection con = sqlSetup();
        Statement queryStatement = con.createStatement();
        String query = "SELECT id FROM users WHERE username='" +username+ "'" ;
        ResultSet resultSet = queryStatement.executeQuery(query);
        resultSet.next();
        Long userID = resultSet.getLong (1);
        User currentUser = userRepository.findById(userID).get();
        currentUser.setProfilePicture(newFile.toString());
        userRepository.save(currentUser);

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:profilePicture";
    }

    public  static File multipartToFile(MultipartFile multipart, String fileName) throws IllegalStateException, IOException {
        File convFile = new File(System.getProperty("java.io.tmpdir")+"/"+fileName);
        multipart.transferTo(convFile);
        return convFile;
    }

    public Connection sqlSetup() throws SQLException {
    DriverManager.registerDriver(new Driver());
    Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/acebook_springboot_development");
    return con;
}

}
