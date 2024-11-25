package dao;

import java.util.List;

import model.Costumer;
import model.Service;

public interface ServiceDao {
	 void save(Service sc);
	 public List<Service> show();
	 public void delete(String id);
	 public void update(Service sc);
	 
}
