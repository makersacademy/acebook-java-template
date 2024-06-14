package com.makersacademy.acebook;

import com.makersacademy.acebook.model.Friend;
import com.makersacademy.acebook.model.Notification;
import com.makersacademy.acebook.model.User;
import com.makersacademy.acebook.repository.FriendRepository;
import com.makersacademy.acebook.repository.NotificationRepository;
import com.makersacademy.acebook.repository.UserRepository;

import java.util.*;

public class Utils {

    public static String GetFriendStatus(String one, String two, UserRepository userRepository, FriendRepository friendRepository){
        User sender = userRepository.findByUsername(one);
        User recipient = userRepository.findByUsername(two);

        if (sender == null || recipient == null || sender.getId() == recipient.getId()) return "N/A";

        Optional<Friend> existingConnection = friendRepository.findBySenderAndRecipient(sender, recipient);
        if (existingConnection.isPresent()){
            if (existingConnection.get().isAccepted()) return "Friend";
            return "Sent";
        }
        existingConnection = friendRepository.findBySenderAndRecipient(recipient, sender);
        if (existingConnection.isPresent()){
            if (existingConnection.get().isAccepted()) return "Friend";
            return "Received";
        }
        return "None";
    }

    public static String GetFriendStatus(User one, User two, UserRepository userRepository, FriendRepository friendRepository){
        User sender = userRepository.findByUsername(one.getUsername());
        User recipient = userRepository.findByUsername(two.getUsername());

        if (sender == null || recipient == null || sender.getId() == recipient.getId()) return "N/A";

        Optional<Friend> existingConnection = friendRepository.findBySenderAndRecipient(sender, recipient);
        if (existingConnection.isPresent()){
            if (existingConnection.get().isAccepted()) return "Friend";
            return "Sent";
        }
        existingConnection = friendRepository.findBySenderAndRecipient(recipient, sender);
        if (existingConnection.isPresent()){
            if (existingConnection.get().isAccepted()) return "Friend";
            return "Received";
        }
        return "None";
    }

    public static List<Long> GetAllFriendsOfUser(User user, boolean includeSelf, FriendRepository friendRepository){
        List<Friend> connections = friendRepository.findBySenderOrRecipient(user, user);
        Set<Long> friendIds = new HashSet<Long>();
        if (includeSelf) friendIds.add(user.getId());
        for (Friend connection : connections){
            friendIds.add(connection.getRecipient().getId());
            friendIds.add(connection.getSender().getId());
        }
        return new ArrayList<Long>(friendIds);
    }

    public static void CreateNotification(User recipient, User sender, String type, String link, NotificationRepository notificationRepository){
        String message = GenerateNotificationMessage(sender.getUsername(), type);
        Notification new_notification = new Notification(recipient, type, message, link);
        notificationRepository.save(new_notification);
    }

    private static String GenerateNotificationMessage(String username, String type) {
        switch(type){
            case "post_like": return username + " liked your post.";
            case "post_comment": return username + " commented on your post.";
            case "comment_like": return username + " liked your comment";
            case "friend_request": return username + " sent you a friend request";
            case "friend_accepted": return "You became friends with " + username;
            default: return "Something went wrong.";
        }
    }
}
