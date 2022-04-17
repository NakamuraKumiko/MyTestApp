package com.kumiko.springboot.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "room")
public class Room implements Serializable {

	@Id
	@GeneratedValue
	@Column(name="room_id")
	private Integer roomId;

	@Column(name="room_name")
	private String roomName;

	@Column(name="capacity")
	private Integer capacity;

	@OneToMany(mappedBy = "room",cascade = CascadeType.ALL)
	//@Column(nullable = true)
	private List<Equipment> equipments;

	public Room() {
		super();
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public List<Equipment> getEquipments() {
		return equipments;
	}

	public void setEquipments(List<Equipment> equipments) {
		this.equipments = equipments;
	}


}
