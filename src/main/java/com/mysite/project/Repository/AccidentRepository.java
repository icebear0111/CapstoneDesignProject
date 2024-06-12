package com.mysite.project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mysite.project.Entity.AccidentEntity;

public interface AccidentRepository extends JpaRepository<AccidentEntity, Long> {
}
