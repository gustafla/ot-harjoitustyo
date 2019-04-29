package td.dao;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.File;
import td.domain.TowerDefense;

public class TowerDefenseFileDao {
    private String path;

    public TowerDefenseFileDao(String path) {
        this.path = path;
    }

    public TowerDefense load() throws IOException {
        TowerDefense towerDefense = null;

        try {
            FileInputStream file = new FileInputStream(path);
            ObjectInputStream input = new ObjectInputStream(file);
            towerDefense = (TowerDefense) input.readObject();
            input.close();
            file.close();
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
            return null;
        }

        towerDefense.createTransientFields();

        return towerDefense;
    }

    public void save(TowerDefense towerDefense) throws IOException {
        FileOutputStream file = new FileOutputStream(path);
        ObjectOutputStream output = new ObjectOutputStream(file);
        output.writeObject(towerDefense);
        output.close();
        file.close();
    }

    public void delete() {
        new File(path).delete();
    }
}
