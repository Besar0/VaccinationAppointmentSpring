package com.spring.project.root.dataacces;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.spring.project.root.repository.EntityModel;

@Entity(name = "users")
@Table(name = "users")
@NamedQueries({
    @NamedQuery(name = "users.findAll", query = "SELECT u FROM users u")
    , @NamedQuery(name = "users.findByIduser", query = "SELECT u FROM users u WHERE u.iduser = :iduser")
    , @NamedQuery(name = "users.findByUsername", query = "SELECT u FROM users u WHERE u.username = :username")
    })
public class Users implements EntityModel{
	
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser")
    private Integer iduser;
    
    @Column(name = "username")
    private String username;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "role")
    private String role;
    
    @Column(name = "vaccinated")
    private short vaccinated;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idcitizen", referencedColumnName = "idcitizen")
    private Citizens idcitizen;
    
    public Users() {
    }
    
    public Users(Integer iduser, String username, String password, String role) {
		super();
		this.iduser = iduser;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public boolean match(final String username, final String password) {
		return this.username.equals(username) && this.password.equals(password);
	}

	public Integer getIduser() {
		return iduser;
	}

	public void setIduser(Integer iduser) {
		this.iduser = iduser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Citizens getIdcitizen() {
		return idcitizen;
	}

	public void setIdcitizen(Citizens idcitizen) {
		this.idcitizen = idcitizen;
	}

	public short getVaccinated() {
		return vaccinated;
	}

	public void setVaccinated(short vaccinated) {
		this.vaccinated = vaccinated;
	}
	
	
	
}

