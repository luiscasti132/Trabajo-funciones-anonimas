case class Producto(nombre: String, categoria: String, precio: Double, stock: Int)

val productos = List(
  Producto("Laptop", "Tecnología", 1200.0, 10),
  Producto("Mouse", "Tecnología", 25.0, 50),
  Producto("Teclado", "Tecnología", 45.0, 30),
  Producto("Monitor", "Tecnología", 300.0, 15),
  Producto("Audífonos", "Tecnología", 80.0, 25),
  Producto("Micrófono", "Tecnología", 110.0, 8),
  Producto("Camisa", "Ropa", 35.0, 40),
  Producto("Pantalón", "Ropa", 50.0, 20),
  Producto("Cafetera", "Hogar", 90.0, 12),
  Producto("Licuadora", "Hogar", 75.0, 18)
)

val resultado = productos
  .filter(p => p.stock > 5)         // 1. Filtrar stock mayor a 5 (Vista en clase)
  .distinctBy(p => p.categoria)     // 2. Obtener categorías únicas (Nueva 1)
  .map(p => p.categoria)            // 3. Extraer el nombre de la categoría
  .sortBy(cat => cat)               // 4. Ordenar alfabéticamente (Nueva 2)
