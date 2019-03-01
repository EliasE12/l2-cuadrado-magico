package userInterface;

import exceptions.NoNumeroException;
import exceptions.NoNumeroImparException;
import exceptions.NoNumeroPositivoException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import model.CuadradoMagico;

import java.net.URL;
import java.util.ResourceBundle;

// Clase

/**
 * Entidad que presenta la controladora de la interfaz del cuadrado mágico
 */
public class CuadradoMagicoController implements Initializable {

    // Atributos

    @FXML private TextField tfOrden;
    @FXML private ComboBox<String> cbPosicionPerimetral;
    @FXML private ComboBox<String> cbSentidoLLenado;
    @FXML private Button btGenerarCuadrado;
    @FXML private Button btLimpiar;
    @FXML private BorderPane bpBanner;
    @FXML private BorderPane bpPrincipal;
    @FXML private TextField tfConstanteMagica;

    private GridPane gpCuadrado;
    private TextField[][] textFields;

    // Relación con clase la clase principal del modelo

    private CuadradoMagico cuadradoMagico;

    // Método inicializador

    // Inicializa los componetes la relacion con la clase princiapl del modelo,
    // junto con los componentes de la interface de usuario.
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        cuadradoMagico = new CuadradoMagico();

        btGenerarCuadrado.setDisable(true);
        btLimpiar.setDisable(true);

        gpCuadrado = new GridPane();
        gpCuadrado.setAlignment(Pos.CENTER);

        // Crea la imagen del banner
        Image image = new Image("banner.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(263.0);
        imageView.setFitHeight(155.0);
        bpBanner.setCenter(imageView);

        // Agrega las posiciones perimetrales al Combobox
        cbPosicionPerimetral.getItems().addAll(CuadradoMagico.PRIMERA_FILA, CuadradoMagico.PRIMERA_COLUMNA,
                CuadradoMagico.ULTIMA_FILA, CuadradoMagico.ULTIMA_COLUMNA);

        // Gestiona lo que se hace el combobox de posiciones perimetrales
        cbPosicionPerimetral.setOnAction(e -> {

            if (cbPosicionPerimetral.getValue().equals(CuadradoMagico.PRIMERA_FILA)) {
                cbSentidoLLenado.getItems().clear();
                cbSentidoLLenado.getItems().addAll(CuadradoMagico.NO, CuadradoMagico.NE);
            } else if (cbPosicionPerimetral.getValue().equals(CuadradoMagico.PRIMERA_COLUMNA)) {
                cbSentidoLLenado.getItems().clear();
                cbSentidoLLenado.getItems().addAll(CuadradoMagico.NO, CuadradoMagico.SO);
            } else if (cbPosicionPerimetral.getValue().equals(CuadradoMagico.ULTIMA_FILA)) {
                cbSentidoLLenado.getItems().clear();
                cbSentidoLLenado.getItems().addAll(CuadradoMagico.SO, CuadradoMagico.SE);
            } else {
                cbSentidoLLenado.getItems().clear();
                cbSentidoLLenado.getItems().addAll(CuadradoMagico.NE, CuadradoMagico.SE);
            }

            btGenerarCuadrado.setDisable(false);
            btLimpiar.setDisable(false);
        });
    }

    // Métodos

