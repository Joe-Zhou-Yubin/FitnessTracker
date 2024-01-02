package com.proj.fitness.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "friends")
public class Friends {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user; 

    @ManyToOne
    @JoinColumn(name = "friend_id", referencedColumnName = "id")
    private User friend; 

    @Column(name = "request")
    private Boolean request; 

    public Friends() {
    }

    public Friends(User user, User friend, Boolean request) {
        this.user = user;
        this.friend = friend;
        this.request = request;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getFriend() {
		return friend;
	}

	public void setFriend(User friend) {
		this.friend = friend;
	}

	public Boolean getRequest() {
		return request;
	}

	public void setRequest(Boolean request) {
		this.request = request;
	}

	@Override
	public String toString() {
		return "Friends [id=" + id + ", user=" + user + ", friend=" + friend + ", request=" + request + "]";
	}
    
}