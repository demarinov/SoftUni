package com.example.advquerying.repositories;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.entities.Label;
import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;


@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {

    List<Shampoo> getAllById(Long id);

    List<Shampoo> findAllBySizeOrderById(Size size);

    List<Shampoo> findAllBySizeOrLabelOrderByPriceAsc(Size size, Label label);

    List<Shampoo> findByPriceGreaterThanOrderByPriceDesc(BigDecimal price);

    List<Shampoo> findAllByPriceLessThan(BigDecimal price);

    @Query(
            "select s from Shampoo s join s.ingredients i where i in :ingredients"
    )
    List<Shampoo> findShampoosAndIngredientsInList(@Param(value="ingredients") List<Ingredient> ingredients);

    @Query (
            "select s from Shampoo s join s.ingredients i group by s.id having count(s.id) < :number"
    )
    List<Shampoo> findByIngredientCountLessThan(@Param(value="number") Long number);
}
