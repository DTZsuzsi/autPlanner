package repository;

import model.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChildRepository extends JpaRepository<Child, Long> {
public Child findChildByParentEmail(String email);
public Child findChildById(long id);
public List<Child> findChildrenByParentEmail(String parentEmail);
}
