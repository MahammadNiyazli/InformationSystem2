package com.company;

import com.company.entity.Users;
import com.company.dao.inter.UsersDaoInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class InfoSysDbAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfoSysDbAppApplication.class, args);

	}
//	@Autowired
//	private UsersDaoInter usersDao;
//
//	@Bean
//	public CommandLineRunner run(){
//		CommandLineRunner clr = new CommandLineRunner() {
//			@Override
//			public void run(String... args) throws Exception {
////				Users user = new Users();
////				user.setName("Mahammad");
////				user.setEmail("adksdmksm@mail.ru");
////				user.setSurname("Niyazli");
////				user.setPassword("12458");
////				user.setRole("USER");
//				Users users = usersDao.findByEmail("haqverdiyev2012@mail.ru");
//				System.out.println(users);
//			}
//		};
//		return clr;
//	}
}
