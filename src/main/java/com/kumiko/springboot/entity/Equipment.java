package com.kumiko.springboot.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "equipment")
public class Equipment implements Serializable{

//Column
	@Id
	@GeneratedValue
	@Column(name="equipment_id")
	private Integer equipmentId;

	@Column(name="equipment_name")
	private String equipmentName;

	@ManyToOne
	@JoinColumn(name="room_id")
	private Room room;

//コンストラクタ
	public Equipment() {
		super();
		room = new Room();
	}

//setter getter
	public Integer getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

}
