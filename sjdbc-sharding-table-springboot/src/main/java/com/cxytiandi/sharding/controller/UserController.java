package com.cxytiandi.sharding.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cxytiandi.sharding.po.User;
import com.cxytiandi.sharding.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public Object list() throws InterruptedException {
		return userService.list();
	}

	/**
	 * 分页就会按表的序号进行查询
	 * @return
	 */
	@GetMapping("/users2")
	public Object list2() {
		PageHelper.startPage(1, 50);
		List<User> list = userService.list();
		PageInfo<User> info = new PageInfo<>(list);
		return info.getList();
	}
//
//	@GetMapping("/users3")
//	public Object list3() {
//		PageHelper.startPage(1, 50);
//		List<User> list = userService.list("timeStart","timeEnd");
//		PageInfo<User> info = new PageInfo<>(list);
//		return info.getList();
//	}
	
	@GetMapping("/add")
	public Object add() throws InterruptedException {
		for (long i = 0; i < 100; i++) {
			User user = new User();
			user.setId(i);
			user.setCity("深圳");
			user.setName("李四");
			user.setCreateDate(new Date());
			userService.add(user);
		}
		return "success";
	}

	/**
	 * 由于使用id, 所以会根据算法,直接去某个表查询
	 * @param id
	 * @return
	 */
	@GetMapping("/users/{id}")
	public Object get(@PathVariable Long id) {
		return userService.findById(id);
	}

	/**
	 * 没有使用id,每个表都查一遍
	 * @param name
	 * @return
	 */
	@GetMapping("/users/query")
	public Object get(String name) {
		return userService.findByName(name);
	}
	
}
