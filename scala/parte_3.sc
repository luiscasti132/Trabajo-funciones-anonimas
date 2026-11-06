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
//3.1 Selección (filter)
val resultadoScala1 = productos.filter(p => p.categoria == "Tecnología" && p.precio > 100)

//3.2 Transformación (map)
val resultadoScala2 = productos.map(p => s"${p.nombre}: $$${p.precio * 0.9}")

//3.3 Extremos (maxBy / minBy)

val resultadoScala = productos.maxBy(p => p.precio)

//3.4 Acumulación (foldLeft / reduce)
val resultadoScala = productos.foldLeft(0.0)((acc, p) => acc + (p.precio * p.stock))

