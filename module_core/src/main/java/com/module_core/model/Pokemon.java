package com.module_core.model;

import java.util.List;

public class Pokemon {
    String name;
    String pokeType;
    float maxHp;
    float curHp;
    float attack;
    List<Skill> skills;
    
    public Pokemon(String name, String pokeType, float maxHp, float curHp, float attack) {
    	this.name = name;
    	this.pokeType = pokeType;
    	this.maxHp = maxHp;
    	this.curHp = curHp;
    	this.attack = attack;
//    	this.skills;
    }

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPokeType() {
		return pokeType;
	}
	
	public void setPokeType(String pokeType) {
		this.pokeType = pokeType;
	}

	public float getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(float maxHp) {
		this.maxHp = maxHp;
	}

	public float getCurHp() {
		return curHp;
	}

	public void setCurHp(float curHp) {
		this.curHp = curHp;
	}

	public float getAttack() {
		return attack;
	}

	public void setAttack(float attack) {
		this.attack = attack;
	}
	
	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}    
    
}
