/**
 *
 */
package io.github.plindzek.lang;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ensure CRUD operations for langs
 *
 * @author Adam
 */

@Repository
public interface LangRepository extends JpaRepository <Lang, Integer> {
List<Lang> findBylangCodeAndWelcomeMsg(String langCode, String welcomeMsg);

}
