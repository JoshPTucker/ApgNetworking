package ApgNetworking.repositories;

import ApgNetworking.models.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface CourseRepository extends CrudRepository<Course,Long> {
    Collection<Course> findCoursesByActiveIsTrue();
    Course findByCrn(String crn);
}
