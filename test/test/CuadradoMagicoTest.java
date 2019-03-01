package test;

import exceptions.NoNumeroImparException;
import exceptions.NoNumeroPositivoException;
import model.CuadradoMagico;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Clase

class CuadradoMagicoTest {

    // Atributo

    private CuadradoMagico cuadradoMagico;

    // EScenarios

    public void setupEscenario1(){}

    public void setupEscenario2(){
        cuadradoMagico = new CuadradoMagico();
    }

    // Métodos

    @Test
    void testCuadradoMagico1(){
        setupEscenario1();
        assertNull(cuadradoMagico, "La instancia de la clase no es nula");
    }

    @Test
    void testCuadradoMagico2(){
        setupEscenario2();

        try {
            cuadradoMagico.inicializar(5,"posicion", "sentido");
        }catch (NoNumeroImparException | NoNumeroPositivoException e){
            e.getMessage();
        }

        int orden = 5;
        String pP = "posicion";
        String sLl = "sentido";

        assertTrue(orden==cuadradoMagico.getOrden(), "El orden no es el esperado");
        assertTrue(pP.equals(cuadradoMagico.getPosicionPerimetral()), "La posicion perimetral no es la esperada");
        assertTrue(sLl.equals(cuadradoMagico.getSentidoLlenado()), "El sentido del llenado no es el esperado");
    }

    @Test
    void testCuadradoMagico3() {
        setupEscenario2();

        try {
            cuadradoMagico.inicializar(10, "posicion", "sentido");
            fail("No se ha lanzado la excepcion");
        }catch (NoNumeroImparException e){
            e.getMessage();
        }catch (NoNumeroPositivoException e){
            e.getMessage();
        }

        try {
            cuadradoMagico.inicializar(-21, "posicion", "sentido");
            fail("No se ha lanzado la excepcion");
        }catch (NoNumeroPositivoException e){
            e.getMessage();
        }catch (NoNumeroImparException e){
            e.getMessage();
        }

    }



    @Test
    void testLlenarCuadradoMagico1() {
        setupEscenario2();

        try {
            cuadradoMagico.inicializar(3,"Primera Fila","NorOeste");
        }catch (NoNumeroImparException | NoNumeroPositivoException e){
            e.getMessage();
        }


        int [][] tM = {{6,1,8},{7,5,3},{2,9,4}};

        for (int i=0; i<tM.length; i++){
            final int x=i;
            for (int j=0; j<tM[0].length; j++){
                final int y=j;
                if(tM[i][j] != tM[x][y]) {

                    fail("La matriz no es la espereda");
                }
            }
        }
    }

    @Test
    void testLlenarCuadradoMagico2() {
        setupEscenario2();

        try {
            cuadradoMagico.inicializar(3,"Primer Fila","NorEste");
        }catch (NoNumeroImparException | NoNumeroPositivoException e){
            e.getMessage();
        }

        int [][] tM = {{8,1,6},{3,5,7},{4,9,2}};

        for (int i=0; i<tM.length; i++){
            final int x=i;
            for (int j=0; j<tM[0].length; j++){
                final int y=j;
                if(tM[i][j] != tM[x][y]) {

                    fail("La matriz no es la espereda");
                }
            }
        }
    }

    @Test
    void testLlenarCuadradoMagico3() {
        setupEscenario2();

        try {
            cuadradoMagico.inicializar(3,"Primera Columna","NorOeste");
        }catch (NoNumeroImparException | NoNumeroPositivoException e){
            e.getMessage();
        }

        int [][] tM = {{6,7,2},{1,5,9},{8,3,4}};

        for (int i=0; i<tM.length; i++){
            final int x=i;
            for (int j=0; j<tM[0].length; j++){
                final int y=j;
                if(tM[i][j] != tM[x][y]) {

                    fail("La matriz no es la espereda");
                }
            }
        }
    }

