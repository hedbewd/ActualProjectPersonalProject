package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("1. 항목을 추가하고 싶으시면 'add'를 입력하세요.");
        System.out.println("2. 항목을 지우고 싶으시면 'del를 입력하세요.");
        System.out.println("3. 항목을 수정하고 싶으시면 'edit'을 입력하세요.");
        System.out.println("4. 전체 항목을 보고 싶으시면 'ls'를 입력하세요.");
        System.out.println("5. 전체 카테고리를 보고 싶으시면 'ls_cate'를 입력하세요.");
        System.out.println("6. 이름순으로 정렬하고 싶으시면 'ls_name_asc'를 입력하세요.");
        System.out.println("7. 이름역순으로 정렬하고 싶으시면 'ls_name_desc'를 입력하세요.");
        System.out.println("8. 날짜순으로 정렬하고 싶으시면 'ls_date'을 입력하세요.");
        System.out.println("9. 날짜역순으로 정렬하고 싶으시면 'ls_date_desc'을 입력하세요.");
        System.out.println("10. 키워드를 통해 리스트를 찾고 싶으시면 'find'를 입력하세요.");
        System.out.println("11. 완료체크를 하고 싶으시면 'comp + id'를 입력하세요.");
        System.out.println("12. 완료체크 한 필드를 보고싶으시면 'ls_comp'를 입력하세요.");
        System.out.println("13. 완료체크 한 카테고리를 보고싶으시면 'ls_comp_cate'를 입력하세요.");
        System.out.println("14. 우선순위를 설정하고 싶으시면 'priority + id + 우선순위'를 입력하세요.");
        System.out.println("15. 원하는 우선순위를 출력하고 싶으시면 'ls_priority + 우선순위'를 입력하세요.");
        System.out.println("16. 중요도를 설정하고 싶으시면 'importance + id + 중요도'를 입력하세요.");
        System.out.println("17. 원하는 중요도를 출력하고 싶으시면 'ls_importance + 중요도'를 입력하세요.");
        System.out.println("18. 종료하고 싶으시면 'exit'을 입력하세요.");
    }
    
    public static void prompt()
    {
    	System.out.print("Command: ");
    }
}
