package com.example.advquerying.repositories;

import com.example.advquerying.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    List<Ingredient> getAllById(Long id);

    List<Ingredient> findByNameStartingWith(String subStr);

    List<Ingredient> findByNameInOrderByPriceAsc(List<String> asList);

    @Modifying
    @Transactional
    @Query("DELETE FROM Ingredient AS b WHERE b.name = :name")
    void deleteByName(@Param(value = "name") String name);

    @Modifying
    @Transactional
    @Query("update Ingredient as b set b.price = b.price + (0.1 * b.price)")
    void increaseByTen();

    @Modifying
    @Transactional
    void increaseByTenNamed();

    @Modifying
    @Transactional
    void increaseByTenForNamesNamed(@Param(value = "names")List<String> names);
}
