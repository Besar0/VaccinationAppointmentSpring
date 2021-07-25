package com.spring.project.root.dataacces;

import java.util.Date;

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

@Entity(name = "citizens")
@Table(name = "citizens")
@NamedQueries({
    @NamedQuery(name = "citizens.findAll", query = "SELECT c FROM citizens c")
    , @NamedQuery(name = "citizens.findById", query = "SELECT c FROM citizens c WHERE c.idcitizen = :idcitizen")
    })
public class Citizens implements EntityModel{
	
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcitizen")
    private Integer idcitizen;
    
    @Column(name = "ssn")
    private Integer ssn;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "surname")
    private String surname;
    
    @Column(name = "sex")
    private String sex;
    
    @Column(name = "dateOfBirth")
    private Date dateOfBirth;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idprofession", referencedColumnName = "idprofession")
    private Professions idprofession;
    
    
	public Citizens() {
	}

	public Citizens(Integer idcitizen, Integer ssn, String name, String surname, String sex, Date dateOfBirth) {
		this.idcitizen = idcitizen;
		this.ssn = ssn;
		this.name = name;
		this.surname = surname;
		this.sex = sex;
		this.dateOfBirth = dateOfBirth;
	}

	public Integer getIdcitizen() {
		return idcitizen;
	}

	public void setIdcitizen(Integer idcitizen) {
		this.idcitizen = idcitizen;
	}

	public Integer getSsn() {
		return ssn;
	}

	public void setSsn(Integer ssn) {
		this.ssn = ssn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Professions getIdprofession() {
		return idprofession;
	}

	public void setIdprofession(Professions idprofession) {
		this.idprofession = idprofession;
	}
	
}
