public interface Intefaces {
    public abstract void CadastrarFuncionarios();
    public abstract void abrirConta(int agencia,int num, int tc);
    public abstract void fecharConta(int agencia,int num, int tc);
    public abstract void sacar(int agencia,int num, int tc,float s);
    public abstract void depositar(int agencia,int num, int tc, float d);
    public abstract void usarChequeEspecial();
    public abstract void atualizarSalario(float s);
    public abstract void print();
}
