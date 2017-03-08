package centre.admin.news;

import centre.constant.AlertMessage;
import centre.model.News;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for the selection or deselection of highlighted news
 */
public class NewsSelectorController {

    @FXML
    private VBox list;

    private List<News> newsList;
    private int sourceId;
    private AdminNewsController controller;


    public void initializeContents(AdminNewsController controller, List<News> newsList, int sourceId, List<News> current) throws IOException {
        this.newsList = newsList;
        this.sourceId = sourceId;
        this.controller = controller;
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
        int i = 1;
        for (News n : newsList) {
            Label label = new Label(n.getName() + " - " + dateFormat.format(new Date(n.getDate() * 1000)));
            label.setPrefWidth(600);
            label.setAlignment(Pos.CENTER);
            label.setCursor(Cursor.HAND);
            if (i % 2 == 0) {
                label.setStyle("-fx-font: 22 System;");
            } else {
                label.setStyle("-fx-font: 22 System; -fx-background-color: #dcdcdc;");
            }
            ++i;
            label.setOnMouseClicked((event) -> {
                for (News news : current) {
                    news.setPosition(0);
                }
                n.setPosition(sourceId);
                n.save();
                controller.placeNews();
                Stage stage = (Stage) list.getScene().getWindow();
                stage.close();
            });
            list.getChildren().add(label);
        }
    }

    @FXML
    public void removeCurrentPromotion(Event event) {
        List<News> sourceNews = newsList.stream().filter(news -> news.getPosition() == sourceId).collect(Collectors.toList());
        ;
        if (sourceNews.isEmpty()) {
            AlertMessage.alert(Alert.AlertType.INFORMATION, "Attention", "Il n'y a pas de promotion à supprimer içi");
        } else {
            for (News n : sourceNews) {
                n.setPosition(0);
                n.save();
            }
            controller.placeNews();
            Stage stage = (Stage) list.getScene().getWindow();
            stage.close();
        }
    }
}
