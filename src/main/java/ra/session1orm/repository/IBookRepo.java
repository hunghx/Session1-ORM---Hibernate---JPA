package ra.session1orm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ra.session1orm.model.Book;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface IBookRepo extends JpaRepository<Book,Long> {

    List<Book> findAllByNameContaining(String name);
    @Transactional // định nghĩa giao dịch khi thực hiện chỉnh sửa dữ liệu
    @Query(value = "sql", nativeQuery = true)
    List<Object[]> truyVan1();
}
