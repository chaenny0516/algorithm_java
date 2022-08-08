package Programmers.StackQueue;

import java.util.*;

/**
 * 프린터
 * https://school.programmers.co.kr/learn/courses/30/lessons/42587?language=java
 * <p>
 * 일반적인 프린터는 인쇄 요청이 들어온 순서대로 인쇄합니다. 그렇기 때문에 중요한 문서가 나중에 인쇄될 수 있습니다.
 * 이런 문제를 보완하기 위해 중요도가 높은 문서를 먼저 인쇄하는 프린터를 개발했습니다. 이 새롭게 개발한 프린터는 아래와 같은 방식으로 인쇄 작업을 수행합니다.
 * <p>
 * 1. 인쇄 대기목록의 가장 앞에 있는 문서(J)를 대기목록에서 꺼냅니다.
 * 2. 나머지 인쇄 대기목록에서 J보다 중요도가 높은 문서가 한 개라도 존재하면 J를 대기목록의 가장 마지막에 넣습니다.
 * 3. 그렇지 않으면 J를 인쇄합니다.
 * 예를 들어, 4개의 문서(A, B, C, D)가 순서대로 인쇄 대기목록에 있고 중요도가 2 1 3 2 라면 C D A B 순으로 인쇄하게 됩니다.
 * <p>
 * 내가 인쇄를 요청한 문서가 몇 번째로 인쇄되는지 알고 싶습니다. 위의 예에서 C는 1번째로, A는 3번째로 인쇄됩니다.
 * <p>
 * 현재 대기목록에 있는 문서의 중요도가 순서대로 담긴 배열 priorities와 내가 인쇄를 요청한 문서가 현재 대기목록의 어떤 위치에 있는지를 알려주는 location이 매개변수로 주어질 때, 내가 인쇄를 요청한 문서가 몇 번째로 인쇄되는지 return 하도록 solution 함수를 작성해주세요.
 * <p>
 * 제한사항
 * 현재 대기목록에는 1개 이상 100개 이하의 문서가 있습니다.
 * 인쇄 작업의 중요도는 1~9로 표현하며 숫자가 클수록 중요하다는 뜻입니다.
 * location은 0 이상 (현재 대기목록에 있는 작업 수 - 1) 이하의 값을 가지며 대기목록의 가장 앞에 있으면 0, 두 번째에 있으면 1로 표현합니다.
 */
public class Lessons_42587 {
    public int solution(int[] priorities, int location) {
        List<Integer> print_list = new ArrayList<>();
        for (int priority : priorities) {
            print_list.add(priority);
        }

        int printed_cnt = 1;
        while (!print_list.isEmpty()) {
            final Integer j = print_list.get(0);
            if (print_list.stream().anyMatch(v -> j < v)) { // 뒤에 더 큰값이 있으면 TODO : stream AnyMatch 등 공부하기
                print_list.add(print_list.remove(0)); // 그 값을 뒤에 추가한다.
            } else { // 뒤에 더 큰값이 없고
                if (location == 0) { // 맨앞값이 원하던 값이면 리턴
                    return printed_cnt;
                } // 아니라면 지우고 출력물수 +1
                print_list.remove(0);
                printed_cnt++;
            }

            if (location > 0) { // 원하던게 뒤에 있었다면 앞에가 지워졌으니 원하던것도 앞으로 왔을 것
                location--;
            } else { // 원하던게 맨앞인데 뒤에 우선순위가 더 큰 게 있었다면 맨 뒤로 갔을 것
                location = print_list.size() - 1;
            }
        }
        throw new IllegalArgumentException(); // TODO : Exception 종류에 대해서 공부할 것
    }

    public static void main(String[] args) {
        int[] priorities = {2, 1, 3, 2};
        int location = 2;
        int answer = 1;

        Lessons_42587 lessons_42587 = new Lessons_42587();
        int result = lessons_42587.solution(priorities, location);
        assert answer == result;
    }
}
