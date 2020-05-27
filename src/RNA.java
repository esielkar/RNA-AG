public class RNA implements Comparable<RNA>{
    public static final int N_MASK = 0b1111000000000;
    public static final int C_MASK = 0b0000110000000;
    public static final int E_MASK = 0b0000001110000;
    public static final int LR_MASK = 0b0000000001100;
    public static final int M_MASK = 0b0000000000011;
    
    public static final int RNA_MASK = 0b1111111111111;
    
    private int rna;
    private double r;

    public RNA(int rna){
        this.rna = rna & RNA_MASK;
        r = 0;
    }
    
    public int getRNA() {
        return rna;
    }

    public void setRNA(int rna) {
        this.rna = rna;
    }

    public double getResultado() {
        return r;
    }

    public void setResultado(double r) {
        this.r = r;
    }
    
    public int getNeuronas(){
        return ((rna & N_MASK) >> 9) + 3;
    }
    
    public void setNeuronas(int neuronas){
        if (neuronas >= 3 && neuronas <= 18)
            //rna &= (((neuronas - 3 & 0b1111) << 9) ^ RNA_MASK ^ N_MASK);
            rna &= (((neuronas - 3 << 9 ) & N_MASK) ^ RNA_MASK ^ N_MASK);
    }

    public int getCapas(){
        return ((rna & C_MASK) >> 7) + 1;
    }
    
    public void setCapas(int capas){
        if (capas >= 1 && capas <= 4)
            rna &= (((capas - 1 << 7 ) & C_MASK) ^ RNA_MASK ^ C_MASK);
    }
    
    public int getEpocas(){
        return ((rna & E_MASK) >> 4) * 250 + 500;
    }
    
    public double getLearningRate(){
        return ((rna & LR_MASK) >> 2) * 0.5 + 2.0;
    }
    
    public double getMomentum(){
        return (rna & M_MASK) * 0.5 + 2.0;
    }
    
    @Override
    public int compareTo(RNA o) {
        return Double.compare(r, o.getResultado());
    }  
}
