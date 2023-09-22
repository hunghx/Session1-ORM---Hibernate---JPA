package ra.session1orm.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.session1orm.model.Book;
import ra.session1orm.repository.IBookRepo;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private IBookRepo bookRepo;
    public List<Book> findAll(){
        return bookRepo.findAll();
    }
    public Book findById(Long id){
        // optional là khái niệm trong java8 dùng để xử lí các đối tượng trả về có thể null
        Optional<Book> bookOptional =  bookRepo.findById(id);
        return bookOptional.orElse(null);
    }
    public void save (Book book){
        bookRepo.save(book);
    }
    public void delete(Long id){
       bookRepo.deleteById(id);
    }
    public List<Book> findByName(String name){
       return bookRepo.findAllByNameContaining(name);
    }

}
