package kr.deity.springboot3.sample;

import java.util.Objects;

public record Book(String title, String author, String isbn) {
    public Book{
        Objects.requireNonNullElse(title,"마지막잎새");
        Objects.requireNonNullElse(author,"무명");
        Objects.requireNonNullElse(isbn,"tmp_1234");
    }

    // 여전히 아래와 같이 표준 생성자와 컴팩트 생성자를 혼용해서 쓸 수 있다.
    public Book(String title, String isbn) {
        this(title, "Unknown", isbn);
    }

    public Book(){
        this("무명", "Unknown", "tmp_1234");
    }
}
