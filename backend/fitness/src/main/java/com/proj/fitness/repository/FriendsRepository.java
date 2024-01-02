package com.proj.fitness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proj.fitness.models.Friends;
import com.proj.fitness.models.User;
import java.util.List;

public interface FriendsRepository extends JpaRepository<Friends, Long> {

    boolean existsByUserAndFriend(User user, User friend);
    List<Friends> findByUserAndFriendAndRequest(User user, User friend, boolean request);
    List<Friends> findByUserAndFriend(User user, User friend);
    List<Friends> findAllByUserOrFriend(User user, User friend);
}
