package kr.deity.springboot3.sample;

import org.junit.jupiter.api.Test;

public class RecodeExamples {
    @Test
    public void testBook() {
        // 클래스와 마찬가지로 new 연산자를 통해 레코드의 인스턴스를 생성한다.
        Book bookA = new Book("Book A", "Author A");
        Book bookB = new Book("Book B", "Author B");

        // 두 레코드의 간단한 비교 : 값이 다른 레코드는 다릅니다.
        System.out.println("bookA.hashCode() = " + bookA.hashCode());
        System.out.println("bookB.hashCode() = " + bookB.hashCode());
        if (bookA.equals(bookB)) {
            System.out.println("bookA와 bookB는 서로 같습니다.");
        }
        System.out.println();

        //같은 값이면 값은 같습니다.
        bookB = new Book("Book A", "Author A");
        System.out.println("bookA.hashCode() = " + bookA.hashCode());
        System.out.println("bookB.hashCode() = " + bookB.hashCode());
        if (bookA.equals(bookB)) {
            System.out.println("bookA와 bookB는 서로 같습니다.");
        }
        System.out.println();

        // toString() 출력 살펴보기
        System.out.println("bookA = " + bookA);
        System.out.println("bookB = " + bookB);

        // 게터로 출력하기
        System.out.println("bookA.title() = " + bookA.title());
        System.out.println("bookA.author() = " + bookA.author());
    }

    /* static */ record Book(String title, String author) { }
}
