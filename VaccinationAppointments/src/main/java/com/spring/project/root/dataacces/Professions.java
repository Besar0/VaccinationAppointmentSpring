package com.spring.project.root.dataacces;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.spring.project.root.repository.EntityModel;

@Entity(name = "professions")
@Table(name = "professions")
@NamedQueries({
    @NamedQuery(name = "professions.findAll", query = "SELECT p FROM professions p")
    , @NamedQuery(name = "professions.findById", query = "SELECT p FROM professions p WHERE p.idprofession = :idprofession")
    })
public class Professions implements EntityModel{
	
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idprofession")
    private Integer idprofession;
    
    @Column(name = "name")
    private String name;

    @Column(name = "priority")
    private short priority;
    
    
    
	public Professions() {
		super();
	}

	public Professions(Integer idprofession, String name, short priority) {
		this.idprofession = idprofession;
		this.name = name;
		this.priority = priority;
	}

	public Integer getIdprofession() {
		return idprofession;
	}

	public void setIdprofession(Integer idprofession) {
		this.idprofession = idprofession;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public short getPriority() {
		return priority;
	}

	public void setPriority(short priority) {
		this.priority = priority;
	}
	
}
