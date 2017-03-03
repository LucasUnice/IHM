package enseigne;

import enseigne.modele.ReadConst;
import enseigne.modele.magasin.Magasin;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

import java.io.IOException;

public class testIHM extends Application {

    @Override public void start(Stage stage) throws IOException {
        Scene scene = new Scene(new Group());
        stage.setTitle("Imported Fruits");
        stage.setWidth(500);
        stage.setHeight(500);

        Magasin m1 = new Magasin(ReadConst.storePath+"salut.fr.json");
        Magasin m2 = new Magasin(ReadConst.storePath+"salut.fr.json");
        Magasin m3 = new Magasin(ReadConst.storePath+"salut.fr.json");

        m1.setChiffreAffaire(20000);
        m2.setChiffreAffaire(35500);
        m3.setChiffreAffaire(50000);


        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data(m1.getCentre(),m1.getChiffreAffaire()),
                        new PieChart.Data(m2.getCentre(),m2.getChiffreAffaire()),
                        new PieChart.Data(m3.getCentre(),m3.getChiffreAffaire()));



        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Chiffres d'affaires");
        ((Group) scene.getRoot()).getChildren().add(chart);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}