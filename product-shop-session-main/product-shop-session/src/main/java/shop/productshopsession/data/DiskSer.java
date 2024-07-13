package shop.productshopsession.data;

import java.io.*;

public class DiskSer {
    public void write(String id, Status status) {
        System.out.print("DiskSer: write " + id);
        try {
            FileOutputStream fos = new FileOutputStream(id.concat(".ser"));
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            // Serialize object
            oos.writeObject(status);
            fos.close();
            oos.close();
        } catch (FileNotFoundException e1) {
            System.out.println(" ** not found ** " + e1.getMessage());
        } catch (IOException e2) {
            System.out.println(" ** failed ** " + e2.getMessage());
        }
        System.out.println(" OK");
    }

    public Status read(String id) {
        System.out.print("DiskSer: looking for " + id);
        try {
            FileInputStream fis = new FileInputStream(id.concat(".ser"));
            ObjectInputStream ois = new ObjectInputStream(fis);
            // Deserialize object
            Status serobject = (Status) ois.readObject();
            fis.close();
            ois.close();
            System.out.println(" read OK");
            return serobject;
        } catch (FileNotFoundException e1) {
            System.out.println(" ** not found ** " + e1.getMessage());
            return null;
        } catch (IOException e2) {
            System.out.println(" ** failed ** " + e2.getMessage());
            return null;
        } catch (ClassNotFoundException e3) {
            return null;
        }
    }
}
