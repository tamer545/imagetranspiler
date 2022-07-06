import java.util.*;

public class Interpreter {
    private final Scanner ob = new Scanner(System.in);
    private int ptr; // Memory pointer

    public Interpreter() {
        Transpiler transpiler = new Transpiler();
        validate(transpiler.checkImage());
    }

    void validate(String brainfuckCode) {
        int openedBrackets = 0;
        for (int i = 0; i < brainfuckCode.length(); i++) {
            if (openedBrackets == 0 && brainfuckCode.charAt(i) == ']') {
                System.out.println("Invalid Code");
                return;
            } else {
                switch (brainfuckCode.charAt(i)) {
                    case '[' -> openedBrackets++;
                    case ']' -> openedBrackets--;
                }
            }
        }
        if (openedBrackets != 0) {
            System.out.println("Invalid Code");
        } else {
            System.out.println("Image is valid. Now interpreting..");
            interpret(brainfuckCode);
        }
    }

    void interpret(String code) {
        int openedBrackets = 0;
        int length = 65535;
        byte[] memory = new byte[length];

        for (int brainfuckCodePosition = 0; brainfuckCodePosition < code.length(); brainfuckCodePosition++) {
            switch (code.charAt(brainfuckCodePosition)) {
                case '>':
                    if (ptr == length - 1) {
                        ptr = 0;
                    } else {
                        ptr++;
                    }
                    break;
                case '<':
                    if (ptr == 0) {
                        ptr = length - 1;
                    } else {
                        ptr--;
                    }
                    break;
                case '+':
                    memory[ptr]++;
                    break;
                case '-':
                    memory[ptr]--;
                    break;
                case '.':
                    System.out.print((memory[ptr]) + " ");
                    break;
                case ',':
                    memory[ptr] = (byte) (ob.next().charAt(0));
                    break;
                case '[':
                    if (memory[ptr] == 0) {
                        brainfuckCodePosition++;
                        while (openedBrackets > 0 || code.charAt(brainfuckCodePosition) != ']') {
                            if (code.charAt(brainfuckCodePosition) == '[') {
                                openedBrackets++;
                            } else if (code.charAt(brainfuckCodePosition) == ']') {
                                openedBrackets--;
                                brainfuckCodePosition++;
                            }
                        }
                    }
                    break;
                case ']':
                    if (memory[ptr] != 0) {
                        brainfuckCodePosition--;
                        while (openedBrackets > 0 || code.charAt(brainfuckCodePosition) != '[') {
                            if (code.charAt(brainfuckCodePosition) == ']')
                                openedBrackets++;
                            else if (code.charAt(brainfuckCodePosition) == '[')
                                openedBrackets--;
                            brainfuckCodePosition--;
                        }
                        brainfuckCodePosition--;
                    }
                    break;
            }
        }
    }
}