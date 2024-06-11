package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Friend;
import com.makersacademy.acebook.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FriendRepository extends CrudRepository<Friend, Long> {
    List<Friend> findAllBySender(User sender);
    List<Friend> findAllByRecipient(User recipient);
    List<User> findAllSenderByRecipient(User recipient);
    List<User> findAllRecipientBySender(User sender);
    List<User> findAllByRecipientAndAccepted(User recipient, boolean accepted);
    List<User> findAllBySenderAndAccepted(User sender, boolean accepted);
    List<User> findDistinctUsersBySenderOrRecipient(User sender, User recipient);
}
