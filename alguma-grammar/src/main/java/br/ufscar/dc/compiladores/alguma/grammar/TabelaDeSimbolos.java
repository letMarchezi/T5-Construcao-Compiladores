package br.ufscar.dc.compiladores.alguma.grammar;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TabelaDeSimbolos {
    public enum AlgumaGrammar {
        INTEIRO,
        REAL,
        LITERAL,
        VOID,
        REGISTRO,
        LOGICO,
        INVALIDO,
    }

    public enum TipoEntrada {
        VARIAVEL, 
        PROCEDIMENTO,
        FUNCAO,
        BOOL,
        ARRAY
    }

    class EntradaTabelaDeSimbolos {
        String nome;
        AlgumaGrammar tipo;
        TipoEntrada tipoEnt;
        boolean flag_ponteiro = false;
        int tamanhoArray = -1;

        private EntradaTabelaDeSimbolos(String nome, AlgumaGrammar tipo, TipoEntrada tipoEnt, boolean flag_ponteiro, int tamanhoArray) {
            this.nome = nome;
            this.tipo = tipo;
            this.tipoEnt = tipoEnt;
            this.flag_ponteiro = flag_ponteiro;
            this.tamanhoArray = tamanhoArray;
        }
    }

    private final Map<String, EntradaTabelaDeSimbolos> tabela;

    static HashMap<String, ArrayList<AlgumaGrammar>> tabelaFuncaoProcedimento = new HashMap<>();

    
    public TabelaDeSimbolos() {
        this.tabela = new HashMap<>();
    }
    
    public void adicionar(String nome, AlgumaGrammar tipo, TipoEntrada tipoEnt, boolean flag_ponteir, int tamArray) {
        tabela.put(nome, new EntradaTabelaDeSimbolos(nome, tipo, tipoEnt, flag_ponteir, tamArray));
    }
    public void remover(String nome) {
        tabela.remove(nome);
    }
    
    public boolean verificarRegistro(String nomeRegistro) {
        return tabela.containsKey(nomeRegistro);
    }

    public int verificarTamanhoArray(String nome) {
        return tabela.get(nome).tamanhoArray;
    }
    public boolean existe(String nome) {
        return tabela.containsKey(nome);
    }
    
    public AlgumaGrammar verificar(String nome) {
        return tabela.get(nome).tipo;
    }

    public TipoEntrada verificartipoEnt(String nome) {
        return tabela.get(nome).tipoEnt;
    }

    public boolean verificar_ponteiro(String nome) {
        return tabela.get(nome).flag_ponteiro;
    }

    
}
