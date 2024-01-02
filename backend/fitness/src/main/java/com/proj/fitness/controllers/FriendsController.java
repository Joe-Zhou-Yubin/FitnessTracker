package com.proj.fitness.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.proj.fitness.models.User;
import com.proj.fitness.models.Friends;
import com.proj.fitness.repository.FriendsRepository;
import com.proj.fitness.repository.UserRepository;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/friend")
public class FriendsController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendsRepository friendsRepository;

    @PostMapping("/request/{userId}")
    public ResponseEntity<?> sendFriendRequest(@PathVariable Long userId) {
        // Extract the current user's username from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        // Find the current user entity based on the username
        User currentUser = userRepository.findByUsername(currentUsername)
                                         .orElseThrow(() -> new RuntimeException("Error: User not found."));

        // Check if the requested user exists
        User requestedUser = userRepository.findById(userId)
                                            .orElseThrow(() -> new RuntimeException("Error: Requested user does not exist."));

        // Check if a friend request already exists or if they are already friends
        if (friendsRepository.existsByUserAndFriend(currentUser, requestedUser)) {
            return ResponseEntity.badRequest().body("Pending request or Friend already exists.");
        }

        // Create and save the new friend request
        Friends friendRequest = new Friends(currentUser, requestedUser, false); // False indicates that the request is pending
        friendsRepository.save(friendRequest);

        return ResponseEntity.ok("Friend request sent.");
    }


    @PutMapping("/add/{friendRequestId}")
    public ResponseEntity<?> acceptFriendRequest(@PathVariable Long friendRequestId) {
        // Extract the current user's username from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        // Find the current user entity based on the username
        User currentUser = userRepository.findByUsername(currentUsername)
                                         .orElseThrow(() -> new RuntimeException("Error: User not found."));

        // Find the friend request by ID
        Friends friendRequest = friendsRepository.findById(friendRequestId)
                                                 .orElseThrow(() -> new RuntimeException("Error: Friend request not found."));

        // Check if the current user is the recipient of the friend request
        if (!friendRequest.getFriend().equals(currentUser)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error: Unauthorized action.");
        }

        // Check if the request is already accepted
        if (friendRequest.getRequest()) {
            return ResponseEntity.badRequest().body("Error: Friend request already accepted.");
        }

        // Accept the friend request
        friendRequest.setRequest(true);
        friendsRepository.save(friendRequest);

        return ResponseEntity.ok("Friend request accepted.");
    }



    @DeleteMapping("/remove/{friendRequestId}")
    public ResponseEntity<?> removeFriend(@PathVariable Long friendRequestId) {
        // Extract the current user's username from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        // Find the current user entity based on the username
        User currentUser = userRepository.findByUsername(currentUsername)
                                         .orElseThrow(() -> new RuntimeException("Error: User not found."));

        // Find the friend request by ID
        Friends friendRequest = friendsRepository.findById(friendRequestId)
                                                 .orElseThrow(() -> new RuntimeException("Error: Friend request not found."));

        // Check if the current user is part of the friend request
        if (!friendRequest.getUser().equals(currentUser) && !friendRequest.getFriend().equals(currentUser)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Error: Unauthorized action.");
        }

        // Delete the friend request
        friendsRepository.deleteById(friendRequestId);

        return ResponseEntity.ok("Friend relationship removed successfully.");
    }


    @GetMapping("/list")
    public ResponseEntity<?> listFriends() {
        // Extract the current user's username from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        // Find the current user entity based on the username
        User currentUser = userRepository.findByUsername(currentUsername)
                                         .orElseThrow(() -> new RuntimeException("Error: User not found."));

        // Get the list of friends where the current user is either the user or the friend
        List<Friends> friends = friendsRepository.findAllByUserOrFriend(currentUser, currentUser);

        // Extract usernames and request statuses
        List<Map<String, Object>> friendDetails = friends.stream()
                                                         .map(friend -> {
                                                             User friendUser = currentUser.equals(friend.getUser()) ? friend.getFriend() : friend.getUser();
                                                             Map<String, Object> friendInfo = new HashMap<>();
                                                             friendInfo.put("username", friendUser.getUsername());
                                                             friendInfo.put("requestStatus", friend.getRequest());
                                                             return friendInfo;
                                                         })
                                                         .collect(Collectors.toList());

        return ResponseEntity.ok(friendDetails);
    }



}
