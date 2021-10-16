package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		//l.importData("todolist.txt");
		boolean isList = false;
		boolean quit = false;
		String want_find;
		int num, priority_num, importance_num;
		
		//TodoUtil.loadList(l, "todolist.txt");
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
				System.out.println("��������� �����Ͽ����ϴ�!");
				TodoUtil.listAll(l, "title", 1);
				break;

			case "ls_name_desc":
				System.out.println("���񿪼����� �����Ͽ����ϴ�!");
				TodoUtil.listAll(l, "title", 0);
				break;
				
			case "ls_date":
				System.out.println("��¥������ �����Ͽ����ϴ�!");
				TodoUtil.listAll(l, "due_date", 1);
				break;
				
			case "ls_date_desc":
				System.out.println("��¥�������� �����Ͽ����ϴ�!");
				TodoUtil.listAll(l, "due_date", 0);
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
				String keyword = sc.nextLine().trim();
				TodoUtil.findList(l, keyword);
				break;
				
			case "find_cate":
				String cate = sc.nextLine().trim();
				TodoUtil.findCateList(l, cate);
				break;
				
			case "comp":
				num = sc.nextInt();
				TodoUtil.checkComplete(l, num);
				break;
			
			case "ls_comp":
				TodoUtil.listComp(l);
				break;
				
			case "ls_comp_cate":
				TodoUtil.listCompCate(l);
				break;
				
			case "priority":
				num = sc.nextInt();
				priority_num = sc.nextInt();
				TodoUtil.setPriority(l, num, priority_num);
				break;
				
			case "ls_priority":
				priority_num = sc.nextInt();
				TodoUtil.listPriority(l, priority_num);
				break;
				
			case "importance":
				num = sc.nextInt();
				importance_num = sc.nextInt();
				TodoUtil.setImportance(l, num, importance_num);
				break;
				
			case "ls_importance":
				importance_num = sc.nextInt();
				TodoUtil.listImportance(l, importance_num);
				break;
				
			case "help":
				Menu.displaymenu();
				isList = true;
				break;

			case "exit":
				System.out.println("TodoList App�� ��� �����Ͱ� �����ͺ��̽��� ����Ǿ����ϴ�!");
				//TodoUtil.saveList(l, "todolist.txt");
				quit = true;
				break;

			default:
				System.out.println("��޵� Ŀ�ǵ常 ������ּ���! (���� Ŀ�ǵ� - 'help')");
				break;
			}
			
		} while (!quit);
	}
}
