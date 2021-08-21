
package com.neosono.assignment.jsfandspring.repository;

        import com.neosono.assignment.jsfandspring.model.Developer;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperFormRepository extends JpaRepository<Developer, Long> {
}

