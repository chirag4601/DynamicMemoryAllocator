import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class testcases {
    public static void main(String args[]) throws IOException{
        File myObj = new File("505000_test.txt");
        FileWriter fw=new FileWriter("505000_test_ans.txt");
        Scanner sc = new Scanner(myObj);
        int numTestCases;
        numTestCases = sc.nextInt();
        while (numTestCases-- > 0) {
            int size;
            size = sc.nextInt();
            A2DynamicMem obj = new A2DynamicMem(size, 2);
            int numCommands = sc.nextInt();
            while (numCommands-- > 0) {
                String command;
                command = sc.next();
                int argument;
                argument = sc.nextInt();
                int result = -5;
                boolean toPrint = true;
                switch (command) {
                    case "Allocate":
                        result = obj.Allocate(argument);
                        break;
                    case "Free":
                        result = obj.Free(argument);
                        break;
                    case "Defragment":
                        obj.Defragment();
                        toPrint = false;
                        break;
                    default:
                        break;
                }
                if(toPrint) {
	                String str = String.valueOf(result);
	                fw.write(str);
	                fw.write("\n");
                }
            }

        }
        fw.close();
        sc.close();
    }
}