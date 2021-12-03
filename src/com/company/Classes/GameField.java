package com.company.Classes;

import com.company.Main;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.lang.String;
import java.net.URL;

public class GameField{
    @FXML
    private VBox vbox;
    @FXML
    private MenuItem buttonBastyBet;
    @FXML
    private MenuItem buttonJanadanbastau;
    @FXML
    private MenuItem buttonEreje;
    @FXML
    private ImageView imgX1;
    @FXML
    private ImageView imgX5;
    @FXML
    private ImageView imgX4;
    @FXML
    private ImageView imgX3;
    @FXML
    private ImageView imgX2;
    @FXML
    private ImageView imgY5;
    @FXML
    private ImageView imgY2;
    @FXML
    private ImageView imgY3;
    @FXML
    private ImageView imgY4;
    @FXML
    private ImageView imgY1;
    @FXML
    private TextArea tablepointX;
    @FXML
    private TextArea tablepointY;
    @FXML
    private ImageView xStrelka;
    @FXML
    private Text txtFirstPName;
    @FXML
    private Text txtSecondPName;
    @FXML
    private Button resButton;


    private Image []img = new Image[22]; // создание Image объекта ( массив ретінде )
    private Image strelka = new Image("com\\company\\img\\strelka.png"); // создание Image объекта, стрелка
    private int pointX = 0; // бірінші ойыншының ұпай саны
    private int pointY = 0; // екінші ойыншының ұпай саны
    private int []hXY = new int[10]; // ұяшықтар
    private int xod = 2; // осы арқылы ход ты тексеріп отырамыз
    private static String WinTxt; // жеңген адамның есемі енгізілетін айнымалы

    public static String getWinTxt() {return WinTxt;} // жеңген адамның есімін алу
    public static void setWinTxt(String winTxt) {WinTxt = winTxt;} // жеңген адамның есімін енгізу

    public void sethXY(){// барлық ұяшыққа 5 тастан беру функциясы
        for (int i = 0; i < 10; i++)
            hXY[i] = 5;
    }
    private static String o1, t1;
    public void setNamePlayers(String o, String t){ // Инкапсулация ойыншыларға басты бетте енгізілген есімде беру
        this.o1 = o;
        this.t1 = t;
    }
    public void LoadImg(){ // Суреттерді жүктеу
        String papka;
        for(int i = 0; i <= 21; i++ ){
            papka = "com\\company\\img\\img"+ i +".png";
            img[i] = new Image(papka);
        }
    }

    public void checkImg(){ // Суреттерді тексеру
        imgX1.setImage(img[hXY[0]]); // каждый ұяшықта қанша тас болса соған сай фото жүктейміз
        imgX2.setImage(img[hXY[1]]);
        imgX3.setImage(img[hXY[2]]);
        imgX4.setImage(img[hXY[3]]);
        imgX5.setImage(img[hXY[4]]);
        imgY1.setImage(img[hXY[5]]);
        imgY2.setImage(img[hXY[6]]);
        imgY3.setImage(img[hXY[7]]);
        imgY4.setImage(img[hXY[8]]);
        imgY5.setImage(img[hXY[9]]);
        xStrelka.setImage(strelka);

    }
    public void ImgRotate(){
        RotateTransition rotate = new RotateTransition(); // поворот стрелки на 180 г
        rotate.setNode(xStrelka);
        rotate.setDuration(Duration.millis(200)); // айналу жылдамдығы
        rotate.setByAngle(180); // 180 градусқа бұру
        rotate.setAxis(Rotate.X_AXIS); // зеркальное отражение
        rotate.play(); // запуск
    }

