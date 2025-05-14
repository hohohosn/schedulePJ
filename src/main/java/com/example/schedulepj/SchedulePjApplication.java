import controller.ScheduleController;
import repository.ScheduleRepository;
import service.ScheduleService;

import java.util.Scanner;

public class ScheduleApp {
    public static void main(String[] args) {
        ScheduleRepository repo = new ScheduleRepository();
        ScheduleService service = new ScheduleService(repo);
        ScheduleController controller = new ScheduleController(service);

        Scanner sc = new Scanner(System.in);
        System.out.println("일정 관리 프로그램 시작");
        boolean run = true;

        while (run) {
            System.out.println("1. 생성  2. 전체조회  3. 단건조회  4. 수정  5. 삭제  0. 종료");
            switch (sc.nextLine()) {
                case "1" -> {
                    System.out.print("할일: "); String task = sc.nextLine();
                    System.out.print("작성자: "); String author = sc.nextLine();
                    System.out.print("비밀번호: "); String pw = sc.nextLine();
                    System.out.println(controller.addSchedule(task, author, pw));
                }
                case "2" -> {
                    System.out.print("수정일(YYYY-MM-DD, 생략 가능): "); String d = sc.nextLine();
                    System.out.print("작성자명(생략 가능): "); String a = sc.nextLine();
                    controller.viewSchedules(d, a).forEach(System.out::println);
                }
                case "3" -> {
                    System.out.print("ID: ");
                    controller.viewSchedule(Long.parseLong(sc.nextLine()))
                            .ifPresentOrElse(System.out::println, () -> System.out.println("없음"));
                }
                case "4" -> {
                    System.out.print("ID: "); long id = Long.parseLong(sc.nextLine());
                    System.out.print("비밀번호: "); String pw = sc.nextLine();
                    System.out.print("할일: "); String task = sc.nextLine();
                    System.out.print("작성자: "); String author = sc.nextLine();
                    if (controller.editSchedule(id, task, author, pw)) System.out.println("수정 완료");
                    else System.out.println("비밀번호 오류 또는 ID 없음");
                }
                case "5" -> {
                    System.out.print("ID: "); long id = Long.parseLong(sc.nextLine());
                    System.out.print("비밀번호: "); String pw = sc.nextLine();
                    if (controller.removeSchedule(id, pw)) System.out.println("삭제 완료");
                    else System.out.println("비밀번호 오류 또는 ID 없음");
                }
                case "0" -> run = false;
            }
        }
        System.out.println("종료합니다.");
    }
}
