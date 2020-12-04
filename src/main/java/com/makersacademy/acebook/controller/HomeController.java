package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.dao.PostDAO;
import com.makersacademy.acebook.dao.UserDAO;
import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class HomeController {

	private final PostDAO repository;

	public HomeController(PostDAO repository) {
		this.repository = repository;
	}

	@Autowired
	UserDAO userDAO;

	@GetMapping("/")
	public String showIndex(Model model, Principal principal, User user){
		if(principal == null){
			return "login";
		}


		return "index";
	}


	@GetMapping("/all")
	//READS ALL RECORDS IN POSTS TABLE
	public List<Post> findAll() {
		List<Post> posts = new ArrayList<>();
		repository.findAll().forEach(posts::add);
		System.out.println(posts);
		return posts;
	}

	@GetMapping("/add")
	public String test() {
		return "add";
	}

	//ADDS A NEW RECORD TO THE POSTS TABLE AS ENTERED BY USER
	@PostMapping("/add")
	@ResponseBody
	public void processData(@RequestParam String message) {
		Post post = new Post(message);
		repository.save(post);
	}

	@GetMapping("/delete")
	public String delete() {
		return "delete";
	}

	//DELETE A SPECIFIC RECORD
	@PostMapping("/delete")
	@ResponseBody
	public void deleteRecord(@RequestParam String message){
		List<Post> postList = findAll();
		for(int i=0; i < postList.size(); i++){
			if (postList.get(i).getContent().equals(message)){
				repository.delete(postList.get(i));
			}
		}
	}

	@GetMapping("/update")
	public String update() {
		return "update";
	}

	//UPDATES A RECORD TO NEW USER INPUT
	@PostMapping("/update")
	@ResponseBody
	public void updateRecord(@RequestParam String oldmessage, @RequestParam String newmessage){
		List<Post> postList = findAll();
		for(int i=0; i < postList.size(); i++){
			if (postList.get(i).getContent().equals(oldmessage)){
				Post currentPost = postList.get(i);
				currentPost.setContent(newmessage);
				repository.save(currentPost);
			}
		}
	}
}
