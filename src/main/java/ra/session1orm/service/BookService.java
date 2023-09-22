package ra.session1orm.service;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.session1orm.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private EntityManager entityManager;

    public List<Book> findAll(){
        TypedQuery<Book> typedQuery = entityManager.createQuery("SELECT B from Book as B",Book.class);
        // trả về 1 câu query sử dụng ngôn ngữ HQL để truy vấn trên lớp Book
        return typedQuery.getResultList();
    }
    public Book findById(Long id){
        TypedQuery<Book> typedQuery = entityManager.createQuery("SELECT B from Book as B where B.id = ?1",Book.class);
        // truyền tham số vào câu querry
        typedQuery.setParameter(1,id);
        return typedQuery.getSingleResult();
    }
    public void save (Book book){
        // có sự thay đổi dữ liệu => đua vào transaction
            // phân chia chức năng
            entityManager.getTransaction().begin();
            if(book.getId() == null){
                // thêm mới
               entityManager.persist(book);
            }else {
              Book old = findById(book.getId());

              old.setName(book.getName());
              old.setPrice(book.getPrice());

              entityManager.merge(old);
            }
            entityManager.getTransaction().commit();

    }
    public void delete(Long id){
        entityManager.getTransaction().begin();
       entityManager.remove(findById(id));
       entityManager.getTransaction().commit();
    }

}
