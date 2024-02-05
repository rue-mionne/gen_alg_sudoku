package progui;

import gen_alg.GeneCrossTemplate;
import gen_alg.GenerationCompleteEvent;
import gen_alg.Incubator;
import gen_alg.IncubatorBase;
import gen_alg_impl.Pole;
import gen_alg_impl.SudokuRozw;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.Flow;

public class ControllerMain {
        @FXML
        private Label LiczbaPokolen;
        @FXML
        private TextField MaxIloscPokolen;
        int MaxIlPok=Integer.MAX_VALUE;
        @FXML
        private TextField OpoznienieMS;
        int OpMS=100;
        @FXML
        private TextField Populacja;
        int Pop=20;
        @FXML
        private TextField PradopodobienstwoMutacji;
        Double PrawMut=0.1;
        @FXML
        private Button StartStop;
        @FXML
        private GridPane AS00;
        @FXML
        private GridPane AS01;
        @FXML
        private GridPane AS02;
        @FXML
        private GridPane AS10;
        @FXML
        private GridPane AS11;
        @FXML
        private GridPane AS12;
        @FXML
        private GridPane AS20;
        @FXML
        private GridPane AS21;
        @FXML
        private GridPane AS22;
        @FXML
        private GridPane QS00;
        @FXML
        private GridPane QS01;
        @FXML
        private GridPane QS02;
        @FXML
        private GridPane QS10;
        @FXML
        private GridPane QS11;
        @FXML
        private GridPane QS12;
        @FXML
        private GridPane QS20;
        @FXML
        private GridPane QS21;
        @FXML
        private GridPane QS22;
        ArrayList<TextField> QFields=new ArrayList<>();
        ArrayList<TextField> AFields=new ArrayList<>();
        ArrayList<GridPane> QPanes=new ArrayList<>();
        ArrayList<GridPane> APanes=new ArrayList<>();
        ArrayList<Integer> setFields=new ArrayList<>();

public void populate() {
    loadPanes();
    generateSpinners();
    setSetFieldsHandlers();
    setParamFieldsHandlers();
    setStartStopHandler();
}

        private void loadPanes(){
            QPanes.add(QS00);
            QPanes.add(QS01);
            QPanes.add(QS02);
            QPanes.add(QS10);
            QPanes.add(QS11);
            QPanes.add(QS12);
            QPanes.add(QS20);
            QPanes.add(QS21);
            QPanes.add(QS22);
            APanes.add(AS00);
            APanes.add(AS01);
            APanes.add(AS02);
            APanes.add(AS10);
            APanes.add(AS11);
            APanes.add(AS12);
            APanes.add(AS20);
            APanes.add(AS21);
            APanes.add(AS22);
        }

        private void generateSpinners(){
            for(int i=0;i<81;i++){
                TextField AField= new TextField("0");
                AField.setFont(Font.font(10.0));
                TextField QField=new TextField("0");
                QField.setFont(Font.font(10.0));
                AField.setDisable(true);
                QFields.add(QField);
                setFields.add(0);
                AFields.add(AField);
            }
            int[] tabWierzch ={0,3,6,27,30,33,54,57,60};
            for(int i=0;i<9;i++){
                GridPane workingQPane = QPanes.get(i);
                GridPane workingAPane = APanes.get(i);
                int indexSpinner = tabWierzch[i];
                workingQPane.add(QFields.get(indexSpinner),0,0);
                workingQPane.add(QFields.get(indexSpinner+1),1,0);
                workingQPane.add(QFields.get(indexSpinner+2),2,0);
                workingQPane.add(QFields.get(indexSpinner+9),0,1);
                workingQPane.add(QFields.get(indexSpinner+10),1,1);
                workingQPane.add(QFields.get(indexSpinner+11),2,1);
                workingQPane.add(QFields.get(indexSpinner+18),0,2);
                workingQPane.add(QFields.get(indexSpinner+19),1,2);
                workingQPane.add(QFields.get(indexSpinner+20),2,2);
                workingAPane.add(AFields.get(indexSpinner),0,0);
                workingAPane.add(AFields.get(indexSpinner+1),1,0);
                workingAPane.add(AFields.get(indexSpinner+2),2,0);
                workingAPane.add(AFields.get(indexSpinner+9),0,1);
                workingAPane.add(AFields.get(indexSpinner+10),1,1);
                workingAPane.add(AFields.get(indexSpinner+11),2,1);
                workingAPane.add(AFields.get(indexSpinner+18),0,2);
                workingAPane.add(AFields.get(indexSpinner+19),1,2);
                workingAPane.add(AFields.get(indexSpinner+20),2,2);
            }
        }

