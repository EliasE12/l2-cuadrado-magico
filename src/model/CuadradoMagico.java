package model;

import exceptions.NoNumeroImparException;
import exceptions.NoNumeroPositivoException;

// Clase

/**
 * Entidad que representa un cuadrado mágico.
 *
 */
public class CuadradoMagico {

    // Constantes

    public final static String PRIMERA_FILA = "Primera Fila";
    public final static String PRIMERA_COLUMNA = "Primera Columna";
    public final static String ULTIMA_FILA = "Útima Fila";
    public final static String ULTIMA_COLUMNA = "Última Columna";

    public final static String NO = "NorOeste";
    public final static String NE = "NorEste";
    public final static String SO = "SurOeste";
    public final static String SE = "SurEste";

    // Atributos

    private int orden;
    private String posicionPerimetral;
    private String sentidoLlenado;
    private int constanteMagica;
    private int[][] mCuadrado;

    // Constructor

    public CuadradoMagico() {}

    // Métodos

    // Inicializa los atributos del cuadrado.
    public void inicializar(int orden, String posicionPerimetral, String sentidoLlenado) throws NoNumeroImparException, NoNumeroPositivoException {

        if(orden%2==0){
            throw new NoNumeroImparException("El numero ingresado "+orden+", es un número par");
        }else if(orden<=0) {
            throw new NoNumeroPositivoException("El número ingresado "+orden+", es un número negativo");
        }else{
            this.orden = orden;
            this.posicionPerimetral = posicionPerimetral;
            this.sentidoLlenado = sentidoLlenado;
            this.constanteMagica = 0;
            mCuadrado = new int[orden][orden];
        }
    }

    // Llena el cuadrado mágico con los valores ingresados.
    public void llenarCuadradoMagico() {

        int n = 1;
        int i = 0, j = 0;

        if (posicionPerimetral.equals(PRIMERA_FILA)) {
            i = 0;
            j = orden / 2;
            mCuadrado[i][j] = n;
        } else if (posicionPerimetral.equals(PRIMERA_COLUMNA)) {
            i = orden / 2;
            j = 0;
            mCuadrado[i][j] = n;
        } else if (posicionPerimetral.equals(ULTIMA_FILA)) {
            i = orden - 1;
            j = orden / 2;
            mCuadrado[i][j] = n;
        } else {
            i = orden / 2;
            j = orden - 1;
            mCuadrado[i][j] = n;
        }

        ponerNumerosEnMatriz(i, j, n);
        calcularConstanteMagica();

        for (int in = 0; in < mCuadrado.length; in++) {
            for (int jn = 0; jn < mCuadrado[0].length; jn++) {
                System.out.print("|" + mCuadrado[in][jn] + "|");
            }
            System.out.println();
        }

    }

    // Pone el primer número en la posicion indicada por el usuario.
    public void ponerNumerosEnMatriz(int fil, int col, int posInicial) {

        switch(sentidoLlenado){
            case NE:
                llenarEnSentidoNE(fil,col,posInicial);
                break;
            case NO:
                llenarEnSentidoNO(fil,col,posInicial);
                break;
            case SE:
                llenarEnSentidoSE(fil,col,posInicial);
                break;
            case SO:
                llenarEnSentidoSO(fil,col,posInicial);
                break;
        }

    }

    // Continúa el llenado del cuadrado en el sentido noreste
    private void llenarEnSentidoNE(int fil, int col, int posInicial){

        int contador = orden * orden;
        int n = posInicial;
        int i = fil;
        int j = col;

        int x = fil;
        int y = col;

        while(contador-->1){
            n++;
                i = i - 1;
                j = j + 1;

                if (i < 0) {
                    i = mCuadrado.length - 1;
                }
                if (j > mCuadrado.length - 1) {
                    j = 0;
                }
                if (mCuadrado[i][j] != 0) {
                    if (posicionPerimetral.equals(PRIMERA_FILA)) {
                        i = x + 1;
                        j = y;
                        mCuadrado[i][y] = n;
                    } else if (posicionPerimetral.equals(ULTIMA_COLUMNA)) {
                        i = x;
                        j = y - 1;
                        mCuadrado[i][j] = n;
                    }
                } else {
                    x = i;
                    y = j;
                    mCuadrado[i][j] = n;
                }
        }

    }

