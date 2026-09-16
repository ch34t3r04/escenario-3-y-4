# Proyecto de Gestión de Ventas — Entrega 1 (Semana 3)

## Descripción
Este proyecto forma parte del módulo de Programación. Consiste en un sistema que procesa archivos planos con información de vendedores, productos y ventas, generando reportes de desempeño.

En esta primera entrega se implementa la clase `GenerateInfoFiles`, encargada de generar los archivos de prueba (pseudoaleatorios) que servirán como entrada para el programa principal en entregas posteriores.

## Requisitos
- Java 8 (JDK)
- Eclipse para Java Developers o Visual Studio Code con la extensión "Extension Pack for Java"

## Estructura del proyecto
```
├── src/
│   └── GenerateInfoFiles.java
└── README.md
```

## ¿Qué hace `GenerateInfoFiles`?
La clase contiene tres métodos principales, todos sin solicitar información al usuario:

| Método | Función |
|---|---|
| `createProductsFile(int productsCount)` | Genera `productos.txt` con el catálogo de productos: `IDProducto;Nombre;Precio` |
| `createSalesManInfoFile(int salesmanCount)` | Genera `vendedores.txt` con la información de los vendedores: `TipoDocumento;NumeroDocumento;Nombres;Apellidos`, y crea el archivo de ventas de cada uno |
| `createSalesMenFile(int randomSalesCount, String name, long id)` | Genera el archivo individual de ventas de un vendedor: `TipoDocumento;NumeroDocumento` en la primera línea, seguido de `IDProducto;Cantidad;` por cada venta |

## Cómo ejecutarlo

**Desde terminal:**
```bash
cd src
javac GenerateInfoFiles.java
java GenerateInfoFiles
```

**Desde Eclipse o VS Code:**
Ejecutar la clase `GenerateInfoFiles` como aplicación Java (contiene el método `main`).

## Salida esperada
Al ejecutarse correctamente, se generan en la raíz del proyecto:
- `productos.txt`
- `vendedores.txt`
- Un archivo `ventas_<id>_<nombre>.txt` por cada vendedor generado

Y se muestra en consola el mensaje: `Archivos generados exitosamente.`

## Autor
Juan Sebastián Uribe López
