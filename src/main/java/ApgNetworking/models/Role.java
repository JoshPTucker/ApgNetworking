package ApgNetworking.models;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String role;
	@ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
	private Collection<ApgUser> users;
	public Role(String role) {
		this.role = role;
	}
	public Role() {
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Collection<ApgUser> getUsers() {
		return users;
	}
	public void setUsers(Collection<ApgUser> users) {
		this.users = users;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

}
