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

@Entity(name = "centers")
@Table(name = "centers")
@NamedQueries({
    @NamedQuery(name = "centers.findAll", query = "SELECT c FROM centers c")
    , @NamedQuery(name = "centers.findById", query = "SELECT c FROM centers c WHERE c.idcenter = :idcenter")
    })
public class Centers implements EntityModel{
	
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcenter")
    private Integer idcenter;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "rooms")
    private Integer rooms;
    
    
	public Centers() {
	}

	public Centers(Integer idcenter, String name, String address, Integer rooms) {
		super();
		this.idcenter = idcenter;
		this.name = name;
		this.address = address;
		this.rooms = rooms;
	}

	public Integer getIdcenter() {
		return idcenter;
	}

	public void setIdcenter(Integer idcenter) {
		this.idcenter = idcenter;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getRooms() {
		return rooms;
	}

	public void setRooms(Integer rooms) {
		this.rooms = rooms;
	}
    
    
	
}
