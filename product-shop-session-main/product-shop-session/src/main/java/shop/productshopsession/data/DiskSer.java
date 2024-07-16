package shop.productshopsession.data;

import java.io.*;

public class DiskSer {
    //scriviamo l'oggetto status su un file
    public void write(String id, Status status) {
        System.out.print("DiskSer: write " + id);
        try {
            //Crea un FileOutputStream che scrive su un file il cui nome è l'ID concatenato con ".ser".
            FileOutputStream fos = new FileOutputStream(id.concat(".ser"));
            //Crea un ObjectOutputStream che permette di scrivere oggetti nel FileOutputStream.
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            // serializza object
            oos.writeObject(status);
            fos.close();
            oos.close();
        } catch (FileNotFoundException e1) {
            System.out.println(" *** not found *** " + e1.getMessage()); //stampa un messaggio di errore se si verifica un FileNotFoundException.
        } catch (IOException e2) {
            System.out.println(" *** failed *** " + e2.getMessage()); //stampa un'eccezione di tipo IOException se si verifica un errore di input/output durante la scrittura dell'oggetto.
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
            //Stampa un messaggio di errore se si verifica un FileNotFoundException.
            System.out.println(" ** not found ** " + e1.getMessage());
            
            return null;
        } catch (IOException e2) {
            //: Stampa un messaggio di errore se si verifica un IOException.
            System.out.println(" ** failed ** " + e2.getMessage());
            return null;
        } catch (ClassNotFoundException e3) { 
            //cattura un'eccezione di tipo ClassNotFoundException se la classe dell'oggetto deserializzato non può essere trovata.
            return null;
        }
    }
}