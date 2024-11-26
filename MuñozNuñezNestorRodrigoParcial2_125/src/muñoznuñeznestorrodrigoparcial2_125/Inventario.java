package muñoznuñeznestorrodrigoparcial2_125;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Inventario<T> implements Iterable<T>{
    
    List<T> inventario = new ArrayList<>();
    
    public void agregar(T item) {
        if (item == null) {
            throw new NullPointerException("El item que se a pasado es nulo");
        }
        else if (inventario.contains(item)) {
            throw new RuntimeException("El item esta repetido");
        }
        inventario.add(item);
    }
    
    public T obtener(int indice) {
        if (validadorDeIndice(indice)) {
            throw new IndexOutOfBoundsException("El indice es invalido");
        }
        return inventario.get(indice);
    }
    
    public void eliminar(int indice) {
        if (validadorDeIndice(indice)) {
            throw new IndexOutOfBoundsException("El indice es invalido");
        }
        inventario.remove(indice);
    }
    
     private boolean validadorDeIndice(int indice){
        if (indice < 0 || indice >= inventario.size()) {
            return true;
        }
        return false;
    }
    
    public List<T> filtrar(Predicate<? super T> criterio) {
        List<T> listaRetorno = new ArrayList<>();
        for (T item : inventario) {
            if (criterio.test(item)) {
                listaRetorno.add(item);
            }
        }
        return listaRetorno;
    } 
     
    @Override
    public Iterator<T> iterator() {
        if (!inventario.isEmpty() || inventario.get(0) instanceof Comparable) {
            return Ordenar((Comparator<? super T>) Comparator.naturalOrder());
        }
        return new ArrayList<>(inventario).iterator();
    }
    
    public Iterator<T> Ordenar(Comparator<? super T> comparator){
        List<T> listaRetorno = new ArrayList<>(inventario);
        listaRetorno.sort(comparator);
        return listaRetorno.iterator();
    }
    
    public List<T> transformar(Function<? super T, ? extends T> transformacion) {
        List<T> listaRetorno = new ArrayList<>();
        for (T item : inventario) {
            listaRetorno.add(transformacion.apply(item));
        }
        return listaRetorno;
    }
    
    public void guardar(String nombreArchivo){
        if (inventario.isEmpty()) {
            throw new NullPointerException("La lista esta vacia");
        }
        
        T elemento = inventario.get(0);
        if (elemento instanceof Serializable) {
            try(ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(nombreArchivo+"naves.dat")) {
            }){    
            salida.writeObject(inventario);                       
            } catch (IOException ex) 
            {
                System.out.println(ex.getMessage());
            }
        }
        else{
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo+"naves.csv"))){
            bw.write("id,marca,precio,tipo\n");
            for (T item : inventario) {
                if (item instanceof NaveEspacial n) {
                    bw.write(n.toCSV() + "\n");
                }            
            }           
            }catch(IOException ex){
            System.out.println(ex.getMessage());
            }
        }
    }
    
    public void cargar(String nombreArchivo){
        File archivo = new File(nombreArchivo);
        if (!archivo.exists()) {
            throw new NullPointerException("El archivo esta vacio");
        }
        if (nombreArchivo.equals("src/data/naves.dat")) {
            
        try(ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(nombreArchivo))){
        
            inventario = (List<T>) entrada.readObject();
            
            }catch(IOException | ClassNotFoundException ex){
            System.out.println(ex.getMessage());
            }
            
        }
        else
        {               
            try(BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));) {      
            String linea;
            br.readLine();
            while ((linea = br.readLine()) != null) {   
                if (linea.endsWith("\n")) {
                    linea.substring(linea.length() -1);
                }
                inventario.add((T)NaveEspacial.fromCSV(linea));               
            }         
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            
        }
    }
}
