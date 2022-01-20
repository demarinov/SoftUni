package com.nlt.app.repository;

import com.nlt.app.model.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    
    List<Project> findAllById(Long id);

    List<Project> getProjectByName(String name);

    List<Project> getProjectsByFinished(boolean finished);
}
