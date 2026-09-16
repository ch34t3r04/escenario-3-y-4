import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * GenerateInfoFiles
 *
 * Clase encargada de generar los archivos planos pseudoaleatorios que sirven
 * como entrada para el programa principal del proyecto (Entrega 1 - Semana 3).
 *
 * Genera tres tipos de archivo:
 *  - productos.txt          -> catalogo de productos disponibles
 *  - vendedores.txt         -> informacion de los vendedores
 *  - ventas_<id>_<name>.txt -> ventas individuales de cada vendedor
 *
 * Ninguno de los metodos solicita informacion al usuario.
 *
 * @author Sebas
 */
public class GenerateInfoFiles {

    /** Generador de numeros pseudoaleatorios reutilizado en toda la clase. */
    private static final Random RANDOM = new Random();

    /** Nombres usados para generar vendedores de forma coherente. */
    private static final String[] NOMBRES = {
        "Juan", "Camila", "Andres", "Laura", "Santiago",
        "Valentina", "Felipe", "Daniela", "Sebastian", "Mariana"
    };
    private static final String[] APELLIDOS = {
        "Gomez", "Rodriguez", "Martinez", "Lopez", "Garcia",
        "Perez", "Sanchez", "Ramirez", "Torres", "Diaz"
    };
    private static final String[] TIPOS_DOCUMENTO = {"CC", "TI", "CE"};

    /** Nombres base usados para generar el catalogo de productos. */
    private static final String[] NOMBRES_PRODUCTOS = {
        "Camisa", "Pantalon", "Zapatos", "Chaqueta", "Gorra",
        "Medias", "Cinturon", "Bufanda", "Guantes", "Bolso"
    };

    /**
     * Cantidad total de productos generados en la ultima llamada a
     * {@link #createProductsFile(int)}. Se usa para poder generar IDs de
     * producto validos dentro de {@link #createSalesMenFile(int, String, long)}.
     */
    private static int totalProductosGenerados = 1;

    /**
     * Punto de entrada del programa. No solicita informacion al usuario;
     * genera los archivos de prueba requeridos por la Entrega 1.
     *
     * @param args no se utilizan
     */
    public static void main(String[] args) {
        int cantidadProductos = 10;
        int cantidadVendedores = 5;

        try {
            createProductsFile(cantidadProductos);
            createSalesManInfoFile(cantidadVendedores);
            System.out.println("Archivos generados exitosamente.");
        } catch (IOException e) {
            System.out.println("Error generando los archivos: " + e.getMessage());
        }
    }

    /**
     * Crea el archivo de productos disponibles (productos.txt).
     * Formato por linea: IDProducto;NombreProducto;PrecioPorUnidad
     *
     * @param productsCount cantidad de productos a generar
     * @throws IOException si ocurre un error escribiendo el archivo
     */
    public static void createProductsFile(int productsCount) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("productos.txt"))) {
            for (int i = 1; i <= productsCount; i++) {
                String idProducto = "P" + i;
                String nombreProducto = NOMBRES_PRODUCTOS[(i - 1) % NOMBRES_PRODUCTOS.length] + i;
                int precio = 10000 + RANDOM.nextInt(90000);

                writer.write(idProducto + ";" + nombreProducto + ";" + precio);
                writer.newLine();
            }
        }
        totalProductosGenerados = productsCount;
    }

    /**
     * Crea el archivo de informacion de vendedores (vendedores.txt).
     * Formato por linea: TipoDocumento;NumeroDocumento;Nombres;Apellidos
     *
     * Adicionalmente genera el archivo individual de ventas de cada
     * vendedor llamando a {@link #createSalesMenFile(int, String, long)}.
     *
     * @param salesmanCount cantidad de vendedores a generar
     * @throws IOException si ocurre un error escribiendo alguno de los archivos
     */
    public static void createSalesManInfoFile(int salesmanCount) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("vendedores.txt"))) {
            for (int i = 0; i < salesmanCount; i++) {
                String tipoDocumento = TIPOS_DOCUMENTO[RANDOM.nextInt(TIPOS_DOCUMENTO.length)];
                long numeroDocumento = 1000000000L + RANDOM.nextInt(900000000);
                String nombres = NOMBRES[RANDOM.nextInt(NOMBRES.length)];
                String apellidos = APELLIDOS[RANDOM.nextInt(APELLIDOS.length)];

                writer.write(tipoDocumento + ";" + numeroDocumento + ";" + nombres + ";" + apellidos);
                writer.newLine();

                int cantidadVentas = 3 + RANDOM.nextInt(8);
                createSalesMenFile(cantidadVentas, nombres + "_" + apellidos, numeroDocumento);
            }
        }
    }

    /**
     * Crea el archivo de ventas de un vendedor especifico.
     * La primera linea contiene el tipo y numero de documento del vendedor;
     * las siguientes lineas contienen, una por linea, el ID de producto
     * vendido y la cantidad vendida.
     *
     * Nota: la firma de este metodo (definida por el enunciado) no recibe
     * el tipo de documento, por lo que se usa "CC" por defecto en la
     * primera linea del archivo.
     *
     * @param randomSalesCount cantidad de lineas de venta a generar
     * @param name nombre del vendedor, usado para nombrar el archivo
     * @param id numero de documento del vendedor
     * @throws IOException si ocurre un error escribiendo el archivo
     */
    public static void createSalesMenFile(int randomSalesCount, String name, long id) throws IOException {
        String tipoDocumento = "CC";
        String nombreArchivo = "ventas_" + id + "_" + name + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            writer.write(tipoDocumento + ";" + id);
            writer.newLine();

            for (int i = 0; i < randomSalesCount; i++) {
                String idProducto = "P" + (1 + RANDOM.nextInt(totalProductosGenerados));
                int cantidadVendida = 1 + RANDOM.nextInt(20);

                writer.write(idProducto + ";" + cantidadVendida + ";");
                writer.newLine();
            }
        }
    }
}
