package io.github.plindzek.lang;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Adam
 * Lang repository
 */
@Repository
public interface LangRepository extends JpaRepository<Lang, Integer> {

}