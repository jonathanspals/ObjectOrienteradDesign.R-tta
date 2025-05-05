package ObjectOrienteradDesign.integration;
/**
 * public class Kassa: Representerar en fysisk kassa som håller koll på försäljningssaldo.
 */
    public class Kassa {
        private float saldo = 0;
    
        /**
         * Uppdaterar kassans saldo med det totala priset från en försäljning.
         * @param totalPris Det totala priset som ska läggas till kassans saldo
         */
        public void uppdateraKassaSaldo(float totalPris) {
            if (totalPris < 0) {
                throw new IllegalArgumentException("Totalpris kan inte vara negativt.");
            }
    
            saldo += totalPris;
            System.out.println("Kassa uppdaterad. Nytt saldo: " + saldo);
        }
    
        /**
         * Hämtar det aktuella saldot på kassan.
         * @return Det aktuella saldot
         */
        public float getSaldo() {
            return saldo;
        }
    
        /**
         * Återställer kassans saldo till noll (för t.ex. tester eller omstart).
         */
        public void återställSaldo() {
            saldo = 0;
            System.out.println("Kassan har återställts till noll.");
        }
    }

