package com.todo.service;

import java.util.*;
import java.io.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("�߰��ϰ���� �׸��� ī�װ�: ");
		category = sc.next();
		
		System.out.print("�߰��ϰ���� �׸��� ����: ");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.println("�ߺ��� �����Դϴ�!");
			return;
		}
		sc.nextLine();
		System.out.print("�߰��ϰ���� �׸��� ����: ");
		desc = sc.nextLine().trim();
		
		System.out.print("�߰��ϰ���� �׸��� ��������: ");
		due_date = sc.next();
		
		TodoItem t = new TodoItem(category, title, desc, due_date);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("�������� �׸��� ��ȣ: ");
		
		int want_del_number = sc.nextInt();
		int number = 1;
		
		l.one_list(want_del_number);
		
		System.out.print("�� �׸��� �����Ͻðڽ��ϱ�? (y/n)");
		String yes_or_no = sc.next();
		
		if (yes_or_no.equals("y")) {
			for (TodoItem item : l.getList()) {
				if (number == want_del_number) {
					l.deleteItem(item);
					break;
				}
				number++;
			}
		}
		
		System.out.println("�׸��� �����Ǿ����ϴ�!!");
	}


	public static void updateItem(TodoList l) {
		
		System.out.print("������ ���Ͻô� �׸��� ��ȣ: ");

		Scanner sc = new Scanner(System.in);
		
		int want_edit_num = sc.nextInt();
		int number = 1;
		
		l.one_list(want_edit_num);

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
		
		for (TodoItem item : l.getList()) {
			if (want_edit_num == number) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_category, new_title, new_description, new_due_date);
				l.addItem(t);
				System.out.println("�׸��� ������Ʈ �Ǿ����ϴ�!");
			}
			number++;
		}

	}

	public static void listAll(TodoList l) {
		int totalNumber = 0;
		int number = 1;
		List list = new ArrayList();
		list = l.getList();
		
		if (list.isEmpty())
			System.out.println("TodoList�� �������� �ʽ��ϴ�!");
		
		for (TodoItem item : l.getList()) {
			totalNumber++;
		}
		
		System.out.println("[��ü ���, �� " + totalNumber + "��]");
		
		for (TodoItem item : l.getList()) {
			System.out.println(number + ". " + "[" + item.getCategory() + "]   " + item.getTitle() + "  -  " + item.getDesc() + "  -  " +item.getDue_date() + "  -  " + item.getCurrent_date());
			number++;
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
				buffer[0] = st.nextToken();
				buffer[1] = st.nextToken();
				buffer[2] = st.nextToken();
				buffer[3] = st.nextToken();
				buffer[4] = st.nextToken();
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
	
	public static void findList(TodoList l, String want_find) {
		
		int number = 1;
		int totalNumber = 0;
		
		for (TodoItem item : l.getList()) {
			if (item.getTitle().contains(want_find) || item.getDesc().contains(want_find)) {
				l.one_list(number);
				totalNumber++;
			}
			number++;
		}
		
		System.out.println("�� " + totalNumber + "���� �׸��� ã�ҽ��ϴ�!");
		
	}
}
