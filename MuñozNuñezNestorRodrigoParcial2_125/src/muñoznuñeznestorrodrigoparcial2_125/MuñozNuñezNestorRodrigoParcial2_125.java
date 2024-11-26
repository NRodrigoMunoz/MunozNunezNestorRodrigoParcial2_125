package muñoznuñeznestorrodrigoparcial2_125;

import java.util.Comparator;
import java.util.List;

public class MuñozNuñezNestorRodrigoParcial2_125 {

    public static void main(String[] args) {
        Inventario<NaveEspacial> inventarioNaves = new Inventario<>();
        try {
            inventarioNaves.agregar(new NaveEspacial(1,"USS Enterprise", TipoDeNave.CIENTIFICA, 50));
            inventarioNaves.agregar(new NaveEspacial(6,"Millennium Falcon", TipoDeNave.TRANSPORTE, 3));
            inventarioNaves.agregar(new NaveEspacial(5,"TIE Fighter", TipoDeNave.MILITAR, 1));
            //InventarioNaves.agregar(new NaveEspacial(7,"USS Enterprise", TipoDeNave.CIENTIFICA, 50));
            inventarioNaves.agregar(new NaveEspacial(4,"X-Wing", TipoDeNave.MILITAR, 2));
            inventarioNaves.agregar(new NaveEspacial(3,"Falcon", TipoDeNave.MILITAR, 2));
            inventarioNaves.agregar(new NaveEspacial(2,"Discovery One", TipoDeNave.CIENTIFICA, 100));
            
            

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
            // Mostrar todas las naves en el inventario
            System.out.println("Inventario de naves espaciales:");
            inventarioNaves.forEach(System.out::println);
        
        // Filtrar naves por categoría MILITAR
        System.out.println("\nNaves de la categoría MILITAR:");
        List<NaveEspacial> inventarioNavesMilitar = inventarioNaves.filtrar(n -> n.getTipoDeNave().equals(TipoDeNave.MILITAR));
        inventarioNavesMilitar.forEach(System.out::println);

        // Filtrar naves cuyo nombre contiene "Falcon"
        System.out.println("\nNaves de la categoría MILITAR:");
        List<NaveEspacial> inventarioNavesFalcon = inventarioNaves.filtrar(n -> n.getNombre().equals("Falcon"));
        inventarioNavesFalcon.forEach(System.out::println);
        
        
        // Ordenar naves de manera natural (por id)
        System.out.println("\nNaves ordenadas de manera natural (por id):");
        inventarioNaves.Ordenar(Comparator.naturalOrder());
        inventarioNaves.forEach(System.out::println);
        
        // Ordenar naves por nombre utilizando un Comparator
        
        System.out.println("\nNaves ordenadas por nombre:");
        Comparator<NaveEspacial> comparadorPorNombre = (n1,n2) -> n1.getNombre().compareTo(n2.getNombre());
        inventarioNaves.Ordenar(comparadorPorNombre);
        inventarioNaves.forEach(System.out::println);
        
        
       
    }
    
}
