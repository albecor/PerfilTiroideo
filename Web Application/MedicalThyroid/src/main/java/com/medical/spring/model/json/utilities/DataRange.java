package com.medical.spring.model.json.utilities;
/**
 * @author GTST - Grupo de tratamiento de señales y telecomunicaciones - Universidad Surcolombiana
 *
 */
public class DataRange {
	Data[] low, high;

	public void setDataLow(Data[] low) {
		this.low = low;
	}

	public void setDataHigh(Data[] high) {
		this.high = high;
	}
}