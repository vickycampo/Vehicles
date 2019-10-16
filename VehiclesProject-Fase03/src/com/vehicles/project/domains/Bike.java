package com.vehicles.project.domains;

import java.util.List;

public class Bike extends Vehicle {

	public Bike(String plate, String brand, String color) {
		super(plate, brand, color);
	}

	public void addWheels(List<Wheel> frontWheels, List<Wheel> backWheels) throws Exception {
		addMultipleWheels(frontWheels);
		addMultipleWheels(backWheels);
	}
	public void addMultipleWheels(List<Wheel> wheels) throws Exception {
		if (wheels.size() == 0)
			throw new Exception();
		if ( wheels.size() == 2 )
		{
			Wheel rightWheel = wheels.get(0);
			Wheel leftWheel = wheels.get(1);

			if (!rightWheel.equals(leftWheel))
				throw new Exception();
		}
		for ( Wheel singleWheel : wheels)
		{
			this.wheels.add(singleWheel);
		}
	}
	public void addOneWheels(List<Wheel> wheels) throws Exception {
		if (wheels.size() != 1)
			throw new Exception();
		Wheel oneWheel = wheels.get(0);
		this.wheels.add(oneWheel);
	}

}
