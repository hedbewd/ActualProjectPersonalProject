package com.todo.service;

import java.util.*;
import java.io.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc, category, due_date;
		int priority, importance;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[항목 추가]\n" + "제목 > ");
		title = sc.next();
		
		if (list.isDuplicate(title)) {
			System.out.println("제목이 중복됩니다!!");
			return;
		}
		
		System.out.print("카테고리 > ");
		category = sc.next();
		sc.nextLine();
		System.out.print("내용 > ");
		desc = sc.nextLine().trim();
		System.out.print("마감일자 > ");
		due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc, category, due_date);
		if (list.addItem(t) > 0)
			System.out.println("추가되었습니다!");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("지우고싶은 항목의 번호: ");
		
		int want_del_number = sc.nextInt();
		if (l.deleteItem(want_del_number) > 0)
			System.out.println("삭제되었습니다!");
	}


	public static void updateItem(TodoList l) {
		
		System.out.print("수정을 원하시는 항목의 번호: ");

		Scanner sc = new Scanner(System.in);
		
		int want_edit_num = sc.nextInt();

		System.out.print("항목의 새로운 제목: ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("제목은 중복될 수 없습니다!");
			return;
		}
		
		System.out.print("항목의 새로운 카테고리: ");
		String new_category = sc.next().trim();
		
		sc.nextLine();
		System.out.print("항목의 새로운 내용: ");
		String new_description = sc.nextLine().trim();
		
		System.out.print("항목의 새로운 마감일자: ");
		String new_due_date = sc.next();
		
		TodoItem t= new TodoItem(new_category, new_title, new_description, new_due_date);
		t.setId(want_edit_num);
		if (l.updateItem(t) > 0)
			System.out.println("수정되었습니다!!");
	}

	public static void listAll(TodoList l) {
		int num = 1;
		System.out.printf("[전체 목록, 총 %d개]\n", l.getCount());
		for (TodoItem item : l.getList()) {
			System.out.println(num + " " + item.toString());
			num++;
		}
	}
	
	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.printf("[전체 목록, 총 %d개]\n", l.getCount());
		for (TodoItem item : l.getOrderedList(orderby, ordering)) {
			System.out.println(item.toString());
		}
	}
	
	public static void listCate(TodoList l) {
		int count = 0;
		for (String item : l.getCategories()) {
			System.out.println(item + " ");
			count++;
		}
		System.out.printf("\n총 %d개의 카테고리가 등록되어 있습니다!\n", count);
	}
	
	public static void listComp(TodoList l) {
		for (TodoItem t : l.getCompletedList()) {
			System.out.println(t.toString());
		}
	}
	
	public static void listCompCate(TodoList l) {
		for (String item : l.getCompCate()) {
			System.out.println(item + " ");
		}
	}
	
	public static void listPriority(TodoList l, int priority_num) {
		for (TodoItem t : l.getPriorityList(priority_num)) {
			System.out.println(t.toString());
		}
	}
	
	public static void listImportance(TodoList l, int importance_num) {
		for (TodoItem t : l.getImportanceList(importance_num)) {
			System.out.println(t.toString());
		}
	}
	
	public static void saveList(TodoList l, String filename) {
		try {
			Writer w = new FileWriter("todolist.txt");
			
			for (TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}
			w.close();
			
			System.out.println("TodoList 저장!!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadList(TodoList l, String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("todolist.txt"));
			
			int number = 0;
			String oneLine;
			while ((oneLine = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(oneLine, "##");
				String[] buffer = new String[5];
				for (int i = 0; i < 5; i++) {
					buffer[i] = st.nextToken();
				}
				TodoItem item = new TodoItem(buffer[0], buffer[1], buffer[2], buffer[3], buffer[4]);
				l.addItem(item);
				number++;
			}
			System.out.println(number + "개의 항목을 읽었습니다!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void findList(TodoList l, String keyword) {
		int count = 0;
		for (TodoItem item : l.getList(keyword)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("총 %d개의 항목을 찾았습니다!\n", count);
	}
	
	public static void findCateList(TodoList l, String cate) {
		int count = 0;
		for (TodoItem item : l.getListCategory(cate)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("\n총 %d개의 항목을 찾았습니다.\n", count);
	}
	
	public static void checkComplete(TodoList l, int num) {
		if (l.check_completed(num) > 0)
			System.out.println("완료 체크되었습니다!");
	}
	
	public static void setPriority(TodoList l, int num, int priority_num) {
		if (l.set_priority(priority_num, num) > 0)
			System.out.println("우선순위가 설정되었습니다!");
	}
	
	public static void setImportance(TodoList l, int num, int importance_num) {
		if (l.set_importance(importance_num, num) > 0)
			System.out.println("중요도가 설정되었습니다!");
	}
}
