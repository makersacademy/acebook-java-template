package com.makersacademy.acebook.repository;

import com.makersacademy.acebook.model.Friend;
import com.makersacademy.acebook.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface FriendRepository extends CrudRepository<Friend, Long> {
    List<Friend> findAllBySender(User sender);
    List<Friend> findAllByRecipient(User recipient);
    List<User> findAllSenderByRecipient(User recipient);
    List<User> findAllRecipientBySender(User sender);
    List<Friend> findAllByRecipientAndAccepted(User recipient, boolean accepted);
    List<Friend> findAllBySenderAndAccepted(User sender, boolean accepted);
    List<Friend> findBySenderOrRecipient(User sender, User recipient);
    Optional<Friend> findBySenderAndRecipient(User sender, User recipient);
}
