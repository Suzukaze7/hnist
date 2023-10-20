package execption;

public class InputInfoExecption extends Exception {
    public void printMsg() {
        System.out.println("输入信息有误，请重新选择");
    }
}