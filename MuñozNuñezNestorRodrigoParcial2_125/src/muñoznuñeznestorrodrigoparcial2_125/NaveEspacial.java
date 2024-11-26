package muñoznuñeznestorrodrigoparcial2_125;

import java.io.Serializable;
import java.util.Objects;

public class NaveEspacial implements CSVSerializable,Comparable<NaveEspacial>,Serializable{
    private static final long serialVersionUID = 1L;
    private int id;
    private String nombre;
    private TipoDeNave tipoDeNave;
    private int cantidadMaximaTripulantes;

    public NaveEspacial(int id,String nombre, TipoDeNave tipoDeNave, int cantidadMaximaTripulantes) {
        this.id = id;
        this.nombre = nombre;
        this.tipoDeNave = tipoDeNave;
        this.cantidadMaximaTripulantes = cantidadMaximaTripulantes;
    }

    @Override
    public String toString() {
        String nombreTipoDeNave = tipoDeNave.name();
        return "NaveEspacial" +" id: " + id + "Nombre: " + nombre + ", TipoDeNave:" + nombreTipoDeNave.substring(0,1) + nombreTipoDeNave.substring(1).toLowerCase() + ", Cantidad maxima de tripulantes: " + cantidadMaximaTripulantes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.nombre);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof TipoDeNave t) {
            return tipoDeNave.equals(t);
        }
        NaveEspacial other = (NaveEspacial) obj;
        return this.nombre.equals(other.nombre);           
    }
    
    
    @Override
    public String toCSV() {
        return nombre + "," + tipoDeNave + "," + cantidadMaximaTripulantes;
    }

    public static NaveEspacial fromCSV(String empleadoCSV) {
        String[] values = empleadoCSV.split(",");
        NaveEspacial naveRetorno = null;
        if (values.length == 4) {
            int id = Integer.parseInt(values[0]);
            String nombre = values[1];
            TipoDeNave tipoDeNave = TipoDeNave.valueOf(values[2]);
            int cantidad = Integer.parseInt(values[3]); 
            naveRetorno = new NaveEspacial(id,nombre, tipoDeNave, cantidad);
        }
        return naveRetorno;
    }

    @Override
    public int compareTo(NaveEspacial n) {
        return Integer.compare(id, n.id);
    }

    public int getCantidadMaximaTripulantes() {
        return cantidadMaximaTripulantes;
    }

    public TipoDeNave getTipoDeNave() {
        return tipoDeNave;
    }

    public String getNombre() {
        return nombre;
    }
    
    
}