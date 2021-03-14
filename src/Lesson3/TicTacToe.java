package Lesson3;

import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    static final char DOT_EMPTY = '•';
    static final char DOT_HUMAN = 'X';
    static final char DOT_AI = 'O';
    static final int MAX_SIZE = 9;
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();
    static char[][] map;
    static int SIZE;
    static int WIN_COMBO;

    static int[][] valueArrayHuman;
    static int[][] valueArrayAI;

    public static void main(String[] args) {
        startGame();
    }

    private static void startGame() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n\nДобро пожаловать в игру \"Крестики нолики\"! \n");
            createMap();
            playGame();
            System.out.println("Желаете ли Вы повторить игру? (\"Да\" чтобы начать)");
            isRunning = (scanner.next().toLowerCase(Locale.ROOT).equals("да")) ? true : false;
        }
        System.out.println("Спасибо за игру!");
    }

    private static void createMap() {
        inputGameParameters();
        initMapArray();

    }

    private static void initMapArray() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    /* Предлагаем выбрать настройки игры */
    private static void inputGameParameters() {
        inputMapSize();
        inputWinCombo();
        System.out.println("Вы выбрали следуюшие параметры поля :");
        System.out.println("Размер поля : " + SIZE + " x " + SIZE);
        System.out.println("Победа: если игрок заполнит линию из " + WIN_COMBO + " символов");
    }

    private static void inputMapSize() {
        System.out.println("Введите размер поля на котором желаете сыграть (3-" + MAX_SIZE + "):");
        boolean validInput = false;
        while (!validInput) {
            while (!scanner.hasNextInt()) {
                System.out.println("Ошибка ввода! Введите число от 3 до " + MAX_SIZE + "!");
                scanner.nextLine();
            }
            SIZE = scanner.nextInt();
            if ((SIZE >= 3) & (SIZE <= MAX_SIZE)) {
                validInput = true;
            } else {
                System.out.println("Ошибка ввода! Введите число от 3 до " + MAX_SIZE + "!");
            }
        }
        scanner.nextLine();
    }

    private static void inputWinCombo() {
        System.out.println("Сколько символов подряд будет означать победу?");
        boolean validInput = false;
        while (!validInput) {
            while (!scanner.hasNextInt()) {
                System.out.println("Ошибка ввода! Введите число от 0 до " + SIZE);
                scanner.nextLine();
            }
            WIN_COMBO = scanner.nextInt();
            if (WIN_COMBO >= 3 && WIN_COMBO <= SIZE) {
                validInput = true;
            } else {
                System.out.println("Ошибка ввода! Введите число от 3 до " + SIZE);
            }
        }
    }

    private static void playGame() {
        drawField();
        while (true) {
            System.out.println("Ход игрока:");
            turnHuman();
            drawField();
            if (checkEndOfTheGame()) {
                break;
            }


            System.out.println("Ход компьютера:");
            turnAI();
            drawField();
            if (checkEndOfTheGame()) {
                break;
            }
        }

    }

    private static void drawField() {
        drawTopBorder();
        drawMapHeader();
        drawArray();
        drawBottomBorder();
    }

    private static void drawTopBorder() {
        System.out.print("╔");
        for (int i = 0; i < SIZE * 2 + 2; i++) {
            System.out.print("═");
        }
        System.out.println("╗");
    }

    private static void drawMapHeader() {
        System.out.print("║  ");
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
        }
        System.out.print("║");
        System.out.println();
    }

    private static void drawBottomBorder() {
        System.out.print("╚");
        for (int i = 0; i < SIZE * 2 + 2; i++) {
            System.out.print("═");
        }
        System.out.println("╝");
    }

    public static void drawArray() {
        for (int i = 0; i < SIZE; i++) {
            System.out.print("║" + (i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.print("║");
            System.out.println();
        }
    }

    private static void turnHuman() {
        System.out.println("Введите поочередно номер столбца и номер строки:");
        inputRead();
    }

    /* Запрос на ввод пользователя */
    private static void inputRead() {
        int i = 0, j = 0;
        do {
            System.out.println("Номер строки:");
            i = validateCoordinate();
            System.out.println("Номер столбца:");
            j = validateCoordinate();
            if (map[i - 1][j - 1] != DOT_EMPTY) {
                System.out.println("Эта клетка занята! Выберите другую!");
            } else {
                break;
            }
        } while (true);
        map[i - 1][j - 1] = DOT_HUMAN;
    }

    /* Обработка ввода + проверка */
    private static int validateCoordinate() {
        boolean validInput = false;
        int coordinate = 0;
        while (!validInput) {
            while (!scanner.hasNextInt()) {
                System.out.println("Ошибка ввода! Введите число от 1 до " + SIZE);
                scanner.nextLine();
            }
            coordinate = scanner.nextInt();
            scanner.nextLine();
            if ((coordinate <= SIZE) & (coordinate >= 0)) {
                validInput = true;
            } else {
                continue;
            }
        }
        return coordinate;
    }

    /* Определяем ход для ИИ */
    private static void turnAI() {
        int[] in = new int[3];
        in = AI_analyzeMap();
        switch (in[0]) {
            case  1:
            case  2:
                map[in[1]][in[2]] = DOT_AI;
                break;
            default:
                AI_randomTurn();
        }
    }

    /* Метод оценивает игровое поле заданному символу,
     * Определяет ценность каждой незанятой ячейки массива
     */
    private static int[] AI_analyzeMap() {
        int[][] valueArrayHuman = new int[SIZE][SIZE];
        int[][] valueArrayAI = new int[SIZE][SIZE];
        AI_initValueArray(valueArrayAI);
        AI_initValueArray(valueArrayHuman);

        AI_setValueArray(DOT_HUMAN, valueArrayHuman);
        AI_setValueArray(DOT_AI, valueArrayAI);
        if (maximumValue(valueArrayAI)[0] > maximumValue(valueArrayHuman)[0]) {
            return new int[]{1, maximumValue(valueArrayAI)[1], maximumValue(valueArrayAI)[2]};
        } else if ((maximumValue(valueArrayAI)[0] < maximumValue(valueArrayHuman)[0])) {
            return new int[]{2, maximumValue(valueArrayHuman)[1], maximumValue(valueArrayHuman)[2]};
        } else {
            return new int[]{5, 0, 0};
        }

    }

    private static void AI_initValueArray(int[][] valueArray) {

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                valueArray[i][j] = 0;
            }
        }


    }

    private static void AI_setValueArray(char symbol, int[][] valueArray) {
        AI_initValueArray(valueArray);
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    map[i][j] = symbol;
                    switch (symbol) {
                        case DOT_AI:
                            if (searchForWinCombo(symbol)) {
                                valueArray[i][j] += 500;
                            }
                            break;
                        case DOT_HUMAN:
                            if (searchForWinCombo(symbol)) {
                                valueArray[i][j] += 300;
                                break;
                            }
                            if (foreseeWinCombo(symbol)){
                                valueArray[i][j] +=200;
                                WIN_COMBO--;
                                valueArray[i][j] += searchForWinCombo(symbol)?50 : 0;
                                WIN_COMBO++;
                            }
                            break;
                    }
                    map[i][j] = DOT_EMPTY;
                } else {
                    valueArray[i][j] = 0;
                }
                 System.out.print(valueArray[i][j]+" ");
            }
             System.out.println();
        }
    }

    private static int[] maximumValue(int[][] array) {
        int max = 0;
        int imax = 0, jmax = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (array[i][j] > max) {
                    max = array[i][j];
                    imax = i;
                    jmax = j;
                }
            }
        }
        return new int[]{max, imax, jmax};
    }

    private static void AI_randomTurn() {
        int i = 0, j = 0;
        do {
            i = random.nextInt(SIZE);
            j = random.nextInt(SIZE);
        } while (map[i][j] != DOT_EMPTY);
        map[i][j] = DOT_AI;
    }

    private static boolean checkEndOfTheGame() {
        return (checkWin() || checkDraw());
    }

    /* Проверка на ничью */
    private static boolean checkDraw() {
        int countEmptyDot = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    countEmptyDot++;
                }
            }
        }
        if (countEmptyDot == 0) {
            System.out.println("НИЧЬЯ =(");
            return true;
        }
        return false;
    }

    /* Определяем победителя */
    private static boolean checkWin() {
        if (searchForWinCombo(DOT_AI)) {
            System.out.println("Скайнет победил =(");
            return true;
        }
        if (searchForWinCombo(DOT_HUMAN)) {
            System.out.println("Вы выиграли!");
            return true;
        }
        return false;
    }
    private static boolean foreseeWinCombo(char symbol){
        for (int i = 0; i < SIZE ; i++) {
            for (int j = 0; j < SIZE ; j++) {
                if(map[i][j]==DOT_EMPTY) {
                    map[i][j] = symbol;
                    if (searchForWinCombo(symbol)) {
                        map[i][j] = DOT_EMPTY;
                        return true;
                    } else {
                        map[i][j] = DOT_EMPTY;
                    }
                }
            }
        }
        return false;
    }
    private static boolean searchForWinCombo(char symbol) {

        return (checkRowsCombo(symbol) || checkColumnsCombo(symbol)
                || checkDiagonals(symbol));
    }

    /*Проверка выигрышной комбинации по строкам*/
    private static boolean checkRowsCombo(char symbol) {
        int combo = 0;
        for (int i = 0; i < SIZE; i++) {
            combo = 0;
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == symbol) {
                    combo++;
                } else if ((combo > 0) && (map[i][j] != symbol) && (combo < WIN_COMBO)) {
                    combo = 0;
                }
            }
            if (combo >= WIN_COMBO) {
                return true;
            }
        }
        return false;
    }

    /* Проверка выигрышной комбинации по столбцам*/
    private static boolean checkColumnsCombo(char symbol) {
        int combo = 0;
        for (int j = 0; j < SIZE; j++) {
            combo = 0;
            for (int i = 0; i < SIZE; i++) {
                if (map[i][j] == symbol) {
                    combo++;
                } else if ((combo > 0) && (map[i][j] != symbol) && (combo < WIN_COMBO)) {
                    combo = 0;
                }
            }
            if (combo >= WIN_COMBO) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkDiagonals(char symbol) {
        return (leftDiagonals(symbol) || rightDiagonals(symbol));
    }

    /* Проверка диагоналей слева-направо */
    private static boolean leftDiagonals(char symbol) {
        int combo = 0;
        for (int diagI = 0; diagI <= WIN_COMBO; diagI++) {
            combo = 0;
            /*Центральная диагональ и нижнее отсчение поля */
            for (int i = 0 + diagI; i < SIZE; i++) {
                if (map[i][i - diagI] == symbol) {
                    combo++;
                } else if ((combo > 0) && (map[i][i - diagI] != symbol) && (combo < WIN_COMBO)) {
                    combo = 0;
                }
            }
            if (combo >= WIN_COMBO) {
                return true;
            }
            combo = 0;
            /*Верхнее отсчение поля и еще раз центральная..ну чтоб наверняка х) */
            for (int i = 0; i < SIZE - diagI; i++) {
                if (map[i][i + diagI] == symbol) {
                    combo++;
                } else if ((combo > 0) && (map[i][i + diagI] != symbol) && (combo < WIN_COMBO)) {
                    combo = 0;
                }
            }
            if (combo >= WIN_COMBO) {
                return true;
            }
        }
        return false;
    }

    /*Проверка диагоналей справа налево*/
    private static boolean rightDiagonals(char symbol) {
        int combo = 0;
        for (int diagJ = 0; diagJ <= WIN_COMBO; diagJ++) {
            combo = 0;
            /* Центральная с верхним отсечением */
            for (int i = 0; i < SIZE - diagJ; i++) {
                if (map[i][SIZE - i - 1 - diagJ] == symbol) {
                    combo++;
                } else if ((combo > 0) && (map[i][SIZE - i - 1 - diagJ] != symbol) && (combo < WIN_COMBO)) {
                    combo = 0;
                }
            }
            if (combo >= WIN_COMBO) {
                return true;
            }
            combo = 0;
            /* Центральная с нижним отсечением */
            for (int i = 0; i < SIZE - diagJ; i++) {
                if (map[i + diagJ][SIZE - i - 1] == symbol) {
                    combo++;
                } else if ((combo > 0) && (map[i + diagJ][SIZE - i - 1] != symbol) && (combo < WIN_COMBO)) {
                    combo = 0;
                }
            }
            if (combo >= WIN_COMBO) {
                return true;
            }
        }
        return false;
    }
}

