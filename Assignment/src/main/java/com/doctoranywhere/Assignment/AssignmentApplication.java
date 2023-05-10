package com.doctoranywhere.Assignment;

import com.doctoranywhere.Assignment.model.Role;
import com.doctoranywhere.Assignment.model.Task;
import com.doctoranywhere.Assignment.model.User;
import com.doctoranywhere.Assignment.service.RoleService;
import com.doctoranywhere.Assignment.service.TaskService;
import com.doctoranywhere.Assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableWebSecurity
public class AssignmentApplication implements CommandLineRunner {
	@Autowired
	private RoleService roleService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private UserService userService;

	//In-Memory Storage of demo Tasks
	private List<Task> listOfTasks= Arrays.asList(
			new Task("Do Homework","Do Math101",false),
			new Task("Do Laundry","Colored clothes today",false),
			new Task("Clean the room","Particularly the floor",false));

	public static void main(String[] args) {
		SpringApplication.run(AssignmentApplication.class, args);
	}

	public void run(String... args) throws Exception{
		Role role = new Role();
		role.setName("Admin");
		roleService.updateRole(1,role);

		User user = new User();
		user.setRole(roleService.getRoleById(1L).get());
		user.setName("user");
		user.setEmail("test@testmail.com");
		user.setPassword(new BCryptPasswordEncoder().encode("password"));
		userService.updateUser(1,user);

		int n=1;
		for (Task task:listOfTasks){
			taskService.updateTask(n,task);
			n+=1;
		}

	}

}
