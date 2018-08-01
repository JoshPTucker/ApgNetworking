package ApgNetworking.models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Course {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)	
private long id;
private String name;
private String semester;
private Set<user>users;
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getSemester() {
	return semester;
}
public void setSemester(String semester) {
	this.semester = semester;
}
public Set<user> getUsers() {
	return users;
}
public void setUsers(Set<user> users) {
	this.users = users;
}

}
