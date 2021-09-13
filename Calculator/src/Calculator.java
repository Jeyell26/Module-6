import acm.graphics.GObject;
import acm.program.GraphicsProgram;

import java.awt.event.MouseEvent;

public class Calculator extends GraphicsProgram {
    // Main calculator variable
    CalculatorLayout calc = new CalculatorLayout(700);
    // Object variable
    GObject object;
    // Input string
    String input;
    // Operands
    String Operand1, Operand2;
    // Possible datatypes
    int int1, int2;
    double db1, db2;
    // Characters
    char opBuffer;

    public void run() {
        setSize(520, 760);
        addMouseListeners();
        add(calc);
        initBooleans();
    }

    // from https://stackabuse.com/java-check-if-string-is-a-number/
    private static boolean isNumeric(String string) {
        int intValue;

//        System.out.println(String.format("Parsing string: \"%s\"", string));

        if(string == null || string.equals("")) {
//            System.out.println("String cannot be parsed, it is null or empty.");
            return false;
        }

        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
//            System.out.println("Input String cannot be parsed to Integer.");
        }
        return false;
    }

    public void mouseClicked(MouseEvent e) {
        object = calc.getElementAt(e.getX(),e.getY());
        if (object instanceof MyButton) {
            input = ((MyButton) object).getText();
            processButton();
        }
    }

    public void operate(int x, int y){
        if(opBuffer == '+'){
            Operand1 = Integer.toString(MathHandler.add(x,y));
            calc.clearMainDisplay();
            calc.setMainDisplay(Operand1);
        }
        if(opBuffer == '-'){
            Operand1 = Integer.toString(MathHandler.subtract(x,y));
            calc.clearMainDisplay();
            calc.setMainDisplay(Operand1);
        }
        if(opBuffer == '÷'){
            Operand1 = Double.toString(MathHandler.divide(x,y));
            calc.clearMainDisplay();
            calc.setMainDisplay(Operand1);
        }
        if(opBuffer == 'x'){
            Operand1 = Integer.toString(MathHandler.multiply(x,y));
            calc.clearMainDisplay();
            calc.setMainDisplay(Operand1);
        }
    }

    public void operate(double x, double y){
        if(opBuffer == '+'){
            Operand1 = Double.toString(MathHandler.add(x,y));
            calc.clearMainDisplay();
            calc.setMainDisplay(Operand1);
        }
        if(opBuffer == '-'){
            Operand1 = Double.toString(MathHandler.subtract(x,y));
            calc.clearMainDisplay();
            calc.setMainDisplay(Operand1);
        }
        if(opBuffer == '÷'){
            Operand1 = Double.toString(MathHandler.divide(x,y));
            calc.clearMainDisplay();
            calc.setMainDisplay(Operand1);
        }
        if(opBuffer == 'x'){
            Operand1 = Double.toString(MathHandler.multiply(x,y));
            calc.clearMainDisplay();
            calc.setMainDisplay(Operand1);
        }
    }

    // Flags
    Boolean isDeletable;
    Boolean wasNumber;
    Boolean wasOperator;
    Boolean performOperator;
    Boolean firstInstance = true;
    Boolean secondInstance = false;
    Boolean nInstance;
    Boolean wasEquals;
    private void initBooleans(){
        isDeletable = false;
        wasNumber = false;
        wasOperator = false;
        performOperator = false;
        nInstance = false;
        wasEquals = false;
        Operand1 = "";
        Operand2 = "";
        opBuffer = ' ';
    }

    private void processButton(){
        // If its the starter ones
        // I. Handle special cases: Clear Element, Clear All, and  Delete
        if (input.equals("CE ")) {
            // clears main display
            calc.clearMainDisplay();
            System.out.println("Clear Element");
            return;
        }
        if (input.equals("C")) {
            calc.clearMainDisplay();
            calc.clearMemoDisplay();
            initBooleans();
            firstInstance = true;
            secondInstance = false;
            System.out.println("Clear Called");
            return;
        }
        if (input.equals("⌫") && isDeletable) {
            calc.delOneMain();
            System.out.println("Delete Called");
            return;
        }
        // II. Handle Number Inputs;
        if(isNumeric(input)){
            if(wasOperator){
                calc.clearMainDisplay();
                calc.setMainDisplay("0");
            }
            if(wasEquals){
                calc.clearMemoDisplay();
                initBooleans();
                firstInstance = true;
                secondInstance = false;
            }
            wasOperator = false;
            wasNumber = true;
            wasEquals = false;
            isDeletable = true;
            calc.setMainDisplay(input.charAt(0));
            return;
        }
        if(input.equals(".")){
            if(!calc.getMainDisplay().contains(".")){
               calc.setMainDisplay(input.charAt(0));
            }
        }
        if(input.equals("±")){
            calc.negateElement();
        }
        // III. Operator Inputs;
        if(input.equals("=") || input.equals("+") || input.equals("-") || input.equals("÷") || input.equals("x")){
            // If previous input was a operator
            if(wasOperator){
                calc.delOneMemo();
                performOperator = false;
            }
            // If previous input was a number
            if(wasNumber){
                if(nInstance){
                    Operand2 = calc.getMainDisplay();
                }
                if(secondInstance){
                    nInstance = true;
                    secondInstance = false;
                    Operand2 = calc.getMainDisplay();
                }
                if(firstInstance){
                    secondInstance = true;
                    firstInstance = false;
                    Operand1 = calc.getMainDisplay();
                }
                calc.setMemoDisplay(calc.getMainDisplay());
            }
            else{
                if(firstInstance){
                    secondInstance = true;
                    firstInstance = false;
                    calc.setMemoDisplay("0");
                    Operand1 = calc.getMainDisplay();
                }
            }
            if(wasOperator && input.equals("=")){
                performOperator = true;
            }
            // If equals is called and there was an operator to be performed
            if(performOperator && input.equals("=")){
                calc.clearMemoDisplay();
                if(secondInstance){
                    calc.setMemoDisplay(Operand1+opBuffer+Operand1);
                }
                else{
                    calc.setMemoDisplay(Operand1+opBuffer+Operand2);
                }

            }
            // If Operator should be performed
            if(performOperator){
                if(secondInstance){
                    Operand2 = Operand1;
                }

                System.out.println(Operand1 + " " + Operand2);
                if(!Operand1.contains(".") && !Operand2.contains(".")){
                    int1 = Integer.parseInt(Operand1);
                    int2 = Integer.parseInt(Operand2);
                    operate(int1,int2);
                }
                else{
                    db1 = Double.parseDouble(Operand1);
                    db2 = Double.parseDouble(Operand2);
                    operate(db1,db2);
                }
            }
            // If x or / is called
            if((input.equals("x") || input.equals("÷")) && !firstInstance){
                calc.clearMemoDisplay();
                calc.setMemoDisplay(Operand1);
            }

            calc.setMemoDisplay(input);
            if(!input.equals("=")){
                opBuffer = input.charAt(0);
                wasEquals = false;
            }

            else{
                wasEquals = true;
            }
            isDeletable = false;
            wasNumber = false;
            wasOperator = true;
            performOperator = true;
        }
    }
    }