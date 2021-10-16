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
				System.out.println("제목순으로 정렬하였습니다!");
				TodoUtil.listAll(l, "title", 1);
				break;

			case "ls_name_desc":
				System.out.println("제목역순으로 정렬하였습니다!");
				TodoUtil.listAll(l, "title", 0);
				break;
				
			case "ls_date":
				System.out.println("날짜순으로 정렬하였습니다!");
				TodoUtil.listAll(l, "due_date", 1);
				break;
				
			case "ls_date_desc":
				System.out.println("날짜역순으로 정렬하였습니다!");
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
				System.out.println("TodoList App의 모든 데이터가 데이터베이스에 저장되었습니다!");
				//TodoUtil.saveList(l, "todolist.txt");
				quit = true;
				break;

			default:
				System.out.println("언급된 커맨드만 사용해주세요! (도움말 커맨드 - 'help')");
				break;
			}
			
		} while (!quit);
	}
}
