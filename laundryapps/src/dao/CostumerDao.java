package dao;

import java.util.List;

import model.Costumer;

public interface CostumerDao {
	 void save(Costumer cs);
	 public List<Costumer> show();
	 public void delete(String id);
	 public void update(Costumer cs);
	 
}
