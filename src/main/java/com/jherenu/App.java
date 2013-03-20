package com.jherenu;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.jherenu.model.User;

public class App {
	public static void main(String[] args) {
		// For XML
		ApplicationContext ctx = new GenericXmlApplicationContext("spring-application.xml");

		MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");

		User user = new User("juan", "password123");

		// save
		mongoOperation.save(user, "users");

		// find
		User savedUser = mongoOperation
				.findOne(new Query(Criteria.where("username").is("juan")), User.class, "users");

		System.out.println("savedUser : " + savedUser);

		// update
		mongoOperation.updateMulti(new Query(Criteria.where("username").is("juan")),
				Update.update("password", "new password"), "users");

		// find
		User updatedUser = mongoOperation.findOne(new Query(Criteria.where("username").is("juan")), User.class,
				"users");

		System.out.println("updatedUser : " + updatedUser);

		// delete
//		mongoOperation.remove(new Query(Criteria.where("username").is("juan")), "users");

		// List
		List<User> listUser = mongoOperation.findAll(User.class, "users");
		System.out.println("Number of user = " + listUser.size());

	}
}
