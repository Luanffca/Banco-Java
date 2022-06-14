public class Emprestimo {

    private Conta credor;
    private Conta devedor;
    private boolean aprovado;
    private int parcelas;
    private double emprestimo;

    
    public Conta getCredor(){    
        return credor;  
    }

    public void setCredor(Conta credor) {
        this.credor = credor;
    }

    public Conta getDevedor() {
        return devedor;
    }

    public void setDevedor(Conta devedor) {
        this.devedor = devedor;
    }

    public boolean getAprovado() {
        return aprovado;
    }

    public void setAprovado(boolean aprovado) {
        this.aprovado = aprovado;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {    
        this.parcelas = parcelas;
    }

    public double getEmprestimo() {
        return emprestimo;
    }

    public void setEmprestimo(double emprestimo) {    
        this.emprestimo = emprestimo;
    }

    //Métodos
    public void pedirEmprestimo(Conta c, Conta d) {
        if (c.getSaldo()*0.30 > getEmprestimo() && c.getOperacao() == "001"){
            this.aprovado = true;
            System.out.println("Emprestimo aprovado!");
            
            this.credor = c;
            
            double e = getEmprestimo();
            double s = d.getSaldo();
            calcularParcelas(s, e);
        }
        else{
            System.out.println("Credor não aprovado para emprestar.");
            System.out.println("========================================================");
            this.aprovado = false;
            this.credor = null;
            this.devedor = null;
        }
    }

    public void calcularParcelas(double s, double e){
        double p = s*0.30f;
        double n = e/p; 
        System.out.println("Numero de parcelas: " + n);
        System.out.println("Valor das parcelas: " + p);
        emprestar(e);
    }

    public void emprestar(double e){
        this.credor.setSaldo(this.credor.getSaldo()-e);
        System.out.println("Emprestimo efetuado de: " + e);
        System.out.println("Debitado da conta corrente, novo saldo: " + this.credor.getSaldo());
        
        this.devedor.setSaldo(this.devedor.getSaldo()+e);
        System.out.println("Emprestimo creditado de: " + e);
        System.out.println("Creditado na conta corrente, novo saldo: " + this.devedor.getSaldo());
        System.out.println("========================================================");
    }
}