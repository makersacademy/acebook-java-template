package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Friendship;
import com.makersacademy.acebook.repository.FriendshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@Controller
public class FriendshipController {

    @Autowired
    FriendshipRepository friendshipRepository;

    @PostMapping("/request/{userid}/{friendid}")
    public void friendRequest(@PathVariable("userid") Long userid, @PathVariable("friendid") Long friendid) {
        Friendship friendship = new Friendship(userid, friendid, "pending");
        friendshipRepository.save(friendship);
    }

//    @GetMapping("/requests/{userid}")
//    public Iterable<Friendship> getRequests(@PathVariable("userid") Long userid){
//        return friendshipRepository.findRequestsForUser(userid);
//    }

    @PostMapping("requests/{userid}/{requestid}/{action}")
    public void acceptOrRejectRequest(@PathVariable("action") String action, @PathVariable("userid") Long userid, @PathVariable("requestid") Long requestid){
        Optional<Friendship> friendship = friendshipRepository.findById(requestid);
        if(Objects.equals(action, "accept") || Objects.equals(action, "reject")) {
            friendshipRepository.deleteById(requestid);
        }
        if(action.equals("accept")) {
            Friendship friendship1 = new Friendship(friendship.get().getUserRequester(), friendship.get().getUserAccepter(), "accepted");
            friendshipRepository.save(friendship1);
        }
    }
}
