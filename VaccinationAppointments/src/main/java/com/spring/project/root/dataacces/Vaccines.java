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

@Entity(name = "vaccines")
@Table(name = "vaccines")
@NamedQueries({
    @NamedQuery(name = "vaccines.findAll", query = "SELECT v FROM vaccines v")
    , @NamedQuery(name = "vaccines.findById", query = "SELECT v FROM vaccines v WHERE v.idvaccine = :idvaccine")
    , @NamedQuery(name = "vaccines.findAllAvailable", query = "SELECT v FROM vaccines v WHERE v.availability = 1")
    })
public class Vaccines implements EntityModel{
	
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idvaccine")
    private Integer idvaccine;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "doses")
    private Integer doses;
    
    @Column(name = "minAge")
    private Integer minAge;
    
    @Column(name = "availability")
    private short availability;

	public Vaccines() {
	}

	public Vaccines(Integer idvaccine, String name, Integer doses, Integer minAge, short availability) {
		super();
		this.idvaccine = idvaccine;
		this.name = name;
		this.doses = doses;
		this.minAge = minAge;
		this.availability = availability;
	}

	public Integer getIdvaccine() {
		return idvaccine;
	}

	public void setIdvaccine(Integer idvaccine) {
		this.idvaccine = idvaccine;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDoses() {
		return doses;
	}

	public void setDoses(Integer doses) {
		this.doses = doses;
	}

	public Integer getMinAge() {
		return minAge;
	}

	public void setMinAge(Integer minAge) {
		this.minAge = minAge;
	}

	public short getAvailability() {
		return availability;
	}

	public void setAvailability(short availability) {
		this.availability = availability;
	}
    
    
	
}
