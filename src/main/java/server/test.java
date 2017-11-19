
public class test {
    public static void main(String[] args) {
      int zahl = 45;
      int  k=0;
      int l=0;
      boolean a = true;
      do{
          l=zahl%10;
          k=k+(l*l);
          System.out.println(k);
          zahl=zahl/10;

          if(k==4){
              System.out.println(k +" Traurige Zahl!");
              break;
          }
          else if(k==1){
              System.out.println(k +" Gückliche Zahl!");
              break;
          }

          else if(zahl==0){
              zahl=k;
              k=0;
          }

      }while(a);
    }
}