    // Continúa el llenado del cuadrado en el sentido noroeste
    private void llenarEnSentidoNO(int fil, int col, int posInicial) {

        int contador = orden * orden;
        int n = posInicial;
        int i = fil;
        int j = col;

        int x = fil;
        int y = col;

        while(contador-->1) {
            n++;

            i = i - 1;
            j = j - 1;

            if (i < 0) {
                i = mCuadrado.length - 1;
            }
            if (j < 0) {
                j = mCuadrado[0].length - 1;
            }

            if (mCuadrado[i][j] != 0) {
                if (posicionPerimetral.equals(PRIMERA_FILA)) {
                    i = x + 1;
                    j = y;
                    mCuadrado[i][y] = n;
                } else if (posicionPerimetral.equals(PRIMERA_COLUMNA)) {
                    i = x;
                    j = y + 1;
                    mCuadrado[i][j] = n;
                }
            } else {
                x = i;
                y = j;
                mCuadrado[i][j] = n;
            }
        }
    }

    // Continúa el llenado del cuadrado en el sentido sureste
    private void llenarEnSentidoSE(int fil, int col, int posInicial) {

        int contador = orden * orden;
        int n = posInicial;
        int i = fil;
        int j = col;

        int x = fil;
        int y = col;

        while(contador-->1) {
            n++;

            i = i + 1;
            j = j + 1;

            if (i > mCuadrado.length - 1) {
                i = 0;
            }
            if (j > mCuadrado[0].length - 1) {
                j = 0;
            }

            if (mCuadrado[i][j] != 0) {
                if (posicionPerimetral.equals(ULTIMA_FILA)) {
                    i = x - 1;
                    j = y;
                    mCuadrado[i][j] = n;
                } else if (posicionPerimetral.equals(ULTIMA_COLUMNA)) {
                    i = x;
                    j = y - 1;
                    mCuadrado[i][j] = n;
                }
            } else {
                x = i;
                y = j;
                mCuadrado[i][j] = n;
            }
        }
    }

    // Continúa el llenado del cuadrado en el sentido suroeste
    private void llenarEnSentidoSO(int fil, int col, int posInicial) {

        int contador = orden * orden;
        int n = posInicial;
        int i = fil;
        int j = col;

        int x = fil;
        int y = col;

        while(contador-->1) {
            n++;

            i = i + 1;
            j = j - 1;

            if (i > mCuadrado.length - 1) {
                i = 0;
            }
            if (j < 0) {
                j = mCuadrado[0].length - 1;
            }
            if (mCuadrado[i][j] != 0) {
                if (posicionPerimetral.equals(ULTIMA_FILA)) {
                    i = x - 1;
                    j = y;
                    mCuadrado[i][j] = n;
                } else if (posicionPerimetral.equals(PRIMERA_COLUMNA)) {
                    i = x;
                    j = y + 1;
                    mCuadrado[i][j] = n;
                }
            } else {
                x = i;
                y = j;
                mCuadrado[i][j] = n;
            }
        }
    }

    // Calcula el valor de la constante mágica
    public void calcularConstanteMagica() {
        constanteMagica = (orden *((orden * orden)+1))/2;
    }

    // Devuelve el orden del cuadrado
    public int getOrden() {
        return orden;
    }

    // Cambia el orden del cuadrado.
    public void setOrden(int orden) {
        this.orden = orden;
    }

    // Devuelve el valor de la constante mágica.
    public String getPosicionPerimetral() {
        return posicionPerimetral;
    }

    // Cambia el valor de la constante mágica.
    public void setPosicionPerimetral(String posicionPerimetral) {
        this.posicionPerimetral = posicionPerimetral;
    }

    // Devuelve el sentido del llenado.
    public String getSentidoLlenado() {
        return sentidoLlenado;
    }

    // Cambia el sentido del llenado.
    public void setSentidoLlenado(String sentidoLlenado) {
        this.sentidoLlenado = sentidoLlenado;
    }

    // Devuelve la matriz de números del cuadrado.
    public int[][] getMCuadrado() {
        return mCuadrado;
    }

    // Cambia la matriz de números del cuadrado.
    public void setMCuadrado(int[][] mCuadrado) {
        this.mCuadrado = mCuadrado;
    }
    // Devuelve la constante mágica.
    public int getConstanteMagica() {
        return constanteMagica;
    }

    // Cambia el valor de la constante mágica.
    public void setConstanteMagica(int constanteMagica) {
        this.constanteMagica = constanteMagica;
    }

    // Verifica que el orden ingresado sea el correcto.
    public boolean validarOrden(String orden){
        try{
            Integer.parseInt(orden);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

}
