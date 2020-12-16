import java.sql.SQLOutput;

public class Currencies {
        public  enum Values{BYN, PLN, USD, EUR, TMT, UGX, JPY}
        private double Exchange_Rates[][];
        private int Size;
        public Currencies(){
            Size = 7;
            Exchange_Rates = new double[Size][Size];
            for(int i=0; i<Size; i++){
                for(int j=0;j<Size; j++){
                    Exchange_Rates[i][j]=(Math.random()*40+1)/Math.random()*10+5;
                    System.out.print(Exchange_Rates[i][j]+" ");
                }
                System.out.println();
            }
        }
        public double GetCurrency(int i, int j){
            return Exchange_Rates[i][j];
        }
}


