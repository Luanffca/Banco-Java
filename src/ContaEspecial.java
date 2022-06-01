public class ContaEspecial extends Corrente implements Redimentos{

    public ContaEspecial(Cliente nome, Integer numero, Integer agencia) {
        super();
        setLimite(5000d);
        setTaxa(0.25d);
    }

    @Override
    public void atualizar(Double taxaRendimento) {
        saldo += saldo * (taxaRendimento / 100);
        
    }
    
}
