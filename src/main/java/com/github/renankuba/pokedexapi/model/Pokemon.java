package com.github.renankuba.pokedexapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Pokemon {
    @Id
    @Column(name = "id")
    private Long number;
    private String name;
    private String image;

    public Pokemon(){}

    public Pokemon(Long number, String name, String image) {
        this.number = number;
        this.name = name;
        this.image = image;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	return prime * result + number.intValue();
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Pokemon other = (Pokemon) obj;
	if (number != other.number)
		return false;
	return true;
}
}