    public void spXY(int i){ // жүріс функциясы
        if(hXY[i] == 0) // егер белгілі ұяшықтағы тастар саны 0 ге тең болса жүріс болмайды
            return;
        if (hXY[i] == 1){ // егер белгілі ұяшықтағы тастар саны 1 ге тең болса
            hXY[i] = 0;   // осы ұяшықтағы тастар саны 0 ге тең болады
            if(i == 9) { // егер бұл 9 шы ұяшық болса
                hXY[0]++; // 0 ші ұяшық толтырылады
                checkXodPoint(0); // ұяшықты тексереді және ұпай береді
            }
            else {  // егер бұл 9 шы ұяшық болмаса
                hXY[i + 1]++; // келесі ұяшық толтырылады
                checkXodPoint(i + 1); // келесі ұяшықты тексереді және ұпай береді
            }
            xod++; // келесі ход
            checkImg(); // Фотоларды тексерді
            ImgRotate();
            inGame(); //  Ойынның біткенін немесе бітпегенін тексереді
            return;
        }
        int k = i, j = hXY[i];
        hXY[i] = 0;
        while (j > 0 ){ // тас саны 0 ден үлкен болу керек
            if(k == 10){ // егер ұяшықтың номері 10 ға тең болса
                k = 0; // ұяшықтың номерін 0 ге өзгертеді
            }
            hXY[k]++; // каждый ұяшыққа 1 тастан қосып отырады
            j--; // тас санын 1 ге азайтып отырады
            k++;  // келесі ұяшыққа өту

        }
        checkXodPoint(k - 1); // ұяшықты тексереді және ұпай береді
        xod++; //  келесі ход
        checkImg(); // Фотоларды тексерді
        ImgRotate();
        inGame(); //  Ойынның біткенін немесе бітпегенін тексереді
    }
    public void checkXodPoint(int k){ // ойыншылардың ұпай беру
        if(hXY[k] % 2 == 0){ //  егер біз жүріп барған ұяшықтағы тастар саны жұп болса
            if(xod % 2 == 0 && k != 0 && k != 1 && k != 2 && k != 3 && k != 4){
                pointX += hXY[k]; // ойыншыға ұпай беру
                hXY[k] = 0; // сол ұяшықтың тастарын алып тастау
                tablepointX.setText(String.valueOf(pointX)); // экранға шығару
            }
            else if(xod % 2 != 0 && k != 5 && k != 6 && k != 7 && k != 8 && k != 9){
                pointY += hXY[k];// ойыншыға ұпай беру
                hXY[k] = 0; // сол ұяшықтың тастарын алып тастау
                tablepointY.setText(String.valueOf(pointY)); // экранға шығару
            }
        }
    }
    public void inGame(){ // Проверка
        if((hXY[0] == 0 && hXY[1] == 0 && hXY[2] == 0 && hXY[3] == 0 && hXY[4] == 0)){ // егер ойыншаның бүкіл ұяшығы бос болса
            pointY+= hXY[5] + hXY[6] + hXY[7] + hXY[8] + hXY[9];
            if(pointX > pointY) { // егер бірінші ойыншының ұпайы үлкен болса
                setWinTxt(txtSecondPName.getText() + "\nJeñdı"); // экранға жеңген ойыншының есімін шығару
            }
            else if (pointX < pointY) { // егер екінші ойыншының ұпайы үлкен болса
                setWinTxt(txtFirstPName.getText() + "\nJeñdı"); // экранға жеңген ойыншының есімін шығару
            }
            else // егер тең болса
                setWinTxt("Teñ näjije");
            loadWinPane();
        }
        if((hXY[5] == 0 && hXY[6] == 0 && hXY[7] == 0 && hXY[8] == 0 && hXY[9] == 0)){ // егер ойыншаның бүкіл ұяшығы бос болса
            pointX+= hXY[0] + hXY[1] + hXY[2] + hXY[3] + hXY[4];
            if(pointX > pointY) { // егер бірінші ойыншының ұпайы үлкен болса
                setWinTxt(txtSecondPName.getText() + "\nJeñdı"); // экранға жеңген ойыншының есімін шығару
            }
            else if (pointX < pointY) {// егер екінші ойыншының ұпайы үлкен болса
                setWinTxt(txtFirstPName.getText() + "\nJeñdı"); // экранға жеңген ойыншының есімін шығару
            }
            else // егер тең болса
                setWinTxt("Teñ näjije");
            loadWinPane();
        }
    }

