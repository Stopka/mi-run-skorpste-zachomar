/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package run_knapsack;

/**
 *
 * @author stopka
 */
public class RUN_KnapSack {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Reader in=new Reader("file_in.txt");
        int M=in.readInt();
        int n=in.readInt();
        int[] w=new int[n];
        int[] c=new int[n];
        for(int i=0;i<n;i++){
            w[i]=in.readInt();
        }
        for(int i=0;i<n;i++){
            c[i]=in.readInt();
        }
        Solver s=new Solver(M, w, c);
        boolean[] conf=s.getMaxConf();
        int price=s.getMaxPrice();
        int weight=s.getMaxWeight();
        Writer out=new Writer("file_out.txt");
        out.writeInt(weight);
        out.writeChar(' ');
        out.writeInt(price);
        out.writeChar('\n');
        for(boolean bit:conf){
            if(bit){
                out.writeInt(1);
            }else{
                out.writeInt(0);
            }
            out.writeChar(' ');
        }
        out.writeChar('\n');
        out.writeChar('\n');
    }
    
}
