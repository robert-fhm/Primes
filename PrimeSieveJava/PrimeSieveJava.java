import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

public class PrimeSieveJava {

    static class PrimeJava {
        static class prime_sieve{
            private int sieveSize = 0;
            // TODO MansanC uses boolean[] here - could try this?
            private BitSet bitArray;

            // TODO try converting myDict into primitive data types later
            private HashMap<Integer, Integer> myDict = new HashMap <>(Map.of(
                 10 , 1,
                 100 , 25,
                 1000 , 168,
                 10000 , 1229,
                 100000 , 9592,
                 1000000 , 78498,
                 10000000 , 664579,
                 100000000 , 5761455));

            // Constructor
            public prime_sieve(int size){
                this.sieveSize = size;
                this.bitArray = new BitSet((this.sieveSize + 1) / 2);
                this.bitArray.flip(0, (this.sieveSize + 1) / 2); // Set all values to true
            }

            public int countPrimes(){
                int count = 0;
                for (int i = 0; i < this.bitArray.size(); i++){
                    if (bitArray.get(i)) {
                        count++;
                    }
                }
                return count;
            }

            private boolean validateResults(){
                if (myDict.containsKey(this.sieveSize)){
                    return this.myDict.get(this.sieveSize) == this.countPrimes();
                }
                return false;
            }

            private boolean getBit(int index){
                if (index % 2 == 0){
                    return false;
                }
                return this.bitArray.get(index / 2);
            }

            private void clearBit(int index){
                if (index % 2 == 0){
                    System.out.println("You are setting even bits, which is sub-optimal");
                    return;
                }
                bitArray.set(index / 2, false);
            }

            public void runSieve(){
                int factor = 3;
                int q = (int) Math.sqrt(this.sieveSize);

                while (factor < q){
                    for (int num = factor; num <= this.sieveSize; num++){
                        if (getBit(num)){
                            factor = num;
                            break;
                        }
                    }

                    for (int num = factor * 3; num <= this.sieveSize; num += factor * 2){
                        clearBit(num);
                    }

                    factor += 2;
                }
            }

            public void printResults(boolean showResults, double duration, int passes){
                if (showResults){
                    System.out.println("2, ");
                }

                int count = 1;
                for (int num = 3; num <= this.sieveSize; num++){
                    if (getBit(num)){
                        if(showResults){
                            System.out.println(num + ", ");
                        }
                        count++;
                    }
                }
                if (showResults){
                    System.out.println("\nPasses: " + passes +
                            ", Time: " + duration +
                            ", Avg: " + (duration/passes) +
                            ", Limit: " + this.sieveSize +
                            ", Count: " + count +
                            ", Valid: " + validateResults()
                    );
                }
            }
        }

    }

    public static void main(String[] args) {

        long tStart = System.currentTimeMillis();
        int passes = 0;
        PrimeJava.prime_sieve sieve = null;

        while ((System.currentTimeMillis() - tStart) < 10000){
            sieve = new PrimeJava.prime_sieve(1000000);
            sieve.runSieve();
            passes++;
        }

        long tEnd = System.currentTimeMillis() - tStart;

        if (sieve != null){
            sieve.printResults(true,  tEnd / 1000f, passes);
        }
    }
}
