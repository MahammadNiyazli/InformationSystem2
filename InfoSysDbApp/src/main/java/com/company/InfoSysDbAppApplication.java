package com.company;

import com.company.dao.inter.*;
import com.company.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.print.Doc;
import java.util.List;

@SpringBootApplication
public class InfoSysDbAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfoSysDbAppApplication.class, args);

	}
//	@Autowired
//	private JournalDaoInter journalDao;
//
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
//				List<Journal> users = journalDao.getAll();
//				String word = users.get(0).getName();
//				System.out.println(word);
//				String[] arr = word.split("\\.");
//				System.out.println("arrayin uzunluugu = "+arr[0]);
//
//			}
//		};
//		return clr;
//	}
}
