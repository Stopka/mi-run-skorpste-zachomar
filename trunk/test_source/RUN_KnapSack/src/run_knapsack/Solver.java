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
public class Solver {
    int M;
    int[] w;
    int[] c;
    int n;
    boolean[] conf;
    
    public Solver(int M,int[] w,int[] c) {
        this.M=M;
        this.w=w;
        this.c=c;
        n=this.w.length;
    }
    
    public void solve(){
        conf = new boolean[n];
        boolean[] max_conf=null;
        
        int max_price=0;
        int price;
        
        //START work loop
        for (int i = 0; i < ((long) 1 << n); i++) {
            //Výpočet dalšího stavu
            for (int j = 0; j < n; j++) {
                if (conf[j]) {
                    conf[j] = false;
                } else {
                    conf[j] = true;
                    break;
                }
            }
            //hodnocení stavu
            if(getMaxWeight()<=M){
                price=getMaxPrice();
                if(price>=max_price){
                    max_price=price;
                    max_conf=conf.clone();
                }
            }
        }
        conf=max_conf;
    }
    
    public boolean[] getMaxConf(){
        if(conf==null){
            solve();
        }
        return conf;
    }
    
    public int getConfPrice(boolean[] conf){
        int result=0;
        for(int i=0;i<n;i++){
            if(conf[i]){
                result+=c[i];
            }
        }
        return result;
    }
    
    public int getMaxPrice(){
        return getConfPrice(getMaxConf());
    }
    
    public int getMaxWeight(){
        return getConfWeight(getMaxConf());
    }
    
    public int getConfWeight(boolean[] conf){
        int result=0;
        for(int i=0;i<n;i++){
            if(conf[i]){
                result+=w[i];
            }
        }
        return result;
    }
    
}
