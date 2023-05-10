package com.example.TasksAG;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TasksAgApplicationTests {

	@Test
	void contextLoads() {
	}

	@BeforeAll
	public static void runBeforeEverything(){
		System.out.println("Before running a test class");
	}
}
