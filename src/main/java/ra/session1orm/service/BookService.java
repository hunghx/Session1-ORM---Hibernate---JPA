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
    @Autowired
    private SessionFactory sessionFactory;

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
        Session session = null;
        Transaction transaction = null;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            // phân chia chức năng
            if(book.getId() == null){
                // thêm mới
                session.save(book);
            }else {
                // cập nhật
                // lấy ra đối tượng cũ
                Book old = findById(book.getId());
                old.setName(book.getName());
                old.setPrice(book.getPrice());

                // lưu lại vào session
                session.saveOrUpdate(old);
            }
            transaction.commit();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session!=null){
                session.close();
            }
        }
    }
    public void delete(Long id){
        Session session = null;
        Transaction transaction = null;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(findById(id));
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session!=null){
                session.close();
            }
        }
    }

}
