package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

public interface FriendRepository extends CrudRepository<Friend, Long> {
    public default Boolean hasRequested(Long fromUser, Long toUser){
        Boolean hasRequested = false;
        Iterable<Friend> allFriendRequests = this.findAll();
        for(Friend f: allFriendRequests) {
            if(f.getFromUser()==fromUser && f.getToUser()==toUser){
                hasRequested = true;
            }
        }
        return hasRequested;
    }
    public default Friend findRequest(Long fromUser, Long toUser){
        Friend friendRequest = new Friend();
        Iterable<Friend> allFriendRequests = this.findAll();
        for(Friend f: allFriendRequests) {
            if(f.getFromUser()==fromUser && f.getToUser()==toUser){
                friendRequest = f;
            }
        }
        return friendRequest;
    }
    
    // public default Friend numFriends(Long fromUser, Long toUser){
    //     Iterable<Friend> num_of_friends = this.findAll();
    //     int num = 0;
    //     for(Friend f: num_of_friends) {
    //         if (f.getId() > num){
    //             num = (int) f.getId();
    //         }
    //         }
    //     }

    }