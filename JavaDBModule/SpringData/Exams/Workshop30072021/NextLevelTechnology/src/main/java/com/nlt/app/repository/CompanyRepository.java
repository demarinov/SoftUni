package com.nlt.app.repository;

import com.nlt.app.model.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    List<Company> findAllById(Long id);

    List<Company> getCompanyByName(String name);
}
