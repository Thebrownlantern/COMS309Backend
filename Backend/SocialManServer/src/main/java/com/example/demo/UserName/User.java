package com.example.demo.UserName;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String password;
    
    @JsonIgnore
    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Post> posts;
    
    @JsonIgnore
    @ManyToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    private List<User> friends;
    
   
    
    public User() {}

    public User(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail()
    {
    	return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword()
    {
    	return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    

    @Override
    public String toString() {
        return "UserName{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public List<User> getFriends(){
		return friends;
	}
	
	public void setFriends(List<User> friends) {
		this.friends = friends;
	}
	
}