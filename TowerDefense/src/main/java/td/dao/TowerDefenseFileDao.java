package td.dao;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.File;
import td.domain.TowerDefense;

/** De-/serializer and file writer for TowerDefense -objects. */
public class TowerDefenseFileDao {
    private String path;

    /**
     * Constructs a new TowerDefenseFileDao.
     *
     * @param path  file path (recommended suffix .ser)
     */
    public TowerDefenseFileDao(String path) {
        this.path = path;
    }

    /**
     * Try to load a TowerDefense object from file.
     *
     * @return  the loaded TowerDefense
     *
     * @throws IOException  thrown when cannot read from file
     */
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

    /**
     * Try to write a TowerDefense object to file.
     *
     * @param towerDefense  TowerDefense object to write
     *
     * @throws IOException  thrown when cannot write to file
     */
    public void save(TowerDefense towerDefense) throws IOException {
        FileOutputStream file = new FileOutputStream(path);
        ObjectOutputStream output = new ObjectOutputStream(file);
        output.writeObject(towerDefense);
        output.close();
        file.close();
    }

    /**
     * Delete the file if it exists.
     */
    public void delete() {
        new File(path).delete();
    }
}
