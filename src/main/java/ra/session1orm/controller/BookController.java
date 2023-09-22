package ra.session1orm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ra.session1orm.model.Book;
import ra.session1orm.service.BookService;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    public BookService bookService;
    @GetMapping
    public String getAll(){
        List<Book> list = bookService.findByName("trình");
        System.out.println(list);
        return "home";
    }
    @GetMapping("/add")
    public String add(){
        Book book1 = new Book(null,"Đắc nhân tâm",3000);
        Book book2 = new Book(null,"Lập trình Java",5000);
        bookService.save(book1);
        bookService.save(book2);
       return "home";
    }
    @GetMapping("/update")
    public String update(){
        Book book1 = new Book(1L,"CUốn số 1",999);
        Book book2 = new Book(2L,"Cuốn số 2",999);
        bookService.save(book1);
        bookService.save(book2);
       return "home";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id ){
        bookService.delete(id);
       return "home";
    }
}
