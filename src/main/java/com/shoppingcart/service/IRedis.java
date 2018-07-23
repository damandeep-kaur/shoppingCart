package com.shoppingcart.service;

public interface IRedis {
	public Object getValue(String key);

	public void setValue(String key, Object value);

	public void deleteValue(String key);

}
