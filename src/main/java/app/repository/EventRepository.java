package app.repository;

import app.entity.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by tlatz on 7/31/2018.
 */
@RepositoryRestResource
public interface EventRepository extends CrudRepository<Event, Integer> {

}