        void setSetFieldsHandlers(){
            for(int i=0;i<81;i++){
                final int index=i;
                QFields.get(i).setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        try {
                            Integer wartosc = Integer.parseInt(QFields.get(index).getText());
                            if(wartosc>-1&&wartosc<10){
                                setFields.set(index,wartosc);
                            }
                        }
                        catch(NumberFormatException e){
                            QFields.get(index).setText("0");
                        }

                    }
                });
            }
        }

        void setParamFieldsHandlers(){
            MaxIloscPokolen.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        Integer wartosc = Integer.parseInt(MaxIloscPokolen.getText());
                        if(wartosc>-1&&wartosc<100){
                            MaxIlPok=wartosc;
                        }
                    }
                    catch(NumberFormatException e){
                        MaxIloscPokolen.setText("0");
                    }
                }
            });
            OpoznienieMS.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        Integer wartosc = Integer.parseInt(OpoznienieMS.getText());
                        if(wartosc>-1&&wartosc<100000){
                            OpMS=wartosc;
                        }
                    }
                    catch(NumberFormatException e){
                        OpoznienieMS.setText("0");
                    }
                }
            });;
            Populacja.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        Integer wartosc = Integer.parseInt(Populacja.getText());
                        if(wartosc>-1&&wartosc<=50){
                            Pop=wartosc;
                        }
                    }
                    catch(NumberFormatException e){
                        Populacja.setText("0");
                    }
                }
            });;
            PradopodobienstwoMutacji.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        Double wartosc = Double.parseDouble(PradopodobienstwoMutacji.getText());
                        if(wartosc>-1&&wartosc<30){
                            PrawMut=wartosc;
                        }
                    }
                    catch(NumberFormatException e){
                        PradopodobienstwoMutacji.setText("0.0");
                    }
                }
            });;
        }
        Incubator inkubatorM;
    Object lock;
    Flow.Subscriber<GenerationCompleteEvent> peeker = new Flow.Subscriber<GenerationCompleteEvent>() {
        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            System.out.println("Zarejestrowano");
            subscription.request(Long.MAX_VALUE);
        }

        @Override
        public void onNext(GenerationCompleteEvent generationCompleteEvent) {
            synchronized (lock){
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        SudokuRozw rozw = (SudokuRozw) inkubatorM.getSpecimen().get(0);
                        for(int i=0;i<81;i++){
                            AFields.get(i).setText(""+((Pole)rozw.getGenome().get(i)).getVal());
                        }
                        LiczbaPokolen.setText("Pokolenie: "+inkubatorM.getGenerationCount()+", konflity: " +((SudokuRozw) inkubatorM.getSpecimen().get(0)).score);
                    }
                });

            }
        }

        @Override
        public void onError(Throwable throwable) {

        }

        @Override
        public void onComplete() {

        }
    };

        void setStartStopHandler(){
            StartStop.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                        Pole poleTempl = new Pole();
                        SudokuRozw specTempl = new SudokuRozw(poleTempl,setFields);
                        Incubator inkubator = new IncubatorBase(specTempl);
                        for(int index =0;index<81;index++){
                            try {
                                Integer wartosc = Integer.parseInt(QFields.get(index).getText());
                                if(wartosc>-1&&wartosc<10){
                                    setFields.set(index,wartosc);
                                }
                            }
                            catch(NumberFormatException e){
                            }
                            QFields.get(index).setDisable(true);
                        }
                        Populacja.setDisable(true);
                        MaxIloscPokolen.setDisable(true);
                        PradopodobienstwoMutacji.setDisable(true);
                        OpoznienieMS.setDisable(true);
                        inkubator.setPopulation(Pop);
                        inkubator.setMaxGenerationCount(MaxIlPok);
                        inkubator.setMutationProbability(PrawMut);
                        inkubator.setDelayInMs(OpMS);
                        Thread thread = new Thread(inkubator);
                        inkubatorM=inkubator;
                        lock = inkubator.getLock();
                        inkubator.addGenerationPeeker(peeker);
                    try {
                        inkubator.setGeneticCrossReference(createCustomTemplate(Pop));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    thread.start();
                        for(TextField pole:AFields){
                            pole.setDisable(false);
                            pole.setEditable(false);
                        }
                }
            });

        }

        GeneCrossTemplate createCustomTemplate(int population) throws Exception {
            LinkedList<Double> defaultPerc = new LinkedList<>();
            defaultPerc.add(1.0);
            LinkedList<LinkedList<Integer>> defaultCrossModel = new LinkedList<>();
            LinkedList<Integer> defaultGeneCross = new LinkedList<>();
            for(int i = 0;i<81;i++){
                if(i%2==0)
                    defaultGeneCross.add(1);
                else
                    defaultGeneCross.add(2);
            }
            defaultCrossModel.add(defaultGeneCross);
            Double array[] =new Double[population];
            Arrays.fill(array,1.0);
            ArrayList<Double> defaultWeights = new ArrayList<>(Arrays.asList(array));
            int half = defaultWeights.size()/2;
            for(int i=half;i<defaultWeights.size();i++){
                defaultWeights.set(i,0.0);
            }
            return new GeneCrossTemplate(defaultPerc,defaultCrossModel,defaultWeights,false);

        }
}
