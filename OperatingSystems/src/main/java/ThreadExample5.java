public class ThreadExample5 {
    public static final void main(String[] args) throws InterruptedException{
        Runnable task = () -> {
            try {
                while (true){
                    System.out.println("Hello, Lambda Runnable!");
                    Thread.sleep(100);
                }
            }
            catch(InterruptedException ie){
                System.out.println("Im interrupted");
            }
        };

        Thread thread = new Thread((task));
        thread.start();
        Thread.sleep(500);
        thread.interrupt();
        System.out.println("hello my interrupted child");
    }
}
