package com.example.demo;

import com.example.demo.view.MainView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	private MainView mainView;

	@Autowired
	public DemoApplication(MainView mainView) {
		this.mainView = mainView;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) {
		mainView.showNumberOfPosts();
		mainView.showNumberOfPostsForSpecificPerson();
		mainView.showWhetherTitleOfPostsAreUnique();
		mainView.showTheNearestLocatedUsers();
	}


}
