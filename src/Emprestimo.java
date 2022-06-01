public class Emprestimo {
    //Atributos
    private Conta credor;
    private Conta devedor;
    private boolean aprovado;
    private int parcelas;

    //Métodos Especiais
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

    public boolean isAprovado() {
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

    //Métodos
    public void pedirEmprestimo(Conta c, Conta d) {
        if (c.getSaldo()*0.30 > d.getEmprestimo()){
            this.aprovado = true;
            System.out.println("Emprestimo aprovado!");
            
            this.credor = c;
            this.devedor = d;
            
            float e = d.getEmprestimo();
            float s = d.getSalario();
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

    public void calcularParcelas(float s, float e){
        float p = s*0.30f;
        float n = e/p; 
        System.out.println("Numero de parcelas: " + n);
        System.out.println("Valor das parcelas: " + p);
        emprestar(e);
    }

    public void emprestar(float e){
        this.credor.setSaldo(this.credor.getSaldo()-e);
        System.out.println("Emprestimo efetuado de: " + e);
        System.out.println("Debitado da conta corrente, novo saldo: " + this.credor.getSaldoCC());
        
        this.devedor.setSaldo(this.devedor.getSaldo()+e);
        System.out.println("Emprestimo creditado de: " + e);
        System.out.println("Creditado na conta corrente, novo saldo: " + this.devedor.getSaldoCC());
        System.out.println("========================================================");
    }
}
