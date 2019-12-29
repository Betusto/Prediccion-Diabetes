/******************************************
 *      FIME UANL SEMESTRE AGOSTO-DICIEMBRE 2019
 *      MATERIA: REDES NEURONALES ARTIFICIALES
 *      Docente: Dr. José Arturo Berrones Santos, Grupo: 002
 *                  Proyecto Final
 *      Predicción de Diabetes en una Mujer
 *      Alberto Villarreal Canales          1746057
******************************************/
package DiabetesGUI;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class DiabetesGUI extends javax.swing.JFrame {
    //dimensiones de la interfaz
    private final int WINDOW_WIDTH = 676;
    private final int WINDOW_HEIGHT = 429;
    //Iniciar las variables de los campos
    int embarazos = 0, glucosa = 0, presion = 0, pliegue = 0, insulina = 0, edad = 0;
    float imc = 0, fpd = 0;
    //variable para detectar si hay algún error en los campos
    boolean hayError = false;
    
    public DiabetesGUI() throws IOException {
        initComponents();
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Predicción de Diabetes");
        setResizable(false); //Remueve el boton de maximizar pantalla
        setLocationRelativeTo(null); //El frame se localiza en el centro
        //Lo que ocurre si cierra la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Mostramos la precision y el error del modelo
        File file = new File("precision y error del modelo.txt"); 
        BufferedReader br = new BufferedReader(new FileReader(file));
        precisionLabel.setText("Precisión del modelo: "+br.readLine());
        int counter = 0;
        String line;  
        while ((line = br.readLine()) != null) {  
           // process the line.  
           counter++;
           if(counter==1){
            errorLabel.setText("Error del modelo: "+line);
           }
        }    
        br.close();
        //Eliminar la posibilidad de pegar en los campos
        embarazosTextField.setTransferHandler(null);
        glucosaTextField.setTransferHandler(null);
        presionTextField.setTransferHandler(null);
        pliegueCutaneoTextField.setTransferHandler(null);
        insulinaTextField.setTransferHandler(null);
        imcTextField.setTransferHandler(null);
        fpdTextField.setTransferHandler(null);
        edadTextField.setTransferHandler(null);
        //Tooltips
        embarazosTextField.setToolTipText("<html>Número de veces embarazada.<br>En caso de no haber estado embarazada"
                + "escriba 0.<br>Rango recomendable a escibir: 0-20.</html>");
        glucosaTextField.setToolTipText("<html>Concentración de glucosa en plasma a 2 horas en una prueba de tolerancia "
                + "a la glucosa oral.<br>Rango recomendable a escibir: 0-200.<br>Se mide en miligramo por decilitro.</html>");
        presionTextField.setToolTipText("<html>Presión arterial diastólica. "
                + "<br>Rango recomendable a escibir: 0-110.<br>Se mide en milímetro de mercurio.</html>");
        pliegueCutaneoTextField.setToolTipText("<html>Grosor del pliegue de la piel del tríceps. "
                + "<br>Rango recomendable: 0-60.<br>Se mide en milímetro.</html>");
        insulinaTextField.setToolTipText("<html>Insulina sérica de 2 horas. "
                + "<br>Rango recomendable a escibir: 0-700.</html>");
        imcTextField.setToolTipText("<html>Índice de masa corporal. "
                + "<br>Rango recomendable: 0.0-60.0.<br>Se mide en peso en kg entre (altura en m) elevado al cuadrado.</html>");
        fpdTextField.setToolTipText("<html>Función de Pedigrí Diabetes (antecedentes genéticos). "
                + "<br>Rango recomendable: 0.0-2.0.</html>");
        edadTextField.setToolTipText("<html>Edad de la paciente (mujer adulta)."
                + "<br>Rango recomendable a escibir: 20-99.</html>");

        
        //Evitar que el usuario escriba ciertos caracteres
        edadTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) { 
                /*if(e.getKeyChar() == '.' && edadTextField.getText().contains(".")){
                    e.consume();
                }*/
                if(edadTextField.getText().equals("0")){
                    e.consume();
                }
                if(!Character.isDigit(e.getKeyChar()/* && e.getKeyChar() != '.'*/)){ //Solo se permiten numeros
                    e.consume(); 
                }
                if (edadTextField.getText().length() >= 2 ){ // solo se puede escribir hasta dos caracteres
                    e.consume(); 
                }
            }  
        });
        
        //Evitar que el usuario escriba ciertos caracteres
        embarazosTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) { 
                /*if(e.getKeyChar() == '.' && edadTextField.getText().contains(".")){
                    e.consume();
                }*/
                if(embarazosTextField.getText().equals("0")){
                    e.consume();
                }
                if(!Character.isDigit(e.getKeyChar()/* && e.getKeyChar() != '.'*/)){ //Solo se permiten numeros
                    e.consume(); 
                }
                if (embarazosTextField.getText().length() >= 2){ // solo se puede escribir hasta dos caracteres
                    e.consume(); 
                }
                /*if(e.getKeyChar() == '1' && embarazosTextField.getText().length() < 1 || e.getKeyChar() == '0' && embarazosTextField.getText().length() < 1){ //No se puede comenzar con 1 o 0
                     e.consume();
                }*/
            }  
        });
        
        //Evitar que el usuario escriba ciertos caracteres
        glucosaTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) { 
                /*if(e.getKeyChar() == '.' && edadTextField.getText().contains(".")){
                    e.consume();
                }*/
                if(glucosaTextField.getText().equals("0")){
                    e.consume();
                }
                if(!Character.isDigit(e.getKeyChar()/* && e.getKeyChar() != '.'*/)){ //Solo se permiten numeros
                    e.consume(); 
                }
                if (glucosaTextField.getText().length() >= 3 ){ // solo se puede escribir hasta dos caracteres
                    e.consume(); 
                }
                /*if(e.getKeyChar() == '1' && glucosaTextField.getText().length() < 1 || e.getKeyChar() == '0' && glucosaTextField.getText().length() < 1){ //No se puede comenzar con 1 o 0
                     e.consume();
                }*/
            }  
        });
        
        //Evitar que el usuario escriba ciertos caracteres
        presionTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) { 
                /*if(e.getKeyChar() == '.' && edadTextField.getText().contains(".")){
                    e.consume();
                }*/
                if(presionTextField.getText().equals("0")){
                    e.consume();
                }
                if(!Character.isDigit(e.getKeyChar()/* && e.getKeyChar() != '.'*/)){ //Solo se permiten numeros
                    e.consume(); 
                }
                if (presionTextField.getText().length() >= 3 ){ // solo se puede escribir hasta tres caracteres
                    e.consume(); 
                }
                /*if(e.getKeyChar() == '1' && presionTextField.getText().length() < 1 || e.getKeyChar() == '0' && presionTextField.getText().length() < 1){ //No se puede comenzar con 1 o 0
                     e.consume();
                }*/
            }  
        });
        
        //Evitar que el usuario escriba ciertos caracteres
        pliegueCutaneoTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) { 
                /*if(e.getKeyChar() == '.' && edadTextField.getText().contains(".")){
                    e.consume();
                }*/
                if(pliegueCutaneoTextField.getText().equals("0")){
                    e.consume();
                }
                if(!Character.isDigit(e.getKeyChar()/* && e.getKeyChar() != '.'*/)){ //Solo se permiten numeros
                    e.consume(); 
                }
                if (pliegueCutaneoTextField.getText().length() >= 2 ){ // solo se puede escribir hasta dos caracteres
                    e.consume(); 
                }
                /*if(e.getKeyChar() == '1' && pliegueCutaneoTextField.getText().length() < 1 || e.getKeyChar() == '0' && pliegueCutaneoTextField.getText().length() < 1){ //No se puede comenzar con 1 o 0
                     e.consume();
                }*/
            }  
        });
        
       //Evitar que el usuario escriba ciertos caracteres
        insulinaTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) { 
                /*if(e.getKeyChar() == '.' && insulinaTextField.getText().contains(".")){
                    e.consume();
                }*/
                if(insulinaTextField.getText().equals("0")){
                    e.consume();
                }
                if(!Character.isDigit(e.getKeyChar()/* && e.getKeyChar() != '.'*/)){ //Solo se permiten numeros
                    e.consume(); 
                }
                if (insulinaTextField.getText().length() >= 3 ){ // solo se puede escribir hasta tres caracteres
                    e.consume(); 
                }
                /*if(e.getKeyChar() == '1' && insulinaTextField.getText().length() < 1 || e.getKeyChar() == '0' && insulinaTextField.getText().length() < 1){ //No se puede comenzar con 1 o 0
                     e.consume();
                }*/
            }  
        });
        
        //Evitar que el usuario escriba ciertos caracteres
        imcTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) { 
                if(e.getKeyChar() == '.' && imcTextField.getText().contains(".")){
                    e.consume();
                }
                if(!Character.isDigit(e.getKeyChar()) && e.getKeyChar() != '.'){ //Solo se permiten numeros
                    e.consume(); 
                }
                if(imcTextField.getText().equals("0") && e.getKeyChar() != '.'){
                    e.consume();
                }
                if(e.getKeyChar() == '.' && imcTextField.getText().length() == 0){
                    e.consume();
                }
                if(!imcTextField.getText().contains(".")){
                    if (imcTextField.getText().length() >= 2 && e.getKeyChar() != '.'){ // solo se puede escribir hasta tres caracteres
                    e.consume(); 
                    }
                }else{
                    if (imcTextField.getText().length() >= 4 ){ // solo se puede escribir hasta tres caracteres
                    e.consume(); 
                    }
                }
            }  
        });    
        
        //Evitar que el usuario escriba ciertos caracteres
        fpdTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) { 
                if(e.getKeyChar() == '.' && fpdTextField.getText().contains(".")){
                    e.consume();
                }
                if(!Character.isDigit(e.getKeyChar()) && e.getKeyChar() != '.'){ //Solo se permiten numeros
                    e.consume(); 
                }
                if(fpdTextField.getText().equals("0") && e.getKeyChar() != '.'){
                    e.consume();
                }
                if(e.getKeyChar() == '.' && fpdTextField.getText().length() == 0){
                    e.consume();
                }
                if(!fpdTextField.getText().contains(".")){
                    if (fpdTextField.getText().length() >= 1 && e.getKeyChar() != '.'){ // solo se puede escribir hasta tres caracteres
                    e.consume(); 
                    }
                }else{
                    if (fpdTextField.getText().length() >= 5 ){ // solo se puede escribir hasta tres caracteres
                    e.consume(); 
                    }
                }
            }  
        }); 
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tituloLabel = new javax.swing.JLabel();
        tituloPrediccionLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        resultadoLabel = new javax.swing.JLabel();
        errorLabel = new javax.swing.JLabel();
        precisionLabel = new javax.swing.JLabel();
        limpiarCamposButton = new javax.swing.JButton();
        embarazosLabel = new javax.swing.JLabel();
        embarazosTextField = new javax.swing.JFormattedTextField();
        presionUnidadLabel = new javax.swing.JLabel();
        glucosaTextField = new javax.swing.JTextField();
        presionTextField = new javax.swing.JTextField();
        presionLabel = new javax.swing.JLabel();
        glucosaLabel = new javax.swing.JLabel();
        insulinaLabel = new javax.swing.JLabel();
        insulinaTextField = new javax.swing.JTextField();
        insulinaUnidadLabel = new javax.swing.JLabel();
        imcLabel = new javax.swing.JLabel();
        imcTextField = new javax.swing.JTextField();
        imcUnidadLabel = new javax.swing.JLabel();
        glucosaUnidadLabel = new javax.swing.JLabel();
        pliegueCutaneoLabel = new javax.swing.JLabel();
        pliegueCutaneoTextField = new javax.swing.JTextField();
        pliegueCutaneoUnidadLabel = new javax.swing.JLabel();
        fpdLabel = new javax.swing.JLabel();
        fpdTextField = new javax.swing.JTextField();
        fpdUnidadLabel = new javax.swing.JLabel();
        edadLabel = new javax.swing.JLabel();
        edadTextField = new javax.swing.JTextField();
        embarazosUnidadLabel = new javax.swing.JLabel();
        predecirButton = new javax.swing.JButton();
        edadUnidadLabel = new javax.swing.JLabel();
        creditosButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);

        tituloLabel.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        tituloLabel.setText("Llena todos los campos para efectuar la predicción de diabetes en una mujer");
        jPanel1.add(tituloLabel);
        tituloLabel.setBounds(30, 0, 610, 30);

        tituloPrediccionLabel.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        tituloPrediccionLabel.setText("Predicción:");
        jPanel1.add(tituloPrediccionLabel);
        tituloPrediccionLabel.setBounds(10, 290, 350, 23);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jPanel2.setLayout(null);

        resultadoLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        resultadoLabel.setText("Presiona el botón de predecir cuando los campos estén llenos...");
        jPanel2.add(resultadoLabel);
        resultadoLabel.setBounds(10, 10, 630, 20);

        errorLabel.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        errorLabel.setText("Error del modelo:");
        jPanel2.add(errorLabel);
        errorLabel.setBounds(350, 50, 280, 14);

        precisionLabel.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        precisionLabel.setText("Precisión del modelo:");
        jPanel2.add(precisionLabel);
        precisionLabel.setBounds(10, 50, 280, 16);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(10, 320, 650, 70);

        limpiarCamposButton.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        limpiarCamposButton.setText("<html>Limpiar<br>Campos</html>");
        limpiarCamposButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarCamposButtonActionPerformed(evt);
            }
        });
        jPanel1.add(limpiarCamposButton);
        limpiarCamposButton.setBounds(490, 190, 130, 40);

        embarazosLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        embarazosLabel.setText("Embarazos:");
        jPanel1.add(embarazosLabel);
        embarazosLabel.setBounds(10, 40, 80, 20);

        embarazosTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        embarazosTextField.setToolTipText("");
        embarazosTextField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        embarazosTextField.setHighlighter(null);
        embarazosTextField.setName("ttgfdghgfd"); // NOI18N
        embarazosTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                embarazosTextFieldActionPerformed(evt);
            }
        });
        jPanel1.add(embarazosTextField);
        embarazosTextField.setBounds(90, 40, 80, 26);

        presionUnidadLabel.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        presionUnidadLabel.setText("mmHg");
        jPanel1.add(presionUnidadLabel);
        presionUnidadLabel.setBounds(610, 40, 40, 40);

        glucosaTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        glucosaTextField.setToolTipText("");
        glucosaTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                glucosaTextFieldActionPerformed(evt);
            }
        });
        jPanel1.add(glucosaTextField);
        glucosaTextField.setBounds(310, 40, 80, 26);

        presionTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        presionTextField.setToolTipText("");
        presionTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                presionTextFieldActionPerformed(evt);
            }
        });
        jPanel1.add(presionTextField);
        presionTextField.setBounds(530, 40, 80, 26);

        presionLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        presionLabel.setText("<html>Presión<br>Sanguínea:</html>");
        jPanel1.add(presionLabel);
        presionLabel.setBounds(460, 30, 70, 40);

        glucosaLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        glucosaLabel.setText("Glucosa:");
        jPanel1.add(glucosaLabel);
        glucosaLabel.setBounds(250, 40, 60, 20);

        insulinaLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        insulinaLabel.setText("Insulina:");
        jPanel1.add(insulinaLabel);
        insulinaLabel.setBounds(250, 120, 60, 20);

        insulinaTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        insulinaTextField.setToolTipText("");
        insulinaTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insulinaTextFieldActionPerformed(evt);
            }
        });
        jPanel1.add(insulinaTextField);
        insulinaTextField.setBounds(310, 120, 80, 26);

        insulinaUnidadLabel.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        insulinaUnidadLabel.setText("µIU/ml");
        jPanel1.add(insulinaUnidadLabel);
        insulinaUnidadLabel.setBounds(390, 120, 40, 40);

        imcLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        imcLabel.setText("IMC:");
        jPanel1.add(imcLabel);
        imcLabel.setBounds(490, 120, 40, 20);

        imcTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        imcTextField.setToolTipText("");
        imcTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imcTextFieldActionPerformed(evt);
            }
        });
        jPanel1.add(imcTextField);
        imcTextField.setBounds(530, 120, 80, 26);

        imcUnidadLabel.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        imcUnidadLabel.setText("kg/m²");
        jPanel1.add(imcUnidadLabel);
        imcUnidadLabel.setBounds(610, 120, 40, 40);

        glucosaUnidadLabel.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        glucosaUnidadLabel.setText("mg/dl");
        jPanel1.add(glucosaUnidadLabel);
        glucosaUnidadLabel.setBounds(390, 40, 40, 40);

        pliegueCutaneoLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pliegueCutaneoLabel.setText("<html>Pliegue<br>Cutáneo:</html>");
        jPanel1.add(pliegueCutaneoLabel);
        pliegueCutaneoLabel.setBounds(20, 110, 70, 40);

        pliegueCutaneoTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pliegueCutaneoTextField.setToolTipText("");
        pliegueCutaneoTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pliegueCutaneoTextFieldActionPerformed(evt);
            }
        });
        jPanel1.add(pliegueCutaneoTextField);
        pliegueCutaneoTextField.setBounds(90, 120, 80, 26);

        pliegueCutaneoUnidadLabel.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        pliegueCutaneoUnidadLabel.setText("mm");
        jPanel1.add(pliegueCutaneoUnidadLabel);
        pliegueCutaneoUnidadLabel.setBounds(170, 120, 40, 40);

        fpdLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        fpdLabel.setText("FPD:");
        jPanel1.add(fpdLabel);
        fpdLabel.setBounds(40, 200, 50, 20);

        fpdTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        fpdTextField.setToolTipText("");
        fpdTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fpdTextFieldActionPerformed(evt);
            }
        });
        jPanel1.add(fpdTextField);
        fpdTextField.setBounds(90, 200, 80, 26);

        fpdUnidadLabel.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jPanel1.add(fpdUnidadLabel);
        fpdUnidadLabel.setBounds(170, 200, 40, 40);

        edadLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        edadLabel.setText("Edad:");
        jPanel1.add(edadLabel);
        edadLabel.setBounds(260, 200, 50, 20);

        edadTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        edadTextField.setToolTipText("");
        edadTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edadTextFieldActionPerformed(evt);
            }
        });
        jPanel1.add(edadTextField);
        edadTextField.setBounds(310, 200, 80, 26);

        embarazosUnidadLabel.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jPanel1.add(embarazosUnidadLabel);
        embarazosUnidadLabel.setBounds(170, 40, 40, 40);

        predecirButton.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        predecirButton.setText("Predecir");
        predecirButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                predecirButtonActionPerformed(evt);
            }
        });
        jPanel1.add(predecirButton);
        predecirButton.setBounds(280, 250, 130, 40);

        edadUnidadLabel.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        edadUnidadLabel.setText("años");
        jPanel1.add(edadUnidadLabel);
        edadUnidadLabel.setBounds(390, 200, 40, 40);

        creditosButton.setText("Créditos");
        creditosButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creditosButtonActionPerformed(evt);
            }
        });
        jPanel1.add(creditosButton);
        creditosButton.setBounds(580, 290, 80, 23);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void embarazosTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_embarazosTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_embarazosTextFieldActionPerformed

    private void glucosaTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_glucosaTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_glucosaTextFieldActionPerformed

    private void presionTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_presionTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_presionTextFieldActionPerformed

    private void insulinaTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insulinaTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_insulinaTextFieldActionPerformed

    private void imcTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imcTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_imcTextFieldActionPerformed

    private void pliegueCutaneoTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pliegueCutaneoTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pliegueCutaneoTextFieldActionPerformed

    private void fpdTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fpdTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fpdTextFieldActionPerformed

    private void edadTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edadTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edadTextFieldActionPerformed

    private void limpiarCamposButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarCamposButtonActionPerformed
        //Limpia todos los campos
        embarazosTextField.setText("");
        glucosaTextField.setText("");
        presionTextField.setText("");
        pliegueCutaneoTextField.setText("");
        insulinaTextField.setText("");
        imcTextField.setText("");
        fpdTextField.setText("");
        edadTextField.setText("");
    }//GEN-LAST:event_limpiarCamposButtonActionPerformed

    private void creditosButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creditosButtonActionPerformed
        //Muestra los créditos del desarrollo del programa
        JOptionPane.showOptionDialog(null, "-Créditos-\nDesarrollo:\n*Alberto Villarreal Canales 1746057\n"
                + "Dataset: https://www.kaggle.com/uciml/pima-indians-diabetes-database\nhttps://www.kaggle.com/saurabh00007/diabetescsv", "Créditos", JOptionPane.DEFAULT_OPTION,
        JOptionPane.INFORMATION_MESSAGE, null, null, null);
    }//GEN-LAST:event_creditosButtonActionPerformed

    private void predecirButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_predecirButtonActionPerformed
        //Al presionar el botón de Predecir, se llama al método de python que ejecuta la predicción
        //Reiniciamos valores por si queremos volver a predecir
         embarazos = 0; glucosa = 0; presion = 0; pliegue = 0; insulina = 0; edad = 0;
         imc = 0; fpd = 0;
         hayError = false;
        //Conseguimos todos los campos escritos        
        String embarazosStr, glucosaStr, presionStr, pliegueStr, insulinaStr, imcStr, fpdStr, edadStr;
        embarazosStr = embarazosTextField.getText();
        glucosaStr = glucosaTextField.getText();
        presionStr = presionTextField.getText();
        pliegueStr = pliegueCutaneoTextField.getText();
        insulinaStr = insulinaTextField.getText();
        imcStr = imcTextField.getText();
        fpdStr = fpdTextField.getText();
        edadStr = edadTextField.getText();
        //Pasar los campos de string a int y float respectivamente
        try{
            embarazos = Integer.parseInt(embarazosStr);
            glucosa = Integer.parseInt(glucosaStr);
            presion = Integer.parseInt(presionStr);
            pliegue = Integer.parseInt(pliegueStr);
            insulina = Integer.parseInt(insulinaStr);
            imc = Float.parseFloat(imcStr);
            fpd = Float.parseFloat(fpdStr);
            edad = Integer.parseInt(edadStr);
        }catch(Exception e){
            //Si hay algun error en los campos se notifica
            System.out.println(e);
            JOptionPane.showOptionDialog(null, "Revise los campos nuevamente", "", JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE, null, null, null);
            hayError = true;
        }
        String resultado = "";
        //Si no hay ningún error entonces podemos continuar
        if(hayError == false){
            //Al usar procesos es obligatorio usar un try-catch
             try {
            //Comando para compilar el script .py y mandarle los campos escritos como argumentos
            String command = "cmd /c python diabetes1_predict.py"+" "+embarazos+" "+glucosa+" "+presion+" "+pliegue+
                    " "+insulina+" "+imc+" "+fpd+" "+edad;
            //Ejecuta el comando y lee todos los resultados que haya arrojado el cmd por medio de un proceso
            Process p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader bri = new BufferedReader(new InputStreamReader(p.getInputStream())); //lee respuestas
            BufferedReader bre = new BufferedReader(new InputStreamReader(p.getErrorStream())); //lee errores
            while ((resultado = bri.readLine()) != null) {
                    resultado = resultado.replace("[","");
                    resultado = resultado.replace("]","");
                    System.out.println(resultado);
                   //Mostramos el resultado dependiendo de la predicción que haya salido en Python
                   if("1".equals(resultado)){
                      resultadoLabel.setText("La paciente tiene diabetes.");
                      resultadoLabel.setForeground(Color.MAGENTA);
                   }else if("0".equals(resultado)){
                      resultadoLabel.setText("La paciente no tiene diabetes.");
                      resultadoLabel.setForeground(Color.BLUE);
                   }
                  }
                  bri.close();
                  while ((resultado = bre.readLine()) != null) {
                    System.out.println(resultado);
                  }
                //Al terminar de mandar resultados se cierrar el proceso
                bre.close();
                p.waitFor();
                p.destroy();
             } catch (IOException ex) {
                 Logger.getLogger(DiabetesGUI.class.getName()).log(Level.SEVERE, null, ex);
             } catch (InterruptedException ex) {
                 Logger.getLogger(DiabetesGUI.class.getName()).log(Level.SEVERE, null, ex);
             }
        }else{
            //Pedimos que revise el campo invalido
            resultadoLabel.setText("Revise los campos nuevamente.");
            resultadoLabel.setForeground(Color.RED);
        }
    }//GEN-LAST:event_predecirButtonActionPerformed
                
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DiabetesGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DiabetesGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DiabetesGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DiabetesGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new DiabetesGUI().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(DiabetesGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton creditosButton;
    private javax.swing.JLabel edadLabel;
    private javax.swing.JTextField edadTextField;
    private javax.swing.JLabel edadUnidadLabel;
    private javax.swing.JLabel embarazosLabel;
    private javax.swing.JTextField embarazosTextField;
    private javax.swing.JLabel embarazosUnidadLabel;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JLabel fpdLabel;
    private javax.swing.JTextField fpdTextField;
    private javax.swing.JLabel fpdUnidadLabel;
    private javax.swing.JLabel glucosaLabel;
    private javax.swing.JTextField glucosaTextField;
    private javax.swing.JLabel glucosaUnidadLabel;
    private javax.swing.JLabel imcLabel;
    private javax.swing.JTextField imcTextField;
    private javax.swing.JLabel imcUnidadLabel;
    private javax.swing.JLabel insulinaLabel;
    private javax.swing.JTextField insulinaTextField;
    private javax.swing.JLabel insulinaUnidadLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton limpiarCamposButton;
    private javax.swing.JLabel pliegueCutaneoLabel;
    private javax.swing.JTextField pliegueCutaneoTextField;
    private javax.swing.JLabel pliegueCutaneoUnidadLabel;
    private javax.swing.JLabel precisionLabel;
    private javax.swing.JButton predecirButton;
    private javax.swing.JLabel presionLabel;
    private javax.swing.JTextField presionTextField;
    private javax.swing.JLabel presionUnidadLabel;
    private javax.swing.JLabel resultadoLabel;
    private javax.swing.JLabel tituloLabel;
    private javax.swing.JLabel tituloPrediccionLabel;
    // End of variables declaration//GEN-END:variables
}
