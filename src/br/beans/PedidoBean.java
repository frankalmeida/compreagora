package br.beans;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.primefaces.model.DualListModel;

import br.AtendimentoLugares.Bairro;
import br.AtendimentoLugares.EmpresaAtendimento;
import br.AtendimentoLugares.EmpresaAtendimentoRN;
import br.Cliente.Cliente;
import br.Cliente.ClienteRN;
import br.Empresa.Empresa;
import br.Empresa.EmpresaRN;
import br.Empresa.Categoria.Categoria;
import br.Empresa.Categoria.CategoriaENUM;
import br.Empresa.FormaDePagamento.FormaDePagamento;
import br.Empresa.FormaDePagamento.FormaDePagamentoRN;
import br.Pedido.Pedido;
import br.Pedido.PedidoRN;
import br.PedidoProduto.PedidoProduto;
import br.Produto.Agua;
import br.Produto.Bebida;
import br.Produto.Gas;
import br.Produto.Lanche;
import br.Produto.Marmitex;
import br.Produto.Pizza;
import br.Produto.Produto;
import br.Produto.ProdutoRN;
import br.Produto.Filtro.WithStatusIndisponivel;
import br.ProdutoAvulso.Avulso;
import br.ProdutoAvulso.AvulsoRN;

@ManagedBean(name = "pedidoBean")
@SessionScoped
public class PedidoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final int TOTAL_PRODUTO = 13;
	private DualListModel<Avulso> avulsoDual;
	private Cliente cliente = null;
	private Empresa empresa = null;
	private int itemMenu;
	private Lanche lanche;
	private Bebida bebida;
	private Pizza pizza;
	private Agua agua;
	private Gas gas;
	private Marmitex marmitex;
	private List<Marmitex> marmitexs;
	private ListDataModel<Marmitex> marmitexsDM;
	private Pedido pedido;
	private PedidoProduto pedidoProduto;
	private String endCliente = null;
	private DataModel<Lanche> lanchesDM;
	private DataModel<Bebida> bebidasDM;
	private EmpresaRN empresaRN;
	private List<Lanche> pedidoLanches;
	private List<Bebida> pedidoBebidas;
	private List<PedidoProduto> pedidoProdutos;
	private DataModel<Pedido> pedidosDM;
	private DataModel<Produto> produtosDM;
	private String cidadeEntrega;
	private String bairroEntrega;
	private float valorTotalMaisTaxa;
	private List<Bebida> bebidas;
	private List<Lanche> lanches;
	private List<Pizza> pizzas;
	private List<Agua> listAgua;
	private List<Gas> listGas;
	private Avulso avulso;
	private boolean empresaAtendeBairro;
	private List<Long> tiposProdutos;
	private Integer categoriaEmpresa;
	private List<String> formasDePagamento;
	private String formaDePagamento;
	private List<FormaDePagamento> formasDePagamentoDM;
	private ListDataModel<Pizza> listPizzaDM;
	private ListDataModel<Agua> listAguaDM;
	private ListDataModel<Gas> listGasDM;
	private float avulsoValorTotal;
	private float troco;
	private int idBairro;
	private Bairro bairro;

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public void setCategoriaEmpresa(Integer categoriaEmpresa) {
		this.categoriaEmpresa = categoriaEmpresa;
	}

	public int getIdBairro() {
		return idBairro;
	}

	public void setIdBairro(int idBairro) {
		this.idBairro = idBairro;
	}

	public int getItemMenu() {
		return itemMenu;
	}

	public void setItemMenu(int itemMenu) {
		this.itemMenu = itemMenu;
	}

	public float getAvulsoValorTotal() {
		return avulsoValorTotal;
	}

	public void setAvulsoValorTotal(float avulsoValorTotal) {
		this.avulsoValorTotal = avulsoValorTotal;
	}

	public void atualizaAvulsoTotal() {

		BigDecimal avulsoBD = new BigDecimal(pedidoProduto.getValor());
		for (Avulso x : avulsoDual.getTarget()) {

			avulsoBD.add(new BigDecimal(x.getValor()));
		}
		avulsoValorTotal = avulsoBD.floatValue();
	}

	public Marmitex getMarmitex() {
		return marmitex;
	}

	public List<Marmitex> getMarmitexs() {
		return marmitexs;
	}

	public ListDataModel<Marmitex> getMarmitexsDM() {
		return marmitexsDM;
	}

	public void setMarmitex(Marmitex marmitex) {
		this.marmitex = marmitex;
	}

	public void setMarmitexs(List<Marmitex> marmitexs) {
		this.marmitexs = marmitexs;
	}

	public void setMarmitexsDM(ListDataModel<Marmitex> marmitexsDM) {
		this.marmitexsDM = marmitexsDM;
	}

	public List<FormaDePagamento> getFormasDePagamentoDM() {
		return formasDePagamentoDM;
	}

	public void setFormasDePagamentoDM(
			List<FormaDePagamento> formasDePagamentoDM) {
		this.formasDePagamentoDM = formasDePagamentoDM;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setAvulso(Avulso avulso) {
		this.avulso = avulso;
	}

	public Agua getAgua() {
		return agua;
	}

	public Gas getGas() {
		return gas;
	}

	public List<Pizza> getPizzas() {
		return pizzas;
	}

	public List<Agua> getListAgua() {
		return listAgua;
	}

	public List<Gas> getListGas() {
		return listGas;
	}

	public ListDataModel<Pizza> getListPizzaDM() {
		return listPizzaDM;
	}

	public ListDataModel<Agua> getListAguaDM() {
		return listAguaDM;
	}

	public ListDataModel<Gas> getListGasDM() {
		return listGasDM;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	public void setAgua(Agua agua) {
		this.agua = agua;
	}

	public void setGas(Gas gas) {
		this.gas = gas;
	}

	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}

	public void setListAgua(List<Agua> listAgua) {
		this.listAgua = listAgua;
	}

	public void setListGas(List<Gas> listGas) {
		this.listGas = listGas;
	}

	public void setListPizzaDM(ListDataModel<Pizza> listPizzaDM) {
		this.listPizzaDM = listPizzaDM;
	}

	public void setListAguaDM(ListDataModel<Agua> listAguaDM) {
		this.listAguaDM = listAguaDM;
	}

	public void setListGasDM(ListDataModel<Gas> listGasDM) {
		this.listGasDM = listGasDM;
	}

	public List<String> getFormasDePagamento() {
		return formasDePagamento;
	}

	public void setFormasDePagamento(List<String> formasDePagamento) {
		this.formasDePagamento = formasDePagamento;
	}

	public String getFormaDePagamento() {
		return formaDePagamento;
	}

	public void setFormaDePagamento(String formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getCategoriaEmpresa() {
		return categoriaEmpresa;
	}

	public void setCategoriaEmpresa(int categoriaEmpresa) {
		this.categoriaEmpresa = categoriaEmpresa;
	}

	public DualListModel<Avulso> getAvulsoDual() {
		return avulsoDual;
	}

	public void setAvulsoDual(DualListModel<Avulso> avulsoDual) {
		this.avulsoDual = avulsoDual;
	}

	public void atualizaAvulsos() {

		int idEmpresa = empresa.getIdEmpresa();
		CategoriaENUM tipoAvulso = pedidoProduto.getQualificacao();

		AvulsoRN avulsoRN = new AvulsoRN();

		List<Avulso> avulsoSorce = avulsoRN.listar(idEmpresa, tipoAvulso);

		List<Avulso> avulsoTarget = pedidoProduto.convertToAvulso();

		avulsoDual = new DualListModel<Avulso>(avulsoSorce, avulsoTarget);
	}

	public void removerAvulso() {

		pedidoProduto.removeAvulso(avulso);

		atualizaAvulsos();
	}

	public Avulso getAvulso() {
		return avulso;
	}

	public List<PedidoProduto> getPedidoProdutos() {
		return pedidoProdutos;
	}

	public void setPedidoProdutos(List<PedidoProduto> pedidoProdutos) {
		this.pedidoProdutos = pedidoProdutos;
	}

	public float getValorTotalMaisTaxa() {
		valorTotalMaisTaxa = pedido.getValorTotalMaisTaxa();
		return valorTotalMaisTaxa;
	}

	public String getCidadeEntrega() {
		return cidadeEntrega;
	}

	public void setCidadeEntrega(String cidadeEntrega) {
		this.cidadeEntrega = cidadeEntrega;
	}

	public String getBairroEntrega() {
		return bairroEntrega;
	}

	public void setBairroEntrega(String bairroEntrega) {
		this.bairroEntrega = bairroEntrega;
	}

	public DataModel<Produto> getProdutosDM() {
		return produtosDM;
	}

	public void setProdutosDM(DataModel<Produto> produtosDM) {
		this.produtosDM = produtosDM;
	}

	public String getEndCliente() {
		return endCliente;
	}

	public void setEndCliente(String endCliente) {
		this.endCliente = endCliente;
	}

	public DataModel<Pedido> getPedidosDM() {
		return pedidosDM;
	}

	public void setPedidosDM(DataModel<Pedido> pedidosDM) {
		this.pedidosDM = pedidosDM;
	}

	public PedidoBean() {
		novoPedido();
	}

	public EmpresaRN getEmpresaRN() {
		return empresaRN;
	}

	public void setEmpresaRN(EmpresaRN empresaRN) {
		this.empresaRN = empresaRN;
	}

	public List<Lanche> getPedidoLanches() {
		return pedidoLanches;
	}

	public void setPedidoLanches(List<Lanche> pedidoLanches) {
		this.pedidoLanches = pedidoLanches;
	}

	public List<Bebida> getPedidoBebidas() {
		return pedidoBebidas;
	}

	public void setPedidoBebidas(List<Bebida> pedidoBebidas) {
		this.pedidoBebidas = pedidoBebidas;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Empresa getEmpresa() {
		if (empresa == null) {
			FacesContext fContext = FacesContext.getCurrentInstance();
			ExternalContext extContext = fContext.getExternalContext();
			try {
				extContext.redirect(extContext.getRequestContextPath()
						+ "/principal.jsf");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Lanche getLanche() {
		return lanche;
	}

	public void setLanche(Lanche lanche) {
		this.lanche = lanche;
	}

	public Bebida getBebida() {
		return bebida;
	}

	public void setBebida(Bebida bebida) {
		this.bebida = bebida;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public DataModel<Lanche> getLanchesDM() {
		return lanchesDM;
	}

	public void setLanchesDM(DataModel<Lanche> lanchesDM) {
		this.lanchesDM = lanchesDM;
	}

	public DataModel<Bebida> getBebidasDM() {
		return bebidasDM;
	}

	public void setBebidasDM(DataModel<Bebida> bebidasDM) {
		this.bebidasDM = bebidasDM;
	}

	public FormaDePagamento converteFormaDePagamento() {
		FormaDePagamentoRN formaDePagamentoRN = new FormaDePagamentoRN();
		return formaDePagamentoRN.peloTipo(formaDePagamento);

	}

	public void novo() {

		tiposProdutos = new ArrayList<Long>();
		itemMenu = 0;
		EmpresaRN empresaRN = new EmpresaRN();
		Empresa empresaTemp = empresaRN.getEmpresa(empresa.getIdEmpresa());
		empresaTemp.getCategorias().size();

		converteTipoProduto(empresaTemp.getCategorias());
		empresaTemp.getFormasDePagamento().size();
		formasDePagamento = new ArrayList<String>();
		formasDePagamentoDM = empresaTemp.getFormasDePagamento();
		for (FormaDePagamento x : formasDePagamentoDM) {
			formasDePagamento.add(x.getTipo());
		}
		formaDePagamento = "Dinheiro";
		pedido = new Pedido();
		pedidoProdutos = new ArrayList<PedidoProduto>();
		pedidoProduto = new PedidoProduto();

		avulsoDual = new DualListModel<Avulso>();
	//	atualizaTaxaEntregaFirst();
	}

	public void converteTipoProduto(List<Categoria> tiposProdutos) {
		for (Categoria tp : tiposProdutos) {
			this.tiposProdutos.add((long) tp.getTipoCategoria().ordinal());
		}
	}

	public boolean verificaTiposProdutos(Long tipoProduto) {

		return tiposProdutos.contains(tipoProduto) ? true : false;
	}

	public String adicionaEmpresa() {
		String url = ("../principal.jsf");
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		try {

			novo();

			Map<Integer, String> mapTipoEnum = new HashMap<Integer, String>();
			mapTipoEnum.put(CategoriaENUM.Lanche.ordinal(), mudaTelaLanche());
			mapTipoEnum.put(CategoriaENUM.Pizza.ordinal(), mudaTelaPizza());
			mapTipoEnum.put(CategoriaENUM.Gas.ordinal(), mudaTelaGas());
			mapTipoEnum.put(CategoriaENUM.Bebida.ordinal(), mudaTelaBebida());
			mapTipoEnum.put(CategoriaENUM.Agua.ordinal(), mudaTelaAgua());
			mapTipoEnum.put(CategoriaENUM.Marmitex.ordinal(),
					mudaTelaMarmitex());

			itemMenu = categoriaEmpresa;
			return mapTipoEnum.get(categoriaEmpresa);

		} catch (Exception e) {
			try {
				ec.redirect(url);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;
	}

	public String mudaTelaMarmitex() {

		if (tiposProdutos.contains((long) CategoriaENUM.Marmitex.ordinal())) {
			ProdutoRN produtoRN = new ProdutoRN();
			produtoRN.alterarFiltro(new WithStatusIndisponivel());
			this.marmitexsDM = new ListDataModel<Marmitex>(
					produtoRN.listarMarmitex(empresa.getIdEmpresa()));
			this.itemMenu = 2;
			return "/paginas/categoria/escolhaProduto/SelecionaProdutoMarmitex?faces-redirect=true";
		}
		return null;
	}

	public String mudaTelaBebida() {

		if (tiposProdutos.contains((long) CategoriaENUM.Bebida.ordinal())) {
			ProdutoRN produtoRN = new ProdutoRN();
			produtoRN.alterarFiltro(new WithStatusIndisponivel());
			this.bebidasDM = new ListDataModel<Bebida>(
					produtoRN.listarBebida(empresa.getIdEmpresa()));
			this.itemMenu = 3;
			return "/paginas/categoria/escolhaProduto/SelecionaProdutoBebida?faces-redirect=true";
		}
		return null;
	}

	public String mudaTelaLanche() {
		if (tiposProdutos.contains((long) CategoriaENUM.Lanche.ordinal())) {
			ProdutoRN produtoRN = new ProdutoRN();
			produtoRN.alterarFiltro(new WithStatusIndisponivel());
			this.lanches = produtoRN.listarLanche(empresa.getIdEmpresa());
			this.lanchesDM = new ListDataModel<Lanche>(
					produtoRN.listarLanche(empresa.getIdEmpresa()));
			this.itemMenu = 0;
			return "/paginas/categoria/escolhaProduto/SelecionaProdutoLanche?faces-redirect=true";
		}
		return null;
	}

	public String mudaTelaPizza() {
		if (tiposProdutos.contains((long) CategoriaENUM.Pizza.ordinal())) {
			ProdutoRN produtoRN = new ProdutoRN();
			produtoRN.alterarFiltro(new WithStatusIndisponivel());
			this.listPizzaDM = new ListDataModel<Pizza>(
					produtoRN.listarPizza(empresa.getIdEmpresa()));
			this.itemMenu = 1;
			return "/paginas/categoria/escolhaProduto/SelecionaProdutoPizza?faces-redirect=true";
		}
		return null;
	}

	public String mudaTelaAgua() {
		if (tiposProdutos.contains((long) CategoriaENUM.Agua.ordinal())) {
			ProdutoRN produtoRN = new ProdutoRN();
			produtoRN.alterarFiltro(new WithStatusIndisponivel());
			this.listAguaDM = new ListDataModel<Agua>(
					produtoRN.listarAgua(empresa.getIdEmpresa()));
			this.itemMenu = 4;
			return "/paginas/categoria/escolhaProduto/SelecionaProdutoAgua?faces-redirect=true";
		}
		return null;
	}

	public String mudaTelaGas() {
		if (tiposProdutos.contains((long) CategoriaENUM.Gas.ordinal())) {
			ProdutoRN produtoRN = new ProdutoRN();
			produtoRN.alterarFiltro(new WithStatusIndisponivel());
			this.listGasDM = new ListDataModel<Gas>(produtoRN.listarGas(empresa
					.getIdEmpresa()));
			this.itemMenu = 5;
			return "/paginas/categoria/escolhaProduto/SelecionaProdutoGas?faces-redirect=true";
		}
		return null;
	}

	public String salvarPedido() {

		PedidoRN pedidoRN = new PedidoRN();
		pedido.setFormaDePagamento(converteFormaDePagamento());
		pedido.setTroco(troco);
		pedido.setEmpresa(empresa);
		pedido.setCliente(cliente);
		pedidoRN.salvar(pedido);
		novoPedido();

		return "/paginas/publico/meusPedidos.jsf?faces-redirect=true";
	}

	public void adicionaLanche() {
		if (verificaQuantidade()) {
			Produto produto = null;
			produto = (Lanche) (lanchesDM.getRowData());
			PedidoProduto pedidoProdutoTemp = new PedidoProduto();
			pedidoProdutoTemp.addProduto(produto);
			pedidoProdutos.add(pedidoProdutoTemp);
			pedido.setPedidoProdutos(pedidoProdutos);
			pedido.calcularTotal();

		}
	}

	public void adicionaPizza() {
		if (verificaQuantidade()) {
			Produto produto = null;
			produto = (Pizza) (listPizzaDM.getRowData());
			PedidoProduto pedidoProdutoTemp = new PedidoProduto();
			pedidoProdutoTemp.addProduto(produto);
			pedidoProdutos.add(pedidoProdutoTemp);
			pedido.setPedidoProdutos(pedidoProdutos);
			pedido.calcularTotal();
		}
	}

	public void adicionaMarmitex() {
		if (verificaQuantidade()) {
			Produto produto = null;
			produto = (Marmitex) (marmitexsDM.getRowData());
			PedidoProduto pedidoProdutoTemp = new PedidoProduto();
			pedidoProdutoTemp.addProduto(produto);
			pedidoProdutos.add(pedidoProdutoTemp);
			pedido.setPedidoProdutos(pedidoProdutos);
			pedido.calcularTotal();
		}
	}

	public void adicionaAgua() {
		if (verificaQuantidade()) {
			Produto produto = null;
			produto = (Agua) (listAguaDM.getRowData());
			PedidoProduto pedidoProdutoTemp = new PedidoProduto();
			pedidoProdutoTemp.addProduto(produto);
			pedidoProdutos.add(pedidoProdutoTemp);
			pedido.setPedidoProdutos(pedidoProdutos);
			pedido.calcularTotal();
		}
	}

	public void adicionaGas() {
		if (verificaQuantidade()) {
			Produto produto = null;
			produto = (Gas) (listGasDM.getRowData());
			PedidoProduto pedidoProdutoTemp = new PedidoProduto();
			pedidoProdutoTemp.addProduto(produto);
			pedidoProdutos.add(pedidoProdutoTemp);
			pedido.setPedidoProdutos(pedidoProdutos);
			pedido.calcularTotal();
		}
	}

	public void adicionaBebida() {

		if (verificaQuantidade()) {
			Produto produto = null;
			produto = (Bebida) (bebidasDM.getRowData());
			PedidoProduto pedidoProdutoTemp = new PedidoProduto();
			pedidoProdutoTemp.addProduto(produto);
			pedidoProdutos.add(pedidoProdutoTemp);
			pedido.setPedidoProdutos(pedidoProdutos);
			pedido.calcularTotal();
		}

	}

	public void personalizaLanche() {
		if (verificaQuantidade()) {
			lanche = (Lanche) (lanchesDM.getRowData());
			pedidoProduto = new PedidoProduto();
			pedidoProduto.addProduto(lanche);

			atualizaAvulsoTotal();
			atualizaAvulsos();

		}
	}

	public void personalizaMarmitex() {
		if (verificaQuantidade()) {
			marmitex = (Marmitex) (marmitexsDM.getRowData());
			pedidoProduto = new PedidoProduto();
			pedidoProduto.addProduto(marmitex);
			atualizaAvulsoTotal();
			atualizaAvulsos();
		}
	}

	public void personalizaBebida() {
		if (verificaQuantidade()) {
			bebida = (Bebida) (bebidasDM.getRowData());
			pedidoProduto = new PedidoProduto();
			pedidoProduto.addProduto(bebida);
			atualizaAvulsoTotal();
			atualizaAvulsos();
		}
	}

	public void personalizaAgua() {
		if (verificaQuantidade()) {
			agua = (Agua) (listAguaDM.getRowData());
			pedidoProduto = new PedidoProduto();
			pedidoProduto.addProduto(agua);
			atualizaAvulsoTotal();
			atualizaAvulsos();
		}
	}

	public void personalizaPizza() {
		if (verificaQuantidade()) {
			pizza = (Pizza) (listPizzaDM.getRowData());
			pedidoProduto = new PedidoProduto();
			pedidoProduto.addProduto(pizza);
			atualizaAvulsoTotal();
			atualizaAvulsos();
		}
	}

	public void personalizaGas() {
		if (verificaQuantidade()) {
			gas = (Gas) (listGasDM.getRowData());
			pedidoProduto = new PedidoProduto();
			pedidoProduto.addProduto(gas);
			atualizaAvulsoTotal();
			atualizaAvulsos();
		}
	}

	public void salvaProdutoPersonalizado() {
		// pedidoProduto.setAvulsos(avulsoDual.getTarget());
		pedidoProduto.convertToPedidoProdutoAvulso(avulsoDual.getTarget());

		pedidoProdutos.add(pedidoProduto);
		pedido.setPedidoProdutos(pedidoProdutos);
		pedido.calcularTotal();
	}

	public boolean verificaQuantidade() {

		if (pedido.getPedidoProdutos().size() < TOTAL_PRODUTO) {

			return true;
		} else {

			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									FacesMessage.SEVERITY_ERROR,
									"Quantidade de Produto por pedido excedido..",
									" "));
			return false;
		}

	}

	public PedidoProduto getPedidoProduto() {
		if (pedidoProduto == null) {
			pedidoProduto = new PedidoProduto();
		}
		return pedidoProduto;
	}

	public void setPedidoProduto(PedidoProduto pedidoProduto) {
		this.pedidoProduto = pedidoProduto;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void clienteLogado() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		String login = external.getRemoteUser();

		if (this.cliente == null || !login.equals(this.cliente.getEmail())) {

			if (login != null) {
				ClienteRN usuarioRN = new ClienteRN();
				this.cliente = usuarioRN.buscarPorEmail(login);
				
			}
		}
		populandoEnderecoPedido();
	}

	public void populandoEnderecoPedido() {
	
		pedido.setBairro(cliente.getBairro());
		pedido.setLogradouro(cliente.getLogradouro());
		pedido.setCep(cliente.getCep());
		pedido.setCidade(cliente.getCidade());
		pedido.setComplemento(cliente.getComplemento());
		pedido.setNumero(cliente.getNumero());
		pedido.setUF(cliente.getUF());
	}

	public void novoEndereco() {
		pedido.setBairro("");
		pedido.setLogradouro("");
		pedido.setCep("");
		pedido.setCidade("");
		pedido.setComplemento("");
		pedido.setNumero("");
		pedido.setUF("");
	}

	public void novoPedido() {

		pedidoLanches = new ArrayList<Lanche>();
		pedidoBebidas = new ArrayList<Bebida>();
		pedidoProdutos = new ArrayList<PedidoProduto>();
		pedido = new Pedido();
		empresa = new Empresa();
		empresaAtendeBairro = false;

	}

	public void deletarProduto() {

		if (pedidoProduto != null) {
			pedido.removeValor(pedidoProduto.getValor());
			pedidoProdutos.remove(pedidoProduto);
			pedidoProduto = null;
		}

	}

	public void atualizaTaxaEntregaEnderecoCliente() {
		clienteLogado();
		int idEmpresa = empresa.getIdEmpresa();

		EmpresaAtendimentoRN empresaAtendimentoRN = new EmpresaAtendimentoRN();

		EmpresaAtendimento empAtend = new EmpresaAtendimento();
		empAtend = empresaAtendimentoRN.empresaAtendimentoEmpresaComBairro(
				idEmpresa, pedido.getCidade(), pedido.getBairro());

		if (empAtend != null) {
			pedido.setTaxa(empAtend.getTaxa());
			empresaAtendeBairro = true;

		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Empresa n�o atende a essa localidade", " "));
			empresaAtendeBairro = false;
		}	
	}

	public void atualizaTaxaEntrega() {
		
		int idEmpresa = empresa.getIdEmpresa();

		EmpresaAtendimentoRN empresaAtendimentoRN = new EmpresaAtendimentoRN();

		EmpresaAtendimento empAtend = new EmpresaAtendimento();
		empAtend = empresaAtendimentoRN.empresaAtendimentoEmpresaComBairro(
				idEmpresa, pedido.getCidade(), pedido.getBairro());

		if (empAtend != null) {
			pedido.setTaxa(empAtend.getTaxa());
			empresaAtendeBairro = true;

		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Empresa n�o atende a essa localidade", " "));
			empresaAtendeBairro = false;
		}

	}

	public void salvarObservacao() {
		int idElemento;
		pedidoProduto.convertToPedidoProdutoAvulso(avulsoDual.getTarget());
		idElemento = pedidoProdutos.lastIndexOf(pedidoProduto);
		pedidoProdutos.set(idElemento, pedidoProduto);

		pedido.setPedidoProdutos(pedidoProdutos);
		pedido.calcularTotal();
	}

	public boolean verificaValorMin() {

		return pedido.getValorTotal() > 6 ? true : false;
	}

	public boolean verificaEstoque() {

		return true;
	}

	public float getTroco() {
		return troco;
	}

	public void setTroco(float troco) {
		this.troco = troco;
	}

	public boolean habilitarBotaoSalvar() {
		boolean ii = false;

		if ((troco > pedido.getValorTotal()) || (troco == 0)) {
			ii = true;
		}

		return (ii && (empresaAtendeBairro));
	}

	public void detalhesProdutoMarmitex() {
		marmitex = (Marmitex) (marmitexsDM.getRowData());
	}

	public void detalhesProdutoLanche() {
		lanche = (Lanche) (lanchesDM.getRowData());
	}

	public void detalhesProdutoBebida() {
		bebida = (Bebida) (bebidasDM.getRowData());
	}

	public void detalhesProdutoPizza() {
		pizza = (Pizza) (listPizzaDM.getRowData());
	}

	public void detalhesProdutoAgua() {
		agua = (Agua) (listAguaDM.getRowData());
	}

	public void detalhesProdutoGas() {
		gas = (Gas) (listGasDM.getRowData());
	}

	public List<Bebida> getBebidas() {
		return bebidas;
	}

	public void setBebidas(List<Bebida> bebidas) {
		this.bebidas = bebidas;
	}

	public List<Lanche> getLanches() {
		return lanches;
	}

	public void setLanches(List<Lanche> lanches) {
		this.lanches = lanches;
	}

}
