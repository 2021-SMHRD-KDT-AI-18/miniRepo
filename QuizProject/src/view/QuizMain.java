package view;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import controller.controller;
import model.DAO;
import model.DTO;

public class QuizMain {

  public static void main(String[] args) {

   Scanner sc = new Scanner(System.in);
   Random ran = new Random();
   DAO dao = new DAO();
   controller controller = new controller();
   DTO dto = null;

   while (true) {
     System.out.println("정처기자바");
     System.out.println("======메뉴=======");
     System.out.println("1.회원 2.로그인 3.랭크확인 4.종료 >>> ");
     int a = sc.nextInt(); //메뉴선택입력

     if (a == 1) { // 회원가입
      System.out.println("======회원등록======");
      System.out.print("ID를 입력하세요:");
      String id = sc.next();
      System.out.print("PW를 입력하세요:");
      String pw = sc.next();
      System.out.print("닉네임을 입력하세요:");
      String nic = sc.next();

      dto = new DTO(id, pw, nic);
      controller.join(dto);
      System.out.println("환영합니다" + dto.getNic() + "님");
     } else if (a == 2) { //로그인
      System.out.println("======로그인======");
      System.out.print("ID를 입력하세요:");
      String id = sc.next();
      System.out.print("PW를 입력하세요:");
      String pw = sc.next();
      DTO info = controller.login(id, pw);
      if (info != null) {
        System.out.println(info.getNic() + "님 안녕하세요!");
        System.out.println("난이도를 선택하세요");
        System.out.println("1.이지 2.하드");
        int b = sc.nextInt();
        if (b == 1) { // 이지모드 게임실행
          System.out.println("이지모드 게임을 시작합니다");

          int cnt = 1;
          int correct = 0;

          ArrayList<DTO> wlist = new ArrayList<DTO>();

          while (cnt <= 10) {
            ArrayList<DTO> list = dao.QuestionList();
            DTO wrongQ = null;

            int num = ran.nextInt(list.size());
            String l_ans = list.get(num).getAnswer().substring(0, 1);
            System.out.println(list.get(num).getQuestion());
            System.out.print("Q" + cnt + ". 정답 입력 : " + l_ans);
            String ans = sc.next();
            if (list.get(num).getAnswer().equals(l_ans + ans)) {
              System.out.println("정답입니다");
              correct++;
            } else {
              System.out.println("틀렸습니다");
              wrongQ = list.get(num);
              wlist.add(wrongQ);
            }
            cnt++;
          }
          
        
          System.out.printf("총 %d문제 중 %d문제를 맞추셨습니다.%n", cnt - 1, correct);
          System.out.println("틀린문제를 다시 푸시겠습니까?");
          System.out.println("1.예 2.종료");
          int c = sc.nextInt();
          if (c == 1) {
        	  System.out.println("오답풀이를 시작합니다.");
            for (int i = 0; i < wlist.size(); i++) {
              System.out.println(wlist.get(i).getQuestion());
              System.out.print("Q" + (i + 1) + ". 정답 입력 : ");
              String ans = sc.next();
              if (wlist.equals(ans)) {
                System.out.println("정답입니다");

              } else {
                System.out.println("틀렸습니다");
              }
            }
          } else if (c == 2) {
            System.out.println("게임을 종료합니다.");
            break;
          } else {
            System.out.println("정확한 숫자를 입력하세요.");
          }
        } else if (b == 2) {
          int cnt = 1;
          int correct = 0;

          ArrayList<DTO> wlist = new ArrayList<DTO>();

          System.out.println("하드모드 게임을 시작합니다.");
          // 하드모드 게임 실행
          while (cnt <= 10) {
            ArrayList<DTO> list = dao.QuestionList();
            DTO wrongQ = null;

            int num = ran.nextInt(list.size());
            System.out.println(list.get(num).getQuestion());
            System.out.println("Q" + cnt + ". 정답 입력 : ");
            String ans = sc.next();
            if (list.get(num).getAnswer().equals(ans)) {
              System.out.println("정답입니다");
              correct++;
            } else {
              System.out.println("틀렸습니다");
              wrongQ = list.get(num);
              wlist.add(wrongQ);
            }
            cnt++;
          }
          System.out.printf("총 %d문제 중 %d문제를 맞추셨습니다.%n", cnt - 1, correct);
          System.out.println("틀린문제를 다시 푸시겠습니까?");
          System.out.println("1.예 2.종료");
          int c = sc.nextInt();
          if (c == 1) {
             System.out.println("오답풀이를 시작합니다.");
            for (int i = 0; i < wlist.size(); i++) {
              System.out.println(wlist.get(i).getQuestion());
              System.out.print("Q" + (i + 1) + ". 정답 입력 : ");
              String ans = sc.next();
              if (wlist.equals(ans)) {
                System.out.println("정답입니다");

              } else {
                System.out.println("틀렸습니다");
              }

            }

          } else if (c == 2) {
            System.out.println("게임을 종료합니다.");
            break;
          } else {
            System.out.println("정확한 숫자를 입력하세요.");
          }
        }

      } else if (info == null) {
        System.out.println("등록되지 않은 회원입니다");
      }
     } else if (a == 3) {
      System.out.println("랭크확인");
     } else if (a == 4) {
      System.out.println("종료합니다.");
      break;
     } else {
      System.out.println("정확한 번호를 입력하세요");
     }
   }
  }
}