    @Test
    void testLlenarCuadradoMagico4() {
        setupEscenario2();

        try {
            cuadradoMagico.inicializar(3,"Primera Columna","SurOeste");
        }catch (NoNumeroImparException | NoNumeroPositivoException e){
            e.getMessage();
        }

        int [][] tM = {{8,3,4},{1,5,9},{6,7,2}};

        for (int i=0; i<tM.length; i++){
            final int x=i;
            for (int j=0; j<tM[0].length; j++){
                final int y=j;
                if(tM[i][j] != tM[x][y]) {

                    fail("La matriz no es la espereda");
                }
            }
        }
    }

    @Test
    void testLlenarCuadradoMagico5() {
        setupEscenario2();

        try {
            cuadradoMagico.inicializar(3,"Ultima Fila","SurOeste");
        }catch (NoNumeroImparException | NoNumeroPositivoException e){
            e.getMessage();
        }

        int [][] tM = {{2,9,4},{7,5,3},{6,1,8}};

        for (int i=0; i<tM.length; i++){
            final int x=i;
            for (int j=0; j<tM[0].length; j++){
                final int y=j;
                if(tM[i][j] != tM[x][y]) {

                    fail("La matriz no es la espereda");
                }
            }
        }
    }

    @Test
    void testLlenarCuadradoMagico6() {
        setupEscenario2();

        try {
            cuadradoMagico.inicializar(3,"Ultima Fila","SurEste");
        }catch (NoNumeroImparException | NoNumeroPositivoException e){
            e.getMessage();
        }

        int [][] tM = {{4,9,2},{3,5,7},{8,1,6}};

        for (int i=0; i<tM.length; i++){
            final int x=i;
            for (int j=0; j<tM[0].length; j++){
                final int y=j;
                if(tM[i][j] != tM[x][y]) {

                    fail("La matriz no es la espereda");
                }
            }
        }
    }

    @Test
    void testLlenarCuadradoMagico7() {
        setupEscenario2();

        try{
            cuadradoMagico.inicializar(3,"Ultima Columna","NorEste");
        }catch (NoNumeroImparException | NoNumeroPositivoException e){
            e.getMessage();
        }

        int [][] tM = {{2,7,6},{9,5,1},{4,3,8}};

        for (int i=0; i<tM.length; i++){
            final int x=i;
            for (int j=0; j<tM[0].length; j++){
                final int y=j;
                if(tM[i][j] != tM[x][y]) {

                    fail("La matriz no es la espereda");
                }
            }
        }
    }

    @Test
    void testLlenarCuadradoMagico8() {
        setupEscenario2();

        try {
            cuadradoMagico.inicializar(3,"Ultima Columna","SurEste");
        }catch (NoNumeroImparException | NoNumeroPositivoException e){
            e.getMessage();
        }

        int [][] tM = {{4,3,8},{9,5,1},{2,7,6}};

        for (int i=0; i<tM.length; i++){
            final int x=i;
            for (int j=0; j<tM[0].length; j++){
                final int y=j;
                if(tM[i][j] != tM[x][y]) {

                    fail("La matriz no es la espereda");
                }
            }
        }
    }

    @Test
    void testCalcularConstanteMagica(){

        setupEscenario2();

        try {
            cuadradoMagico.inicializar(11,"posicion1","sentido1");
        }catch (NoNumeroImparException | NoNumeroPositivoException e){
            e.getMessage();
        }

        int constante1 = 671;
        cuadradoMagico.calcularConstanteMagica();
        assertTrue(constante1==cuadradoMagico.getConstanteMagica(), "La constante mágica es distinta a la esperada");




        try {
            cuadradoMagico.inicializar(7,"posicion2","sentido2");
        }catch (NoNumeroImparException | NoNumeroPositivoException e){
            e.getMessage();
        }

        int constante2 = 175;
        cuadradoMagico.calcularConstanteMagica();
        assertTrue(constante2==cuadradoMagico.getConstanteMagica(), "La constante mágica no es la esperada");
    }




}