package kr.deity.springboot3.sample;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BeanTest {

    @Test
    public void testLombok(){
        LombokBean lombokBean = new LombokBean(1,2,3);
        System.out.println("lombokBean = " + lombokBean);

        StudentRecode studentRecode = new StudentRecode("kim", "gyp", 1L, "deity@mail.com","010" ,"address","seoul",11);


        Book book = new Book(null, null, "1234");
        System.out.println("book = " + book);

        Book book1 = new Book();
        System.out.println("book1 = " + book1);
    }

}