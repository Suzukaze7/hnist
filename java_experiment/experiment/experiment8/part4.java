package experiment.experiment8;

public class part4 {
    public static void main(String[] args) {
        for (int i = 1; i <= 4; i++) {
            try {
                switch (i) {
                    case 1:
                        Object object1 = null;
                        object1.toString();
                        break;
                    case 2:
                        Object object2 = new Object();
                        String string = (String) object2;
                        break;
                    case 3:
                        int array1[] = new int[-1];
                    case 4:
                        int array2[] = new int[10];
                        int t1 = array2[-1];
                }
            }
            catch (NullPointerException e) {
                System.out.println("抛出了 " + e + " 异常并捕获");
            }
            catch (ClassCastException e) {
                System.out.println("抛出了 " + e + " 异常并捕获");
            }
            catch (NegativeArraySizeException e) {
                System.out.println("抛出了 " + e + " 异常并捕获");
            }
            catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("抛出了 " + e + " 异常并捕获");
            }
        }
    }
}
