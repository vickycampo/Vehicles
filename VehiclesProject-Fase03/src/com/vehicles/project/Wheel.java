package com.vehicles.project;

import java.util.Objects;

public class Wheel {
	private String brand;
	private double diameter;

	public Wheel(String brand, double diameter) {
		this.brand = brand;
		this.diameter = diameter;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Wheel wheel = (Wheel) o;
		return Double.compare(wheel.diameter, diameter) == 0 &&
				Objects.equals(brand, wheel.brand);
	}
}
