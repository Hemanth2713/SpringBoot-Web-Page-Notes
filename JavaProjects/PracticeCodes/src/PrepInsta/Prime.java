package PrepInsta;

public class Prime {
    public static void main(String[] args) {
        int num=17;
        System.out.println(prime(num));
    }
    public static  boolean prime(int num){
        int count=0;
        for(int i=1;i<num;i++){
            if(num%i==0)
                count++;
        }
        if(count==1)
            return true;
        else
            return false;
    }
}
