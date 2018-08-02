package ApgNetworking.models;

import java.util.Collection;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String semester;
	private boolean active;
	private String crn;
	@ManyToMany
	private Collection<ApgUser> users;

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
	public Collection<ApgUser> getUsers() {
		return users;
	}
	public void setUsers(Collection<ApgUser> users) {
		this.users = users;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

	public String getCrn() {
		return crn;
	}

	public void setCrn(String crn) {
		this.crn = crn;
	}
}
