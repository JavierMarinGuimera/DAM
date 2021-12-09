package joinThreads;

class TiradaDaus {
   private int tiradaDau;

   public TiradaDaus(int e) {
           tiradaDau = e;
   }

   public synchronized int getSumaTirada() {
           return tiradaDau;
   }

   public synchronized void setSumaTirada(int e) {
           tiradaDau += e;
   }
}

public class JoinThreads implements Runnable {

   private static TiradaDaus tiradaCompartida;

   @Override
   public void run() {
           try {
                   Thread.sleep(1000);
                   int resultatDau = (int) (Math.random() * 6) + 1;
                   tiradaCompartida.setSumaTirada(resultatDau);
                   System.out.println("Tirada fil " + Thread.currentThread().getName() + ": " + resultatDau);
           } catch (InterruptedException e) {
           }
   }

   public static void main(String[] args) throws InterruptedException {
           tiradaCompartida = new TiradaDaus(0);

           JoinThreads obj1 = new JoinThreads();
           JoinThreads obj2 = new JoinThreads();
           JoinThreads obj3 = new JoinThreads();

           Thread fil_1 = new Thread(obj1);
           fil_1.setName("Dau 1");
           Thread fil_2 = new Thread(obj2);
           fil_2.setName("Dau 2");
           Thread fil_3 = new Thread(obj3);
           fil_3.setName("Dau 3");

           fil_1.start();
           fil_2.start();
           fil_3.start();

           fil_1.join();
           fil_2.join();
           fil_3.join();

           System.out.println("Total tirada: " + tiradaCompartida.getSumaTirada());
           System.out.println("Final Fil Principal");
   }

}