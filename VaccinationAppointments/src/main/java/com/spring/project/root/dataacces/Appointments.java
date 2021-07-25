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

import org.springframework.format.annotation.DateTimeFormat;

import com.spring.project.root.repository.EntityModel;

@Entity(name = "appointments")
@Table(name = "appointments")
@NamedQueries({
    @NamedQuery(name = "appointments.findAll", query = "SELECT a FROM appointments a")
    , @NamedQuery(name = "appointments.findById", query = "SELECT a FROM appointments a WHERE a.idappointment = :idappointment")
    , @NamedQuery(name = "appointments.findByIdUser", query = "SELECT a FROM appointments a WHERE a.iduser = :iduser")
    , @NamedQuery(name = "appointments.findByIdUserCanceled", query = "SELECT a FROM appointments a WHERE a.iduser = :iduser AND canceled = 1")
    , @NamedQuery(name = "appointments.findByIdUserMissed", query = "SELECT a FROM appointments a WHERE a.iduser = :iduser AND missed = 1")
    , @NamedQuery(name = "appointments.findByIdUserDone", query = "SELECT a FROM appointments a WHERE a.iduser = :iduser AND done = 1")
    })
public class Appointments implements EntityModel{
	
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idappointment")
    private Integer idappointment;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "iduser", referencedColumnName = "iduser")
    private Users iduser;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idvaccine", referencedColumnName = "idvaccine")
    private Vaccines idvaccine;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idcenter", referencedColumnName = "idcenter")
    private Centers idcenter;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date")
    private Date date;
    
    @Column(name = "time")
    private String time;
    
    @Column(name = "canceled")
    private short canceled;
    
    @Column(name = "missed")
    private short missed;
    
    @Column(name = "done")
    private short done;

	public Appointments() {
	}

	public Appointments(Integer idappointment, Date date, String time, short canceled, short missed, short done) {
		this.idappointment = idappointment;
		this.date = date;
		this.time = time;
		this.canceled = canceled;
		this.missed = missed;
		this.done = done;
	}

	public Integer getIdappointment() {
		return idappointment;
	}

	public void setIdappointment(Integer idappointment) {
		this.idappointment = idappointment;
	}

	public Users getIduser() {
		return iduser;
	}

	public void setIduser(Users iduser) {
		this.iduser = iduser;
	}

	public Vaccines getIdvaccine() {
		return idvaccine;
	}

	public void setIdvaccine(Vaccines idvaccine) {
		this.idvaccine = idvaccine;
	}

	public Centers getIdcenter() {
		return idcenter;
	}

	public void setIdcenter(Centers idcenter) {
		this.idcenter = idcenter;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public short getCanceled() {
		return canceled;
	}

	public void setCanceled(short canceled) {
		this.canceled = canceled;
	}

	public short getMissed() {
		return missed;
	}

	public void setMissed(short missed) {
		this.missed = missed;
	}

	public short getDone() {
		return done;
	}

	public void setDone(short done) {
		this.done = done;
	}

	@Override
	public String toString() {
		return "Appointments [iduser=" + iduser + ", idvaccine=" + idvaccine + ", idcenter=" + idcenter + ", date="
				+ date + ", time=" + time + ", canceled=" + canceled + ", missed=" + missed + ", done=" + done + "]";
	}
    
	
}
