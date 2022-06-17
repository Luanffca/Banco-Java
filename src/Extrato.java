
public class Extrato {

	private String data;
	private String transacao;
	private Double valor;
	private Double cheque;
	private Double saldoAnterior;
	private Double saldoAtualizado;
	private String detalhes;

	Extrato(String data, String transacao, Double valor, Double cheque, Double saldoAnterior, Double saldoAtualizado,
			String detalhes) {
		this.data = data;
		this.transacao = transacao;
		this.valor = valor;
		this.cheque = cheque;
		this.saldoAnterior = saldoAnterior;
		this.saldoAtualizado = saldoAtualizado;
		this.detalhes = detalhes;
	}

	public void setCheque(Double cheque) {
		this.cheque = cheque;
	}

	public Double getCheque() {
		return cheque;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getValor() {
		return valor;
	}

	public Double getSaldoAnterior() {
		return saldoAnterior;
	}

	public void setSaldoAnterior(Double saldoAnterior) {
		this.saldoAnterior = saldoAnterior;
	}

	public Double getSaldoAtualizado() {
		return saldoAtualizado;
	}

	public void setSaldoAtualizado(Double saldoAtualizado) {
		this.saldoAtualizado = saldoAtualizado;
	}

	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getTransacao() {
		return this.transacao;
	}

	public void setTransacao(String transacao) {
		this.transacao = transacao;
	}

	public String getDetalhes() {
		return this.detalhes;
	}

	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}

}
