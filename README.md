## Parte 1: Explorar: la programación funcional en el mundo real

### Caso Real 1: Apache Spark (Procesamiento de Big Data en Scala)

* **Problema que resuelven:** Procesamiento distribuido de volúmenes masivos de datos en clústeres.


* **Lenguaje o herramienta:** Scala.


* **Ventaja del enfoque funcional:** Permite aplicar transformaciones inmutables y evaluación perezosa (*lazy evaluation*), optimizando planes de ejecución en paralelo sin efectos secundarios.
* **Fragmento de código real:**
```scala
val logData = spark.read.textFile("hdfs://.../logs.txt")
val numErrors = logData.filter(line => line.contains("ERROR")).count()

```


*Explicación:* La función anónima `line => line.contains("ERROR")` evalúa cada línea del archivo de registro para filtrar aquellas que contienen errores de manera declarativa.
* **Fuente consultada:** Documentación oficial de Apache Spark ([spark.apache.org](https://spark.apache.org/docs/latest/rdd-programming-guide.html)).



### Caso Real 2: Pandas en Python (Análisis de Datos)

* **Problema que resuelven:** Limpieza, filtrado y transformación estructurada de datasets tabulares para análisis estadístico.


* **Lenguaje o herramienta:** Python (Librería Pandas).


* **Ventaja del enfoque funcional:** Permite aplicar transformaciones fila por fila o columna por columna de forma compacta mediante funciones `lambda`, evitando bucles explícitos `for`.
* **Fragmento de código real:**
```python
import pandas as pd
df = pd.DataFrame({'cliente': ['A', 'B', 'C'], 'total': [120, 85, 450]})
df['categoria_compra'] = df['total'].apply(lambda precio: 'Alto' if precio > 100 else 'Bajo')

```


*Explicación:* Utiliza el método `.apply()` junto con una función anónima `lambda` para clasificar dinámicamente los registros de la columna según el valor numérico.
* **Fuente consultada:** Documentación oficial de Pandas Python ([pandas.pydata.org](https://pandas.pydata.org/docs/)).



---

## Parte 2: Entender: ¿qué es una función anónima?

### ¿Qué es una función anónima y de dónde viene el nombre lambda?

Una función anónima es una definición de función que no posee un identificador o nombre formal asignado en el ámbito del programa. Se utiliza de forma local y temporal para ser pasada como argumento a funciones de orden superior. Su nombre proviene del **Cálculo Lambda ($\lambda$-calculus)**, un sistema formal creado por Alonzo Church en la década de 1930 para formalizar la computación y la abstracción de funciones.

### Diferencia con una función nombrada

Una función nombrada se declara explícitamente con un identificador para ser reutilizada en múltiples lugares del código. Una función anónima se define directamente en el punto donde se requiere utilizar.

* **Ejemplo en Scala:**
```scala
// Función nombrada
def esPar(n: Int): Boolean = n % 2 == 0

// Función anónima (Lambda)
(n: Int) => n % 2 == 0

```



### ¿Qué significa que las funciones sean ciudadanos de primera clase?

Significa que las funciones poseen los mismos derechos que cualquier otro tipo de dato primitivo o variable: pueden asignarse a variables, almacenarse en estructuras de datos, retornarse desde otras funciones y pasarse como argumentos. Esto es lo que permite que métodos como `map` o `filter` reciban expresiones lambda como parámetros.

### ¿Qué es un closure?

Un closure (clausura) es una función anónima que captura o hace referencia a variables definidas fuera de su ámbito léxico local, es decir, en el contexto externo donde fue creada.

* **Ejemplo en Scala:**
```scala
val umbral = 50
val filtrarAltos = (precio: Int) => precio > umbral // 'umbral' es capturado del exterior

```



---

## Parte 3: Aplicar: ¿en qué problemas sirven las funciones anónimas?

### Dataset Base Común (Scala, Java y Python)

Se utiliza un conjunto de datos de 10 productos con los atributos: `nombre`, `categoria`, `precio` y `stock`.

---

### 3.1 Selección (`filter`)

* **Pregunta que responde:** ¿Qué elementos cumplen una condición?


* **Situación real:** Un comercio necesita filtrar los productos de la categoría "Tecnología" cuyo precio sea superior a $100.

#### Scala

```scala
val resultadoScala = productos.filter(p => p.categoria == "Tecnología" && p.precio > 100)

```

#### Java

```java
List<Producto> resultadoJava = productos.stream()
    .filter(p -> p.categoria().equals("Tecnología") && p.precio() > 100)
    .collect(Collectors.toList());

```

#### Python

```python
resultado_python = list(filter(lambda p: p.categoria == 'Tecnología' and p.precio > 100, productos))

```

* **Salida:** `[Laptop, Monitor, Audífonos, Micrófono]`

---

### 3.2 Transformación (`map`)

* **Pregunta que responde:** ¿Cómo convierto cada elemento en otro valor?


* **Situación real:** Generar un reporte con el nombre de cada producto y su precio aplicando un descuento del 10%.

#### Scala

```scala
val resultadoScala = productos.map(p => s"${p.nombre}: $$${p.precio * 0.9}")

```

#### Java

```java
List<String> resultadoJava = productos.stream()
    .map(p -> p.nombre() + ": $" + (p.precio() * 0.9))
    .collect(Collectors.toList());

```

#### Python

```python
resultado_python = list(map(lambda p: f"{p.nombre}: ${p.precio * 0.9}", productos))

```

* **Salida:** Lista de cadenas formateadas con el precio rebajado para cada producto.

---

### 3.3 Extremos (`maxBy` / `minBy`)

* **Pregunta que responde:** ¿Cuál es el mejor o el peor según un criterio?


* **Situación real:** Encontrar el producto con el precio más alto del inventario.

#### Scala

```scala
val resultadoScala = productos.maxBy(p => p.precio)

```

#### Java

```java
Producto resultadoJava = productos.stream()
    .max(Comparator.comparingDouble(Producto::precio))
    .orElseThrow();

```

#### Python

```python
resultado_python = max(productos, key=lambda p: p.precio)

```

* **Salida:** `Producto más caro: Laptop (1200.0)`

---

### 3.4 Acumulación (`foldLeft` / `reduce`)

* **Pregunta que responde:** ¿Qué valor resume la lista?


* **Situación real:** Calcular el valor total monetario del inventario sumando el producto de `precio * stock`.

#### Scala

```scala
val resultadoScala = productos.foldLeft(0.0)((acc, p) => acc + (p.precio * p.stock))

```

#### Java

```java
double resultadoJava = productos.stream()
    .mapToDouble(p -> p.precio() * p.stock())
    .sum();

```

#### Python

```python
from functools import reduce
resultado_python = reduce(lambda acc, p: acc + (p.precio * p.stock), productos, 0.0)

```

* **Salida:** `Valor total del inventario: $14835.0`

---

## Parte 4: Ampliar: cuatro operaciones nuevas

### 4.1 Ficha de cada operación en Scala

#### 1. groupBy

* **Qué hace:** Agrupa los elementos de la colección en un mapa donde las claves son los valores retornados por la función anónima.


* **Lambda recibida:** Recibe una función `A => K` que extrae la clave de agrupación.


* **Ejemplo:** `val porCat = productos.groupBy(p => p.categoria)`
* **Problema real:** Clasificar el inventario por categoría para reportes sectorizados (similar a Agrupación).

#### 2. partition

* **Qué hace:** Divide la colección en una tupla de dos listas: una con los elementos que cumplen la condición booleana y otra con los que no.


* **Lambda recibida:** Recibe una función `A => Boolean`.


* **Ejemplo:** `val (criticos, normales) = productos.partition(p => p.stock < 5)`
* **Problema real:** Separar automáticamente productos con stock crítico para reabastecimiento urgente.

#### 3. sortBy

* **Qué hace:** Ordena los elementos de la colección en orden ascendente basándose en la clave extraída.


* **Lambda recibida:** Recibe una función `A => B` (donde B es ordenable, como numérico o texto).


* **Ejemplo:** `val ordenados = productos.sortBy(p => p.precio)`
* **Problema real:** Presentar catálogos ordenados por costo.

#### 4. distinctBy

* **Qué hace:** Elimina elementos duplicados conservando el primero de ellos según el criterio evaluado por la función.


* **Lambda recibida:** Recibe una función `A => B` que define la propiedad de unicidad.


* **Ejemplo:** `val unicas = productos.distinctBy(p => p.categoria)`
* **Problema real:** Obtener el listado de categorías únicas presentes en el inventario.

### 4.2 Combinar operaciones

* **Pregunta planteada:** ¿Cuáles son las categorías que tienen productos con stock mayor a 5 unidades, ordenadas alfabéticamente?


* **Cadena en Scala:**
```scala
val resultado = productos
  .filter(p => p.stock > 5)         // 1. Filtrar stock mayor a 5 (Vista en clase)
  .distinctBy(p => p.categoria)     // 2. Obtener categorías únicas (Nueva 1)
  .map(p => p.categoria)            // 3. Extraer el nombre de la categoría
  .sortBy(cat => cat)               // 4. Ordenar alfabéticamente (Nueva 2)

```


* **Explicación del flujo:** Primero se filtran los productos válidos por stock, luego se eliminan categorías repetidas, se transforman los objetos a texto plano mediante `map` y finalmente se ordenan alfabéticamente las categorías resultantes.



---

## Parte 5: Comparar y concluir

### 5.1 Tabla comparativa de lenguajes

| Criterio                         |                                  Scala                            | Java | Python |
| ---                              |                                                               --- |                             --- | --- |
| **Sintaxis de la lambda**
| `p => p.precio > 10`             |                              `p -> p.precio() > 10`               | `lambda p: p.precio > 10` |
| **Declaración de tipos**
| Inferencia estática automática   | Inferencia mediante Interfaces Funcionales                        | Tipado dinámico implícito |
| **Operaciones con listas**
| Nativas directas en colecciones  | Mediante API de Streams | Nativas (`map`, `filter`) o `functools` |
| **Restricción del lenguaje**
| Mínimas; diseño funcional nativo |       Verbosidad por sintaxis de clases y streams                 | Limitado a expresiones de una sola línea (`lambda`) |   
| **Legibilidad**                  |                        Muy alta y concisa                         | Moderada (requiere boilerplate) | Alta, limpia pero limitada |

* **¿En qué lenguaje fue más natural resolver los problemas?** En **Scala**, debido a que el paradigma funcional está integrado de forma nativa en su sintaxis y colecciones sin necesidad de adaptadores ni conversiones a streams.


* **¿En qué tipo de problema encontraste más diferencias?** En la **acumulación / reducción (`foldLeft` / `reduce`)**, ya que Java exige un flujo de conversión numérico (`mapToDouble`) y Python requiere importar explícitamente el módulo `functools`.



### 5.2 Conclusión

Las funciones anónimas son el núcleo conceptual que permite la programación declarativa sobre colecciones, separando el algoritmo de recorrido de la lógica de negocio específica. Tal como se evidenció en casos del mundo real como Apache Spark, el uso de expresiones lambda puras e inmutables facilita la paralelización y optimización de tareas pesadas a escala empresarial sin comprometer la estabilidad del sistema.

IA:https://gemini.google.com/
