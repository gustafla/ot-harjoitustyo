package td.dao;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import td.domain.TowerDefense;

public class TowerDefenseFileDao {
	private String path;

	TowerDefenseFileDao(String path) {
		this.path = path;
	}

	public TowerDefense load() {
		TowerDefense towerDefense = null;

		try {
			FileInputStream file = new FileInputStream(path);
			ObjectInputStream input = new ObjectInputStream(file);
			towerDefense = (TowerDefense) input.readObject();
			input.close();
			file.close();
		} catch (IOException i) {
			i.printStackTrace();
			return null;
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			return null;
		}

		return towerDefense;
	}

	public boolean save(TowerDefense towerDefense) {
		try {
			FileOutputStream file = new FileOutputStream(path);
			ObjectOutputStream output = new ObjectOutputStream(file);
			output.writeObject(towerDefense);
			output.close();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