    public void loadWinPane(){ // егер оы функцияны шақырсақ, Main класы ашылады, сол арқылы басты бетке өтеміз
        resButton.getScene().getWindow().hide(); // осы окноны жабу
        Main app=new Main(); // объект класса Main
        try {
            app.start(new Stage()); // Запуск
        } catch (Exception e) { // Исключение
            e.printStackTrace();
        }
    }
    @FXML
    void initialize() {  // Инициализация класса
        txtFirstPName.setText(o1); // Player 1
        txtSecondPName.setText(t1);  // Player 2
        xStrelka.setRotate(0);
        sethXY();
        LoadImg();
        checkImg();
        imgX1.setOnMouseClicked(mouseEvent -> {
            if(xod % 2 != 0) // егер бұл екінші ойыншы болса, өйткені екінші ойыншының реттік номері тақ сан, бұл бірінші ойыншының ұяшығы
                return;
            int i = 0; // ұяшықтың реттік номерін беру
            spXY(i); // жүріс функциясын шақыру
        });
        imgX2.setOnMouseClicked(mouseEvent -> {
            if(xod % 2 != 0)// егер бұл екінші ойыншы болса, өйткені екінші ойыншының реттік номері тақ сан, бұл бірінші ойыншының ұяшығы
                return;
            int i = 1; // ұяшықтың реттік номерін беру
            spXY(i); // жүріс функциясын шақыру
        });
        imgX3.setOnMouseClicked(mouseEvent -> {
            if(xod % 2 != 0)// егер бұл екінші ойыншы болса, өйткені екінші ойыншының реттік номері тақ сан, бұл бірінші ойыншының ұяшығы
                return;
            int i = 2; // ұяшықтың реттік номерін беру
            spXY(i); // жүріс функциясын шақыру
        });
        imgX4.setOnMouseClicked(mouseEvent -> {
            if(xod % 2 != 0)// егер бұл екінші ойыншы болса, өйткені екінші ойыншының реттік номері тақ сан, бұл бірінші ойыншының ұяшығы
                return;
            int i = 3; // ұяшықтың реттік номерін беру
            spXY(i); // жүріс функциясын шақыру
        });
        imgX5.setOnMouseClicked(mouseEvent -> {
            if(xod % 2 != 0)// егер бұл екінші ойыншы болса, өйткені екінші ойыншының реттік номері тақ сан, бұл бірінші ойыншының ұяшығы
                return;
            int i = 4; // ұяшықтың реттік номерін беру
            spXY(i); // жүріс функциясын шақыру
        });
        imgY1.setOnMouseClicked(mouseEvent -> {
            if(xod % 2 == 0) // егер бұл бірінші ойыншы болса, өйткені бірінші ойыншының реттік номері жұп сан, бұл екінші ойыншының ұяшығы
                return;
            int i = 5; // ұяшықтың реттік номерін беру
            spXY(i); // жүріс функциясын шақыру
        });
        imgY2.setOnMouseClicked(mouseEvent -> {
            if(xod % 2 == 0)// егер бұл бірінші ойыншы болса, өйткені бірінші ойыншының реттік номері жұп сан, бұл екінші ойыншының ұяшығы
                return;
            int i = 6; // ұяшықтың реттік номерін беру
            spXY(i); // жүріс функциясын шақыру
        });
        imgY3.setOnMouseClicked(mouseEvent -> {
            if(xod % 2 == 0)// егер бұл бірінші ойыншы болса, өйткені бірінші ойыншының реттік номері жұп сан, бұл екінші ойыншының ұяшығы
                return;
            int i = 7; // ұяшықтың реттік номерін беру
            spXY(i); // жүріс функциясын шақыру
        });
        imgY4.setOnMouseClicked(mouseEvent -> {
            if(xod % 2 == 0)// егер бұл бірінші ойыншы болса, өйткені бірінші ойыншының реттік номері жұп сан, бұл екінші ойыншының ұяшығы
                return;
            int i = 8; // ұяшықтың реттік номерін беру
            spXY(i); // жүріс функциясын шақыру
        });
        imgY5.setOnMouseClicked(mouseEvent -> {
            if(xod % 2 == 0)// егер бұл бірінші ойыншы болса, өйткені бірінші ойыншының реттік номері жұп сан, бұл екінші ойыншының ұяшығы
                return;
            int i = 9; // ұяшықтың реттік номерін беру
            spXY(i); // жүріс функциясын шақыру
        });
        buttonEreje.setOnAction(actionEvent -> {
            openWebpage("https://www.youtube.com/watch?v=RrDoHClMMzs"); // ссылка ашу функциясын шақырамыз
        });
        buttonBastyBet.setOnAction(actionEvent -> { // басты бетті ашу
            setWinTxt("");
            loadWinPane(); // басты бетті ашу функциясын шақырамыз
        });
        buttonJanadanbastau.setOnAction(actionEvent -> { // жаңадан бастау батырмасы
            xod = 2; // жаңадан ход
            pointX = 0; // очко тең болады 0
            pointY = 0; //
            tablepointX.setText(String.valueOf(pointX)); // ойыншылардың есімдерін вставить ет
            tablepointY.setText(String.valueOf(pointY));
            sethXY(); // ұяшықтарға тастарды беру [5]
            checkImg();
            xStrelka.setRotate(0);

        });
    }
    public static void openWebpage(String urlString) { // ссылка ашу
        try {
            Desktop.getDesktop().browse(new URL(urlString).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
