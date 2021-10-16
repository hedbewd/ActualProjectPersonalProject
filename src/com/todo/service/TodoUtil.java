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
		
		System.out.print("[�׸� �߰�]\n" + "���� > ");
		title = sc.next();
		
		if (list.isDuplicate(title)) {
			System.out.println("������ �ߺ��˴ϴ�!!");
			return;
		}
		
		System.out.print("ī�װ� > ");
		category = sc.next();
		sc.nextLine();
		System.out.print("���� > ");
		desc = sc.nextLine().trim();
		System.out.print("�������� > ");
		due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc, category, due_date);
		if (list.addItem(t) > 0)
			System.out.println("�߰��Ǿ����ϴ�!");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("�������� �׸��� ��ȣ: ");
		
		int want_del_number = sc.nextInt();
		if (l.deleteItem(want_del_number) > 0)
			System.out.println("�����Ǿ����ϴ�!");
	}


	public static void updateItem(TodoList l) {
		
		System.out.print("������ ���Ͻô� �׸��� ��ȣ: ");

		Scanner sc = new Scanner(System.in);
		
		int want_edit_num = sc.nextInt();

		System.out.print("�׸��� ���ο� ����: ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("������ �ߺ��� �� �����ϴ�!");
			return;
		}
		
		System.out.print("�׸��� ���ο� ī�װ�: ");
		String new_category = sc.next().trim();
		
		sc.nextLine();
		System.out.print("�׸��� ���ο� ����: ");
		String new_description = sc.nextLine().trim();
		
		System.out.print("�׸��� ���ο� ��������: ");
		String new_due_date = sc.next();
		
		TodoItem t= new TodoItem(new_title, new_description, new_category, new_due_date);
		t.setId(want_edit_num);
		if (l.updateItem(t) > 0)
			System.out.println("�����Ǿ����ϴ�!!");
	}

	public static void listAll(TodoList l) {
		int num = 1;
		System.out.printf("[��ü ���, �� %d��]\n", l.getCount());
		for (TodoItem item : l.getList()) {
			System.out.println(num + " " + item.toString());
			num++;
		}
	}
	
	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.printf("[��ü ���, �� %d��]\n", l.getCount());
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
		System.out.printf("\n�� %d���� ī�װ��� ��ϵǾ� �ֽ��ϴ�!\n", count);
	}
	
	public static void listComp(TodoList l) {
		for (TodoItem t : l.getCompletedList()) {
			System.out.println(t.toString());
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
			
			System.out.println("TodoList ����!!");
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
			System.out.println(number + "���� �׸��� �о����ϴ�!");
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
		System.out.printf("�� %d���� �׸��� ã�ҽ��ϴ�!\n", count);
	}
	
	public static void findCateList(TodoList l, String cate) {
		int count = 0;
		for (TodoItem item : l.getListCategory(cate)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("\n�� %d���� �׸��� ã�ҽ��ϴ�.\n", count);
	}
	
	public static void checkComplete(TodoList l, int num) {
		if (l.check_completed(num) > 0)
			System.out.println("�Ϸ� üũ�Ǿ����ϴ�!");
	}
	
	public static void setPriority(TodoList l, int num, int priority_num) {
		if (l.set_priority(priority_num, num) > 0)
			System.out.println("�켱������ �����Ǿ����ϴ�!");
	}
	
	public static void setImportance(TodoList l, int num, int importance_num) {
		if (l.set_importance(importance_num, num) > 0)
			System.out.println("�߿䵵�� �����Ǿ����ϴ�!");
	}
}
