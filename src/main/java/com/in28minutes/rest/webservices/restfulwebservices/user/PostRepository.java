/**
 * 
 */
package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author <a href=mailto:support@aeronomos.org>aeronomos</a>
 *
 *         $Id: $
 * 
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

}
