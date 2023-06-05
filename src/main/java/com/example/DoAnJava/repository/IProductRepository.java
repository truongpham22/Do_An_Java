    package com.example.DoAnJava.repository;

    import com.example.DoAnJava.entity.Product;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.query.Param;
    import org.springframework.stereotype.Repository;

    import java.util.List;

    @Repository
    public interface IProductRepository extends JpaRepository<Product,Long> {
        @Query("SELECT p FROM Product p WHERE p.category.name = :cate")
        List<Product> findByCategory( @Param("cate") String category);

        @Query("SELECT p FROM Product p WHERE p.name like %?1%")
        List<Product> searchByName(String keyWord);
    }
