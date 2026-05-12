/**
 * Esta clase modela un personaje de un juego de rol.
 */
public class Personaje {
    private final Integer MAX_VIDA;
    private final Integer PESO_MAXIMO_BOLSA;
    private String nombre;
    private Integer vida;
    private Caldero caldero;
    private Elemento objeto;
    private Bolsa bolsa;

    public Personaje (String nombre, Integer vida, Integer peso) {
        this.nombre = nombre;
        this.vida = vida;
        this.MAX_VIDA = vida;
        this.PESO_MAXIMO_BOLSA = peso;
        this.bolsa = null;
        this.caldero = null;
        this.objeto = null;
    }

    public void setBolsa(Bolsa bolsa) {
        if (bolsa.getPesoMaximo() > PESO_MAXIMO_BOLSA) {
            System.out.println("Bolsa inapropiada");
            return;
        }
        if (this.bolsa != null && bolsa.getPesoMaximo() <= this.bolsa.getPesoMaximo()) {
            System.out.println("Bolsa inapropiada");
            return;
        }
        if (this.bolsa != null) {
            for (Elemento e : this.bolsa.getElementosEnLaBolsa()) {
                bolsa.addElemento(e);
                this.bolsa.delElemento(e.getNombre()); // vaciar bolsa vieja
            }
        }
        this.bolsa = bolsa;
    }

    public Bolsa getBolsa() {
        return bolsa;
    }

    public void guardarElemento() {
        if (objeto == null) {
            System.out.println("No hay elemento para agregar a la bolsa");
            return;
        }
        bolsa.addElemento(objeto);
        objeto = null;
    }

    public void tomarElemento (String nombre) {
        Elemento elemento = bolsa.delElemento(nombre);
        if (elemento == null) {
            System.out.println("No se cuenta con el " + nombre);
            return;
        }
        objeto = elemento;
    }
    
    public void setCaldero(Caldero caldero) {
        this.caldero = caldero;
    }
    
    public Caldero getCaldero() {
        return caldero;
    }

    public void prepararReceta (Receta receta) {
        caldero.setReceta(receta);
        for (String ingrediente : receta.getIngredientes()) {
            Elemento elemento = bolsa.delElemento(ingrediente);
            if (elemento != null) {
                caldero.addIngrediente(elemento);
            }
        }
        int faltantes = caldero.getIngredientesFaltantes().size();
        if (faltantes > 0) {
            System.out.println("Faltan " + faltantes + " ingredientes para " + receta.getNombre());
        } else {
            caldero.prepararPocima();
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getVida() {
        return vida;
    }

    public void setVida(Integer vida) {
        this.vida = vida;
    }

    public void resetVida(Integer vida) {
        this.vida = MAX_VIDA;
    }

    public Elemento getElemento () {
        return objeto;
    }

    public void setElemento (Elemento objeto) {
        this.objeto = objeto;
    }
}
