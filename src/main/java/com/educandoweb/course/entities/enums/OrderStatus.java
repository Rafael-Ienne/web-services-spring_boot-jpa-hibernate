package com.educandoweb.course.entities.enums;

public enum OrderStatus {

	/*As numerações deixam a manutenção mais eficiente*/
	WAITING_PAYMENT(1),
	PAID(2),
	SHIPPED(3),
	DELIVERED(4),
	CANCELED(5);
	
	/*Atributo que representa o código do tipo enumerado*/
	private int code;
	
	/*O construtor ocorre pela existência de um atributo(code)*/
	private OrderStatus(int code) {
		this.code = code;
	}
	
	/*Método público para acessar o código*/
	public int getCode() {
		return code;
	}
	
	/*Método estático que converte um número para um tipo enumerado (OrderStatus)*/
	public static OrderStatus valueOf(int code) {
		for(OrderStatus value: OrderStatus.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		/*Excessão que mostra que o código informado não corresponde a nenhum enum*/
		throw new IllegalArgumentException("Invalid OrderStatus code");
	}
}
