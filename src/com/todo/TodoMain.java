package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		boolean isList = false;
		boolean quit = false;
		String want_find;
		
		TodoUtil.loadList(l, "todolist.txt");
		Menu.displaymenu();
		
		do {
			Menu.prompt();
			isList = false;
			String choice = sc.next();
			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;

			case "ls_name_asc":
				l.sortByName();
				System.out.println("��������� �����Ͽ����ϴ�!");
				isList = true;
				break;

			case "ls_name_desc":
				l.sortByName();
				l.reverseList();
				System.out.println("���񿪼����� �����Ͽ����ϴ�!");
				isList = true;
				break;
				
			case "ls_date":
				l.sortByDate();
				System.out.println("��¥������ �����Ͽ����ϴ�!");
				isList = true;
				break;
				
			case "ls_date_desc":
				l.sortByDate();
				l.reverseList();
				System.out.println("��¥�������� �����Ͽ����ϴ�!");
				isList = true;
				break;
				
			case "ls_cate":
				TodoUtil.listCate(l);
				break;
				
			case "save":
				TodoUtil.saveList(l, "todolist.txt");
				break;
				
			case "load":
				TodoUtil.loadList(l, "todolist.txt");
				break;
				
			case "find":
				want_find = sc.next();
				TodoUtil.findList(l, want_find);
				break;
				
			case "find_cate":
				want_find = sc.next();
				TodoUtil.findCate(l,want_find);
				break;
				
			case "help":
				Menu.displaymenu();
				isList = true;
				break;

			case "exit":
				System.out.println("TodoList App�� ��� �����Ͱ� ����Ǿ����ϴ�!");
				TodoUtil.saveList(l, "todolist.txt");
				quit = true;
				break;

			default:
				System.out.println("��޵� Ŀ�ǵ常 ������ּ���! (���� Ŀ�ǵ� - 'help')");
				break;
			}
			
			if(isList) l.listAll();
		} while (!quit);
	}
}
