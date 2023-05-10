package kr.deity.springboot2_x.sample.recod;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/recode")
public class RecodeSampleController {
    @GetMapping("/list")
    public ResponseBook list(RequestBook paramBook){
        System.out.println("book = " + paramBook);

        Book book = new Book();
        book.setTitle(paramBook.title());
        book.setAuthor(paramBook.author());
        book.setIsbn("1234");

        return new ResponseBook(book.title,book.author,book.isbn,new Date());
    }

    record RequestBook(String title, String author) { }

    record ResponseBook(String title, String author, String isbn, Date regDate) { }
}