    // Controla la acción del botón que genera el cuadrado mágico.
    @FXML
    public void controlBtGenerarCuadrado(ActionEvent event) {

        gpCuadrado.setGridLinesVisible(true);

        btGenerarCuadrado.setOnMouseClicked(e -> {

            int orden=0;
            String posicion = "";
            String sentido = "";

            try {

                if(tfOrden.getText().equals("")){
                    throw new NullPointerException();
                }if (cuadradoMagico.validarOrden(tfOrden.getText())){
                    orden = Integer.parseInt(tfOrden.getText());
                }else{
                    throw new NoNumeroException(tfOrden.getText());
                }

                posicion = cbPosicionPerimetral.getValue();
                sentido = cbSentidoLLenado.getValue();

                cuadradoMagico.inicializar(orden,posicion,sentido);
                cuadradoMagico.llenarCuadradoMagico();
                generarTablero();
                mostrarConstanteMagica();

            } catch (NullPointerException e1) {
                Alert men = new Alert(Alert.AlertType.WARNING);
                men.setTitle("Avertencia !!!");
                men.setHeaderText("Faltan valores.");
                men.setContentText("No ha ingresado y seleccionado todos los valores. Debe ingresar todos los valores.");
                men.showAndWait();
            }catch (NoNumeroImparException e1) {
                Alert men = new Alert(Alert.AlertType.WARNING);
                men.setTitle("Avertencia !!!");
                men.setHeaderText("Número incorrecto.");
                men.setContentText( e1.getMessage() + ". Debe ingresar un número impar positivo.");
                men.showAndWait();
            }catch (NoNumeroException e2){
                Alert men = new Alert(Alert.AlertType.WARNING);
                men.setTitle("Avertencia !!!");
                men.setHeaderText("No es un número entero.");
                men.setContentText("El valor ingresado "+e2.getMessage()+ " no es un número. Debe ingresar un número entero impar positivo.");
                men.showAndWait();
            }catch (NoNumeroPositivoException e3){
                Alert men = new Alert(Alert.AlertType.WARNING);
                men.setTitle("Avertencia !!!");
                men.setHeaderText("No es un número positivo.");
                men.setContentText(e3.getMessage()+ ". Debe ingresar un número impar positivo.");
                men.showAndWait();
            }
        });
        btGenerarCuadrado.setDisable(true);
    }

    // Genera el cuadrado impar de TextFields.
    public void generarTablero() {

        // Pone la cantidad de filas y columnas indicadas al GridPane
        GridPane.setRowIndex(gpCuadrado, cuadradoMagico.getOrden());
        GridPane.setColumnIndex(gpCuadrado, cuadradoMagico.getOrden());

        textFields = new TextField[cuadradoMagico.getOrden()][cuadradoMagico.getOrden()];

        // Crea los TextField y los adiciona al GridPane
        for (int i = 0; i < cuadradoMagico.getMCuadrado().length; i++) {
            final int x=i;
            for (int j = 0; j < cuadradoMagico.getMCuadrado()[0].length; j++) {
                final int y=j;

                textFields[i][j] = new TextField();
                textFields[i][j].setStyle("-fx-background-color:  #e5e5e5");
                textFields[i][j].setPrefSize(35.0, 30.0);
                textFields[i][j].setText(cuadradoMagico.getMCuadrado()[i][j] + "");

                gpCuadrado.setHgap(4);
                gpCuadrado.setVgap(4);

                gpCuadrado.add(textFields[i][j],j,i);
            }
        }
        GridPane.setMargin(gpCuadrado, new Insets(2,2,2,2));
        // Adiciona el GridPane al BorderPane secundario.
        bpPrincipal.setCenter(gpCuadrado);
    }


    // Controla la acción del botón que límpia la interfaz
    @FXML
    public void controlBtLimpiar(ActionEvent event){
        btLimpiar.setOnMouseClicked(e->{
            tfOrden.clear();
            tfConstanteMagica.clear();
            gpCuadrado.getChildren().clear();
            gpCuadrado.setGridLinesVisible(false);
            btGenerarCuadrado.setDisable(false);
        });
    }

    // Muestra la constante mágica del cuadrado.
    public void mostrarConstanteMagica() {
        tfConstanteMagica.setText(cuadradoMagico.getConstanteMagica() + "");
    }

    // Devuelve el cuadrado mágico.
    public CuadradoMagico getCuadradoMagico() {
        return cuadradoMagico;
    }

    // Devuelve el cuadrado mágico.
    public void setCuadradoMagico(CuadradoMagico cuadradoMagico) {
        this.cuadradoMagico = cuadradoMagico;
    }
}
