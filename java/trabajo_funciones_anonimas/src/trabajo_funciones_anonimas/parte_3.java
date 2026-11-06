package trabajo_funciones_anonimas;

import java.util.List;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

// Definición del record Producto compatible con Java moderno
record Producto(String nombre, String categoria, double precio, int stock) {}

public class parte_3 {
    public static void main(String[] args) {
        // 1. Definir la lista de productos
        List<Producto> productos = Arrays.asList(
            new Producto("Laptop", "Tecnología", 1200.0, 10),
            new Producto("Mouse", "Tecnología", 25.0, 50),
            new Producto("Teclado", "Tecnología", 45.0, 30),
            new Producto("Monitor", "Tecnología", 300.0, 15),
            new Producto("Audífonos", "Tecnología", 80.0, 25),
            new Producto("Micrófono", "Tecnología", 110.0, 8),
            new Producto("Camisa", "Ropa", 35.0, 40),
            new Producto("Pantalón", "Ropa", 50.0, 20),
            new Producto("Cafetera", "Hogar", 90.0, 12),
            new Producto("Licuadora", "Hogar", 75.0, 18)
        );

        // 3.1 Selección (filter)
        List<Producto> resultadoFiltro = productos.stream()
            .filter(p -> p.categoria().equals("Tecnología") && p.precio() > 100)
            .collect(Collectors.toList());

        // 3.2 Transformación (map)
        List<String> resultadoMap = productos.stream()
            .map(p -> p.nombre() + ": $" + (p.precio() * 0.9))
            .collect(Collectors.toList());
        
        // 3.3 Extremos (maxBy / minBy)
        Producto resultadoMax = productos.stream()
            .max(Comparator.comparingDouble(Producto::precio))
            .orElseThrow();

        // 3.4 Acumulación (foldLeft / reduce)
        double resultadoAcumulado = productos.stream()
            .mapToDouble(p -> p.precio() * p.stock())
            .sum();

        // Imprimir resultados en consola
        System.out.println("--- 3.1 FILTRADOS ---");
        resultadoFiltro.forEach(System.out::println);
        
        System.out.println("\n--- 3.2 MAPEADOS ---");
        resultadoMap.forEach(System.out::println);

        System.out.println("\n--- 3.3 MÁS CARO ---");
        System.out.println(resultadoMax);

        System.out.println("\n--- 3.4 VALOR TOTAL DEL INVENTARIO ---");
        System.out.println("$" + resultadoAcumulado);
    }
}